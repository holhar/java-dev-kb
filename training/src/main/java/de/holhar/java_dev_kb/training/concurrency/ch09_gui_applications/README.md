# GUI Applications

## Why are GUIs single-threaded?

### Thread confinement in Swing

The _Swing single-threaded rule_: Swing components and models should be created, modified, and
queried only from the event-dispatching thread.
