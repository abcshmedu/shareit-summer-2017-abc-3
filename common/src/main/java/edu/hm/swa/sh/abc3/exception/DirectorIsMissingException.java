package edu.hm.swa.sh.abc3.exception;

/**
 * Exception if the Director is missing.
 */
public class DirectorIsMissingException extends BaseException {
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public DirectorIsMissingException(final String message) {
        super(message, ErrorCodes.DIRECTOR_MISSING.getErrorCode());
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
