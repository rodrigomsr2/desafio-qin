package com.tenniscourts.reservations;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenniscourts.schedules.Schedule;

import java.time.OffsetDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findBySchedule_Id(Long scheduleId);

    List<Reservation> findByReservationStatusAndSchedule_StartDateTimeGreaterThanEqualAndSchedule_EndDateTimeLessThanEqual(ReservationStatus reservationStatus, OffsetDateTime startDateTime, OffsetDateTime endDateTime);

    List<Reservation> findByScheduleIn(List<Schedule> schedules);
    
//    List<Reservation> findByStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndTennisCourt(LocalDateTime startDateTime, LocalDateTime endDateTime, TennisCourt tennisCourt);
}
