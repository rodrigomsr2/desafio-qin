package com.tenniscourts.guests.dto;

import javax.validation.constraints.NotNull;

import com.tenniscourts.usergroup.dto.CreateGuestUserGroupDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateGuestDTO {

	@NotNull
	private String name;
	
	@NotNull
	private CreateGuestUserGroupDTO userGroup;
	
}
