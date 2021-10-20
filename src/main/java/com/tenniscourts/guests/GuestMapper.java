package com.tenniscourts.guests;

import org.mapstruct.Mapper;

import com.tenniscourts.guests.dto.CreateGuestDTO;
import com.tenniscourts.guests.dto.GuestOutputDTO;
import com.tenniscourts.guests.dto.UpdateGuestDTO;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    GuestOutputDTO map(Guest guest);

    Guest map(CreateGuestDTO createGuestDTO);
    
    Guest map(UpdateGuestDTO updateGuestDTO);

//    @InheritInverseConfiguration
//    ReservationDTO map(Reservation source);
//
//    @Mappings({
//    	@Mapping(target = "guest.id", source = "guestId"),
//        @Mapping(target = "schedule.id", source = "scheduleId")
//    })
//    Reservation map(CreateReservationRequestDTO source);
}
