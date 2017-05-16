package edu.hm.swa.sh.abc3.mediaservice.buisness.validate;


import edu.hm.swa.sh.abc3.mediaservice.common.exception.InvalidIdentifierException;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IdentifierValidatorTest {
    @Test
    public void testCheckIdentifier() throws InvalidIdentifierException {
        final String identifier = "978-3-7657-2781-8";
        final IdentifierValidator underTest = new IdentifierValidator();
        final boolean result = underTest.checkIdentifier(identifier);

        assertTrue("Identifier should be valid", result);
    }

    @Test
    public void testCheckIdentifierInvalidChecksum() throws InvalidIdentifierException {
        final String identifier = "978-3-7657-2781-6";
        final IdentifierValidator underTest = new IdentifierValidator();
        final boolean result = underTest.checkIdentifier(identifier);

        assertFalse("Identifier should be invalid", result);
    }

    @Test(expected = InvalidIdentifierException.class)
    public void testCheckIdentifierInvalidLength() throws InvalidIdentifierException {
        final String identifier = "978-3";
        final IdentifierValidator underTest = new IdentifierValidator();
        underTest.checkIdentifier(identifier);
    }
}
