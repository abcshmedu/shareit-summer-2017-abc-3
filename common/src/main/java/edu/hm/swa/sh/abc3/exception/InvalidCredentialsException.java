package edu.hm.swa.sh.abc3.exception;

/**
 * InvalidCredentialsException.
 */
public class InvalidCredentialsException extends BaseException {
    /**
     * Cstr.
     *
     * @param message Exception message.
     */
    public InvalidCredentialsException(final String message) {
        super(message, ErrorCodes.INVALID_CREDENTIALS.getErrorCode());
    }
}
