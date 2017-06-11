package com.fibonacci.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fibonacci.service.impl.FibonacciCalculator;
import com.fibonacci.service.impl.InvalidFibonacciParameterException;
import com.fibonacci.service.utils.ErrorMessageTranslator;
import com.fibonacci.service.utils.MessageCodes;

/**
 * Fibonacci service controller
 * 
 * @author igorl
 *
 */
@RestController
public class ServiceController {

	@Autowired
	private FibonacciCalculator fibonacciCalculator;
	
	@RequestMapping("/fibonacci/{numberStr}")
	public ResponseEntity fibonacci(@PathVariable String numberStr) {
		try {
			if(StringUtils.isEmpty(numberStr)){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessageTranslator.getInstance().translateMessage(MessageCodes.EMPTY_INPUT,"number"));
			}
			
			// There is no need to handle decimal values since path parameter is resolved until '.' is encountered. 
			Integer number = Integer.valueOf(numberStr);
			
			if (number < 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessageTranslator.getInstance().translateMessage(MessageCodes.NOT_VALID_NUMBER));
			}

			return ResponseEntity.ok(fibonacciCalculator.get(number));
		} catch (InvalidFibonacciParameterException | NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessageTranslator.getInstance().translateMessage(MessageCodes.NOT_VALID_NUMBER));
		}
		
	}
}
