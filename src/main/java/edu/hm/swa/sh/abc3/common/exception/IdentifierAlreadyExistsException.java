package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if ISBN already stored.
 */
public class IdentifierAlreadyExistsException extends BaseException {
    private static final int ERROR_CODE = -110;
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public IdentifierAlreadyExistsException(final String message) {
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
