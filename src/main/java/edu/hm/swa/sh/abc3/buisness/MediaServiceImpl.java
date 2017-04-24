package edu.hm.swa.sh.abc3.buisness;

import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.dto.Disc;
import edu.hm.swa.sh.abc3.common.MediaServiceResult;
import edu.hm.swa.sh.abc3.common.dto.Medium;

/**
 * Implementation of MediaService.
 */
public class MediaServiceImpl implements MediaService {

    @Override
    public MediaServiceResult addBook(Book book) {
        return null;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
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
    public MediaServiceResult updateBooks(Book book) {
        return null;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        return null;
    }
}
