# The Java Memory Model

## What is a memory model, and why would I want one?

### The Java Memory Model in 500 words or less

The rules for _happens-before_ are:

* **Program order rule**. Each action in a thread _happens-before_ every action in that thread that comes later in 
  the program order.
* **Monitor lock rule**. An unlock on a monitor lock _happens-before_ every subsequent lock on that same monitor lock.
* **Volatile variable rule**. A write to a volatile field _happens-before_ every subsequent read of that same field.
* **Thread start rule**. A call to `Thread.start` on a thread _happens-before_ every action in the started thread.
* **Thread termination rule**. Any action in a thread _happens-before_ any other thread detects that thread has 
  terminated, either by successfully return from `Thread.join` or by `Thread.isAlive` returning `false`
* **Interruption rule**. A thread calling `interrupt` on another thread _happens-before_ the interrupted thread 
  detects the interrupt (either by having `InterruptedException` thrown, or invoking `isInterrupted` or `interrupted`).
* **Finalizer rule**. The end of a constructor for an object _happens-before_ the start of the finalizer for that 
  object.
* **Transitivity**. If A _happens-before_ B, and B _happens-before_ C, then A _happens-before_ C.

## Publication

### Unsafe publication

With the exception of immutable objects, it is not safe to use an object that has been initialized by another thread 
unless the publication _happens-before_ the consuming thread uses it.

## Initialization safety

Initialization safety guarantees that for _properly constructed_ objects, all threads will see the correct values of 
final fields that were set by the constructor, regardless of how the object is published. Further, any variables 
that can be _reached_ through a final field of a properly constructed object (such as the elements of a final array 
or the contents of a `HashMap` referenced by a final field) are also guaranteed to be visible to other threads.

----

Initialization safety makes visibility guarantees only for the values that are reachable through final fields as of 
the time the constructor finishes. For values reachable through nonfinal fields, or values that may change after 
construction, you must use synchronization to ensure visibility.
