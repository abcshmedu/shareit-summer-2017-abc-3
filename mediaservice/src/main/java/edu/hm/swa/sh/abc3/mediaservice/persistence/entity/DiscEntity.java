package edu.hm.swa.sh.abc3.mediaservice.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entity for a book.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = DiscEntity.FIND_DISC, query = "FROM DiscEntity b WHERE b.barcode = :barcode")
})
public class DiscEntity {
    public static final String FIND_DISC = "findDisc";

    @Id
    private String barcode;
    private String title;
    private String director;
    private int fsk;

    /**
     * Empty default cstr.
     */
    public DiscEntity() {

    }

    /**
     * Cstr.
     *
     * @param barcode  barcode.
     * @param title    title.
     * @param director director.
     * @param fsk      fsk.
     */
    public DiscEntity(String barcode, String title, String director, int fsk) {
        this.barcode = barcode;
        this.title = title;
        this.director = director;
        this.fsk = fsk;
    }

    /**
     * Barcode of disc.
     *
     * @return barcode.
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Set Barcode of disc.
     *
     * @param barcode barcode.
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * Title of disc.
     *
     * @return disc.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title of disc.
     *
     * @param title title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * FSK of disc.
     *
     * @return fsk.
     */
    public int getFsk() {
        return fsk;
    }

    /**
     * Set fsk of disc.
     *
     * @param fsk fsk.
     */
    public void setFsk(int fsk) {
        this.fsk = fsk;
    }

    /**
     * Director of disc.
     *
     * @return director.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Set director.
     *
     * @param director director.
     */
    public void setDirector(String director) {
        this.director = director;
    }
}
