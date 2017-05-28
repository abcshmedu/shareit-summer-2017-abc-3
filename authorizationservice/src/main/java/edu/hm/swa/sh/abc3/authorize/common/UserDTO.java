package edu.hm.swa.sh.abc3.authorize.common;

/**
 * UserDTO.
 */
public class UserDTO {
    private final long userId;
    private final String username;
    private final String secret;
    private final Permission userrole;

    /**
     * Cstr.
     *
     * @param userId   user id.
     * @param username username.
     * @param secret   secret.
     * @param userrole Users permission role.
     */
    public UserDTO(final long userId, final String username, final String secret, final Permission userrole) {
        this.userId = userId;
        this.username = username;
        this.secret = secret;
        this.userrole = userrole;
    }

    /**
     * User id.
     *
     * @return UserId.
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Username.
     *
     * @return Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Secret.
     *
     * @return Secret.
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Userrole.
     *
     * @return userrole.
     */
    public Permission getUserrole() {
        return userrole;
    }
}
