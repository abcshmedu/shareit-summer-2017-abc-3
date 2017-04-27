package edu.hm.swa.sh.abc3.common.exception;

/**
 * Exception if title of a book is missing.
 */
public class TitleIsMissingException extends BaseException {
    private static final int ERROR_CODE = -150;
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public TitleIsMissingException(final String message) {
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
