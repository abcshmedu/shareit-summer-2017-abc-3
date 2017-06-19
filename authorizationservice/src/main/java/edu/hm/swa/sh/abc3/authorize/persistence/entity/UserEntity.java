package edu.hm.swa.sh.abc3.authorize.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * User Entity.
 */
@Entity
@NamedQueries({@NamedQuery(name = UserEntity.FIND_BY_USERNAME, query = "FROM UserEntity u WHERE u.username = " +
        ":username")})
public class UserEntity {
    public static final String FIND_BY_USERNAME = "findByUsername";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String secret;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserRoleEntity userRoleEntity;

    /**
     * Cstr.
     */
    public UserEntity() {
    }

    /**
     * Id of user.
     *
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set id of user.
     *
     * @param id id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Username.
     *
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username.
     *
     * @param username username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Secret of user.
     *
     * @return password.
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Set secret.
     *
     * @param secret secret.
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * userrole of user.
     *
     * @return Userrole.
     */
    public UserRoleEntity getUserRoleEntity() {
        return userRoleEntity;
    }

    /**
     * Set userrole of user.
     *
     * @param userRoleEntity user role.
     */
    public void setUserRoleEntity(UserRoleEntity userRoleEntity) {
        this.userRoleEntity = userRoleEntity;
    }
}
