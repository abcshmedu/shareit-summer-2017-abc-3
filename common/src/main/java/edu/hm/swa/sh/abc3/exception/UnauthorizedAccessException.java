package edu.hm.swa.sh.abc3.exception;

/**
 * UnauthorizedAccessException.
 */
public class UnauthorizedAccessException extends BaseException {
    /**
     * Cstr.
     *
     * @param message Exception message.
     */
    public UnauthorizedAccessException(final String message) {
        super(message, ErrorCodes.UNAUTHORIZED_METHOD.getErrorCode());
    }
}
