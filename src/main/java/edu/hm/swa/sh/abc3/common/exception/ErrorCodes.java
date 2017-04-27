package edu.hm.swa.sh.abc3.common.exception;

/**
 * Holds the error codes for Exceptions.
 */
public enum ErrorCodes {
    // Common
    TITLE_MISSING(-100),
    IDENTIFIER_ALREADY_EXISTS(-110),
    IDENTIFIER_IMMUTABLE(-120),
    IDENTIFIER_MISSING(-130),
    IDENTIFIER_INVALID(-140),

    // Book
    AUTHOR_MISSING(-150),

    // Disc
    DIRECTOR_MISSING(-160);

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
