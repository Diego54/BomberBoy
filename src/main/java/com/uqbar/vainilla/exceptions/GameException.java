package com.uqbar.vainilla.exceptions;

public class GameException extends RuntimeException {

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public GameException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GameException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public GameException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GameException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public GameException(String description) {
		super(description);
	}
}