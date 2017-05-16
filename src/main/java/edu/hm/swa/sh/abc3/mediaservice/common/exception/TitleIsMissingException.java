package edu.hm.swa.sh.abc3.mediaservice.common.exception;

/**
 * Exception if title of a book is missing.
 */
public class TitleIsMissingException extends BaseException {
    private final String message;

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public TitleIsMissingException(final String message) {
        super(message, ErrorCodes.TITLE_MISSING.getErrorCode());
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
