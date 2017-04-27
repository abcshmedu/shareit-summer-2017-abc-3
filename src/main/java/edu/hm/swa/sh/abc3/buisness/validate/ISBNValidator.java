package edu.hm.swa.sh.abc3.buisness.validate;

import edu.hm.swa.sh.abc3.common.exception.InvalidIdentifierException;

import javax.enterprise.context.RequestScoped;

/**
 * Checks if a ISBN number is valid.
 */
@RequestScoped
public class ISBNValidator {
    private static final int ISBN13_LENGTH = 13;

    private static final int ISBN13_MODULO = 10;
    private static final int ISBN13_MULTYPLIER = 3;

    /**
     * Check if a given ISBN number is valid or not.
     * Can check ISBN 13 and ISBN 10.
     *
     * @param isbn ISBN number to validate.
     * @return true if ISBN is valid, false otherwise.
     * @throws InvalidIdentifierException if ISBN number is to short or to long.
     */
    public boolean checkISBN(final String isbn) throws InvalidIdentifierException {
        final String cleanedISBN = isbn.replace("-", "").replace(" ", "");

        if (cleanedISBN.length() == ISBN13_LENGTH) {
            return checkISBN13(cleanedISBN);
        }
        throw new InvalidIdentifierException("ISBN has invalid length.");
    }

    /**
     * Validate an ISBN 13 number.
     *
     * @param isbn number without '-' chars and spaces.
     * @return true if ISBN 13 is valid, false otherwise.
     */
    private boolean checkISBN13(final String isbn) {
        int sum = 0;
        for (int index = 0; index < ISBN13_LENGTH; index++) {
            final int singleInt = Integer.parseInt(String.valueOf(isbn.charAt(index)));
            sum += (singleInt * Math.pow(ISBN13_MULTYPLIER, (index) % 2));
        }
        return (sum % ISBN13_MODULO) == 0;
    }
}
