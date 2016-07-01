package com.java8.futures;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureOperations {

	public CompletableFuture<String> askWithoutReturnedCode() {
		final CompletableFuture<String> future = new CompletableFuture<>();
		
		return future;
	}

	/*
	 * We completed the CompletableFuture with some value. The code that is calling this method will not be blocked anymore
	 */
	public CompletableFuture<String> askReturningSomething() {
		final CompletableFuture<String> future = new CompletableFuture<>();
		
		future.complete("Some string value");
		
		return future;
	}
	
}
