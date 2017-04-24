package edu.hm.swa.sh.abc3.persistence;

import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.dto.Disc;
import edu.hm.swa.sh.abc3.common.exception.IdentifierAlreadyExsists;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.InvalidIdentifierException;

/**
 * Interface of ShareIt persistence layer.
 */
public interface PersistenceLayer {
    /**
     * Persist a new book.
     *
     * @param book Book to store.
     */
    void storeBook(Book book) throws IdentifierAlreadyExsists;

    /**
     * Find a single book.
     *
     * @param isbn ISBN of book to find.
     * @return Book or null if no book was found.
     */
    Book getBook(String isbn) throws NullPointerException;

    /**
     * Return all books.
     *
     * @return all stored books.
     */
    Book[] getBooks();

    /**
     * Update a single book.
     *
     * @param book Book to update.
     */
    void updateBook(Book book) throws IdentifierIsMissingException, InvalidIdentifierException;

    /**
     * Persist a new disc.
     *
     * @param disc Disc to store.
     */
    void storeDisc(Disc disc) throws IdentifierAlreadyExsists;

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
     * @param disc Disc to update.
     */
    void updateDisc(Disc disc) throws IdentifierIsMissingException, InvalidIdentifierException;
}
