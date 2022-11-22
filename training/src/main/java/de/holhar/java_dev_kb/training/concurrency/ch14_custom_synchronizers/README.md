# Custom Synchronizers

## Using condition queues

### The condition predicate

Document the condition predicate(s) associated with a condition queue and the operations that wait on them.

----

Every call to `wait` is implicitly associated with a specific _condition predicate_. When calling `wait` regarding a
particular condition predicate, the caller must already hold the lock associated with the condition queue, and that
lock must also guard the state variables from which the condition predicate is composed.
