package edu.hm.swa.sh.abc3.authorize.persistence;

import edu.hm.swa.sh.abc3.authorize.common.Permission;
import edu.hm.swa.sh.abc3.authorize.common.TokenDTO;
import edu.hm.swa.sh.abc3.authorize.common.UserDTO;

import java.util.List;

/**
 * AuthPersistenceLayer.
 */
public interface AuthPersistenceLayer {
    /**
     * Returns a user or null.
     *
     * @param username username of user.
     * @return UserDTO or null if user not exists.
     */
    UserDTO getUser(String username);

    /**
     * Returns a user or null.
     *
     * @param userid userid of user.
     * @return UserDTO or null if user not exists.
     */
    UserDTO getUser(long userid);

    /**
     * Store a new Token.
     *
     * @param token TokenDTO.
     */
    void storeToken(TokenDTO token);

    /**
     * Updated a stored token.
     *
     * @param token TokenDTO.
     */
    void updateToken(TokenDTO token);

    /**
     * Returns a token or null.
     *
     * @param token TokenDTO.
     * @return TokenDTO or null if no token was found.
     */
    TokenDTO getToken(String token);

    /**
     * Destroy the given token.
     *
     * @param token TokenDTO.
     */
    void destroyToken(String token);

    /**
     * Returns the List of methods for given permission.
     *
     * @param permission Permission.
     * @return String list with method names.
     */
    List<String> getMethodsForPermission(Permission permission);
}
