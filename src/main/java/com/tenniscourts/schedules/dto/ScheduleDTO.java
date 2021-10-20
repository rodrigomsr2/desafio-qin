package com.tenniscourts.schedules.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tenniscourts.tenniscourts.dto.TennisCourtDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
public class ScheduleDTO {

    private Long id;

    private TennisCourtDTO tennisCourt;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @NotNull
    private OffsetDateTime startDateTime;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private OffsetDateTime endDateTime;

}
