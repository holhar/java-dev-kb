package de.holhar.java_dev_kb.designpatterns.creational.abstract_factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComputerFactoryTest {

  @Test
  void getComputer() {
    Computer pc = ComputerFactory.getComputer(new PCFactory("2 GB", "500 GB", "2.4 Ghz"));
    Computer server = ComputerFactory.getComputer(new ServerFactory("16 GB", "1 TB", "2.9 Ghz"));

    assertEquals("RAM=2 GB, HDD=500 GB, CPU=2.4 Ghz", pc.toString());
    assertEquals("RAM=16 GB, HDD=1 TB, CPU=2.9 Ghz", server.toString());
  }
}
