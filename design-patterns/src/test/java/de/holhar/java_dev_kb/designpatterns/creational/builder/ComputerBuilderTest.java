package de.holhar.java_dev_kb.designpatterns.creational.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerBuilderTest {

    @Test
    void computerBuilder() {
        Computer computer = new Computer.ComputerBuilder("500 GB", "8 GB")
                .setBluetoothEnabled(true)
                .setGraphicsCardEnabled(true)
                .build();

        assertEquals("500 GB", computer.getHdd());
        assertEquals("8 GB", computer.getRam());
        assertTrue(computer.isBluetoothEnabled());
        assertTrue(computer.isGraphicsCardEnabled());
    }

}
