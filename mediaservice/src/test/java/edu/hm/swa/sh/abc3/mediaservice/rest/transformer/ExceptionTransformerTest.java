package edu.hm.swa.sh.abc3.mediaservice.rest.transformer;

import edu.hm.swa.sh.abc3.exception.AuthorIsMissingException;
import edu.hm.swa.sh.abc3.exception.BaseException;
import edu.hm.swa.sh.abc3.exception.DirectorIsMissingException;
import edu.hm.swa.sh.abc3.exception.IdentifierIsImmutableException;
import edu.hm.swa.sh.abc3.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.mediaservice.rest.types.MessageResponseType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExceptionTransformerTest {
    private static final String INVALID_IDENTIFIER = "Test 1";
    private static final String IDENTIFIER_IMMUTABLE = "Test 2";
    private static final String AUTHOR_MISSING = "Test 3";
    private static final String DIRECTOR_MISSING = "Test 4";

    @Test
    public void testHandleExceptionCommonExceptions() {
        final ExceptionTransformer underTest = new ExceptionTransformer();

        final BaseException invalidIdentifierException = new InvalidIdentifierException(INVALID_IDENTIFIER);
        final BaseException identifierIsImmutableException = new IdentifierIsImmutableException(IDENTIFIER_IMMUTABLE);

        final MessageResponseType result1 = underTest.handleException(invalidIdentifierException);
        assertEquals("Message should be " + INVALID_IDENTIFIER, INVALID_IDENTIFIER, result1.getMessage());
        assertEquals("Code should be " + invalidIdentifierException.getErrorCode(),
                invalidIdentifierException.getErrorCode(), result1.getCode());

        final MessageResponseType result2 = underTest.handleException(identifierIsImmutableException);
        assertEquals("Message should be " + IDENTIFIER_IMMUTABLE, IDENTIFIER_IMMUTABLE, result2.getMessage());
        assertEquals("Code should be " + identifierIsImmutableException.getErrorCode(),
                identifierIsImmutableException.getErrorCode(), result2.getCode());
    }

    @Test
    public void testHandleExceptionBookException() {
        final ExceptionTransformer underTest = new ExceptionTransformer();

        final BaseException authorIsMissingException = new AuthorIsMissingException(AUTHOR_MISSING);

        final MessageResponseType result = underTest.handleException(authorIsMissingException);
        assertEquals("Message should be " + AUTHOR_MISSING, AUTHOR_MISSING, result.getMessage());
        assertEquals("Code should be " + authorIsMissingException.getErrorCode(),
                authorIsMissingException.getErrorCode(), result.getCode());
    }

    @Test
    public void testHandleExceptionDiscException() {
        final ExceptionTransformer underTest = new ExceptionTransformer();

        final BaseException directorIsMissingException = new DirectorIsMissingException(DIRECTOR_MISSING);

        final MessageResponseType result = underTest.handleException(directorIsMissingException);
        assertEquals("Message should be " + DIRECTOR_MISSING, DIRECTOR_MISSING, result.getMessage());
        assertEquals("Code should be " + directorIsMissingException.getErrorCode(),
                directorIsMissingException.getErrorCode(), result.getCode());
    }
}
