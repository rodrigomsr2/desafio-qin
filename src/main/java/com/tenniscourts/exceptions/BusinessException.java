package com.tenniscourts.exceptions;

public class BusinessException extends RuntimeException {
  
	private static final long serialVersionUID = -417250264451546216L;

	public BusinessException(String msg){
        super(msg);
    }
  
}
