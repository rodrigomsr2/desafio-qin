package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import com.tenniscourts.reservations.dto.CreateReservationRequestDTO;
import com.tenniscourts.reservations.dto.ReservationDTO;
import com.tenniscourts.schedules.dto.ScheduleDTO;

import lombok.AllArgsConstructor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/reservations")
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<ReservationDTO> bookReservation(@RequestBody CreateReservationRequestDTO createReservationRequestDTO) {
        return reservationService.bookReservation(createReservationRequestDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> findReservation(@PathVariable("id") Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }

    @DeleteMapping
    public ResponseEntity<ReservationDTO> cancelReservation(Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @PutMapping("/{reservationId}/schedules/{scheduleId}")
    public ResponseEntity<ReservationDTO> rescheduleReservation(@RequestBody ScheduleDTO ScheduleDto, @PathVariable("reservationId") Long reservationId, @PathVariable("scheduleId") Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}
