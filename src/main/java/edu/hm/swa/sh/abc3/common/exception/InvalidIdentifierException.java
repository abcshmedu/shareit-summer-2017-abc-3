package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if ISBN is invalid.
 */
public class InvalidIdentifierException extends BaseException {
    private static final int ERROR_CODE = -140;
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public InvalidIdentifierException(final String message) {
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
