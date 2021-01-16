# Effective Java

## 4. Classes and Interfaces

Classes and interfaces lie at the heart of the Java programming language. They are its basic units of abstraction. The
language provides many powerful elements that you can use to design classes and interfaces. This chapter contains
guidelines to help you make the best use of these elements so that your classes and interfaces are usable, robust, and
flexible.

---

### Item 13: Minimize the accessibility of classes and members<a name="item13"></a>

The single most important factor that distinguishes a well-designed module from a poorly designed one is the degree to
which the module hides its internal data and other implementation details from other modules. A well-designed module
hides all of its implementation details, cleanly separating its API from its implementation. MOdules then communicate
only through their APIs and are oblivious to each others' inner workings. This concepts, known as *information hiding*
or *encapsulation*, is one of the fundamental tenets of software
design [Parnas72](http://repository.cmu.edu/cgi/viewcontent.cgi?article=2828&context=compsci).

#### Reasons for encapsulation

* *decouples* modules that comprise a system - develop, test, optimize, use, understand, and modify in isolation!
* enables effective performance tuning: once a system is complete and profiling has determined which modules are causing
  performance problems [Item 55](08_general_programming.md#item55)
* increases software reuse
* decreases the risk in building large systems, because individual modules may prove successful even if the system does
  not

#### Java access control mechanism - [JLS, 6.6](https://docs.oracle.com/javase/specs/jls/se7/html/jls-6.html#jls-6.6)

* accessibility of en entity is determined by **the location of its declaration** and by which, if any, of the **access
  modifiers** is present on the declaration: *private, protected, and public*
* **make each class or member as inaccessible as possible**

##### Classes and Interfaces

* for top-level (non-nested) classes and interfaces there are only two possible access levels: *public* and *
  package-private* (in case no modifier is provided)
* *use package-private where possible* ot make it *part of the implementation* rather than part of the exportet API -
  makes modification, replacement, and elimination possible in future releases
* if the entity is package-private and only used by one class, consider to *make it a private nested class* of the sole
  class that uses it [Item 22](#item22)

##### Members (fields, methods, nested classes, and nested interfaces)

* access levels:
    * **private** - The member is accessible only from the top-level class where it is declared.
    * **package-private** - The member is accessible from any class in the package where it is declared. Technically
      known as *default access*, this is the access level you get if no access modifier is specified.
    * **protected** - The member is accessble from subclasses of the class where it is declared (subject to a few
      restrictions - [JLS, 6.6.2](https://docs.oracle.com/javase/specs/jls/se7/html/jls-6.html#jls-6.6.2)) and from any
      class in the package where it is declared.
    * **public** - The member is accessible from anywhere.

#### Further considerations

* After carefully designing your class's public API, your reflex should be to make all other members private (or at
  least package-private if necessary)
* if you are forced to use package-private often, you should reexamine the design of your system to see if another
  decomposition might yield classes that are better decouled from one another
* private and package-private are part of the implementation and can only "lead" into the exported API if the class
  implements *Serializable* ([Item74](11_serialization.md#item74), [Item74](11_serialization.md#item74))

* protected members are part of the class's exported API and must be supported forever
* protected members of an exported class represent a public commitment to an implementation detail ([Item 17](#item17))
* the need for protected members should be relatively rare

* if a method overrides a superclass method, it is not permitted to have a lower access level in the subclass than it
  does in the superclass ([JLS, 8.4.8.3](https://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#jls-8.4.8.3)) -
  this is necessary to ensure that an instance of the subclass is usable anywhere that an instance of the superclass is
  usable
* it is fine to facilitate testing by making a class, interface, or member more accessbile, up to a certain point (which
  shouldn't be higher than package-private)

* **instance fields should never be public** [Item 14](#item14)
* **classes with public mutable fields are not thread-safe**
* even if a field is final and refers to an immutable object, by making the field public you give up the flexibility to
  switch to a new internal data representation in which the field does not exist
* The same advice applies to static fields, with the one exception: you can expose *
  constants* [Item 56](08_general_programming.md#item56) via public static final fields, assuming the constants form an
  integral part of the abstraction provided by the class
* It's critical that these fields contain either primitive values or references to immutable
  objects ([Item 15](#item15))
* **it is wrong for a class to have a public static final array field, or an accessor that returns such a field** - if a
  class has such a field or accessor, client will be able to midfy the contents of the array (*security holws*):

```Java
// Potentials security hole!
public static final Thing[] VALUES = { ... };
```

* there are two ways to fix this problem: you can make the public array private and add a public immutable list:

```Java
private static final Thing[] PRIVATE_VALUES = { ... };
public static final List<Thing> VALUES =
    Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
```

* Alternatively, you can make the array private and add a public method that returns a copy of a private array:

```Java
private static final Thing[] PRIVATE_VALUES = { ... };
public static final Thing[] values() {
    return PRIVATE_VALUES.clone();
}
```

* to choose between these alternatives, think about what the client is likely to do with the result

---

### Item 14: In public classes, use accessor methods, not public fields<a name="item14"></a>

```Java
// Degenerate classes like this should not be public!
class Point {
    public double x;
    public double y;
}
```

* b ecause the data fields of such classes are accessed directly, these classes do not offer the benefits of *
  encapsulation* ([Item 13](#item13))
* use **getters (accessor methods)** and **setters (mutators)** instead
* **if a class is accessible outside its package, provide accessor methods**
* **if a class is package-private or is a private nested class, there is nothing inherently wrong with exposing its data
  fields** - assuming they do an adequate job of describing the abstraction provided by the class (generates less visual
  clutter)

* summary:
    * public classes should never expose mutable fields
    * it is less harmful, though still questionable, for public classes to expose immutable fields
    * it is, however, sometimes desirable for package-private or private nested classes to expose fields, wheter mutable
      or immutable

---

### Item 15: Minimize mutability<a name="item15"></a>

* An immutable class is a class whose instances cannot be modified.
* Examples in the Java platform library are *String*, the boxed primitive classes, *BigInteger* and *BigDecimal*
* immutable classes are easier to design, implement, and use than mutable classes; they are less prone to error and more
  secure

#### Five rules to make a class immutable

1. **Don't provide any methods that modify the object's state**
2. **Ensure that the class can't be extended**
3. **Make all fields final**
4. **Make all fields private**
5. **Ensure exclusive access to any mutable components**. If your class has any fields that refer to mutable objects,
   ensure that clients of the class cannot obtain references to these objects. Never initialize such a field to a
   client-provided object reference or return the object reference from an accessor. Make *defensive
   copies* ([Item 39](07_methods.md#item39)) in constructors, accessors, and *readObject*
   methods ([Item 76](11_serialization.md#item76)).

#### Example

```Java
public final class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // Accessors with no corresponding mutators
    public double realPart() { return re; }
    public double imaginaryPart() { return im; }

    public Complex add(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    public Complex subtract(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    public Complex multiply(Complex c) {
        return new Complex(re * c.re - im * c.im,
                            re * c.im + im * c.re);
    }

    public Complex divide(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp,
                            (im * c.re - re * c.im) / tmp);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) 
            return true;
        if(!(o instanceof Complex))
            return false;
        Complex c = (Complex) o;

        // See page 43 to find out why we use compare instead of ==
        return Double.compare(re, c.re) == 0 &&
                Double.compare(im, c.im) == 0;
    }

    @Override
    public int hashCode() {
        int result = 17 + hashDouble(re);
        result = 31 * result + hashDouble(im);
        return result;
    }

    private int hashDouble(double val) {
        long longBits = Double.doubleToLongBits(re);
        return (int) (longBits ^ (longBits >>> 32));
    }

    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}
```

#### Discussion

* Notice how the arithmetic operations create and return a new *Complex* instance rather than modifying this instance
  => **functional approach**
    * in contrast to the more common **procedural** or **imperative approach** in which methods apply a procedure to
      their operand, causing its state to change
* **Immutable objects are simple**, as it can be in exactly one state, the state in which it was created
* **Immutabe objects are inherently thread-safe; they require no synchronization**
* **immutable objects can be shared freely**
    * provide public static final constants for frequently used values
    * for example, the *Complex* class might provide these constants:

```Java
public static final Complex ZERO = New Complex(0, 0);
public static final Complex ONE = New Complex(1, 0);
public static final Complex I = New Complex(0, 1);
```

* an immutable class can provide static factories ([Item 1](02_creating_and_destroying_objects.md#item1)) that cache
  frequently requested instances to avoid creating new instances when existing ones would do
    * using such static factories causes clients to share instances instead of creating new ones, reducing memory
      footprint and garbage collection costs
    * optin for static factories in place of public constrctors when designing a new class gives you the flexibility to
      add caching later, without modifying clients
* a consequence of the fact that immutable objects can be shared freely is that you never have to make *defensive
  copies* ([Item 39](07_methods.md#item39))
    * therefore, you need not and should not provide a *clone* method or *copy
      constructor* ([Item 11](03_methods_common_to_all_objects.md#item11))
* **Not only can you share immutable objects, but you can share their internals**
* **Immutable objects make great building blocks for other objects**, whether mutable or immutable - it's much easier to
  maintain the invariants of a complex object if you know that its component objects will not change underneath it (
  immutable objects make great map keys and set elements)
* **the only real disadvantage of immutable classes is that they require a separate object for each distinct value**
    * creating these objects can be costly, expecially if ehy are large (example see book, page 77).
    * the performance problem is magnified if you perform a multistep operation that generates a new object at every
      step, eventually discarding all objects except the final result
    * but *internally*, the immutable class can be arbitrarily clever
    * or there can be an package-private or public mutable companion class (for example *String* and *StringBuilder*)

#### Design alternatives

* alternative to making the class final is to make all constructors private or package-private, and to add public **
  static factories** in place of the public constructors ([Item 1](02_creating_and_destroying_objects.md#item1))
    - here's how *Complex* would look if you took this approach:

```Java
// Immutable class with static factories instead of constructors
public class Complex {
    private final double re;
    private final double im;

    private Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex valueOf(double re, double im) {
        return new Complex(re, im);
    }

    ... // Remainder unchanged
}
```

* this approach is not commonly used, but it is flexible:
    - allows the use of multiple package-private implementation classes
    - makes it possible to tune the performance of the class in subsequent releases by improving the **object-caching
      capabilities** of the static factories
    - for upcoming use cases it is easy to implement a new static factory, messy constructors can be avoided - just add
      a second static factory with a name that clearly identifies its function:

```Java
public static Complex valueOfPolar(double r, double theta) {
    return new Complex(r * Math.cos(theta),
                        r * Math.sin(theta));
}
```

* it was not widely understood that immutable classes hat to be effectively final when *BigInteger* and *BigDecimal*
  were written, so all of their methods may be overridden
    - if you write a class whose security depends on the immutability of a *BigInteger* or *BigDecimal* argument from an
      untrusted client, you must check to see that the argument is a "real" *BigInteger* or *BigDecimal*, rather than an
      instance of an untrusted subclass
    - if it is the latter, you must defensivley copy it under the assumption that it might be
      mutable ([Item 39](07_methods.md#item39))

```Java
public static BigInteger safeInstance(BigInteger val) {
    if(val.getClass() != BigInteger.class)
        return new BigInteger(val.toByteArray());
    return val;
}
```

The list of rules for immutable classes at the beginning of this item says that no methods may modify the object an that
all its fields must be final. In fact these **rules are a bit stronger than necessary and can be relaxed to improve
performance**.

* in truth, no method may produce an *externally visible* change in the object's state
* however, some immutable classes have one or more nonfinal fields in which they cache the results of expensive
  computations the first time they are needed - this trick works precisely because the object is immutable, which
  guarantees that the computation would yield the same result if it were repeated (**idempotence**)
    - for example, *PhoneNumber*'s *hashCode* method ([Item 9](03_methods_common_to_all_objects.md#item9)) computes the
      hash code the first time it's invoked and caches it in case it's invoked again.
    - this technique, an example of **lazy initialization** ([Item 71](10_concurrency.md#item71)), is also used by *
      String*
* if you choose to have your immutable class implement *Serializable* and it contains one or more fields that refer to
  mutable objects, you must provide an explicit *readObject* or *readResolve* methos, or use the *
  ObjectOutputStream.writeUnshared* and *ObjectInputStream.readUnshared* methods, even if the default serialized form is
  acceptable
    - otherwise an attacker could create a mutable instance of your not-quite-immutable class (covered in detail
      in [Item 76](11_serialization.md#item76))

#### Summary

* classes should be immutabe unless there's a very good reason to make them mutable
* if a class cannot be made immutable, limit its mutability as much as possible
* make every field final unless there is a compelling reason to make it nonfinal

---

### Item 16: Favor composition over inheritance<a name="item16"></a>

* Inheritance used inappropriately leads to fragile software
    - it is safe to use inheritance within a package, where the subclass and the superclass implementations are under
      the control of the same programmers
    - it is safe to use inheritance when extending classes specifically designed and documented for
      extension ([Item](#item17))
    - inheriting from ordinary concrete classes acreoss package boundaries, however, is dangerous (the problems
      discussed in this item do not apply to *interface inheritance* - when a class implements an interface or where one
      interface extends another)
* **Unlike method invocation, inheritance violates encapsulation**
    - in other words, a subclass depends on the implementation details of its superclass for its proper function
    - the superclass's implementation may change from release to release, and if it does, the subclass may break, even
      though its code has not been touched

- as a consequence, a subclass must evolve in tandem with its superclass, unless the superclass's authors have designed
  and documented it specifically for the purpose of being extended

#### Example - Inappropriate use of inheritance!

```Java
// Broken - Inappropriate use of inheritance!
public class InstrumentedHashSet<E> extends HashSet<E> {
    // the number of attempted element insertions
    private int addCount = 0;

    public InstrumentedHashSet() {
    }

    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
```

* this class looks reasonable, but it doesn't work:

```Java
InstrumentedHashSet<String> s = new InstrumentedHashSet<String>();
s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
```

* we would expect the *getAddCount* method to return three at this point, but it return six - what went wrong?
    - internally, *HashSet*'s *addAll* method is implemented on top of its *add* method, althoud *HashSet*, quite
      reasonably, does not document this implementation detail

#### Discussion

* this issue could be fixed, but the class would depend for its proper function on the fact that *HashSet*'s *addAll*
  method is implemented on top of its *add* method
* this "self-use" is an implementation detail, not guaranteed to hold in all implementations of the Java platform and
  subject to change from release to release => fragile *InstrumentedHashSet* class
* a related cause of fragility in subclasses is that their superclass can acquire new mtehods in subsequent releases
* both aforementioned problems stem from overriding mehthods; you might think that it is safe to extend a class if you
  merely add new methods and refrain from overriding existing methods
    - this is much safer, but still not without risk
    - the superclass might add the same method with the same signature but with different return type => the subclass
      cannot compile anymore
    - if the return type is the same, you are overriding again

#### Composition

* Instead of extending an existing class, give your new class a private field that references an instance of the
  existing class
    * This is called **composition** because the existing class becomes a component of the new one
* Each instance method in the new class invokes the corresponding method on the contained instance of the existing class
  and returns the results
    * this is known as **forwarding**, and the methods in the new class are known as **forwarding methods**
* **The resulting class will be rock solid, with no dependencies on the implementation details of the existing class**
    - to make this concrete, here's a replacement for *InstrumentedHashSet* that uses the composition-and-forwarding
      approach
    - note that the implementation is broken into two pieces, the class itself and a reusable *forwarding class*, which
      contains all of the forwarding methods and nothing else:

```Java
// Wrapper class - uses composition in place of inheritance
public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0;

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
```

```Java
// Reusable forwarding class
public class ForwardingSet<E> implements Set<E> {

    private final Set<E> s;

    public ForwardingSet(Set<E> s) {
        this.s = s;
    }

    public void clear() {
        s.clear();
    }

    public boolean contains(Object o) {
        return s.contains(o);
    }

    public boolean isEmpty() {
        return s.isEmpty();
    }

    public int size() {
        return s.size();
    }

    public Iterator<E> iterator() {
        return s.iterator();
    }

    public boolean add(E e) {
        return s.add(e);
    }

    public boolean remove(Object o) {
        return s.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return s.containsAll(c);
    }

    public boolean addAll(Collection<? extends E> c) {
        return s.addAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return s.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return s.retainAll(c);
    }

    public Object[] toArray() {
        return s.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return s.toArray(a);
    }

    @Override
    public boolean equals(Object o) {
        return s.equals(o);
    }

    @Override
    public int hashCode() {
        return s.hashCode();
    }

    @Override public String toString() {
        return s.toString();
    }
}
```

* the design of the *InstrumentedSet* class is enabled by the existence of the *Set* interface, which captures the
  functionality of the *HashSet* class
* Unlike the inheritance-based approach, which works only for a single concrete class and requires a separate
  constructor for each supported constructor in the superclass, the wrapper class can be used to instrument any *Set*
  implementation and will work in conjunction with any preexisting constructor:

```Java
Set<Date> s=new InstrumentedSet<Date>(new TreeSet<Date>(cmp));
        Set<E> s2=new InstrumentedSet<Date>(new HashSet<E>(capacity));
```

* the *InstrumentedSet* class can even be used to temporarily instrument a set instance that has already been used
  without instrumentation:

```Java
static void walk(Set<Dog> dogs){
        InstrumentedSet<Dog> iDogs=new InstrumentedSet<Dog>(dogs);
        ... // Within this method use iDogs instead of dogs
        }
```

* The *InstrumentedSet* class is known as a *wrapper* class because each *InstrumentedSet* instance contains ("wraps")
  another *Set* instance
    - This is also known as the **Decorator pattern** [Gamma95, p. 175], because the *InstrumentedSet* class "decorates"
      a set by adding instrumentation
    - Sometimes the combination of composition and forwarding is loosely referred to as **delegation**, but **
      technically it's not delegation** unless the wrapper object passes itself to the wrapped
      object [Lieberman86; Gamma95, p.20]

#### Disadvantages of wrapper classes

* the disadvantages of wrapper classes are few
    - wrapper classes are not suited for use in *callback frameworks*, wherin objects pass self-references to other
      objects for subsequent invocations ("callbacks")
    - because a wrapped object doesn't know of its wrapper, it passes a reference to itself (*this*) and callbacks elude
      the wrapper - this is known as the **SELF problem** [Lieberman86]
    - some worry about the performance impact of forwarding method invocations or the memory footprint impact of wrapper
      objects => neither turn out to have much impact in practice

#### When inheritance is approppriate

* Inheritance is appropriate only in circumstances where the subclass really is *subtype* of the superclass
* **a class B should extend a class A only if an "is-a" relationship exists between the two classes**:
    - Is every B really an A?
        - If the answer is no, it is often the case that B should contain a private instance of A and expose a smaller
          and simpler API: **A is not an essential part of B, merely a detail of its implementation**
        - If you use inheritance where composition is appropriate, you needlessly expose implmentation details (the API
          is too open)
        - at the very least, this can lead to confusing semantics

    * Does the class that you contemplate extending have any flaws in its API? If so, are you comfortable propagating
      those flaws into your class's API?

#### Summary

* inheritance is powerful, but it is problematic because it violates encapsulation
* it is appropriate only when a genuine subtype relationship exists between the subclass and the superclass
* to avoid fragility, use composition and farwarding instead of inheritance, especially if an appropriate interface to
  implement a wrapper class exists

---

### Item 17: Design and document for inheritance or else prohibit it<a name="item17"></a>

Item 16 alerted you to the dangers of subclassing a "foreign" class that was not designed and documented for
inheritance. So what does it mean for a class to be designed and documented for inheritance?

* The class must document precisely the effects of overriding any method. That is, **the class must document its *
  self-use* of overridable methods** (by overridable, we mean nonfinal and either public or protected)
* More generally, a class must document any circumstances under which it might invoke an overridable method
    - For example, invocations might come from background threads or static initializers

By convention, a method that invokes overridable methods contains a description of these invocations at the end of its
documentation comment. The description begins with the phrase "This implementation." This phrase should not be taken to
indicate that the behavior may change from release to release. It connotes that the description concerns the inner
workings of the method. Here’s an example, copied from the specification for *java.util.AbstractCollection*:

```Java
public boolean remove(Object o)
```

> Removes a single instance of the specified element from this collection, if it is present (optional operation). More formally, removes an element *e* such that ´´´(o==null ? e==null : o.equals(e))´´´, if the collection contains one or more such elements. Returns *true* if the collection contained the specified element (or equivalently, if the collection changed as a result of the call).

> This implementation iterates over the collection looking for the specified element. If it finds the element, it removes the element from the collection using the iterator’s remove method. Note that this implementation throws an
*UnsupportedOperationException* if the *iterator* returned by this collection’s iterator method does not implement the *remove* method.

This documentation leaves no doubt that overriding the *iterator* method will affect the behavior of the *remove*
method. Furthermore, it describes exactly how the behavior of the *Iterator* returned by the iterator method will affect
the behavior of the *remove* method. Contrast this to the situation in Item 16, where the programmer subclassing *
HashSet* simply could not say whether overriding the *add* method would affect the behavior of the *addAll* method.

* **to document a class so that it can be safely subclassed, you must describe implementation details that should
  otherwise be left unspecified**
* To allow programmers to write efficient subclasses without undue pain, **a class may have to provide hooks into its
  internal workings in the form of judiciously chosen protected methods** or, in rare instances, protected fields
* A further documentation example of *java.util.AbstractList.removeRange(int fromIndex, int toIndex)* (see p. 88)
  illustrates this:
    - This method is of no interest to end users of a *List* implementation
    - It is provided solely to make it easy for subclasses to provide a fast *clear* method on sublists
    - In the absence of the *removeRange* method, subclasses would have to make do with quadratic performance when the *
      clear* method was invoked on sublists or rewrite the entire *subList* mechanism from scrath - not an easy task!

#### How to decide what protected members to expose when you design a class for inheritance?

* there is no magic bullet
    - the best you can do is to think hard, take your best guress, and then test it by writing subclasses
    - expose as few protected members as possible, as each one represents a commitmet to an implementation detail
    - on the other hand, you must not expose too few, as a missing protected member can render class practically
      unusabel for inheritance
* **The *only* way to test a class designed for inheritance is to write subclasses**
    - if you omit a crucial protected member, trying to write a subclass will make the omission painfully obvious
    - conversely, if several subclasses are written and none uses a protected member, you should probably make it
      private
    - **experience shows that three subclasses are usually sufficient to test an extendable class**
    - one or more of these subclasses should be written by someone other than the subclass author
* **you must test your class by writing subclasses *before* you release it**, as if the class achieves wide use it can
  get difficult to impossible to improve the performance of functionality of the class in a subsequent release
* **Constructors must not invoke overridable methods**, directly or indirectly
    - if you violate this rule, program failure will result
    - the superclass constructor runs before the subclass constructor, so the overriding method in the subclass will get
      invoked before the subclass constructor has run
    - if the overriding method depends on any initialization performed by the subclass constructor, the method will not
      behave as expected; example:

```Java
public class Super {
    // Broken - constructor invokes an overridable method
    public Super() {
        overrideMe();
    }
    public void overrideMe() {
    }
}
```

Hers's a subclass that overrides the *overrideMe* method which is erroneously invoked by *Super*'s sole constructor:

```Java
public final class Sub extends Super {
    private final Date date; //Blank final, set by constructor

    Sub() {
        date = new Date();
    }

    // Overriding method invoked by superclass constructor
    @Override
    public void overrideMe() {
        LOGGER.debug(date);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}
```

You might expect this program to print out the date twice, but it prints out *null* the first time, because the *
overrideMe* method is invoked by the *Super* constructor before the *Sub* constructor has a chance to initialize the *
date* field. Note that this program observes a final field in two different states! Note also that if *overrideMe* had
invoked any method on *date*, the invocation would have thrown a *NullPointerException* when the *Super* constructor
invoked *overrideMe*. The only reason this program doesn’t throw a *NullPointerException* as it stands is that the *
println* method has special provisions for dealing with a *null* argument.

* classes designed for inheritance should not implement the *Cloneable* and *Serializabe* interfaced, as they place a
  substantial burden on programmers who extend the class
    - but there are special actions that you can take to allow subclasses to implement these interfaces without
      mandating that they do so (see [Item 11](03_methods_common_to_all_objects.md#item11)
      and [Item 74](11_serialization.md#item74))
* if a class designed for inheritance implements *Cloneable* or *Serializable*, you should be aware that because the *
  clone* and *readObject* methods behave a lot like constructors, restrictions apply:
    - **neither *clone* nor *readObject* may invoke an overridable method, directly or indirectly**
    - In the case of the *readObject* method, the overriding method will run before the subclass’s state has been
      deserialized.
    - In the case of the *clone* method, the overriding method will run before the subclass’s *clone* method has a
      chance to fix the clone's state
    - in either case, a program failure is likely to follow
    - This can happen, for example, if the overriding method assumes it is modifying the clone's copy of the object's
      deep structure, but the copy hasn’t been made yet
* Finally, if you decide to implement *Serializable* in a class designed for inheritance and the class has a *
  readResolve* or *writeReplace* method, you must make the *readResolve* or *writeReplace* method *protected* rather
  than *private*.
    * If these methods are *private*, they will be silently ignored by subclasses.
    * This is one more case where an implementation detail becomes part of a class’s API to permit inheritance.

* **designing a class for inheritance places substantial limitations on the class** - This is not a decision to be
  undertaken lightly:
    * There are some situations where it is clearly the right thing to do, such as *abstract classes*, including *
      skeletal implementations* of interfaces ([Item 18](#item18)).
    * There are other situations where it is clearly the wrong thing to do, such as *immutable
      classes* ([Item 15](#item15)).

* **Prohibit subclassing in classes that are not designed and documented to be safely subclasses**, for instance via
    * making the class final or
    * by making all constructors private of package-private and to add public *static factories* in place of the
      constructors
    * this advice may be somewhat controversial as many programmers have grown accustomed to subclassing ordinary
      concrete classes to add facilities such as instrumentation, notification, and synchronization or to limit
      functionality
    * But the **wrapper class pattern**, described in Item 16, provides a superior alternative to inheritance for
      augmenting the functionality.

If a concrete class does not implement a standard interface, then you may inconvenience some programmers by prohibiting
inheritance. If you feel that you must allow inheritance from such a class, one reasonable approach is to ensure that
the class never invokes any of its overridable methods and to document this fact. **In other words, eliminate the
class's self-use of overridable methods entirely**. In doing so, you'll create a class that is reasonably safe to
subclass. Overriding a method will never affect the behavior of any other method.

You can eliminate a class's self-use of overridable methods mechanically, without changing its behavior. Move the body
of each overridable method to a private "helper method" and have each overridable method invoke its private helper
method. Then replace each self-use of an overridable method with a direct invocation of the overridable method's private
helper method.

---

### Item 18: Prefer interfaces to abstract classes<a name="item18"></a>

The Java programming language provides two machanisms for defining a type that permits multiple implementations:
interfaces and abstract classes.

* **Existing classes can be easily retrofitted to implement a new interface.**
    - for example, many existing classes were retrofitted to implement the *Comparable* interface when it was introduced
      into the platform
* existing classes cannot, in general, be retrofitted to extend a new abstract class
    - if you want to have two classes extend the same abstract class, you have to place the abstract class high up in
      the type hierarchy where it subclasses an ancestor of both classes
    - unfortunately, this causes great collateral damage to the type hierarchy, forcing all descendants of the common
      ancestor to extend the new abstract class whether or not it is appropriate for them to do so
* **Interfaces are ideal for defining mixins**
    - a *mixin* is a type that a class can implement in addition to its "primary type" to declare that it provides some
      optional behavior (i.e. the *Comparable* interface)
* **Interfaces allow the construction of nonhierarchical type frameworks**
    - type hierarchies are great for organizing some things, but other things don't fall neatly into a rigid hierarchy
    - for example, suppose we have an interface representing a singer and another representing a songwriter

```Java
public interface Singer {
    AudioClip sing(Song s);
}

public interface Songwriter {
    Song compose(boolean hit);
}
```

* In real life, some singers are also songwriters
    * because we used interfaces rather than abstract classes to define these types, it is perfectly permissible for a
      single class to implement bother *Singer* and *Songwriter*
    * in fact, we can define a third interface that extends both *Singer* and *Songwriter* and adds new methods that are
      appropriate to the combination:

```Java
public interface SingerSongwriter extends Singer, Songwriter {
    AudioClip strum();
    void actSensitive();
}
```

* this provides a big amount of flexibility
* the alternative is a bloated class hierarchy containing a separate class for every supported combination of attributes
    - if there are *n* attributes in the type system, there are 2^n possible combinations that you might have to support
      => *combinatorial explosion*
    - bloated class hierarchies can lead to bloated classes containing many methods that differ only in the type of
      their arguments, as there are no types in the class hierarchy to capture common behaviors
* **Interfaces enable safe, powerful functionality enhancements** via the *wrapper class idiom*, described
  in [Item 16](#item16)
    - if you use abstract classes to define types, you leave the programmer who wants to add functionality with no
      alternative but to use inheritance
    - the resulting classes are less powerful and more fragile than wrapper classes
* **You can combine the virtues of interfaces and abstract classes by providing an abstract *skeletal implementation*
  class to go with each nontrivial interface that you export.**
    - the interface still defines the type, but the skeletal implementation takes all of the work out of implementing
      it.
* By convention, skeletal implementations are called *AbstractInterface*, where *Interface* is the name of the interface
  they implement
    - for example, the Collections Framework provides a skeletal implementation to go along with each main collection
      interface: *AbstractCollection*, *AbstractSet*, *AbstractList*, and *AbstractMap*
    - when properly designed, skeletal implementations can make it *very* easy for programmers to provide their own
      implementations of your interfaces
    - for example, here's a static factory method containing a complete, fully functional *List* implementation:

```Java
// Concrete implementation built atop skeletal implementation
static List<Integer> intArrayAsList(final int[] a) {
    if(a == null) 
        throw new NullPointerException();

    return new AbstractList<Integer>() {
        public Integer get(int i) {
            return a[i]; // Autoboxing (Item 5)
        }

        @Override
        public Integer set(int i, Integer val) {
            int oldVal = a[i];
            a[i] = val;     // Auto-unbosing
            return oldVal;  // Autoboxing
        }

        public int size() {
            return a.length;
        }
    };
}
```

* This example illustrates the power of skeletal implementations
* it's also an **Adapter** [Gamma95, p. 139] that allows an *int* array to be viewed as a list of *Integer* instances
* note that a static factory is provided and that the class is an inaccessible **anonymous class** [Item 22](#item22)
  hidden inside the static factory.

* **The beauty of skeletal implementations is that they provide the implementation assistance of abstract classes
  without imposing the severe constraints that abstract classes impost when they serve as type definitions.**
    - for most implementors of an interface, extending the skeletal implementation is the **obvious choice**, but it is
      strictly **optional**
    - if a preexisting class cannot be made to extend the skeletal implementation, the class can always implement the
      interface manually
    - furthermore, , the skeletal implementation can still aid the implementor's task; the class implementing the
      interface can forward invocations of interface methods to a contained instance of a private inner class that
      extends the skeletal implementation
        + this technique, known as **simulated multiple inheritance**, is closely related to the wrapper class idiom
          discussed in [Item 16](#item16) - it provides most of the benefits of multiple inheritance, while avoiding the
          pifgalls

#### Write a skeletal implementation

* relatively simplt, but somewhat tedious process
* study hte interface and decide which methods are the primitives in terms of which the others can be implemented
    - these primitives will be the abstract methods in your skeletal implementation
    - then you must provide concrete implementations of all the other methods in the interface
    - example: skeletal implementation of the *Map.Entry* interface:

```Java
// Skeletal Implementation
public abstract class AbstractMapEntry<K,V> implements Map.Entry<K,V> {
    // Primitive operations
    public abstract K getKey();
    public abstract V getValue();

    // Entries in modifiable maps must override this method
    public V setValue(V value) {
        throw new UnsupportedOperationException
    }

    // Implements the general contract of Map.Entry.equals
    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Map.Entry))
            return false;

        Map.Entry<?,?> arg = (Map.Entry) o;
        return equals(getKey(), arg.getKey()) &&
                equals(getValue(), arg.getValue());
    }

    private static boolean equals(Object o1, Object o2) {
        return 01 == null ? o2 == null : o1.equals(o2);
    }

    // Implements the generals contract of Map.Entry.hashcode
    @Override 
    public int hashCode() {
        return hashCode(getKey()) ^ hashCode(geValue());
    }

    private static int hashCode(Object obj) {
        return obj == null ? 0 : obj.hashCode();
    }
}
```

* because skeletal implementations are designed for inheritance, you should follow all of the design and documentation
  guidelines in [Item 17](#item17)

* a minor variant on the skeletal implementation is the *simple implementation*, exemplified by *
  AbstractMap.SimpleEntry*
    - a simple implementation is like a inheritance, but it differs in that it isn't abstract: it is the simplest
      possible working implementation
    - you can ust it as it stands or subclass it as circumstances warrant

* Using abstract classes to define types that permit multiple implementations has one great advantag over using
  interfaces: **It is far easier to evolve an abstract class than an interface**
    - if, in a subsequent release, you want to add a new method to an abstract class, you can always add a concrete
      method containing a reasonable default implementation
    - all existing implementations of the abstract class will then provide the new method
    - this does not work for interfaces

* Public interfaces, therefore, must be designed carefully: **Once an interface is released and widely implemented, it
  is almost impossible to change.**
    - if an interface is severely deficient, it can doom an API
    - the best thing to do when releasing a new interface is to have as many programmers as possible implement the
      interface in as many ways as possible *before* the interface is frozen
    - this will allow you to discover flaws while you can still correct them

#### Summary

* An interface is generally the best way to define a type that permits multiple implementations.
* An exception to this rule is the case where ease of evolution is deemed more important than flexibility and power.
* Under these circumstances, you should use an abstract class to define the type, but only if you understand and can
  accept the limitations.
* If you export a nontrivial interface, you should strongly consider providing a skeletal implementation to go with it.
* Finally, you should design all of your public interfaces with the utmost care and test them thoroughly by writing
  multiple implementations.

---

### Item 19: Use interfaces only to define types<a name="item19"></a>

* an interface serves as a *type* that can be used to refer to instances of the class
* that a class implements an interface should therefore say something about what a client can do with instances of the
  class
* it is inappropriate to define an interface for any other purpose

#### Antipattern

* one kind of interface that fails this test is the so-called *constant interface*
    - such an interface contains no methods; it consists solely of static final fields, each exporting a constant
    - classes using these constants implement the interface to avoid the need to qualify constant names with a class
      name - example:

```Java
// Constant interface antipattern - do not use!
public interface PhysicalConstants {
    // Avogadro's number (1/mol)
    static final double AVOGADROS_NUMBER = 6.02214199e23;
    // Boltzmann constant (J/K)
    static final double BOLTZMANN_CONSTANT = 1.3806503e-23;
    // Mass of the electron (kg)
    static final double ELECTRON_MASS = 9.10938188e-31;
}
```

* **The constant interface pattern is a poor use of interfaces.**
    - constants are part of implementation details - in that way implementation details leak into the class's exportet
      API!
    - constant interfaces can be confusing and worse, it represents a commitment:
        + if in a future release the class is modified so that it no longer needs to use the constants, it still must
          implement the interface to ensure binary compatibility
    - if a nonfinal class implements a constant interface, all of its subclasses will have their namespaces polluted by
      the constants in the interface

#### How to provide constants

* if you want to export constants, there are several reasonable choices:
    - if the constants are strongly tied to an existing class or interface, you should add them to the class or
      interface
    - example: all boxed numerical primitive classes, such as *Integer* and *Double*, export *MIN_VALUE* and *MAX_VALUE*
      constants
    - if the constants are best viewed as members of an enumerated type, you should export them with an *enum
      type* ([Item 30](06_enums_and_annotations.md#item30))
    - otherwise, you should export the constants with a noninstantiable *utility
      class* ([Item 4](02_creating_and_destroying_objects.md#item4))
    - here is a utility class version of the *PhysicalConstants* example above:

```Java
// Constant utility class
package com.effectivejava.science;

public class PhysicalConstants {
    private PhysicalConstants() { } // Prevents instantiation

    public static final double AVOGADROS_NUMBER = 6.02214199e23;
    public static final double BOLTZMANN_CONSTANT = 1.3806503e-23;
    public static final double ELECTRON_MASS = 9.10938188e-31;
}
```

* Normally a utility class requires clients to qualify constant names with a class name, for example, *
  PhysicalConstants.AVOGADROS_NUMBER*.
* If you make heavy use of the constants exported by a utility class, you can avoid the need for qualifying the
  constants with the class name by making use of the **static import** facility, introduced in release 1.5:

```Java
// Use of static import to avoid qualifying constants
import static com.effectivejava.science.PhysicalConstants.*;

public class Test {
    double atoms(double mols) {
        return AVOGADROS_NUMBER * mols;
    }
    ...
    // Many more uses of PhysicalConstants justify static import
}
```

In summary, **interfaces should be used only to define types. They should not be used to export constants**

---

### Item 20: Prefer class hierarchies to tagged classes<a name="item20"></a>

You may run across a class whose instances come in two or more flavors and contain a *tag* field indicating the flavor
of the instance - for example, consider this class, which is capable of representing a circle or a reactangle:

```Java
// Tagged class - vastly inferior to a class hierarchy!
class Figure {
    enum Shape {RECTANGLE, CIRCLE}

    ;

    // Tag field - the shape of this figure
    final Shape shape;

    // These fields are used only if shape is RECTANGLE
    double length;
    double width;

    // this field is used only if shape is CIRCLE
    double radius;

    // Constructor for circle
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    // Constructor for rectangle
    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    double area() {
        switch (shape) {
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError();
        }
    }
}
```

#### Shortcomings of tagged classes

* **tagged classes are verbose, error-prone, and inefficient**:
    - cluttered with boilerplate, including enum declarations, tag fields, and switch statements
    - multiple implementations are jumbled together in a single class
    - memory footprint is increased because instances are burdened with irrelevant fields belonging to other flavors
    - fields can't be made final unless constructors initialize irrelevant fields, resulting in more boilerplate
    - constructors must set the tag field and initialize the right data fields with no help from the compiler: if you
      initialize the wrong fields, the program will fail at runtime
    - if you add a flavor, you must remember to add a case to every switch statement, or the class will fail at runtime
    - ...
* **a tagged class is just a pallid imitation of a class hierarchy**

#### Change tagged classes into a class hierarchy

* first, define an abstract class containing an abstract method for each method in the tagged class whose behaviour
  depends on the tag value
    - in the *Figure* class, there is only one such method, which is *area*
* similarly, if there are any **data fields used by all the flavors**, put them in this class
* next, define a concrete subclass of the root class for each flavor of the original tagged class
* include in each subclass the data fields particular to its flavor
* also include in each subclass the appropriate implementation of each abstract method in the root class
    - here is the class hierarchy corresponding to the original *Figure* class:

```Java
// Class hierarchy replacement for a tagged class
abstract class Figure {
    abstract double area();
}

class Circle extends Figure {
    final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double area() {
        return Math.PI * (radius * radius);
    }
}

class Rectangle extends Figure {
    final double length;
    final double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    double area() {
        return length * width;
    }
}
```

* this class hierarchy corrects every shortcoming of tagged classes noted previously (list of advantages see page 102)
* class hierarchies can be made to reflect natural hierarchical relationships among types, allowing for increased
  flexibility and better compile-time type checking - example:

```Java
class Square extends Rectangle {
    Square(double side) {
        super(side, side);
    }
}
```

* note that the fields in the above hierarchy are accessed directly rather than by accessor methods
    * this was done for brevity and would be unacceptable if the hierarchy were public ([Item 14](#item14))

---

### Item 21: Use function objects to represent strategies<a name="item21"></a>

* Java does not provide function pointers, but object references can be used to achieve a similar effect
* An instance of a class that exports exactly one such method is effectivley a pointer to that method, so called **
  function objects**

```Java
class StringLengthComparator {
public int compare(String s1, String s2) {
return s1.length() - s2.length();
}
}
```

* An instance of the *StringLengthComparator* class is a *concrete strategy* for string comparison
* As is typical for concrete strategy classes, the *StringLengthComparator* class is **stateless**: **it has no fields,
  hence all instances of the class are functionally equivalent

- Thus it should be a singleton to save on unnecessary object creation
  costs ([Item 3](02_creating_and_destroying_objects.md#item3), [Item 5](02_creating_and_destroying_objects.md#item5))

```Java
class StringLengthComparator {

private StringLengthComparator() { }

public static final StringLengthComparator 
INSTANCE = new StringLengthComparator();

public int compare(String s1, String s2) {
return s1.length() - s2.length();
}
}
```

* To pass a *StringLengthCompmarator* instance to a method, we need an appropriate type for the parameter

- We need to define a *Comparator* interface and modify *StringLengthCompmarator* to implement the interface **strategy
  interface**

```Java
// Strategy interface
public interface Comparator<T> {
public int compare(T t1, T t2);
}
```

* The *Comparator* interface is *generic* ([Item 26](05_generics.md#item26)) so that it is applicable to comparators for
  objects other than strings

- Its compare method takes two parameters of type T (its **formal type parameter**) rather than *String*
- The *StringLengthComparator* class shown above can be made to implement *Comparator<String>* merely by declaring it to
  do so:

```Java
class StringLengthComparator implements Comparator<String> {
... // class body is identical to the one shown above
}
```

* Concrete strategy classes are often declared using anonymous classes ([Item 22](#item22)):

```Java
Arrays.sort(stringArray, new Comparator<String>() {
public int compare(String s1, String s2) {
return s1.length() - s2.length();
}
});
```

* **Note:** using an anonymous class this way will create a new instance each time the call is executed

- in these cases consider *storing the function object in a private static final field*!

* The strategy interface serves as a type for all of its concrete strategy instances - a concrete strategy class needn't
  be made public to export a concrete strategy.
    - Instead, a "host class" can export a public static field (or static factory method) whose type is the strategy
      interface, and the concrete strategy class can be a private nested class of the host.
    - In the example that follows, a static member class is used in preference to an anonymous class to allow the
      concrete strategy class to implement a second interface, *Serializable*.
    - The *String* class uses this pattern to export a case-independent string comparator via its CASE_INSENSITIVE_ORDER
      field.

```Java
// Exporting a concrete strategy
class Host {
    private static class StrLenCmp
        implements Comparator<String>, Serializable {
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
    }

    // Returned comparator is seializable
    public static final Comparator<String>
        STRING_LENGTH_COMPARATOR = new StrLenCmp();

    ... // Bulk of class omitted
}
```

#### Summary

* To summarize, a primary use of function pointers is to implement the Strategy pattern.
* To implement this pattern in Java, declare an interface to represent the strategy, and a class that implements this
  interface for each concrete strategy.
* When a concrete strategy is used only once, it is typically declared and instantiated as an anonymous class.
* When a concrete strategy is designed for repeated use, it is generally implemented as a private static member class
  and exported in a public static final field whose type is the strategy interface.

---

### Item 22: Favor static member classes over nonstatic<a name="item22"></a>

* a *nested class* is a class defined within another class and should exist only to serve its enclosing class - if it is
  useful in another context, the nested class should be a top-level class
* there are four kinds of nested classes:
    - *static member classes* (no inner class)
    - *nonstatic member classes* (inner class)
    - *anonymous classes* (inner class)
    - *local classes* (inner class)
* **When tou use which kind of nested class?**

#### Static Member Class

* best thought of as an ordinary class that happens to be declared inside another class and has access to all of the
  enclosing class's members, even those declared private
* a static member class is a static member of its enclosing class and obeys the same accessibility rules as other static
  members
* if it is declared private, it is accessible only within the enclosing class, and so forth
* **Usage:**
    - serves as a public helper class, useful only in conjunction with its outer class
    - example: consider an enum describing the operations supported by a
      calculator ([Item 30](06_enums_and_annotations.md#item30))
        - the *Operation* enum should be a public static member class of the *Calculator* class
        - clients of *Calculator* could then refer to operations using names like *Calculator.Operations.PLUS* and *
          Calculator.Operation.MINUS*

#### Nonstatic Member Class

* Syntactically, the only difference to static member classes is the missing *static* modifier, but in fact, they are
  very different
* each instance of a nonstatic member class is implicitly associated with an *enclosing instance* of its containing
  class
* within instance methods of a nonstatic member class, you can invoke methods on the enclosing instance or obtain a
  reference to the enclosing instance using the *qualified this*
  construct ([JLS, 15.8.4](https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.8.4))
* if an instance of a nested class can exist in isolation from an instance of its enclosing class, then the nested
  class *must* be a static member class: it is impossible to create an instance of a nonstatic member class without an
  enclosing instance
    - the association between a nonstatic member class instance and its enclosing instance is established when the
      former is created; it cannot be modified therafter
* normally, the association is established automatically by invoking a nonstatic member class constructor from within an
  instance method of the enclosing class
* it is possible, although rare, to establish the association manually using the expression *enclosingInstance.new
  MemberClass(args)*
* the association takes up space in the nonstatic member class instance and adds time to its construction

* one common use of a nonstatic member class is to define an *Adapter* [Gamma95, p.139] that allows an instance of the
  outer class to be viewed as an instance of some unrelated class
* example: implementations of the *Map* interface typically use nonstatic member classes to implement ther *collection
  views*, which are returned by Map's *keySet*, *entrySet*, ans *values* methods
    - similarly, implementations of the collection interfaces, such as *Set* and *List*, typically use nonstatic member
      classes to implement their iterators:

```Java
// Typical use of a nonstatic member class
public class MySet<E> extends AbstractSet<E> {
    // ... Bulk of the class omitted

    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        // ...
    }
}
```

**If you declare a member class that does not require access to an enclosing instance, *always* put the *static*
modifier in its declaration, making it a static rather than a nonstatic member class to save in this case unnessary and
possibly dangerous references** ([Item 6](#2_creating_and_destroying_objects.md#item6))

* a common **use of private statice member classes** is to represent components of the object represented by their
  enclosing class
    - example: consider a *Map* instance, which associates keys with values (further explanations see book)

#### Anonymous Classes

* has no name and is a member of its enclosing class
* rather than being declared along with other members, it is simultaneously declared and instantiated at the point of
  use
* anonymous classes have enclosing instances if and only if they occur in a nonstatic context
* there are many limitations on the applicability of anonymous classes:
    * you can't instantiate them except at the point they're declared
    * you can't perform *instanceof* tests or do anything else that requires you to name the class
    * you can't declare an anonymous class to implement multiple interfaces, or to extend a class and implement an
      interface at the same time
    * clients of an anonymous class can't invoke any members except those it inherits from its supertype
    * because anonymous classes occur in the midst of expressions, they must be kept short - about ten lines or fewer -
      or readability will suffer
* **usage:**
    * creation of **function objects** ([Item 21](#item21)) on the fly
    * creation of **process objects** such as *Runnable*, *Thread*, or *TimerTask* instances
    * within static factory methods (see the *intArrayAsList* method in [Item 18](#item18))

#### Local Classes

* least frequently used type of these four nested classes types
* a local class can be declared anywhere a local variable can be declared and obeys the same scoping rules
* like member classes, they have names and can be used repeatedly
* like anonymous classes, they have enclosing instances only if they are defined in a nonstatic context, and they cannot
  contain static members
* like anonymous classes, they should be kept short so as not to harm readability

#### Summary

* if a nested class needs to be visible outside of a single method or is too long to fit comfortably inside a method,
  use a member class
* if each instance of the member class needs a reference to its enclosing instnace, makte it nonstatice; otherwise, make
  it static
* assuming the class belongs inside a method, if you need to create instances from only on location and there is a
  preexisting type that characterizes the class, make it an anonymous class; otherwise, make it a local class
