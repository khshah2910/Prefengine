package com.prefengine.service;

public class ServiceException extends Exception{
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String arg) {
		super(arg);
	}
    public ServiceException(String reason, Throwable cause){
        super(reason, cause);
    }
}
