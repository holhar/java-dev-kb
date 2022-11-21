# Explicit Locks

## Performance considerations

Performance is a moving target; yesterday's benchmark showing that X is faster than Y may already
be out of date today.

## Choosing between synchronized and ReentrantLock

ReentrantLock is an advanced tool for situations where intrinsic locking is not practical. Use it if
you need its advanced features: timed, polled, or interruptible lock acquisition, fair queueing, or
non-block-structured locking. Otherwise, prefer synchronized.
