package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if the Director is missing.
 */
public class DirectorIsMissingException extends Exception {
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public DirectorIsMissingException(final String message) {
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
