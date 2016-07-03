package com.java8.futures.combining;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureOperationsCombiningFutures {

	public String doOperationCombiningTwoCompletableFutures() throws InterruptedException, ExecutionException {
		CompletableFuture<String> secondFuture = CompletableFuture.supplyAsync(() -> {
			try {
				SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Second value";
		}).thenCompose(this::toUpperCase);
		
		String value = secondFuture.get();
		
		return value;
	}

	public CompletableFuture<String> toUpperCase(String value) {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			return value.toUpperCase();
		});
		
		return future;
	}
	
}
