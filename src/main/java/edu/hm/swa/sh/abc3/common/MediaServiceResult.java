package edu.hm.swa.sh.abc3.common;

/**
 * Possible error codes.
 */
public enum MediaServiceResult {
    /**
     * If no error occurs.
     */
    OK(200),

    /**
     * If an ISBN number is incorrect.
     */
    INVALID_ISBN(-100),

    /**
     * A ISBN number must be unique.
     */
    ISBN_ALREADY_EXISTS(-110),

    /**
     * If the author of a book is not given.
     */
    AUTHOR_MISSING(-120),

    /**
     * If the title of a book is missing.
     */
    TITLE_MISSING(-130),

    /**
     * Returned while trying to change the ISBN.
     */
    ISBN_IS_IMMUTABLE(-140),

    /**
     * If the Barcode of a disc is invalid.
     */
    INVALID_BARCODE(-200),

    /**
     * If the Barcode of a disc is missing.
     */
    BARCODE_ALREADY_EXISTS(-210),

    /**
     * If the director of a disc is missing.
     */
    DIRECTOR_MISSING(-220),

    /**
     * Returned while trying to change the barcode of a disc.
     */
    BARCODE_IS_IMMUTABLE(-230);

    /**
     * The error code.
     */
    private final int errorCode;

    /**
     * Cstr.
     *
     * @param errorCode of the exception.
     */
    MediaServiceResult(final int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Returns the error code of the exception.
     *
     * @return The error code.
     */
    public int getCode() {
        return this.errorCode;
    }

    /**
     * Return the status of the exception.
     *
     * @return the status as String.
     */
    public String getStatus() {
        return this.name();
    }
}
