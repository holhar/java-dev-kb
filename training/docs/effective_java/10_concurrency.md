# Effective Java

## Concurrency

* threads allow multiple activities to proceed concurrently
    - concurrent programming is harder than single-threaded programming, because more things can go wrong, and failures
      can be hard to reproduce
    - but you can't avoid concurrency
    - it is inherent in much of what we do, and a requirement if you are to obtain good performance from multicore
      processors, which are now commonplace
    - this chapter contains advice to help you write clear, correct, well-documented concurrent programs

---

### Item 66: Synchronize access to shared mutable data<a name="item66"></a>

* the *synchronized* keyword ensures that only a single thread can execute a method or block at one time
    - many programmers think of synchronization solely as a means of mutual exclusion, to prevent an object from being
      observed in an inconsistent state while it's being modified by another thread
    - in this view, an object is created in a consistent state ([Item 15](04_classes_and_interfaces.md#item15)) and
      locked by the methods that access it
    - these methods observe the state and optionally cause a **state transition**, transforming the object from on
      consistent state to another
    - proper use of synchronization guarantees that no method will ever observe the object in an inconsistent state
* this view is correct, but it's only half the story
    - without synchronization, one thread's changes might not be visible to other threads
    - not only does synchronization prevent a thread from observing an object in an inconsistent state, but it ensures
      that each thread entering a synchronized method or block sees the effects of all previous modifications that were
      guarded by the same lock
* **synchronization is required for reliable communication between threads as well as for mutual exclusion**
    - the consequences of failing to synchronize access to shared mutable data can be dire even if the data is
      atomically readable and writable
    - **do not use Thread.stop** - this method was deprecated long ago because it is inherently *unsafe*
        + a recommended way to stop one thread from another is to have the first thread poll a *boolean* field that is
          initially *false* but can be set to *true* by the second thread to indicate that the first thread is to stop
          itself
        + because reading and writing a *boolean* field is atomic, some programmers dispense with synchronization when
          accessing the field:

```Java
// Broken! - How long would you expect this program to run?
public class BrokenStopThread {
  
    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override public void run() {
                int i = 0;
                while(!stopRequested)
                    i++;
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
```

* you might expect this program to run for about a second, after which the main thread set *stopRequested* to *true*,
  causing the background thread's loop to terminate
    - on my machine, however, the program *never* terminates: the background thread loops forever! (But on *my* machine,
      now in the year 2017, it actually does stop after one second)
    - the problem is that in the absence of synchronization, there is no guarantee as to when, if ever, the background
      thread will see the change in the value of *stopRequested* that was made by the main thread
* one way to fix the problem is to synchronize access to the *stopRequested* field
    - this program terminates in about one second, as expected:

```Java
// Properly synchronized cooperative thread termination
public class FixedStopThread {
  
    private static boolean stopRequested;

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized boolean isStopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override public void run() {
                int i = 0;
                while(!isStopRequested())
                    i++;
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }
}
```

* **NOTE: synchronization has no effect unless both, read and write operations, are synchronized**
* the actions of the synchronized methods in StopThread would be atomic even without synchronization
    - in other words, the synchronization on these methods is used solely for its communication effects, not for mutual
      exclusion
    - while the cost of synchronizing on each iteration of the loop is small, there is a correct alternative that is
      less verbose and whose performance is likely to be better
    - the locking in the second version of StopThread can be omitted if *stopRequested* is declared **volatile**
    - while the volatile modifier performs no mutual exclusion, it guarantees that any thread that reads the field will
      see the most recently written value:

```Java
// Cooperative thread termination with a volatile field
public class VolatileStopThread {
    private static volatile boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(!stopRequested)
                    i++;
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
```

* you do have to be careful when using *volatile*
    - consider the following method, which is supposed to generate serial numbers:

```Java
class SomeClass {
    // Broken - requires synchronization!
    private static volatile int nextSerialNumber = 0;
    
    public static int generateSerialNumber() {
        return nextSerialNumber++;
    }
}
```

* the problem is that the increment operator (*++*) is not atomic
    - it performs two operations on the *nextSerialNumber* field:
        + first it reads the value, then it writes back a new value, equal to the old value plus one
        + if a second thread reads the field between the time a thread reads the old value and writes back a new one,
          the second thread will see the same value as the first and return the same serial number
        + this is a safety failure: the program computes the wrong results.
    - one way to fix the *generateSerialNumber* method is to add the synchronized modifier to its declaration
        + once you’ve done that, you can and should remove the *volatile* modifier from *nextSerialNumber*
        + to bulletproof the method, use *long* instead of int, or throw an exception if *nextSerialNumber* is about to
          wrap
    - better still, follow the advice in [Item 47](08_general_programming.md#item47) and use the class *AtomicLong*,
      which is part of *java.util.concurrent.atomic*
        + it does exactly what you want and is likely to perform better than the synchronized version of *
          generateSerialNumber*:

```Java
class SomeClass {
    private static final AtomicLong nextSerialNum = new AtomicLong();
    
    public static long generateSerialNumber() {
        return nextSerialNum.getAndIncrement();
    }
}
```

* the best way to avoid the problems discussed in this item is not to share mutable data
    - either share immutable data ([Item 15](04_classes_and_interfaces.md#item15)), or don’t share at all
    - in other words, **limit mutable data to a single thread**
    - if you adopt this policy, it is important to document it, so that it is maintained as your program evolves
    - it is also important to have a deep understanding of the frameworks and libraries you’re using, as they may
      introduce threads that you are unaware of
* in summary, **when multiple threads share mutable data, each thread that reads or writes the data must perform
  synchronization**
    - if you need only inter-thread communication, and not mutual exclusion, the *volatile* modifier is an acceptable
      form of synchronization, but it can be tricky to use correctly

---

### Item 67: Avoid excessive synchronization<a name="item67"></a>

* [Item 66](#item66) warns of the dangers of insufficient synchronization
    - this item concerns the opposite problem - depending on the situation, excessive synchronization can cause reduced
      performance, deadlock, or even nondeterministic behavior
* **to avoid liveness and safety failures, never hand over control to the client within a synchronized method or block**
    - in other words, inside a synchronized region, do not invoke a method that is designed to be overridden, or one
      provided by a client in the form of a function object ([Item 21](04_classes_and_interfaces.md#item21))
    - from the perspective of the class with the synchronized region, such methods are alien
    - the class has no knowledge of what the method does and has no control over it
    - depending on what an alien method does, calling it from a synchronized region can cause exceptions, deadlocks, or
      data corruption
* to make this concrete, consider the following class, which implements an observable set wrapper
    - tt allows clients to subscribe to notifications when elements are added to the set
    - this is the Observer pattern [Gamma95, p. 293]
    - for brevity's sake, the class does not provide notifications when elements are removed from the set, but it would
      be a simple matter to provide them
    - this class is implemented atop the reusable *ForwardingSet* from [Item 16](04_classes_and_interfaces.md#item16):

```Java
// Broken - invokes alien method from synchronized block!
public class ObservableSet<E> extends ForwardingSet<E> {
    public ObservableSet(Set<E> set) {
        super(set);
    }

    private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E element) {
        synchronized (observers) {
            for (SetObserver<E> observer : observers)
                observer.added(this, element);
        }
    }

    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        if (added)
            notifyElementAdded(element);
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E element : c)
            result |= add(element); // calls notifyElementAdded
        return result;
    }
}
```

* Observers subscribe to notifications by invoking the *addObserver* method and unsubscribe by invoking the *
  removeObserver* method
    - in both cases, an instance of this callback interface is passed to the method:

```Java
public interface SetObserver<E> {
    // Invoked when an element is added to the observable set
    void added(ObservableSet<E> set, E element);
}
```

* on cursory inspection, *ObservableSet* appears to work
    - for example, the following program prints the numbers from 0 through 99:

```Java
class ObservableSetClient {

    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());

        set.addObserver(new SetObserver<Integer>() {

            public void added(ObservableSet<Integer> s, Integer e) {
                LOGGER.debug(e);
            }
        });

        for (int i = 0; i < 100; i++)
            set.add(i);
    }
}
```

* now let’s try something a bit fancier
    - suppose we replace the *addObserver* call with one that passes an observer that prints the *Integer* value that
      was added to the set and removes itself if the value is 23:

```Java
class ObservableSetClient {

    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());

        set.addObserver(new SetObserver<Integer>() {
            public void added(ObservableSet<Integer> s, Integer e) {
                LOGGER.debug(e);
                if (e == 23) s.removeObserver(this);
            }
        });

        for (int i = 0; i < 100; i++)
            set.add(i);
    }
}
```

* You might expect the program to print the numbers 0 through 23, after which the observer would unsubscribe and the
  program complete its work silently
    - what actually happens is that it prints the numbers 0 through 23, and then throws a *
      ConcurrentModificationException*
    - the problem is that *notifyElementAdded* is in the process of iterating over the observers list when it invokes
      the observer's added method
    - the added method calls the observable set's *removeObserver* method, which in turn calls *observers.remove*
    - now we are in trouble
    - we are trying to remove an element from a list in the midst of iterating over it, which is illegal
    - the iteration in the *notifyElementAdded* method is in a synchronized block to prevent concurrent modification,
      but it doesn't prevent the iterating thread itself from calling back into the observable set and modifying its
      observers list
* now let's try something odd:
    - let's write an observer that attempts to unsubscribe, but instead of calling *removeObserver* directly, it engages
      the services of another thread to do the deed
    - this observer uses an executor service ([Item 68](#item68)):

```Java
class ObservableSetClient {

    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());

        // Observer that uses a background thread needlessly
        set.addObserver(new SetObserver<Integer>() {
            public void added(final ObservableSet<Integer> s, Integer e) {
                LOGGER.debug(e);
                if (e == 23) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();

                    final SetObserver<Integer> observer = this;

                    try {
                        executor.submit(new Runnable() {
                            public void run() {
                                s.removeObserver(observer);
                            }
                        }).get();
                    } catch (ExecutionException ex) {
                        throw new AssertionError(ex.getCause());
                    } catch (InterruptedException ex) {
                        throw new AssertionError(ex.getCause());
                    } finally {
                        executor.shutdown();
                    }
                }
            }
        });

        for (int i = 0; i < 100; i++)
            set.add(i);
    }
}
```

* this time we don't get an exception; we get a deadlock
    - the background thread calls *s.removeObserver*, which attempts to lock observers, but it can't acquire the lock,
      because the main thread already has the lock
    - all the while, the main thread is waiting for the background thread to finish removing the observer, which
      explains the deadlock
* this example is contrived because there is no reason for the observer to use a background thread, but the problem is
  real
    - invoking alien methods from synchronized regions has caused many deadlocks in real systems, such as GUI toolkits
* in both of the previous examples (the exception and the deadlock) we were lucky
    - the resource that was guarded by the synchronized region (observers) was in a consistent state when the alien
      method (added) was invoked
    - suppose you were to invoke an alien method from within a synchronized region while the invariant protected by the
      synchronized region was temporarily invalid
    - because locks in the Java programming language are reentrant, such calls won’t deadlock
    - as in the first example, which resulted in an exception, the calling thread already holds the lock, so the thread
      will succeed when it tries to reacquire the lock, even though another conceptually unrelated operation is in
      progress on the data guarded by the lock
    - the consequences of such a failure can be catastrophic
    - in essence, the lock has failed to do its job.
    - *Reentrant* locks simplify the construction of multithreaded object-oriented programs, but they can turn liveness
      failures into safety failures
* luckily, it is usually not too hard to fix this sort of problem by moving alien method invocations out of synchronized
  blocks
    - for the *notifyElementAdded* method, this involves taking a "snapshot" of the observers list that can then be
      safely traversed without a lock
    - with this change, both of the previous examples run without exception or deadlock:

```Java
public class FixedObservableSet<E> extends ForwardingSet<E> {
    // ...

    // Alien method moved outside of synchronized block - open calls
    private void notifyElementAdded(E element) {
        List<SetObserver<E>> snapshot = null;
        synchronized (observers) {
            snapshot = new ArrayList<SetObserver<E>>(observers);
        }
        for (SetObserver<E> observer : snapshot)
            observer.added(this, element);
    }

    // ...
}
```

* in fact, there's a better way to move the alien method invocations out of the synchronized block
    - since release 1.5, the Java libraries have provided a concurrent collection ([Item 69](#item69)) known as *
      CopyOnWriteArrayList*, which is tailor-made for this purpose
    - it is a variant of *ArrayList* in which all write operations are implemented by making a fresh copy of the entire
      underlying array
    - because the internal array is never modified, iteration requires no locking and is very fast
    - for most uses, the performance of *CopyOnWriteArrayList* would be atrocious, but it's perfect for observer lists,
      which are rarely modified and often traversed
* the add and *addAll* methods of *ObservableSet* need not be changed if the list is modified to use *
  CopyOnWriteArrayList*
    - here is how the remainder of the class looks
    - notice that there is no explicit synchronization whatsoever:

```Java
public class FixedObservableSet<E> extends ForwardingSet<E> {
    // ...

    // Thread-safe observable set with CopyOnWriteArrayList
    private final List<FixedSetObserver<E>> observers = new CopyOnWriteArrayList<FixedSetObserver<E>>();

    public void addObserver(FixedSetObserver<E> observer) {
        observers.add(observer);
    }

    public boolean removeObserver(FixedSetObserver<E> observer) {
        return observers.remove(observer);
    }

    private void notifyElementAdded(E element) {
        for (FixedSetObserver<E> observer : observers)
            observer.added(this, element);
    }

    // ...
}
```

* an alien method invoked outside of a synchronized region is known as an open call [Lea00 2.4.1.3]
    - besides preventing failures, open calls can greatly increase concurrency
    - an alien method might run for an arbitrarily long period
    - if the alien method were invoked from a synchronized region, other threads would be denied access to the protected
      resource unnecessarily
* **as a rule, you should do as little work as possible inside synchronized regions**
    - obtain the lock, examine the shared data, transform it as necessary, and drop the lock
    - if you must perform some time-consuming activity, find a way to move the activity out of the synchronized region
      without violating the guidelines in [Item 66](#item66)

#### Performance

* the first part of this item was about correctness
    - now let's take a brief look at performance
    - while the cost of synchronization has plummeted since the early days of Java, it is more important than ever not
      to oversynchronize
    - in a multicore world, the real cost of excessive synchronization is not the CPU time spent obtaining locks; it is
      the lost opportunities for parallelism and the delays imposed by the need to ensure that every core has a
      consistent view of memory
    - another hidden cost of oversynchronization is that it can limit the VM’s ability to optimize code execution
* you should make a mutable class thread-safe ([Item 70](#item70)) if it is intended for concurrent use and you can
  achieve significantly higher concurrency by synchronizing internally than you could by locking the entire object
  externally
    - otherwise, don't synchronize internally
    - let the client synchronize externally where it is appropriate
    - in the early days of the Java platform, many classes violated these guidelines
    - for example, *StringBuffer* instances are almost always used by a single thread, yet they perform internal
      synchronization
    - it is for this reason that *StringBuffer* was essentially replaced by *StringBuilder*, which is an
      unsynchronized *StringBuffer*, in release 1.5. When in doubt, do not synchronize your class, but document that it
      is not thread-safe ([Item 70](#item70)).
* if you do synchronize your class internally, you can use various techniques to achieve high concurrency, such as lock
  splitting, lock striping, and nonblocking concurrency control
    - these techniques are beyond the scope of this book, but they are discussed elsewhere [Goetz06, Lea00]
* if a method modifies a static field, you must synchronize access to this field, even if the method is typically used
  only by a single thread
    - it is not possible for clients to perform external synchronization on such a method because there can be no
      guarantee that unrelated clients will do likewise
    - the *generateSerialNumber* method in [Item 66](#item66) exemplifies this situation.

#### Summary

* to avoid deadlock and data corruption, never call an alien method from within a synchronized region
* more generally, try to limit the amount of work that you do from within synchronized regions
* when you are designing a mutable class, think about whether it should do its own synchronization
* in the modern multicore era, it is more important than ever not to synchronize excessively
* synchronize your class internally only if there is a good reason to do so, and document your decision
  clearly ([Item 70](#item70)).

---

### Item 68: Prefer executors and tasks to threads<a name="item68"></a>

* the first edition of this book contained code for a simple work queue
    - this class allowed clients to enqueue work items for asynchronous processing by a background thread
    - when the work queue was no longer needed, the client could invoke a method to ask the background thread to
      terminate itself gracefully after completing any work that was already on the queue
    - the implementation was little more than a toy, but even so, it required a full page of subtle, delicate code, of
      the sort that is prone to safety and liveness failures if you don’t get it just right
    - luckily, there is no reason to write this sort of code anymore
* In release 1.5, *java.util.concurrent* was added to the Java platform
    - this package contains an **Executor Framework**, which is a flexible interface-based task execution facility
    - creating a work queue that is better in every way than the one in the first edition of this book requires but a
      single line of code:

```
ExecutorService executor = Executors.newSingleThreadExecutor();
```

* Here is how to submit a runnable for execution:

```
executor.execute(runnable);
```

* And here is how to tell the executor to terminate gracefully (if you fail to do this, it is likely that your VM will
  not exit):

```
executor.shutdown();
```

* You can do many more things with an executor service
    - for example, you can wait for a particular task to complete (as in the "background thread SetObserver"
      in [Item 67](#item67)), you can wait for any or all of a collection of tasks to complete (using the *invokeAny*
      or *invokeAll* methods), you can wait for the executor service's graceful termination to complete (using the *
      awaitTermination* method), you can retrieve the results of tasks one by one as they complete (using an *
      ExecutorCompletionService*), and so on
* If you want more than one thread to process requests from the queue, simply call a different static factory that
  creates a different kind of executor service called a thread pool
    - you can create a thread pool with a fixed or variable number of threads
    - the *java.util.concurrent.Executors* class contains static factories that provide most of the executors you'll
      ever need
    - if, however, you want some thing out of the ordinary, you can use the *ThreadPoolExecutor* class directly
    - this class lets you control nearly every aspect of a thread pool’s operation
* Choosing the executor service for a particular application can be tricky
    - if you're writing a small program, or a lightly loaded server, using *Executors.newCachedThreadPool* is generally
      a good choice, as it demands no configuration and generally "does the right thing"
    - but a cached thread pool is not a good choice for a heavily loaded production server!
    - in a cached thread pool, submitted tasks are not queued but immediately handed off to a thread for execution
    - if no threads are available, a new one is created
    - if a server is so heavily loaded that all of its CPUs are fully utilized, and more tasks arrive, more threads will
      be created, which will only make matters worse
    - therefore, in a heavily loaded production server, you are much better off using *Executors.newFixedThreadPool*,
      which gives you a pool with a fixed number of threads, or using the *ThreadPoolExecutor* class directly, for
      maximum control
* **Not only should you refrain from writing your own work queues, but you should generally refrain from working
  directly with threads**
    - the key abstraction is no longer *Thread*, which served as both the unit of work and the mechanism for executing
      it
    - now the unit of work and mechanism are separate
    - the key abstraction is the unit of work, which is called a task
    - there are two kinds of tasks:
        + **Runnable** and its close cousin, **Callable** (which is like Runnable, except that it returns a value)
        + the general mechanism for executing tasks is the executor service
    - if you think in terms of tasks and let an executor service execute them for you, you gain great flexibility in
      terms of selecting appropriate execution policies
    - **in essence, the *Executor Framework* does for execution what the Collections Framework did for aggregation**
* The *Executor Framework* also has a replacement for *java.util.Timer*, which is *ScheduledThreadPoolExecutor*
    - while it is easier to use a timer, a scheduled thread pool executor is much more flexible
    - a timer uses only a single thread for task execution, which can hurt timing accuracy in the presence of
      longrunning tasks
    - if a timer's sole thread throws an uncaught exception, the timer ceases to operate
    - a scheduled thread pool executor supports multiple threads and recovers gracefully from tasks that throw unchecked
      exceptions
* A complete treatment of the *Executor Framework* is beyond the scope of this book, but the interested reader is
  directed to *Java Concurrency in Practice*

---

### Item 69: Prefer concurrency utilities to wait and notify<a name="item69"></a>

* the first edition of this book devoted an item to the correct use of *wait* and *notify*
    - its advice is still valid and is summarized at end of this item, but this advice is far less important than it
      once was
    - this is because there is far less reason to use *wait* and *notify*
    - as of release 1.5, the Java platform provides higher-level concurrency utilities that do the sorts of things you
      formerly had to hand-code atop *wait* and *notify*
    - **given the difficulty of using *wait* and *notify* correctly, you should use the higher-level concurrency
      utilities instead**
* the higher-level utilities in *java.util.concurrent* fall into three categories:
    - the Executor Framework, which was covered only briefly in [Item 68](#item68);
    - concurrent collections;
    - and synchronizers
    - *concurrent collections and synchronizers are covered briefly in this item*
* the concurrent collections provide high-performance concurrent implementations of standard collection interfaces such
  as *List*, *Queue*, and *Map*
    - to provide high concurrency, these implementations manage their own synchronization
      internally ([Item 67](#item67))
    - **therefore, it is impossible to exclude concurrent activity from a concurrent collection; locking it will have no
      effect** but to slow the program
* this means that clients can't atomically compose method invocations on concurrent collections
    - some of the collection interfaces have therefore been extended with state-dependent modify operations, which
      combine several primitives into a single atomic operation
    - for example, *ConcurrentMap* extends *Map* and adds several methods, including *putIfAbsent(key, value)*, which
      inserts a mapping for a key if none was present and returns the previous value associated with the key, or *null*
      if there was none
    - this makes it easy to implement thread-safe canonicalizing maps
    - for example, this method simulates the behavior of *String.intern*:

```Java
class SomeClass {
    // Concurrent canonicalizing map atop ConcurrentMap - not optimal
    private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
    
    public static String intern(String s) {
        String previousValue = map.putIfAbsent(s, s);
        return previousValue == null ? s : previousValue;
    }
}
```

* in fact, you can do even better
    - *ConcurrentHashMap* is optimized for retrieval operations, such as *get*
    - therefore, it is worth invoking *get* initially and calling *putIfAbsent* only if *get* indicates that it is
      necessary:

```Java
class SomeClass {
    // Concurrent canonicalizing map atop ConcurrentMap - faster!
    public static String intern(String s) {
        String result = map.get(s);
        if (result == null) {
            result = map.putIfAbsent(s, s);
            if (result == null)
                result = s;
        }
        return result;
    }
}
```

* besides offering excellent concurrency, *ConcurrentHashMap* is very fast
    - on my machine the optimized intern method above is over six times faster than *String.intern* (but keep in mind
      that *String.intern* must use some sort of weak reference to keep from leaking memory over time)
    - unless you have a compelling reason to do otherwise, **use *ConcurrentHashMap* in preference to *
      Collections.synchronizedMap* or *Hashtable***
    - simply replacing old-style synchronized maps with concurrent maps can dramatically increase the performance of
      concurrent applications
    - more generally, use concurrent collections in preference to externally synchronized collections
* some of the collection interfaces have been extended with blocking operations, which *wait* (or *block*) until they
  can be successfully performed
    - for example, *BlockingQueue* extends *Queue* and adds several methods, including *take*, which removes and returns
      the head element from the queue, waiting if the queue is empty
    - this allows blocking queues to be used for *work queues* (also known as *producer-consumer queues*), to which one
      or more *producer threads* enqueue work items and from which one or more *consumer threads dequeue* and process
      items as they become available
    - as you’d expect, most *ExecutorService* implementations, including *ThreadPoolExecutor*, use a *
      BlockingQueue* ([Item 68](#item68))
* *Synchronizers* are objects that enable threads to *wait* for one another, allowing them to coordinate their
  activities
    - the most commonly used synchronizers are *CountDownLatch* and *Semaphore*
    - less commonly used are *CyclicBarrier* and *Exchanger*
* countdown latches are single-use barriers that allow one or more threads to wait for one or more other threads to do
  something
    - the sole constructor for *CountDownLatch* takes an *int* that is the number of times the *countDown* method must
      be invoked on the latch before all waiting threads are allowed to proceed
* it is surprisingly easy to build useful things atop this simple primitive
    - for example, suppose you want to build a simple framework for timing the concurrent execution of an action
    - this framework consists of a single method that takes an executor to execute the action, a concurrency level
      actions to be executed concurrently, and a runnable representing the action
    - all of the worker threads ready themselves to run the action before the timer thread starts the clock (this is
      necessary to get an accurate timing)
    - when the last worker thread is ready to run the action, the timer thread "fires the starting gun," allowing the
      worker threads to perform the action
    - as soon as the last worker thread finishes performing the action, the timer thread stops the clock
    - implementing this logic directly on top of *wait* and *notify* would be messy to say the least, but it is
      surprisingly straightforward on top of *CountDownLatch*:

```Java
class TimingConcurrentExcecutionClass {
    
    // Simple framework for timing concurrent execution
    public static long time(Executor executor, int concurrency,
            final Runnable action) throws InterruptedException {
    
        final CountDownLatch ready = new CountDownLatch(concurrency);
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(concurrency);
    
        for (int i = 0; i < concurrency; i++) {
            executor.execute(new Runnable() {
                public void run() {
                    ready.countDown(); // Tell timer we're ready
                    try {
                        start.await(); // Wait till peers are ready
                        action.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        done.countDown(); // Tell timer we're done
                    }
                }
            });
        }
        ready.await(); // Wait for all workers to be ready
        long startNanos = System.nanoTime();
        start.countDown(); // And they're off!
        done.await(); // Wait for all workers to finish
        return System.nanoTime() - startNanos;
    }
}
```

* Note that the method uses three countdown latches
    - the first, *ready*, is used by worker threads to tell the timer thread when they're ready
    - the worker threads then wait on the second latch, which is *start*
    - when the last worker thread invokes *ready.countDown*, the timer thread records the start time and invokes *
      start.countDown*, allowing all of the worker threads to proceed
    - then the timer thread waits on the third latch, *done*, until the last of the worker threads finishes running the
      action and calls *done.countDown*
    - as soon as this happens, the timer thread awakens and records the end time.
* a few more details bear noting
    - the executor that is passed to the time method must allow for the creation of at least as many threads as the
      given concurrency level, or the test will never complete
    - this is known as a *thread starvation deadlock* [Goetz06 8.1.1]
        + if a worker thread catches an *InterruptedException*, it reasserts the interrupt using the idiom *
          Thread.currentThread().interrupt()* and returns from its *run* method
        + this allows the executor to deal with the interrupt as it sees fit, which is as it should be
    - finally, note that *System.nanoTime* is used to time the activity rather than *System.currentTimeMillis*
        + **for interval timing, always use *System.nanoTime* in preference to *System.currentTimeMillis***
        + *System.nanoTime* is both more accurate and more precise, and it is not affected by adjustments to the
          system's real-time clock
* This item only scratches the surface of the concurrency utilities
    - for example, the three countdown latches in the previous example can be replaced by a single *cyclic barrier*
    - the resulting code is even more concise, but it is more difficult to understand
    - for more information, see *Java Concurrency in Practice* [Goetz06]
* While you should always use the concurrency utilities in preference to *wait* and *notify*, you might have to maintain
  legacy code that uses *wait* and *notify*
    - the wait method is used to make a thread wait for some condition
    - it must be invoked inside a synchronized region that locks the object on which it is invoked
    - here is the standard idiom for using the wait method:

```
// The standard idiom for using the wait method
synchronized (obj) {
    while (<condition does not hold>)
        obj.wait(); // (Releases lock, and reacquires on wakeup)
        // ... // Perform action appropriate to condition
}
```

* **Always use the wait loop idiom to invoke the wait method; never invoke it outside of a loop**
    - the loop serves to test the condition before and after waiting
* testing the condition before waiting and skipping the wait if the condition already holds are necessary to ensure
  liveness
    - if the condition already holds and the *notify* (or *notifyAll*) method has already been invoked before a thread
      waits, there is no guarantee that the thread will *ever* wake from the wait
* testing the condition after waiting and waiting again if the condition does not hold are necessary to ensure safety
    - if the thread proceeds with the action when the condition does not hold, it can destroy the invariant guarded by
      the lock
    - there are several reasons a thread might wake up when the condition does not hold:
        + Another thread could have obtained the lock and changed the guarded state between the time a thread invoked *
          notify* and the time the waiting thread woke.
        + Another thread could have invoked *notify* accidentally or maliciously when the condition did not hold.
          Classes expose themselves to this sort of mischief by waiting on publicly accessible objects. Any *wait*
          contained in a synchronized method of a publicly accessible object is susceptible to this problem.
        + The notifying thread could be overly "generous" in waking waiting threads. For example, the notifying thread
          might invoke *notifyAll* even if only some of the waiting threads have their condition satisfied.
        + The waiting thread could (rarely) wake up in the absence of a notify. This is known as a *spurious
          wakeup* [Posix, 11.4.3.6.1; JavaSE6].

* a related issue is whether you should use *notify* or *notifyAll* to wake waiting threads
    - (Recall that *notify* wakes a single waiting thread, assuming such a thread exists, and *notifyAll* wakes all
      waiting threads.)
    - it is often said that you should always use *notifyAll* - this is reasonable, conservative advice
    - it will always yield correct results because it guarantees that you’ll wake the threads that need to be awakened
    - you may wake some other threads, too, but this won’t affect the correctness of your program
    - These threads will check the condition for which they’re waiting and, finding it false, will continue waiting
* as an optimization, you may choose to invoke *notify* instead of *notifyAll* if all threads that could be in the
  wait-set are waiting for the same condition and only one thread at a time can benefit from the condition becoming true
* even if these conditions appear true, there may be cause to use *notifyAll* in place of *notify*
    - just as placing the *wait* invocation in a loop protects against accidental or malicious notifications on a
      publicly accessible object, using *notifyAll* in place of *notify* protects against accidental or malicious waits
      by an unrelated thread.
    - such waits could otherwise "swallow" a critical notification, leaving its intended recipient waiting indefinitely.
* in summary, using *wait* and *notify* directly is like programming in "concurrency assembly language," as compared to
  the higher-level language provided by *java.util.concurrent*
    - **there is seldom, if ever, a reason to use *wait* and *notify* in new code**
    - if you maintain code that uses *wait* and *notify*, make sure that it always invokes *wait* from within a while
      loop using the standard idiom
    - the *notifyAll* method should generally be used in preference to *notify*
    - if *notify* is used, great care must be taken to ensure liveness

---

### Item 70: Document thread safety<a name="item70"></a>

* how a class behaves when its instances or static methods are subjected to concurrent use is an important part of the
  contract the class makes with its clients
    - if you don't document this facet of a class’s behavior, programmers who use the class will be forced to make
      assumptions
    - if those assumptions are wrong, the resulting program may perform insufficient
      synchronization ([Item 66](#item66)) or excessive synchronization ([Item 67](#item67))
    - in either case, serious errors can result.
* you might hear it said that you can tell if a method is thread-safe by looking for the *synchronized* modifier in its
  documentation
    - this is wrong on several counts
    - in normal operation, Javadoc does not include the *synchronized* modifier in its output, and with good reason
    - **the presence of the *synchronized* modifier in a method declaration is an implementation detail, not a part of
      its exported API**
    - it does not reliably indicate that a method is thread-safe
* moreover, the claim that the presence of the *synchronized* modifier is sufficient to document thread safety embodies
  the misconception that thread safety is an all-or-nothing property
    - in fact, there are several levels of thread safety
    - **to enable safe concurrent use, a class must clearly document what level of thread safety it supports**
    - the following list summarizes levels of thread safety. It is not exhaustive but covers the common cases:

* **immutable**
  — instances of this class appear constant
    - no external synchronization is necessary
    - examples include *String*, *Long*, and *BigInteger* ([Item 15](04_classes_and_interfaces.md#item15))
* **unconditionally thread-safe**
  — instances of this class are mutable, but the class has sufficient internal synchronization that its instances can be
  used concurrently without the need for any external synchronization
    - examples include *Random* and *ConcurrentHashMap*
* **conditionally thread-safe**
  — like unconditionally thread-safe, except that some methods require external synchronization for safe concurrent use
    - examples include the collections returned by the *Collections.synchronized* wrappers, whose iterators require
      external synchronization
* **not thread-safe**
  — instances of this class are mutable
    - to use them concurrently, clients must surround each method invocation (or invocation sequence) with external
      synchronization of the clients' choosing
    - examples include the general-purpose collection implementations, such as *ArrayList* and *HashMap*
* **thread-hostile**
  — this class is not safe for concurrent use even if all method invocations are surrounded by external synchronization
    - thread hostility usually results from modifying static data without synchronization
    - no one writes a thread-hostile class on purpose
    - such classes result from the failure to consider *concurrency*
    - luckily, there are very few thread-hostile classes or methods in the Java libraries
    - the *System.runFinalizersOnExit* method is thread-hostile and has been deprecated

* these categories (apart from thread-hostile) correspond roughly to the *thread safety annotations* in *Java
  Concurrency in Practice*, which are *Immutable*, *ThreadSafe*, and *NotThreadSafe* [Goetz06, Appendix A]
    - the unconditionally and conditionally thread-safe categories in the above taxonomy are both covered under the *
      ThreadSafe* annotation
* documenting a conditionally thread-safe class requires care
    - you must indicate which invocation sequences require external synchronization, and which lock (or in rare cases,
      which locks) must be acquired to execute these sequences
    - typically it is the lock on the instance itself, but there are exceptions
    - if an object represents a view on some other object, the client generally must synchronize on the backing object,
      so as to prevent its direct modification
    - for example, the documentation for *Collections.synchronizedMap* says this:
        + it is imperative that the user manually synchronize on the returned map when iterating over any of its
          collection views; failure to follow this advice may result in non-deterministic behavior:

```Java
class SomeClass {
    public static void main(String[] args) {
        
        Map<K, V> m = Collections.synchronizedMap(new HashMap<K, V>());
        // ...
        Set<K> s = m.keySet(); // Needn't be in synchronized block
        // ...
        synchronized(m) { // Synchronizing on m, not s!
            for (K key : s)
                key.f();
        }
    }
}
```

* the description of a class's thread safety generally belongs in its documentation comment, but methods with special
  thread safety properties should describe these properties in their own documentation comments
    - it is not necessary to document the immutability of enum types
    - unless it is obvious from the return type, static factories must document the thread safety of the returned
      object, as demonstrated by *Collections.synchronizedMap* (above)
* when a class commits to using a publicly accessible lock, it enables clients to execute a sequence of method
  invocations atomically, but this flexibility comes at a price
    - it is incompatible with high-performance internal concurrency control, of the sort used by concurrent collections
      such as *ConcurrentHashMap* and *ConcurrentLinkedQueue*
    - also, a client can mount a denial-of-service attack by holding the publicly accessible lock for a prolonged period
    - this can be done accidentally or intentionally
* to prevent this denial-of-service attack, you can use a *private lock object* instead of using synchronized methods (
  which imply a publicly accessible lock):

```Java
class SomeClass {
    
    // Private lock object idiom - thwarts denial-of-service attack
    private final Object lock = new Object();
            
    public void foo() {
        synchronized(lock) {
            // ...
        }
    }
}
```

* because the private lock object is inaccessible to clients of the class, it is impossible for them to interfere with
  the object's synchronization
    - in effect, we are applying the advice of [Item 13](04_classes_and_interfaces.md#item13) by encapsulating the lock
      object within the object it synchronizes
* note that the lock field is declared *final*
    - this prevents you from inadvertently changing its contents, which could result in catastrophic unsynchronized
      access to the containing object ([Item 66](#item66))
    - we are applying the advice of [Item 15](04_classes_and_interfaces.md#item15), by minimizing the mutability of
      the *lock* field
* to reiterate, the private lock object idiom can be used only on *unconditionally* thread-safe classes
    - conditionally thread-safe classes can’t use this idiom because they must document which lock their clients are to
      acquire when performing certain method invocation sequences
* the private lock object idiom is particularly well-suited to classes designed for
  inheritance ([Item 17](04_classes_and_interfaces.md#item17))
    - if such a class were to use its instances for locking, a subclass could easily and unintentionally interfere with
      the operation of the base class, or vice versa
    - by using the same lock for different purposes, the subclass and the base class could end up "stepping on each
      other's toes"
    - this is not just a theoretical problem
    - for example, it happened with the *Thread* class [Bloch05, Puzzle 77].
* to summarize, **every class should clearly document its thread safety properties with a carefully worded prose
  description or a thread safety annotation**
    - the *synchronized* modifier plays no part in this documentation
    - conditionally thread-safe classes must document which method invocation sequences require external
      synchronization, and which *lock* to acquire when executing these sequences
    - if you write an unconditionally thread-safe class, consider using a *private lock object* in place of synchronized
      methods
    - this protects you against synchronization interference by clients and subclasses and gives you the flexibility to
      adopt a more sophisticated approach to concurrency control in a later release

---

### Item 71: Use lazy initialization judiciously<a name="item71"></a>

* *lazy initialization* is the act of delaying the initialization of a field until its value is needed
    - if the value is never needed, the field is never initialized
    - this technique is applicable to both static and instance fields
    - while lazy initialization is primarily an optimization, it can also be used to break harmful circularities in
      class and instance initialization [Bloch05, Puzzle 51]
* as is the case for most optimizations, the best advice for lazy initialization is "**don't do it unless you need
  to**" ([Item 55](08_general_programming.md#item55))
    - lazy initialization is a double-edged sword
    - it decreases the cost of initializing a class or creating an instance, at the expense of increasing the cost of
      accessing the lazily initialized field
    - depending on what fraction of lazily initialized fields eventually require initialization, how expensive it is to
      initialize them, and how often each field is accessed, lazy initialization can (like many "optimizations")
      actually harm performance
* that said, lazy initialization has its uses
    - if a field is accessed only on a fraction of the instances of a class *and* it is costly to initialize the field,
      then lazy initialization may be worthwhile
    - the only way to know for sure is to measure the performance of the class with and without lazy initialization
* in the presence of multiple threads, lazy initialization is tricky
    - if two or more threads share a lazily initialized field, it is critical that some form of synchronization be
      employed, or severe bugs can result ([Item 66](#item66))
    - all of the initialization techniques discussed in this item are thread-safe
* **under most circumstances, normal initialization is preferable to lazy initialization**
    - here is a typical declaration for a normally initialized instance field
    - note the use of the final modifier ([Item 15](04_classes_and_interfaces.md#item15)):

```Java
class SomeClass {
    
    // Normal initialization of an instance field
    private final FieldType field = computeFieldValue();
    
    // ...
}
```

* **if you use lazy initialization to break an initialization circularity, use a synchronized accessor**, as it is the
  simplest, clearest alternative:

```Java
class SomeClass {
    
    // Lazy initialization of instance field - synchronized accessor
    private FieldType field;
    
    synchronized FieldType getField() {
        if (field == null)
            field = computeFieldValue();
        return field;
    }
}
```

* both of these idioms (*normal initialization* and *lazy initialization with a synchronized accessor*) are unchanged
  when applied to static fields, except that you add the static modifier to the field and accessor declarations
* **if you need to use lazy initialization for performance on a static field, use the *lazy initialization holder class
  idiom***
    - this idiom (also known as the *initialize-on-demand holder class idiom*) exploits the guarantee that a class will
      not be initialized until it is used [JLS, 12.4.1]
    - here’s how it looks:

```Java
class SomeClass {
    
    // Lazy initialization holder class idiom for static fields
    private static class FieldHolder {
        static final FieldType field = computeFieldValue();
    }
    
    static FieldType getField() { return FieldHolder.field; }
}
```

* when the *getField* method is invoked for the first time, it reads *FieldHolder.field* for the first time, causing
  the *FieldHolder* class to get initialized
    - the beauty of this idiom is that the *getField* method is not synchronized and performs only a field access, so
      lazy initialization adds practically nothing to the cost of access
    - a modern VM will synchronize field access only to initialize the class
    - once the class is initialized, the VM will patch the code so that subsequent access to the field does not involve
      any testing or synchronization
* **if you need to use lazy initialization for performance on an instance field, use the *double-check idiom***
    - this idiom avoids the cost of locking when accessing the field after it has been initialized ([Item 67](#item67))
    - the idea behind the idiom is to check the value of the field twice (hence the name *double-check*):
        + once without locking, and then, if the field appears to be uninitialized, a second time with locking
    - only if the second check indicates that the field is uninitialized does the call initialize the field
    - because there is no locking if the field is already initialized, it is *critical* that the field be declared *
      volatile* ([Item 66](#item66))
    - here is the idiom:

```Java
class SomeClass {
    
    // Double-check idiom for lazy initialization of instance fields
    private volatile FieldType field;
    
    FieldType getField() {
        FieldType result = field;
        if (result == null) { // First check (no locking)
            synchronized(this) {
                result = field;
                if (result == null) // Second check (with locking)
                    field = result = computeFieldValue();
            }
        }
        return result;
    }
}
```

* this code may appear a bit convoluted
    - in particular, the need for the local variable *result* may be unclear
    - what this variable does is to ensure that field is read only once in the common case where it's already
      initialized
    - while not strictly necessary, this may improve performance and is more elegant by the standards applied to
      low-level concurrent programming
    - on my machine, the method above is about 25 percent faster than the obvious version without a local variable
* prior to release 1.5, the double-check idiom did not work reliably because the semantics of the *volatile* modifier
  were not strong enough to support it [Pugh01]
    - the memory model introduced in release 1.5 fixed this problem [JLS, 17, Goetz06 16]
    - today, the double-check idiom is the technique of choice for lazily initializing an instance field
    - while you can apply the double-check idiom to static fields as well, there is no reason to do so: the lazy
      initialization holder class idiom is a better choice
* two variants of the double-check idiom bear noting
    - occasionally, you may need to lazily initialize an instance field that can tolerate repeated initialization
    - if you find yourself in this situation, you can use a variant of the double-check idiom that dispenses with the
      second check
    - it is, not surprisingly, known as the *single-check idiom*
    - here is how it looks - note that field is still declared *volatile*:

```Java
class SomeClass {
    // Single-check idiom - can cause repeated initialization!
    private volatile FieldType field;
 
    private FieldType getField() {
       FieldType result = field;
        if (result == null)
            field = result = computeFieldValue();
        return result;
    }
}
```

* all of the initialization techniques discussed in this item apply to primitive fields as well as object reference
  fields
    - when the double-check or single-check idiom is applied to a numerical primitive field, the field’s value is
      checked against *0* (the default value for numerical primitive variables) rather than *null*
* if you don't care whether *every* thread recalculates the value of a field, and the type of the field is a primitive
  other than *long* or *double*, then you may choose to remove the *volatile* modifier from the field declaration in the
  single-check idiom
    - this variant is known as the *racy single-check idiom*
    - it speeds up field access on some architectures, at the expense of additional initializations (up to one per
      thread that accesses the field)
    - this is definitely an exotic technique, not for everyday use
    - it is, however, used by *String* instances to cache their hash codes
* in summary, you should initialize most fields normally, not lazily
    - if you must initialize a field lazily in order to achieve your performance goals, or to break a harmful
      initialization circularity, then use the appropriate lazy initialization technique
    - for instance fields, it is the double-check idiom; for static fields, the lazy initialization holder class idiom
    - for instance fields that can tolerate repeated initialization, you may also consider the single-check idiom

---

### Item 72: Don't depend on the thread scheduler<a name="item72"></a>

* when many threads are runnable, the thread scheduler determines which ones get to run, and for how long
    - any reasonable operating system will try to make this determination fairly, but the policy can vary
    - therefore, well-written programs shouldn't depend on the details of this policy
    - **any program that relies on the thread scheduler for correctness or performance is likely to be nonportable**
* the best way to write a robust, responsive, portable program is to ensure that the average number of *runnable*
  threads is not significantly greater than the number of processors
    - this leaves the thread scheduler with little choice: it simply runs the runnable threads till they're no longer
      runnable
    - the program's behavior doesn't vary too much, even under radically different thread-scheduling policies
    - note that the number of runnable threads isn't the same as the total number of threads, which can be much higher
    - threads that are waiting are not runnable
* the main technique for keeping the number of runnable threads down is to have each thread do some useful work and then
  wait for more
    - **threads should not run if they aren't doing useful work**
    - in terms of the *Executor Framework* ([Item 68](#item68)), this means sizing your thread pools
      appropriately [Goetz06 8.2], and keeping tasks reasonably small and independent of one another
    - tasks shouldn't be *too* small, or dispatching overhead will harm performance
* threads should not *busy-wait*, repeatedly checking a shared object waiting for something to happen
    - besides making the program vulnerable to the vagaries of the scheduler, busy-waiting greatly increases the load on
      the processor, reducing the amount of useful work that others can accomplish
    - as an extreme example of what *not* to do, consider this perverse reimplementation of *CountDownLatch*:

```Java
// Awful CountDownLatch implementation - busy-waits incessantly!
public class SlowCountDownLatch {
    private int count;
    
    public SlowCountDownLatch(int count) {
        if (count < 0)
            throw new IllegalArgumentException(count + " < 0");
        this.count = count;
    }
    
    public void await() {
        while (true) {
            synchronized(this) {
                if (count == 0) return;
            }
        }
    }

    public synchronized void countDown() {
        if (count != 0)
            count--;
        }
}
```

* on my machine, *SlowCountDownLatch* is about *2.000* times slower than *CountDownLatch* when *1.000* threads wait on a
  latch
    - while this example may seem a bit far-fetched, it’s not uncommon to see systems with one or more threads that are
      unnecessarily runnable
    - the results may not be as dramatic as Slow-*CountDownLatch*, but performance and portability are likely to suffer
* when faced with a program that barely works because some threads aren't getting enough CPU time relative to others, **
  resist the temptation to "fix" the program by putting in calls to Thread.yield**
    - you may succeed in getting the program to work after a fashion, but it will not be portable
    - the same *yield* invocations that improve performance on one JVM implementation might make it worse on a second
      and have no effect on a third
    - **Thread.yield has no testable semantics**
    - better course of action is to restructure the application to reduce the number of concurrently runnable threads
* a related technique, to which similar caveats apply, is adjusting thread priorities
    - **thread priorities are among the least portable features of the Java platform**
    - it is not unreasonable to tune the responsiveness of an application by tweaking a few thread priorities, but it is
      rarely necessary and is not portable
    - it is unreasonable to solve a serious liveness problem by adjusting thread priorities
    - the problem is likely to return until you find and fix the underlying cause
* in the first edition of this book, it was said that the only use most programmers would ever have for *Thread.yield*
  was to artificially increase the concurrency for testing
    - the idea was to shake out bugs by exploring a larger fraction of the program's statespace
    - this technique was once quite effective, but it was never guaranteed to work
    - it is within specification for *Thread.yield* to do nothing at all, simply returning control to its caller
    - some modern VMs actually do this
    - therefore, you should use *Thread.sleep(1)* instead of *Thread.yield* for concurrency testing
    - do not use *Thread.sleep(0)*, which can return immediately
* in summary, do not depend on the thread scheduler for the correctness of your program
    - the resulting program will be neither robust nor portable
    - as a corollary, do not rely on *Thread.yield* or thread priorities
    - these facilities are merely hints to the scheduler
    - thread priorities may be used sparingly to improve the quality of service of an already working program, but they
      should never be used to "fix" a program that barely works

---

### Item 73: Avoid thread groups<a name="item73"></a>

* along with threads, locks, and monitors, a basic abstraction offered by the threading system is *thread groups*
    - thread groups were originally envisioned as a mechanism for isolating applets for security purposes
    - they never really fulfilled this promise, and their security importance has waned to the extent that they aren't
      even mentioned in the standard work on the Java security model [Gong03]
* Given that thread groups don't provide any security functionality to speak of, what functionality do they provide? Not
  much.
    - they allow you to apply certain Thread primitives to a bunch of threads at once
    - several of these primitives have been deprecated, and the remainder are infrequently used
* in an ironic twist, the *ThreadGroup* API is weak from a thread safety standpoint
    - to get a list of the active threads in a thread group, you must invoke the enumerate method, which takes as a
      parameter an array large enough to hold all the active threads
    - the *activeCount* method returns the number of active threads in a thread group, but there is no guarantee that
      this count will still be accurate once an array has been allocated and passed to the enumerate method
    - if the thread count has increased and the array is too small, the enumerate method silently ignores any threads
      for which there is no room in the array
* the API that lists the subgroups of a thread group is similarly flawed
    - while these problems could have been fixed with the addition of new methods, they haven't, because there is no
      real need: **thread groups are obsolete**
* Prior to release 1.5, there was one small piece of functionality that was available only with the *ThreadGroup* API:
    - the *ThreadGroup.uncaughtException* method was the only way to gain control when a thread threw an uncaught
      exception
    - this functionality is useful, for example, to direct stack traces to an application-specific log
    - as of release 1.5, however, the same functionality is available with *Thread*’s *setUncaughtExceptionHandler*
      method
* To summarize, thread groups don't provide much in the way of useful functionality, and much of the functionality they
  do provide is flawed
    - thread groups are best viewed as an unsuccessful experiment, and you should simply ignore their existence
    - if you design a class that deals with logical groups of threads, you should probably use *thread pool
      executors* ([Item 68](#item68))