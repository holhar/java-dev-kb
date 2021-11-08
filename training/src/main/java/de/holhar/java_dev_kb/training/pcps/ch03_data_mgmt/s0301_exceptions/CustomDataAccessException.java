package de.holhar.java_dev_kb.training.pcps.ch03_data_mgmt.s0301_exceptions;

import org.springframework.dao.DataAccessException;

/**
 * {@link DataAccessException} and all of its subclasses form the 'data exception hierarchy' - they are all unchecked.
 * Unlike checked exceptions, unchecked exceptions must not be declared or caught/handled by the methods that might
 * throw the corresponding exception (this is enforced by the Java compiler). All exceptions inheriting from
 * {@link RuntimeException} are unchecked exceptions.
 *
 * Spring prefers unchecked exceptions to avoid code prone to cluttered code and/or unnecessary coupling to the
 * underlying methods. Unchecked exceptions give developers the freedom to decide where to implement error handling
 * and removes any coupling to related exceptions.
 *
 * The 'data exception hierarchy' isolates the particulars of JDBC data access APIs, e.g. database drivers from
 * different vendors.
 */
public class CustomDataAccessException extends DataAccessException {

    public CustomDataAccessException(String message) {
        super(message);
    }

    public CustomDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
