package com.tenniscourts.reservations;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.exceptions.ScheduleNotFoundException;
import com.tenniscourts.guests.Guest;
import com.tenniscourts.reservations.dto.CreateReservationRequestDTO;
import com.tenniscourts.reservations.dto.ReservationDTO;
import com.tenniscourts.schedules.Schedule;
import com.tenniscourts.schedules.ScheduleService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;
    
    private final ScheduleService scheduleService;

    @Transactional
    public List<ReservationDTO> bookReservation(@RequestBody CreateReservationRequestDTO dto) {
    	List<ReservationDTO> reservationsSaved = new ArrayList<>();
    	
		List<Long> tennisCourtsIds = dto.getTennisCourts().stream()
				.map(rdto -> rdto.getId())
				.collect(Collectors.toList());

		List<Schedule> schedules = this.scheduleService.findSchedulesBy(dto, tennisCourtsIds);
		
		if(tennisCourtsIds.size() > schedules.size()) {
			throw new ScheduleNotFoundException("One or more schedules are not registered. Please check '/schedules/free' for available schedules.");
		}
		
		List<Reservation> reservations = reservationRepository.findByScheduleIn(schedules);
		
		if(!reservations.isEmpty()) {
			throw new ScheduleNotFoundException("One or more schedules have already been taken. Please check '/schedules/free' for available schedules.");
		}
		
		schedules.stream().forEach(s -> {
			Guest guest = new Guest();
			guest.setId(dto.getGuest().getId());
			
			Reservation reservation = Reservation.builder()
													.schedule(s)
													.guest(guest)
													.value(BigDecimal.valueOf(10.00))
													.refundValue(BigDecimal.valueOf(0.00))
													.reservationStatus(ReservationStatus.READY_TO_PLAY)
													.build();
			
			reservation = this.reservationRepository.save(reservation);
			reservationsSaved.add(this.reservationMapper.map(reservation));
		});
    	
        return reservationsSaved;
    }

    public ReservationDTO findReservation(Long reservationId) {
        return reservationRepository.findById(reservationId).map(reservationMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Reservation not found.");
        });
    }

    public ReservationDTO cancelReservation(Long reservationId) {
        return reservationMapper.map(this.cancel(reservationId));
    }

    private Reservation cancel(Long reservationId) {
        return reservationRepository.findById(reservationId).map(reservation -> {

            this.validateCancellation(reservation);

            BigDecimal refundValue = getRefundValue(reservation);
            return this.updateReservation(reservation, refundValue, ReservationStatus.CANCELLED);

        }).orElseThrow(() -> {
            throw new EntityNotFoundException("Reservation not found.");
        });
    }

    private Reservation updateReservation(Reservation reservation, BigDecimal refundValue, ReservationStatus status) {
        reservation.setReservationStatus(status);
        reservation.setValue(reservation.getValue().subtract(refundValue));
        reservation.setRefundValue(refundValue);

        return reservationRepository.save(reservation);
    }

    private void validateCancellation(Reservation reservation) {
        if (!ReservationStatus.READY_TO_PLAY.equals(reservation.getReservationStatus())) {
            throw new IllegalArgumentException("Cannot cancel/reschedule because it's not in ready to play status.");
        }

        if (reservation.getSchedule().getStartDateTime().isBefore(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Can cancel/reschedule only future dates.");
        }
    }

    public BigDecimal getRefundValue(Reservation reservation) {
        long hours = ChronoUnit.HOURS.between(OffsetDateTime.now(), reservation.getSchedule().getStartDateTime());

        if (hours >= 24) {
            return reservation.getValue();
        }

        return BigDecimal.ZERO;
    }

    /*TODO: This method actually not fully working, find a way to fix the issue when it's throwing the error:
            "Cannot reschedule to the same slot.*/
    public ReservationDTO rescheduleReservation(Long previousReservationId, Long scheduleId) {
        Reservation previousReservation = cancel(previousReservationId);

        if (scheduleId.equals(previousReservation.getSchedule().getId())) {
            throw new IllegalArgumentException("Cannot reschedule to the same slot.");
        }

        previousReservation.setReservationStatus(ReservationStatus.RESCHEDULED);
        reservationRepository.save(previousReservation);

        ReservationDTO newReservation = null;
        
//        ReservationDTO newReservation = bookReservation(CreateReservationRequestDTO.builder()
//                .guestId(previousReservation.getGuest().getId())
//                .scheduleId(scheduleId)
//                .build());
        newReservation.setPreviousReservation(reservationMapper.map(previousReservation));
        return newReservation;
    }
}
