package com.tenniscourts.guests;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import com.tenniscourts.exceptions.GuestNotFoundException;
import com.tenniscourts.guests.dto.UpdateGuestDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GuestService {

	private final GuestRepository guestRepository;
	
	private final GuestAssembler guestAssembler;
	
	public Guest create(Guest guest) {
		return this.guestRepository.save(guest);
	}
	
	public Guest updateGuest(Guest guest) {
		return this.guestRepository.save(guest);
	}
	
	public List<Guest> findAll() {
		return IterableUtils.toList(this.guestRepository.findAll());
	}
	
	public Guest findById(Long id) {
		return this.guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException("The guest was not found."));
	}
	
	public List<Guest> findByName(String name) {
		List<Guest> guests = this.guestRepository.findByNameContaining(name);
		
		if(guests.isEmpty())
			throw new GuestNotFoundException("No guests were found with that name.");
		
		return guests;
	}
	
	public Guest update(UpdateGuestDTO updateGuestDTO, Long id) {
		Guest guest = this.findById(id);
		
		guestAssembler.copyFromUpdateDtoToEntity(updateGuestDTO, guest);
		
		return this.guestRepository.save(guest);
	}
	
	public void delete(Long id) {
		this.guestRepository.deleteById(id);
	}
	
}
