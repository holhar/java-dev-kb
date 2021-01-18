# Heuristics

## Coding Conventions

### Basic Rules

* Keep it human-readable
* Keep it short and simple (KISS)
* Keep it natural
* Keep it clean

### Program Design

* Keep the source code clean
* avoid side effects
* avoid excessive log and debug statements
* avoid output by ```System.out```
* use ```final```
* avoid **magic numbers** and **Strings**
* prefer few ```return``` statements
* validate input arguments
* use Assertions and Exceptions to hedge pre and post conditions
* avoid careless return statements of ```null```
* avoid ```null``` if possible, check references to ```null```
* check return values and handle Exceptions
* avoid ```catch(Exception ex)``` and ```catch (Throwable th)```
* avoid ```return``` in ```catch/finally``` blocks

### Class Design

* prefer local variables against attributes
* avoid static attributes
* access attributes via methods
* avoid fine grained adjustments the objects states
* minimize state changes
* ...