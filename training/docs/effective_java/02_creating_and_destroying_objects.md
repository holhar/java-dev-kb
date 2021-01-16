# Effective Java

## 2. Creating and Destroying Objects

This chapter concerns creating and destroying objects: when and how to create them, when and how to avoid creating them,
how to ensure they are destroyed in a timely manner, and how to manage any cleanup actions that must precede their
destruction.

---

### Item 1: Consider static factory methods instead of constructors<a name="item1"></a>

* Example:

```Java
class MyClass {

    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
}
```

#### Advantages

* they have names - names describe the returned and created object better and make the usage for clients easier
* they are not required to create a new object each time they're invoked - they empower *instance-control* and make
  specific patterns possible, like [immutable classes](04_classes_and_interfaces.md#item15), [singleton](#item3)
  or [noninstantiable classes](#item4) <= [Enum types](06_enums_and_annotations.md#item30) provide this guarantee
* they reduce the verbosity of creating parameterized type instances

```Java
class MyClass {
    // Bad: verbose parameterization
    Map<String, List<String>> m = new HashMap<String, List<String>>();
    
    // Better: simple instantiation
    Map<String, List<String>> m = HashMap.newInstance();
    
    // Implementation of static factory 'newInstance()'
    public static <K, V> HashMap<K, V> newInstance() {
        return new HashMap<K, V>();
    }
}
```

* they can return an object of any subtype of their return type
    - this gives you great flexibility in choosing the class of the returned object
    - in this case they form the basis of *service provider frameworks* (see example below)

#### Service provider framework sketch

```Java
// service interface
public interface Service {
    Service newService();
}

// Noninstantiable class for service registration and access
public class Services {
    private Services() { } // [Prevents instantiation](#item4)

    // Maps service names to services
    private static final Map<String, Provider> providers = new ConcurrentHashMap<String, Provider>();
    public static final String DEFAULT_PROVIDER_NAME = "<def>";

    // Provider registration API
    public static void registerDefaultProvider(Provider p) {
        registerProvider(DEFAULT_PROVIDER_NAME, p);
    }

    public static void registerProvider(String name, Provider p) {
        providers.put(name, p);
    }

    // Service access API
    public static Service newInstance() {
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    public static Service newInstance(String name) {
        provider p = providers.get(name);
        if(p == null) {
            throw new IllegalArgumentException("No provider registered with name: " + name);
        }
        return p.newInstance();
    }
}
```

##### Disadvantages

* (if only static factory methods are provided) classes without public or protected constructors cannot be subclassed
* they are not readily distinguishable from other static methods - here are some common names for static factory
  methods:
    * valueOf — Returns an instance that has, loosely speaking, the same value as its parameters. Such static factories
      are effectively type-conversion methods.
    * of — A concise alternative to valueOf, popularized by EnumSet (Item 32).
    * getInstance—Returns an instance that is described by the parameters but cannot be said to have the same value. In
      the case of a singleton, getInstance takes no parameters and returns the sole instance.
    * newInstance — Like getInstance, except that newInstance guarantees that each instance returned is distinct from
      all others.
    * getType — Like getInstance, but used when the factory method is in a different class. Type indicates the type of
      object returned by the factory method.
    * newType — Like newInstance, but used when the factory method is in a different class. *Type* indicates the type of
      object returned by the factory method.

#### Summary

* static factory methods and public constructors both have their uses, and it pays to understand their relative merits
* often static factories are preferable, so avoid the reflex to provide public constructors without first considering
  static factories

---

### Item 2: Consider a builder when faced with many constructor parameters<a name="item2"></a>

* Alternatives to builder: *telescoping constructor* and *JavaBeans pattern*

#### Telescoping constructor pattern

```Java
// Telescoping constructor pattern - does not scale well!
public class NutritionFacts {
    private final int servingSize;      // (mL)             required
    private final int servings;         // (per container)  required
    private final int calories;         //                  optional
    private final int fat;              // (g)              optional
    private final int sodium;           // (mg)             optional
    private final int carbohydrate;     // (g)              optional

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, 
            int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this. fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}

// ...

// Client usage:
public class NutritionFactsClients {
    // When you want to create an instance, you use the constructor with the shortest parameter list containing all the parameters you want to set
    NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 0, 35, 27);
}
```

##### Disadvantage

* The telescoping constructor pattern works, but it is hard to write client code when there are many parameters, and
  harder still to read it!

#### JavaBeans Pattern

```Java
// JavaBeans Pattern - allows inconsistency, mandates mutability
public class NutritionFacts {
    // Parameters initialized to default values (if any)
    private int servingSize = -1; // Required; no default value
    private int servings = -1; // Required; no default value
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public NutritionFacts() { }

    // Setters
    public void setServingSize(int val) { servingSize = val; }
    // ...
}

// Client usage:
public class NutritionFactsClient {
    public static void main(String[] args) {
        // has none of the disadvantages of the telescoping constructor pattern 
        // -> easy, a bit wordy, and easy to read
        NutritionFacts cocaCola = new NutritionFacts();
        cocaCola.setServingSize(240);
        // ...
    }
}
```

##### Disadvantages

* a JavaBean may be in an inconsistent state partway through its construction
* the JavaBeans pattern precludes the possibility of making a class immutable

#### Builder Pattern

```Java
// Builder Pattern
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;
    
    public static class Builder {
        // Required parameters
        private final int servingSize;
        private final int servings;

        // Optional parameters - initialized to default values
        private final int calories;
        private final int fat;
        private final int sodium;
        private final int carbohydrate;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }
        public Builder fat(int val) {
            fat = val;
            return this;
        }
        public Builder sodium(int val) {
            sodium = val;
            return this;
        }
        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
         fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}

// Client usage:
public class NutritionFactsClient {
    public static void main(String[] args) {    
        // Note that NutritionFacts is immutable, and that all parameter default 
        // values are in a single location; the builder's setter methods return the 
        // builder itself so that invocations can be chained
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8).
            calories(100).sodium(35).carbohydrate(27).build();
    }
}
```

* The Builder pattern simulates named optional parameters
* The Builder pattern is a good choice when designing classes whose constructors or static facories would have more than
  a handful of parameters

---

### Item 3: Enforce the singleton property with a private constructor or an enum type<a name="item3"></a>

* Making a class a singleton can make it difficult to test its clients

* **Useful if you want a class unique, such as the window manager or file system**

```Java
// 1. approach: Singleton with public final field
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() { 
        // ... 
    }

    public void leaveTheBuilding() { 
        // ... 
    }
}

// 2. approach: Singleton with static factory
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis() { 
        // ... 
    }
    public static Elvis getInstance() { return INSTANCE; }

    public void leaveTheBuilding() { 
        // ... 
    }
}

public class Elvis {
    
    // 3. approach: readResolve method to preserve singleton property 
    // (in cases concurrency and/or serialization have to be considered)
    private Object readResolve() {
        // Return the one true Elvis and let the garbage collector 
        // take care of the Elvis impersonator
        return INSTANCE;
    }
}

// 4. approach: Enum singleton - the preferred approach
public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding() { 
        //... 
    }
}
```

* a single-element enum type is the best way to implement a singleton

---

### Item 4: Enforce noninstantiability with a private constructor<a name="item4"></a>

* Attempting to enforce noninstantiability by making a class abstract does not work => the class can be subclassed and
  the subclass instantiated
* A class can be made noninstantiable by including a private constructor
* **This is useful for utility classes that group static methods and static fields**

```Java
// Noninstantiable utility class
public class UtilityClass {
    // Suppress default constructor for noninstantiability
    private UtilityClass() {
        throw new AssertionError();
    }
    // ... Remainder omitted
}
```

* A comment that states the instantiability is useful
* The AssertionError isn't required, but it provides insurance in case the constructor is accidentally invoked from
  within the class
* this idiom also prevents the class from being subclassed

---

### Item 5: Avoid creating unnecessary objects<a name="item5"></a>

* It is often appropriate to reuse a single object instead of creating a new functionally equivalent object each time it
  is neeed
* Reuse can be both faster and more stylish
* An object can always be reused if it is [immutable](04_classes_and_interfaces.md#item15)
* You can often avodi creating unnecessary objects by using [static factory methods](#item1) in preference to
  constructors on immitable classes that provide both
* You can also reuse mutable objects if you know they won't be modified => consider the following example that
  illustrates an *anti-pattern* as well as a good idiom

```Java
// Anti-Pattern
public class Person {
    private final Date birthdate;
    // Other fields, methods, and constructor omitted

    // DON'T DO THIS!
    public boolean isBabyBoomer() {
        // Unnecessary allocation of expensive object
        Calendar gmtCal = 
            Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >= 0 && 
                birthDate.compareTo(boomEnd) < 0;
    }   
}
```

The isBabaBoomer method unnecessarily create a new Calendar, TimeZone, and two Date instances each time it is invoked.
The version that follows avoids this inefficiency with a static initializer:

```Java
public class Person {
    private final Date birthdate;
    // Other fields, methods, and constructor omitted

    /**
     * The starting and ending dates of the baby boom.
     */
    private static final Date BOOM_START;
    private static final Date BOOM_END;

    static {
        Calendar gmtCal = 
            Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date BOOM_END = gmtCal.getTime();
    }

    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 && 
                birthDate.compareTo(BOOM_END) < 0;
    }
}
```

The improved version of the Person class creates Calendar, TimeZone, and Date instances only once, when it is
initialized, instead of creating them every time isBabyBoomer is invoked!!!

---

**Further Recommendations**

* Pefer primitives to boxed primitives, and wathc out for unintentional autoboxing:

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

* Conversely, avoiding object creation by maintaining your own **object pool** is a bad idea unless the objects in the
  ppol are extremely heafyweigth => the classic example of an object that **does** justify an object pool is a database
  connection
* The counterpoint to this item is [Item 39 on defensive copying](07_methods.md#item39)

---

### Item 6: Eliminate obsolete object references<a name="item6"></a>

* null out references once they become obsolete - example: in case of a Stack class, the reference to an item becomes
  obsolete as soon as it's popped off the stack
* added benefit with this approach: if nulled out by mistake a *NullPointerException* will be thrown - **fail fast!**
* But still: **nulling out object references should be the exception rather than the norm**
    - The best way to eliminate an obsolete reference is to let the variable that contained the reference fall out of
      scope. This occurs naturally if
      you [define each variable in the narrowest possible scope](08_general_programming.md#item45).
* **Whenever a class manages its own memory, the programmer should be alert for memory leaks**.
* Another common source of memory leaks is **caches** - caches need to be cleaned after some time!
* memory leaks are hard to discover and hard to debug - *heap profilers* can help during the debugging process

---

### Item 7: Avoid finalizers

* **Finalizers are unpredictable, often dangerous, and generally unnecessary - avoid them!**
* Still, if in use:
    - never do anything time-critical in a finalizer
    - never depend on a finalizer to update critical persistent state
* There is a *severe* performance penalty for using finalizers
* **Instead provide an *explicit termination method***, and require clients of the class to invoke this method on each
  instance when it is no longer needed
* Explicit termination methods are typically used in combination with the **try-finally** constrct to ensure termination

```Java
class MyClass {
    public static void main(String[] args) {
        Foo foo = new Foo(
                    // ...
                );
        
        try {
            // Do what must be done with foo
            // ...
        } finally {
            foo.terminate(); // Explicit termination method
        }        
    }
}
```

* So what, if anything, are finalizers good for?:
    - they can act as a "safety net"in case the owner of an object forgets to call its explicit termination method
    - the finalizer should/could log a warning if it finds that the resource has not been terminated, as this indicates
      a bug in the client code, that should be fixed
    - can be used to finalize *native peers* (a native object to which a normal object delegates via native methods),
      assuming the native peer holds no critical resources
* if in use, one should use **super.finalize** - if this can not be ensured, consider so called **finalizer guardians**
  which can be created to protect against careless use of *finalize* on an object:

```Java
// Finalizer Guardian idiom
public class Foo {
    // Sole purpose of the object is to finalize outer Foo object
    private final Object finalizerGuardian = new Object() {
        @Override protected void finalize() throws Throwable {
            // ... // Finalize outer Foo object
        }
    };
    // ... // Remainder omitted
}
```
