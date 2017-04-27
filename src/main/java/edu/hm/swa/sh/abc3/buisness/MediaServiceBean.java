package edu.hm.swa.sh.abc3.buisness;

import edu.hm.swa.sh.abc3.buisness.validate.IdentifierValidator;
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

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
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
    private IdentifierValidator identifierValidator;

    @Override
    public void addBook(final Book book) throws IdentifierAlreadyExistsException, InvalidIdentifierException,
            AuthorIsMissingException, TitleIsMissingException {
        final String isbn = book.getIsbn();
        final String author = book.getAuthor();
        final String title = book.getTitle();

        if (isbn == null || !identifierValidator.checkIdentifier(isbn)) {
            throw new InvalidIdentifierException("ISBN number is not valid.");
        }
        if (author == null || author.length() < 1) {
            throw new AuthorIsMissingException("Book author name is missing.");
        }
        if (title == null || title.length() < 1) {
            throw new TitleIsMissingException("Book title is missing.");
        }
        if (persistenceLayer.getBook(book.getIsbn()) != null) {
            throw new IdentifierAlreadyExistsException("ISBN already exists.");
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
        if (isbn == null || "".equals(isbn)) {
            throw new IdentifierIsMissingException("Parameter 'ISBN' should not be null or empty.");
        }
        final Book oldData = getBook(isbn);
        if (oldData == null) {
            throw new InvalidIdentifierException("Book does not exist");
        }
        if (!isbn.equals(book.getIsbn())) {
            throw new IdentifierIsImmutableException("ISBN could not be change");
        }

        persistenceLayer.updateBook(isbn, book);
    }

    @Override
    public void updateDisc(final Disc disc) throws IdentifierIsMissingException, InvalidIdentifierException {
        persistenceLayer.updateDisc(disc);
    }
}
