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
        @NamedQuery(name = BookEntity.FIND_BOOK, query = "FROM BookEntity b WHERE b.isbn = :isbn")
})
public class BookEntity {
    public static final String FIND_BOOK = "findBook";

    @Id
    private String isbn;
    private String title;
    private String author;

    /**
     * Empty default cstr.
     */
    public BookEntity() {

    }

    /**
     * Cstr.
     *
     * @param title  title.
     * @param author author.
     * @param isbn   isbn.
     */
    public BookEntity(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    /**
     * Title of book.
     *
     * @return title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Title of book.
     *
     * @param title title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Author of book.
     *
     * @return author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Author of book.
     *
     * @param author author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * isbn of book.
     *
     * @return isbn.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * isbn of book.
     *
     * @param isbn isbn.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
