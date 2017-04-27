package edu.hm.swa.sh.abc3.persistence.simplepersistence;

import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.dto.Disc;
import edu.hm.swa.sh.abc3.common.exception.IdentifierAlreadyExistsException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.persistence.PersistenceLayer;

import javax.ejb.Local;
import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A simple implementation of PersistenceLayer interface.
 * <p>
 * Objects stored in HashMap instead in database.
 * I know, very simple but for the first step enough I think.
 */
@Local
@Singleton(mappedName = "PersistenceLayer", name = "PersistenceLayer")
public class PersistenceLayerBean implements PersistenceLayer {
    /**
     * HashMap for books, the key is the ISBN.
     */
    private Map<String, Book> books = new HashMap<>();
    /**
     * HashMap for discs, the key is the barcode.
     */
    private Map<String, Disc> discs = new HashMap<>();

    @Override
    public void storeBook(final Book book) {
        this.books.put(book.getIsbn(), book);
        System.out.println("Book stored: " + book);
    }

    @Override
    public Book getBook(final String isbn) {
        final Book result = this.books.get(isbn);
        System.out.println("Found book: " + result);
        return result;
    }

    @Override
    public Book[] getBooks() {
        final Set<Map.Entry<String, Book>> booksEntrySet = books.entrySet();
        final Book[] result = new Book[booksEntrySet.size()];
        int index = 0;

        for (final Map.Entry entry : booksEntrySet) {
            result[index] = ((Book) entry.getValue());
            index++;
        }
        System.out.println("Found books: " + result);
        return result;
    }

    @Override
    public void updateBook(final String isbn, final Book book) {
        books.put(isbn, book);
        System.out.println("Updated book with ISBN '" + isbn + "' to " + book);
    }

    @Override
    public void storeDisc(final Disc disc) throws IdentifierAlreadyExistsException {
        if (discs.get(disc.getBarcode()) != null) {
            throw new IdentifierAlreadyExistsException("Barcode already exists.");
        }
        discs.put(disc.getBarcode(), disc);
    }

    @Override
    public Disc getDisc(final String barcode) {
        return discs.get(barcode);
    }

    @Override
    public Disc[] getDiscs() {
        final Set<Map.Entry<String, Disc>> discsEntrySet = discs.entrySet();
        final Disc[] result = new Disc[discsEntrySet.size()];
        int index = 0;

        for (final Map.Entry entry : discsEntrySet) {
            result[index] = ((Disc) entry.getValue());
            index++;
        }

        return result;
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
