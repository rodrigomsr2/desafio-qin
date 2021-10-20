package com.tenniscourts.tenniscourts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

import com.tenniscourts.schedules.dto.ScheduleDTO;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TennisCourtDTO {

    private Long id;

    @NotNull
    private String name;

    private List<ScheduleDTO> tennisCourtSchedules;

}
