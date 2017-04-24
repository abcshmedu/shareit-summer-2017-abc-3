package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if identifier is missing.
 */
public class IdentifierIsMissingException extends Exception {
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public IdentifierIsMissingException(final String message) {
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
