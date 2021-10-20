package com.tenniscourts.exceptions;

public class ScheduleNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1947861463000895495L;
	
	public ScheduleNotFoundException(String msg) {
		super(msg);
	}

}
