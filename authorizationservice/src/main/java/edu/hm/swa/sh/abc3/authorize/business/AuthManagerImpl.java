package edu.hm.swa.sh.abc3.authorize.business;

import edu.hm.swa.sh.abc3.authorize.common.CredentialsDTO;
import edu.hm.swa.sh.abc3.authorize.common.TokenDTO;
import edu.hm.swa.sh.abc3.authorize.common.UserDTO;
import edu.hm.swa.sh.abc3.authorize.persistence.AuthPersistenceLayer;
import edu.hm.swa.sh.abc3.exception.InvalidCredentialsException;
import edu.hm.swa.sh.abc3.exception.InvalidTokenException;
import edu.hm.swa.sh.abc3.exception.UnauthorizedAccessException;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of AuthManager.
 */
public class AuthManagerImpl implements AuthManager {
    protected static final long TOKEN_TTL = 1_800_000;
    @Inject
    private AuthPersistenceLayer persistenceLayer;

    @Override
    public TokenDTO loginUser(final CredentialsDTO credentials) throws InvalidCredentialsException {
        if (StringUtils.isBlank(credentials.getUsername())) {
            throw new InvalidCredentialsException("No username given.");
        }
        final UserDTO user = persistenceLayer.getUser(credentials.getUsername());

        if (user == null || !user.getSecret().equals(credentials.getSecret())) {
            throw new InvalidCredentialsException("Credentials are invalid.");
        }

        final TokenDTO token = createNewToken(user);
        persistenceLayer.storeToken(token);
        return token;
    }

    @Override
    public void logoutUser(final CredentialsDTO token) {
        if (StringUtils.isNotBlank(token.getToken())) {
            persistenceLayer.destroyToken(token.getToken());
        }
    }

    @Override
    public void validateToken(final CredentialsDTO token, final String method) throws InvalidCredentialsException,
            InvalidTokenException, UnauthorizedAccessException {
        if (StringUtils.isNotBlank(token.getToken())) {
            final TokenDTO tokenDTO = persistenceLayer.getToken(token.getToken());
            if (tokenDTO == null) {
                throw new InvalidTokenException("Token is invalid.");
            }

            if (tokenDTO.getTimeToLive() > System.currentTimeMillis()) {
                final TokenDTO newToken = new TokenDTO(tokenDTO.getToken(), tokenDTO.getUserid(), System
                        .currentTimeMillis() + TOKEN_TTL);
                persistenceLayer.updateToken(newToken);
            } else {
                throw new InvalidTokenException("Token has expired.");
            }

            final UserDTO userDTO = persistenceLayer.getUser(tokenDTO.getUserid());
            if (userDTO == null) {
                throw new InvalidTokenException("Token is invalid.");
            }

            final List<String> permissionList = persistenceLayer.getMethodsForPermission(userDTO.getUserrole());

            for (final String userMethods : permissionList) {
                if (userMethods.equalsIgnoreCase(method)) {
                    return;
                }
            }
            throw new UnauthorizedAccessException("Method is not allowed for this user.");
        } else {
            throw new InvalidCredentialsException("No token found!");
        }
    }

    /**
     * Generate a new unique Token for user.
     *
     * @param user UserDTO.
     * @return TokenDTO.
     */
    private TokenDTO createNewToken(final UserDTO user) {
        String tokenUuid;

        do {
            tokenUuid = UUID.randomUUID().toString();
        } while (persistenceLayer.getToken(tokenUuid) != null);

        return new TokenDTO(tokenUuid, user.getUserId(), System.currentTimeMillis() + TOKEN_TTL);
    }
}
