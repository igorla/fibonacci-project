/**
 * 
 */
package com.fibonacci.service.storage;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * This is a memory implementation for calculated values
 * @author igorl
 *
 */
@Component
public class ResultsStorageMemoryImpl implements ResultsStorage{
	// Accumulator for already calculated numbers. BigInteger is used to support results that quick become bigger than Long.MAX_VALUE.
	//Thread-safe is implemented by synchronized on add operation, which will lock whole instance	
	private  List<BigInteger> fibValues = new ArrayList<>();
	
	
	public ResultsStorageMemoryImpl(){
		fibValues.add(BigInteger.valueOf(0));
		fibValues.add(BigInteger.valueOf(1));
	}
	@Override
	public BigInteger get(int number) {
		return fibValues.get(number);
	}
	@Override
	public synchronized void add(BigInteger result) {
		fibValues.add(result);
	}
	@Override
	public int getSize() {
		return fibValues.size();
	}

}
