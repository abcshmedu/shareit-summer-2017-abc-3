package edu.hm.swa.sh.abc3.rest.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Base response type.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExceptionResponseType {
    private String message;
    private int code;

    /**
     * Return error message.
     * @return message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set error message.
     * @param message message.
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Get error code.
     * @return error code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Set error code.
     * @param code error code.
     */
    public void setCode(int code) {
        this.code = code;
    }
}
