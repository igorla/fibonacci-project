/**
 * 
 */
package com.fibonacci.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
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
		assertEquals(BigInteger.valueOf(0), fibonacciCalculator.get(0));
		assertEquals(BigInteger.valueOf(1), fibonacciCalculator.get(1));
		assertEquals( new BigInteger("767476895805689369991789277073521737239462473500936993792414590762971170414053972421838788552962951090412321568104958251305958672537315916096680015791574354131667637189603239871595291143454548069913507248538315878474818381309151810472376275027339038726484850625",10), fibonacciCalculator.get(1250));
		
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
		 threads[0] = new CalcThread(0,BigInteger.valueOf(0));
		 threads[1] = new CalcThread(10,BigInteger.valueOf(55));
		 threads[2] = new CalcThread(3,BigInteger.valueOf(2));
		 threads[3] = new CalcThread(18,BigInteger.valueOf(2584));
		 threads[4] = new CalcThread(38,BigInteger.valueOf(39088169) );
		 threads[5] = new CalcThread(2,BigInteger.valueOf(1));
		 threads[6] = new CalcThread(19,BigInteger.valueOf(4181) );
		 threads[7] = new CalcThread(11,BigInteger.valueOf(89));
		 threads[8] = new CalcThread(7,BigInteger.valueOf(13));
		 threads[9] = new CalcThread(5,BigInteger.valueOf(5));
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
		private BigInteger expected;
		private boolean passed;
		public CalcThread(int number,BigInteger expected){
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
