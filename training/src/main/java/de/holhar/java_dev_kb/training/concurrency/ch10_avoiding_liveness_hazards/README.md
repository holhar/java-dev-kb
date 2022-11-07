# Avoiding Liveness Hazards

## Deadlock

### Lock-ordering deadlocks

A program will be free of lock-ordering deadlocks if all threads  acquire the locks they need in a
fixed global order.

### Deadlocks between cooperating objects

Invoking an alien method with a lock held is asking for liveness trouble. The alien method might
acquire other locks (risking deadlock) or block for an unexpectedly long time, stalling other
threads that need the lock you hold.

### Open calls

Strive to use open calls throughout your program. Programs that rely on open calls are far easier
to analyze for deadlock-freedom than those that allow calls to alien methods with locks held.

## Other liveness hazards

### Starvation

Avoid the temptation to use thread priorities, since they increase platform dependence and can
cause liveness problems. Most concurrent applications can use the default priority for all threads.
