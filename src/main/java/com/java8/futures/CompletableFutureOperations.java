package com.java8.futures;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureOperations {

	public CompletableFuture<String> askWithoutReturnedCode() {
		final CompletableFuture<String> future = new CompletableFuture<>();
		
		return future;
	}

	/*
	 * We completed the CompletableFuture with some value. The code that is calling this method will not be blocked anymore
	 * 
	 * Notice that all clients that calls the future.get() method 
	 */
	public CompletableFuture<String> askReturningSomething() {
		final CompletableFuture<String> future = new CompletableFuture<>();
		
		future.complete("Some string value");
		
		return future;
	}
	
	/*
	 * Notice that here we are stopping the client when we call the get() method. Everytime that you call
	 * the get() method on Future object, you will see that the Thread will stop 
	 * 
	 * CompletableFuture.complete() can only be called once, subsequent invocations are ignored
	 */
	public CompletableFuture<String> askReturningSomethingWithDelay() throws InterruptedException {
		final CompletableFuture<String> future = new CompletableFuture<>();
		
		future.complete("Some string value");
		SECONDS.sleep(5);
		
		return future;
	}

	/*
	 * As you can see, we are using now the obstrudeValue() method to override the previous completed value
	 * Remember that the complete() method does not have the same behavior, since it does not change the first completed value
	 */
	public CompletableFuture<String> askReturningSomethingWithAnotherCompletedValue() {
		final CompletableFuture<String> future = new CompletableFuture<>();
		
		future.complete("Some string value");
		future.obtrudeValue("Another Value");
		
		return future;
	}
	
}
