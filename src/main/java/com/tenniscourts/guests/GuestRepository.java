package com.tenniscourts.guests;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

	List<Guest> findByNameContaining(String name);
	
}
