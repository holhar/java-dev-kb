# Sharing Objects

## Visibility

In the absence of synchronization, the compiler, processor, and runtime can do some downright weird things to the 
order in which operations appear to execute.
Attempts to reason about the order in which memory actions "must" happen in insufficiently synchronized 
multithreaded programs will almost certainly be incorrect (see de.holhar.java_dev_kb.training.concurrency.
ch03_sharing_objects.visibility.NoVisibility).

### Locking and visibility

Locking is not just about mutual exclusion; it is also about memory visibility.
To ensure that all threads see the most up-to-date values of shared mutable variables, the reading and writing 
threads must synchronize on a common lock.

(see de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s1_visibility.MutableInteger and see de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s1_visibility.SynchronizedInteger)

### Volatile variable

Use `volatile` variables only when they simplify implementing and verifying your synchronization policy; avoid using 
`volatile` variables when verifying correctness would require subtle reasoning about visibility.
Good uses of `volatile` variables include ensuring the visibility of their own state, that of the object they refer 
to, or indicating that an important life-cycle event (such as initialization or shutdown) has occurred (see de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s1_visibility.SheepCounter).

----

Locking can guarantee both visibility and atomicity; volatile variables can only guarantee visibility.

## Publication and escape

### Safe construction practices

Do not allow the `this` reference to escape during construction.

## Immutability

Immutable objects are always thread-safe.

----

An object is *immutable* if:

* Its state cannot be modified after construction;
* All its fields are `final`; and
* It is *properly constructed* (the `this` reference does not escape during construction).

### Final fields

Just as it is a good practice to make all fields `private` unless they need greater visibility, it is a good 
practice to make all fields `final` unless they need to be mutable.

## Safe publication

### Immutable objects and initialization safety

*Immutable* objects can be used safely by any thread without additional synchronization, even when synchronization 
is not used to publish them.

### Safe publication idioms

To publish an object safely, both the reference to the object and the object's state must be made visible to other 
threads at the same time.
A properly constructed object can be safely published by:

* Initializing an object reference from a static initializer;
* Storing a reference to it into a `volatile` field or `AtomicReference`;
* Storing a reference to it into a `final` field of a properly constructed object; or
* Storing a reference to it into a field that is properly guarded by a lock.

### Effectively immutable objects

Safely published *effectively immutable* objects can be used safely by any thread without additional synchronization.

### Mutable objects

The publication requirements for an object depend on its mutability:

* *Immutable objects* can be published through any mechanism;
* *Effectively immutable objects* must be safely published;
* *Mutable objects* must be safely published, and must be either thread-safe or guarded by a lock.

### Sharing objects safely

The most useful policies for using and sharing objects in a concurrent program are:

**Thread-confined**.
A thread-confined object is owned exclusively by and confined to one thread, and can be modified by its owning thread.

**Shared read-only**.
A shared read-only object can be accessed concurrently by multiple threads without additional synchronization, but 
cannot be modified by any thread.
Shared read-only objects include immutable and effectively immutable objects.

**Shared thread-safe**.
A thread-safe object performs synchronization internally, so multiple threads can freely access it through its 
public interface without further synchronization.

**Guarded**.
A guarded object can be accessed only with a specific lock held.
Guarded objects include those that are encapsulated within other thread-safe objects and published objects that are 
known to be guarded by a specific lock.

