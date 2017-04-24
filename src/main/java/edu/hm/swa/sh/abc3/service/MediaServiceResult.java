package edu.hm.swa.sh.abc3.service;

/**
 * Created by Sebastian on 21.04.2017.
 */
public enum MediaServiceResult {
    INVALID_ISBN(-100),
    ISBN_ALREADY_EXISTS(-110),
    AUTHOR_MISSING(-120),
    TITLE_MISSING(-130),
    ISBN_IS_IMMUTABLE(-140),

    INVALID_BARCODE(-200),
    BARCODE_ALREADY_EXISTS(-210),
    DIRECTOR_MISSING(-220),
    BARCODE_IS_IMMUTABLE(-230);

    private final int errorCode;

    MediaServiceResult(final int errorCode) {
        this.errorCode = errorCode;
    }

    public int getCode() {
        return this.errorCode;
    }

    public String getStatus() {
        return this.name();
    }
}
