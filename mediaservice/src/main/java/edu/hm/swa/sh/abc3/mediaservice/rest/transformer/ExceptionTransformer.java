package edu.hm.swa.sh.abc3.mediaservice.rest.transformer;

import edu.hm.swa.sh.abc3.exception.BaseException;
import edu.hm.swa.sh.abc3.mediaservice.rest.types.MessageResponseType;

/**
 * Transform a Exception to Response.
 */
public class ExceptionTransformer {
    /**
     * Transform a exception to a JSONObject.
     *
     * @param exception exception to transform.
     * @return MessageResponseType.
     */
    public MessageResponseType handleException(final BaseException exception) {
        final MessageResponseType errorDescriptor = new MessageResponseType();
        errorDescriptor.setCode(exception.getErrorCode());
        errorDescriptor.setMessage(exception.getMessage());
        return errorDescriptor;
    }
}
