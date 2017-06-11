/**
 * 
 */
package com.fibonacci.service.storage;

import java.math.BigInteger;

/**
 * Storage for calculated results. Implmentation may inMemory, distributed cache, some fast-access database, like s3/MongoDB/Cassandra, etc 
 * @author igorl
 *
 */
public interface ResultsStorage {

	/**
	 * Returns calculated value for specified number
	 * @param number
	 * @return
	 */
	public BigInteger get(int number);
	
	/**
	 * Places result to next available slot
	 * @param result
	 */
	public void add(BigInteger result);
	
	/**
	 * 
	 * @return total number of items in the storage
	 */
	public int getSize();
}
