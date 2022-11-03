# Composing Objects

## Designing a thread-safe class

The design process for a thread-safe class should include these three basic elements:

* Identify the variables that form the object's state;
* Identify the invariants that constrain the state variables;
* Establish a policy for managing concurrent access to the object's state.

### Gathering synchronization requirements

You cannot ensure thread safety without understanding an object's invariants and post-conditions.
Constraints on the valid values or state transitions for state variables can create atomicity and encapsulation 
requirements.

## Instance confinement

Encapsulating data within an object confines access to the data to the object's methods, making it easier to ensure 
that the data is always accessed with the appropriate lock held.

---

Confinement makes it easier to build thread-safe classes because a class that confines its state can be analyzed for 
thread safety without having to examine the whole program.

## Delegating thread safety

### When delegation fails

If a class is composed of multiple *independent* thread-safe state variables and has no operations that have any 
invalid state transitions, then it can delegate thread safety to the underlying state variables.

### Publishing underlying state variables

If a state variable is thread-safe, does not participate in any invariants that constrain its value, and has no 
prohibited state transitions for any of its operations, then it can safely be published.

## Documenting synchronization policies

Document a class's thread safety guarantees for its clients; document its synchronization policy for its maintainers.

