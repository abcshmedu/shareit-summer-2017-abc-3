package edu.hm.swa.sh.abc3.exception;

/**
 * Holds the error codes for Exceptions.
 */
public enum ErrorCodes {
    // Mediaservice
    TITLE_MISSING(-100),
    IDENTIFIER_ALREADY_EXISTS(-110),
    IDENTIFIER_IMMUTABLE(-120),
    IDENTIFIER_MISSING(-130),
    IDENTIFIER_INVALID(-140),
    AUTHOR_MISSING(-150),
    DIRECTOR_MISSING(-160),

    // Authorize
    INVALID_CREDENTIALS(-200),
    INVALID_TOKEN(-210),
    UNAUTHORIZED_METHOD(-220),
    ;

    private final int errorCode;

    /**
     * Cstr.
     *
     * @param errorCode error code.
     */
    ErrorCodes(final int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Returns the Error code.
     *
     * @return error code.
     */
    public int getErrorCode() {
        return errorCode;
    }
}
