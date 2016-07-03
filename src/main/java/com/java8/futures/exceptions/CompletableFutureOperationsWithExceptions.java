package com.java8.futures.exceptions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureOperationsWithExceptions {

	public String doAOperationThrowingAnException() throws InterruptedException, ExecutionException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				return String.valueOf(1 / 0);
			} catch (Exception e) {
				throw new RuntimeException("An error ocurred");
			}
		}).thenApply(t -> {
			return t.toUpperCase();
		}).thenApply(String::toLowerCase)
		  .exceptionally(ex -> "Throws an exception");
		
		String value = future.get();
		
		return value;
	}

}
