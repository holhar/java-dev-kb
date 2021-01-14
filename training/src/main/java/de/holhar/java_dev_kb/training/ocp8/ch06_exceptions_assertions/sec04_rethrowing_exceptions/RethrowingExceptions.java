package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec04_rethrowing_exceptions;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 * <p>
 * It's a common pattern to log and then rethrow the same exception.
 */
public class RethrowingExceptions {

    public void parseData() throws IOException, SQLException, DateTimeParseException {
    }

    /*
     * Easier to maintain than multiCatch() as only the method signature needs updates in case parseData() changes;
     * but therefore it catches ANY exception in the method body
     */
    public void rethrowing() throws IOException, SQLException, DateTimeParseException {
        try {
            parseData();
        } catch (Exception e) {
            println(e);
            throw e;
        }
    }

    /*
     * More overhead when maintaining, if parseData() changes; but specific Exceptions get handled!
     */
    public void multiCatch() throws IOException, SQLException, DateTimeParseException {
        try {
            parseData();
        } catch (IOException | SQLException | DateTimeParseException e) {
            println(e);
            throw e;
        }
    }
}
