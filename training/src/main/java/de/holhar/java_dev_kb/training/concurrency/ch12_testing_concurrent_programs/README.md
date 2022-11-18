# Testing concurrent Programs

## Testing for correctness

### Testing safety

The challenge to constructing effective safety tests for concurrent classes is identifying easily
checked properties that will, with high probability, fail if something goes wrong, while at the
same time not letting the failure-auditing code limit concurrency artificially. It is best if
checking the test property does not require any synchronization.

----

Tests should be run on multiprocessor systems to increase the diversity of potential interleavings.
However, having more than a few CPUs does not necessarily make tests more effective. To maximize
the chance of detecting timing-sensitive data races, there should be more active threads than CPUs,
so that at any given time some threads are running and some are switched out, thus reducing the
predicatability of interactions between threads.

## Avoiding performance testing pitfalls

### Dead code elimination

Writing effective performance tests requires tricking the optimizer into not optimizing away your
benchmark as dead code. This requires every computed result to be used somehow by your program - in
a way that does not require synchronization or substantial computation.
