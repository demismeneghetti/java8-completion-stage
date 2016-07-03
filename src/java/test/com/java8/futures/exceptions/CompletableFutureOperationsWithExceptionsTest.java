package com.java8.futures.exceptions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CompletableFutureOperationsWithExceptionsTest {

	@Test
	public void testName() throws Exception {
		CompletableFutureOperationsWithExceptions operations = new CompletableFutureOperationsWithExceptions();
		
		String value = operations.doAOperationThrowingAnException();
		
		assertThat(value, is(equalTo("Throws an exception")));
	}
	
}
