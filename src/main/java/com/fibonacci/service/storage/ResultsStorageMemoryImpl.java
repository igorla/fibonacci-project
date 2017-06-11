/**
 * 
 */
package com.fibonacci.service.storage;

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
	// Accumulator for already calculated numbers. Thread-safe is implemented by synchronized on add operation, which will lock whole instance
	private  List<Long> fibValues = new ArrayList<>();
	
	
	public ResultsStorageMemoryImpl(){
		fibValues.add(0L);
		fibValues.add(1L);
	}
	@Override
	public long get(int number) {
		return fibValues.get(number);
	}
	@Override
	public synchronized void add(long result) {
		fibValues.add(result);
	}
	@Override
	public int getSize() {
		return fibValues.size();
	}

}
