/**
 * 
 */
package com.fibonacci.service.impl;

/**
 * Calculates Fibonacci sequence
 * 
 * @author igorl
 *
 */
public interface FibonacciCalculator {

	/**
	 * Calculates Fibonacci value for specified sequence index
	 * @param number
	 * @return
	 * @throws InvalidFibonacciParameterException
	 */
	public   long get(int number) throws InvalidFibonacciParameterException;
}
