package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if the identifier of a medium should be updated.
 */
public class IdentifierIsImmutableException extends Exception {
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public IdentifierIsImmutableException(final String message) {
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
