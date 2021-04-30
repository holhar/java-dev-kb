# Task Execution

## The Executor Framework

Using an Executor is usually the easiest path to implementing a producer-consumer design in your application.

---

Whenever you see code of the form:

    new Thread(runnable).start()

and you think you might at some point want a more flexible execution policy, seriously consider replacing it with 
the use of an Executor.

## Finding exploitable parallelism

The real performance payoff of dividing a program's workload into tasks comes when there are a large number of 
independent, *homogeneous* tasks that can be processed concurrently.