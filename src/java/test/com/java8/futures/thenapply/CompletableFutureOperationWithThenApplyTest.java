package com.java8.futures.thenapply;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CompletableFutureOperationWithThenApplyTest {

	@Test
	public void shouldReturnAValueFromSupplyAsyncCallUsingThenApplyMethod() throws Exception {
		CompletableFutureOperationWithThenApply operations = new CompletableFutureOperationWithThenApply();
		
		String value = operations.returningWithThenApply();
		
		assertThat(value, is(equalTo("some value")));
	}
	
	@Test
	public void shouldReturnAValueFromSupplyAsyncCallUsingThenApplyMethodOnLongTask() throws Exception {
		CompletableFutureOperationWithThenApply operations = new CompletableFutureOperationWithThenApply();
		
		String value = operations.returnValueFromLongTaskUsingThenApplyMethod();
		
		assertThat(value, is(equalTo("some value")));
	}
	
}
