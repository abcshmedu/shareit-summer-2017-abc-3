package edu.hm.swa.sh.abc3.authorize.common;

/**
 * CredentialsDTO.
 */
public class CredentialsDTO {
    private final String username;
    private final String secret;
    private final String token;

    /**
     * Cstr.
     * @param username Username.
     * @param secret Password.
     * @param token Token.
     */
    private CredentialsDTO(final String username, final String secret, final String token) {
        this.username = username;
        this.secret = secret;
        this.token = token;
    }

    /**
     * Cstr for login.
     * @param username username.
     * @param secret secret.
     */
    public CredentialsDTO(final String username, final String secret) {
        this(username, secret, "");
    }

    /**
     * Cstr for other methods.
     * @param token Token.
     */
    public CredentialsDTO(final String token) {
        this("", "", token);
    }

    /**
     * Username.
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Secret.
     * @return secret.
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Token.
     * @return token.
     */
    public String getToken() {
        return token;
    }
}
