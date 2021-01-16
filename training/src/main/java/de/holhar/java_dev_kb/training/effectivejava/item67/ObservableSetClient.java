package de.holhar.java_dev_kb.training.effectivejava.item67;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ObservableSetClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObservableSetClient.class);

    public static void main(String[] args) {
        // BrokenObservableSet<Integer> set = new BrokenObservableSet<Integer>(new HashSet<Integer>());
        FixedObservableSet<Integer> set = new FixedObservableSet<Integer>(new HashSet<Integer>());

        /*
        this works:
        set.addObserver(new BrokenSetObserver<Integer>() {
            public void added(BrokenObservableSet<Integer> s, Integer e) {
                LOGGER.debug(e);
            }
        });
        */

        /*
        throws an exception with BrokenObservableSet/BrokenSetObserver:
        set.addObserver(new BrokenSetObserver<Integer>() {
            public void added(BrokenObservableSet<Integer> s, Integer e) {
                LOGGER.debug(e);
                if (e == 23) s.removeObserver(this);
            }
        });
        */

        // Observer that uses a background thread needlessly, causes Deadlock with BrokenObservableSet/BrokenSetObserver:
        set.addObserver(new FixedSetObserver<Integer>() {

            public void added(final FixedObservableSet<Integer> s, Integer e) {
                LOGGER.debug("{}", e);
                if (e == 23) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();

                    final FixedSetObserver<Integer> observer = this;

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