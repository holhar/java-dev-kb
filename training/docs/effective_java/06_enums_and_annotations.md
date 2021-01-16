# Effective Java

## 6. Enums and Annotations

---

### Item 30: Use enums instead of int constants<a name="item30"></a>

* an *enumerated type* is a type whose legal values consist of a fixed set of constants, such as the seasons of the
  year, the planets in the solar system, or the suits in a deck of playing cards

#### Antipatterns

* before enum types were added to the language there were other approaches, like the *int enum pattern* or the *String
  enum pattern* - which both were **severely deficient!**

```Java
public class MyClass {
    // The int enum pattern - severely deficient!
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;
    
    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;
}
```

* **Shortcomings**
    - programs that use the *int enum pattern* are brittle
        + because *int enums* are compile-time constants, they are compiled into the clients that use them - if the *
          int* associated with an enum constant is changed, its clients must be recompiled (if they aren't, they will
          still run, but their behavior will be undefined)
    - no type safety and inconvenient - there is no easy way to translate *int enum* constants into printable strings
    - no namespace feature, so it has to be emulated
    - the compiler won't complain if you pass an apple to a method that expects an orange, compare apples to oranges
      with the *==* operator, or worse:

```Java
public class MyClass {
    // Tasty citrus flavored applesauce!
    int i = (APPLE_FUJI - ORANGE_TEMPLE) / APPLE_PIPPIN;
}
```

* the *String enum pattern* is even less desirable:
    - can lead to performance problems because it relies on string comparisons
    - can lead naive users to hard-code string constants into client code (which can contain typos) instead of using
      field names

#### enum types to the rescue

```Java
// enum types in its simplest form
public enum Apple { FUJI, PIPPIN, GRANNY_SMITH }
public enum Orange { NAVEL, TEMPLE, BLOOD }
```

##### Basic idea behind Java's enum types

