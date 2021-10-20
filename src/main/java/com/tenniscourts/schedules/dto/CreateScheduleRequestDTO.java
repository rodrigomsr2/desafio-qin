package com.tenniscourts.schedules.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tenniscourts.tenniscourts.dto.TennisCourtIdDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
public class CreateScheduleRequestDTO {

    @NotNull
    private TennisCourtIdDTO tennisCourt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mmz")
    @NotNull
    private OffsetDateTime startDateTime;

}
