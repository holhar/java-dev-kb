package de.holhar.java_dev_kb.designpatterns.creational.abstract_factory;

public abstract class Computer {

  public abstract String getRAM();
  public abstract String getHDD();
  public abstract String getCPU();

  @Override
  public String toString() {
    return "RAM="+this.getRAM()+", HDD="+this.getHDD()+", CPU="+this.getCPU();
  }
}
