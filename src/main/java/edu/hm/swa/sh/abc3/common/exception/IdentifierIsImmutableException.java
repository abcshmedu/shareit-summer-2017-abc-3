package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if the identifier of a medium should be updated.
 */
public class IdentifierIsImmutableException extends BaseException {
    private static final int ERROR_CODE = -120;
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public IdentifierIsImmutableException(final String message) {
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
