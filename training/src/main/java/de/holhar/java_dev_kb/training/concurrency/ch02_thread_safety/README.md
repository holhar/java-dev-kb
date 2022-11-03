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

## Atomicity

### Compound actions

Operation A and B are *atomic* with respect to each other if, from the perspective of a thread executing A, when 
another thread executes B, either all of B has executed or none of it has.
An *atomic operation* is one that is atomic with respect to all operations, including itself, that operate on the 
same state.

----

Where practical, use existing thread-safe objects, like *AtomicLong*, to manage your class's state.
It is simpler to reason about the possible states and state transitions for existing thread-safe objects than it is 
for arbitrary state variables, and this makes it easier to maintain and verify thread safety (see *de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.s2_2_atomicity.CountingFactorizer*).

## Locking

To preserve state consistency, update related state variable in a single atomic operation.

## Guarding state with locks

For each mutable state variable that may be accessed by more than one thread, *all* accesses to that variable must 
be performed with the *same* lock held.
In this case, we say that the variable is *guarded by* that lock.

----

Every shared, mutable variable should be guarded by exactly one lock.
Make it clear to maintainers which lock that is.

----

For every invariant that involves more than one variable, *all* the variables involved in that invariant must be 
guarded by the *same* lock.

## Liveness and performance

There is frequently a tension between simplicity and performance.
When implementing a synchronization policy, resist the temptation to prematurely sacrifice simplicity (potentially 
compromising safety) for the sake of performance.

----

Avoid holding locks during lenghty computations or operations at risk of not completing quickly such as network or 
console I/O (see de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.s2_5_liveness_performance.CachedFactorizer).
