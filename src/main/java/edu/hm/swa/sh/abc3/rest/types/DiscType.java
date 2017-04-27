package edu.hm.swa.sh.abc3.rest.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Book type for REST Service.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DiscType {
    private String title;
    private String director;
    private String barcode;

    /**
     * Returns the Title.
     * @return Title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     * @param title Title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the Director.
     * @return Director.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets the Director.
     * @param director Director.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Returns the Barcode.
     * @return Barcode.
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Sets the barcode.
     * @param barcode Barcode.
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

}
