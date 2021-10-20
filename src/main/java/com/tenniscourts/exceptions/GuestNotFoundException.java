package com.tenniscourts.exceptions;

public class GuestNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = -6170695260102536300L;

	public GuestNotFoundException(String msg) {
		super(msg);
	}

}
