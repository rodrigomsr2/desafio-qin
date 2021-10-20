package com.tenniscourts.guests;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tenniscourts.guests.dto.CreateGuestDTO;
import com.tenniscourts.guests.dto.GuestOutputDTO;
import com.tenniscourts.guests.dto.UpdateGuestDTO;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/guests")
public class GuestController {

	private final GuestService guestService;
	
	private final GuestMapper guestMapper;
	
	@GetMapping
	public List<GuestOutputDTO> findAll() {
		return this.guestService.findAll().stream()
											.map(g -> guestMapper.map(g))
											.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public GuestOutputDTO findById(@PathVariable("id") Long id) {
		Guest guest = this.guestService.findById(id);
		return guestMapper.map(guest);
	}
	
	@GetMapping("/name")
	public List<GuestOutputDTO> findByName(@RequestParam("name") String name) {
		return this.guestService.findByName(name).stream()
													.map(g -> guestMapper.map(g))
													.collect(Collectors.toList());
	}
	
	@PostMapping
	@ResponseStatus(value=HttpStatus.CREATED)
	public GuestOutputDTO createNew(@RequestBody @Valid CreateGuestDTO guestDto) {
		Guest guest = this.guestService.create(this.guestMapper.map(guestDto));
		return this.guestMapper.map(guest);
	}
	
	@PutMapping("/{id}")
	public GuestOutputDTO update(@RequestBody @Valid UpdateGuestDTO guestDto, @PathVariable("id") Long id) {
		Guest guest = this.guestService.update(guestDto, id);
		return this.guestMapper.map(guest);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		this.guestService.delete(id);
	}
	
}
