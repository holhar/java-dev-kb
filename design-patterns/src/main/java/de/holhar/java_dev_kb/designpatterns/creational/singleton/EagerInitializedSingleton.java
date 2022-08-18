package de.holhar.java_dev_kb.designpatterns.creational.singleton;

public class EagerInitializedSingleton {

  private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

  // Private constructor to prevent clients to use constructor
  private EagerInitializedSingleton() {}

  public static EagerInitializedSingleton getInstance() {
    return instance;
  }
}
