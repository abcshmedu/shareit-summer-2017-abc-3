package edu.hm.swa.sh.abc3.dto;

/**
 * Disc DTO.
 */
public class Disc extends Medium {
    private final String barcode;
    private final String director;
    private final int fsk;

    /**
     * Cstr.
     *
     * @param title    Title of disc.
     * @param barcode  Barcode of disc.
     * @param director Director of disc.
     * @param fsk      FSK of disc.
     */
    public Disc(final String title, final String barcode, final String director, final int fsk) {
        super(title);
        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    /**
     * Returns the Barcode.
     *
     * @return Barcode.
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Returns the director.
     *
     * @return Director.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Returns the FSK of disc.
     *
     * @return FSK.
     */
    public int getFsk() {
        return fsk;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }

        final Disc disc = (Disc) other;

        return fsk == disc.fsk && barcode.equals(disc.barcode) && (director != null ? director.equals(disc.director)
                : disc.director == null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + barcode.hashCode();
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + fsk;
        return result;
    }

    @Override
    public String toString() {
        return "Disc{"
                + "barcode='" + barcode + '\''
                + ", director='" + director + '\''
                + ", fsk=" + fsk
                + '}';
    }
}
