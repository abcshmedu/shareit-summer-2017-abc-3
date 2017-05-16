package edu.hm.swa.sh.abc3.mediaservice.rest;

import edu.hm.swa.sh.abc3.mediaservice.buisness.MediaService;
import edu.hm.swa.sh.abc3.mediaservice.buisness.MediaServiceBean;
import edu.hm.swa.sh.abc3.mediaservice.common.dto.Book;
import edu.hm.swa.sh.abc3.mediaservice.common.exception.AuthorIsMissingException;
import edu.hm.swa.sh.abc3.mediaservice.common.exception.IdentifierAlreadyExistsException;
import edu.hm.swa.sh.abc3.mediaservice.common.exception.IdentifierIsImmutableException;
import edu.hm.swa.sh.abc3.mediaservice.common.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.mediaservice.common.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.mediaservice.common.exception.TitleIsMissingException;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.BookTransformer;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.ExceptionTransformer;
import edu.hm.swa.sh.abc3.mediaservice.rest.types.BookType;
import edu.hm.swa.sh.abc3.mediaservice.rest.types.MessageResponseType;

import javax.ws.rs.core.Response;

/**
 * Services for books.
 */
public class BookService {
    private static final int STATUS_OK = 200;

    private MediaService mediaService;
    private BookTransformer bookTransformer;
    private ExceptionTransformer exceptionTransformer;

    /**
     * Cstr.
     */
    public BookService() {
        this.mediaService = new MediaServiceBean();
        this.bookTransformer = new BookTransformer();
        this.exceptionTransformer = new ExceptionTransformer();
    }

    /**
     * Add a new book to application.
     *
     * @param bookType Book to add.
     * @return Response.
     */
    public Response addBook(final BookType bookType) {
        final Book book = bookTransformer.toBook(bookType);
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        MessageResponseType result;
        try {
            mediaService.addBook(book);
            result = createOkMessageResponse();
        } catch (final IdentifierAlreadyExistsException | TitleIsMissingException | AuthorIsMissingException
                | InvalidIdentifierException exception) {
            result = exceptionTransformer.handleException(exception);
        }
        response.entity(result);
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
        MessageResponseType result;
        try {
            final Book book = bookTransformer.toBook(bookType);
            mediaService.updateBook(isbn, book);
            result = createOkMessageResponse();
        } catch (final IdentifierIsMissingException | InvalidIdentifierException | IdentifierIsImmutableException
                exception) {
            result = exceptionTransformer.handleException(exception);
        }
        response.entity(result);
        return response.build();
    }

    /**
     * Create a new MessageRespnoseType.
     * @return new messageResponse object.
     */
    private MessageResponseType createOkMessageResponse() {
        return new MessageResponseType().setCode(STATUS_OK).setMessage("OK");
    }
}
