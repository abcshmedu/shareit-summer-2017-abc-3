package edu.hm.swa.sh.abc3.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Base response type.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageResponseType {
    private String message;
    private int code;

    /**
     * Return error message.
     *
     * @return message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set error message.
     *
     * @param message message.
     * @return this object.
     */
    public MessageResponseType setMessage(final String message) {
        this.message = message;
        return this;
    }

    /**
     * Get error code.
     *
     * @return error code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Set error code.
     *
     * @param code error code.
     * @return this object.
     */
    public MessageResponseType setCode(int code) {
        this.code = code;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final MessageResponseType that = (MessageResponseType) o;

        return code == that.code && (message != null ? message.equals(that.message) : that.message == null);
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + code;
        return result;
    }
}
