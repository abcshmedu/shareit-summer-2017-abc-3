package edu.hm.swa.sh.abc3.exception;

/**
 * Exception if ISBN is invalid.
 */
public class InvalidIdentifierException extends BaseException {
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public InvalidIdentifierException(final String message) {
        super(message, ErrorCodes.IDENTIFIER_INVALID.getErrorCode());
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
