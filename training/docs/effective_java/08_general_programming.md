# Effective Java

## 8. General Programming

* this chapter is largely devoted to the nuts and bolts of the language
    - it discusses the treatment of local variables, control structures, the use of libraries, the use of various data
      types, and the use of two extralinguistic facilities:
        + reflection and native methods.
    - finally, it discusses optimization and naming conventions

---

### Item 45: Minimize the scope of local variables<a name="item45"></a>

* this item is similar in nature to [Item 13](04_classes_and_interfaces.md#item13)
* **by minimizing the scope of local variables, you increase the readability and maintainability of your code and reduce
  the likelihood of error**
* **the most powerful technique for minimizing the scope of a local variable is to declare it where it is first used**
    - declaring a local variable prematurely can cause its scope not only to extend too early, but also to end too late
* **nearly every local variable declaration should contain an initializer**
    - if you don't yet have enough information to initialize a variable sensibly, you should postpone the declaration
      until you do
    - one exception th this rule concerns *try-catch* statements
        + if a variable is initialized by a method that throws a checked exception, it must be initialized inside a *
          try* block
        + if a value must be used outside of the *try* block, then it must be declared before the *try* block, where it
          cannot yet be "sensibly initialized" (for example, see [Item 53](08_general_programming.md#item53))
* loops present a special opportunity to minimize the scope of variables
    - the *for* loop, in both its traditional and *for-each* forms, allows you to declare *loop variables*, limiting
      their scope to the exact region where they're neede
    - (this region consists of the body of the loop and the code in parantheses between the *for* keyword and the body)
    - therefore, **prefer *for* loops to *while* loops**, assuming the contents of the loop variable aren't needed after
      the loop terminates
        + for example, here is the preferred idiom for iterating over a collection ([Item 46](#item46)):

```Java
class MyClass {
    public static void main(String[] args) {
        // Preferred idiom for iterating over a collection
        for (Element e : c) {
            doSomething(e);
        }
    }
}
```

* before release 1.5, this was the preferred idiom (and it still has valid uses):

```Java
class MyClass {
    public static void main(String[] args) {
        // No for-each loop or generics before release 1.5
        for (Iterator i = c.iterator(); i.hasNext(); ) {
            doSomething((Element) i.next());
        }
    }
}
```

* to see why these *for* loops are preferable to a *while* loop, consider the following code fragment, which contains
  two *while* loops and one bug:

```Java
class MyClass {
    public static void main(String[] args) {
        Iterator<Element> i = c.iterator();
        while (i.hasNext()) {
            doSomething(i.next());
        }
    
        // ...
    
        Iterator<Element> i2 = c2.iterator();
        while (i.hasNext()) {           // BUG!
            doSomethingElse(i2.next());
        }
    }
}
```

* the second loop contains a copy-and-paste error; it initializes a new loop variable, *i2*, but uses the old one, *i*,
  which is, unfortunately, still in scope
    - the resulting code compiles without error and runs without throwing an exception, but it does the wrong thing
    - because the programm errs silenty, the error can remain undetected for a long time
* if a similar copy-and-paste error were made in conjunction with either of the *for* loops (for-each or traditional),
  the resulting code wouldn't even compile

* here is another loop idiom that minimizes the scope of local variables:

```Java
class MyClass {
    public static void main(String[] args) {
        // No for-each loop or generics before release 1.5
        for (int i = 0, n = expensiveComputation(); i < n; i++) {
            doSomething(i);
        }
    }
}
```

* here exist *two* loop variables, *i* and *n*, both of which have exactly the right scope
    - as a rule, you should use this ideiom if the loop test involves a method invocation that is guaranteed to return
      the same result on each iteration
* a final technique to minimize the scope of local variables is to **keep methods small and focused**
    - if you combine two activities in the same method, local variables relevant to one activity may be in the scope of
      the code performing the other activity
    - to prevent this from happening, simply separate the method into two: one for each activity

---

### Item 46: Prefer for-each loops to traditional for loops<a name="item46"></a>

* prior to release 1.5, this was the preferred idiom for iterating over a collection:

```Java
class MyClass {
    public static void main(String[] args) {
        // No longer the preferred idiom to iterate over a collection!
        for (Iterator i = c.iterator(); i.hasNext(); ) {
            doSomething((Element) i.next()); // (No generics before 1.5)
        }
    }
}
```

* this was the preferred idiom for iterating over an array:

```Java
class MyClass {
    public static void main(String[] args) {
        // No longer the preferred idiom to iterate over a array!
        for (int i = 0; i < a.length; i++) {
            doSomething(a[i]);
        }
    }
}
```

* these idiom are better than *while* loops ([Item 45](#item45)), but they aren't perfect
    - the iterator and the index variables are both just clutter
    - furthermore, they represent opportunities for error
* the for-each loop, introduced in release 1.5, gets rid of the clutter and the opportunity for error by hiding the
  iterator or index variable completely
    - the resulting idiom applies equally to collections and arrays:

```Java
class MyClass {
    public static void main(String[] args) {
        // The preferred idiom for iterating over collections and arrays
        for (Element e : elements) {
            doSomething(e);
        }
    }
}
```

* note that there is no performance penalty for using the for-each loop, even for arrays
    - in fact, it may offer a slight performance advantage over an ordinary *for* loop in some circumstances, as it
      computes the limit of the array index only once
    - while you *can* do this by dand ([Item 45](#item45)), programmers don't always do so
* the advantages of the for-each loop over the traditional *for* loop are even greater when it comes to nested iteration
  over multiple collections
    - here is a common mistake that people make when they try to do nested iteration over two collections:

```Java
class MyClass {
    enum Suit { CLUB, DIAMOND, HEART, SPADE }
    enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }
    
    // ...
    
    Collection<Suit> suits = Arrays.asList(Suit.values());
    Collection<Rank> ranks = Arrays.asList(Rank.values());
    
    public static void main(String[] args) {
        List<Card> deck = new ArrayList<>();
        for (Iterator<Suit> i = suits.iterator(); i.hasNext(); )
            for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); )
                deck.acc(new Card(i.next(), j.next()));
    }
}
```

* the problem here is, that the *next* method is called too many times on the iterator for the outer collection (*
  suits*)
    - it should be called from the outer loop, so that it is called once per suit, but instead it is called from the
      inner loop, so it is called once per card
    - after you run out of suits, the loop throws a *NoSuchElementException*
* if you're really unlucky and the size of the outer collection is a multiple of the size of the inner collection -
  perhaps because they're the same collection - the loop will terminate normally, but it won't do what you want
    - for example, consider this ill-conceived attempt to print all of the possible rolls of a pair of dice:

```Java
class MyClass {
    // Same bug different symptom!
    enum Face { ONE, TWO, THREE, FOUR, FIVE, SIX }
    // ...
    Collection<Face> faces = Arrays.asList(Face.values());
    
    public static void main(String[] args) {
        for (Iterator<Face> i = faces.iterator(); i.hasNext(); )
            for (Iterator<Face> j = faces.iterator(); j.hasNext(); )
                System.out.printlm(i.next() + " " + j.next());
    }
}
```

* this program doesn't throw an exception but it print only the six "doubles" (from "ONE ONE" to "SIX SIX"), instead of
  the expected thirty-six combinations
    - to fix the bugs in these examples, you must add a variable in the scope of the outer loop to hold the outer
      element:

```Java

class MyClass {
    // ...
    public static void main(String[] args) {
        List<Card> deck = new ArrayList<>();
        // Fixed, but ugly - you can do better!
        for (Iterator<Suit> i = suits.iterator(); i.hasNext(); ) {
            Suit suit = i.next();
            for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); )
                deck.acc(new Card(suit, j.next()));
        }
    }
}
```

* if instead you use a nested for-each loop, the problem simply disappears
    - the resulting code is as succinct as you could wish for:

```Java
class MyClass {
    // ...
    public static void main(String[] args) {
        // Preferred idiom for nested iteration on collections and arrays
        for (Suit suit : suits) {
            for (Rank rank : ranks)
                deck.add(new Card(suit, rank));
        }
    }
}
```

* not only does the for-each loop let you iterate over collections and arrays, it lets you iterate over any object that
  implements the *Iterable* interface
    - this simple interface, which consists of a single method, was added to the platform at the same time as the
      for-each loop
    - here is how it looks:

```Java
public interface Iterable<E> {
    // Returns an iterator over the elements in this iterable
    Iterator<E> iterator();
}
```

* it is not hard to implement the *Iterable* interface
    - if you are writing a type that represents a group of elements, have it implement *Iterable* even if you choose not
      to have it implement *Collection*
    - this will allow your users to iterate over your type using the for-each loop, and they will be forever grateful

#### Situation where you can't use a for-each loop

1. **Filtering** - if you need to traverse a collection and remove selected elements, then you need to use an explicit
   iterator so that you can call its *remove* method
2. **Transforming** - if you need to traverse a list or array and replace some or all of the values of its elements,
   then you need the list iterator or array index in order to set the value of an element
3. **Parallel iteration** - if you need to traverse multiple collections in parallel, then you need explicit control
   over the iterator or index variable, so that all iterators or index variables can be advanced in lockstep (as
   demonstrated unintentionally in the buggy card and dice examples above)

* if you find yourself in any of these situations, use an ordinary *for* loop, be wary of the traps mentioned in this
  item, and know that you're doing the best you can

---

### Item 47: Know and use the libraries<a name="item47"></a>

* the item in the book starts with an exhaustive example of an self implemented method that generates random numbers
    - the example illustrates how many pitfalls in this example exists and that the java library provides a much better
      solution
* these are the key advantages of using libraries:
    - **by using a standard library, you take advantage of the knowledge of the experts who wrote it and the experience
      of those who used it before you**
    - you don't have to waste your time writing ad hoc solutions to problems that are only marginally related to your
      work
    - the performance of standard libraries tends to improve over time, with no effort on your part
    - libraries tend to gain new functionality over time
    - with libraries *you place your code in the mainstream*; such code is more easily readable, maintainable, and
      reusable by the multitude of developers
* **numerous features are added to the libraries in every major release, and it pays to keep abreast of these
  additions**
    - each time there is a major release of the Java platform, Oracle publishes a Web page describing its new features
    - these pages are well worth reading: [feature link](https://todo/add/url)
    - the libraries are too big to study all the documentation, but **every programmer should be familiar with the
      contents of *java.lang*, *java.util* (especially the Collection Framework), and, to lesser extend, *java.io*
        + knowledge of other libraries can be acquired on an as-neede basis
        + the high-level parts of *java.util.concurrent* should also be part of every programmer's basic
          toolkit ([Item 68](10_concurrency.md#item68), [Item 69](10_concurrency.md#item69))
* BUT: if the functionality that you need is missing in the libraries you use, you may have no choice but to implement
  it yourself
* to summarize, don't reinvent the wheel:
    - if you need to do something that seems like it should be reasonably common, there may already be a class in the
      libraries that does what you want
    - if there is, ust it; if you don't know, check
    - generally speaking, library code is likely to be better than code that you'd write yourself and is likely to
      improve over time

---

### Item 48: Avoid float and double if exact answers are required<a name="item48"></a>

* the *float* and *double* types are designed primarily for scientific and engineering calculations
    - they perform *binary floating-point arithmetic*, which was carefully designed to furnish accurate approximations
      quickly over a broad range of magnitudes
    - they do not, however, provide exact results and should not be used where exact results are required
    - **the *float* and *double* types are particularly ill-suited for monetary calculations** because it is impossible
      to represent 0.1 (or any negative power of ten) as a *float* or *double* exactly
        + for example, suppose you have $1.03 in your pocket, and you spend 42¢ - how much money do you have left?
        + here's a naive program fragment that attempts to answer this question:

```Java
class NaiveMonetaryCalculation {
    public static void main(String[] args) {
        LOGGER.debug(1.03 - .42);
    }
}
```

* unfortunately, it prints out 0.6100000000000001 - this is not an isolated case
    - suppose you have a dollar in your pocket, and you buy nine washers priced at ten cents each - how much change do
      you get?

```Java
class NaiveMonetaryCalculation {
    public static void main(String[] args) {
        LOGGER.debug(1.00 - 9 * .10);
    }
}
```

* according to this program fragment, you get $0.09999999999999998

* you might think that the problem could be solved merely by rounding results prior to printing, but this does not
  always work
    - for example, suppose you have a dollar in your pocket, and you see a shelf with a row of delicious candies priced
      at 10¢, 20¢, 30¢, and so forth, up to a dollar
    - you buy one of each candy, starting with the one that costs 10¢, until you can't afford to buy the next candy on
      the shelf
    - how many candies do you buy, and how much change do you get?
    - here's a naive program designed to solve this problem:

```Java
class NaiveMonetaryCalculation2 {
    // Broken - uses floating point for monetary calculation!
    public static void main(String[] args) {
        double funds = 1.00;
        int itemsBought = 0;
        for (double price = .10; funds >= price; price += .10) {
            funds -= price;
            itemsBought++;
        }
        LOGGER.debug(itemsBought + " items bought.");
        LOGGER.debug("Change: $" + funds);
    }
}
```

* if you run the program, you'll find that you can afford three pieces of candy, and you have $0.3999999999999999 left
    - this is the wrong answer!
    - the right way to solve this problem is to **use *BigDecimal*, *int*, or *long* for monetary calculations**
    - here's a straightforward transformation of the previous program to use the *BigDecimal* type in place of *doucle*:

```Java
class ProperMonetaryCalculation {
    public static void main(String[] args) {
        final BigDecimal TEN_CENTS = new BigDecimal(".10");
        
        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.00");
        for (BigDecimal price = TEN_CENTS; 
                funds.compareTo(price) >= 0;
                price = price.add(TEN_CENTS)) {
            itemsBought++;
            funds = funds.subtract(price);
        }
        LOGGER.debug(itemsBought + " items bought.");
        LOGGER.debug("Money left over: $" + funds);
    }
}
```

* if you run the revised program, you'll find that you can afford four pieces of candy, with *$0.00* left over - which
  is the correct answer
* there are, however, two disadvantages to using *BigDecimal*: it's less convenient than using a primitive arithmetic
  type, and it's slower
* an alternative to using *BigDecimal* is to use *int* or *long*, depending on the amounts involved, and to keep track
  of the decimal point yourself
    - in this example, the obvious approach is to do all computation in cents instead of dollars
    - here's a straightforward transformation of the program just shown that takes this approach:

```Java
class ProperMonetaryCalculation2 {
    public static void main(String[] args) {
        int itemsBought = 0;
        int funds = 100;
        for (int price = 10; funds >= price; price += 10) {
            itemsBought++;
            funds -= price;
        }
        LOGGER.debug(itemsBought + " items bought.");
        LOGGER.debug("Money left over: $" + funds + " cents");
    }
}
```

#### Summary

* don't use *float* or *double* for any calculations that require and exact answer
* use *BigDecimal* if you want the system to keep track of the decimal point and you don't mind the inconvenience and
  cost of not using a primitive type
    - using *BigDecimal* has the added advantage that it gives you full control over rounding, letting you select from
      eight rounding modes whenever an operation that entails rounding is performed
    - this comes in handy if you're performing business claculations with legally mandated rounding behavior
* if performance is of the essence, you don't mind keeping track of the decimal point yourself, and the quantites aren't
  too big, use *int* or *long*
    - if the quantities don't exceed nine decimal digits, you can use *int*
    - if they don't exceed eighteen digits, you can use *long*
    - if the quantities might exceed eighteen digits, you must use *BigDecimal*

---

### Item 49: Prefer primitive types to boxed primitives<a name="item49"></a>

* Java has a two-part type system, consisting of *primitives*, such as *int*, *double*, and *boolean*, and *reference
  types*, such as *String* and *List*
    - every primitive type has a corresponding reference type, called a *boxed primitive*
    - the boxed primitives corresponding to *int*, *double*, and *boolean* are *Integer*, *Double*, and *Boolean*
* in release 1.5, *autoboxing* and *auto-unboxing* were added to the language
    - as mentioned in [Item 5](02_creating_and_destroying_objects.md#item5), these features blur but do not erase the
      distinction between the primitive and boxed primitive types
    - there are real differences between the two, and it's important that you remain aware of which you are using, and
      that you choose carefully between them
* there are three major differences between primitives and boxed primitives
    - primitives have only their values, whereas boxed primitives have identites distinct from their values
        + in other words, two boxed primitive instances can have the same value and different identities
    - primitive types have only fully functional values, whereas each boxed primitive type has one nonfunctional value,
      which is *null*, in addition to all of the functional values of its corresponding primitive type
    - primitives are generally more time- and space-efficient than boxed primitives
    - all three of these differences can get you into real trouble if you aren't careful
* consider the following comparator, which is designed to represent ascending numerical order on *Integer* values:

```Java
// Broken comparator - can you spot the flaw?
class MyUtils {
    public static final Comparator<Integer> naturalOrder = new Comparator<>() {
        public int compare(Integer first, Integer second) {
            return first < second ? -1 : (first == second ? 0 : 1);
        }
    };
}
```

* this comparator is deeply flawed
    - if you pass to boxed primitives with the same value (for instance *new Integer(42)*) the returned value should be
      0, but it's 1, which indicates that the first *Integer* value is greater than the second
    - so what's the problem?
        + the first test in *naturalOrder* works fine - the expression *first < second* causes the *Integer* instances
          to be *auto-unboxed*
        + the next test evaluates the expression *first == second*, which performs an *identity comparison* on the two
          object references
        + if *first* and *second* refer to distinct *Integer* instances that represent the same *int* value, this
          comparison will return *false*, and the comparator will incorrectly return 1, indicating that the first *
          Integer* value is greater than the second
        + **Applying the == operator to boxed primitives is almost always wrong**
* the clearest way to fix the problem is to add two local variables, to store the primitive *int* values corresponding
  to *first* and *second*, and to perform all of the comparisons on these variables
    - this avoid the erroneous identity comparison:

```Java
class MyUtils {
    public static final Comparator<Integer> naturalOrder = new Comparator<>() {
        public int compare(Integer first, Integer second) {
            int f = first;  // Auto-unboxing
            int s = second; // Auto-unboxing
            return f < s ? -1 : (f == s ? 0 : 1);
        }
    };
}
```

* next, consider this little program:

```Java
public class Unbelievable {
    static Integer i;
    
    public static void main(String[] args) {
        if (i == 42)
            LOGGER.debug("Unbelievable");
    }
}
```

* this program throws a *NullPointerException* as *i* is auto-unboxed to a primitive
    - as *i* is not initialized the value becomes *null* during the auto-unboxing process and as a result, in this case
      a *NullPointerException* is thrown
    - **when you mix primitives and boxed primitives in a single operation, the boxed primitive is auto-unboxed**
    - fixing the program is as simple as declaring *i* to be an *int* instead of an *Integer*

* finally, consider the program from [Item 5](02_creating_and_destroying_objects.md#item5)
    - in this example a variable is repeatedly boxed and unboxed, which results in **severe performance problems**

```Java
class MyClass {
    // Hideously slow program!
    public static void main(String[] args) {
        Long sum = 0L;
        for(long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        LOGGER.debug(sum);
    }
}
```

#### When to use boxed primitives

* boxed primitives have severals legitimate uses:
    - as elements, keys, and values in collections
        + you can't put primitives in collections, so you're forced to use boxed primitives
    - you must use boxed primitives as type parameters in parametized types (Chapter5), because the language does not
      permit you to use primitives
        + for example, you cannot declare a variable to be of type *ThreadLocal\<int\>*, so you must use *
          ThreadLocal\<Integer\> instead
    - you must use boxed primitives when making reflective method invocations ([Item 53](#item53))

#### Summary

* use primitives in preference to boxed primitives whenever you have the choice
    - primitive types are simpler and faster
* if you must use boxed primitives, be careful!
    - **autoboxing reduces the verbosity, but not the danger, of using boxed primitives**
    - **when your program does unboxing, it can throw a *NullPointerException***

---

### Item 50: Avoid strings where other types are more appropriate<a name="item50"></a>

* **strings are poor substitutes for other value types**
    - when a piece of data comes into a program from a file, from the network, or from keyboard input, it is often in
      string from
    - there is a natural tendency to leave it that way, but his tendency is justified only if the data really is tectual
      in nature
        + if it's numeric, it should be translated into the appropraite numeric type, such as *int*, *float*, or *
          BigInteger*
        + if it's the answer to a yes-or-no question, it should be translated into a *boolean*
        + if there's an appropriate value type, whether primitive or object reference, you should use it; if there
          isn't, you should write one
* **strings are poor substitutes for enum types**
    - as discussed in [Item 30](06_enums_and_annotations.md#item30), enums make far better enumerated type constants
      than strings
* **strings are poor substitutes for aggregate types**
    - if an entity has multiple components, it is usually a bad idea to represent it as a single string
    - for example, here's a line code that comes from a real system:
        + this approach has many disadvantages (what if the character that separates occurs in one of the fiels?; you
          have to parse the string to access certain fields; you can't provide *equals*, *toString*, or *compareTo*
          methods; ...)
        + (a better approach is simply to write a class to represent the aggregate, often a private static member
          class ([Item 22](04_classes_and_interfaces.md#item22)))

```Java
class SomeClass {
    // ...
    
    // Inappropriate use of string as aggregate type
    String compoundKey = className + "#" + i.next();
}
```

* **strings are poor substitutes for *capabilities***
    - occasionally, strings are used to grant access to some functionality
    - for example, consider the design of a thread-local variable facility:
        + such a facility provides variables for which each thread has its own value
        + the Java libraries have had a thread-local variable facility since release 1.2, but prior to that, programmers
          had to roll their own
        + when confronted with the task of designing such a facility many years ago, several people independently came
          up with the same design in which client-profided string keys are used to identiry each thread-local variable
    - the problem with this approach is that the string keys represent a shared global namespace for thread-local
      variables
        + in order for the approach to work, the client-provided string keys have to be unique: if two clients
          independently decide to use the same name for their thread-local variable, they unintentionally share a single
          variable, which will generally cause both clients to fail
        + also, the security is poor: a malicious client could intentionally use the same string key as another client
          to gain illicit access to the other client's data
    - the example is illustrated with code in the book, p. 225f.

---

### Item 51: Beware the performance of string concatenation<a name="item51"></a>

* **using the string concatenation operator *(+)* repeatedly to concatenate *n* strings requires time quadratic in *n*
    - it is an unfortunate consequence of the fact that strings are *
      immutable* ([Item 15](04_classes_and_interfaces.md#item15))
    - when two string are concatenated, the contents of both are copied
    - for example, consider the following method that constructs a string representation of a billing statement by
      repeatedly concatenating a line for each item:

```Java
class SomeClass {
    // Inappropriate use of string concatenation - Performs horribly!
    public String statement() {
        String result = "";
        for (int i = 0; i < numItems(); i++)
            result += lineForItem(i);   // String concatenation
        return result;
    }
} 
```

* this method performs abysmally if the number of items is large
* **to achieve acceptable performance, use a *StringBuilder* in place of a *String*** to store the statement under
  construction
    - (the *StringBuilder* class is an unsynchronized replacement for *StringBuffer*, which is now obsolete)

```Java
class SomeClass {
    public String statement() {
        StringBuilder b = new StringBuilder(numItems() * LINE_WIDTH);
        for (int i = 0; i < numItems(); i++)
            b.append(lineForItem(i));
        return b.toString();
    }
}
```

* the difference in performance is dramatic!
* the moral is simple: don't use the string concatenation operator to combine more than a few strings unless performance
  is irrelevant
    - use *StringBuilder*'s append method instead
    - alternativeley, use a character array, or process the strings one at a time instead of combining them

---

### Item 52: Refer to objects by their interfaces<a name="item52"></a>

* [Item 40](07_methods.md#item40) contains the advice that you should use interfaces rather than classes as parameter
  types
* more generally, you should favor the use of interfaces rather than classes to refer to objects
* **if appropriate interface types exist, then parameters, return values, variables, and fields should all be declared
  using interface types**
    - the only time you really need to refer to an object's class is when you're creating it with a constructor
    - to make this conrete, consider the case of *Vector*, which is an implementation of the *List* interface; get in
      the habit ot typing this:

```Java
class SomeClass {
    // Good - uses interface as type
    List<Subscriber> subscribers = new Vector<>();
}
```

* rather than this:

```Java
class SomeClass {
    // Bad - uses class as type!
    Vector<Subscriber> subscribers = new Vector<>();
}
```

* **if you get into the habit of using interfaces as types, your program will be much more flexible**
    - if you decide that you want to switch implementations, all you have to do is change the class name in the
      constructor (or use a different static factory)
    - for example, the first declaration could be changed to read:

```Java
class SomeClass {
    List<Subscriber> subscribers = new ArrayList<>();
}
```

* and all of the surrounding code would continue to work
    - the surrounding code was unaware of the old implementation type, so it would be oblivious to the change
* **there is one caveat**:
    - if the original implementation offered some special functionality, then it is critical that the new implementation
      provides the same functionality
    - for example, if the code surrounding the first declaration depended on *Vector*'s synchronization policy, then it
      would be incorrect to substitute *ArrayList* for *Vector* in the declaration
    - if you depend on any special properties of an implementation, document these requirements where you declare the
      variable
* you would want to change implementations because the new implementation offers better performance or because it offers
  desirable extra functionality (for the example *ThreadLocal* class, see book p. 228f.)
* **it is entirely appropriate to refer to an object by class rather than an interface if no appropriate interface
  exists**
    - for example, consider *value classes*, such as *String* and *BigInteger*
        + value classes are rarely written with multiple implementations in mind
        + they are often final and rarely have corresponding interfaces
        + if a concrete class has no associated interface, then you have no choice but to refer to it by its class
          whether or not it represents a value (the *Random* class falls into this category)
    - a second case in which there is no appropriate interface type is that of objects belonging to a framework whose
      fundamental types are classes rather than interfaces
        + if an object belongs to such a *class-based framework*, it is preferable to refer to it by the relevant *base
          class*, which is typically abstract, rather than by its implementation class (the *java.util.TimerTask* class
          falls into this category)
    - a final case in which there is no appropriate interface type is that of classes that implement an interface but
      provide extra method not found in the interface - for example, *PriorityQueue*
        + such a class should be used to refer to its instances only if the program relies on the extra methods
        + it should rarely be used as a parameter type ([Item 40](07_methods.md#item40))
    - these cases are not meant to be exhaustive but merely to convey the flavor of situations where it is appropriate
      to refer to an object by its class
        + in practice, it should be apparent whether a given object has an appropriate interface
        + if it does, your program will be more flexible if you use the interface to refer to the object
        + if not, just use the least specific class in the class hierarchy that provides the required functionality

---

### Item 53: Prefer interfaces to reflection<a name="item53"></a>

* the *core reflection facility*, *java.lang.reflect*, offers programmatic access to information about loaded classes
    - given a *Class* object, you can obtain *Constructor*, *Method*, and *Field* instances representing the
      constructors, methods, and fields of the class represented by the *Class* instance
    - the objects provide programmatic access to the class's member names, field types, method signatures, and so on
* moreover, *Constructor*, *Method*, and *Field* instances let you manipulate their underlying counterparts *
  reflectively*:
    - you can construct instances, invoke methods, and access fields of the underlying class by invoking methods on
      the *Constructor*, *Method*, and *Field* instances
    - for example, *Method.invoke* lets you invoke any method on any object of any class (subject to the usual security
      constraints)
* reflection allows one class to use another, even if the latter class did not exist when the former was compiled
    - this power, however, comes at a price:
        + **you lose all the benefits of compile-time type checking**, including exception checking - if a program
          attempts to invoke a nonexistent or inaccessible method reflectively, it will fail at runtime unless you've
          taken special precautions
        + **the code required to perform reflective access is clumsy and verbose** - it is tedious to write and
          difficult to read
        + **performance suffers** - reflective method invocation is much slower than normal method invocation; exactly
          how much slower is hard to say, because there are so many factors at work

* the core reflection facility was originally designed for component-based application builder tools
    - reflection is used only at *design time*
    - **a sa rule, objects should not be accessed reflectively in normal applications at runtime**
* there are few sophistacated applications that require reflection
    - examples include class browsers, object inspectors, code analysis tools, and interpretive embedded systems
    - reflection is also appropriate for use in remote procedure call (RPC) systems to eliminate the need for stub
      compilers
* **you can obtain many of the benefits of reflection while incurring few of its costs by using it only in a very
  limited form**
    - for many programs that must use a class that is unavailable at compile time, there exists at compile time an
      appropriate interface or superclass by which to refer to the class ([Item 52](#item52))
    - if this is the case, you can **create instances reflectively and access them normally via their interface or
      superclass**
    - if the appropriate constructor has no parameters, then you don't even need to use *java.lang.reflect*; the *
      Class.newInstance* method provides the required functionality

* for example, here's a program that creates a *Set\<String\>* instance whose class is specified by the first command
  line argument
    - the program inserts the remaining command line arguments into the set and prints it
    - regardless of the first argument, the program print the remaining arguments with duplicates eliminated
    - the order in which these arguments are printed, however, depends on the class specified in the first argument:
        + if you specify *java.util.HashSet*, they're printed in apparently random order
        + if you specify *java.util.TreeSet*, they're printed in alphabetical order, as the elements in a *TreeSet* are
          sorted:

```Java
class ReflectiveInstantiation {
    // Reflective instantiation with interface access
    public static void main(String[] args) {
        // Translate the class name into a Class object
        Class<?> cl = null;
        try {
            cl = Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found.");
            System.exit(1);
        }
        
        // Instantiate the class
        Set<String> s = null;
        try {
            s = (Set<String>) cl.newInstance();
        } catch (IllegalAccessException e) {
            System.err.println("Class not accessible.");
            System.exit(1);
        } catch (InstantiationException e) {
            System.err.println("Class not instantiable.");
            System.exit(1);
        }
        
        // Exercise the set
        s.addAll(Arrays.asList(args).subList(1, args.length));
        LOGGER.debug(s);
    }
}
```

* while this program is just a toy, the technique is sufficiently powerful to implement a full-blown *service provider
  framework* ([Item 1](#item1))
* the example demonstrates two disadvantages of reflection
    - the example can generate three runtime errors, all of which would have been compile-time errors if reflective
      instantiation were not used
    - it takes twenty lines of tedious code to generate an instance of the class from its name, whereas a constructor
      invocation would fit neatly on a single line
* these disadvantages are, however, restricted to the part of the program that instantiates the object
    - once, instantiated, it is indistinguishable from any other *Set* instance
    - in a real program, the great bulk of the code is thus unaffected by this limited use of reflection
    - for further remarks on the example see book p. 232

* in summary, reflection is a powerful facility that is required for certain sophisticated system programming tasks, but
  it has many disadvantages
    - if you are writing a program that has to work with classes unknown at compile time, you should, if at all
      possible, use reflection only to instantiate objects, and access the objects using some interface or superclass
      that is known at compile time

---

### Item 54: Use native methods judiciously<a name="item54"></a>

* the Java Native Interface (JNI) allows Java applications to call *native methods*, which are special methods written
  in *native programming languages* such as C or C++
    - native methods can perform arbitrary computation in native languages before returning to the Java programming
      language
* historically, native methods have had three main uses:
    - they provided access to platform-specific facilities such as registries and file locks
    - they provided access to libraries of legacy code, which could in turn provide access to legacy data
    - finally, native methods were used to write performance-critical parts of applications in native languages for
      improved performance
* it is legitimate to use native methods to access platform-specific facilities, but as the Java platform matures, it
  provides more and more features previously found only in host platforms
    - for example, *java.util.prefs*, added in release 1.4, offers the functionality of a registry, and *
      java.awt.SystemTray*, added in release 1.6, offers access to the desktop system tray area
    - it is also legitimate to use native methods to access legacy code
* **it is rarely advisable to use native methods for improved performance**
    - in early releases (prior to 1.3), it was often necessary, but JVM implementations habe gotten *much* faster
* the use of native methods has serious disadvantages
    - because native languages are not *safe* ([Item 39](07_methods.md#item39)), applications using native methods are
      no longer immune to memory corruption errors
    - because native languages are platform dependent, applications using native methods are far less portable
    - applications using native code are far more difficult to debug
    - there is a fixed cost associated with going into and out of native code, so native methods can *decrease*
      performance if they do only a small amount of work
    - finally, native methods require "glue code" that is difficult to read and tedious to write
* in summary, think twice before using native methods
    - rarely, if ever, use them for improved performance
    - if you must use native methods to access low-level resources or legacy libraries, use as little native code as
      possible and test it thoroughly
        + a single bug in the native code can corrupt your entire application

---

### Item 55: Optimize judiciously<a name="item55"></a>

* there are three aphorisms concerning optimization that everyone should know:
    - More computing sins are committed in the name of efficiency (without necessarily achieving it) than for any other
      single reason - including blind stupidity. (William A. Wulf)
    - We *should* forget about small efficiencies, say about 97% of the time: premature optimization is the root of all
      evil. (Donald E. Knuth)
    - We follow two rules in the matter of optimization:
        + Rule 1. Don't do it.
        + Rule 2 (for experts only). Don't do it yet - that is, not until you have perfectly clear and unoptimized
          solution. (M. A. Jackson)
* these aphorisms tell a depp truth about optimization: it is easy to do more harm than good, especially if you optimize
  prematurely
    - in the process, you may produce software that is neither fast nor correct and cannot easily be fixed
* don't sacrifice sound architectural principles for performance
    - **strive to write good programs rather than fast ones**
    - if a good program is not fast enough, its architecture will allow it to be optimized
    - good programs embody the principle of *information hiding*: where possible, they localize design decisions within
      individual modules, so individual decisions can be changed without affecting the remainder of the
      system ([Item 13](04_classes_and_interfaces.md#item13))
* this does *not* mean that you can ignore performance concerns until your program is complete
    - implementation problems can be fixed by later optimization, but pervasive architectural flaws that limit
      performance can be impossible to fix without rewriting the system
    - changing a fundamental facet of your design after the fact can result in an ill-structured system that is
      difficutlt to maintain and evolve
    - therefore you must think about performance during the design process
* **strive to avoid design decisions that limit performance**
    - the components of a design that are most difficult to change after the fact are those specifying interactions
      between modules and with the outside world
    - chief among these design components are APIs, wire-level protocols, and persistent data formats
    - not only are these design components difficult or impossible to change after the fact, but all of them can place
      significant limitations on the performance that a system can ever achieve
* **consider the performance consequences of your API design decisions**
    - making a public type mutable may require a lot of needless defensive copying ([Item 39](07_methods.md#item39))
    - similarly, using inheritance in a public class where composition would have been appropriate ties the class
      forever to its superclass, which can place artificial limits on the performance of the
      subclass ([Item 16](04_classes_and_interfaces.md#item16))
    - using an implementation type rather than an interface in an API ties you to a specific implementation, even though
      faster implementations may be written in the future ([Item 52](#item52))
    - for an illustrative example see book, p. 235
* luckily, it is generally the case that good API design is consistent with good performance
    - **it is a very bad idea to warp an API to achieve good performance**
    - once you've carefully designed you program and produced a clear, concise, and well-structured implementation, *
      then* it may be time to consider optimization, assuming you're not already satisfied with the performance of the
      program
* **measure performance before and after each attempted optimization** - you may be surprised by what you finde
    - it's difficult to guess where your program is spending its time
    - the part of the program that you think is slow may not be at fault, in which case you'd be wasting your time
      trying to optimize it
    - *profiling tools* can help you decide where to focus your optimization efforts
        + such tools give you runtime information, such as roughly how much time each method is consuming and how many
          times it is invoked
        + in addition to focusing your tuning efforts, *this can alert you to the need for algorithmic changes*
        + the more code in the system, the more important it is to use a profiler
    - the need to measure the effects of attempted optimization is even greater on the java platform than on more
      traditional platforms, because the Java programming language does not have a strong *performance model*
        + for instance, the relative costs of the various primitive operations are not well defined and the "semantic
          gap" between what the programmer writes and what the CPU executes is far greater than in traditional
          statically compiled languages (which makes it very difficult to reliably predict the performance consequences
          of any optimization)
    - not only is Java's performance model ill-defined, but it varies from JVM implementation to JVM implementation,
      from release to release, and from processor to processor
* to summarize, do not strive to write fast programs - strive to write good ones; speed will follow
    - do think about performance issues while you're designing systems and especially while you're designing APIs,
      wire-level protodols, and persistent data formats

---

### Item 56: Adhere to generally accepted naming conventions<a name="item56"></a>

* the Java platform has a well-established set of *naming conventions*, many of which are contained in *The Java
  Language Specification ([JLS, 6.8](https://todo/add/url))
    - loosely speaking, naming conventions fall into two categories: *typographical and grammatical*
* there are only a handful of *typographical naming conventions*, covering packages, classes, interfaces, methods,
  fields and type variables
    - you should never violate them and never withoug a good reason
    - detailed rules for converting Internet domain names to package name prefixes can be found in *The Java Language
      Specification* ([JLS, 7.7](https://todo/add/url))
* for quick reference, the following table shows examples of typographical conventions (for detailed explanations see
  book, p. 237f.):

| Identifier Type    | Examples                                                         |
|--------------------|------------------------------------------------------------------|
| Package            | com.google.inject, org.joda.time.format                          |
| Class or Interface | Timer, FutureTask, LinkedHashMap, HttpServlet                    |
| Method or Field    | remove, ensureCapacity, getCrc                                   |
| Constant Field     | MIN_VALUE, NEGATIVE_INFINITY                                     |
| Local Variable     | i, xref, houseNumber                                             |
| Type Parameter     | T (Type), E (Element), K (Key), V (Value), X (Exception), T1, T2 |

* *grammatical naming conventions* are more flexible and more controversial than typographical conventions
    - there are no grammatical naming conventions to speak of for *packages*
    - *classes*, including *enum types*, are generally named with a singular noun or noun phrase, for example, *Timer*
      , *BufferedWrtier*, or *ChessPiece*
    - *interfaces* are named like classes, for example, *Collection* or *Comparator*, or with an adjective ending in *
      able* or *ible*, for example, *Runnable*, *Iterable*, or *Accessible*
    - because *annotations types* have so many uses, no part of speech predominates: nouns, verbs, prepositions, and
      adjectives are all common, for example, *BindingAnnotation*, *Inject*, *ImplementedBy*, or *Singleton*
    - *methods* that perform some action are generally named with a verb or verb phrase (including object), for
      example, *append* or *drawImage*
        + methods that return a *boolean* value usually have names that begin with the word *is* or, less commonly, *
          has*, followed by a noun, noun phrase, or any word or phrase that functions as an adjective, for example, *
          isDigit*, *isProbablePrime*, *isEmpty*, *isEnabled*, or *hasSiblings*
        + methods that return a non-boolean function or attribute of the object on which they're invoked are usually
          named with a noun, a noun phrase, or a verb phrase beginning with the verg *get*, for example, *size*, *
          hashCode*, or *getTime*
            - the form beginning with *get* is mandatory if the class containing the method is *Bean*, and it's
              advisable if you're considering turning the class into a Bean at a later time
            - also, there is strong precedent for this form if the class contains a method to set the same attribute -
              in this case, the two methods should be name *get*Attribute and *set*Attribute
            - there is a vocal contingent that claims that only the third form (beginning with *get*) is acceptable, but
              there is little basis for this claim - the first two forms usually lead to more readable code, for
              example:

```Java
class SomeClass {
    public static void main(String[] args) {
        if (car.speed() > 2 * SPEED_LIMIT)
            generateAudibleAlert("Watch out for cops!");
    }
}
```

* still *grammatical naming conventions*:
    - continuing on *methods*:
        + a few method names deserve special mention
            - methods that convert the type of an object, returning an independent object of a different type, are often
              called *to*Type, for example, *toString*, *toArray*
            - methods that return a *view* ([Item 5](02_creating_and_destroying_objects.md#item5)) whose type differs
              from that of the receiving object are often called *as*Type, for example, *asList*
            - methods that return a primitive with the same value as the object on which they're invoked are often
              called type*Value*, for example, *intValue*
            - common names for static factories are *valueOf*, *of*, *getInstance*, *newInstance*, *getType*, and *
              newType* ([Item 1](02_creating_and_destroying_objects.md#item1))
    - grammatical conventions for field names and local viariables are less well established and less important than
      those for class, interface, and method names
* to summarize, internalize the standard naming conventions and learn to use them as second nature    