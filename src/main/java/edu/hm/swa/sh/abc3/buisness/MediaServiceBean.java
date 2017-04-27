package edu.hm.swa.sh.abc3.buisness;

import edu.hm.swa.sh.abc3.buisness.validate.ISBNValidator;
import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.dto.Disc;
import edu.hm.swa.sh.abc3.common.exception.AuthorIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.DirectorIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierAlreadyExistsException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsImmutableException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.common.exception.TitleIsMissingException;
import edu.hm.swa.sh.abc3.persistence.PersistenceLayer;
import edu.hm.swa.sh.abc3.persistence.simplepersistence.PersistenceLayerBean;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Implementation of MediaService.
 */
@Local
@Stateless(mappedName = "MediaService", name = "MediaService")
public class MediaServiceBean implements MediaService {
    @EJB
    private PersistenceLayer persistenceLayer;
    @Inject
    private ISBNValidator isbnValidator;

    @Override
    public void addBook(final Book book) throws IdentifierAlreadyExistsException, InvalidIdentifierException,
            AuthorIsMissingException, TitleIsMissingException {
        final String isbn = book.getIsbn();
        final String author = book.getAuthor();
        final String title = book.getTitle();

        if (isbn == null || !isbnValidator.checkISBN(isbn)) {
            throw new InvalidIdentifierException("ISBN number is not valid.");
        }
        if (author == null || author.length() < 1) {
            throw new AuthorIsMissingException("Book author name is missing.");
        }
        if (title == null || title.length() < 1) {
            throw new TitleIsMissingException("Book title is missing.");
        }
        persistenceLayer.storeBook(book);
    }

    @Override
    public void addDisc(final Disc disc) throws InvalidIdentifierException, DirectorIsMissingException,
            IdentifierAlreadyExistsException {
        final String barcode = disc.getBarcode();
        final String director = disc.getDirector();

        if (barcode == null || barcode.length() < 1) {
            throw new InvalidIdentifierException("Disc barcode is missing.");
        }
        if (director == null || director.length() < 1) {
            throw new DirectorIsMissingException("Disc director is missing.");
        }
        persistenceLayer.storeDisc(disc);
    }

    @Override
    public Book[] getBooks() {
        return persistenceLayer.getBooks();
    }

    @Override
    public Book getBook(final String isbn) {
        return persistenceLayer.getBook(isbn);
    }

    @Override
    public Disc[] getDiscs() {
        return persistenceLayer.getDiscs();
    }

    @Override
    public Disc getDisc(final String barcode) {
        return persistenceLayer.getDisc(barcode);
    }

    @Override
    public void updateBook(final String isbn, final Book book) throws IdentifierIsMissingException,
            InvalidIdentifierException, IdentifierIsImmutableException {
        persistenceLayer.updateBook(isbn, book);
    }

    @Override
    public void updateDisc(final Disc disc) throws IdentifierIsMissingException, InvalidIdentifierException {
        persistenceLayer.updateDisc(disc);
    }
}
