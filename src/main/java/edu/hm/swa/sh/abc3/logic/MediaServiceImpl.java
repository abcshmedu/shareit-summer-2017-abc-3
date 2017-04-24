package edu.hm.swa.sh.abc3.logic;

import edu.hm.swa.sh.abc3.common.Book;
import edu.hm.swa.sh.abc3.common.Disc;
import edu.hm.swa.sh.abc3.common.Medium;
import edu.hm.swa.sh.abc3.common.MediaServiceResult;

/**
 * Created by Sebastian on 21.04.2017.
 */
public class MediaServiceImpl implements MediaService {

    @Override
    public MediaServiceResult addBook(final Book book) {
        return null;
    }

    @Override
    public MediaServiceResult addDisc(final Disc disc) {
        return null;
    }

    @Override
    public Medium[] getBooks() {
        return new Medium[0];
    }

    @Override
    public Medium[] getDiscs() {
        return new Medium[0];
    }

    @Override
    public MediaServiceResult updateBooks(final Book book) {
        return null;
    }

    @Override
    public MediaServiceResult updateDisc(final Disc disc) {
        return null;
    }
}
