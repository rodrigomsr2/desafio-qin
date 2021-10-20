package com.tenniscourts.exceptions;

public class EntityNotFoundException extends BusinessException {

	private static final long serialVersionUID = -4691343621128003232L;

	public EntityNotFoundException(String msg){
        super(msg);
    }
	
}
