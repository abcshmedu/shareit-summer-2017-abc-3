package edu.hm.swa.sh.abc3.exception;

/**
 * Exception if author is missing.
 */
public class AuthorIsMissingException extends BaseException {
    private final String message;
    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public AuthorIsMissingException(final String message) {
        super(message, ErrorCodes.AUTHOR_MISSING.getErrorCode());
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
