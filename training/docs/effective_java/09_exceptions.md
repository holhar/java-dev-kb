# Effective Java

## Exceptions

* when used to best advantage, exceptions can improve a program’s readability, reliability, and maintainability
* when used improperly, they can have the opposite effect
* this chapter provides guidelines for using exceptions effectively

---

### Item 57: Use exceptions only for exceptional conditions<a name="item57"></a>

* someday, if you are unlucky, you may stumble across a piece of code that looks something like this:

```Java
class SomeClass {
    public static void main(String[] args) {
        // Horrible abuse of exceptions. Don't ever do this!
        try {
            int i = 0;
            while(true)
                range[i++].climb();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }    
}
```

* What does this code do? It's not at all obvious from inspection, and that's reason enough not to use
  it ([Item 55](08_general_programming.md#item55))
    - it turns out to be a horribly ill-conceived idiom for looping through the elements of an array
    - the infinite loop terminates by throwing, catching, and ignoring an *ArrayIndexOutOfBoundsException* when it
      attempts to access the first array element outside the bounds of the array
    - it's supposed to be equivalent to the stanrd idiom for looping throught an array, which is instantly recognizable
      to any Java programmer:

```Java
class SomeClass {
    public static void main(String[] args) {
        for(Mountain m : range)
            m.climb();
    }
}
```

* so why would anyone use the exception-based loop in preference to the tried and true?
    - it's a misguided attempt to improve performance based on the faulty reasoning that, since the VM checks the bound
      of all array accesses, the normal loo p termination test - hidden by the compiler but still present in the
      for-each loop - is redundant and should be avoided
    - there are three things wrong with this reasoning:
        + because exceptions are designed for exceptional circumstances, there is little incentive for JVM implementors
          to make them as fast as explicit tests
        + placing code inside a *try-catch* block inhibits certain optimizations that modern JVM implementations might
          otherwise perform
        + the standard idiom for looping through an array doesn't necessarily result in redundant checks - modern JVM
          implementations optimize them away
    - in fact, the exception-based idiom is far slower than the standard one on modern JVM implementations
    - not only does the exception-based loop obfuscate the purpose of the code and reduce its performance, but it's not
      guaranteed to work!
        + in the presence of an unrelated bug, the loop can fail silentrly and mask the bug, greatly complicating the
          debugging process
* the moral of this story is simple: **exceptions are, as their name implies, to be used only for exceptional
  conditions; they should never be used for ordinary control flow**
* this principle also has implications for API design: **a well-designed API must not force its clients to use
  exceptions for ordinary control flow**
    - a class with a "state-dependent" method that can be invoked only under certain unpredictable conditions should
      generally have a separate "state-testing" method indicating whether it is appropriate to invoke the
      state-dependent method
    - for example, the *Iterator* interface has the state-dependent method *next* and the corresponding state-testing
      method *hasNext*
        + this enables the standard idiom for iterating over a collection with a traditional *for loop* (as well as
          the *for-each loop*, where the *hasNext* method is used internally):

```Java
class SomeClass {
    public static void main(String[] args) {
        for (Iterator<Foo> i = collection.iterator(); i.hasNext(); ) {
            Foo foo = i.next();
            // ...
        }
    }
}
```

* If *Iterator* lacked the *hasNext* method, clients would be forced to do this instead:

```Java
class SomeClass {
    public static void main(String[] args) {
        // Do not use this hideous code for iteration over a collection!
        try {
            Iterator<Foo> i = collection.iterator();
            while(true) {
                Foo foo = i.next();
                // ...
            }
        } catch (NoSuchElementException e) {
        }
    }
}
```

* this should look very familiar after the array iteration example that began this item
    - in addition to being wordy and misleading, the exception-based loop is likely to perform poorly and can mask bugs
      in unrelated parts of the system
* an alternative to providing a separate state-testing method is to have the state-dependent method return a
  distinguished value such as *null* if it is invoked with the object in an inappropriate state
    - this technique would not be appropriate for *Iterator*, as *null* is a legitimate return value for the *next*
      method

#### Guidelines to help to choose between a state-testing method and a distinguished return value

* if an object is to be accessed concurrently without external synchronization or is subject to externally induced state
  transitions, you must use a distinguished return value, as the object's state could change in the interval between the
  invocation of the state-testing method and its state-dependent method
* performance concerns may dictate that a distinguished return value be used if a separate state-testing method would
  duplicate the work of the state-dependent method
* all other things being equal, a state-testing method is mildly preferable to a distinguished return value
    - it offers slightly better readability, and incorrect use may be easier to detect:
        + if you forget to call a state-testing method, the state-dependent method will throw an exception, making the
          bug obvious
        + if you forget to check for a distinguished return value, the bug may be subtle

---

### Item 58: Use checked exceptions for recoverable conditions and runtime exceptions for programming erros<a name="item58"></a>

* the Java programming language provides three kinds of throwables: *checked exceptions, runtime exceptions, and errors*
    - there is some confusion among programmers as to when it is appropriate to use each kind of throwable
    - while the decision is not always clear-cut, there are some general rules that provide strong guidance
* the cardinal rule in deciding whether to use a checked or an unchecked exception is this: **use checked exceptions for
  conditions from which the caller can reasonably be expected to recover**
    - by throwing a checked exception, you force the caller to handle the exception in a *catch* clause or to propagate
      it outward
    - each checked exception that a method is declared to throw is therefore a potent indication to the API user that
      the associated condition is a possible outcome of invoking the method
    - by confronting the API user with a checked exception, the API designer presents a mandate to recover from the
      condition
    - the use can disregard the mandate by catching the exception and ignoring it, but this is usually a bad
      idea ([Item 65](#item65))
* there are two kinds of unchecked throwables: runtime exceptions and errors
    - they are identical in their behavior: both are throwables that needn't, and generally shouldn't, be caught
    - if a program throws an unchecked exception or an error, it is generally the case that recovery is impossible and
      continued execution would do more harm than good
    - if a program dies not catch such a throwable, it will cause the current thread to halt with an appropriate error
      message
* **use runtime exceptions to indicate programming errors**
    - the great majority of runtime exceptions indicate *precondition violations*
    - a precondition violation is simply a failure by the client of an API to adhere to the contract established by the
      API specification
    - for example, the contract for array access specifies that the array index must be between zero and the array
      length minus one
        + *ArrayIndexOutOfBoundsException* indicates that this precondition was violated
* while the Java Language Specification does not require it, there is a strong convention that errors are reserved for
  use by the JVM to indicate resource deficiencies, invariant failures, or other conditions that make it impossible to
  continure execution
    - given the almost universal acceptance of this convention, it's best not to implemlent any new *Error* subclasses
    - therefore, **all of the unchecked throwables you implement should subclass *RuntimeException*** (directly or
      indirectly)
* API designers often forget that exceptions are full-fledged objects on which arbitrary methods can be defined
    - the primary use of such methods is to provide the code that catches the exception with additional information
      concerning the condition that caused the exception to be thrown
    - in the absence of such methods, programmers have been known to parse the string representation of an exception to
      ferret out additional information
        + this is extremely bad practice ([Item 10](03_methods_common_to_all_objects.md#item10))
        + classes seldom specify the details of their string representations, so string representations can differ from
          implementation to implementation and release to release
        + therefore, code that parses the string representation of an exception is likely to be nonportable and fragile
* because checked exceptions generally indicate recoverable conditions, it’s especially important for such exceptions to
  provide methods that furnish information that could help the caller to recover
    - for example, suppose a checked exception is thrown when an attempt to make a purchase with a gift card fails
      because the card doesn’t have enough money left on it
    - the exception should provide an accessor method to query the amount of the shortfall, so the amount can be relayed
      to the shopper

---

### Item 59: Avoid unnecessary use of checked exceptions<a name="item59"></a>

* checked exceptions are a wonderful feature of the Java programming language
    - unlike return codes, they *force* the programmer to deal with exceptional conditions, greatly enhancing
      reliability
* that said, overuse of checked exceptions can make an API far less pleasant to use
    - if a method throws one or more checked exceptions, the code that invokes the method must handle the exceptions and
      let them propagate outward
    - either way, it places a nontrivial burden on the programmer
* the burden is justified if the exceptional condition cannot be prevented by proper use of the API *and* the programmer
  using the API can take some useful action once contronted with the exception
    - as a litmus test, ask yourself how the programmer will handle the exception
    - Is this the best that can be done?:

```Java
class SomeClass {
    public static void main(String[] args) {
        try {
            // ...
        } catch(TheCheckedException e) {
            throw new AssertionError(); // Can't happen!
        }       
    }
}
```

* How about this?:

```Java
class SomeClass {
    public static void main(String[] args) {
        try {
            // ...
        } catch(TheCheckedException e) {
            e.printStackTrace();        // Oh well, we lose.
            System.exit(1);
        }
    }
}
```

* if the programmer using the API can do no better, an unchecked exception would be more appropriate
* the additional burden on the programmer caused by a checked exception is substantially higher if it is the *sole*
  checked exception thrown by a method
    - if there are other, the method must already appear in a *try* block, and this exception merely requires another *
      catch* block
    - if a method throws a single checked exception, this exception alone is responsible for the fact that the method
      must appear in a *try* block
        + under these circumstances, it pays to ask yourself whether there isn't some way to avoid the checked exception

#### Turning checked exceptions into unchecked exceptions

* one technique for turning a checked exception into an unchecked exception is to break the method that throws the
  exception into two methods, the first of which return a *boolean* that indicates whether the exception would be thrown
    - this API refactoring transforms the calling sequence from this:

```Java
class SomeClass {
    public static void main(String[] args) {
        // Invocation with checked exception
        try {
            obj.action(args);
        } catch(TheCheckedException e) {
            // Handle exceptional condition...
        }
    }
}
```

* to this:

```Java
class SomeClass {
    public static void main(String[] args) {
        // Invocation with state-testing method and unchecked exception
        if (obj.actionPermitted(args)) {
            obj.action(args);
        } else {
            // Handle exceptional condition...
        }
    }
}
```

* this refactoring is not always appropriate, but where it is appropriate, it can make an API more pleasant to use
    - while the latter calling sequence is no prettiert than the former, the resulting API is more flexible
    - in cases where the programmer knows the call will succeed or is content to let the thread terminate if the call
      fails
    - the refactoring also allows this simple calling sequence:

```
obj.action(args)
```

* if you suspect that the simple calling sequence will be the norm, then this API refactoring may be appropriate
* the API resulting from this refactoring is essentially identical to the state-testing method API in [Item 57](#item57)
  and the same caveats apply:
    - if an object is to be accessed concurrently without external synchronization or it is subject to externally
      induced state transitions, this refactoring is inappropriate, as the object's state may change between the
      invocations of *actionPermitted* and action
    - if a separate *actionPermitted* method would, of necessity, duplicate the work of the action method, the
      refactoring may be ruled out by performance concerns

---

### Item 60: Favor the use of standard exceptions<a name="item60"></a>

* exceptions are no exception to the general rule that code reuse is good
    - the Java platform libraries provide a basic set of unchecked exceptions that cover a large fraction of the
      exception-throwing needs of most APIs
    - in this item, we’ll discuss these commonly reused exceptions
* reusing preexisting exceptions has several benefits:
    - it makes your API easiert to learn and use because it matches established conventions with which programmers are
      already familiar
    - programs using your API are easier to read because they aren't cluttered with unfamiliar exceptions
    - fewer exception classes mean a smaller memory footprint and less time spent loading classes

* the most commonly reused exception is **IllegalArgumentException**
    - this is generally the exception to throw when the caller passes in an argument whose value is inappropriate
    - for example, this would be the exception to throw if the caller passed a negative number in a parameter
      representing the number of times some action was to be repeated
* another commonly reused exception is **IllegalStateException**
    - this is generally the exception to throw if the invocation is illegal because of the state of the receiving object
    - for example. this would be the exception to throw if the caller attempted to use some object before it had been
      properly initialized
* if a caller passes *null* in some parameter for which null values are prohibited, convention dictates that **
  NullPointerException** be thrown rather than **IllegalArgumentException**
* similarly, if a caller passes an out-of-range value in a parameter representing an index into a sequence, **
  IndexOutOfBoundsException** should be thrown rather thann **IllegalArgumentException**
* another general-purpose exception worth knowing about is **ConcurrentModificationException**
    - this exception should be thrown if an object that was designed for use by a single thread or with external
      synchronization detects that it is being (or has been) concurrently modified
* a last general-purpose exception worthy of note is **UnsupportedOperationException**
    - this is the exception to throw if an object does not support an attempted operation
    - its use is rare compared to the other exceptions discussed in this item, as most objects support all the methods
      they implement
    - this exception is used by implementations that fail to implement one or more optional operations defined by an
      interface
    - for example, an append-only *list* implementation would throw this exception if someone tried to delete an element
      from the list

| Exception                         | Occasion for Use                                                              |
|-----------------------------------|-------------------------------------------------------------------------------|
| IllegalArgumentException          | Non-null parameter value is inappropriate                                     |
| IllegalStateException             | Object state is inappropriate for method invocation                           |
| NullPointerException              | Parameter value is null where prohibited                                      |
| IndexOutOfBoundsException         | Index parameter value is out of range                                         |
| ConcurrentModificationException   | Concurrent modification of an object has been detected where it is prohibited |
| UnsupportedOperationException     | Object does not support method                                                |

* while these are by far the most commonly reused exceptions in the Java platform libraries, other exceptions may be
  reused where circumstances warrant
* if an exception fits your needs, go ahead and use it, but only if the conditions under which you would throw it are
  consistent with the exception’s documentation
    - reuse must be based on semantics, not just on name
* also, feel free to subclass an existing exception if you want to add a bit more failure-capture
  information ([Item 63](#item63)).
* finally, be aware that choosing which exception to reuse is not always an exact science, as the occasions for use in
  the table above are not mutually exclusive

---

### Item 61: Throw exceptions appropriate to the abstraction<a name="item61"></a>

* it is irritating when a method throws an exception that has no apparent connection to the task that it performs
    - this often happens when a method propagates an exception thrown by a lower-level abstraction
    - not only is this confusing, but it pollutes the API of the higher layer with implementation details
    - if the implementation of the higher layer changes in subsequent release, the exceptions that it throws will change
      too, potentially breaking existing client programs
* to avoid this problem, **higher layers should catch lower-level exceptions and, in their place, throw exceptions that
  can be explained in terms of the higher-level abstraction**
    - this idiom is known as **exception translation**:

```Java
class SomeClass {
    public static void main(String[] args) {
        // Exception Translation
        try {
            // Use lower-level abstraction to do our bidding
            // ...
        } catch (LowerLevelException e) {
            throw new HigherLevelException();
        }
    }
}
```

* here is an example of exception translation taken from the *AbstractSequentialList* class, which is a *skeletal
  implementation ([Item 18](04_classes_and_interfaces.md#item18)) of the *List* interface
    - in this example, exception translation is mandated by the specification of the *get* method in the *List\<E\>*
      interface:

```Java
class AbstractSequentialList {
    // ...
    
    /**
     * Returns the element at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range
     *          ({@code index < 0 || index >= size()}).
     */
    public E get(int index) {
        ListIterator<E> i = listIterator(index);
        try {
            return i.next();
        } catch (NoSuchElementException e) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }
}
```

* a special form of exception translation called **exception chaining** is appropriate in cases where the lower-level
  exception might be helpful to someone debugging the problem that caused the higher-level exception
    - the lower-level exception (the *cause*) is passed to the higher-level exception, which provides an accessor
      method (*Throwable.getCause*) to retrieve the lower-level exception:

```Java
class SomeClass {
    public static void main(String[] args) {
        // Exception chaining
        try {
            // Use lower-level abstraction to do our bidding
            // ...
        } catch (LowerLevelException cause) {
            throw new HigherLevelException(cause);
        }
    }
}
```

* the higher-level exception's constructor passes the cause to a *chaining-aware* superclass constructor, so it is
  ultimately passed to one of *Throwable*'s chaining-aware constructors, such as *Throwable(Throwable)*:

```Java
// Exception with chaining-aware constructor
class HigherLevelException extends Exception {
    HigherLevelException(Throwable cause) {
        super(cause);
    }
}
```

* most standard exceptions have chaining-aware constructors
    - for exceptions that don't, you can set the cause using *Throwable*'s *initCause* method
    - not only does exception chaining let you access the cause programmaticall (with *getCause), but it integrates the
      caus's stack trace into that of the higher-level exception
* **while exception translation is superior to mindless propagation of exceptions from lower layers, it should not be
  overused**
    - where possible, the best way to deal with exceptions from lower layers is to avoid them, by ensuring that
      lower-level methods succeed
    - sometimes you can do this by checking the validity of the higher-level method's parameters before passing them on
      to lower layers
* **if it is impossible to prevent exceptions from lower layers, the next best thing is to have the higher layer
  silently work around these exceptions, isolating the caller of the higher-level method from lower-level problems**
    - under these circumstances, it may be appropriate to log the exception using some appropriate logging facility such
      as *java.util.logging*
    - this allows an administrator to investigate the problem, while isolating the client code and the end user from it

---

### Item 62: Document all exceptions thrown by each method<a name="item62"></a>

* a description of the exceptions thrown by a method is an important part of the documentation required to use the
  method properly
    - therefore, it is critically important that you take the time to carefully document all of the exceptions thrown by
      each method
* **always declare checked exceptions individually, and document precisely the conditions under which each one is thrown
  using the Javadoc @throws tag**
    - don't take the shortcut of declaring that a method throws some superclass of multiple exception classes that it
      can throw
    - as an extreme example, never declare that a method "throws Exception" or, worse yet, "throws Throwable"
    - in addition to denying any guidance to the method's user concerning the exceptions that it is capable of throwing,
      such a declaration greatly hinders the use of the method, as it effectively obscures any other exception that may
      be thrown in the same context
* **while the language does not require programmers to declare the unchecked exceptions that a method is capable of
  throwing, it is wise to document them as carefully as the checked exceptions**
    - unchecked exceptions generally represent programming errors ([Item 58](#item58)), and familiarizing programmers
      with all of the errors they can make helps them avoid making these errors
    - a well-documented list of the unchecked exceptions that a method can throw effectively describes the preconditions
      for its successful execution
    - it is essential that each method's documentation describe its preconditions, and documenting its unchecked
      exceptions is the best way to satisfy this requirement
* it is particularly important that methods in interfaces document the unchecked exceptions they may throw
    - this documentation forms a part of the interface's *general contract* and enables common behavior among multiple
      implementations of the interface
* **use the Javadoc *@throws* tag to document each unchecked exception that a method can throw, but do not use the
  throws keyword to include unchecked exceptions in the method declaration**
    - it is important that the programmers using your API be aware of which exceptions are checked and which are
      unchecked, as their responsibilities differ in these two cases
    - the documentation generated by the Javadoc @throws tag in the absence of the method header generated by the throws
      declaration provides a strong visual cue to help the programmer distinguish checked exceptions from unchecked
* **if an exception is thrown by many methods in a class for the same reason, it is acceptable to document the exception
  in the class’s documentation comment** rather than documenting it individually for each method
    - a common example is *NullPointerException*
    - it is fine for a class's documentation comment to say, "All methods in this class throw a *NullPointerException*
      if a *null* object reference is passed in any parameter," or words to that effect

---

### Item 63: Include failure-capture information in detail messages<a name="item63"></a>

* When a program fails due to an uncaught exception, the system automatically prints out the exception's stack trace
    - the stack trace contains the exception’s string representation, the result of invoking its *toString* method
    - this typically consists of the exception's class name followed by its detail message
    - frequently this is the only information that programmers or field service personnel will have when investigating a
      software failure
    - if the failure is not easily reproducible, it may be difficult or impossible to get any more information
    - therefore, it is critically important that the exception's *toString* method return as much information as
      possible concerning the cause of the failure
    - in other words, the detail message of an exception should capture the failure for subsequent analysis
* **to capture the failure, the detail message of an exception should contain the values of all parameters and fields
  that "contributed to the exception"**
    - for example, the detail message of an IndexOutOfBoundsException should contain the lower bound, the upper bound,
      and the index value that failed to lie between the bounds
    - while it is critical to include all of the pertinent "hard data" in the detail message of an exception, it is
      generally unimportant to include a lot of prose
* the detail message of an exception should not be confused with a user-level error message, which must be intelligible
  to end users
    - unlike a user-level error message, it is primarily for the benefit of programmers or field service personnel for
      use when analyzing a failure
* one way to ensure that exceptions contain adequate failure-capture information in their detail messages is to require
  this information in their constructors instead of a string detail message
    - the detail message can then be generated automatically to include the information
    - for example, instead of a String constructor, *IndexOutOfBoundsException* could have had a constructor that looks
      like this:

```Java
public class IndexOutOfBoundsException {
    
    /**
    * Construct an IndexOutOfBoundsException.
    *
    * @param lowerBound the lowest legal index value.
    * @param upperBound the highest legal index value plus one.
    * @param index the actual index value.
    */
    public IndexOutOfBoundsException(int lowerBound, int upperBound, 
                                     int index) {
        // Generate a detail message that captures the failure
        super("Lower bound: " + lowerBound +
              ", Upper bound: " + upperBound +
              ", Index: " + index);
    
        // Save failure information for programmatic access
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.index = index;
    }
}
```

* unfortunately, the Java platform libraries do not make heavy use of this idiom, but it is highly recommended
    - it makes it easy for the programmer throwing an exception to capture the failure
    - in fact, it makes it hard for the programmer not to capture the failure!
* as suggested in [Item 58](#item58), it may be appropriate for an exception to provide accessor methods for its
  failure-capture information (*lowerBound*, *upperBound*, and *index* in the above example)
    - it is more important to provide such accessor methods on checked exceptions than on unchecked exceptions, because
      the failure-capture information could be useful in recovering from the failure

---

### Item 64: Strive for failure atomicity<a name="item64"></a>

* after an object throws an exception, it is generally desirable that the object still be in a well-defined, usable
  state, even if the failure occurred in the midst of performing an operation
    - this is especially true for checked exceptions, from which the caller is expected to recover
    - **generally speaking, a failed method invocation should leave the object in the state that it was in prior to the
      invocation**
    - a method with this property is said to be *failure atomic*
* there are several ways to achieve this effect
    - the simplest is to design immutable objects ([Item 15](04_classes_and_interfaces.md#item15))
        + if an object is immutable, failure atomicity is free
        + if an operation fails, it may prevent a new object from getting created, but it will never leave an existing
          object in an inconsistent state, because the state of each object is consistent when it is created and can’t
          be modified thereafter
    - for methods that operate on mutable objects, the most common way to achieve failure atomicity is to check
      parameters for validity before performing the operation ([Item 38](07_methods.md#item38))
        + this causes any exception to get thrown before object modification commences
        + for example, consider the *Stack.pop* method in [Item 6](02_creating_and_destroying_objects.md#item6):

```Java
public class Stack {
    // ...
    
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
}
```

* continuing on ways to achieve failure atomiciy:
    - still regarding validity checks:
        + if the initial size check were eliminated, the method would still throw an exception when it attempted to pop
          an element from an empty stack
        + it would, however, leave the size field in an inconsistent (negative) state, causing any future method
          invocations on the object to fail
        + additionally, the exception thrown by the pop method would be inappropriate to the
          abstraction ([Item 61](#item61))
    - a closely related approach to achieving failure atomicity is to order the computation so that any part that may
      fail takes place before any part that modifies the object
        + this approach is a natural extension of the previous one when arguments cannot be checked without performing a
          part of the computation
        + for example, consider the case of *TreeMap*, whose elements are sorted according to some ordering
        + in order to add an element to a *TreeMap*, the element must be of a type that can be compared using the *
          TreeMap*'s ordering
        + attempting to add an incorrectly typed element will naturally fail with a *ClassCastException* as a result of
          searching for the element in the tree, before the tree has been modified in any way
    - a third and far less common approach to achieving failure atomicity is to write recovery code that intercepts a
      failure that occurs in the midst of an operation and causes the object to roll back its state to the point before
      the operation began
        + this approach is used mainly for durable (disk-based) data structures
    - a final approach to achieving failure atomicity is to perform the operation on a temporary copy of the object and
      to replace the contents of the object with the temporary copy once the operation is complete
        - this approach occurs naturally when the computation can be performed more quickly once the data has been
          stored in a temporary data structure

* while failure atomicity is generally desirable, it is not always achievable
    - for example, if two threads attempt to modify the same object concurrently without proper synchronization, the
      object may be left in an inconsistent state
    - it would therefore be wrong to assume that an object was still usable after catching a *
      ConcurrentModificationException*
    - **as a rule, errors (as opposed to exceptions) are unrecoverable, and methods need not even attempt to preserve
      failure atomicity when throwing errors**
* even where failure atomicity is possible, it is not always desirable
    - for some operations, it would significantly increase the cost or complexity
    - that said, it is often both free and easy to achieve failure atomicity once you’re aware of the issue
* **as a rule, any generated exception that is part of a method's specification should leave the object in the same
  state it was in prior to the method invocation**
    - where this rule is violated, the API documentation should clearly indicate what state the object will be left in
    - unfortunately, plenty of existing API documentation fails to live up to this ideal

---

### Item 65: Don't ignore exceptions<a name="item65"></a>

* when the designers of an API declare a method to throw an exception, they are trying to tell you something
    - don't ignore it!
    - it is easy to ignore exceptions by surrounding a method invocation with a *try* statement with an empty *catch*
      block:

```Java
class SomeClass {
    public static void main(String[] args) {
        // Empty catch block ignores exception - Highly suspect!
        try {
            // ...
        } catch (SomeException e) {
        }
    }
}
```

* **an empty *catch* block defeats the purpose of exceptions**, which is to force you to handle exceptional conditions
    - ignoring an exception is analogous to ignoring a fire alarm — and turning it off so no one else gets a chance to
      see if there’s a real fire
    - you may get away with it, or the results may be disastrous
    - whenever you see an empty catch block, alarm bells should go off in your head
    - **at the very least, the *catch* block should contain a comment explaining why it is appropriate to ignore the
      exception**
* an example of the sort of situation where it might be appropriate to ignore an exception is when closing a *
  FileInputStream*
    - you haven't changed the state of the file, so there's no need to perform any recovery action, and you've already
      read the information that you need from the file, so there's no reason to abort the operation in progress
    - even in this case, it is wise to log the exception, so that you can investigate the matter if these exceptions
      happen often
* the advice in this item applies equally to checked and unchecked exceptions
    - whether an exception represents a predictable exceptional condition or a programming error, ignoring it with an
      empty *catch* block will result in a program that continues silently in the face of error
    - **the program might then fail at an arbitrary time in the future, at a point in the code that bears no apparent
      relation to the source of the problem**
    - properly handling an exception can avert failure entirely
    - merely letting an exception propagate outward can at least cause the program to fail swiftly, preserving
      information to aid in debugging the failure