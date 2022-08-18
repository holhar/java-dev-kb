package de.holhar.java_dev_kb.designpatterns.creational.singleton;

public final class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    // Private constructor to prevent the use of "new"
    private ThreadSafeSingleton() {}

    public static synchronized ThreadSafeSingleton getInstance() {
        // Lazy initialization
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    public static ThreadSafeSingleton getInstanceUsingDoubleLocking(){
        if(instance == null){
            synchronized (ThreadSafeSingleton.class) {
                if(instance == null){
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

}
