/**
 * 
 */
package com.fibonacci.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fibonacci.service.storage.ResultsStorage;
import com.fibonacci.service.utils.ErrorMessageTranslator;
import com.fibonacci.service.utils.MessageCodes;

/**
 * Calculates Fibonacci sequence using Dynamic Programming
 * 
 * @author igorl
 *
 */
@Component
public class FibonacciCalculatorImpl implements FibonacciCalculator{

	private final static Logger logger = LoggerFactory.getLogger(FibonacciCalculatorImpl.class);

	
	@Autowired
	private ResultsStorage resultsStorage;

	/**
	 * Calculates fibonacci value for specified number. It was decided to implement in dynamic programming way to boost CPu performance. 
	 * Certain penalty will be payed on synchronized access in high-load env 
	 * @param n Input number
	 * @return fibonacci value
	 * @throws InvalidFibonacciParameterException is thrown when an input is negative
	 */
	@Override
	public  synchronized long get(int n) throws InvalidFibonacciParameterException {

		if (n < 0) {
			throw new InvalidFibonacciParameterException(
					ErrorMessageTranslator.getInstance().translateMessage(MessageCodes.NOT_VALID_NUMBER));
		}

		int currentSize = resultsStorage.getSize();
		if (currentSize < n + 1) { // not calculated yet

			for (int i = currentSize; i <= n; i++) {
				// calculate new values and store it
				resultsStorage.add(resultsStorage.get(i - 1) + resultsStorage.get(i - 2));
			}
		}
		long result = resultsStorage.get(n);
		if (logger.isDebugEnabled()) {
			logger.debug("Calculated fibonacci value " + result + " for parameter " + n);
		}
		return result;
	}

}