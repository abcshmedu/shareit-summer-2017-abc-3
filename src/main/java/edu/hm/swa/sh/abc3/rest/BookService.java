package edu.hm.swa.sh.abc3.rest;

import edu.hm.swa.sh.abc3.buisness.MediaService;
import edu.hm.swa.sh.abc3.buisness.MediaServiceBean;
import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.exception.AuthorIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierAlreadyExistsException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsImmutableException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.common.exception.TitleIsMissingException;
import edu.hm.swa.sh.abc3.rest.transformer.BookTransformer;
import edu.hm.swa.sh.abc3.rest.transformer.ExceptionTransformer;
import edu.hm.swa.sh.abc3.rest.types.BookType;
import edu.hm.swa.sh.abc3.rest.types.ExceptionResponseType;

import javax.ws.rs.core.Response;

/**
 * Services for books.
 */
public class BookService {
    private static final int STATUS_OK = 200;

    private MediaService mediaService = new MediaServiceBean();
    private BookTransformer bookTransformer = new BookTransformer();
    private ExceptionTransformer exceptionTransformer = new ExceptionTransformer();

    /**
     * Add a new book to application.
     *
     * @param bookType Book to add.
     * @return Response.
     */
    public Response addBook(final BookType bookType) {
        final Book book = bookTransformer.toBook(bookType);
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        try {
            mediaService.addBook(book);
        } catch (final IdentifierAlreadyExistsException | TitleIsMissingException | AuthorIsMissingException
                | InvalidIdentifierException exception) {
            final ExceptionResponseType result = exceptionTransformer.handleException(exception);
            response.entity(result);
        }
        return response.build();
    }

    /**
     * Returns a single book.
     *
     * @param isbn ISBN of book.
     * @return Response.
     */
    public Response getBook(final String isbn) {
        final Book book = mediaService.getBook(isbn);
        final BookType bookType = bookTransformer.toBookType(book);
        return Response.status(STATUS_OK).entity(bookType).build();
    }

    /**
     * Returns all stored books.
     *
     * @return Response.
     */
    public Response getBooks() {
        final Book[] books = mediaService.getBooks();
        final BookType[] result = bookTransformer.toBookTypeArray(books);

        return Response.status(Response.Status.OK).entity(result).build();
    }

    /**
     * Update a book.
     *
     * @param isbn     ISBN of book to update.
     * @param bookType new book data.
     * @return Response.
     */
    public Response updateBooks(final String isbn, final BookType bookType) {
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        try {
            final Book book = bookTransformer.toBook(bookType);
            mediaService.updateBook(isbn, book);
        } catch (final IdentifierIsMissingException | InvalidIdentifierException | IdentifierIsImmutableException
                exception) {
            response.entity(exceptionTransformer.handleException(exception));
        }
        return response.build();
    }
}
