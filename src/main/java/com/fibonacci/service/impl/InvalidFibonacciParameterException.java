/**
 * 
 */
package com.fibonacci.service.impl;

/**
 * Thrown when an invalid number is passed to Fibonacci calculator 
 * @author igorl
 *
 */
public class InvalidFibonacciParameterException extends Exception {

	
	private static final long serialVersionUID = 6187352060324996021L;

	/**
	 * 
	 */
	public InvalidFibonacciParameterException() {
	}

	/**
	 * @param message
	 */
	public InvalidFibonacciParameterException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidFibonacciParameterException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidFibonacciParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidFibonacciParameterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
