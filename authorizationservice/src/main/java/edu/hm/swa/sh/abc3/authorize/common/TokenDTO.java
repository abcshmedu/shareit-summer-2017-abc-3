package edu.hm.swa.sh.abc3.authorize.common;

/**
 * TokenDTO.
 */
public class TokenDTO {
    private final String token;
    private final long userid;
    private final long timeToLive;

    /**
     * Cstr.
     * @param token Token.
     * @param userid Userid.
     * @param timeToLive Token ttl.
     */
    public TokenDTO(final String token, final long userid, final long timeToLive) {
        this.token = token;
        this.userid = userid;
        this.timeToLive = timeToLive;
    }

    /**
     * token.
     * @return Token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Userid.
     * @return Userid.
     */
    public long getUserid() {
        return userid;
    }

    /**
     * TimeToLive.
     * @return TimeToLive.
     */
    public long getTimeToLive() {
        return timeToLive;
    }
}
