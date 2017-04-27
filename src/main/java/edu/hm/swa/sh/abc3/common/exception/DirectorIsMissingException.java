package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if the Director is missing.
 */
public class DirectorIsMissingException extends BaseException {
    private static final int ERROR_CODE = -160;
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public DirectorIsMissingException(final String message) {
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
