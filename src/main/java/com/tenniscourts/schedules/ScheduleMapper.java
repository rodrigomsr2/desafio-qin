package com.tenniscourts.schedules;

import org.mapstruct.Mapper;

import com.tenniscourts.schedules.dto.CreateScheduleRequestDTO;
import com.tenniscourts.schedules.dto.ScheduleDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    Schedule map(ScheduleDTO source);

    ScheduleDTO map(Schedule source);
    
    Schedule map(CreateScheduleRequestDTO source);

    List<ScheduleDTO> map(List<Schedule> source);
}
