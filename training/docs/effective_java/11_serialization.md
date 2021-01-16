# Effective Java

## Serialization

* this chapter concerns the *object serialization* API, which provides a framework for encoding objects as byte streams
  and reconstructing objects from their byte-stream encodings
    - encoding an object as a byte stream is known as *serializing* the object; the reverse process is known as *
      deserializing* it
    - once an object has been serialized, its encoding can be transmitted from one running virtual machine to another or
      stored on disk for later deserialization
    - serialization provides the standard wire-level object representation for remote communication, and the standard
      persistent data format for the JavaBeans component architecture
    - a notable feature of this chapter is the *serialization proxy* pattern ([Item 78](#item78)), which can help you
      avoid many of the pitfalls of object serialization.

---

### Item 74: Implement Serializable judiciously<a name="item74"></a>

* allowing a class's instances to be serialized can be as simple as adding the words "*implements Serializable*" to its
  declaration
    - because this is so easy to do, there is a common misconception that serialization requires little effort on the
      part of the programmer
    - the truth is far more complex
    - while the immediate cost to make a class serializable can be negligible, the long-term costs are often substantial
* **a major cost of implementing *Serializable* is that it decreases the flexibility to change a class's implementation
  once it has been released**
    - when a class implements *Serializable*, its byte-stream encoding (or serialized form) becomes part of its exported
      API
    - once you distribute a class widely, you are generally required to support the serialized form forever, just as you
      are required to support all other parts of the exported API
    - if you do not make the effort to design a *custom serialized form*, but merely accept the default, the serialized
      form will forever be tied to the class's original internal representation
    - in other words, if you accept the default serialized form, the class's private and package-private instance fields
      become part of its exported API, and the practice of minimizing access to
      fields ([Item 13](04_classes_and_interfaces.md#item13)) loses its effectiveness as a tool for information hiding
* if you accept the default serialized form and later change the class's internal representation, an incompatible change
  in the serialized form might result
    - clients attempting to serialize an instance using an old version of the class and deserialize it using the new
      version will experience program failures
    - it is possible to change the internal representation while maintaining the original serialized form (using *
      ObjectOutputStream.putFields* and *ObjectInputStream.readFields*), but it can be difficult and leaves visible
      warts in the source code
    - therefore, you should carefully design a high-quality serialized form that you are willing to live with for the
      long haul ([Items 75](#item75), [78](#item78))
    - doing so will add to the initial cost of development, but it is worth the effort
    - even a well-designed serialized form places constraints on the evolution of a class; an ill-designed serialized
      form can be crippling
* a simple example of the constraints on evolution that accompany serializability concerns *stream unique identifiers*,
  more commonly known as **serial version UIDs**
    - every serializable class has a unique identification number associated with it
    - if you do not specify this number explicitly by declaring a *static final long* field named *serialVersionUID*,
      the system automatically generates it at runtime by applying a complex procedure to the class
    - the automatically generated value is affected by the class's name, the names of the interfaces it implements, and
      all of its public and protected members
    - if you change any of these things in any way, for example, by adding a trivial convenience method, the
      automatically generated serial version UID changes
    - if you fail to declare an explicit serial version UID, compatibility will be broken, resulting in an *
      InvalidClassException* at runtime
* **a second cost of implementing *Serializable* is that it increases the likelihood of bugs and security holes**
    - normally, objects are created using constructors; serialization is an *extralinguistic mechanism* for creating
      objects
    - whether you accept the default behavior or override it, deserialization is a "*hidden constructor*" with all of
      the same issues as other constructors
    - because there is no explicit constructor associated with deserialization, it is easy to forget that you must
      ensure that it guarantees all of the invariants established by the constructors and that it does not allow an
      attacker to gain access to the internals of the object under construction
    - relying on the default deserialization mechanism can easily leave objects open to invariant corruption and illegal
      access ([Item 76](#item76)).
* **a third cost of implementing *Serializable* is that it increases the testing burden associated with releasing a new
  version of a class**
    - when a serializable class is revised, it is important to check that it is possible to serialize an instance in the
      new release and deserialize it in old releases, and vice versa
    - the amount of testing required is thus proportional to the product of the number of serializable classes and the
      number of releases, which can be large
    - these tests cannot be constructed automatically because, in addition to *binary compatibility*, you must test
      for *semantic compatibility*
    - in other words, you must ensure both that the serialization-deserialization process succeeds and that it results
      in a faithful replica of the original object
    - the greater the change to a serializable class, the greater the need for testing
    - the need is reduced if a custom serialized form is carefully designed when the class is first
      written ([Items 75](#item75), [78](#item78)), but it does not vanish entirely
* **implementing the *Serializable* interface is not a decision to be undertaken lightly**
    - it offers real benefits
    - it is essential if a class is to participate in a framework that relies on serialization for object transmission
      or persistence
    - also, it greatly eases the use of a class as a component in another class that must implement *Serializable*
    - there are, however, many real costs associated with implementing *Serializable*
    - each time you design a class, weigh the costs against the benefits
    - as a rule of thumb, value classes such as *Date* and *BigInteger* should implement *Serializable*, as should most
      collection classes
    - classes representing active entities, such as thread pools, should rarely implement *Serializable*
* **classes designed for inheritance ([Item 17](04_classes_and_interfaces.md#item18)) should rarely implement *
  Serializable*, and interfaces should rarely extend it**
    - violating this rule places a significant burden on anyone who extends the class or implements the interface
    - there are times when it is appropriate to violate the rule
    - for example, if a class or interface exists primarily to participate in a framework that requires all participants
      to implement *Serializable*, then it makes perfect sense for the class or interface to implement or extend *
      Serializable*
* classes designed for inheritance that *do* implement *Serializable* include *Throwable*, *Component*, and *
  HttpServlet*
    - *Throwable* implements *Serializable* so exceptions from remote method invocation (*RMI*) can be passed from
      server to client
    - *Component* implements *Serializable* so GUIs can be sent, saved, and restored
    - *HttpServlet* implements *Serializable* so session state can be cached
* if you implement a class with instance fields that is serializable and extendable, there is a caution you should be
  aware of
    - if the class has invariants that would be violated if its instance fields were initialized to their default
      values (zero for integral types, *false* for *boolean*, and *null* for object reference types), you must add
      this *readObjectNoData* method to the class:

```Java
class SomeClass implements Serializable {
    // ...
    
    // readObjectNoData for stateful extendable serializable classes
    private void readObjectNoData() throws InvalidObjectException {
        throw new InvalidObjectException("Stream data required");
    }
}
```

* in case you're curious, the *readObjectNoData* method was added in release 1.4 to cover a corner case involving the
  addition of a serializable superclass to an existing serializable class
    - details can be found in the serialization specification [Serialization, 3.5]
* there is one caveat regarding the decision *not* to implement *Serializable*
    - if a class that is designed for inheritance is not serializable, it may be impossible to write a serializable
      subclass
    - specifically, it will be impossible if the superclass does not provide an accessible parameterless constructor
    - therefore, **you should consider providing a parameterless constructor on nonserializable classes designed for
      inheritance**
    - often this requires no effort because many classes designed for inheritance have no state, but this is not always
      the case
* it is best to create objects with their invariants already
  established ([Item 15](04_classes_and_interfaces.md#item15))
    - if client-provided data is required to establish these invariants, this excludes the use of a parameterless
      constructor
    - naively adding a parameterless constructor and a separate initialization method to a class whose remaining
      constructors establish its invariants would complicate the state space, increasing the likelihood of error
* here is a way to add a parameterless constructor to a nonserializable extendable class that avoids these deficiencies
    - suppose the class has one constructor:

```
public AbstractFoo(int x, int y) { ... }
```

* the following transformation adds a protected parameterless constructor and an initialization method
    - the initialization method has the same parameters as the normal constructor and establishes the same invariants
    - note that the variables that store the object's state (*x* and *y*) can't be final, as they are set by the *
      initialize* method:

```Java
// Nonserializable stateful class allowing serializable subclass
public abstract class AbstractFoo {

    private int x, y; // Our state

    // This enum and field are used to track initialization
    private enum State { NEW, INITIALIZING, INITIALIZED };
    private final AtomicReference<State> init = new AtomicReference<State>(State.NEW);

    public AbstractFoo(int x, int y) { initialize(x, y); }

    // This constructor and the following method allow
    // subclass's readObject method to initialize our state.
    protected AbstractFoo() { }

    protected final void initialize(int x, int y) {
        if (!init.compareAndSet(State.NEW, State.INITIALIZING))
            throw new IllegalStateException("Already initialized");

        this.x = x;
        this.y = y;
        // ... Do anything else the original constructor did
        init.set(State.INITIALIZED);
    }

    // These methods provide access to internal state so it can
    // be manually serialized by subclass's writeObject method.
    protected final int getX() { checkInit(); return x; }
    protected final int getY() { checkInit(); return y; }

    // Must call from all public and protected instance methods
    private void checkInit() {
        if (init.get() != State.INITIALIZED)
            throw new IllegalStateException("Uninitialized");
    }
    // ... Remainder omitted
}
```

* all public and protected instance methods in *AbstractFoo* must invoke *checkInit* before doing anything else
    - this ensures that method invocations fail quickly and cleanly if a poorly written subclass fails to initialize an
      instance
    - note that the *initialized* field is an *atomic reference* (*java.util.concurrent.atomic.AtomicReference*)
    - this is necessary to ensure object integrity in the face of a determined adversary
    - in the absence of this precaution, if one thread were to invoke initialize on an instance while a second thread
      attempted to use it, the second thread might see the instance in an inconsistent state
    - with this mechanism in place, it is reasonably straightforward to implement a serializable subclass:

```Java
// Serializable subclass of nonserializable stateful class
public class Foo extends AbstractFoo implements Serializable {

    private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        // Manually deserialize and initialize superclass state
        int x = s.readInt();
        int y = s.readInt();
        initialize(x, y);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {

        s.defaultWriteObject();
        // Manually serialize superclass state
        s.writeInt(getX());
        s.writeInt(getY());
    }

    // Constructor does not use the fancy mechanism
    public Foo(int x, int y) { super(x, y); }
    private static final long serialVersionUID = 1856835860954L;
}
```

* ***inner classes* ([Item 22](04_classes_and_interfaces.md#item22)) should not implement *Serializable*
    - they use *compiler-generated synthetic fields* to store references to *enclosing instances* and to store values of
      local variables from enclosing scopes
    - how these fields correspond to the class definition is unspecified, as are the names of anonymous and local
      classes
    - therefore, **the default serialized form of an inner class is illdefined**
    - a *static member class* can, however, implement *Serializable*
* to summarize, the ease of implementing *Serializable* is specious
    - unless a class is to be thrown away after a short period of use, implementing *Serializable* is a serious
      commitment that should be made with care
    - extra caution is warranted if a class is designed for inheritance
    - for such classes, an intermediate design point between implementing *Serializable* and prohibiting it in
      subclasses is to provide an accessible parameterless constructor
    - this design point permits, but does not require, subclasses to implement *Serializable*

---

### Item 75: Consider using a custom serialized form<a name="item75"></a>

* When you are producing a class under time pressure, it is generally appropriate to concentrate your efforts on
  designing the best API
    - Sometimes this means releasing a "throwaway" implementation that you know you'll replace in a future release
    - normally this is not a problem, but if the class implements *Serializable* and uses the default serialized form,
      you'll never be able to escape completely from the throwaway implementation
    - it will dictate the serialized form forever
    - this is not just a theoretical problem
    - it happened to several classes in the Java platform libraries, *including BigInteger*
* **do not accept the default serialized form without first considering whether it is appropriate**
    - accepting the default serialized form should be a conscious decision that this encoding is reasonable from the
      standpoint of flexibility, performance, and correctness
    - generally speaking, you should accept the default serialized form only if it is largely identical to the encoding
      that you would choose if you were designing a custom serialized form
* the default serialized form of an object is a reasonably efficient encoding of the physical representation of the
  object graph rooted at the object
    - in other words, it describes the data contained in the object and in every object that is reachable from this
      object
    - it also describes the topology by which all of these objects are interlinked
    - the ideal serialized form of an object contains only the logical data represented by the object
    - it is independent of the physical representation
* **The default serialized form is likely to be appropriate if an object's physical representation is identical to its
  logical content**
    - for example, the default serialized form would be reasonable for the following class, which simplistically
      represents a person's name:

```Java
// Good candidate for default serialized form
public class Name implements Serializable {
    
    /**
    * Last name. Must be non-null.
    * @serial
    */
    private final String lastName;
    
    /**
    * First name. Must be non-null.
    * @serial
    */
    private final String firstName;
    
    /**
    * Middle name, or null if there is none.
    * @serial
    */
    private final String middleName;
    
    // ... Remainder omitted
}
```

* logically speaking, a name consists of three strings that represent a last name, a first name, and a middle name
    - the instance fields in *Name* precisely mirror this logical content
* **even if you decide that the default serialized form is appropriate, you often must provide a *readObject* method to
  ensure invariants and security**
    - in the case of *Name*, the *readObject* method must ensure that *lastName* and *firstName* are non-null
    - this issue is discussed at length in [Items 76](#item76) and [78](#item78)
* Note that there are documentation comments on the *lastName*, *firstName*, and *middleName* fields, even though they
  are *private*
    - that is because these private fields define a public API, which is the serialized form of the class, and this
      public API must be documented
    - the presence of the *@serial* tag tells the Javadoc utility to place this documentation on a special page that
      documents serialized forms
* Near the opposite end of the spectrum from *Name*, consider the following class, which represents a list of strings (
  ignoring for the moment that you’d be better off using one of the standard *List* implementations):

```Java
// Awful candidate for default serialized form
public final class StringList implements Serializable {
    
    private int size = 0;
    private Entry head = null;
    
    private static class Entry implements Serializable {
        String data;
        Entry next;
        Entry previous;
    }
    
    // ... Remainder omitted
}
```

* logically speaking, this class represents a sequence of strings
    - physically, it represents the sequence as a doubly linked list
    - if you accept the default serialized form, the serialized form will painstakingly mirror every entry in the linked
      list and all the links between the entries, in both directions
* **Using the default serialized form when an object's physical representation differs substantially from its logical
  data content has four disadvantages:**
    1. **It permanently ties the exported API to the current internal representation.**
        + In the above example, the private *StringList.Entry* class becomes part of the public API.
        + If the representation is changed in a future release, the *StringList* class will still need to accept the
          linked list representation on input and generate it on output.
        + The class will never be rid of all the code dealing with linked list entries, even if it doesn't use them
          anymore.
    2. **It can consume excessive space.**
        + In the above example, the serialized form unnecessarily represents each entry in the linked list and all the
          links.
        + These entries and links are mere implementation details, not worthy of inclusion in the serialized form.
        + Because the serialized form is excessively large, writing it to disk or sending it across the network will be
          excessively slow.
    3. **It can consume excessive time.**
        + The serialization logic has no knowledge of the topology of the object graph, so it must go through an
          expensive graph traversal.
        + In the example above, it would be sufficient simply to follow the next references.
    4. **It can cause stack overflows.**
        + The default serialization procedure performs a recursive traversal of the object graph, which can cause stack
          overflows even for moderately sized object graphs.
        + Serializing a *StringList* instance with 1.258 elements causes the stack to overflow on my machine.
        + The number of elements required to cause this problem may vary depending on the JVM implementation and command
          line flags; some implementations may not have this problem at all.
* a reasonable serialized form for *StringList* is simply the number of strings in the list, followed by the strings
  themselves
    - this constitutes the logical data represented by a *StringList*, stripped of the details of its physical
      representation
    - here is a revised version of *StringList* containing *writeObject* and *readObject* methods implementing this
      serialized form
    - as a reminder, the transient modifier indicates that an instance field is to be omitted from a class’s default
      serialized form:

```Java
// StringList with a reasonable custom serialized form
public final class StringList implements Serializable {
    
    private transient int size = 0;
    private transient Entry head = null;
    
    // No longer Serializable!
    private static class Entry {
        String data;
        Entry next;
        Entry previous;
    }
    
    // Appends the specified string to the list
    public final void add(String s) { 
        // ... 
    }
    
    /**
    * Serialize this {@code StringList} instance.
    *
    * @serialData The size of the list (the number of strings
    * it contains) is emitted ({@code int}), followed by all of
    * its elements (each a {@code String}), in the proper
    * sequence.
    */
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(size);
        // Write out all elements in the proper order.
        for (Entry e = head; e != null; e = e.next)
            s.writeObject(e.data);
    }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        int numElements = s.readInt();
        // Read in all elements and insert them in list
        for (int i = 0; i < numElements; i++)
            add((String) s.readObject());
    }
    
    // ... Remainder omitted
}
```

* note that the first thing *writeObject* does is to invoke *defaultWriteObject*, and the first thing *readObject* does
  is to invoke *defaultReadObject*, even though all of *StringList*'s fields are transient
    - **if all instance fields are transient, it is technically permissible to dispense with invoking *
      defaultWriteObject* and *defaultReadObject*, but it is not recommended**
    - even if all instance fields are transient, invoking *defaultWriteObject* affects the serialized form, resulting in
      greatly enhanced flexibility
    - the resulting serialized form makes it possible to add nontransient instance fields in a later release while
      preserving backward and forward compatibility
    - if an instance is serialized in a later version and deserialized in an earlier version, the added fields will be
      ignored
    - had the earlier version's *readObject* method failed to invoke *defaultReadObject*, the deserialization would fail
      with a *StreamCorruptedException*
* note that there is a documentation comment on the *writeObject* method, even though it is private
    - this is analogous to the documentation comment on the private fields in the *Name* class
    - this private method defines a public API, which is the serialized form, and that public API should be documented
    - like the '@serial' tag for fields, the *@serialData* tag for methods tells the Javadoc utility to place this
      documentation on the serialized forms page
* to lend some sense of scale to the earlier performance discussion, if the average string length is ten characters, the
  serialized form of the revised version of *StringList* occupies about half as much space as the serialized form of the
  original
    - on my machine, serializing the revised version of *StringList* is over twice as fast as serializing the original
      version, again with a string length of ten
    - finally, there is no stack overflow problem in the revised form, hence no practical upper limit to the size of a *
      StringList* that can be serialized
* while the default serialized form would be bad for *StringList*, there are classes for which it would be far worse
    - for *StringList*, the default serialized form is inflexible and performs badly, but it is correct in the sense
      that serializing and deserializing a *StringList* instance yields a faithful copy of the original object with all
      of its invariants intact
    - this is not the case for any object whose invariants are tied to implementation-specific details
* for example, consider the case of a hash table
    - the physical representation is a sequence of hash buckets containing key-value entries
    - the bucket that an entry resides in is a function of the hash code of its key, which is not, in general,
      guaranteed to be the same from JVM implementation to JVM implementation
    - in fact, it isn't even guaranteed to be the same from run to run
    - therefore, accepting the default serialized form for a hash table would constitute a serious bug
    - serializing and deserializing the hash table could yield an object whose invariants were seriously corrupt
* whether or not you use the default serialized form, every instance field that is not labeled transient will be
  serialized when the *defaultWriteObject* method is invoked
    - therefore, every instance field that can be made transient should be made so
    - this includes redundant fields, whose values can be computed from "primary data fields," such as a cached hash
      value
    - it also includes fields whose values are tied to one particular run of the JVM, such as a *long* field
      representing a pointer to a native data structure
    - **before deciding to make a field nontransient, convince yourself that its value is part of the logical state of
      the object**
    - if you use a custom serialized form, most or all of the instance fields should be labeled transient, as in the *
      StringList* example shown above
* if you are using the default serialized form and you have labeled one or more fields transient, remember that these
  fields will be initialized to their default values when an instance is deserialized: *null* for object reference
  fields, zero for numeric primitive fields, and *false* for *boolean* fields [JLS, 4.12.5]
    - if these values are unacceptable for any transient fields, you must provide a *readObject* method that invokes
      the *defaultReadObject* method and then restores transient fields to acceptable values ([Item 76](#item76))
    - alternatively, these fields can be lazily initialized the first time they are
      used ([Item 71](10_concurrency.md#item71))
* whether or not you use the default serialized form, **you must impose any synchronization on object serialization that
  you would impose on any other method that reads the entire state of the object**
    - so, for example, if you have a thread-safe object ([Item 70](10_concurrency.md#item70)) that achieves its thread
      safety by synchronizing every method, and you elect to use the default serialized form, use the following *
      writeObject* method:

```
// writeObject for synchronized class with default serialized form
private synchronized void writeObject(ObjectOutputStream s)
    throws IOException {
    s.defaultWriteObject();
}
```

* if you put synchronization in the *writeObject* method, you must ensure that it adheres to the same lock-ordering
  constraints as other activity, or you risk a resource-ordering deadlock [Goetz06, 10.1.5].
* **regardless of what serialized form you choose, declare an explicit *serial version UID* in every serializable class
  you write**
    - this eliminates the *serial version UID* as a potential source of incompatibility ([Item 74](#item74))
    - there is also a small performance benefit
    - if no *serial version UID* is provided, an expensive computation is required to generate one at runtime
* declaring a serial version UID is simple. Just add this line to your class:

```
private static final long serialVersionUID = randomLongValue ;
```

* if you write a new class, it doesn't matter what value you choose for *randomLongValue*
    - you can generate the value by running the *serialver* utility on the class, but it's also fine to pick a number
      out of thin air
    - if you modify an existing class that lacks a *serial version UID*, and you want the new version to accept existing
      serialized instances, you must use the value that was automatically generated for the old version
    - you can get this number by running the *serialver* utility on the old version of the class — the one for which
      serialized instances exist.
* If you ever want to make a new version of a class that is incompatible with existing versions, merely change the value
  in the *serial version UID* declaration
    - this will cause attempts to deserialize serialized instances of previous versions to fail with an *
      InvalidClassException*
* To summarize, when you have decided that a class should be serializable ([Item 74](#item74)), think hard about what
  the serialized form should be
    - use the default serialized form only if it is a reasonable description of the logical state of the object
        + otherwise design a custom serialized form that aptly describes the object
    - you should allocate as much time to designing the serialized form of a class as you allocate to designing its
      exported methods ([Item 40](07_methods.md#item40))
    - just as you cannot eliminate exported methods from future versions, you cannot eliminate fields from the
      serialized form
        + they must be preserved forever to ensure serialization compatibility
    - choosing the wrong serialized form can have a permanent, negative impact on the complexity and performance of a
      class

---

### Item 76: Write readObject methods defensively<a name="item76"></a>

* [Item 39](07_methods.md#item39) contains an immutable date-range class containing mutable *private Date* fields
    - the class goes to great lengths to preserve its invariants and its immutability by defensively copying *Date*
      objects in its constructor and accessors
    - here is the class:

```Java
// Immutable class that uses defensive copying
public final class Period {

    private final Date start;
    private final Date end;

    /**
     * @param start the beginning of the period
     * @param end the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException if start or end is null
     */
    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (this.start.compareTo(this.end) > 0)
            throw new IllegalArgumentException(start + " after " + end);
    }

    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }

    public String toString() {
        return start + " - " + end;
    }
    // ... Remainder omitted
}
```

* suppose you decide that you want this class to be serializable
    - because the physical representation of a *Period* object exactly mirrors its logical data content, it is not
      unreasonable to use the default serialized form ([Item 75](#item76))
    - terefore, it might seem that all you have to do to make the class serializable is to add the words "*implements
      Serializable*" to the class declaration
    - if you did so, however, the class would no longer guarantee its critical invariants
* The problem is that the *readObject* method is effectively another public constructor, and it demands all of the same
  care as any other constructor
    - just as a constructor must check its arguments for validity ([Item 38](07_methods.md#item38)) and make defensive
      copies of parameters where appropriate ([Item 39](07_methods.md#item39)), so must a *readObject* method
    - if a *readObject* method fails to do either of these things, it is a relatively simple matter for an attacker to
      violate the class's invariants
* Loosely speaking, *readObject* is a constructor that takes a byte stream as its sole parameter
    - in normal use, the byte stream is generated by serializing a normally constructed instance
    - the problem arises when *readObject* is presented with a byte stream that is artificially constructed to generate
      an object that violates the invariants of its class
    - assume that we simply added *implements Serializable* to the class declaration for *Period*
    - this ugly program would then generate a *Period* instance whose end precedes its start:

```Java
public class BogusPeriod {

    // Byte stream could not have come from real Period instance!
    private static final byte[] serializedForm = new byte[]{
            (byte) 0xac, (byte) 0xed, 0x00, 0x05, 0x73, 0x72, 0x00, 0x06,
            0x50, 0x65, 0x72, 0x69, 0x6f, 0x64, 0x40, 0x7e, (byte) 0xf8,
            0x2b, 0x4f, 0x46, (byte) 0xc0, (byte) 0xf4, 0x02, 0x00, 0x02,
            0x4c, 0x00, 0x03, 0x65, 0x6e, 0x64, 0x74, 0x00, 0x10, 0x4c,
            0x6a, 0x61, 0x76, 0x61, 0x2f, 0x75, 0x74, 0x69, 0x6c, 0x2f,
            0x44, 0x61, 0x74, 0x65, 0x3b, 0x4c, 0x00, 0x05, 0x73, 0x74,
            0x61, 0x72, 0x74, 0x71, 0x00, 0x7e, 0x00, 0x01, 0x78, 0x70,
            0x73, 0x72, 0x00, 0x0e, 0x6a, 0x61, 0x76, 0x61, 0x2e, 0x75,
            0x74, 0x69, 0x6c, 0x2e, 0x44, 0x61, 0x74, 0x65, 0x68, 0x6a,
            (byte) 0x81, 0x01, 0x4b, 0x59, 0x74, 0x19, 0x03, 0x00, 0x00,
            0x78, 0x70, 0x77, 0x08, 0x00, 0x00, 0x00, 0x66, (byte) 0xdf,
            0x6e, 0x1e, 0x00, 0x78, 0x73, 0x71, 0x00, 0x7e, 0x00, 0x03,
            0x77, 0x08, 0x00, 0x00, 0x00, (byte) 0xd5, 0x17, 0x69, 0x22,
            0x00, 0x78};

    public static void main(String[] args) {
        Period p = (Period) deserialize(serializedForm);
        LOGGER.debug(p);
    }

    // Returns the object with the specified serialized form
    private static Object deserialize(byte[] sf) {
        try {
            InputStream is = new ByteArrayInputStream(sf);
            ObjectInputStream ois = new ObjectInputStream(is);
            return ois.readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
```

* the byte array literal used to initialize *serializedForm* was generated by serializing a normal *Period* instance and
  hand-editing the resulting byte stream
    - the details of the stream are unimportant to the example, but if you're curious, the serialization byte-stream
      format is described in the Java Object Serialization Specification [Serialization, 6]
    - if you run this program, it prints "Fri Jan 01 12:00:00 PST 1999 - Sun Jan 01 12:00:00 PST 1984"
    - simply declaring *Period* serializable enabled us to create an object that violates its class invariants
* to fix this problem, provide a *readObject* method for *Period* that calls *defaultReadObject* and then checks the
  validity of the deserialized object
    - if the validity check fails, the *readObject* method throws an *InvalidObjectException*, preventing the
      deserialization from completing:

```Java
class Period {
    // ...

    // readObject method with validity checking
    private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        // Check that our invariants are satisfied
        if (start.compareTo(end) > 0)
            throw new InvalidObjectException(start + " after " + end);
    }
}
```

* while this fix prevents an attacker from creating an invalid *Period* instance, there is a more subtle problem still
  lurking
    - it is possible to create a mutable *Period* instance by fabricating a byte stream that begins with a valid *
      Period* instance and then appends extra references to the private *Date* fields internal to the *Period* instance
    - the attacker reads the *Period* instance from the *ObjectInput*-Stream and then reads the "*rogue object
      references*" that were appended to the stream
    - these references give the attacker access to the objects referenced by the private *Date* fields within the *
      Period* object
    - by mutating these *Date* instances, the attacker can mutate the *Period* instance
    - the following class demonstrates this attack:

```Java
public class MutablePeriod {

    // A period instance
    public final Period period;

    // period's start field, to which we shouldn't have access
    public final Date start;

    // period's end field, to which we shouldn't have access
    public final Date end;

    public MutablePeriod() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);

            // Serialize a valid Period instance
            out.writeObject(new Period(new Date(), new Date()));

            /*
             * Append rogue "previous object refs" for internal
             * Date fields in Period. For details, see "Java
             * Object Serialization Specification," Section 6.4.
             */
            byte[] ref = {0x71, 0, 0x7e, 0, 5}; // Ref #5
            bos.write(ref); // The start field
            ref[4] = 4; // Ref # 4
            bos.write(ref); // The end field

            // Deserialize Period and "stolen" Date references
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            period = (Period) in.readObject();
            start = (Date) in.readObject();
            end = (Date) in.readObject();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
```

* To see the attack in action, run the following program:

```Java
class Attacker {
    public static void main(String[] args) {
        MutablePeriod mp = new MutablePeriod();
        Period p = mp.period;
        Date pEnd = mp.end;

        // Let's turn back the clock
        pEnd.setYear(78);
        LOGGER.debug(p);

        // Bring back the 60s!
        pEnd.setYear(69);
        LOGGER.debug(p);
    }
}
```

* running this program produces the following output:

```
Wed Apr 02 11:04:26 PDT 2008 - Sun Apr 02 11:04:26 PST 1978
Wed Apr 02 11:04:26 PDT 2008 - Wed Apr 02 11:04:26 PST 1969
```

* while the *Period* instance is created with its invariants intact, it is possible to modify its internal components at
  will
    - once in possession of a mutable *Period* instance, an attacker might cause great harm by passing the instance on
      to a class that depends on *Period*'s immutability for its security
    - this is not so far-fetched:
        + there are classes that depend on String's immutability for their security.
* the source of the problem is that *Period*'s *readObject* method is not doing enough defensive copying
    - **when an object is deserialized, it is critical to defensively copy any field containing an object reference that
      a client must not possess**
    - **therefore, every serializable immutable class containing private mutable components must defensively copy these
      components in its *readObject* method**
    - the following *readObject* method suffices to ensure *Period*'s invariants and to maintain its immutability:

```Java
class Period {

    // ...

    // readObject method with defensive copying and validity checking
    private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        // Defensively copy our mutable components
        start = new Date(start.getTime());
        end = new Date(end.getTime());

        // Check that our invariants are satisfied
        if (start.compareTo(end) > 0)
            throw new InvalidObjectException(start + " after " + end);
    }
}
```

* note that the defensive copy is performed prior to the validity check and that we did not use Date's *clone* method to
  perform the defensive copy
    - both of these details are required to protect *Period* against attack ([Item 39](07_methods.md#item39))
    - note also that defensive copying is not possible for final fields
    - to use the *readObject* method, we must make the *start* and *end* fields nonfinal
    - this is unfortunate, but it is the lesser of two evils
    - with the new *readObject* method in place and the final modifier removed from the *start* and *end* fields, the *
      MutablePeriod* class is rendered ineffective
    - the above attack program now generates this output:

```
Wed Apr 02 11:05:47 PDT 2008 - Wed Apr 02 11:05:47 PDT 2008
Wed Apr 02 11:05:47 PDT 2008 - Wed Apr 02 11:05:47 PDT 2008
```

* In release 1.4, the *writeUnshared* and *readUnshared* methods were added to *ObjectOutputStream* with the goal of
  thwarting rogue object reference attacks without the cost of defensive copying [Serialization]
    - unfortunately, these methods are vulnerable to sophisticated attacks similar in nature to the *ElvisStealer*
      attack described in [Item 77](#item77)
    - **do not use the *writeUnshared* and *readUnshared* methods**
    - they are typically faster than defensive copying, but they don't provide the necessary safety guarantee
* here is a simple litmus test for deciding whether the default *readObject* method is acceptable for a class:
    - would you feel comfortable adding a public constructor that took as parameters the values for each nontransient
      field in the object and stored the values in the fields with no validation whatsoever?
    - if not, you must provide a *readObject* method, and it must perform all the validity checking and defensive
      copying that would be required of a constructor
    - alternatively, you can use the *serialization proxy pattern* ([Item 78](#item78)).
* There is one other similarity between *readObject* methods and constructors, concerning nonfinal serializable classes
    - a *readObject* method must not invoke an overridable method, directly or
      indirectly ([Item 17](04_classes_and_interfaces.md#item17))
    - if this rule is violated and the method is overridden, the overriding method will run before the subclass's state
      has been deserialized
    - a program failure is likely to result [Bloch05, Puzzle 91]
* To summarize, anytime you write a *readObject* method, adopt the mind-set that you are writing a public constructor
  that must produce a valid instance regardless of what byte stream it is given
    - do not assume that the byte stream represents an actual serialized instance
    - while the examples in this item concern a class that uses the default serialized form, all of the issues that were
      raised apply equally to classes with custom serialized forms
    - here, in summary form, are the guidelines for writing a bulletproof *readObject* method:
        + For classes with object reference fields that must remain private, defensively copy each object in such a
          field. Mutable components of immutable classes fall into this category.
        + Check any invariants and throw an *InvalidObjectException* if a check fails. The checks should follow any
          defensive copying.
        + If an entire object graph must be validated after it is deserialized, use the *ObjectInputValidation*
          interface [JavaSE6, Serialization].

        * Do not invoke any overridable methods in the class, directly or indirectly.

---

### Item 77: For instance control, prefer enum types to readResolve<a name="item77"></a>

* [Item 3](02_creating_and_destroying_objects.md#item3) describes the *Singleton* pattern and gives the following
  example of a singleton class
    - this class restricts access to its constructor to ensure that only a single instance is ever created:

```Java
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    
    private Elvis() { 
        // ... 
    }

    public void leaveTheBuilding() { 
        // ... 
    }
}
```

* as noted in [Item 3](02_creating_and_destroying_objects.md#item3), this class would no longer be a singleton if the
  words "implements Serializable" were added to its declaration
    - it doesn't matter whether the class uses the default serialized form or a custom serialized
      form ([Item 75](#item75)), nor does it matter whether the class provides an explicit *readObject*
      method ([Item 76](#item76))
    - any *readObject* method, whether explicit or default, returns a newly created instance, which will not be the same
      instance that was created at class initialization time
* the *readResolve* feature allows you to substitute another instance for the one created by *
  readObject* [Serialization, 3.7]
    - if the class of an object being deserialized defines a *readResolve* method with the proper declaration, this
      method is invoked on the newly created object after it is deserialized
    - the object reference returned by this method is then returned in place of the newly created object
    - in most uses of this feature, no reference to the newly created object is retained, so it immediately becomes
      eligible for garbage collection
* if the *Elvis* class is made to implement *Serializable*, the following *readResolve* method suffices to guarantee the
  singleton property:

```Java
public class Elvis {
    // ...
    
    // readResolve for instance control - you can do better!
    private Object readResolve() {
        // Return the one true Elvis and let the garbage collector
        // take care of the Elvis impersonator.
        return INSTANCE;
    }
}
```

* this method ignores the deserialized object, returning the distinguished *Elvis* instance that was created when the
  class was initialized
    - therefore, the serialized form of an *Elvis* instance need not contain any real data
        + all instance fields should be declared transient
    - in fact, **if you depend on *readResolve* for instance control, all instance fields with object reference types *
      must* be declared *transient***
    - otherwise, it is possible for a determined attacker to secure a reference to the deserialized object before its *
      readResolve* method is run, using a technique that is vaguely similar to the *MutablePeriod* attack
      in [Item 76](#item76)
* the attack is a bit complicated, but the underlying idea is simple
    - if a singleton contains a nontransient object reference field, the contents of this field will be deserialized
      before the singleton's *readResolve* method is run
    - this allows a carefully crafted stream to "steal" a reference to the originally deserialized singleton at the time
      the contents of the object reference field are deserialized
* here's how it works in more detail
    - first, write a "stealer" class that has both a *readResolve* method and an instance field that refers to the
      serialized singleton in which the stealer "hides"
    - in the serialization stream, replace the singleton's nontransient field with an instance of the stealer
    - you now have a circularity:
        + the singleton contains the stealer and the stealer refers to the singleton
        + because the singleton contains the stealer, the stealer's *readResolve* method runs first when the singleton
          is deserialized
        + as a result, when the stealer's *readResolve* method runs, its instance field still refers to the partially
          deserialized (and as yet unresolved) singleton
* the stealer's *readResolve* method copies the reference from its instance field into a static field, so that the
  reference can be accessed after the *readResolve* method runs
    - the method then returns a value of the correct type for the field in which it's hiding
    - if it didn't do this, the VM would throw a *ClassCastException* when the serialization system tried to store the
      stealer reference into this field
* to make this concrete, consider the following broken singleton:

```Java
// Broken singleton - has nontransient object reference field!
public class Elvis implements Serializable {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() { }

    private String[] favoriteSongs = { "Hound Dog", "Heartbreak Hotel" };

    public void printFavorites() {
        LOGGER.debug(Arrays.toString(favoriteSongs));
    }

    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }
}
```

* here is a "stealer" class, constructed as per the description above:

```Java
public class ElvisStealer implements Serializable {

    static Elvis impersonator;

    private Elvis payload;

    private Object readResolve() {
        // Save a reference to the "unresolved" Elvis instance
        impersonator = payload;

        // Return an object of correct type for favorites field
        return new String[] { "A Fool Such as I" };
    }

    private static final long serialVersionUID = 0;
}
```

* finally, here is an ugly program that deserializes a handcrafted stream to produce two distinct instances of the
  flawed singleton
    - the deserialize method is omitted from this program because it's identical to the one on page 303:

```Java
public class ElvisImpersonator {

    // Byte stream could not have come from real Elvis instance!
    private static final byte[] serializedForm = new byte[] {
        (byte)0xac, (byte)0xed, 0x00, 0x05, 0x73, 0x72, 0x00, 0x05,
        0x45, 0x6c, 0x76, 0x69, 0x73, (byte)0x84, (byte)0xe6,
        (byte)0x93, 0x33, (byte)0xc3, (byte)0xf4, (byte)0x8b,
        0x32, 0x02, 0x00, 0x01, 0x4c, 0x00, 0x0d, 0x66, 0x61, 0x76,
        0x6f, 0x72, 0x69, 0x74, 0x65, 0x53, 0x6f, 0x6e, 0x67, 0x73,
        0x74, 0x00, 0x12, 0x4c, 0x6a, 0x61, 0x76, 0x61, 0x2f, 0x6c,
        0x61, 0x6e, 0x67, 0x2f, 0x4f, 0x62, 0x6a, 0x65, 0x63, 0x74,
        0x3b, 0x78, 0x70, 0x73, 0x72, 0x00, 0x0c, 0x45, 0x6c, 0x76,
        0x69, 0x73, 0x53, 0x74, 0x65, 0x61, 0x6c, 0x65, 0x72, 0x00,
        0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x01,
        0x4c, 0x00, 0x07, 0x70, 0x61, 0x79, 0x6c, 0x6f, 0x61, 0x64,
        0x74, 0x00, 0x07, 0x4c, 0x45, 0x6c, 0x76, 0x69, 0x73, 0x3b,
        0x78, 0x70, 0x71, 0x00, 0x7e, 0x00, 0x02
    };

    public static void main(String[] args) {
        // Initializes ElvisStealer.impersonator and returns
        // the real Elvis (which is Elvis.INSTANCE)
        Elvis elvis = (Elvis) deserialize(serializedForm);
        Elvis impersonator = ElvisStealer.impersonator;
        elvis.printFavorites();
        impersonator.printFavorites();
    }
}
```

* running this program produces the following output, conclusively proving that it's possible to create two distinct *
  Elvis* instances (with different tastes in music):

```
[Hound Dog, Heartbreak Hotel]
[A Fool Such as I]
```

* you could fix the problem by declaring the favorites field transient, but you're better off fixing it by making Elvis
  a single-element enum type ([Item 3](02_creating_and_destroying_objects.md#item3))
    - historically, the *readResolve* method was used for all serializable instance-controlled classes
    - as of release 1.5, this is no longer the best way to maintain instance control in a serializable class
    - as demonstrated by the *ElvisStealer* attack, this technique is fragile and demands great care
* If instead you write your serializable instance-controlled class as an enum, you get an ironclad guarantee that there
  can be no instances besides the declared constants
    - the JVM makes this guarantee, and you can depend on it
    - it requires no special care on your part
    - here's how our *Elvis* example looks as an enum:

```Java
// Enum singleton - the preferred approach
public enum Elvis {
    INSTANCE;
    
    private String[] favoriteSongs = { "Hound Dog", "Heartbreak Hotel" };
    
    public void printFavorites() {
        LOGGER.debug(Arrays.toString(favoriteSongs));
    }
}
```

* the use of *readResolve* for instance control is not obsolete
    - if you have to write a serializable instance-controlled class whose instances are not known at compile time, you
      will not be able to represent the class as an enum type
* **the accessibility of *readResolve* is significant**
    - if you place a *readResolve* method on a final class, it should be private
    - if you place a *readResolve* method on a nonfinal class, you must carefully consider its accessibility
    - if it is private, it will not apply to any subclasses
    - if it is package-private, it will apply only to subclasses in the same package
    - if it is protected or public, it will apply to all subclasses that do not override it
    - if a *readResolve* method is protected or public and a subclass does not override it, deserializing a serialized
      subclass instance will produce a superclass instance, which is likely to cause a *ClassCastException*
* to summarize, you should use enum types to enforce instance control invariants wherever possible
    - if this is not possible and you need a class to be both serializable and instance-controlled, you must provide a *
      readResolve* method and ensure that all of the class's instance fields are either primitive or transient

---

### Item 78: Consider serialization proxies instead of serialized instances<a name="item78"></a>

* as mentioned in [Item 74](#item74) and discussed throughout this chapter, the decision to implement *Serializable*
  increases the likelihood of bugs and security problems, because it causes instances to be created using an
  extralinguistic mechanism in place of ordinary constructors
    - there is, however, a technique that greatly reduces these risks
    - this technique is known as the serialization proxy pattern
* The serialization proxy pattern is reasonably straightforward
    - first, design a private static nested class of the serializable class that concisely represents the logical state
      of an instance of the enclosing class
    - this nested class, known as the *serialization proxy*, should have a single constructor, whose parameter type is
      the enclosing class
    - this constructor merely copies the data from its argument:
        + it need not do any consistency checking or defensive copying
    - by design, the default serialized form of the serialization proxy is the perfect serialized form of the enclosing
      class
    - both the enclosing class and its serialization proxy must be declared to implement Serializable
* For example, consider the immutable *Period* class written in [Item 39](07_methods.md#item39) and made serializable
  in [Item 76](#item76)
    - here is a serialization proxy for this class
    - *Period* is so simple that its serialization proxy has exactly the same fields as the class:

```Java
// Serialization proxy for Period class
private static class SerializationProxy implements Serializable {
    private final Date start;
    private final Date end;

    SerializationProxy(Period p) {
        this.start = p.start;
        this.end = p.end;
    }

    private static final long serialVersionUID = 234098243823485285L; // Any number will do (Item 75)
}
```

* Next, add the following *writeReplace* method to the enclosing class
    - this method can be copied verbatim into any class with a serialization proxy:

```Java
private static class SerializationProxy implements Serializable {
    // ...
    
    // writeReplace method for the serialization proxy pattern
    private Object writeReplace() {
        return new SerializationProxy(this);
    }
}
```

* the presence of this method causes the serialization system to emit a *SerializationProxy* instance instead of an
  instance of the enclosing class
    - in other words, the *writeReplace* method translates an instance of the enclosing class to its serialization proxy
      prior to serialization
* with this *writeReplace* method in place, the serialization system will never generate a serialized instance of the
  enclosing class, but an attacker might fabricate one in an attempt to violate the class's invariants
    - to guarantee that such an attack would fail, merely add this *readObject* method to the enclosing class:

```Java
private static class SerializationProxy implements Serializable {
    // ...
    
    // readObject method for the serialization proxy pattern
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }
}
```

* finally, provide a *readResolve* method on the *SerializationProxy* class that returns a logically equivalent instance
  of the enclosing class
    - the presence of this method causes the serialization system to translate the serialization proxy back into an
      instance of the enclosing class upon deserialization
* this *readResolve* method creates an instance of the enclosing class using only its public API, and therein lies the
  beauty of the pattern
    - it largely eliminates the extralinguistic character of serialization, because the deserialized instance is created
      using the same constructors, static factories, and methods as any other instance
    - this frees you from having to separately ensure that deserialized instances obey the class's invariants
    - if the class's static factories or constructors establish these invariants, and its instance methods maintain
      them, you've ensured that the invariants will be maintained by serialization as well
* Here is the readResolve method for Period.SerializationProxy above:

```Java
class SomeClass {
    // readResolve method for Period.SerializationProxy
    private Object readResolve() {
        return new Period(start, end); // Uses public constructor
    }
}
```

* like the defensive copying approach (page 306), the serialization proxy approach stops the bogus byte-stream attack (
  page 303) and the internal field theft attack (page 305) dead in their tracks
    - unlike the two previous approaches, this one allows the fields of *Period* to be final, which is required in order
      for the *Period* class to be truly immutable ([Item 15](04_classes_and_interfaces.md#item15))
    - and unlike the two previous approaches, this one doesn't involve a great deal of thought
    - you don't have to figure out which fields might be compromised by devious serialization attacks, nor do you have
      to explicitly perform validity checking as part of deserialization
* there is another way in which the serialization proxy pattern is more powerful than defensive copying
    - the serialization proxy pattern allows the deserialized instance to have a different class from the originally
      serialized instance
    - you might not think that this would be useful in practice, but it is
* consider the case of *EnumSet* ([Item 32](06_enums_and_annotations.md#item32))
    - this class has no public constructors
    - only static factories
    - From the client's perspective, they return *EnumSet* instances, but in fact, they return one of two subclasses,
      depending on the size of the underlying enum type ([Item 1](02_creating_and_destroying_objects.md#item1) - page 7)
    - if the underlying enum type has sixty-four or fewer elements, the static factories return a RegularEnumSet
        + otherwise, they return a *JumboEnumSet*
    - now consider what happens if you serialize an enum set whose enum type has sixty elements, then add five more
      elements to the enum type, and then deserialize the enum set
    - it was a *RegularEnumSet* instance when it was serialized, but it had better be a *JumboEnumSet* instance once it
      is deserialized
* in fact that's exactly what happens, because *EnumSet* uses the serialization proxy pattern
    - in case you're curious, here is *EnumSet*'s serialization proxy
    - it really is this simple:

```Java
// EnumSet's serialization proxy
private static class SerializationProxy <E extends Enum<E>> implements Serializable {
    // The element type of this enum set.
    private final Class<E> elementType;
    
    // The elements contained in this enum set.
    private final Enum[] elements;
    
    SerializationProxy(EnumSet<E> set) {
        elementType = set.elementType;
        elements = set.toArray(EMPTY_ENUM_ARRAY); // (Item 43)
    }
    
    private Object readResolve() {
        EnumSet<E> result = EnumSet.noneOf(elementType);
        for (Enum e : elements)
            result.add((E)e);
        return result;
    }
    
    private static final long serialVersionUID = 362491234563181265L;
}
```

* the serialization proxy pattern has two limitations
    - it is not compatible with classes that are extendable by their
      clients ([Item 17](04_classes_and_interfaces.md#item17))
    - also, it is not compatible with some classes whose object graphs contain circularities
        + if you attempt to invoke a method on an object from within its serialization proxy's *readResolve* method,
          you’ll get a *ClassCastException*, as you don't have the object yet, only its serialization proxy
* finally, the added power and safety of the serialization proxy pattern are not free
    - on my machine, it is 14 percent more expensive to serialize and deserialize *Period* instances with serialization
      proxies than it is with defensive copying
* in summary, consider the serialization proxy pattern whenever you find yourself having to write a *readObject* or *
  writeObject* method on a class that is not extendable by its clients
    - this pattern is perhaps the easiest way to robustly serialize objects with nontrivial invariants