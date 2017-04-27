package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if identifier is missing.
 */
public class IdentifierIsMissingException extends BaseException {
    private static final int ERROR_CODE = -130;
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public IdentifierIsMissingException(final String message) {
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
