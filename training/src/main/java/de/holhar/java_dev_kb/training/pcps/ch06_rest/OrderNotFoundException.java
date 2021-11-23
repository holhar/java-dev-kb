package de.holhar.java_dev_kb.training.pcps.ch06_rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Q6.11:
 * The @ResponseStatus annotation can  be used to annotate exception classes in order to specify the HTTP response
 * status and reason that are to be returned, instead of the default Server Internal Error (500), when an exception
 * of the type is thrown during the processing of a request in a controller handler method.
 *
 * In addition, the @ResponseStatus annotation can be applied to controller handler methods in order
 * to override the original response status information. In the annotation a HTTP response status code
 * and a reason string can be specified. The @ResponseStatus annotation can also be applied at class
 * level in controller classes, in which case it will apply to all the controller handler methods in the
 * class. In both these cases, the response body will not be the response body produced by the
 * controller handler method processing the request.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such order")
public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {

        super(message);
    }
}
