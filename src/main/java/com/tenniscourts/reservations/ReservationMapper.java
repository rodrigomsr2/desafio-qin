package com.tenniscourts.reservations;

import org.mapstruct.Mapper;

import com.tenniscourts.reservations.dto.ReservationDTO;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation map(ReservationDTO source);

    ReservationDTO map(Reservation source);
}
