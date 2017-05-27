package edu.hm.swa.sh.abc3.mediaservice.rest;

import edu.hm.swa.sh.abc3.dto.Book;
import edu.hm.swa.sh.abc3.exception.BaseException;
import edu.hm.swa.sh.abc3.exception.IdentifierIsImmutableException;
import edu.hm.swa.sh.abc3.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.mediaservice.business.MediaService;
import edu.hm.swa.sh.abc3.mediaservice.business.MediaServiceBean;
import edu.hm.swa.sh.abc3.mediaservice.rest.auth.AuthserviceOutbound;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.BookTransformer;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.ExceptionTransformer;
import edu.hm.swa.sh.abc3.types.MessageResponseType;
import edu.hm.swa.sh.abc3.types.media.BookType;

import javax.ws.rs.core.Response;

/**
 * Services for books.
 */
public class BookService {
    private static final int STATUS_OK = 200;

    private MediaService mediaService;
    private BookTransformer bookTransformer;
    private ExceptionTransformer exceptionTransformer;

    private AuthserviceOutbound authserviceOutbound;

    /**
     * Cstr.
     */
    public BookService() {
        this.mediaService = new MediaServiceBean();
        this.bookTransformer = new BookTransformer();
        this.exceptionTransformer = new ExceptionTransformer();
        this.authserviceOutbound = new AuthserviceOutbound();
    }

    /**
     * Add a new book to application.
     *
     * @param token    user token.
     * @param bookType Book to add.
     * @return Response.
     */
    public Response addBook(String token, final BookType bookType) {
        final Book book = bookTransformer.toBook(bookType);
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        MessageResponseType result;
        try {
            this.authserviceOutbound.validateToken(token, "addbook");
            mediaService.addBook(book);
            result = createOkMessageResponse();
        } catch (final BaseException exception) {
            result = exceptionTransformer.handleException(exception);
        }
        response.entity(result);
        return response.build();
    }

    /**
     * Returns a single book.
     *
     * @param token user token.
     * @param isbn  ISBN of book.
     * @return Response.
     */
    public Response getBook(String token, final String isbn) {
        try {
            this.authserviceOutbound.validateToken(token, "addbook");
        } catch (BaseException e) {
            e.printStackTrace();
        }
        final Book book = mediaService.getBook(isbn);
        final BookType bookType = bookTransformer.toBookType(book);
        return Response.status(STATUS_OK).entity(bookType).build();
    }

    /**
     * Returns all stored books.
     *
     * @param token user token.
     * @return Response.
     */
    public Response getBooks(String token) {
        final Book[] books = mediaService.getBooks();
        final BookType[] result = bookTransformer.toBookTypeArray(books);

        return Response.status(Response.Status.OK).entity(result).build();
    }

    /**
     * Update a book.
     *
     * @param token    user token.
     * @param isbn     ISBN of book to update.
     * @param bookType new book data.
     * @return Response.
     */
    public Response updateBooks(String token, final String isbn, final BookType bookType) {
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
     *
     * @return new messageResponse object.
     */
    private MessageResponseType createOkMessageResponse() {
        return new MessageResponseType().setCode(STATUS_OK).setMessage("OK");
    }
}
