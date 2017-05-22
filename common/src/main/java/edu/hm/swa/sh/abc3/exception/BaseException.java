package edu.hm.swa.sh.abc3.exception;

/**
 * Base Exception for Application.
 */
public abstract class BaseException extends Exception {
    /**
     * Error code of Exception.
     */
    private final int errorCode;

    /**
     * Cstr.
     *
     * @param message   Exception message.
     * @param errorCode Error code of exception.
     */
    public BaseException(final String message, final int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Returns the error code.
     * @return Error code of Exception.
     */
    public int getErrorCode() {
        return errorCode;
    }
}
