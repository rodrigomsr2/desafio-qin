package com.tenniscourts.schedules.dto;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotNull;

import com.tenniscourts.tenniscourts.dto.TennisCourtForReservationDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ScheduleForReservationDTO {

	@NotNull
	private TennisCourtForReservationDTO tennisCourtForReservationDTO;
	
	@NotNull
	private OffsetDateTime startDateTime;
	
	@NotNull
	private OffsetDateTime endDateTime;
	
}
