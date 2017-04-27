package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if ISBN already stored.
 */
public class IdentifierAlreadyExistsException extends BaseException {
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public IdentifierAlreadyExistsException(final String message) {
        super(message, ErrorCodes.IDENTIFIER_ALREADY_EXISTS.getErrorCode());
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
