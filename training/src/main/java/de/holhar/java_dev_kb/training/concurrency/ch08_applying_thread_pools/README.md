# Applying Thread Pools

## Implicit couplings between tasks and execution policies

Some tasks have characteristics that require or preclude a specific execution policy. Tasks that
depend on other tasks require that the thread pool be large enough that tasks are never queued or
rejected; tasks that exploit thread confinement require sequential execution. Document these
requirements so that future maintainers do not undermine safety or liveness by substituting an
incompatible execution policy.

### Thread starvation deadlock

Whenever you submit to Executor tasks that are not independent, be aware of the possibility of
thread starvation deadlock, and document any pool sizing or configuration constraints in the code
or configuration file where the Executor is configured.

----

## Sizing thread pools

    N_threads = N_cpu * U_cpu * (1 + W/C)

----

## Configuring ThreadPoolExecutor

### Managing queued tasks

The newCachedThreadPool factory is a good default choice for an Executor, providing better
queueing performance than a fixed thread pool. A fixed size thread pool is a good choice when you
need to limit the number of concurrent tasks for resource-management purposes, as in a server
application that accepts requests from network clients and would otherwise be vulnerable to
overload.

----

## Parallelizing recursive algorithms

Sequential loop iterations are suitable for prallelization when each iteration is independent of
the others and the work done in each iteration of the loop body is significant enough to offset the
cost of managing a new task.
