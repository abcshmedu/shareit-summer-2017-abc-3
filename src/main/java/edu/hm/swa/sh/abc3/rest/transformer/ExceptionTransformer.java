package edu.hm.swa.sh.abc3.rest.transformer;

import edu.hm.swa.sh.abc3.common.exception.BaseException;
import edu.hm.swa.sh.abc3.rest.types.ExceptionResponseType;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;

/**
 * Transform a Exception to Response.
 */
public class ExceptionTransformer {
    /**
     * Transform a exception to a JSONObject.
     *
     * @param exception exception to transform.
     * @return ExceptionResponseType.
     */
    public ExceptionResponseType handleException(final BaseException exception) {
        final ExceptionResponseType errorDescriptor = new ExceptionResponseType();
        errorDescriptor.setCode(exception.getErrorCode());
        errorDescriptor.setMessage(exception.getMessage());
        return errorDescriptor;
    }
}
