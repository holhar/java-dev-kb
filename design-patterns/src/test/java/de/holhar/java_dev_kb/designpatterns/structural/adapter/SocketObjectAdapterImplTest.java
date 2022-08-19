package de.holhar.java_dev_kb.designpatterns.structural.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SocketObjectAdapterImplTest {

    private final SocketObjectAdapterImpl cut = new SocketObjectAdapterImpl();

    @Test
    void get120Volt() {
        Volt volt = cut.get120Volt();
        assertEquals(120, volt.getVolts());
    }

    @Test
    void get12Volt() {
        Volt volt = cut.get12Volt();
        assertEquals(12, volt.getVolts());
    }

    @Test
    void get3Volt() {
        Volt volt = cut.get3Volt();
        assertEquals(3, volt.getVolts());
    }
}
