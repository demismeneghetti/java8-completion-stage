package com.java8.futures.thenrun;

import org.junit.Test;

public class CompletableFutureOperationsWithThenRunTest {

	@Test
	public void shouldReturnAValueUsingThenRunMethodOnCompletableFuture() throws Exception {
		CompletableFutureOperationsWithThenRun operations = new CompletableFutureOperationsWithThenRun();
		
		operations.runAnotherTaskUsingThenRunMethod();
	}
	
}
