package edu.hm.swa.sh.abc3.common.dto;

/**
 * Medium DTO.
 */
public class Medium {
    private final String title;

    /**
     * Cstr.
     *
     * @param title of Medium.
     */
    public Medium(final String title) {
        this.title = title;
    }

    /**
     * Returns the title.
     *
     * @return Title.
     */
    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        final Medium medium = (Medium) other;

        return title.equals(medium.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        return "Medium[title='" + title + '\'' + ']';
    }
}
