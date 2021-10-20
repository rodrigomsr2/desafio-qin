package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;
import com.tenniscourts.schedules.dto.CreateScheduleRequestDTO;
import com.tenniscourts.schedules.dto.ScheduleDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.validation.Valid;

@AllArgsConstructor
@RestController()
@RequestMapping("/schedules")
@Api(tags = "Schedule")
public class ScheduleController extends BaseRestController {

	private final ScheduleService scheduleService;

	// TODO: implement rest and swagger
	@PostMapping
	@ApiOperation("Add a new schedule for a specific tennis course")
	public ResponseEntity<Void> addScheduleTennisCourt(
			@ApiParam(name = "request body", value = "A DTO which holds data necessary to create a new schedule")
			@RequestBody @Valid CreateScheduleRequestDTO createScheduleRequestDTO) {
		return ResponseEntity.created(locationByEntity(scheduleService
				.addSchedule(createScheduleRequestDTO.getTennisCourt().getId(), createScheduleRequestDTO).getId()))
				.build();
	}

	// TODO: implement rest and swagger
	@GetMapping("/free")
	@ApiOperation("Show registered schedules by the admin that have no reservations or reservations with status cancelled or rescheduled")
	public ResponseEntity<List<ScheduleDTO>> findFreeSchedules() {
		return ResponseEntity.ok(scheduleService.findFreeSchedules());
	}
	
	// TODO: implement rest and swagger
	@GetMapping("/dates")
	@ApiOperation("Find schedules by a given date interval")
	public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(
			@ApiParam(name = "start date", value = "The start of the given date interval")
			@RequestParam("startDate") @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
			@ApiParam(name = "end date", value = "The end of the given date interval")
			@RequestParam("endDate") @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
		LocalDateTime localDateTimeStart = LocalDateTime.of(startDate, LocalTime.of(0, 0));
		LocalDateTime localDateTimeEnd = LocalDateTime.of(endDate, LocalTime.of(23, 59));

		return ResponseEntity
				.ok(scheduleService.findSchedulesByDates(OffsetDateTime.of(localDateTimeStart, ZoneOffset.of("Z")),
						OffsetDateTime.of(localDateTimeEnd, ZoneOffset.of("Z"))));
	}

	// TODO: implement rest and swagger
	@GetMapping("/{id}")
	@ApiOperation("Find a specific schedule by its id")
	public ResponseEntity<ScheduleDTO> findByScheduleId(
			@ApiParam(name = "identifier", value = "The id used to find the specific schedule", example="1")
			@PathVariable("id") Long scheduleId) {
		return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
	}
}
