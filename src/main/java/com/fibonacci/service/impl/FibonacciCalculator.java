/**
 * 
 */
package com.fibonacci.service.impl;

import java.math.BigInteger;

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
	public   BigInteger get(int number) throws InvalidFibonacciParameterException;
}
