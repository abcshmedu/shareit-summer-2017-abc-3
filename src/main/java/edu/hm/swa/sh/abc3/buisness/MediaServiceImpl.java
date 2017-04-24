package edu.hm.swa.sh.abc3.buisness;

import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.dto.Disc;
import edu.hm.swa.sh.abc3.common.dto.Medium;
import edu.hm.swa.sh.abc3.common.exception.IdentifierAlreadyExsistsException;
import edu.hm.swa.sh.abc3.persistence.PersistenceLayer;

import javax.inject.Inject;

/**
 * Implementation of MediaService.
 */
public class MediaServiceImpl implements MediaService {
    @Inject
    private PersistenceLayer persistenceLayer;

    @Override
    public void addBook(final Book book) throws IdentifierAlreadyExsistsException {
        persistenceLayer.storeBook(book);
    }

    @Override
    public void addDisc(Disc disc) {
        return null;
    }

    @Override
    public Medium[] getBooks() {
        return new Medium[0];
    }

    @Override
    public Medium[] getBook(String isbn) {
        return new Medium[0];
    }

    @Override
    public Medium[] getDiscs() {
        return new Medium[0];
    }

    @Override
    public Medium[] getDisc(String barcode) {
        return new Medium[0];
    }

    @Override
    public void updateBooks(Book book) {
        return null;
    }

    @Override
    public void updateDisc(Disc disc) {
        return null;
    }
}
