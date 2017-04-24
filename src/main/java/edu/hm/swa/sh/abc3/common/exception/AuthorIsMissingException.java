package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if author is missing.
 */
public class AuthorIsMissingException extends Exception {
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public AuthorIsMissingException(final String message) {
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
