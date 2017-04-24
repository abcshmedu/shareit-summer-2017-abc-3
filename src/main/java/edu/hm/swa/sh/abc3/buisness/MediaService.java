package edu.hm.swa.sh.abc3.buisness;

import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.dto.Disc;
import edu.hm.swa.sh.abc3.common.dto.Medium;
import edu.hm.swa.sh.abc3.common.exception.IdentifierAlreadyExsistsException;

/**
 * Interface of MediaService.
 */
public interface MediaService {
    /**
     * Add a new Book.
     * @param book Book to add.
     */
    void addBook(Book book) throws IdentifierAlreadyExsistsException;

    /**
     * Add new Disc.
     * @param disc to add.
     */
    void addDisc(Disc disc);

    /**
     * Get all books.
     * @return All books.
     */
    Medium[] getBooks();

    /**
     * Return a single book.
     * @param isbn of book.
     * @return requested book.
     */
    Medium[] getBook(String isbn);

    /**
     * Get all discs.
     * @return all discs.
     */
    Medium[] getDiscs();

    /**
     * Return a single disc.
     * @param barcode of requested disc.
     * @return requested disc.
     */
    Medium[] getDisc(String barcode);

    /**
     * Update a book.
     * @param book updated information of book.
     */
    void updateBooks(Book book);

    /**
     * Update a disc.
     * @param disc update information of disc.
     */
    void updateDisc(Disc disc);
}
