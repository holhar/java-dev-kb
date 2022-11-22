# Custom Synchronizers

## Using condition queues

### The condition predicate

Document the condition predicate(s) associated with a condition queue and the operations that wait on them.

----

Every call to `wait` is implicitly associated with a specific _condition predicate_. When calling `wait` regarding a
particular condition predicate, the caller must already hold the lock associated with the condition queue, and that
lock must also guard the state variables from which the condition predicate is composed.

### Waking up too soon

When using condition waits (`Object.wait` or `Condition.wait`:

* Always have a condition predicate - some test of object state that must hold before proceeding;
* Always test the condition predicate before calling `wait`, and again after returning from `wait`;
* Always call `wait` in a loop;
* Ensure that the state variables making up the condition predicate are guarded by the lock associated with the 
  condition queue;
* Hold the lock associated with the condition queue when calling `wait`, `notify`, or `notifyAll`; and
* Do not release the lock after checking the condition predicate but before acting on it.

### Notification

Whenever you wait on a condition, make sure that someone will perform a notification whenever the condition predicate
becomes true.

----

Single `notify` can be used instead of `notifyAll` only when both of the following conditions hold:

* **Uniform waiters**. Only one condition predicate is associated with the condition queue, and each thread executes 
  the same logic upon returning from `wait`; and
* **One-in, one-out**. A notification on the condition variable enables at most one thread to proceed.

## Explicit condition objects

Hazard warning: The equivalents of `wait`, `notify`, and `notifyAll` for `Condition` objects are `await`, `signal`, 
and `signalAll`. However, `Condition` extends `Object`, which means that it also has `wait` and `notify` methods. Be 
sure to use the proper versions -- `await` and `signal` -- instead!
