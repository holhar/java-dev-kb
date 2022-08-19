package de.holhar.java_dev_kb.designpatterns.structural.proxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandExecutorProxyTest {

    @Test
    void commandExecutorProxy() throws Exception {
        CommandExecutorProxy proxy = new CommandExecutorProxy("MaxPower", "WRONG_PW");

        proxy.runCommand("ls -lisah");
        assertThrows(IllegalArgumentException.class, () -> proxy.runCommand(" rm -rf abc.pdf"));

    }

}
