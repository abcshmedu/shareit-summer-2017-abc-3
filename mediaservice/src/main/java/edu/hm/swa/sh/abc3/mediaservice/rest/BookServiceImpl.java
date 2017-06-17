package edu.hm.swa.sh.abc3.mediaservice.rest;

import edu.hm.swa.sh.abc3.dto.Book;
import edu.hm.swa.sh.abc3.exception.BaseException;
import edu.hm.swa.sh.abc3.mediaservice.business.MediaService;
import edu.hm.swa.sh.abc3.mediaservice.rest.auth.AuthserviceOutbound;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.BookTransformer;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.ExceptionTransformer;
import edu.hm.swa.sh.abc3.types.MessageResponseType;
import edu.hm.swa.sh.abc3.types.media.BookType;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * Services for books.
 */
public class BookServiceImpl implements BookService {
    private static final int STATUS_OK = 200;

    private final MediaService mediaService;
    private final BookTransformer bookTransformer;
    private ExceptionTransformer exceptionTransformer;

    private AuthserviceOutbound authserviceOutbound;

    /**
     * Cstr.
     */
    @Inject
    public BookServiceImpl(MediaService mediaService, BookTransformer bookTransformer, ExceptionTransformer
            exceptionTransformer, AuthserviceOutbound authserviceOutbound) {
        this.mediaService = mediaService;
        this.bookTransformer = bookTransformer;
        this.exceptionTransformer = exceptionTransformer;
        this.authserviceOutbound = authserviceOutbound;
    }

    /**
     * Add a new book to application.
     *
     * @param token    user token.
     * @param bookType Book to add.
     * @return Response.
     */
    public Response addBook(final String token, final BookType bookType) {
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
    public Response getBook(final String token, final String isbn) {
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        Object result;

        try {
            this.authserviceOutbound.validateToken(token, "getBook");
            final Book book = mediaService.getBook(isbn);
            result = bookTransformer.toBookType(book);
        } catch (final BaseException exception) {
            result = exceptionTransformer.handleException(exception);
        }

        response.entity(result);
        return response.build();
    }

    /**
     * Returns all stored books.
     *
     * @param token user token.
     * @return Response.
     */
    public Response getBooks(final String token) {
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        Object result;

        try {
            this.authserviceOutbound.validateToken(token, "getBooks");
            final Book[] books = mediaService.getBooks();
            result = bookTransformer.toBookTypeArray(books);
        } catch (final BaseException exception) {
            result = exceptionTransformer.handleException(exception);
        }

        response.entity(result);
        return response.build();
    }

    /**
     * Update a book.
     *
     * @param token    user token.
     * @param isbn     ISBN of book to update.
     * @param bookType new book data.
     * @return Response.
     */
    public Response updateBooks(final String token, final String isbn, final BookType bookType) {
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        MessageResponseType result;
        try {
            this.authserviceOutbound.validateToken(token, "updateBooks");
            final Book book = bookTransformer.toBook(bookType);
            mediaService.updateBook(isbn, book);
            result = createOkMessageResponse();
        } catch (final BaseException exception) {
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
