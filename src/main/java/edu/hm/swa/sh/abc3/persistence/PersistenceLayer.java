package edu.hm.swa.sh.abc3.persistence;

import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.dto.Disc;

/**
 * Interface of ShareIt persistence layer.
 */
public interface PersistenceLayer {
    /**
     * Persist a new book.
     *
     * @param book Book to store.
     */
    void storeBook(Book book);

    /**
     * Find a single book.
     *
     * @param isbn ISBN of book to find.
     * @return Book or null if no book was found.
     */
    Book getBook(String isbn);

    /**
     * Return all books.
     *
     * @return all stored books.
     */
    Book[] getBooks();

    /**
     * Update a single book.
     *
     * @param isbn of book to update.
     * @param book Book to update.
     */
    void updateBook(String isbn, Book book);

    /**
     * Persist a new disc.
     *
     * @param disc Disc to store.
     */
    void storeDisc(Disc disc);

    /**
     * Find a single Disc by its barcode.
     *
     * @param barcode Barcode of the disc to find.
     * @return Disc or null if no disc found.
     */
    Disc getDisc(String barcode);

    /**
     * Return all Discs.
     *
     * @return all stored discs.
     */
    Disc[] getDiscs();

    /**
     * Update information about a disc.
     *
     * @param disc    Disc to update.
     * @param barcode Barcode of disc.
     */
    void updateDisc(String barcode, Disc disc);
}
