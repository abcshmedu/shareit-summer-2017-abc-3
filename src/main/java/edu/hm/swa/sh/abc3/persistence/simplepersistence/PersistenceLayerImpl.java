package edu.hm.swa.sh.abc3.persistence.simplepersistence;

import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.dto.Disc;
import edu.hm.swa.sh.abc3.common.exception.IdentifierAlreadyExsistsException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.persistence.PersistenceLayer;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple implementation of PersistenceLayer interface.
 * <p>
 * Objects stored in HashMap instead in database.
 * I know, very simple but for the first step enough I think.
 */
@Singleton
public class PersistenceLayerImpl implements PersistenceLayer {
    /**
     * HashMap for books, the key is the ISBN.
     */
    private Map<String, Book> books = new HashMap<>();
    /**
     * HashMap for discs, the key is the barcode.
     */
    private Map<String, Disc> discs = new HashMap<>();

    @Override
    public void storeBook(final Book book) throws IdentifierAlreadyExsistsException {
        if (books.get(book.getIsbn()) != null) {
            throw new IdentifierAlreadyExsistsException("ISBN already exists.");
        }
        books.put(book.getIsbn(), book);
    }

    @Override
    public Book getBook(final String isbn) {
        return books.get(isbn);
    }

    @Override
    public Book[] getBooks() {
        final Set<Map.Entry<String, Book>> booksEntrySet = books.entrySet();
        final List<Book> result = new ArrayList<>();

        for (final Map.Entry entry : booksEntrySet) {
            result.add((Book) entry.getValue());
        }

        return (Book[]) result.toArray();
    }

    @Override
    public void updateBook(final Book book) throws IdentifierIsMissingException, InvalidIdentifierException {
        final String isbn = book.getIsbn();
        if (isbn == null || "".equals(isbn)) {
            throw new IdentifierIsMissingException("Parameter 'isbn' should not be null or empty.");
        }
        if (getBook(isbn) == null) {
            throw new InvalidIdentifierException("Book does not exist");
        }
        books.put(isbn, book);
    }

    @Override
    public void storeDisc(final Disc disc) throws IdentifierAlreadyExsistsException {
        if (discs.get(disc.getBarcode()) != null) {
            throw new IdentifierAlreadyExsistsException("Barcode already exists.");
        }
        discs.put(disc.getBarcode(), disc);
    }

    @Override
    public Disc getDisc(final String barcode) {
        return discs.get(barcode);
    }

    @Override
    public Disc[] getDiscs() {
        final Set<Map.Entry<String, Disc>> booksEntrySet = discs.entrySet();
        final List<Disc> result = new ArrayList<>();

        for (final Map.Entry entry : booksEntrySet) {
            result.add((Disc) entry.getValue());
        }

        return (Disc[]) result.toArray();
    }

    @Override
    public void updateDisc(final Disc disc) throws IdentifierIsMissingException, InvalidIdentifierException {
        final String barcode = disc.getBarcode();
        if (barcode == null || "".equals(barcode)) {
            throw new IdentifierIsMissingException("Parameter 'isbn' should not be null or empty.");
        }
        if (getDisc(barcode) == null) {
            throw new InvalidIdentifierException("Book does not exist");
        }
        discs.put(barcode, disc);
    }
}
