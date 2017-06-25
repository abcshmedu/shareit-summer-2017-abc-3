package edu.hm.swa.sh.abc3.authorize.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * Tokens.
 */
@Entity
@NamedQueries({@NamedQuery(name = TokenEntity.FIND_BY_USER, query = "FROM TokenEntity t WHERE t.user = :user")})
public class TokenEntity {
    public static final String FIND_BY_USER = "findByUser";
    @Id
    private String token;
    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity user;
    private Long ttl;

    /**
     * Cstr.
     */
    public TokenEntity() {

    }

    /**
     * Token.
     *
     * @return token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Set token.
     *
     * @param token token.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * User.
     *
     * @return user.
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Set user.
     *
     * @param user user.
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Get timetolive.
     *
     * @return ttl.
     */
    public Long getTtl() {
        return ttl;
    }

    /**
     * set time to live.
     *
     * @param ttl ttl.
     */
    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
}
