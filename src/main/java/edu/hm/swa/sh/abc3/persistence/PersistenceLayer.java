package edu.hm.swa.sh.abc3.persistence;

import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.dto.Disc;
import edu.hm.swa.sh.abc3.common.exception.IdentifierAlreadyExsistsException;
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
     * @throws IdentifierAlreadyExsistsException if ISBN already exists.
     */
    void storeBook(Book book) throws IdentifierAlreadyExsistsException;

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
     * @param book Book to update.
     * @throws IdentifierIsMissingException if book has no ISBN number.
     * @throws InvalidIdentifierException   if book not exists yet.
     */
    void updateBook(Book book) throws IdentifierIsMissingException, InvalidIdentifierException;

    /**
     * Persist a new disc.
     *
     * @param disc Disc to store.
     * @throws IdentifierAlreadyExsistsException barcode of disc already exists.
     */
    void storeDisc(Disc disc) throws IdentifierAlreadyExsistsException;

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
     * @throws IdentifierIsMissingException if disc has no barcode.
     * @throws InvalidIdentifierException   if disc not exists yet.
     */
    void updateDisc(Disc disc) throws IdentifierIsMissingException, InvalidIdentifierException;
}
