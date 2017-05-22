package edu.hm.swa.sh.abc3.mediaservice.rest.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Book type for REST Service.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BookType {
    private String title;
    private String author;
    private String isbn;

    /**
     * Returns the Title.
     * @return Title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the Title.
     * @param title Title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the Author.
     * @return Author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     * @param author Author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the ISBN.
     * @return ISBN.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN.
     * @param isbn ISBN.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
