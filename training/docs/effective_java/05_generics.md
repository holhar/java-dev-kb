# Effective Java

## 5. Generics

With generics, you tell the compiler what types of objects are permitted in each collection. The compiler inserts casts
four you automatically and tells you at compile time if you try to insert an object of the wrong type. This results in
programs that are both safer and clearer, but these benefits come with complications. This chapter tells you how to
maximize the benefits and minimize the complications.

---

### Item 23: Don't use raw types in new code<a name="item23"></a>

#### Definitions and Terms

| Term                    | Example                              | Item                   |
|-------------------------|--------------------------------------|------------------------|
| Parameterized type      | List\<String\>                       | Item 23                |
| Actual type parameter   | String                               | Item 23                |
| Generic type            | List\<E\>                            | Item 23, [26](#item26) |
| Formal type parameter   | E                                    | Item 23                |
| Unbounded wildcard type | List\<?\>                            | Item 23                |
| Raw type                | List                                 | Item 23                |
| Bounded type parameter  | \<E extends Number\>                 | [Item 26](#item26)     |
| Recursive type bound    | \<T extends Comparable\<T\>\>        | [Item 27](#item27)     |
| Bounded wildcard type   | List\<? extends Number\>             | [Item 28](#item28)     |
| Generic method          | static \<E\> List\<E\> asList(E[] a) | [Item 27](#item27)     |
| Type token              | String.class                         | [Item 29](#item29)     |

* a class or interface whose declaration has one or more **formal type parameters** is a **generic** class or
  interface ([JLS, 8.1.2](https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.1.2)
  and [JLS, 9.2.1](https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.2))
    - example: *List\<E\>* (read "list of E")
    - generic classes and interfaces are collectively known as **generic types**
* each generic type defines a set of **parameterized types**, which consist of the class or interface name followed by
  an angle-bracketed list of **actual type parameters** corresponding to the generic type's formal type
  parameters ([JLS, 4.4](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.4)
  and [JLS, 4.5](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.5))
    - example: *List\<String\>* (read "list of string") is a parameterized type representing a list whose elements are
      of type *String*
    - *String* ist the *actual type parameter* corresponding to the *formal type parameter* *E*
* each generic type defines a **raw type**, which is the name of the generic type used without any accompanying actual
  type parameters ([JLS, 4.8](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.8))
    - example: the *raw type* corresponding to *List\<E\>* is *List*
    - raw types behave as if all of the generic type information were erased from the type declaration => **behaviour in
      the same way as before generics were added to the platform**

##### raw type example prior to release 1.5

```Java
// raw collection type - don't do this!

/**
 * My stamp collection. Contains only Stamp instances.
 */
private final Collection stamp = ... ;
```

If you accidentally put a coin into your stamp collection, the erroneous insertion compiles and runs without error:

```Java
// Erroneous insertion of coin into stamp collection
stamps.add(new Coin(...));
```

You don't get an error until you retrieve the coin from the stamp collection:

```Java
// Now a raw iterator type - don't do this!
for(Iterator i = stamps.iterator(); i.hasNext(); ) {
    Stamp s = (Stamp) i.next(); // Throws ClassCastException
    ... // do something with the stamp
}
```

Error is discovered at runtime, not compile time - **we want to fail fast, it is much harder to track the error at
runtime and the compiler can't help with that!**

##### Generics example

```Java
// Parameterized collection type - typesafe
private final Collection<Stamp> stamps = ... ;
```

Now the compiler knows that *stamps* should contain only *Stamp* instances and **guarantees** this to be the case,
assuming your entire code base is compiled with a compiler from release 1.5 or later and all the code compiles without
emitting (or suppressing; see [Item 24](#item24)) any warnings. When *stamps* is declared with a parameterized type, the
errorneous insertion generates a compile-time error message that tells you exactly what is wrong:

```Java
Tehs.java:9 add(Stamp) in Collection<Stamp> cannot be applied to (Coin)
    stamps.add(new Coin());
```

**Manual casts are no longer required**

#### Discussion

##### Example 1

* **If you use raw types, you lose all the safety and expressiveness benefits of generics.**
* raw types are still provided for compatibility - *migration compatibility
* **you lose type safety if you use a raw type like *List*, but not if you use a parameterized type lie *
  List\<Object\>***:

```Java
// Uses raw type (List) - fails at runtime!
public static void main(String[] args) {
    List<String> strings = new ArrayList<String>();
    unsafeAdd(strings, new Integer(42));
    String s = strings.get(0); // Compiler,generated cast
}

private static void unsafeAdd(List list, Object o) {
    list.add(o);
}
```

This program compiles, but because it uses the raw type *List*, you get a warning:

```Java
Test.java:10: warning: unchecked call to add(E) in raw type List list.add(0);
```

If you run the program, you get a *ClassCastException* when the program tries to cast the result of the invocation *
strings.get(0)* to a *String*. If you replace the raw type *List* with the parameterized type *List\<Object\>* in the *
unsafeAdd* declaration and tryp to recompile the program, you'll find that it no longer compiles:

```Java
Test.java:5: unsafeAdd(List<Object>, Object) cannot be applied to (List<String>, Integer)
    unsafeAdd(strings, new Integer(42));
```

##### Example 2 - unbounded wildcard types

* You might be tempted to use a raw type for a collection whose element type is unknown and doesn’t matter
* For example, suppose you want to write a method that takes two sets and returns the number of elements they have in
  common.
* Here’s how you might write such a method if you were new to generics:

```Java
// Use of raw type for unknown element type - don't do this!
static int numElementsInCommon(Set s1, Set s2) {
    int result = 0;
    for (Object o1 : s1)
        if (s2.contains(o1))
            result++;
    return result;
}
```

* This method works but it uses raw types, which are dangerous.
* Since release 1.5, Java has provided a safe alternative known as **unbounded wildcard types**.
* If you want to use a generic type but you don’t know or care what the actual type parameter is, you can use a question
  mark instead.
* For example, the unbounded wildcard type for the generic type Set\<E\> is Set\<?\> (read “set of some type”)
* It is the most general parameterized *Set* type, capable of holding any set.
* Here is how the numElementsInCommon method looks with unbounded wildcard types:

```Java
// Unbounded wildcard type - typesafe and flexible
static int numElementsInCommon(Set<?> s1, Set<?> s2) {
    int result = 0;
    for (Object o1 : s1)
        if (s2.contains(o1))
            result++;
    return result;
}
```

* the wildcard type is safe and the raw type isn't
* you can put any element into a collection with a raw type, easily corrupting the collection's type invariant
* **you can't put any element (other than *null*) into a *Collection<?>*** - attempting to do so will generate a
  compile-time error message
* Not only can’t you put any element (other than *null*) into a Collection\<?\>, but you can’t assume anything about the
  type of the objects that you get out.
* If these restrictions are unacceptable, you can use **generic methods** ([Item 27](#item27)) or **bounded wildcard
  types** ([Item 28](#item28)).

##### Example 3 - minor exceptions to the rule that you should not use raw types in new code

* both rules stem from the fact **that generic type information is erased at runtime** ([Item 25](#item25))

* *Exception 1*: **you must use raw types in class literals**
    - The specification does not permit the use of parameterized types (though it does permit array types and primitive
      types) ([JLS, 15.8.2](https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.8.2))
    - In other words, *List.class*, *String[].class*, and *int.class* are all legal, but *List<String>.class* and *List<
      ?>.class* are not

* *Exception 2*: concerns *instanceof* operator
    - Because generic type information is erased at runtime, it is illegal to use the *instanceof* operator on
      parameterized types other than unbounded wildcard types
    - The use of unbounded wildcard types in place of raw types does not affect the behavior of the instanceof operator
      in any way
    - In this case, the angle brackets and question marks are just noise
    - This is the preferred way to use the instanceof operator with generic types:

```Java
// Legitimate use of raw type - instanceof operator
if (o instanceof Set) { // Raw type
    Set<?> m = (Set<?>) o; // Wildcard type
...
}
```

Note that once you’ve determined that *o* is a *Set*, you must cast it to the wildcard type *Set\<?\>*, not the raw
type *Set*. This is a checked cast, so it will not cause a compiler warning.

#### Summary

* In summary, using raw types can lead to exceptions at runtime, so don’t use them in new code.
* They are provided only for compatibility and interoperability with legacy code that predates the introduction of
  generics.
* As a quick review:
    * *Set\<Object\>* is a parameterized type representing a set that can contain objects of any type,
    * Set\<?\> is a wildcard type representing a set that can contain only objects of some unknown type,
    * and Set is a raw type, which opts out of the generic type system. The first two are safe and the last is not.

---

### Item 24: Eliminate unchecked warnings<a name="item24"></a>

* **Eliminate every unchecked warning that you can.**
* if you eliminate all warnings, you are assured that your code is typesafe, which is a very good thing - **no *
  ClassCastExceptions* are possible**.
* **If you can't eliminate a warning, and you can prove that the code that provoked the warning is typesafe, then (and
  only then) suppress the warning with an @SuppressWarnings("unchecked") annotation.**
    - If you ignore unchecked warnings that you know to be safe (instead of suppressing them), you won't notice when a
      new warning crops up that represents a real problem.
* **Always use the *SuppressWarnings* annotation on the smallest scope possible**
    - Never use *SuppressWarnings* on an entire class - doing so could mask critical warnings
    - If you find yourself using the SuppressWarnings annotation on a method or constructor that’s more than one line
      long, you may be able to move it onto a local variable declaration
    - You may have to declare a new local variable, but it’s worth it.
* **Every time you use an @SuppressWarnings("unchecked") annotation, add a comment saying why it's safe to do so**

---

### Item 25: Prefer lists to arrays<a name="item25"></a>

* two important differences between arrays and generic types:
*
    1. difference:

    - **arrays are covariant**:
        + if *Sub* is a subtype of *Super*, then the array type *Sub[]* is a subtype of *Super[]*
    - **generics are invariant**:
        + for any two distinct types *Type1* and *Type2*, *List\<Type1\>* is neither a subtype nor a supertype of *
          List\<Type2\>* ([JLS, 4.10](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.10))
    - Example:

```Java
// Fails at runtime but not at compile time!
Object[] objectArray = new Long[1];
objectArray[0] = "I don't fit in!"; //Throws ArrayStoreException

// Won't compile!
List<Object> ol = new ArrayList<Long>(); // Incompatible types
ol.add("I don't fit in!");
```

*
    2. difference:

    - **arrays are reified** ([JLS, 4.7](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.7)):
        + arrays know and enforce their element types at runtime
    - **generic are implemented by type erasure (they are
      non-reifiable)** ([JLS, 4.7](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.7)):
        + they enforce their type constraints only at compile-time and discard (or *erase*) their element type
          information at runtime
        + erasure is what allows generic types to interoperate freely with legacy code that does not use
          generics ([Item 23](#item23))
        + the only parameterized types that are reifiable are unbounded wildcard types such as *List\<?\>* and *Map\<?,
          ?\>* ([Item 23](#item23))
        + it is legal, though infrequently useful, to create arrays of unbounded wildcard types

* because of these differences, arrays and generics do not mix well
* example: it is illegal to create an array of a generic type, a parameterized type, or a type parameter
    - this is illegal because it isn't typesafe
    - it it were legal, casts generated by the compiler in anotherwise correct program could fail at runtime with a *
      ClassCastException*
    - example:

```Java
// Why generic array creation is illegal - won't compile!
List<String>[] stringLists = new List<String>[1];   // (1)
List<Integer> intList = Arrays.asList(42);          // (2)
Object[] objects = stringLists;                     // (3)
objects[0] = intList;                               // (4)
String s = stringLists[0].get(0);                   // (5)
```

* let's pretend that line 1, which creates a generic array, is legal
* line 2 creates and initializes a *List\<Integer\>* containing a single element
* line 3 stores the *List\<String\>* array into an *Object* array variable, which is legal because arrays are covariant
* line 4 stores the *List\<Integer\>* into the sole element of the *Object* array, which succeeds because generics are
  implemented by erasure:
    - the runtime type of a *List\<Integer\>* instance is simpliy *List*, adn the runtime type of a *List\<String\>[]*
      instance is *List[]*, so this assignment doesn't generate an *ArrayStoreException*
* now we're in trouble:
    - we've stored a *List\<Integer\>* instance into an array that is decalred to hold only *List\<String\>* instances
* in line 5 we retrieve the sole element from the sole list in this array
    - the compiler automatically casts the retrieved element to *String*, but it's an *Integer*, so we get a *
      ClassCastException* at runtime
    - in order to prevent this from happening, line 1 (which creates a generic array) generates a compile-time error

#### Results of the prohibition on generic array creation

* it's not generally possible for a generic type to return an array of its element type (see [Item 29](#item29) for a
  partial solution)
* you can get confusing warnings when using varargs methods([Item 42](#item42)) in combination with generic types
    - this is because every time you invoke a varargs method, an array is created to hold the varargs parameters
    - if the element type of this array is not reifiable, you get a warning
    - there is little you can do about these warnings other than to suppress them ([Item 24](#item24)), and to avoid
      mixing generics and varargs in your APIs

#### How to handle generic array creation errors (including exhaustive example)

* when you get a generic array creation error, the best solution is often to use the collection type *List\<E\>* in
  preference to array type *E[]*
* you might sacrifice some performance or conciseness, but in exchange you get better type safety and interoperability

##### Example

* suppose you have a synchronized list (of the sort returned by *Collections.synchronizedList*) and a function that
  takes two values of the type held by the list and returns a thirs
* now suppose you want to write a method to "reduce" the list by applying the function accros it
    - for example:
        - if the list contains integers and the function adds two integer values, the *reduce* method returns the sum of
          all the values in the list
        - if the function multiplies two integer values, the method return the product of the values in the list
        - if the list contains strings and the function concatendates two strings, the method returns a string
          consisting of all the strings in the list in sequence
    - in addition to a list and a function, the *reduce* method takes an initial value for the reduction, which is
      returned if the list is empty (the initial value is typically the identity element for the function, which is 0
      for addition, 1 for multiplication, and "" for string concatenation)
    - here's how the code might have looked without generics:

```Java
// Reduction without generics, and with concurrency flaw!
static Object reduce(List list,Function f,Object initVal){
synchronized(list){
        Object result=initVal;
        for(Object o:list)
        result=f.apply(result,o);
        return result;
        }
        }

interface Function {
    Object apply(Object arg1, Object arg2);
}
```

* suppose you've read [Item 67](10_concurrency.md#item67), which tells you not to call "alien methods" from a
  synchronized region
    - so, you modify the *reduce* method to copy the contents of the list while holding the lock, which allows you to
      perform the reduction on the copy
    - prior to release 1.5, the natural way to do this would have been using *List*'s *toArray* method (which locks the
      list internally):

```Java
// Reduction without generics or concurrency flaw
static Object reduce(List list,Function f,Object initVal){
        Object[]snapshot=list.toArray(); // Locks list internally
        Object result=initVal;
        for(Object o:list)
        result=f.apply(result,o);
        return result;
        }
```

* If you try to do this with generics you'll get into trouble of the sort that we discussed above
    - here's a generic version of the *Function* interface:

```Java
interface Function<T> {
    T apply(T arg1, T arg2);
}
```

* And now a noive attempt to apply generics to the revised version of the *reduce* method - it's a **generic
  mehod** ([Item 27](#item27)):

```Java
// Naive generic version of reduction - won't compile!
static<E> E reduce(List<E> list,Function<E> f,E initVal){
        E[]snapshot=list.toArray(); // Locks list
        E result=initVal;
        for(E e:snapshot)
        result=f.apply(result,e);
        return result;
        }
```

* If you try to compile this method, you'll get a **incompatible types error**, because of the line:
    - ```E[] snapshot = list.toArray();```
* No big deal, you say, I'll cast the *Object* array to an *E* array:
    - ```E[] snapshot = (E[]) list.toArray();```
* That gets rid of the rror, but now you get a **unchecked cast warning**
    - the compiler is telling you that it can't check the safety of the cast at runtime because it doesn't know what *E*
      is at runtime (because of the type erasure)
    - the program will work, but it is not safe:
        - the compile-time type of *snapshot* is E[] which could be *String[]*, *Integer[]*, or any other kind of array
        - the runtime type is *Object[]*, and that's dangerous
    - casts to arrays of non-reifiable types should be used only under special circumstances ([Item 26](#item26))
* So what should you do? Use a list instead of an array.
    - Here is a version of the *reduce* method that compiles without error or warning:

```Java
// List-based generic reduction
static<E> E reduce(List<E> list,Function<E> f,E initVal){
        List<E> snapshot;
synchronized(list){
        snapshot=new ArrayList<E>(list);
        }
        E result=initVal;
        for(E e:snapshot)
        result=f.apply(result,e);
        return result;
        }
```

#### Summary

* arrays and generics have very different type rules
* arrays are covariant and reified; generics are invariant and erased
* as a consequence, arrays provide runtime type safety but not compile-time type safety and vice versa for generics
* generally speaking, arrays and generics don’t mix well
* if you find yourself mixing them and getting compile-time errors or warnings, your first impulse should be to replace
  the arrays with lists

---

### Item 26: Favor generic types<a name="item26"></a>

* Consider the simple stack implementation from [Item 6](Eliminate obsolete object references) which is a prime
  candidate for *generification*

```Java
// Object-based collection - a prime candidate for generics
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if(elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
```

* As this class stands, you have to cast objects that are popped off the stack, and those casts might fail at runtime
* **steps generifying a class**
    - add one or more type parameters to its declaration
        - in this case one type parameter, representing the element type of the stack, and the conventional name for
          this parameter is *E* ([Item 44](07_methods.md#item44))
    - replace all the uses of the type *Object* with the appropriate type parameter, and then try to compile the
      resulting program:

```Java
// Initial attempt to generify Stack = won't compile!
public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new E[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
    ... // no changes in isEmpty or ensureCapacity
}
```

* Subsequently, this class will produce one error at this stage:

```Java
Stack.java:8: generic array creation
    elements = new E[DEFAULT_INITIAL_CAPACITY];
```

* **you can't create an array of a non-reifiable type**, such as *E* (see [Item 25](#item25))
* this problem arises every time you write a generic type that is backed by an array
* there are two ways to solve this:
    - **first solution**:
        + create an array of *Object* and cast it to the generic array type
        + this solution directly circumvents the prohibition on generic array creation
        + no in place of an error, the compiler will emit a warning
        + this usage is legal, but it's not (in general)
          typesafe: ```elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];``` <= *warning: unchecked cast*
        + the compiler may not be able to prove that your program is typesafe, but you can; you must convince yourself
          that the unchecked cast will not compromise the type safety of the program
            * the array in question (*elements*) is stored in a private field and never returned to the client or passed
              to any other method
            * the only elements stored in the array are those passed to the *push* method, which are of type *E*, so the
              unchecked cast can do no harm
        + once you've proved that an unchecked cast is safe, suppress the warning in as narrow a scope as
          possible ([Item 24](#item24))
        + in this case, the constructor contains only the unchecked array creation, so it's appropriate to suppress the
          warning in the entire constructor
        + with the addition of an annotation to do this, *Stack* compiles cleanly and you can ust it without explicit
          casts or fear of a *ClassCastException*:

```Java
// The element array will contain only E instances from push(E).
// This is sufficient to ensure type safety, but the runtime
// type of the array won't be E[], it will always be Object[]!
@SuppressWarnings("unchecked")
public Stack() {
    elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
}
```

* remember, there are two ways to solve this:
    - **second solution**:
        + change the type of the field *elements* from *E[]* to *Object[]*
        + if you do this you get a *incompatible types error* for ```E result = elements[--size];``
        + you can change this error into a warning by casting the element retrieved from the array from *Object* to *
          E*: ```E result = (E) elements[--size];``` <= *warning: unchecked cast*
            * as *E* is a non-reifiable type, there's no way the compiler can check the cast at runtime, but you can
              again prove it to yourself
            * we suppress the warning in the smallest scope possible ([Item 24](#item24))

```Java
// Appropriate suppression of unchecked warning
public E pop() {
    if(size == 0)
        throw new EmptyStackException();

    // push requires elements to be of type E, so cast is correct
    @SuppressWarning("unchecked")
    E result = (E) elements[--size];

    elements[size] = null; //Eliminate obsolete reference
    return result;
}
```

* which of the two techniques you choose for dealing with the generic array creation error is largely a matter of taste,
  but:
    - it is riskier to suppress an unchecked cast to an array type than to a scalar type, which would suggest the second
      solution
    - But in a more realistic generic class than *Stack*, you would probably be reading from the array at many points in
      the code, so choosing the second solution would require many casts to *E* rather than a single cast to *E[]*, **
      which is why the first solution is used more commonly**
* Example program that uses the generic *Stack* class:

```Java
// Little program to exercise our generic Stack
public static void main(String[] args) {
    Stack<String> stack = new Stack<>();
    for(String arg : args)
        stack.push(arg);
    while(!stack.isEmpty())
        LOGGER.debug(stack.pop().toUpperCast());
}
```

**The foregoing example may appear to contradict [Item 25](#item25), which encourages the use of lists in preference to
arrays. It is not always possible or desirable to use lists inside your generic types. Java doesn’t support lists
natively, so some generic types, such as ArrayList, must be implemented atop arrays. Other generic types, such as
HashMap, are implemented atop arrays for performance.**

#### Restrict generic type parameters

* The great majority of generic types are like our *Stack* example in that their type parameters have no restrictions
* There are some generic types that restrict the permissible values of their type parameters
    - example - *java.util.concurrent.DelayQueue*: ```class DelayQueue\<E extends Delayed\> implements
      BlockingQueue\<E\>;`` <= *bounded type parameter*
    - note that the subtype relation is defined so that every type is a subtype of
      itself ([JLS, 4.10](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.10)), so it is legal to
      create a ```DelayQueue\<Delayed\>```.

#### Summary

* generic types are safer and easier to use than types that require casts in client code
* when you design new types, make sure that they can be used without such casts
* this will often mean making the types generic
* generify your existing types as time permits
* this will make life easier for new users of these types without breaking existing clients ([Item 23](#item23))

---

### Item 27: Favor generic methods<a name="item27"></a>

* just as classes can benefit from generification, so can methods
* static utility methods are particularly good candidates for generification
* sll of the "algorithm" methods in *Collections* (such as *binarySearch* and *sort*) have been generified

```Java
// Uses raw types - unacceptable! (Item 23)
public static Set union(Set s1, Set s2) {
    Set result = new HashSet(s1);
    result.addAll(s2);
    return result;
}
```

* this method compiles, but with two warnings:

```Java
Union.java:5: warning: [unchecked] unchecked call to
HashSet(Collection<? extends E>) as a member of raw type HashSet
Set result = new HashSet(s1);

Union.java:6: warning: [unchecked] unchecked call to
addAll(Collection<? extends E>) as a member of raw type Set
result.addAll(s2);
```

* to fix these warnings and make the method typesave, modify the method declaration to declare a type parameter
  representing the element type for the three sets and use the type parameter in the method
* **the *type parameter list*, which declares the type parameter, goes between the method's modifiers and its return
  type**

```Java
// Generic method
public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
    Set<E> result = new HashSet<E>(s1);
    result.addAll(s2);
    return result;
}
```

* At least for simple generic methods, that’s all there is to it
* now the method compiles without generating any warnings and provides type safety as well as ease of use
* here's a simple program to exercise our method - the program contains no casts and compiles without errors or
  warnings:

```Java
// Simple program to exercise generic method
public static void main(String[] args) {
    Set<String> guys = new HashSet<String>(
        Arrays.asList("Tom", "Dick", "Harray"));
    Set<String> stooges = new HashSet<String>(
        Arrays.asList("Larry", "Moe", "Curly"));
    Set<String> aflCio = union(guys, stooges);
    LOGGER.debug(aflCio);
}
```

* a limitation of the *union* method is that the types of all three sets have to be the same
* you can make the method more flexible by using *bounded wildcard types* ([Item 28](#item28))
* you don't need to specify the value of the type parameter explicitly as you must when invoking generic
  constructors (**Pattern no longer necessary sind Java 7**)
    - the compiler figures out the value of the type parameters by examining the types of the method arguments => **type
      inference**

#### Related Patterns

##### Avoid redundant type parameter values for generic constructors

* **Pattern no longer necessary sind Java 7**

##### Generic singleton factory

* sometimes you need to create an object that is immutable but applicable to many different types
* because generics are implemented by erasure ([Item 25](#item25)), you can use a single object for all required type
  parameterizations, but you need to write a static factory method to repeatedly dole out the object for each requested
  type parameterization
* this pattern is most frequently used for function objects ([Item 21](04_classes_and_interfaces.md#item21)) such as *
  Collection.reverseOrder*, but it is alos used for collections such as *Cellections.emptySet*
* suppose you have an interface that describes a function that accepts and returns a value of some type *T*:

```Java
public interface UnaryFunction<T> {
    T apply(T arg);
}
```

* now suppose that you want to provide an identity function
    - it would be wasteful to create a new one each time it's required, as it's stateless
    - if generics were reified, you would need one identity function per type, but since they're erased you need only a
      generic singleton
    - here's how it looks:

```Java
// Generic singleton factoy pattern
private static UnaryFunction<Object> IDENTITY_FUNCTION=
        new UnaryFunction<Object>(){
public Object apply(Object arg){return arg;}
        }

// IDENTITY_FUNCTION is stateless and its type parameter is
// unbounded so it's safe to share one instance across all types.
@SuppressWarnins("unchecked")
public static<T> UnaryFunction<T> identityFunction(){
        return(UnaryFunction<T>)IDENTITY_FUNCTION;
        }
```

* the cast of *IDENTITY_FUNCTION* to *(UnaryFunction\<T\>)* generates an unchecked cast warning, as *
  UnaryFunction\<Object\>* is not a *UnaryFunction\<T\>* for every *T*
* but the identity function is special: it returns its argument unmodified, so we know that it is typesafe to use it as
  a *UnaryFunction\<T\>* whatever the value of *T*
* therefore, we can confidently suppress the unchecked cast warning that is generated by this cast
* once we've done this, the code compiles without error or warning

* Here is a sample program that uses our generic singleton asa *UnaryFunction\<String\>* and a *UnaryFunction\<Number\>
    - as usual, it contains no casts and compiles without errors or warnings:

```Java
// Sample program to exercise generic singleton
public static void main(String[]args){
        String[]strings={"jute","hemp","nylon"};
        UnaryFunction<String> sameString=identityFunction();
        for(String s:strings)
        LOGGER.debug(sameString.apply(s));

        Number[]numbers={1,2.0,2L};
        UnaryFunction<Number> sameNumber=identityFunction();
        for(Number n:numbers)
        LOGGER.debug(sameNumber.apply(n));
        }
```

##### Recursive type bound

* it is permissible, though relatively rare, for a type parameter to be bounded by some expression involving that type
  parameter itself
* this is what’s known as a **recursive type bound**
* the most common use of recursive type bounds is in connection with the Comparable interface, which defines a type’s
  natural ordering:

```Java
public interface Comparable<T> {
    int compareTo(T o);
}
```

* the type parameter *T* defines the type to which elements of the type implementing *Comparable\<T\>* can be compared
* in practice, nearly all types can be compared only to elements of their own type
* there are many methods that take a list of elements that implement *Comparable*, in order to sort the list, search
  within it, calculate its minimum or maximum, and the like
* to do any of these things, it is required that every element in the list is comparable to every other element in the
  list, in other words, that the elements of the list are *mutually comparable* (*gegenseitig vergleichbar*)
* here is how to express that constraint:

```Java
// Using a recursive type bound to express mutual comparability
public static <T extends Comparable<T>> T max(List<T> list) { ... }
```

* the type bound *\<T extends Comparable\<T\>\>* may be read as "for every type *T* that can be compared to itself,"
  which corresponds more or less exactly to the notion of **mutual comparability**.
    - here is a method to got with the declaration above - it calculates the maximum value of a list according to its
      elements' natural order, and it compiles without errors or warnings:

```Java
// Returns the maximum value in a list - uses recursive type bound
public static <T extends Comparable<T>> T max(List<T> list) {
    Iterator<T> i = list.iterator();
    T result = i.next();
    while(i.hasNext()) {
        T t = i.next();
        if(t.compareTo(result) > 0)
            result = t;
    }
    return result;
}
```

#### Summary

* generic methods, like generic types, are safer and easier to use than methods that require their clients to cast input
  parameters and return values
* like types, you should make sure that your new methods can be used without casts, which will often mean making them
  generic and like types, you should generify your existing methods to make life easier for new users without breaking
  existing clients ([Item 23](#item23)).

---

### Item 28: Use bounded wildcards to increase API flexibility<a name="item28"></a>

* as noted in [Item 25](#item25), parameterized types are *invariant*, but sometimes more flexibility is needed than
  invariant typing can provide
* consider the *Stack* class from [Item 26](#item26), here is its public API:

```Java
public class Stack<E> {
    public Stack();
    public void push(E e);
    public E pop();
    public boolean isEmpty();
}
```

#### PECS - Producer-Extends, Consumer-super

* PECS a.k.a. **Get and Put Principle**
* **for maximum flexibility, use wildcard types on input parameters that represent producers or consumers**
* if an input parameter is both a producer and a consumer, then wildcard types will do you no good: you need an exact
  type match, which is what you get without any wildcards

##### Producer-Extends

* Suppose we want to add a method that takes a sequence of elements and pushes them all onto the stack - here's a first
  attempt:

```Java
// pushAll method without wildcard type - deficient!
public void pushAll(Iterable<E> src) {
    for (E e : src)
        push(e);
}
```

* Suppose you have a *Stack\<Number\>* and you invoke *push(intVal)*, where *intVal* is of type *Integer*
    - this works, because *Integer* is a subtype of *Number*
    - So logically, it seems that this should work, too:

```Java
Stack<Number> numberStack = new Stack<>();
Iterable<Interger> integers = ...;
numberStack.pushAll(integers);
```

* if you try it, however, you'll get this erro message because, as noted above, parameterized types are invariant
    - luckily, there's a way out: **bounded wildcard type**s can deal with situations like this
    - the type of the input parameter to *pushAll* should not be *Iterable of E* but *Iterable of some subtype of E*,
      which is expressed by: **Iterable<? extends E>**
        + (the use of the keyword *extends* is slighty misleading: recall from [Item 26](#item26) that *subtype* is
          defined so that every type is a subtype of itself, even though it does not extend itself)
    - let's modify *pushAll* to use this type:

```Java
// Wildcard type for parameter that serves as an E producer
public void pushAll(Iterable<? extends E> src) {
    for (E e : src)
        push(e);
}
```

* now the *Stack* class and the client code compile cleanly!

##### Consumer-Super

* now suppose you want to write a *popAll* method to go with *pushAll*
* the *popAll* method pops each element off the stack and adds the elements to the given collection
    - here's how a first attempt at writing the *popAll* method might look:

```Java
// popAll method without wildcard type - deficient!
public void popAll(Collection<E> dst) {
    while (!isEmpty())
        dst.add(pop());
}
```

* again, this compiles cleanly and works fine if the element type of the destination collection exactly mathces that of
  the stack
    - but again, it doesn't seem entirely satisfactory
* suppose you have a *Stack\<Number\>* and variable of type *Object*
    - if you pop an element from the stack and store it in the variable, it compiles and runs without error
    - so shouldn't you be able to do this, too?

```Java
Stack<Number> numberStack = new Stack<>();
Collection<Object> objects = ...;
numberStack.popAll(objects);
```

* if you try to compile this, you get an error again: *Collection\<Object\>* is not a subtype of *Collection\<Number\>*
    - again, wildcard types provide a way out
* the type of the input parameter to *popAll* should not be *collection of E* but *collection of some supertype of E* (
  where supertype is defined such that E is a supertype of
  itself ([JLS, 4.10](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.10)))
    - again, there's a wildcard type that means precisely that: *Collection\<? super E\>* => let's modify *popAll* to
      use it; with this change, both *Stack* and the client code compile cleanly; with the following change, both *
      Stack* and the client code compile cleanly:

```Java
// Wildcard type for parameter that serves as an E consumer
public void popAll(Collection<? super E> dst) {
    while(!isEmpty()) {
        dst.add(pop());
    }
}
```

* if a parameterized type represents a *T producer*, use **\<? extends T\>**; if it represents a *T consumer*, use **\<?
  super T\>**
* in our *Stack* example, *pushAll*'s *src* parameter produces *E* instances for use by the *Stack*, so the appropriate
  type for *src* is *Iterable\<? extends E\>*
* *popAll*'s *dst* parameter consumes *E* instances from the *Stack*, so the appropriate type for *dst* is *
  Collection\<? super E\>*

#### Further considerations (for some examples see book)

##### Return Types

* **Do not use wildcard types as return types** => rather than providing additional flexibility for your users, it would
  force them to use wildcard types in client code
* **If the user of a class has to think about wildcard types, there is probably something wrong with the class's API**

##### Type Inference

* Unfortunately, the type inference rules are quite
  complex ([JLS, 15.12.2.7-8](https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.12.4.2))
* sometimes they cause trouble, like here:

```Java
public static <E> Set<E> union(Set<? extends E> s1,
                                Set<? extends E> s2)

// Client code - if you try something like this,
// you get an incompatible types error
Set<Integer> integer = ...;
Set<Double> doubles = ...;
Set<Number> numbers = union(integers, doubles);
```

* Luckily, there's a way out of this:
    - if the compiler doesn't infer the type that you wish it had, you can tell it what type to use with an **explicit
      type parameter**:

```Java
Set<Number> numbers = Union.<Number>union(integers, doubles);
```

##### Dealing with Comparable

* let's turn our attention to the *max* method from [Item 27](#item27) - here's the original declaration:

```Java
public static <T extends Comparable<T>> T max(List<T> list)
```

* Here's a revised declaration that uses wildcard types:

```Java
public static <T extends Comparable<? super T>> T max(List<? extends T> list)
```

* to get the revised declaration from the original one, we apply the PECS transformation twice:
* *the parameter list*:
    - it produces *T* instances, so we change the type from *List\<T\>* to *List\<? extends T\>*
* *the type parameter T*:
    - the first time we see a wildcard applied to a type parameter
    - *T* was originally specified to extend *Comparable\<T\>*, but a comparable of *T* consumes *T* instances (and
      produces integers indicating order relations)
    - therfore the parameterized type *Comparable\<T\>* is replaced by the bounded wildcard type *Comparable\<? super
      T\>*
    - Comparables are always consumers, so you should **always use *Comparable\<? super T\>* in preference to *
      Comparable\<T\>***
    - the same is true of comparators, so you should **always use *Comparator\<? super T\>* in preference to *
      Comparator\<T\>***
* as a consequence, the revised *max* method looks like this:

```Java
public static <T extends Comparable<? super T>> T max(
    List<? extends T> list) {
        Iterator<? extends T> i = list.iterator();
        T result = i.next();
        while(i.hasNext()) {
            T t = i.next();
            if(t.compareTo(result) > 0)
                result = t;
        }
        return result;
}
```

* note that the body of the method needs a change, too: ```Iterator<? extends T> i = list.iterator();```
    - the elements returned by the iterator's *next* method are of some subtype of *T*, so they can be safely stored in
      a variable of type *T*
* the method declaration is very compley - what does is bring?
    - here's a simple example of a list that would be excluded by the original declaration but is permitted by the
      revised one:

```Java
List<ScheduledFuture<?>> scheduledFutures = ...;
```

* The reason that you can’t apply the original method declaration to this list is that *
  java.util.concurrent.ScheduledFuture* does not implement *Comparable\<ScheduledFuture\>*
* Instead, it is a subinterface of *Delayed*, which extends *Comparable\<Delayed\>*
* In other words, a *ScheduledFuture* instance isn’t merely comparable to other *ScheduledFuture* instances; it’s
  comparable to any *Delayed* instance, and that’s enough to cause the original declaration to reject it

##### Duality between type parameters and wildcards

* many methods can be declared using one or the other
* here are two possible declarations for a static method to swap two indexed items in a list
* the first uses an unbounded type parameter ([Item 27](#item27)) and the second an unbounded wildcard:

```Java
// Two possible declarations for the swap method
public static <E> void swap(List<E> list, int i, int j);
public static void swap(List<?> list, int i, int j);
```

* Which of these two declarations is preferable, and why?
    - in a public API, the second is better because it's simpler
    - you pass in a list - any list - and the method swaps the indexed elements (there is no type parameter to worry
      about)
    - **if a type parameter appears only once in a method declaration, replace it with a wildcard**
        + if it's an unbounded type parameter, replace it with an unbounded wildcard
        + if it's a bounded type parameter, replace it with a bounded wildcard

* the problem with the second declaration for *swap* is, that it does not compile immediately:

```Java
public static void swap(List<?> list, int i, int j) {
    list.set(i, list.set(j, list.get(i)));
}
```

* it doesn't seem right that we can't put an element back into the list that we just took it out of
* the problem is that the type of *list* is *List\<?\>*, and you can't put any value except *null* into a *List\<?\>*
* a solution is to write a private helper method to *capture* the widlcard type

```Java
public static void swap(List<?> list, int i, int j) {
    swapHelper(list, i, j);
}

// Private helper method for wildcard capture
private static <E> void swapHelper(List<E> list, int i, int j) {
    list.set(i, list.set(j, list.get(i)));
}
```

* this allows us to export the nice wildcard-based declaration of *swap*, while taking advantage of the more complex
  generic method internally

#### Summary

* using wildcard types in your APIs, while tricky, makes the APIs far more flexible
* if you write a library that will be widely used, the proper use of wildcard types should be considered mandatory
* remember the basic rule: producer-extends, consumer-super (PECS)
* and remember that all comparables and comparators are consumers.

---

### Item 29: Consider typesafe heterogeneous containers<a name="item29"></a>

#### Idea

* most common use of generics is for collections; in these cases, it's the container that's parameterized
    - limits to a fixed number of type parameters per container, which is *mostly desirable*
* sometimes more flexibility is needed: for example, a database row can have arbitrarily many columns, and it would be
  nice to be able to access all of them in a typesafe manner
    - that can be done by parameterizing the *key* and not the *container*
    - present the parameterized key to the container to insert or retrieve a value
    - the generic type system is used to guarantee that the type of the value agrees with its key

#### Example

* consider a *Favorites* class that allows its clients to store and retrieve a "favorite" instance of arbitrarily many
  other classes
    - the *Class* object will play the part of the parameterized key
    - the reason this works is that class *Class* was generified in release 1.5
    - the type of a class literal is no longer simply *Class*, but *Class\<T\>*
    - for example, *String.class* is of type *Class<String>*, and *Integer.class* is of type *Class\<Integer\>*
    - when a class literal is passed among methods to communicate both compile-time and runtime type information, it is
      called a **type token**
* the API for the *Favorites* class is simple; it looks just like a simple map, except that the key is parameterized
  instead of the map
* the client presents a *Class* object when setting and getting favorites - here is the API:

```Java
// Typesafe heterogeneous container pattern - API
public class Favorites {
    public <T> void putFavorite(Class<T> type, T instance);
    public <T> T getFavorite(Class<T> type);
}
```

* here is a sample program that exercises the *Favorites* class, storing, retrieving, and printing a favorite *String*
  , *Integer*, and *Class* instance:

```Java
// Typesafe heterogeneous container pattern - client
public static void main(String[] args) {
    Favorites f = new Favorites();
    f.putFavorite(String.class, "Java");
    f.putFavorite(Integer.class, 0xcafebabe);
    f.putFavorite(Class.class, Favorites.class);

    String favoriteString = f.getFavorite(String.class);
    int favoriteInteger = f.getFavorite(Integer.class);
    Class<?> favoriteClass = f.getFavorite(Class.class);
    LOGGER.debug("%s %x %s%n", favoriteString,
        favoriteInteger, favoriteClass.getName());
}
```

* a *Favorites* instance is *typesafe*: it will never return an *Integer* when you ask it for a String
* it is also *heterogeneous: unlike an ordinary map, all the keys are of differen types*
* **there, we call *Favorites* a *typesafe heterogeneous container***

```Java
// Typesafe heterogeneous container pattern - implementation
public class Favorites {
    private Map<Class<?>, Object> favorites =
            new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        if (type == null)
                throw new NullPointerException("Type is null");
        favorites.put(type, instance);
    }

    public<T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}
```

#### Anatomy of the example

* each *Favorites* class is backed by a private *Map\<Class\<?\>, Object\>* called *favorites*
    - every key can have a *different* parameterized type: one can be *Class\<String\>*, the next *Class\<Integer\>*,
      and so on
* the value of the *Favorites* class is simply *Object*
    - the *Map* does not guarantee the type relationship between keys and values, which is that every value is of the
      type represented by its key
    - in fact, Java's type system is not powerful enough to express this - but we know that it's true, and we take
      advantage of it when it comes time to retrieve a favorite
* the *putFavorite* implementation is trivial: it simply puts into *favorites* a mapping from the given *Class* object
  to the given favorite instance
    - this discards the "type linkage" between the key and the value
    - it loses the knowledge that the value is an instance of the key
    - but that's OK, because the *getFavorites* method can and does reestablish this linkage
* the *getFavorite* method first gets from the *favorites* map the value corresponding to geh given *Class* object
    - this is the correct object reference to return, but it has the wrong compile-time type
    - its type is simply *Object* (the value type of the *favorites* map) and we need to return a *T*
    - the the method implementation *dynamically casts* the object reference to the type represented by the *Class*
      object, using *Class*'s *cast* method
        + but we know that the values in the *favorites* map always match the types of the keys (so there won't be a *
          ClassCastException*)

* so what does the *cast* method do for us, given that it simply return its argument
    - the signature of the *cast* method takes full advantage of the fact that class *Class* has been generified
    - its return type is the type parameter of the *Class* object:

```Java
public class Class<T> {
    T cast(Object obj);
}
```

* this is precisely what's needed by the *getFavorite* method
    - it is what allows us to make *Favorites* typesafe without resorting to an unchecked cast to *T*

// TODO Limitations

#### Limitations

* there are two limitations described in the book (see p. 144)

#### Summary

* the normal use of generics, examplified by the collections APIs, restricts you to a fixed number of type parameters
  per container
* you can get around this restriction by placing the type parameter on the key rather than the container
* you can use *Class* objects as keys for such typesafe heterogeneous containers
* a *Class* object used in this fashion is called a type token
* you can also use a custom key type; for example, you could have a *DatabaseRow* type representing a database row (the
  container), and a generic type *Column\<T\>* as its key
