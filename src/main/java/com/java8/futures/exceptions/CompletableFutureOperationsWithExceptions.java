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

	public String doOperationDealingWithExceptionsInABetterWay() throws InterruptedException, ExecutionException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			return "Some value";
		}).handle((ok, ex) -> {
			if (ok != null) {
				return "Another value";
			} else {
				return "There is a problem";
			}
		});
		
		String value = future.get();
		
		return value;
	}

	public String doAOperationReturningAMessageException() throws InterruptedException, ExecutionException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				return String.valueOf(1 / 0);
			} catch (Exception e) {
				throw new RuntimeException("Something goes wrong");
			}
		}).handle((ok, ex) -> {
			if (ok != null) {
				return "Good value";
			} else {
				return "Some problems";
			}
		});
		
		String value = future.get();
		
		return value;
	}

}
