package com.java8.futures;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

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

	public CompletableFuture<String> askReturningSomethingUsingSupplier() {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				try {
					System.out.println("Executing something really really hard!");
					TimeUnit.SECONDS.sleep(5);
					System.out.println("Finished execution!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "Some value";
			}
		});

		System.out.println("Finished asking!");
		
		return future;
	}

	public CompletableFuture<String> askReturningSomethingUsingSupplierUsingLambda() {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			System.out.println("Long running task on Thread called " + Thread.currentThread().getName());
			try {
				SECONDS.sleep(5);
				System.out.println("Task finished!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Some value";
		});
		System.out.println("Method finished on Thread called " + Thread.currentThread().getName());
		return future;
	}

	public CompletableFuture<String> askReturningSomethingUsingSupplierUsingLambdaAndMethod() {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> runLongTask());
		
		return future;
	}
	
	/*
	 * Notice that we are using a custom Executor, passing it on supplyAsync method
	 */
	public CompletableFuture<String> askReturningSomethingUsingSupplierAndExecutor() {
		final ExecutorService executor = Executors.newFixedThreadPool(1);
		
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			return "Some value";
		}, executor);
		
		executor.shutdown();
		
		return future;
	}
	
	/*
	 * As you notice, we can call a several thenApply method on CompletableFuture
	 */
	public String returningWithThenApply() throws InterruptedException, ExecutionException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("Will wait for 5 seconds..");
				SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Some value";
		}).thenApply(String::toUpperCase)
		  .thenApply(String::toLowerCase);
		
		String value = future.get(); //stops the Thread on client caller
		
		return value;
	}
	
	public String returnValueFromLongTaskUsingThenApplyMethod() throws InterruptedException, ExecutionException {
		System.out.println("Will start the method chain on Thread: " + Thread.currentThread().getName().concat("\n"));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			System.out.println("Running long task. Wait 5 seconds on Thread: " + Thread.currentThread().getName());
			try {
				SECONDS.sleep(5);
				System.out.println("Task finished on Thread: " + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Some value";
		}).thenApplyAsync(s -> {
			try {
				System.out.println("Upper case on value using Thread: " + Thread.currentThread().getName().concat("\n"));
				SECONDS.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return s.toUpperCase();
		}, executor).thenApply(s -> {
			try {
				System.out.println("Lower case on value using Thread: " + Thread.currentThread().getName().concat("\n"));
				SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return s.toLowerCase();
		});
		
		System.out.println("Method finished on Thread: " + Thread.currentThread().getName());
		String value = future.get();
	
		executor.shutdown();
		return value;
	}

	private String runLongTask() {
		System.out.println("Executing long tasks");
		return "Some value";
	}
	
}
