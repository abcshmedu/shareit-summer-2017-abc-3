package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if ISBN already stored.
 */
public class IdentifierAlreadyExsistsException extends Exception {
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public IdentifierAlreadyExsistsException(final String message) {
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