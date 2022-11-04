# Avoiding Liveness Hazards

## Deadlock

### Lock-ordering deadlocks

A program will be free of lock-ordering deadlocks if all threads  acquire the locks they need in a
fixed global order.

### Deadlocks between cooperating objects

Invoking an alien method with a lock held is asking for liveness trouble. The alien method might
acquire other locks (risking deadlock) or block for an unexpectedly long time, stalling other
threads that need the lock you hold.
