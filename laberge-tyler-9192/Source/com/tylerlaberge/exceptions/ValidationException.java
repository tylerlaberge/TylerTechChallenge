package com.tylerlaberge.exceptions;


/**
 * An exception for when an input fails validation.
 */
public class ValidationException extends Exception {

    /**
     * Create a new ValidationException instance.
     *
     * @param message   The message to display with the exception.
     */
    public ValidationException(String message) {
        super(message);
    }
}
