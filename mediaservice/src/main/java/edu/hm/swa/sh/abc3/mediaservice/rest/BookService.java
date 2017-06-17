package edu.hm.swa.sh.abc3.mediaservice.rest;

import edu.hm.swa.sh.abc3.types.media.BookType;

import javax.ws.rs.core.Response;

/**
 * Created by Sese on 17.06.2017.
 */
public interface BookService {
    Response addBook(final String token, final BookType bookType);

    Response getBook(final String token, final String isbn);

    Response getBooks(final String token);

    Response updateBooks(final String token, final String isbn, final BookType bookType);
}
