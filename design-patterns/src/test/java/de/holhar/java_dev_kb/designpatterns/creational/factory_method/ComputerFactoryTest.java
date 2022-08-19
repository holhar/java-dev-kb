package de.holhar.java_dev_kb.designpatterns.creational.factory_method;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComputerFactoryTest {

    @Test
    void getComputer() {
        Computer pc = ComputerFactory.getComputer("PC", "8 GB", "500 GB", "2,4 GHz");
        assertTrue(pc instanceof PC);

        Computer server = ComputerFactory.getComputer("SERVER", "8 GB", "500 GB", "2,4 GHz");
        assertTrue(server instanceof Server);

        Computer invalid = ComputerFactory.getComputer("INVALID", "8 GB", "500 GB", "2,4 GHz");
        assertNull(invalid);
    }
}
