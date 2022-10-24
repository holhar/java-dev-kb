package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s3_2_publication_and_escape;

import de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.event.Event;
import de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.event.EventListener;
import de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.event.EventSource;

/**
 * Implicitly allowing the 'this' reference to escape.
 * Don't do this.
 */
public class ThisEscape {

    /*
     * Publishing an inner class instance also publishing the internal state of ThisEscape. When ThisEscape publishes
     * the EventListener, it implicitly publishes the enclosing ThisEscape instance as well, because inner class
     * instances contain a hidden reference to the enclosing instance.
     */
    public ThisEscape(EventSource source) {
        source.registerListener(
                new EventListener() {
                    @Override
                    public void onEvent(Event e) {
                        doSomething(e);
                    }
                }
        );
    }

    private void doSomething(Event e) {
        // Do some fancy stuff...
    }
}
