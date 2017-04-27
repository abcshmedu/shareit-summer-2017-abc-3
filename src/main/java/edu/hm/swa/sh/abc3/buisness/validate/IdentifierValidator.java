package edu.hm.swa.sh.abc3.buisness.validate;

import edu.hm.swa.sh.abc3.common.exception.InvalidIdentifierException;

import javax.enterprise.context.RequestScoped;

/**
 * Checks if a ISBN number is valid.
 */
@RequestScoped
public class IdentifierValidator {
    private static final int IDENTIFIER_LENGTH = 13;

    private static final int IDENTIFIER_MODULO = 10;
    private static final int IDENTIFIER_MULTYPLIER = 3;

    /**
     * Check if a given identifier is valid or not.
     * Can check ISBN 13 and EAN.
     *
     * @param identifier number to validate.
     * @return true if identifier is valid, false otherwise.
     * @throws InvalidIdentifierException if identifier is to short or to long.
     */
    public boolean checkIdentifier(final String identifier) throws InvalidIdentifierException {
        final String cleanedIdentifier = identifier.replace("-", "").replace(" ", "");

        if (cleanedIdentifier.length() == IDENTIFIER_LENGTH) {
            int sum = 0;
            for (int index = 0; index < IDENTIFIER_LENGTH; index++) {
                final int singleInt = Integer.parseInt(String.valueOf(cleanedIdentifier.charAt(index)));
                sum += (singleInt * Math.pow(IDENTIFIER_MULTYPLIER, (index) % 2));
            }
            return (sum % IDENTIFIER_MODULO) == 0;
        }
        throw new InvalidIdentifierException("Identifier has invalid length.");
    }
}
