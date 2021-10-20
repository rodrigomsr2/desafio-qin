package com.tenniscourts.guests.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GuestOutputDTO {
	
	private Long id;
	
	private String name;
	
}
