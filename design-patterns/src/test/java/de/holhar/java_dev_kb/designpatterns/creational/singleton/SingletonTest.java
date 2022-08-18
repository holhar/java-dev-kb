package de.holhar.java_dev_kb.designpatterns.creational.singleton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.lang.reflect.Constructor;
import org.junit.jupiter.api.Test;

class SingletonTest {

    @Test
    void destroySingleton() {
        ThreadSafeSingleton instance1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton instance2 = null;

        try {
            Constructor[] constructors = ThreadSafeSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
                instance2 = (ThreadSafeSingleton) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotEquals(instance1, instance2);
    }

    @Test
    void whenRetrievingThreadSafeSingletonInstanceMultipleTimesThenShouldReturnSameInstance() {
        // Not applicable
        //ThreadSafeSingleton instance = new ThreadSafeSingleton();

        ThreadSafeSingleton instance1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton instance2 = ThreadSafeSingleton.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    void whenRetrievingStaticBlockSingletonInstanceMultipleTimesThenShouldReturnSameInstance() {
        StaticBlockSingleton instance1 = StaticBlockSingleton.getInstance();
        StaticBlockSingleton instance2 = StaticBlockSingleton.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    void whenRetrievingLazyInitializedSingletonInstanceMultipleTimesThenShouldReturnSameInstance() {
        LazyInitializedSingleton instance1 = LazyInitializedSingleton.getInstance();
        LazyInitializedSingleton instance2 = LazyInitializedSingleton.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    void whenRetrievingEnumSingletonInstanceMultipleTimesThenShouldReturnSameInstance() {
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        assertEquals(instance1, instance2);
    }

    @Test
    void whenRetrievingEagerInitializedSingletonInstanceMultipleTimesThenShouldReturnSameInstance() {
        EagerInitializedSingleton instance1 = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton instance2 = EagerInitializedSingleton.getInstance();
        assertEquals(instance1, instance2);
    }

}
