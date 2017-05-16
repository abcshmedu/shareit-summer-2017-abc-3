package edu.hm.swa.sh.abc3.mediaservice.common.exception;

/**
 * Exception if identifier is missing.
 */
public class IdentifierIsMissingException extends BaseException {
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public IdentifierIsMissingException(final String message) {
        super(message, ErrorCodes.IDENTIFIER_MISSING.getErrorCode());
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
