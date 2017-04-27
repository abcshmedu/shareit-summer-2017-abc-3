package edu.hm.swa.sh.abc3.buisness.validate;


import edu.hm.swa.sh.abc3.common.exception.InvalidIdentifierException;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ISBNValidatorTest {
    @Test
    public void testCheckISBN() throws InvalidIdentifierException {
        final String isbn = "978-3-7657-2781-8";
        final ISBNValidator underTest = new ISBNValidator();
        final boolean result = underTest.checkISBN(isbn);

        assertTrue("ISBN should be valid", result);
    }

    @Test
    public void testCheckISBNInvalidISBN() throws InvalidIdentifierException {
        final String isbn = "978-3-7657-2781-6";
        final ISBNValidator underTest = new ISBNValidator();
        final boolean result = underTest.checkISBN(isbn);

        assertFalse("ISBN should be invalid", result);
    }

    @Test(expected = InvalidIdentifierException.class)
    public void testCheckISBNInvalidLength() throws InvalidIdentifierException {
        final String isbn = "978-3";
        final ISBNValidator underTest = new ISBNValidator();
        underTest.checkISBN(isbn);
    }
}
