package de.holhar.java_dev_kb.designpatterns.behavioral.chain_of_responsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ATMDispenseChain {

    private static final Logger logger = LoggerFactory.getLogger(ATMDispenseChain.class);

    private final DispenseChain c1;

    public ATMDispenseChain() {
        // Init the chain
        this.c1 = new Dollar50Dispenser();
        Dollar20Dispenser c2 = new Dollar20Dispenser();
        Dollar10Dispenser c3 = new Dollar10Dispenser();

        // Set the chain of responsibility
        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }

    public static void main(String[] args) {
        ATMDispenseChain atmDispenseChain = new ATMDispenseChain();
        while (true) {
            int amount;
            logger.info("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 10 != 0) {
                logger.error("Amount must be multiple of 10s.");
                return;
            }
            // Process the request
            atmDispenseChain.c1.dispense(new Currency(amount));
        }
    }
}
