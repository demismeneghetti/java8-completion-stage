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
		
		//operations.ask().get(); It's comment because the get method will stop the client forever
	}

	@Test
	public void shouldReturnAValueFromCompletableFuture() throws Exception {
		CompletableFutureOperations operations = new CompletableFutureOperations();
		
		String someValue = operations.askReturningSomething().get();
		
		assertThat(someValue, is(equalTo("Some string value")));
	}
	
	@Test
	public void shouldReturnAValueFromCompletableFutureWithDelay() throws Exception {
		CompletableFutureOperations operations = new CompletableFutureOperations();
		
		String someValue = operations.askReturningSomethingWithDelay().get(); //Stops the Thread, waiting for response
		
		assertThat(someValue, is(equalTo("Some string value")));
	}
	
	@Test
	public void shouldReturnADifferentValueFromPreviousOneWhenUsingCompletableFuture() throws Exception {
		CompletableFutureOperations operations = new CompletableFutureOperations();
		
		String someValue = operations.askReturningSomethingWithAnotherCompletedValue().get();
		
		assertThat(someValue, is(equalTo("Another Value")));
	}
	
	@Test
	public void shouldReturnACompletableFutureUsingSupplierObject() throws Exception {
		CompletableFutureOperations operations = new CompletableFutureOperations();
		
		String someValue = operations.askReturningSomethingUsingSupplier().get(); //Stops the client and waits the returned value
		
		assertThat(someValue, is(equalTo("Some value")));
	}
	
	@Test
	public void shouldReturnACompletableFutureUsingSupplierObjectUsingLambda() throws Exception {
		CompletableFutureOperations operations = new CompletableFutureOperations();
		
		String someValue = operations.askReturningSomethingUsingSupplierUsingLambda().get(); //Stops the client and waits the returned value
		
		assertThat(someValue, is(equalTo("Some value")));
	}
	
	@Test
	public void shouldReturnACompletableFutureUsingSupplierObjectUsingLambdaAndMethod() throws Exception {
		CompletableFutureOperations operations = new CompletableFutureOperations();
		
		String someValue = operations.askReturningSomethingUsingSupplierUsingLambdaAndMethod().get(); //Stops the client and waits the returned value
		
		assertThat(someValue, is(equalTo("Some value")));
	}

	@Test
	public void shouldReturnACompletableFutureUsingSupplierAndExecutor() throws Exception {
		CompletableFutureOperations operations = new CompletableFutureOperations();
		
		String someValue = operations.askReturningSomethingUsingSupplierAndExecutor().get();
		assertThat(someValue, is(equalTo("Some value")));
	}
	
	@Test
	public void shouldReturnAValueFromSupplyAsyncCallUsingThenApplyMethod() throws Exception {
		CompletableFutureOperations operations = new CompletableFutureOperations();
		
		String value = operations.returningWithThenApply();
		
		assertThat(value, is(equalTo("some value")));
	}
	
	@Test
	public void shouldReturnAValueFromSupplyAsyncCallUsingThenApplyMethodOnLongTask() throws Exception {
		CompletableFutureOperations operations = new CompletableFutureOperations();
		
		String value = operations.returnValueFromLongTaskUsingThenApplyMethod();
		
		assertThat(value, is(equalTo("some value")));
	}
}
