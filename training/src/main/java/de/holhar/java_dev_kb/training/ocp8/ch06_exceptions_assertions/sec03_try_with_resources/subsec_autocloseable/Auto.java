package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec03_try_with_resources.subsec_autocloseable;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class Auto implements AutoCloseable {

    private int num;

    public Auto(int num) {
        this.num = num;
    }

    public static void main(String[] args) {
        try (Auto a1 = new Auto(1); Auto a2 = new Auto(2)) {
            throw new RuntimeException();
        } catch (Exception e) {
            println("ex");
        } finally {
            println("finally");
        }
    }

    /*
     * Resources get closed after the try clause and before any following catch or finally clauses
     * Resources get closed in reverse order in which they got opened
     */
    @Override
    public void close() {
        println("Close: " + num);
    }
}
