package com.java8.futures;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CompletableFutureOperationsTest {

	@Test
	public void shouldDoNothing() throws Exception {
		CompletableFutureOperations operations = new CompletableFutureOperations();
		
		operations.askWithoutReturnedCode(); //Nothing happens here because we are not calling the get() method
	}
	
	/*
	 * The ask().get() method is comment because there is no code returned when we call the ask() method.
	 * If you call, the method will block your client forever, since there is no code to be returned
	 */
	@Test
	@SuppressWarnings("unused")
	public void shouldRunForever() throws Exception {
		CompletableFutureOperations operations = new CompletableFutureOperations();
		
		//operations.ask().get();
	}

	@Test
	public void shouldReturnAValueFromCompletableFuture() throws Exception {
		CompletableFutureOperations operations = new CompletableFutureOperations();
		
		String someValue = operations.askReturningSomething().get();
		
		assertThat(someValue, is(equalTo("Some string value")));
	}
	
}
