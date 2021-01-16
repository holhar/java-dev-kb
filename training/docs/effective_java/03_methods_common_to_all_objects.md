# Effective Java

## 3. Methods Common to All Objects

Although *Object* is a concrete class, it is designed primarily for extension. All of its nonfinal methods (*equals,
hashCode, toString, clone, and finalize*) have explicit **general contracts** because they are designed to be
overridden. It is the responsibility of any class overriding these methods to obey their general contracts; failure to
do so will prevent other classes that depend on the contracts (such as *HashMap* and *HashSet*) from functioning
properly in conjunction with the class.

This chapter tells you when and how to override the nonfinal *Object* methods. The *finalize* method is omitted from
this chapter because it was discussed in [item7](02_creating_and_destroying_objects.md#item7). While not an *Object*
method, **Comparable.compareTo** is discussed in this chapter because it has a similar character.

---

### Item 8: Obey the general contract when overriding equals<a name="item8"></a>

The easiest way to avoid problems is not to override the *equals* method, in which case each instance of the class is
equal only to itself: this is the right thing to do if any of the following conditions apply:

* each instance of the class is inherently unique
* you con't care whether the class provides a "logical equality" test
* a supercalss has already overridden *equals*, and the superclass behavior is appropriate for this class
* the class is private or package-private, and you are certain that its equals method will never be invoked - arguably,
  the *equals* method should be overidden under these circumstances, in case it is accidentally invoked:

```Java
@Override
public boolean equals(Object o) {
    throw new AssertionError(); // Method is never called
}
```

**When is it appropriate to override Object.equals?**:

* When a class has a notion of *logical equality* that differs from mere object identity, and a superclass has not
  already overriden *equals* to implement the desired behavior - this is generally the case for **value classes**
    - this satisfys programmer expectations, and
    - it enables instances to serve as map keys or set elements with predictable, desirable behavior
* objects with [instance control](02_creating_and_destroying_objects.md#item1)
  and [enum types](06_enums_and_annotations.md#item30) *do not* require the equals method to be overridden

When you override the *equals* method, you must adhere to its general contract. **The *equals* method implements an *
equivalence relation*, it is:**

* **Reflexive**: for any non-null reference value *x*, **x.equals(x)** must return *true*.
* **Symmetric**: for any non-null reference values *x* and *y*, **x.equals(y)** must return *true* if and only if **
  y.equals(x) returns *true*.
* **Transitive**: for any non-null reference values *x*, *y*, *z*, if **x.equals(y)** returns *true* and **y.equals(z)**
  returns *true*, then **x.equals(z)** must return *true*.
* **Consistent**: for any non-null reference values *x* and *y*, multiple invoations of **x.equals(y)** consistently
  return *true* or consistently return *false*, provided no information used in *equals* comparisons on the objects is
  modified.
* for any non-null reference value *x*, **x.equals(null)** must return *false*.

For implementation examples of the *equals* method that violate the contract, see the book.

* Once you've violated the *equals* contract, you simply don't know how other objects will behave when confronted with
  your object!
* There is no way to extend an instantiable class and add a **value component** while preserving the *equals* contract (
  unless you are willing to forgo the benefits of object-oriented abstraction).
* do not write an *equals* method that depends on **unreliable resources**!

#### Putting it all together, here's a recipe for a high-quality equals method

1. Use the **==** operator to check if the argument is a reference to this object.
2. Use the **instanceof** operator to check if the argument has the correct type.
3. Cast the argument to the correct type.
4. For each **significant** field in the class, check if that field of the argument matches the corresponding field of
   this object.
5. When you are finished writing your *equals* method, ask yourself (and write unit tests!) three questions: Is it
   symmetric? Is it transitive? Is it consistent?

#### A good *equals* example

```Java
public final class PhoneNumber {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        reangeCheck(prefix, 999, "prefix");
        reangeCheck(lineNumber, 9999, "line number");

        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }

    private static void rangeCheck(int arg, int max, String name) {
        if(arg < 0 || arg > max) {
            throw new IllegalArgumentException(name + ": " + arg);
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if!(o instanceof PhoneNumber))
            return false;
        PhoneNumber pn = (PhoneNumber)o;
        return pn.lineNumber == lineNumber
            && pn.prefix == prefix
            && pn.areaCode == areaCode;
    }

    // hashCode implementation also needed, [see Item 9](#item9)
    // ...
}
```

#### Final caveats

* Always override *hashCode* when you override *equals* (see also [Item 9](#item9))
* Don't try to be too clever (that is, keep your equality checks as simple as possible)
* Don't substitute another type for *Object* in the *equals* declaration

---

### Item 9: Always override hashCode when you override equals<a name="item9"></a>

**You must override hashCode in every class that overrides equals.** Failure to do so will result in a violation of the
general contract for *Object.hashCode*, which will prevent your class from functioning properly in conjunction with all
hash-based collections, including *HashMap*, *HashSet*, ans *Hashtable*.

#### General Contract

* Whenever it is invoked on the same object more than once curing an execution of an application, the *hashCode* method
  must consistently return the same integer, provided no information used in *equals* comparisons on the object is
  modified. This integer need not remain consistent from one execution of an application to another execution of the
  same application.
* If two object are equal according to the *equal(Object)* method, then calling the *hashCode* method on each of the two
  objects must produce the same integer result.
* It is not required that if two objects are unequal according to the *equals(Object)* method, then calling the *
  hashCode* method on each of the two objects must produce distinct integer results. However, the programmer should be
  aware that producing distinct integer results for unequal objects may improve the performance of the hash tables.

**The key provision that is violated when you fail to override hashCode is the second one: equal objects must have equal
hash codes.**

#### hashCode Recipe

* Store some constant nonzero value, say, 17, in an *int* variable called *result*.
* For each significant field *f* in your object (**each field taken into account by the *equals* method, that is**), do
  the following:
    * Compute an *int* hash code *c* for the field:
        * If the field is a *boolean*, compute (**f ? 1 : 0**).
        * If the field is a *byte, char, short, or int*, comput **(int) f**.
        * If the field is a *long*, compute **(int) (f ^ (f >>> 32))**;
        * If the field is *float*, compute **Float.floatToIntBits(f)**.
        * If the field is a *double*, compute **Double.doubleToLongBits(f)**, and then hash the resulting *long* as in
          step *2.a.iii*.
        * If the field is an object reference and this class's *equals* method compares the field by recursively
          invoking *equals*, recursively invoke *hashCode* on the field. If a more complex comparison is required,
          compute a "conanincal representation" for this field and invoke *hashCode* on the canonical representation. If
          the value of the fiel is *null*, **return 0** (or some other constant, but *0* is traditional).
        * If the field is an array, treat it as if each element were a separate field. That is, compute a hash code for
          each significant element by applying these rules recursively, and combine these values per step *2.b*. If
          every element in an array field is significant, you can use one of the *Arrays.hashCode* methods added in
          release 1.5.
    * Combine the hash code *c* computed in step 2.a into *result* as follows: **result = 31 * result + c;**
* Return *result*.
* When you are finished writing the *hashCode* method, ask yourself whether equal instances have equal hash codes. **
  Write unit tests to verify your inuition!** If equal instances have unequal hash codes, figure out why and fix the
  problem.

* you may exclude *redundant fields* from the hash code computation. In other words, you may ignore any field whose
  value can be computed from fields included in the computation.
* you *must* exclude any fields that are not used in *equals* comparisons, or you risk violating the second provision of
  the *hashCode* contract

#### hashCode Example

```Java
public final class PhoneNumber {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;
    
    // For the rest of the class [see Item 8](#item8)
    // ...

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + areaCode;
        result = 31 * result + prefix;
        result = 31 * result + lineNumber;
        return result;
    }
}
```

#### Lazily initialized, cached hashCode

```Java
private volatile int hashCode; // ([See Item 71](10_concurrency.md#item71))

@Override
public int hashCode() {
    int result = hashCode;
    if(result == 0) {
        int result = 17;
        result = 31 * result + areaCode;
        result = 31 * result + prefix;
        result = 31 * result + lineNumber;
    }
    return result;
}
```

**Do not be tempted to exclude significant parts of an object from the hash code computation to improve performance, as
it may degrade hash tables' performance to the point where they become unusably slow.**

---

### Item 10: Always override toString<a name="item10"></a>

* While it isn't as important as obeying the *equals* and *hashCode* contracts ([Item 8](#item8), [Item 9](#item9)), **
  providing a good *toString* implementation makes your class much more pleasant to use.**
* The *toString* method is automatically invoked when an object is passed to *println*, *printf*, the string
  concatenation operator, or *assert*, or printed by a debugger.
* When practical, the *toString* method should return *all* of the interesting information contained in the object.
* Wheter or not you decide to specify the format, you should clearly document your intentions - this is important in
  case the *toString* method of a class is used for special purposes in client clode (like parsing and similar things).
* Provide programmatic access to all of the information contained in the value returned by toString.

#### Example

```Java
/**
* Returns the string representation of this phone number.
* The string consists of fourteen characters whose format
* is "(XXX) YYY-ZZZZ", where XXX is the area code, YYY is
* the prefix, and ZZZZ is the line number. (Each of the
* capital letters represents a single decimal digit.)
*
* If any of the three parts of this phone number is too small
* to fill up its field, the field is padded with leading zeros.
* For example, if the value of the line number is 123, the last
* four characters of the string representation will be "0123".
*
* Note that there is a single space separating the closing
* parenthesis after the area code from the first digit of the
* prefix.
*/
@Override 
public String toString() {
    return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
}
```

---

### Item 11: Override clone judiciously<a name="item 11"></a>

The *Cloneable* interface was intended as a *mixin interface* ([Item 18](04_classes_and_interfaces.md#item18)) for
objects to advertise that they permit cloning. Unfortunately, it fails to serve this purpose. Its primary flaw is that
it lacks a *clone* method, and *Object*'s *clone* method is protected. You cannot, without resorting to *
reflection* ([Item 53](08_general_programming.md#item53)), invoke the *clone* method on an object merely because it
implements *Cloneable*. Even a reflective invocation may fail, as there is no guarantee that the object has an
accessible *clone* method. Despite this flaw and otehrs, the facility is in wide use so it pays to understand it. This
item tells you how to implement a well-behaved *cloen* method, discusses when it is appropriate to do so, and presents
alternatives.

#### What does *Cloneable* do?

*Cloneable* determines the behavior of *Object*'s protected *clone* implementation: if a class implements *Cloneable*, *
Object*'s *clone* method returns a field-by-field copy of the object; otherwise it throws *CloneNotSupportedException* -
this is a **highly atypical use of interfaces and not to be emulated**.

#### General contract

Creates and returns a copy of this object. The precise meaning of "copy" may depend on the class of the object. The
general intent is that, for any object x, the expression

**x.clone() != x**

will be *true*, and the expression

**x.clone().getClass() == x.getClass()**

will be *true*, but these are not absolute requirements. While it is typically the case that

**x.clone().equals(x)**

will be *true*, this is not an absolute requirement. Copying an object will typically entail creating a new instance of
its class, but **it may require copying of internal data structures as well**.

#### Discussion

* If you override the *clone* method in a nonfinal class, you should return an object obtained by invoking *super.clone*
  .
* In practice, a class that implements *Cloneable* is expected to provide a properly functioning public *clone* method.
* **Never make the client do anything the library can do for the client**
* **The *clone* architecture is incompatible with normal use of final fields referring to mutable objects**

#### Examples

**Simple case - only primitive types to be cloned**

```Java
@Override
public PhoneNumber clone() {
    try {
        return (PhoneNumber) super.clone();
    } catch(CloneNotSupportedException e) {
        throw new AssertionError(); // Can't happen
    }
}
```

**Not that simple case - internal data structures to be cloned I**

```Java
@Override
public Stack clone() {
    try {
        Strack result = (Stack) super.clone();
        result.elements = elements.clone();
        return result;
    } catch(CloneNotSupportedException e) {
        throw new AssertionError(); // Can't happen
    }
}
```

**Complex case - internal data structures to be cloned II**

In effect, the *clone* method functions as another constructor; you must ensure that it does no harm to the original
object and that it properly establishes invariants on the clone.

```Java
public class HashTable implements Cloneable {
    private Entry[] buckets = ...;

    // ...

    private static class Entry {
        // ...

        // Recursively copy the linked list headed by this Entry
        Entry deepCopy() {
            return new Entry(key, value, next == null ? null : next.deepCopy());
        }
    }

    @Override
    public HashTable clone() {
        try {
            HashTable result = (HashTable) super.clone();
            result.buckets = new Entry[buckets.length];
            for(int i = 0; i < buckets.length; i++)
                if(buckets[i] != null)
                    result.buckets[i] = buckets[i].deepCopy();
                return result;
        } catch(CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }
    // ... Remainder omitted
}
```

#### Alternatives

Is all this complxity really necessary? Rarely. If you extend a class that implements *Cloneable*, you have little
choice but to implement a well-behaved *clone* method. Otherwise, **you are better off providing an alternative means of
object copying, or simply not providing the capability** - for example, for immutable classes the support of object
copying does not make sense.

**A fine approach to object copying is to provide a *copy constructor* or *copy factory***

* a copy constructor is simply a constrctor that takes a single argument whose type is the class containing the
  constructor, for example,

```Java
        public Yum(Yum yum);
```

* a copy factory is the static factory analog of a copy constructor:

```Java
        public static Yum newInstance(Yum yum);
```

The copy constructor approach and its static factory variant have many advantages over *Cloneable/clone*: they don't
rely on a risk-prone extralinguistic object creation mechanism; they don't demand unenforceable adherence to thinly
documented conventions; they don't conflict with the proper use of final fields; they don't throw unnecessary checked
exceptions; and they don’t require casts.

---

### Item 12: Consider implementing Comparable<a name="item12"></a>

Unlike the other methods discussed in this chapter, the *compareTo* method is not declared in *Object*. Rather, it is
the sole method in the *Comparable* interface. It is similar in character to *Object*'s *equals* method, except that it
permits order comparisons in addition to simple equality comparisons, and it is generic. By implementing *Comparable*, a
class indicates that its instances have a *natural ordering*. Sorting an array of objects that implement *Comparable* is
as simple as this:

```Java
    Arrys.sort(a);
```

* By implementing *Comparable*, you allow your class to interoperate with all of the many generic algorithms and
  collection implementations that depend on this interface
* You gain a tremendous amount of power for a small amount of effort
* If you are writing a value class with an obvious natural ordering, such as alphabetical order, numerical order, or
  chronological order, you should strongly consider implementing the interface:

```Java
public interface Comparable<T> {
    int compareTo(T t);
}
```

#### General contract

Compares this object with the specified object for order. Returns a negative integer, zero, or a positive integer as
this object is less than, equal to, or greater than the specified object. Throws *ClassCastException* if the specified
object's type prevents it from being compared to this object.

(In the following description, the notation *sgn(expression)** designates the mathematical *signum* function, which is
defined to return -1, 0, or 1, according to whether the value of expression is negative, zero, or positive.)

* The implementor must ensure **sgn(x.compareTo(y)) == -sgn(y.compareTo(x))** for all *x* and *y*. (This implies that **
  x.compareTo(y)** must throw an exception if and only if **y.compareTo(x)** throws and exception.)
* The implementor must also ensure that the relation is transitive: **(x.compareTo(y) > 0 && y.compareTo(z) > 0)**
  implies **x.compareTo(z) > 0**.
* Finally, the implementor must ensure that **x.compareTo(y) == 0** implies that **sgn(x.compareTo(z)) == sgn(
  y.compareTo(z))**, for all *z*.
* It is strongly recommended, but not strictly required, that **(x.compareTo(y) == 0) == (x.equals(y))**. Generally
  speaking, any class that implements the *Comparable* interface and violates this condition should clearly indicate
  this fact. The recommended language ist *"Note: This class has a natural ordering that is inconsistent with equals."*

Just as a class that violates the *hashCode* contract can break other classes that depend on hashing, a class that
violates the *compareTo* contract can break other classes that depend on comparison. Classes that depend on comparison
include the sorted collections *TreeSet* and *TreeMap*, and the utility classes *Collections* and *Arrays*, which
contain searching and sorting algorithms.

#### Examples

* Because the *Comparable* interface is parameterized, the *compareTo* method is statically typed, so you don't need to
  type check or cast its argument. If the argument is of the wrong type, the invocation won't even compile.
* The field comparisons in a *compareTo* method are order comparisons rather than equality comparisons. Compare object
  reference fields by invoking the *compareTo* method recursively. If a field does not implement *Comparable*, or you
  need to use a nonstandard ordering, you can use an explicit *Comparator* instead. Either wirte your own, or use a
  preexisting one as in this *compareTo* method for the *CaseInsensitiveString* class in [Item 8](#item8).

```Java
public final class CaseInsensitiveString 
    implements Comparable<CaseInsensitiveString> {
        public int compareTo(CaseInsensitiveString cis) {
            return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
        }
        // ... Remainder omitted
}
```

* Compare integral primitive fields using the relational operators *<* and *>*.
* For floating-point fields, use *Double.compare* or *Float.compare* in place of the relational operators, which do not
  obey the general contract for *compareTo* when applied to floating point values.
* For array fields, apply these guidelines to each element.
* If a class has multiple significant fields, the order in which you compare them is critical.
* You must start with the most significant field and work your way down.
* If a comparison results in anything other than zero (which represents equality), you’re done; just return the result.
* If the most significant fields are equal, go on to compare the next-most-significant fields, and so on.
* If all fields are equal, the objects are equal; return zero.
* The technique is demonstrated by this *compareTo* method for the PhoneNumber class in [Item 9](#item9):

```Java
public int compareTo(PhoneNumber pn) {
    // Compare area codes
    if(areaCode < pn.areaCode)
        return -1;
    if(areaCode > pn.areaCode)
        return 1;

    // Area codes are equal, compare prefixes
    if(prefix < pn.prefix)
        return -1;
    if(prefix > pn.prefix)
        return 1;

    // Area codes and prefixes are equal, compare line numbers
    if(lineNumber < pn.lineNumber)
        return -1;
    if(lineNumber > pn.lineNumber)
        return 1;

    return 0; // All fields are equal
}
```
