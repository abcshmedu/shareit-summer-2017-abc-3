package edu.hm.swa.sh.abc3.mediaservice.persistence.simplepersistence;

import edu.hm.swa.sh.abc3.dto.Book;
import edu.hm.swa.sh.abc3.dto.Disc;
import edu.hm.swa.sh.abc3.dto.Medium;
import edu.hm.swa.sh.abc3.mediaservice.persistence.PersistenceLayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A simple implementation of PersistenceLayer interface.
 * <p>
 * Objects stored in HashMap instead in database.
 * I know, very simple but for the first step enough I think.
 */
public class PersistenceLayerBean implements PersistenceLayer {
    private static PersistenceLayerBean instance;

    /**
     * To make persistence layer singleton.
     * @return singleton instance of this class.
     */
    public static PersistenceLayerBean getInstance() {
        if (instance == null) {
            instance = new PersistenceLayerBean();
        }
        return instance;
    }

    /**
     * Private constructor.
     */
    public PersistenceLayerBean() {
    }

    /**
     * HashMap for books, the key is the ISBN.
     */
    private Map<String, Book> books = new HashMap<>();
    /**
     * HashMap for discs, the key is the barcode.
     */
    private Map<String, Disc> discs = new HashMap<>();

    @Override
    public void storeBook(final String isbn, final Book book) {
        this.books.put(isbn, book);
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
        System.out.println("Found books: " + arrayToString(result));
        return result;
    }

    @Override
    public void updateBook(final String isbn, final Book book) {
        books.put(isbn, book);
        System.out.println("Updated book with ISBN '" + isbn + "' to " + book);
    }

    @Override
    public void storeDisc(final String barcode, final Disc disc) {
        discs.put(barcode, disc);
        System.out.println("Disc stored: " + disc);
    }

    @Override
    public Disc getDisc(final String barcode) {
        final Disc result = discs.get(barcode);
        System.out.println("Found disc: " + result);
        return result;
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
        System.out.println("Found discs: " + arrayToString(result));
        return result;
    }

    @Override
    public void updateDisc(final String barcode, final Disc disc) {
        discs.put(barcode, disc);
        System.out.println("Updated disc with barcode '" + barcode + "' to " + disc);
    }

    /**
     * For logging.
     * @param mediums Mediums to convert to string.
     * @return string representation of mediums.
     */
    private String arrayToString(final Medium[] mediums) {
        final StringBuilder builder = new StringBuilder();

        builder.append('[');
        for (final Medium medium : mediums) {
            builder.append(medium);
        }
        builder.append(']');

        return builder.toString();
    }
}
