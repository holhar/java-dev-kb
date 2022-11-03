# GUI Applications

## Why are GUIs single-threaded?

### Thread confinement in Swing

The _Swing single-threaded rule_: Swing components and models should be created, modified, and
queried only from the event-dispatching thread.

## Shared data models

### Split data models

Consider a split-model design when a data model must be shared by more than one thread and
implementing a thread-safe data model would be inadvisable because of blocking, consistency,
or complexity reasons.
