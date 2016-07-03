package com.java8.futures.exceptions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CompletableFutureOperationsWithExceptionsTest {

	@Test
	public void shouldReturnAMessageCreatedByMethodAfterAnExceptionBeThrows() throws Exception {
		CompletableFutureOperationsWithExceptions operations = new CompletableFutureOperationsWithExceptions();
		
		String value = operations.doAOperationThrowingAnException();
		
		assertThat(value, is(equalTo("Throws an exception")));
	}
	
	@Test
	public void shoudlDealWithExceptionsUsingHandleWay() throws Exception {
		CompletableFutureOperationsWithExceptions operations = new CompletableFutureOperationsWithExceptions();
		
		String value = operations.doOperationDealingWithExceptionsInABetterWay();
		
		assertThat(value, is(equalTo("Another value")));
	}

	@Test
	public void shoudlReturnAValueThatWasSentByAThrowableOperation() throws Exception {
		CompletableFutureOperationsWithExceptions operations = new CompletableFutureOperationsWithExceptions();
		
		String value = operations.doAOperationReturningAMessageException();
		
		assertThat(value, is(equalTo("Some problems")));
	}
	
}
