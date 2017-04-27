package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if author is missing.
 */
public class AuthorIsMissingException extends BaseException {
    private static final int ERROR_CODE = -100;
    private final String message;
    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public AuthorIsMissingException(final String message) {
        super(message, ERROR_CODE);
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
