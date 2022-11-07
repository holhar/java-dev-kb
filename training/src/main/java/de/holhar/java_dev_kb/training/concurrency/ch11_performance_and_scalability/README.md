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
