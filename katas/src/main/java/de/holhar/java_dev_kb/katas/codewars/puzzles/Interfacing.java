package de.holhar.java_dev_kb.katas.codewars.puzzles;

/**
 * Interfacing (https://www.codewars.com/kata/54c183dd3f069611c3000f97)
 * <p>
 * Your boss is making yet another absolutely absurd request. He wants you to write a function that creates an
 * object, which implements a couple of interfaces. In fact, an entire list of interfaces. The catch? The list is
 * different every time, and is in fact, essentially random.
 * <p>
 * You do know a bit about what the interfaces are supposed to do, however. Each interface declares a list of method
 * pairs, each of which is made up of a get and a set method. For example, you might run into an interface like this:
 * <p>
 * public interface Date {
 * <p>
 * Integer getYear();
 * void setYear(Integer i);
 * Integer getMonth();
 * void setMonth(Integer i);
 * }
 * <p>
 * This interface being called Date, and having Year and Month as its method pairs. All of the interfaces will
 * generally follow this pattern, some declaring more methods, some declaring less. You also know that you'll never
 * run into any combination of interfaces that declare conflicting methods. Every interface is entirely unique and semi-random.
 * <p>
 * And as a lucky break, you also got access to the unit test code, which might come in as a handy reference, if
 * you're ever stuck.
 */
public class Interfacing {

    // TODO finish me!
    public static Object create(Class<?>[] interfaces) {
        return null;
    }
}
