# Performance and Scalability

## Thinking about performance

### Performance versus scalability

Scalability describes the ability to improve throughput or capacity when additional computing
resources (such as additional CPSs, memory, storage, or I/O bandwidth) are added.

### Evaluating performance tradeoffs

Avoid premature optimization. First make it right, then make it fast - if it is not already fast
enough.

----

Measure, don't guess.

## Amdahl's law

    Speedup <= 1 / (f + ( (1 - F) / N ))

----

All concurrent applications have some sources of serialization; if you think yours does not, look
again.

## Costs introduced by threads

### Memory synchronization

Don't worry excessively about the cost of uncontended synchronization. The basic mechanism is
already quite fast, and JVMs can perform additional optimizations that further reduce or eliminate
the cost. Instead, focus optimization efforts on areas where lock contention actually occurs.

## Reducing lock contention

The principal threat to scalability in concurrent applications is the execlusive resource lock.

----

There are three ways to reduce lock contention:

* Reduce the duration for which locks are held;
* Reduce frequency with which locks are requested; or
* Replace exclusive locks with coordination mechanisms that permit greater concurrency

### Just say no to object pooling

Allocating objects is usually cheaper than synchronization
