# Thread Safety

If multiple threads access the same mutable state variable without appropriate synchronization, *your program is 
broken*.
There are three ways to fix it:

* *Don't share* the state variable across threads;
* Make the state variable *immutable*; or
* Use *synchronization* whenever accessing the state variable.

----
When designing thread-safe classes, good object-oriented techniques - encapsulation, immutability, and clear 
specification of invariants - are your best friends.

## What is thread safety?

A class is *thread-safe* if it behaves correctly when accessed from multiple threads, regardless of the scheduling 
or interleaving of the execution of those threads by the runtime environment, and with no additional synchronization 
or other coordination on the part of the calling code.

----

Thread-safe classes encapsulate any needed synchronization so that clients need not provide their own.

----

Stateless objects are always thread-safe (see StatelessFactorizer#service(...))