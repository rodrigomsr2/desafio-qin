package com.tenniscourts.reservations.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tenniscourts.guests.dto.GuestForReservationDTO;
import com.tenniscourts.tenniscourts.dto.TennisCourtIdDTO;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateReservationRequestDTO {

	private List<TennisCourtIdDTO> tennisCourts;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mmz")
	private OffsetDateTime dateSchedule;
    
    private GuestForReservationDTO guest;

}
