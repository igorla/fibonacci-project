/**
 * 
 */
package com.fibonacci.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fibonacci.service.AppConfig;

/**
 * @author igorl
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {AppConfig.class})
public class FibonacciCalculatorTest {

	@Autowired
	FibonacciCalculator fibonacciCalculator = new FibonacciCalculatorImpl();
	
	
	@Test
	public void testCalculator() throws InvalidFibonacciParameterException{
		assertEquals(0, fibonacciCalculator.get(0));
		assertEquals(1, fibonacciCalculator.get(1));
		assertEquals(55, fibonacciCalculator.get(10));
		
		try{
			fibonacciCalculator.get(-10);
		}catch(InvalidFibonacciParameterException e){
			// expected exception
			assertNotNull(e.getMessage());
		}
	}
	
	
	@Test
	// Tests simulataneous access
	public void testMultiThreaded() throws InterruptedException{
		 ExecutorService executor = Executors.newFixedThreadPool(10);
		 CalcThread[] threads = new CalcThread[10];
		 threads[0] = new CalcThread(0,0);
		 threads[1] = new CalcThread(10,55);
		 threads[2] = new CalcThread(3,2);
		 threads[3] = new CalcThread(18,2584);
		 threads[4] = new CalcThread(38,39088169 );
		 threads[5] = new CalcThread(2,1);
		 threads[6] = new CalcThread(19,4181 );
		 threads[7] = new CalcThread(11,89);
		 threads[8] = new CalcThread(7,13);
		 threads[9] = new CalcThread(5,5);
		 for (int i = 0; i < threads.length; i++) {
	            Runnable t = threads[i];
	            executor.execute(t);
	          }
	        executor.shutdown();
	        
	        executor.awaitTermination(5, TimeUnit.SECONDS);
	        
	        for (int i = 0; i < threads.length; i++) {
	        	CalcThread t = threads[i];
	        	assertTrue(t.isPassed());
	        }
	        
	}
	
	
	class CalcThread implements Runnable{
		private int number;
		private int expected;
		private boolean passed;
		public CalcThread(int number,int expected){
			this.number = number;
			this.expected = expected;
		}
		public void run(){
			try {
				assertEquals(expected, fibonacciCalculator.get(number));
			} catch (Throwable e) {
				e.printStackTrace();
				passed = false;
				return;
			}
			passed = true;
			
		}
		
		public boolean isPassed(){
			return passed;
		}
	}
}
