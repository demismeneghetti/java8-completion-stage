package com.java8.futures.thenrun;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureOperationsWithThenRun {

	/*
	 * Treat the thenRun() method like an event handler/listener that you attach to a future and that will execute some time in the future 
	 */
	public void runAnotherTaskUsingThenRunMethod() throws InterruptedException, ExecutionException {
		CompletableFuture.supplyAsync(() -> {
			return "Some value";
		}).thenRun(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Running a long task..");
				try {
					TimeUnit.SECONDS.sleep(5);
					System.out.println("Long task finished!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
