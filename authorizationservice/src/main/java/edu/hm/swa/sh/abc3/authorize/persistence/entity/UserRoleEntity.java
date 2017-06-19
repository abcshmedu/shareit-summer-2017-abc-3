package edu.hm.swa.sh.abc3.authorize.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Set;

/**
 * Userrole.
 */
@Entity
@NamedQueries({@NamedQuery(name = UserRoleEntity.FIND_BY_NAME, query = "FROM UserRoleEntity u WHERE u.name = :name")})
public class UserRoleEntity {
    public static final String FIND_BY_NAME = "findbyname";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PermissionEntity> permissions;

    /**
     * Dflt. Cstr.
     */
    public UserRoleEntity() {

    }

    /**
     * Id of role.
     *
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set id of role.
     *
     * @param id id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Name of role.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of role.
     *
     * @param name role name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Permissions of role.
     *
     * @return permission.
     */
    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    /**
     * Sets permission of role.
     *
     * @param permissions permissions.
     */
    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }
}
