package edu.hm.swa.sh.abc3.authorize.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Permission entity.
 */
@Entity
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String method;

    /**
     * Cstr.
     *
     * @param method methodname.
     */
    public PermissionEntity(String method) {
        this.method = method;
    }

    /**
     * Dflt Cstr.
     */
    public PermissionEntity() {
    }

    /**
     * Id of permission.
     *
     * @return id id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set id of permission.
     *
     * @param id id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method of permission.
     *
     * @return methodname.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Set method name of permission.
     *
     * @param method method.
     */
    public void setMethod(String method) {
        this.method = method;
    }
}
