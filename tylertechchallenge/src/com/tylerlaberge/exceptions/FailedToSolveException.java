package com.tylerlaberge.exceptions;


/**
 * An exception for when a task unexpectedly failed to solve a given input.
 */
public class FailedToSolveException extends Exception {

    /**
     * Create a new FailedToSolveException instance.
     *
     * @param message   The message to display with the exception.
     */
    public FailedToSolveException(String message) {
        super(message);
    }
}
