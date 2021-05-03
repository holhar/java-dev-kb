# Cancellation and Shutdown

## Task cancellation

### Interruption

There is nothing in the API or language specification that ties interruption to any specific cancellation semantics, 
but in practice, using interruption for anything but cancellation is fragile and difficult to sustain in larger 
applications.

----

Calling `interrupt` does not necessarily stop the target thread from doing what it is doing; it merely delivers the 
message that interruption has been requested.

----

Interruption is usually the most sensible way to implement cancellation.

### Interruption policies

Because each thread has its own interruption policy, you should not interrupt a thread unless you know what 
interruption means to that thread.

### Responding to interruption

Only code that implements a thread's interruption policy may swallow an interruption request.
General-purpose task and library code should never swallow interruption requests.

### Cancellation via Future

When `Future.get` throw `InterruptedException` or `TimeoutException` and you know that the result is no longer 
needed by the program, cancel the task with `Future.cancel`.