package de.holhar.java_dev_kb.designpatterns.behavioral.chain_of_responsibility;

public interface DispenseChain {
    void setNextChain(DispenseChain nextChain);
    void dispense(Currency currency);
}
