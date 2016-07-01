[![Stories in Ready](https://badge.waffle.io/alexandregama/java8-completion-stage.png?label=ready&title=Ready)](https://waffle.io/alexandregama/java8-completion-stage)
# Java 8 Completion Stage


- Typically futures represent piece of code running by other thread

- So you have Future<Message> but there is no asynchronous job underlying this future

See the code bellow that are creating and returning a CompletableFuture object when someone calls **ask()** method.

```java
public CompletableFuture<String> ask() {
    final CompletableFuture<String> future = new CompletableFuture<>();
    //...
    return future;
}
```

Notice that if you call the **ask()** method, it will block your client forever, since we are not using a Callable object in somewhere.
