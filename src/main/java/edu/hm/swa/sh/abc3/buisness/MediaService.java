package edu.hm.swa.sh.abc3.buisness;

import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.dto.Disc;
import edu.hm.swa.sh.abc3.common.exception.AuthorIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.DirectorIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierAlreadyExistsException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsImmutableException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.common.exception.TitleIsMissingException;

/**
 * Interface of MediaService.
 */
public interface MediaService {
    /**
     * Add a new Book.
     *
     * @param book Book to add.
     * @throws IdentifierAlreadyExistsException If already a book with same ISBN stored.
     * @throws InvalidIdentifierException       If ISBN number is invalid.
     * @throws AuthorIsMissingException         If the book author is missing.
     * @throws TitleIsMissingException          If the book title is missing.
     */
    void addBook(Book book) throws IdentifierAlreadyExistsException, InvalidIdentifierException,
            AuthorIsMissingException, TitleIsMissingException;

    /**
     * Add new Disc.
     *
     * @param disc to add.
     * @throws InvalidIdentifierException       If disc barcode is missing.
     * @throws DirectorIsMissingException       If disc director is missing.
     * @throws IdentifierAlreadyExistsException If already a disc is persisted with same barcode.
     * @throws TitleIsMissingException          If the title of the disc is missing.
     */
    void addDisc(Disc disc) throws InvalidIdentifierException, DirectorIsMissingException,
            IdentifierAlreadyExistsException, TitleIsMissingException;

    /**
     * Get all books.
     *
     * @return All books.
     */
    Book[] getBooks();

    /**
     * Return a single book.
     *
     * @param isbn of book.
     * @return requested book.
     */
    Book getBook(String isbn);

    /**
     * Get all discs.
     *
     * @return all discs.
     */
    Disc[] getDiscs();

    /**
     * Return a single disc.
     *
     * @param barcode of requested disc.
     * @return requested disc.
     */
    Disc getDisc(String barcode);

    /**
     * Update a book.
     *
     * @param isbn isbn of book to update.
     * @param book updated information of book.
     * @throws IdentifierIsMissingException   If ISBN number is missing.
     * @throws InvalidIdentifierException     If no book with this ISBN number exists.
     * @throws IdentifierIsImmutableException If trying to change ISBN number.
     */
    void updateBook(String isbn, Book book) throws IdentifierIsMissingException, InvalidIdentifierException,
            IdentifierIsImmutableException;

    /**
     * Update a disc.
     *
     * @param disc    update information of disc.
     * @param barcode Barcode of disc which should be updated.
     * @throws IdentifierIsMissingException   If disc barcode is missing.
     * @throws InvalidIdentifierException     If no disc with this barcode exists.
     * @throws IdentifierIsImmutableException If barcode and barcode of disc didn't match.
     */
    void updateDisc(String barcode, Disc disc) throws IdentifierIsMissingException, InvalidIdentifierException,
            IdentifierIsImmutableException;
}
