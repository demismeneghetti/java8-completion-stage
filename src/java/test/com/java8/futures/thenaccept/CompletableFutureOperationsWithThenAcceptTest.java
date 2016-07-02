package com.java8.futures.thenaccept;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CompletableFutureOperationsWithThenAcceptTest {

	@Test
	public void shouldUseThenAcceptMethodOnCompletableFuture() throws Exception {
		CompletableFutureOperationsWithThenAccept operations = new CompletableFutureOperationsWithThenAccept();
		
		String value = operations.getValueUsingThenAcceptMethod();
		
		assertThat(value, is(equalTo("Some value")));
	}
}
