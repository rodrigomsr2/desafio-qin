package com.tenniscourts.guests;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tenniscourts.guests.dto.UpdateGuestDTO;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GuestAssembler {

	private ModelMapper modelMapper;
	
	public void copyFromUpdateDtoToEntity(UpdateGuestDTO updateGuestDTO, Guest guest) {
		modelMapper.map(updateGuestDTO, guest);
	}
	
}
