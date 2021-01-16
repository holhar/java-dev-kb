# Effective Java

## 7. Methods

* this chapter discusses several aspects of method design:
    - how to treat parameters and return values,
    - how to design method signatures, and
    - how to document methods
* Much of the material in this chapter applies to constructors as well as to methods
* Like [Chapter 4](04_classes_and_interfaces.md), this chapter focuses on usability, robustness, and flexibility

---

### Item 38: Check parameters for validity<a name="item38"></a>

* most methods and constructors have some restrictions on what values may be passed into their parameters
    - for example, it is not uncommon that index values must be non-negative and object references must be non-null
* **you should clearly document all such restrictions and enforce them with checks at the beginning of the method body**
    * if an invalid parameter value is passed to a method and the method checks its parameters befor execution, it will
      fail quickly and cleanly with an appropriate exception

* for public methods, use the Javadock *@throws* tag to document the exception that will be thrown if a restriction on
  parameter values is violated ([Item 62](09_exceptions.md#item62))
    - typically the exception will be *IlliegalArgumentException*, *IndexOutOfBoundsException*, or *
      NullPointerException* ([Item 60](09_exceptions.md#item60))
    - **if you document the restrictions and possible thrown exceptions, it is a simple matter to enforce the
      restrictions** - here's an example:

```Java
class MyBigInter extends BigInteger {
    
    // ...
    
    /**
     * Returns a BigInteger whose value is (this mod m). This method
     * differs from the remainder method in that it always returns a
     * non-negative BigInteger.
     * 
     * @param m the modulus, which must be positive
     * @return this mod m
     * @throws ArithmeticException if m is less than or equal to 0
     */
    public BigInteger mod(BigInteger m) {
        if(m.signum() <= 0) {
            throw new ArithmeticException("Modulus <= 0: " + m);
        // ... Do the computation
        }
    }
}
```

* for an unexported method, you as the package author control the circumstances under which the method is called, so you
  can and should ensure that only valid parameter values are ever passed in
    - therefore, nonpublic methods should generally check their parameters using *assertions*, as shown below:

```Java
class MyUtils {
    // Private helper function for a recursive sort
    private static void sort(long a[], int offset, int length) {
        assert a != null;
        assert offset >= 0 && offset <= a.length;
        assert length >= 0 && length <= a.length - offset;
        // ... Do the computation
    }
}
```

* in essence, assertions are claims that the asserted condition *will* be true, regardless of how the enclosing package
  is used by its clients
* unlike normal validity checks:
    - assertions throw *AssertionError* if they fail
    - assertions have no effect and essentially no cost unless you enable them, which you do by passing the *-ea* (or *
      -enableassertions*) flag to the *java* interpreter

#### Further remarks (which are discussed in more detail in the book, p. 182f.)

* it is particularly important to **check the validity of parameters that are not used by a method but are stored away
  for later use**
    - constructors represent a special case of this principle, as **it is critical to check the validity of constructor
      parameters to prevent the construction of an object that violates its class invariants**

* there are exceptions to the rule that you should check a method's parameters before performing its computation
    - an important exception is the case in which the validity check would be expensive or impractical *and* the
      validity check is performed implicitly in the process of doing the compution (i.e. the *Collections.sort(List)*
      method)

* occasionally, a computation implicitly performs a required validity check but throws the wrong exception if the check
  fails
    - under these circumstances, you should use the **exception translation** idiom, described
      in [Item 61](9_exceptions.md#item61), to translate the natural exception in the correct one

* do not infer from this item that arbitrary restrictions on parameters are a good thins
    - on the contrary, **you should design methods to be as general as it is practical to make them**

---

### Item 39: Make defensive copies when needed<a name="item39"></a>

* One thind that makes Java such a pleasure to use is that it is a **safe language**
    - this means that in the absence of native methods it is immune to buffer overruns, array overruns, wild pointers,
      and other memory corruption erros that plague unsafe languages such as C and C++
    - in a safe language, it is possible to write classes and to know with certainty that their invariants will remain
      true, no matter what happens in any other part of the system
    - this is not passible in languages that treat all of memory as one giant array

* but even in a safe language, you aren't isolated from other classes without some effort on your part
* **you must program defensively, with the assumption that clients of your class will do their best to destroy its
  invariants**
    - possible dangers are *attackers* and *mistakes* by programmers using your API

* while it is impossible to another class to midfy an object's internal state without some assistance from the object,
  it is surprisingly easy to provide such assistance without meaning to do so:
    - for example, consider the following class, which purports to represent an immutable time period:

```Java
// Broken "immutable" time period class
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
        if (start.compareTo(end) > 0)
            throw IllegalArgumentException(
                    start + " after " + end);
        this.start = start;
        this.end = end;
    }

    public Date start() {
        return start;
    }

    public Date end() {
        return end;
    }

    // ... Remainder omitted
}
```

* The problem is, that *Period* may be immutable, but the *Date* class is not!

```Java
public class AttackerClient {
    public static void main(String[] args) {
        // Attack the internals of a Period instance
        Date start = new Date();
        Date end = new Date();
        period p = new Period(start, ende);
        end.setYear(78); // Modifies internals of p!
    }
}
```

* to protect the internals of a *Period* instance from this sort of attack, **it is essential to make a *defensive copy*
  of each mutable parameter to the constructor** and to use the copies as components of the *Period* instance in plave
  of the originals:

```Java
public final class Period {
    private final Date start;
    private final Date end;

    // Repaired constructor - makes defensive copies of parameters
    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (start.compareTo(end) > 0)
            throw IllegalArgumentException(
                    start + " after " + end);
    }

    // ... Remainder omitted
}
```

* note that **defensive copies are made *before* checking the validity of the parameters ([Item 38](#item38)), and the
  validity check is performed on the copies rather than on the originals**
* note also that we did not use *Date*'s *clone* method to make the defensive copies
    - because *Date* is nonfinal, the *clone* method is not guaranteed to return an object whose class is *
      java.util.Date*
    - **do not use the *clone* method to make a defensive copy of a parameter whose type is subclassable by untrusted
      parties**

* while the replacement constructor above successfully defends against the previous attack, it is still possible to
  mutate a *Period* instance, because its accessors offer access ot its mutable internals:

```Java
public class AttackerClient {
    public static void main(String[] args) {
        // Second attack on the internals of a Period instance
        Date start = new Date();
        Date end = new Date();
        period p = new Period(start, ende);
        p.end.setYear(78); // Modifies internals of p!
    }
}
```

* to defend agains the second attack, merely modify the accessors to *return defensive copies of mutable internal
  fields**:

```Java
public final class Period {

    // ... Remainder omitted

    // Repaired accessors - make defensive copies of internal fields
    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }
}
```

* now, the *Period* class is *truly immutable*

#### Further situations to use defensive copies

* defensive copying of parameters is not just for immutable classes
* anytime you write a method of constructor that enters a client-provided object into an internal data structure, this
  about whether the client-provided object is potentially mutable
    - it it is, think about whether you class could tolerate a change in the object after it was entered into the data
      structure
    - if the answer is no, you must defensively copy the object and enter the copy into the data structure in place of
      the original
* the same is true for defensive copying the internal components prior to returning them to clients
* arguably, the real lesson in all of this is that you should, where possible, use immutable objects as components of
  you objects, so that you don't have to worry about defensive copying ([Item 15](04_classes_and_interfaces.md#item15))

#### Reasons agains defensive copies

* defensive copying can have a performance penalty and isn't always justified
    - if a class trusts its caller not to modify an internal component, then it may be appropriate to dispense with
      defensive copying
    - but the documentation should point out, that the client must not modify the affected parameters or return values
* even across package boundaries, it is not always appropriate to make a defensive copy of a mutable parameter before
  integrating it into an object
    - there are some methods and constructors whose invocation indicates an explicit *handoff* of the object referenced
      by a parameter
    - this must be properly documented as well
    - classes containing methods or constructors whose invocation indicates a transfer of control cannot defend
      themselves against malicious clients
        + such classes are acceptable only when ther is mutual trust between the class and its client or when damage ot
          the class's invariants would harm no one but the client
        + an example of the latter situation is the wrapper class
          pattern ([Item 16](04_classes_and_interfaces.md#item16))

---

### Item 40: Design method signatures carefully<a name="item40"></a>

* this item is a grab bag of API design hints that don't quite deserve items of their own
* taken together, they'll help make your API easier to learn and use and less prone to errors

* **choose method names carefully**
    - names should always obey the standard naming conventions ([Item 56](08_general_programming.md#item56))
    - names should be understandable and consistent (primarily with other names in the same package, secondary with the
      broader consensus)
* **don't go overboard in providing convenience methods**
    - every emthod should "pull its weight"; too many methods make a class difficult to learn, use, document, test, and
      maintain
    - this is doubly true for interfaces!
    - consider providing a "shorthand" only if it will be used often - **when in doubt, leave it out**
* **avoid long parameter lists**
    - aim for four parameters or fewer
    - **long sequences of identically types parameters are especially harmful**, as it is easy to mix up the order of
      the parameters
* **there are three techniques to shorten overly long parameter lists**:
    - *break the method up into multiple methods*, each of which requires only a subset of the parameters
        + if done carelessly, this can lead to too many methods, but it can also help *reduce* the method count by
          increasing *orthogonality*
    - *create helper classes to hold groups of parameters*
        + typically these helper classes are static member classes ([Item 22](04_classes_and_interfaces.md#item22))
        + this technique is recommended if a frequently occuring sequence of parameters is seen to represent some
          distinct entity
    - *adapt the Builder pattern* ([Item 2](02_creating_and_destroying_objects.md#item2)) from object construction to
      method invocation
        + if you have a method with many parameters, especially if some of them are optional, it can be beneficial to
          make multiple "setter" calls on this object, each of which sets a single parameter or a small, related group
* **for parameter types, favor interfaces over classes** ([Item 52](08_general_programming.md#item52))
    - if there is an appropriate interface to define a parameter, use it in favor of a class that implements the
      interface
        + for exampe, ther is no reason ever to write a method that takes *HasMap* on input - use *Map* instead
    - by using a class instead of an interface, you restrict your client to a particular implementation and force an
      unnecessary and potentially expensive copy operation if the input data happens to exist in some other form
* **prefer two-element enum types to *boolean* parameters**
    - it makes your code easier to read and to write, especially if you're using an IDE that supports autocompletion
    - also, it makes it easy to add more options later
    - also, you can refactor enum data dependencies into methods on the enum
      constants ([Item 30](06_enums_and_annotations.md#item30))

---

### Item 41: Use overloading judiciously<a name="item41"></a>

* the following program is a well-intentioned attempt to classify collections according to whether they are sets, lists,
  or some other kind of collection:

```Java
// Broken! What does this program print?
public class CollectionClassifier {
    public static String classify(Set<?> s) {
        return "Set";
    }

    public static String classify(List<?> lst) {
        return "List";
    }

    public static String classify(Collection<?> c) {
        return "Unknown Collection";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for (Collection<?> c : collections) {
            LOGGER.debug(classify(c));
        }
    }
}
```

* this program print *Unknown Collection* three times <= Why does this happen?
    - because the *classify* method is *overloaded*, and **the choice of which overloading to invoke is made at compile
      time**
    - for all three iterations of the loop, the *compile-time* type of the parameter is the same: *Collection\<?\>*
    - the *runtime type* is different in each iteration, but this does not affect the choice of overloading
    - because the compile-time type of the parameter is *Collection\<?\>*, the only applicable overloading is the third
      one, *classify(Collection\<?\>)*, and this overloading is invoked in each iteration of the loop
* the behavior of this program is counterintuitive because **selection among overloaded methods is static, while
  selection among overridden methods is dynamic**
    - the correct version of an *overridden* method is chosen at runtime, based on the runtime type of the object on
      which the method is invoked
    - as a reminder, *a method is overridden when a subclass contains a method declaration with the same signature as a
      method declaration in an ancestor*
        + if an instance method is overridden in a subclass and this method is invoked on an instance of the subclass,
          the subclass's *overriding method* executes, regardless of the compile-time type of the subclass instance
        + to make this concrete, consider the following program:

```Java
class Wine {
    String name() { return "wine"; }
}

class SparklingWine extends Wine {
    @Override
    String name() { return "sparkling wine"; }
}

class Champagne extends SparklingWine {
    @Override
    String name() { return "champagne"; }
}

public class Overriding {
    public static void main(String[] args) {
        Wine[] wine = {
                new Wine(), new SparklingWine(), new Champagne()
        };
        
        for(Wine wine : wines) {
            LOGGER.debug(wine.name());
        }
    }
}
```

* as you would expect, this program prints out *wine*, *sparkling wine*, and *champagne*, even though the compile-time
  type of the instance is *Wine* in each iteration of the loop
    - the "most specific" overriding method always gets executed
    - compare this to overloading, where the runtime type of an object has no effect on which overloading is executed;
      the selection is made at compile time, based entirely on the compile-time types of the parameters
* assuming a static method is required, the best way to fix the program is to replace all three overloadings of *
  classify* with a single method that does an explicit *instanceof* test:

```Java
class CollectionClassifier {
    public static String classify(Collection<?> c) {
        return c instanceof Set ? "Set" :
                c instanceof List ? "List" : "Unknown Collection";
    }
}
```

* because overriding is the norm and overloading is the exception, overriding sets people's expectations for the
  behavior of method invocation
* as demonstrated by the *CollectionClassifier* example, overloading can easily confound these expectations
* to avoid errors made by users you should **avoid confusing uses of overloading**
* exactly what constitutes a confusing use of overloading is open to some debate
    - **a safe, conservative policy is never to export two overloadings with the same number of parameters**
* if a method uses varargs, a conservative policy is not to overload it at all, except as described
  in [Item 42](#item42)
* if you adhere to these restrictions, programmers will never be in doubt as to which overloading applies to any set of
  actual parameters
* the restrictions are not terribly onerous because *you can always give methodsdifferent names instead of overloading
  them

* for constructors, you don't have the option of using different names: multiple constructors for a class are *always*
  overloaded
    - you do, in many cases, have the option of exporting **static factories** instead of
      constructors ([Item 1](02_creating_and_destroying_objects.md#item1))
* also, with constructors you don't have to worry about interactions between overloading and overriding, because
  constructors can't be overridden
* you probably will have occasion to export multiple constructors with the same number of parameters, so it pays to know
  how to do it safely

#### How to avoid confusing overloading with the same number of parameters

* exporting multiple overloadings with the same number of parameters is unlikely to confuse programmers *if* it is
  always clear which overloading will apply to any given set of actual parameters
    - this is the case when at least one corresponding formal parameter in each pair of overloadings has a "*radically
      different*" type in the two overloadings
    - two types are radically different if it is clearly impossible to cast an instance of either type to the other
    - under these circumstances, which overloading applies to a given set of actual parameters is fully determined by
      the runtime types of the parameters and cannot be affected by their compile-time types, so the major source of
      confusion goes away
        + for example, *ArrayList* has one constructor that takes an *int* and a second constructor that takes a *
          Collection*
        + it is hard to imagine any confusion over which of these two constructors will be invoked under any
          circumstances

##### Types that are radically different

* array types and classes other than *Object* are radically different
* also, array types and interfaces other than *Serializable* and *Cloneable* are radically different
* two distinct classes are said to be *unrelated* if neither class is a descendant of the
  other ([JLS, 5.5](https://todo/add/url))
    - for example, *String* and *Throwable* are unrelated classes, so unrelated classes are radically different

#### Summary

* just because you can overload methods doesn't mean you should
* you should generally refrain from overloading methods with multiple signatures that have the same number of parameters
* in some cases, especially where constructors are involved, it may be impossible to follow this advice
    - in that case, you should at least avoid situations where the same set of parameters can be passed to different
      overloadings by the addition of casts
    - if such a situation cannot be avoided, for example, because you are retrofitting an existing class to implement a
      new interface, you should ensure that all overloadings behave identically when passed the same parameters
    - if you fail to do this, programmers will be hard pressed to make effective use of the overloaded method or
      constructor, and they won't understand why it doesn't work

---

### Item 42: Use varargs judiciously<a name="item42"></a>

* varargs methods accept zero or more arguments of a specified type
* the varargs facility works by first creating an array whose size is the number of arguments passed at the call site,
  then putting the argument values into the array, and finally passing the array to the method
    - for example, here is a varargs method that takes a sequence of *int* arguments and returns their sum
    - as you would expect, the value of *sum(1, 2, 3)* is *6*, and the value of *sum()* is *0*:

```Java
class SomeUtils {
    // Simple use of varargs
    static int sum(int... args) {
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }
}
```

* sometimes it's appropriate to write a method that requires *one* or more arguments of some type, rather than *zero* or
  more
    - for example, suppose you want to compute the minimum of a number of *int* arguments
    - this function is not well defined if the client passes no arguments
    - you could check the array length at runtime:

```Java
class SomeUtils {
    // The WRONG way to use varargs to pass one or more arguments!
    static int min(int... args) {
        if (args.length == 0)
                throw new IllegalArgumentException("Too frew arguments");
        int min = args[0];
        for (int i = 1; i < args.length; i++)
            if (args[i] < min)
                min = args[i];
        return min;
    }
}
```

* this solution has several problems:
    - the most serious is that if the client invokes this method with no arguments, it fails at runtime rather than
      compile time
    - another problem is that it is ugly - you have to include an explicit validity check on *args*, and you can't use a
      for-each loop unless you initialize *min* to *Integer.MAX_VALUE*, which is also ugly
* luckily there's a much better way to achieve the desired effect
    - declare the method to take two parameters, one normal parameter of the specified type and one varargs parameter of
      this type
    - this solution corrects all the deficiencies of the previous one:

```Java
class SomeUtils {
    // The right way to use varargs to pass one or more arguments
    static int min(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int arg : remainingArgs)
            if (arg < min)
                min = arg;
        return min;
    }
}
```

* this example illustrates, that varargs are effective in circumstances where you really do want a method with a
  variable number of arguments
    - varargs were designed for *printf*, which was added to the platform in release 1.5, and for the core reflection
      facility ([Item 53](08_general_programming.md#item53)), which was retrofitted to take advantage of varargs in that
      release
    - both *printf* and reflection benefit enormously from varargs

#### Retrofitting methods to use varargs

* you can retrofit an existing method that takes an array as its final parameter to take a varargs parameter instead
  with no effect on existing clients
    - but just cause you can doesn't mean you should!
    - **Don't retrofit every method that has a final array parameter; use varargs *only* when a call really operates on
      a variable-length sequence of values**
    - for an exhaustive illustrative example see the book, pages 198f.

#### Suspect signatures

* two method signatures are particularly suspect:

```Java
class SomeUtils {
    ReturnType1 suspect1(Object... args) { }
    <T> ReturnType2 suspect2(T... args) { }   
}
```

* methods with either of these signatures will accept *any* parameter list
* any compile-time type-checkint that you had prior to the retrofit will be lost, as demonstrated by what happened to *
  Arrays.asList* (see the book, pages 198f.)

#### Using varargs in performance-critical situations

* exercise care when using the varargs facility in performance-critical situations
    - every invocation of a varargs method causes an array allocation and initialization
    - if you have determined empirically that you can't afford this cost but you need the flexibility of varargs, there
      is a pattern that letzs you have your cake and eat it too
        + suppose you've determined that 95 percent of the calls to a method have three or fewer parameter
        + then declare five overloadings of the method, one each with zero through three ordinary parameters, and a
          single varargs method for use when the number of arguments exceeds three:

```Java
class SomeUtils {
    public void foo() { }
    public void foo(int a1) { }
    public void foo(int a1, int a2) { }
    public void foo(int a1, int a2, int a3) { }
    public void foo(int a1, int a2, int a3, int... rest) { }
}
```

* now you know that you'll pay the cost of the array creation only in the 5 percent of all invocations where the number
  of parameters exceeds three
* like most performance optimizations, this technique usually isn't appropriate, but when it is, it's a lifesaver

---

### Item 43: Return empty arrays or collections, not nulls<a name="item43"></a>

* it is not uncommon to see methods that look something like this:

```Java
class SomeShopUtils {
    private final List<Cheese> cheesesInStock = new ArrayList<>();
    
    // ...
    
    /**
     * @return an array containing all of the cheeses in the shop,
     *  or null if no cheeses are available for purchase
     *
     */
    public Cheese[] getCheeses() {
        if (cheesesInStock.size() == 0)
            return null;
        // ...
    }
}
```

* there is no reason to make a special case for the situation where no cheeses are available for puchase
    - doing so requires extra code in the client to handle the null return value, for example:

```Java
class ShopClient {
    public static void main(String[] args) {
        Cheese[] cheeses = shop.getCheeses();
        if (cheeses != null &&
            Arrays.asList(cheeses).contains(Cheese.STILTON))
            LOGGER.debug("Jolly good, just the thing.");
    }
}
```

* instead of:

```Java
class ShopClient {
    public static void main(String[] args) {
        Cheese[] cheeses = shop.getCheeses();
        if (Arrays.asList(cheeses).contains(Cheese.STILTON))
            LOGGER.debug("Jolly good, just the thing.");
    }
}
```

* this sort of circumlocution is required in nearly every use of a method that returns *null* in place of an empty (
  zero-length) array or collection
    - **it is error-prone, because the programmer writing the client might forget to write the special-case code to
      handle a null return**
    - such an error may go unnoticed for years, as such methods usually return one or more objects
    - less significant, but still worthy of note, returning *null* in place of an empty array also complicates the
      method that returns the array or collection
* it is sometimes argued that a null return value is preferable to an empty array because it avoids the expense of
  allocating the array
    - this argument fails on two count:
        + first, it is inadvisable to worry about performance at this level unless profiling has shown that the method
          in question is a real contributor to performance problems ([Item 55](08_general_programming.md#item55))
        + second, it is possible to return the same zero-length array from every invocation that returns no items
          because zero-length arrays are immutable and immutable objects may be shared
          freely ([Item 15](04_classes_and_interfaces.md#item15))
        + in fact, this is exactly what happens when you use the standard idiom for dumping items from a collection into
          a typed array:

```Java
// The right way to return an array from a collection
class SomeShopUtils {
    private final List<Cheese> cheesesInStock = new ArrayList<>();
    
    // ...
    
    private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];
    
    /**
     * @return an array containing all of the cheeses in the shop
     */
    public Cheese[] getCheeses() {
        return cheesesInStock.toArray(EMPTY_CHEESE_ARRAY);
    }
}
```

* in this idiom, an empty-array constant is passed to the *toArray* method to indicate the desired return type
    - normally the *toArray* method allocates the returned array, but if the collection is empty, it fits in the
      zero-length input array, and the specification for *Collection.toArray(T[])* guarantees that the input array will
      be returned if it is large enough to hold the collection
    - therefore the idiom never allocates an empty array

* in similar fashion, a collection-valued method can be made to return the same immutable empty collection every time it
  needs to return an empty collection
    - *Collections-emptySet*, *emptyList*, and *emptyMap* methods provide exactly what you need, as shown below:

```Java
// The right way to return a copy of a collection
class SomeShopUtils {
    public List<Cheese> getCheeseList() {
        if (cheesesInStock.isEmpty())
            return Collections.emptyList(); // Always returns same list
        else
            return new ArrayListy<Cheese>(cheesesInStock);
    }
}
```

* in summary, **there is no reason ever to retun *null* from an array- or collection-valued method instead of returning
  an empty array or collection**

---

### Item 44: Write doc comments for all exposed API elements<a name="item44"></a>

* if an API is to be usable, it must be documented
* *the javadoc utility* generates API documentation automatically from source code with specially formatted *
  documentation comments*, mor commonly known as *doc comments*
    - the *doc comments* constitute a de facto API for documentation that every programmer should know
    - these conventions are described on Sun's *How to Write Doc Comments* [web page](//https://todo/add/url)
* the item in the book describes relatively exhaustive how to deal with doc comments, here I summarize key points

* **to document you API properly, you must precede *every* exported class, interface, constructor, method, and field
  declaration with a doc comment**
    - if a class is serializable, you should also document its serialized form ([Item 75](11_serialization.md#item75))
* **the doc comment for a method should describe succinctly the contract between the method and its client**
    - with the exception of methods in classes designed for inheritance ([Item 17](04_classes_and_interfaces.md#item17))
      , the contract should say *what* the method does rather than *how* it does its job
    - the doc comment should enumerate all of the method's *preconditions*, which are the things that have to be true in
      order for a client to invoke it, and its *postconditions*, whihc are the things that will be true after the
      invocation has completed successfully
    - typically, preconditions are described implicitly by the *@throws* tags for unchecked exceptions; each unchecked
      exception corresponds to a precondition violation
    - also, preconditions can be specified along with the affected parameters in their *@param* tags
* in addition to preconditions and postconditions, methods should document any *side effects*
    - a side effect is an observable change in the state of the system that is not obviously required in order to
      achieve the postcondition
    - for example, if a method starts a background thread, the documentation should make note of it
    - finally, documentation comments should describe the *thread safety* of a class or method, as discussed
      in [Item 70](10_concurrency.md#item70)
* to describe a method's contract fully, the doc comment should have an *@param* tag for every parameter, an *@return*
  tag unless the method has a void return type, and an *@throws* tag for every exception thrown by the method, whether
  checked or unchecked ([Item 62](09_exceptions.md#item62))
    - by convention, the text following an *@param* tag or *@return* tag should be a noun phrase describing the value
      represented by the parameter or return value
    - the text following an *@throws* tag should consist of the word "*if*," followed by a clause describing the
      conditions under which the exception is thrown
    - occasionally, arithmetic expressions are used in place of noun phrases
    - by convention, the phrase or clause following an *@param*, *@return*, or *@throws* tag is not terminated by a
      period
    - all of these conventions are illustrated by the following short doc comment:

```Java
public interface BlahUtils {
    /**
     * Returns the element at the specified position in this list.
     *
     * <p>This method is <i>not</i> guaranteed to run in constant
     * time. In some imiplementations it may run in time proportional
     * to the element position.</p>
     * 
     * @param   index index of element to return; must be
     *          non-negative and less than the size of this list
     * @return  the element at the specified position in this list
     * @throws  IndexOutOfBoundsException if the index is out of range
     *          ({@code index < 0 || index >= this.size()})
     */
    E get(int index);
}
```

* **it is no longer necessary to use the HTML \<code\> or \<tt\> tags in doc comments: the Javadoc {@code} tag is
  preferable because it eliminates the need to escapt eHTML metacharacters**

#### Summary

* **no two members or constructors in a class or interface should have the same summary description**
* **when documenting a generic type or method, be sure to document all type parameters**
* **when documenting an enum type, be sure to document the constants** as well as the type and any public methods
* **when documenting an annotation type, be sure to document any members** as well as the type itself
* documentation comments are the best, most effective way to document your API
    - their use should be considered mandatory for all exported API elements
    - adopt a consistent style that adheres to standard conventions
    - remember that arbitrary HTML is permissible within documentation comments and that HTML metacharacters must be
      escaped