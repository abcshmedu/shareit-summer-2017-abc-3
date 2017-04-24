package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if ISBN is invalid.
 */
public class InvalidIdentifierException extends Exception {
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public InvalidIdentifierException(final String message) {
        super(message);
        this.message = message;
    }

    /**
     * Return the error message.
     *
     * @return message.
     */
    public String getMessage() {
        return message;
    }
}
