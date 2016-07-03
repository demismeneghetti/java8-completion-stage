package com.java8.futures.combining;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CompletableFutureOperationsCombiningFuturesTest {

	@Test
	public void shouldCombineTwoCompletableFuture() throws Exception {
		CompletableFutureOperationsCombiningFutures operations = new CompletableFutureOperationsCombiningFutures();
		
		String value = operations.doOperationCombiningTwoCompletableFutures();
		
		assertThat(value, is(equalTo("SECOND VALUE")));
	}
	
}
