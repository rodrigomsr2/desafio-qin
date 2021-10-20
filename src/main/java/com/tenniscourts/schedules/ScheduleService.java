package com.tenniscourts.schedules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.tenniscourts.exceptions.AlreadyExistsEntityException;
import com.tenniscourts.reservations.dto.CreateReservationRequestDTO;
import com.tenniscourts.schedules.dto.CreateScheduleRequestDTO;
import com.tenniscourts.schedules.dto.ScheduleDTO;
import com.tenniscourts.tenniscourts.TennisCourtRepository;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleMapper scheduleMapper;
    
    // injecting repository to evade circular dependency
    private final TennisCourtRepository tennisCourtRepository;

    public ScheduleDTO addSchedule(Long tennisCourtId, CreateScheduleRequestDTO createScheduleRequestDTO) {
    	OffsetDateTime startDate = createScheduleRequestDTO.getStartDateTime();
    	
    	if (!doesTennisCourtExists(tennisCourtId)) 
    		throw new EntityNotFoundException("Tennis court not found.");
    	
    	if(doesScheduleInTennisCourseExists(tennisCourtId, startDate))
    		throw new AlreadyExistsEntityException("Schedule already exists.");
    	
    	Schedule schedule = scheduleMapper.map(createScheduleRequestDTO);
    	schedule.setEndDateTime(startDate.plusHours(1));
    	
        return this.scheduleMapper.map(this.scheduleRepository.save(schedule));
    }

    public List<ScheduleDTO> findSchedulesByDates(OffsetDateTime startDate, OffsetDateTime endDate) {
        List<Schedule> schedules = this.scheduleRepository.findAllByStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(endDate, startDate);
        return this.scheduleMapper.map(schedules);
    }

    public ScheduleDTO findSchedule(Long scheduleId) {
    	Schedule schedule = this.scheduleRepository.findById(scheduleId).orElseThrow(() -> 
    												new EntityNotFoundException("Schedule not found."));
        return this.scheduleMapper.map(schedule);
    }

    public List<ScheduleDTO> findSchedulesByTennisCourtId(Long tennisCourtId) {
        return scheduleMapper.map(scheduleRepository.findByTennisCourt_IdOrderByStartDateTime(tennisCourtId));
    }
    
    public List<ScheduleDTO> findFreeSchedules() {
    	return this.scheduleMapper.map(this.scheduleRepository.findFreeSchedules());
    }
    
    private Boolean doesTennisCourtExists(Long tennisCourtId) {
    	return tennisCourtRepository.existsById(tennisCourtId);
    }
    
    private Boolean doesScheduleInTennisCourseExists(Long tennisCourtId, OffsetDateTime startDate) {
    	return this.scheduleRepository.existsByTennisCourtIdAndStartDateTime(tennisCourtId, startDate);
    }
    
    public List<Schedule> findSchedulesBy(CreateReservationRequestDTO dto, List<Long> tennisCourtsIds) {
    	return this.scheduleRepository.findSchedulesBy(dto.getDateSchedule(), tennisCourtsIds);
    }
}
