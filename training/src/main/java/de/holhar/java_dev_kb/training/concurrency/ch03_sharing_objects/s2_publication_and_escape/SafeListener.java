package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s2_publication_and_escape;

import de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.event.Event;
import de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.event.EventListener;
import de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.event.EventSource;

/**
 * Using a factory method to prevent the this reference from escaping during construction.
 */
public class SafeListener {

    private final EventListener listener;

    /*
     * If you are tempted to register an event listener or start a thread from a constructor, you can avoid improper
     * construction by using a private constructor and a public factory method.
     */
    private SafeListener() {
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething(e);
            }
        };
    }

    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }

    private void doSomething(Event e) {
        // Do some fancy stuff...
    }
}
