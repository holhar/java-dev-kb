package de.holhar.java_dev_kb.designpatterns.creational.abstract_factory;

public class ComputerFactory {

  public static Computer getComputer(ComputerAbstractFactory factory) {
    return factory.createComputer();
  }
}
