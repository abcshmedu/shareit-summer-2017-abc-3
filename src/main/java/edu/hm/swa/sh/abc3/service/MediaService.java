package edu.hm.swa.sh.abc3.service;

import edu.hm.swa.sh.abc3.common.Book;
import edu.hm.swa.sh.abc3.common.Disc;
import edu.hm.swa.sh.abc3.common.Medium;

/**
 * Created by Sebastian on 21.04.2017.
 */
public interface MediaService {
    MediaServiceResult addBook(Book book);

    MediaServiceResult addDisc(Disc disc);

    Medium[] getBooks();

    Medium[] getDiscs();

    MediaServiceResult updateBooks(Book book);

    MediaServiceResult updateDisc(Disc disc);
}
