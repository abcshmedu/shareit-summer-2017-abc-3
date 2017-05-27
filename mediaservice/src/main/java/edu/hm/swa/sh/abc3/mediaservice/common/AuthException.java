package edu.hm.swa.sh.abc3.mediaservice.common;

import edu.hm.swa.sh.abc3.exception.BaseException;

/**
 * AuthException.
 */
public class AuthException extends BaseException {
    /**
     * Cstr.
     *
     * @param message   Exception message.
     * @param errorCode Error code of exception.
     */
    public AuthException(final String message, final int errorCode) {
        super(message, errorCode);
    }
}
