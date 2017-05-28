package edu.hm.swa.sh.abc3.authorize.business;

import edu.hm.swa.sh.abc3.authorize.common.CredentialsDTO;
import edu.hm.swa.sh.abc3.authorize.common.TokenDTO;
import edu.hm.swa.sh.abc3.exception.InvalidCredentialsException;
import edu.hm.swa.sh.abc3.exception.InvalidTokenException;
import edu.hm.swa.sh.abc3.exception.UnauthorizedAccessException;

/**
 * Business logic of Authservice.
 */
public interface AuthManager {
    /**
     * Login the given user.
     * @param credentials Credentials of user.
     * @return Token.
     * @throws InvalidCredentialsException if credentials are invalid.
     */
    TokenDTO loginUser(CredentialsDTO credentials) throws InvalidCredentialsException;

    /**
     * Logout the user for given token.
     * @param token CredentialsDTO.
     */
    void logoutUser(CredentialsDTO token);

    /**
     * Validate if the token is valid and user have permission to call method.
     * @param token CredentialsDTO.
     * @param method String.
     * @throws InvalidCredentialsException If no token found.
     * @throws InvalidTokenException if token is invalid.
     * @throws UnauthorizedAccessException if method is not allowed for user.
     */
    void validateToken(CredentialsDTO token, String method) throws InvalidCredentialsException,
            InvalidTokenException, UnauthorizedAccessException;
}
