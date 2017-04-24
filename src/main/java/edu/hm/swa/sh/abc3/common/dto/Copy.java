package edu.hm.swa.sh.abc3.common.dto;

/**
 * Created by Sebastian on 21.04.2017.
 */
public class Copy {
    private final Medium medium;
    private final String owner;

    public Copy(final Medium medium, final String owner) {
        this.medium = medium;
        this.owner = owner;
    }

    public Medium getMedium() {
        return medium;
    }

    public String getOwner() {
        return owner;
    }
}
