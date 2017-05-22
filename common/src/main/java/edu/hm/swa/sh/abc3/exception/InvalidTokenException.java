package edu.hm.swa.sh.abc3.exception;

/**
 * InvalidTokenException.
 */
public class InvalidTokenException extends BaseException {
    /**
     * Cstr.
     *
     * @param message Exception message.
     */
    public InvalidTokenException(final String message) {
        super(message, ErrorCodes.INVALID_TOKEN.getErrorCode());
    }
}
