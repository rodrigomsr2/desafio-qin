package com.tenniscourts.permission;

import javax.persistence.Entity;

import com.tenniscourts.config.persistence.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity<Long> {

	private String name;
	
	private String operation;
	
	private String description;
	
}
