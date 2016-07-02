package com.java8.futures.thenaccept;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureOperationsWithThenAccept {

	/*
	 * Notice that the thenAccept() method is a typical "final" stage in future pipeline
	 * Its allows you to consume future value when it's ready. 
	 * 
	 * As you can see, the thenAccept() method provides the final value from the supplyAsync method.
	 */
	public String getValueUsingThenAcceptMethod() throws InterruptedException, ExecutionException {
		CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("Executing the long task..");
				SECONDS.sleep(5);
				System.out.println("Task finished!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Some value";
		}).thenAccept(t -> {
			try {
				System.out.println("Executing the accepted task with the value: " + t); //As you can see, we have the value provided by supplyAsync method that has been called 
				SECONDS.sleep(5);
				System.out.println("Accepted task finished!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Accepted method!");
		});
		
		System.out.println("Before the get() method be called");
		future.get(); //stops the client
		System.out.println("After the get() method be called");
		
		return "Some value";
	}

}
