package edu.hm.swa.sh.abc3.rest;

import edu.hm.swa.sh.abc3.buisness.MediaService;
import edu.hm.swa.sh.abc3.buisness.MediaServiceBean;
import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.dto.Disc;
import edu.hm.swa.sh.abc3.common.exception.AuthorIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierAlreadyExistsException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsImmutableException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.common.exception.TitleIsMissingException;
import edu.hm.swa.sh.abc3.rest.transformer.BookTransformer;
import edu.hm.swa.sh.abc3.rest.transformer.DiscTransformer;
import edu.hm.swa.sh.abc3.rest.transformer.ExceptionTransformer;
import edu.hm.swa.sh.abc3.rest.types.BookType;
import edu.hm.swa.sh.abc3.rest.types.ExceptionResponseType;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Service outbound implementation.
 */
@Path("media")
@Produces(MediaType.APPLICATION_JSON)
public class RestService {
    private static final int STATUS_OK = 200;
    @EJB
    private MediaService mediaService;
    @EJB
    private BookTransformer bookTransformer;
    @EJB
    private ExceptionTransformer exceptionTransformer;
    @EJB
    private DiscTransformer discTransformer;

    /**
     * Create a new book.
     *
     * @param bookType Book to be created.
     * @return when a error occur: {@code MediaServiceResult.INVALID_ISBN}, {@code MediaServiceResult.ISBN_ALREADY_EXISTS},
     * {@code MediaServiceResult.AUTHOR_MISSING}, {@code MediaServiceResult.TITLE_MISSING}
     */
    @Path("books")
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response addBook(final BookType bookType) {
        final Book book = bookTransformer.toBook(bookType);
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        try {
            mediaService.addBook(book);
        } catch (final IdentifierAlreadyExistsException | TitleIsMissingException | AuthorIsMissingException
                | InvalidIdentifierException exception) {
            final ExceptionResponseType result =
                    exceptionTransformer.handleException(exception);
            response.entity(result);
        }
        return response.build();
    }

    /**
     * Returns the book matching the given ISBN.
     *
     * @param isbn ISBN of requested book.
     * @return empty Medium-array if no book found or the book which matching the ISBN.
     */
    @Path("books/{isbn}")
    @GET
    public Response getBook(@PathParam("isbn") final String isbn) {
        final Book book = mediaService.getBook(isbn);
        final BookType bookType = bookTransformer.toBookType(book);
        return Response.status(STATUS_OK).entity(bookType).build();
    }

    /**
     * Returns all books.
     *
     * @return Medium-array with all books stored.
     */
    @Path("books")
    @GET
    public Response getBooks() {
        final Book[] books = mediaService.getBooks();
        final BookType[] result = bookTransformer.toBookTypeArray(books);

        return Response
                .status(Response.Status.OK)
                .entity(result)
                .build();
    }

    /**
     * Updates information about a book.
     *
     * @param bookType Book data to update.
     * @return when an error occur: {@code MediaServiceResult.ISBN_IS_IMMUTABLE},
     * {@code MediaServiceResult.INVALID_ISBN}, {@code MediaServiceResult.AUTHOR_MISSING},
     * {@code MediaServiceResult.TITLE_MISSING}
     */
    @Path("books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response updateBooks(final BookType bookType, @PathParam("isbn") final String isbn) {
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        try {
            final Book book = bookTransformer.toBook(bookType);
            mediaService.updateBook(isbn, book);
        } catch (final IdentifierIsMissingException | InvalidIdentifierException
                | IdentifierIsImmutableException exception) {
            response.entity(exceptionTransformer.handleException(exception));
        }
        return response.build();
    }

    /**
     * Returns the disc matching the given barcode.
     * @param barcode barcode of disc.
     * @return empty Medium-array if no disc found or the disc which matching the barcode.
     */
    @Path("discs/{barcode}")
    @GET
    public Response getDisc(@PathParam("barcode") final String barcode) {
        final Disc disc = mediaService.getDisc(barcode);
        final JSONObject result = discTransformer.toJSONObject(disc);

        return Response
                .status(Response.Status.OK)
                .entity(result.toString())
                .build();
    }

    /**
     * Returns all stored discs.
     *
     * @return Medium-array with all books stored.
     */
    @Path("discs")
    @GET
    public Response getDiscs() {
        final Disc[] disc = mediaService.getDiscs();
        final JSONObject result = discTransformer.toJSONObject(disc);

        return Response
                .status(Response.Status.OK)
                .entity(result.toString())
                .build();
    }

    /**
     * Create a new disc.
     *
     * @param disc Disc to be created.
     * @return when a error occur: {@code MediaServiceResult.INVALID_BARCODE},
     * {@code MediaServiceResult.BARCODE_ALREADY_EXISTS}, {@code MediaServiceResult.DIRECTOR_MISSING}
     */
    @Path("discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response addDisc(final Disc disc) {
//        final JSONObject result = new JSONObject();
//
//        try {
//            mediaService.addDisc(disc);
//        } catch (final InvalidIdentifierException | DirectorIsMissingException
//                | IdentifierAlreadyExistsException exception) {
//            exceptionTransformer.handleException(result, exception);
//        }

        return Response
                .status(Response.Status.OK)
//                .entity(result.toString())
                .build();
    }

    /**
     * Updates information about a disc.
     *
     * @param disc disc data to update.
     * @return when an error occur: {@code MediaServiceResult.BARCODE_IS_IMMUTABLE},
     * {@code MediaServiceResult.INVALID_BARCODE}, {@code MediaServiceResult.DIRECTOR_MISSING}
     */
    @Path("discs/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response updateDisc(final Disc disc) {
//        final JSONObject result = new JSONObject();
//
//        try {
//            mediaService.updateDisc(disc);
//        } catch (final InvalidIdentifierException | IdentifierIsMissingException exception) {
//            exceptionTransformer.handleException(result, exception);
//        }

        return Response
                .status(Response.Status.OK)
//                .entity(result.toString())
                .build();
    }
}
