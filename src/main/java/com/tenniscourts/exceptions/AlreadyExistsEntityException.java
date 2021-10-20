package com.tenniscourts.exceptions;

public class AlreadyExistsEntityException extends BusinessException {
  
	private static final long serialVersionUID = -3882709800738762080L;

	public AlreadyExistsEntityException(String msg){
        super(msg);
    }
	
}
