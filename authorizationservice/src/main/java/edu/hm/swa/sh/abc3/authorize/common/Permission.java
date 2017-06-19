package edu.hm.swa.sh.abc3.authorize.common;

/**
 * Permissions.
 */
public enum Permission {
    UNKOWN, REGISTRED, ADMIN;

    /**
     * Find the correct Permission.
     *
     * @param name name.
     * @return permission.
     */
    public static Permission find(final String name) {
        if (ADMIN.name().equals(name)) {
            return ADMIN;
        } else if (REGISTRED.name().equals(name)) {
            return REGISTRED;
        } else {
            return UNKOWN;
        }
    }
}