* they are classes that *export one instance* for each enumeration constant via a public static final field
* enum types *are effectively final*, by virtue of having no accessible constructors - as a result:
    - there can be no instances but the declared enum constants, so that they are **
      instance-controlled** [Item 6](02_creating_and_destroying_objects.md#item6)
* they are a *generalization of singletons* [Item 3](02_creating_and_destroying_objects.md#item3), which are essentially
  single-element enums
* enums *provide compile-time type safety*
* *make use of namespaces* so that enum types with identical name constants coexist peacefully
* you can add or reorder constants in an enum type *without recompiling its clients* because the fields that export the
  constants provide a layer of isolation between an enum type and its clients:
    - the constant values are not compiled into the clients as they are int tne *int enum pattern*
* enum types let you *add arbitrary methods and fields and implement arbitrary interfaces*
* they provide high-quality implementations of all the *Object*
  methods ([Chapter 3](03_methods_common_to_all_objects.md))
* they implement *Comparable* ([Item 12](03_methods_common_to_all_objects.md#item12)) and *
  Serializable* ([Chapter 11](11_serialization.md)), and their serialized form is designed to withstand most changes to
  the enum type

##### Adding methods and fields to enum types

* **To associate data with enum constants, declare instance fields and write a constructor that takes the data and
  stores it in the fields**
* **motivation** associate data with its constants - example:
    - consider the eight planets of our solar system
    - each planet has a mass and a radius, and from these two attributes you can compute its surface gravity
    - this in turn lets you compute the weight of an object on the planet's surface, given the mass of the object

```Java
// Enum type with data and behavior
public enum Planet {
    // The numbers in parentheses after each enum constant are para-
    // meters that are passed to its constructor - mass and radius
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6),
    MARS(6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN(5.685e+26, 6.027e7),
    URANUS(8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);

    private final double mass;          // In kilograms
    private final double radius;        // In meters
    private final double surfaceGravity; // In m / s^2

    // Universal gravitational constant in m^3 / kg s^2
    private static final double G = 6.67300E-11;

    // Constructor
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }

    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    public double surfaceGravity() {
        return surfaceGravity;
    }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity; // F = ma
    }
}
```

* here is a short program that takes the earth-weight of an object (in any unit) and prints a nice table of the objects
  weight on all eight planets (in the same unit):

```Java
public class WeightTable {
    public static void main(String[] args) {
        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values()) {
            LOGGER.debug("Weight on %s is %f%n", p, p.surfaceWeight(mass));
        }
    }
}
```

##### General Tips

* enums are by their nature immutable, so all fields should be final ([Item 15](04_classes_and_interfaces.md#item15))
    - they can be public, but it is better to make them private and provide public
      accessors ([Item 14](04_classes_and_interfaces.md#item14))
* just as with other classes, unless you have a compelling reason to expose an enum method to its clients, declare it
  private or, if need be, package-private ([Item 13](04_classes_and_interfaces.md#item13))
* if an enum is gerally useful, it shoud be a top-level class; it its use is tied to a specific top-level class, it
  should be a member class of that top-level class ([Item 22]((04_classes_and_interfaces.md#item22)))

##### Advanced usages

###### Associate fundamentally different behavior with each constant

* **example**: suppose you are writing an enum type to represent the operations on a basic four-function calculator, and
  you want to provide a method to perform the arithmetic operation represented by each constant
    - you could achieve this is to switch on the value of the enum:

```Java
// Enum type that switches on its own value - questionable
public enum Operation {
    PLUS, MINUS, TIMES, DIVIDE;

    // Do the arithmetic op represented by this constant
    double apply(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
        }
        throw new AssertionError("Unknown op: " + this);
    }
}
```

* this code works, but is not pretty
* *worse*: the code is fragile - it's possible to add a new enum constant but to forget to add it to the apply-method
* **the way to do it**:
    - declare an abstract *apply* method in the enum type, and override it with a concrete method for each constant in
      a **constant-specific class body**
    - such methods are known as **constant-specific method implementations**:

```Java
// Enum type with constant-specific method implementations
public enum Operation {
    PLUS {
        double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        double apply(double x, double y) {
            return x / y;
        }
    };

    abstract double apply(double x, double y);
}
```

* **constant-specific method implementations can be combined with constant-specific data**
    - example: here is a version of *Operation* that overrides the *toString* method to return the symbom commonly
      associated with the operation:

```Java
public enum Operation {
    PLUS("+") {
        double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    abstract double apply(double x, double y);
}
```

* Overriding *toString* in an enum is very useful and makes it easy to nicely print information:

```Java
class PrintOperation {
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        for (Operation op : Operation.values()) {
            LOGGER.debug("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
        }
    }
}
```

###### Translate the custom string representation back to the corresponding enum type

* enum types have an automatically generated *valueOf(String)* method that translates a constant's name into the
  constant itself
* if you override the *toString* method in an enum type, consider writing a *fromString* method to translate the custom
  string representation back to the corresponding enum
* the following code (with the type name changed appropriately) will do the trick for any enum, so long as each constant
  has a unique string representation:

```Java
public enum Operation {
    PLUS, MINUS, TIMES, DIVIDE;

    // Implementing a fromString method on an enum type
    private static final Map<String, Operation> stringToEnum = new HashMap<>();

    // Initialize the map
    static {
        for (Operation op : values()) {
            stringToEnum.put(op.toString(), op);
        }
    }

    // Returns Operation for string, or null if string is invalid
    public static Operation fromString(String symbol) {
        return stringToEnum.get(symbol);
    }
}
```

* note that the *Operation* constants are put into the *stringToEnum* map from a static block that runs after the
  constants have been created
    - trying to make each constant put itself into the map from its own constructor would cause a compilation error

###### Using the Strategy enum pattern for multiple enum constants that share common behaviors

* for an exhaustive explanation of the example see the book p. 154 ff.
* **procedure**:
    - move the commonly shared behavior into a private nested enum, and pass an instance of this instance of this **
      strategy enum** to the constructor for the wrapper enum
    - the wrapper enum **delegates** the requested behavior to the strategy enum, **eliminating the need for a switch
      statement or constant-specific method implementation**

```Java
// The strategy enum pattern
public enum PayrollDay {
    MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY),
    WEDNESDAY(PayType.WEEKDAY), THURSDAY(PayType.WEEKDAY),
    FRIDAY(PayType.WEEKDAY),
    SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    double pay(double hoursWorked, double payRate) {
        return payType.pay(hoursWorked, payRate);
    }

    // The strategy enum type
    private enum PayType {
        WEEKDAY {
            double overtimePay(double hours, double payRate) {
                return hours <= HOURS_PER_SHIFT ? 0 :
                        (hours - HOURS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            double overtimePay(double hours, double payRate) {
                return hours * payRate / 2;
            }
        };

        private static final int HOURS_PER_SHIFT = 8;

        abstract double overtimePay(double hrs, double payRate);

        double pay(double hoursWorked, double payRate) {
            double basePay = hoursWorked * payRate;
            return basePay + overtimePay(hoursWorked, payRate);
        }
    }
}
```

###### Extend external enum types with constant-specific behavior

* **switches on enums are good for augmenting external enum types with constant-specific behavior**
* **example**: suppose the *Operation* enum is not under your control, and you wish it had an instance method to return
  the inverse of each operation
    - you can simulate the effect with the following static method:

```Java
public class MyClass {
    // Switch on an enum to simulate a missing method
    public static Operation inverse(Operation op) {
        switch (op) {
            case PLUS:
                return Operation.MINUS;
            case MINUS:
                return Operation.PLUS;
            case TIMES:
                return Operation.DIVIDE;
            case DIVIDE:
                return Operation.TIMES;
            default:
                throw new AssertionError("Unknown op: " + op);
        }
    }
}
```

#### When to use enums

* anytime you need a fixed set of constants, which includes:
    - "natural enumerated types" (planets, days of the week, etc.)
    - other sets for which you know all the possible values at compile time (choices on a menu, operation codes, command
      line flags)
* it's not necessary that the set of constants in an enum type stay fixed for all time

#### Summary

* the advantages of enum types over int constants are compelling:
    - Enums are far more readable, safer, and more powerful
    - Many enums require no explicit constructors or members, but many others benefit from *associating data with each
      constant and providing methods* whose behavior is affected by this data.
    - Far fewer enums benefit from associating multiple behaviors with a single method.
        + In this relatively rare case, prefer *constant-specific methods* to enums that switch on their own values.
    - Consider the *strategy enum pattern* if multiple enum constants share common behaviors.

---

### Item 31: Use instance fields instead of ordinals<a name="item31"></a>

* many enums are naturally associated with a single *int* value
* all enums have an **ordinal method**, which returns the numerical position of each enum constant in its type
* **Never derive a value associated with an enum from its ordinal; store it in an instance field instead**
* the enum specification has this to say about *ordinal*:
    - "most programmers will have no use for this method"
    - "it is designed for use by general-purpose enum-based data structures such as *EnumSet* and *EnumMap*"
    - unless you are writing such a data structure, you are best off avoiding the *ordinal* method entirely

---

### Item 32: Use EnumSet instead of bit fields<a name="item32"></a>

#### Bit fields

* if the elements of an enumerated type are used primarily in sets, it is traditional to use the *int enum
  pattern* ([Item 30](#item30)), assigning a different power of 2 to each constant:

```Java
// Bit field enumeration constants - OBSOLETE!
public class Text {
    public static final int STYLE_BOLD          = 1 << 0; // 1
    public static final int STYLE_ITALIC        = 1 << 1; // 2
    public static final int STYLE_UNDERLINE     = 1 << 2; // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3; // 8
    
    // Parameter is bitwise OR of zero or more STYLE_ constants
    public void applyStyles(int styles) { 
        // ... 
    }
}
```

* this representation lets you use the bitwise OR operation to combine several constants into a set, known as a **bit
  field**:

```Java
public class TextClient {
    public static void main(String[] args) {
        text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
    }
}
```

* the bit field representation also lets you perform set operations such as union and intersection efficiently using
  bitwise arithmetic
* but bit fields have all the disadvantages of int enum constants and more
* it is even harder to interpret a bit field than a simple int enum constnant when it is printed as a number
* also, there is no easy way to iterate over all of the elements represented by a bit field

#### EnumSet as better alternative

* the *java.util* package provides the *EnumSet* class to efficiently represent sets of values drawn from a single enum
  type
* this class implements the *Set* interface, providing all of the richness, type safety, and interoperability you get
  with any other *Set* implementation (further discussion see book p. 159)
* here is how the previous example looks when modified to use enums instead of bit fields - it is shorter, clearer, and
  safer:

```Java
// EnumSet - a replacement for bit fields
public class Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH;}

    // Any Set could be passed in, but EnumSet is clearly best
    public void apply(Set<Styles> styles) {
        // ...
    }
}
```

* here is the client code that passes an *EnumSet* instance to the *applyStyles* method; *EnumSet* provides a rich set
  of static factories for easy set creation, one of which is illustrated in this code:

```Java
public class TextClient {
    public static void main(String[] args) {
        text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
```

* In summary, **just because an enumerated type will be used in sets, there is no reason to represent it with bit
  fields**

---

### Item 33: Use EnumMap instead of ordinal indexing<a name="item33"></a>

* occasionally you may see code that uses the *ordinal* method ([Item 31](#item31)) to index into an array
* consider this simplistic class meant to represent a culinary herb:

```Java
class Herb {
    enum Type {ANNUAL, PERENNIAL, BIENNIAL}

    final String name;
    final Type type;

    Herb(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
```

#### The way not to do it - ordinal indexing

* now suppose you have an array of herbs representing the plants in a garden, and you want to list these plants
  organized by type
* you could do this by putting the sets into an array indexed by the type's ordinal:

```Java
// Using ordinal() to index an array - DON'T DO THIS!
class HerbClientOrdinalIndexing {
    public static void main(String[] args) {
        Herb[] garden = getHerbs();

        // Array of sets indexed by Herb.Type.ordinal()
        Set<Herb>[] herbsByType = (Set<Herb>[]) new Set[Herb.Type.values().length];

        for (int i = 0; i < herbsByType.length; i++) {
            herbsByType[i] = new HashSet<Herb>();
        }

        for (Herb h : garden) {
            herbsByType[h.type.ordinal()].add(h);
        }

        // Print the results
        for (int i = 0; i < herbsByType.length; i++) {
            LOGGER.debug("%s: %s%n",
                    Herb.Type.values()[i], herbsByType[i]);
        }
    }
}
```

##### Problems with this approach

* usage of array => not compatible with generics ([Item 25](05_generics.md#item25)), so the program an unchecked cast
* when you access an array that is indexed by an enum's ordinal, it is your responsibility to use the correct *int*
  value; *ints* do not provide the type safety of enums
    - if you use the wrong value, the program will silently do the wrong thing or throw an *
      ArrayIndexOutOfBoundsException*

#### The way to do it - use EnumMap

```Java
// Using an EnumMap to associate data with an enum
class HerbClientEnumSet {
    public static void main(String[] args) {
        Herb[] garden = getHerbs();

        Map<Herb.Type, Set<Herb>> herbsByType = new EnumMap<>(Herb.Type.class);

        for (Herb.Type t : Herb.Type.values()) {
            herbsByType.put(t, new HashSet<>());
        }

        for (Herb h : garden) {
            herbsByType.get(h.type).add(h);
        }

        LOGGER.debug(herbsByType);
    }
}
```

* this program is shorter, clearer, safer, and comparable in speed to the original version:
    - no need to label the output manually, as the map keys are enums that know how to translate themselves to printable
      strings
    - and no possibility for error in computing arrey indices
* **note that the *EnumMap* constructor takes the *Class* object of the key type: this is a *bounded type token*, which
  provides runtime generic type information** ([Item 29](05_generics.md#item29))

* the same is true for cases where, back then, two-dimensional arrays would have been used, also with ordinals for
  indexing
    - here, you also can do much better with *EnumMap*:

```Java
// Using nested EnumMap to associate data with enum pairs
public enum Phase {
    SOLID, LIQUID, GAS;

    public enum Transition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

        private final Phase src;
        private final Phase dst;

        Transition(Phase src, Phase dst) {
            this.src = src;
            this.dst = dst;
        }

        // Initialize the phase transition map
        private static final Map<Phase, Map<Phase, Transition>> m = new EnumMap<>(Phase.class);

        static {
            for (Phase p : Phase.values()) {
                m.put(p, new EnumMap<>(Phase.class));
            }
            for (Transition trans : Transition.values()) {
                m.get(trans.src).put(trans.dst, trans);
            }
        }

        public static Transition from(Phase src, Phase dst) {
            return m.get(src).get(dst);
        }
    }
}
```

* in summary, **it is rarely appropriate to use ordinals to index arrays: use *EnumMap* instead
* if the relationship that you are representing is multidimensional, use *EnumMap\<..., EnumMap\<...\>\>*
    - this is a special case of the general principle that application programmers should rarely, if ever, use *
      Enum.ordinal* ([Item 31](#item31))

---

### Item 34: Emulate extensible enums with interfaces<a name="item34"></a>

#### How to implement extensible enums

* **extensibility is by itself not supported by the enum language feature**
    - this is no accident; for the most part, extensibility of enums turns out to be a bad idea
    - it's confusing that elements of an extension type are instances of the base type and not vice versa
    - ther is no good way to enumerate over all of the elements of a base type and its extensions
* but there is at least one compelling use case for extensible enumerated type, which is *operation codes (or opcodes)*
    - an opcode is an enumerated type whose elements represent operations on some machine, such as the *Operation* type
      in [Item 30](#item30), which represents the functions on a simple calculator
    - sometimes it is desirable to let the users of an API provide their own operations, effectively extending the set
      of operations provided by the API
* to achieve this - the basic idea is to take advantage of the fact that enum types can implement arbitrary interfaces
  by defining an interface for the opcode type and an enum that is the standard implementation of the interface
    - example, here is an extensible version of the *Operation* type from [Item 30](#item30):

```Java
// Emulated extensible enum using an interface
public interface Operation {
    double apply(double x, double y);
}

public enum BasicOperation implements Operation {
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
```

* while the enum type (*BasicOperation*) is not extensible, the interface type (*Operation*) is, and it is the interface
  type that is used to represent operations in APIs
* you can define another enum type that implements this interface and use instances of this new type in place of the
  base type
* example: suppose you want to define an extension to the operation type above, consisting of the exponentiation and
  remainder operations:

```Java
// Emulated extension enum
public enum ExtendedOperation implements Operation {
    EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
```

* you can use your new operations anywhere you could use the basic operations, provided that APIs are written to take
  the interface type (*Operation*), not the implementation (*BasicOperation*)
* Note that you don't have to declare the abstract *apply* method in the enum as you do in a nonextensible enum with
  instance-specific method implementations (see book p. 152)
    - this is because the abstract method (*apply*) is a member of the interface (*Operation*)

#### How to use extensible enums

* a *single instance* or an *entire extension* of an *extension enum type* can be used where a *base enum* is expected -
  these can be used in addition to or instead of those of the base type
    - here is a version of the test program on page 153 in the book that exercises all of the extended operations
      defined above:

```Java
class ExtendedEnumClient {
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test(ExtendedOperation.class, x, y);
    }

    private static <T extends Enum<T> & Operation> void test(
            Class<T> opSet, double x, double y) {
        for (Operation op : opSet.getEnumConstants()) {
            LOGGER.debug("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
        }
    }
}
```

* note that the class literal for the extended operation type (*ExtendedOperation.class*) is passed from *main* to *
  test* to describe the set of extended operations
    - the class literal serves as a *bounded type token ([Item 29](05_generics.md#item29))
* the complex declaration for the *opSet* parameter (*\<T extends Enum\<T\> & Operation\>* with *Class\<T\>*) ensures
  that the *Class* object represents both an enum and a subtype of *Operation*, which is exactly what is required to
  iterate over the elements and perform the operation associated with each one

* a second alternative is to use *Collection\<? extends Operation\>*, which is a *bounded wildcard
  type* ([Item 28](05_generics.md#item28)), as the type for the *opSet* parameter:

```Java
class ExtendedEnumClient {
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test(Arrays.asList(ExtendedOperation.values()), x, y);
    }

    private static void test(Collection<? extends Operation> opSet,
                             double x, double y) {
        for (Operation op : opSet) {
            LOGGER.debug("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
        }
    }
}
```

* the resulting code is a bit less complex, and the *test* method is a bit more flexible:
    - it allows the caller to combine operations from multiple implementation types
* on the other hand, you forgo the ability to use *EnumSet* ([Item 32](#item32)) and *EnumMap* ([Item 33](#item33)) on
  the specified operations, so you are probably better off with the bounded type token unless you need the flexibility
  to combine operations of multiple implementation types

* a minor disadvantage of the use of interfaces to emulate extensible enums is that implementations cannot be inherited
  from one enum type to another
* in summary, **while you cannot write an extensible enum type, you can emulate it by writing an interface to go with a
  basic enum type that implements the interface**

---

### Item 35: Prefer annotations to naming patterns<a name="item35"></a>

* Prior to release 1.5, it was common to use **naming patterns** to indicate that some program elements demanded special
  treatment by a tool or framework (
    - for example, JUnit originally required to designate test methods by beginning their names with the characters *
      test*
* naming patterns have many disadvantages:
    - typographical errors may result in silent failures
    - there is no way to ensure that they are used only on appropriate program elements
    - they provide no good way to associate parameter values with program elements
    - for a full discussion about the disadvantages of naming patterns see the book p. 169
* **annotations** ([JLS, 9.7](https://todo/add/url))) solve all the problems of naming patterns nicely
    - suppose you want to define an annotation type to designate simple tests that are run automatically and fail if
      they throw an exception:

```Java
/**
 * Marker annotation type declaration.
 * Indicates that the annotated method is a test method.
 * Use only on parameterless static methods.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}
```

* the declaration for the *Test* annotation type is itself annotated with **Retention** and **Target** annotations
    - such annotations on annotation type declarations are known as **meta-annotations**
* *@Retention(RetentionPolicy.RUNTIME)* indicates that *Test* annotations should be retained at runtime
    - without it, *Test* annotations would be invisible to the test tool
* *@Target(ElementType.METHOD)* indicates that the *Test* annotation is legal only on method declarations: it cannot be
  applied to class declarations, field declarations, or other program elements
    - the above example says in the comments "use only on parameterless static methods"
    - the compiler will not complain if the annotation is placed above other method types, but the annotation will have
      no effect
* here is how the *Test* annotation looks in practice
    - it is called a **marker annotation**, because it has no parameters but simply "marks" the annotated element
    - if the annotation is placed above other program elements, the compiler will complain:

```Java
// Program containing marker annotations
public class Sample {
    @Test
    public static void m1() {
    } // Test should pass

    public static void m2() {
    }

    @Test
    public static void m3() {   // Test should fail
        throw new RuntimeException("Boom");
    }

    public static void m4() {
    }

    @Test
    public void m5() {
    } // INVALID USE: nonstatic method

    public static void m6() {
    }

    @Test
    public static void m7() { // Test should fail
        throw new RuntimeException("Crash");
    }

    public static void m8() {
    }
}
```

* the *Sample* class contains four tests
    - one will pass, two will fail, and one is invalid as it is no static method

* the *Test* annotations have no direct effect on the semantics of the *Sample* class
    - they serve only to provide information for use by interested programs
    - more generally, annotations never change the semantics of the annotated code, but enable it for special treatment
      by tools such as this *simple test runner*:

```Java
// Program to process marker annotations
public class RunTests {
    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        Class testClass = Class.forName(args[0]);

        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(null);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    LOGGER.debug(m + " failed: " + exc);
                } catch (Exception exc) {
                    LOGGER.debug("INVALID @Test: " + m);
                }
            }
        }
        LOGGER.debug("Passed: %d, Failed: %d%n",
                            passed, tests - passed);
    }
}
```

* the test runner tool takes a fully qualified class name on the command line and runs all of the class's *Test*
  -annotated methods reflectively, by calling *Method.invoke*
* the *isAnnotationPresent* method tells the tool which methods to run
* if a test method throws an exception, the reflection facility wraps it in an *InvocationTargetException*
    - the tool catches this exception and prints a failure report containing the original exception thrown by the test
      method, which is extracted from the *InvocationTargetException* with the *getCause* method
* if an attempt to invoke a test method by reflection throws any exception other than *InvocationTargetException*, it
  indicates an invalid use of the *Test* annotation that was not caught on compile time
    - such uses include annotation of an instance methode, of a method with one or more parameters, or of an
      inaccessible method
    - the second catch block in the test runner catches these *Test* usage errors and prints an appropriate error
      message

* now let's add support for tests that succeed only if they throw a particular exception
    - we need a new annotation type for this:

```Java
/**
 * Annotation type with a parameter.
 * Indicates that the annotated method is a test method that
 * must throw the designated exception to succeed.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {

    Class<? extends Exception> value();
}
```

* the type of the **parameter for this annotation** is *Class\<? extends Exception\>*
    - the wildcard means "the *Class* object for some class that extends *Exception*" and it allows the user of the
      annotation to specify any exception type
    - this usage is an example of a *bounded type token* ([Item 29](05_generics.md#item29))
* here's how the annotation looks in practice
    - note that class literals are used as the values for the annotation parameter:

```Java
// Program containing annotations with a parameter
public class Sample2 {

    @ExceptionTest(ArithmeticException.class)
    public static void m1() { // Test should pass
        int i = 0;
        i = i / i;
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m2() { // Test should fail (wrong exception)
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m3() {
    } // Test should fail (no exception)
}
```

* now let's modify the test runner tool to process the new annotation:

```Java
// Program to process annotation type with a parameter (or array parameter)
public class RunTests2 {
    public static void main(String[] args) throws Exception {

        // ...

        if (m.isAnnotationPresent(ExceptionTest.class)) {
            tests++;
            try {
                m.invoke(null);
                LOGGER.debug("Test %s failed: no exception%n", m);
            } catch (InvocationTargetException wrappedExc) {
                Throwable exc = wrappedExc.getCause();
                Class<? extends Exception> excType =
                        m.getAnnotation(ExceptionTest.class).value();

                if (excType.isInstance(exc)) {
                    passed++;
                } else {
                    LOGGER.debug(
                            "Test %s failed: expected %s, got %s%n",
                            m, excType.getName(), exc);
                }
            } catch (Exception exc) {
                LOGGER.debug("INVALID @Test: " + m);
            }
        }

        // ...
    }
}
```

* the difference to the test runner version before is that this code extracts the value of the annotation parameter and
  uses it to check if the exception thrown by the test is of the right type
    - there is no explicit cast, hence no danger of a *ClassCastException*
* taking our exception testing example one step further, it is possible to envision a test that passes if it throws any
  one of several specified exceptions
    - the annotation mechanism has a facility that makes it easy to support this usage
    - suppose we change the parameter type of the *ExceptionTest* annotation to be an array of *Class* objects:

```Java
// Annotation type with an array parameter
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {

    Class<? extends Exception>[] value();
}
```

* the syntax for array parameters in annotations is flexible - it is optimized for single-element arrays
    - all of the previous *ExceptionTest* annotations are still valid
* to specify a multiple-element array, surround the elements with curly braces and separate them with commas:

```Java
public class Sample2 {

    // ...

    // Code Containing an annotation with an array parameter
    @ExceptionTest({IndexOutOfBoundsException.class,
            NullPointerException.class})
    public static void doublyBad() {
        List<String> list = new ArrayList<>();

        // The spec permits ths method to throw either
        // IndexOutOfBoundsException or NullPointerException
        list.addAll(5, null);
    }
}
```

* it is reasonably straightforward to modify the test runner tool to process the new version of *ExceptionTest*:

```Java
// Program to process annotation type with a parameter (or array parameter)
public class RunTests2 {
    public static void main(String[] args) throws Exception {

        // ...

        if (m.isAnnotationPresent(ExceptionTest.class)) {
            tests++;
            try {
                m.invoke(null);
                LOGGER.debug("Test %s failed: no exception%n", m);
            } catch (InvocationTargetException wrappedExc) {
                Throwable exc = wrappedExc.getCause();
                Class<? extends Exception>[] excTypes =
                        m.getAnnotation(ExceptionTest.class).value();
                int oldPassed = passed;

                for (Class<? extends Exception> excType : excTypes) {
                    if (excType.isInstance(exc)) {
                        passed++;
                        break;
                    }
                }

                if (passed == oldPassed) {
                    LOGGER.debug("Test %s failed: %s %n", m, exc);
                }
            }
        }

        // ...

    }
}
```

#### Summary

* **there is simply no reason to use naming patterns now that we have annotations**
    - that said, with the exception of toolsmiths, most programmers will have no need to define annotation types
* **all programmers should, however, use the the predefined annotation types provided by the Java platform and of tool
  or frameworks in use** ([Item 36](#item36) and [Item 24](05_generics.md#item24))

---

### Item 36: Consistently use the Override annotation<a name="item36"></a>

* the *Override* annotation can be used only on method declarations, and it indicates that the annotated method
  declaration overrides a declaration in a supertype
    - if you consistently use this annotation, it will protect you from a large class of nefarious baugs
* consider this program, in which the class *Bigram* represents a *bigram*, or ordered pair of letters:

```Java
// Can you spot the bug?L1008
public class Bigram {
    private final char first;
    private final char second;
    
    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }
    
    public boolean equals(Bigram b) {
        return b.first == first && b.second == second;
    }
    
    public int hashCode() {
        return 31 * first + second;
    }
    
    public static void main(String[] args) {
        Set<Bigram> s = new HashSet<Bigram>();
        for (int i = 0; i < 10; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                s.add(new Bigram(ch, ch));
            }
        }
        LOGGER.debug(s.size());
    }
}
```

* you migh expect the program to print 26, as sets cannot contain duplicates
    - if you try running the program, you'll find that it prints not 26 but 260 - *What is wrong with it?*

* Clearly, the author of the *Bigram* class intended to override the *equals*
  method ([Item 8](03_methods_common_to_all_objects.md#item8)) and even remembered to override *hashCode* in
  tandem ([Item 9](03_methods_common_to_all_objects.md#item9))
    - Unfortunately, our hapless programmer failed to override *equals*, ocerloading it
      instead ([Item 41](07_methods.md#item41))
    - to override *Object.equals*, you must define an *equals* method whose parameter is of type *Object*, but the
      parameter of *Bifram*'s *equals* method is not of type *Object*, so *Bigram* inherits the *equals* method from *
      Object*
* Luckily, the compiler can help you find this error, but only if you help the compiler by telling it that you intend to
  override *Object.equals*
    - to do this, annotate *Bigram,equals* with *@Override*, as shown below:

```Java
public class Bigram {
    
    // ...

    @Override
    public boolean equals(Bigram b) {
        return b.first == first && b.second == second;    
    }
}
```

* if you insert this annotation and try to recompile the program, the compiler will generate an error message
    - you will immediately realize what you did wrong, slap yourself on the forehead, and replace the broken *equals*
      implementation with a correct one ([Item 8](03_methods_common_to_all_objects.md#item8))

```Java
public class Bigram {
    
    // ...

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Bigram))
            return false;
        Bigram b = (Bigram) o;
        return b.first == first && b.second == second;    
    }
}
```

* **therefore, you should use the *Override* annotation on every method declaration that you believe to override a
  superclass declaration**
    - in that way the compiler can protect you from a great many errors
* there is one minor exception: in concrete classes, you need not annotate methods that you believe to override abstract
  method declarations (though it is not harmful to do so)

---

### Item 37: Use marker interfaces to define types<a name="item37"></a>

* a **marker interface** is an interface that contains no method declarations, but merely designates (or "marks") a
  class that implements the interface as having some property
    - for example, consider the *Serializable* interface ([Chapter 11](11_serialization.md))
    - by implementing this interface, a class indicates that its instances can be written to an *ObjectOutputStream* (
      or "serialized")

* marker annotations ([Item 35](#item35)) *do not* make marker interfaces obsolete - **marker interfaces have two
  advantages** over marker annotations:
    - **marker interfaces define a type that is implemented by instances of the marked class; marker annotations do
      not**
        + the existence of this type allows you to catch errors at compile time that you couldn't catch until runtime if
          you used a marker annotation
    - **marker interfaces can be targeted more precisely**
        + an annotation type is declared with target *ElementType.TYPE*, it can be applied to *any* class or interface

* the **chief advantage of marker annotations** over marker interfaces is that it is possible to add more information to
  an annotation type after it is already in use, by adding one or more annotation type elements with
  default ([JLS, 9.6](https://todo/add/url))
* another advantage of marker annotations is that they are part of the larger annotation facility
    - therefore, marker annotations allow for consistency in frameworks that permit annotation of a variety of program
      elements

#### When to use marker annotations and when to use marker interfaces

* you muse use an annotation if the marker applies to any program element other than a class or interface
* if the marker applies only to classes and interfaces, ask yourself the question:
    - *Might I want to write one or more methods that accept only objects that have this marking?*
        + if so, you should use a marker interface in preference ot an annotation
            - this will make it possible for you to use the interface as a parameter type for the methods in question,
              which will result in the very real benefit of compile-time type checking
        + it not, as yourself one more question: *Do I want to limit the use of the marker to elements of particular
          interface, forever?*
            - if yes, it makes sense to define the marker as a subinterface of that interface
            - if no to both questions, you should probably use a marker annotation

* **If you find yourself writing a marker annotation type whose target is *ElementType.TYPE*, take the time to figure
  out whether it really should be an annotation type, or whether a marker interface would be more appropriate**
* in a sense, this item is the inverse of [Item 19](04_classes_and_interfaces.md#item19), which says, "if you don't want
  to define a type, don't use an interface"
    - to a first approximation, this item says, "if you do want to define a type, do use an interface"