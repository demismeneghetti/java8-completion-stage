package com.java8.futures.thenapply;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureOperationWithThenApply {

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
	
}
