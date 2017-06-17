package edu.hm.swa.sh.abc3.mediaservice.rest;

import edu.hm.swa.sh.abc3.types.media.BookType;
import edu.hm.swa.sh.abc3.types.media.DiscType;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
@Path("/media")
@Produces(MediaType.APPLICATION_JSON)
public class RestService {
    private final BookService bookService;
    private final DiscService discService;

    @Inject
    public RestService(BookService bookService, DiscService discService) {
        this.bookService = bookService;
        this.discService = discService;
    }

    /**
     * Create a new book.
     *
     * @param token    Token from http header.
     * @param bookType Book to be created.
     * @return when a error occur: {@code MediaServiceResult.INVALID_ISBN}, {@code MediaServiceResult
     * .ISBN_ALREADY_EXISTS},
     * {@code MediaServiceResult.AUTHOR_MISSING}, {@code MediaServiceResult.TITLE_MISSING}
     */
    @Path("books")
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response addBook(final BookType bookType, @HeaderParam("token") final String token) {
        return bookService.addBook(token, bookType);
    }

    /**
     * Returns the book matching the given ISBN.
     *
     * @param token Token from http header.
     * @param isbn  ISBN of requested book.
     * @return empty Medium-array if no book found or the book which matching the ISBN.
     */
    @Path("books/{isbn}")
    @GET
    public Response getBook(@PathParam("isbn") final String isbn, @HeaderParam("token") final String token) {
        return bookService.getBook(token, isbn);
    }

    /**
     * Returns all books.
     *
     * @param token Token from http header.
     * @return Medium-array with all books stored.
     */
    @Path("books")
    @GET
    public Response getBooks(@HeaderParam("token") final String token) {
        return bookService.getBooks(token);
    }

    /**
     * Updates information about a book.
     *
     * @param token    Token from http header.
     * @param bookType Book data to update.
     * @param isbn     ISBN for book to update.
     * @return when an error occur: {@code MediaServiceResult.ISBN_IS_IMMUTABLE},
     * {@code MediaServiceResult.INVALID_ISBN}, {@code MediaServiceResult.AUTHOR_MISSING},
     * {@code MediaServiceResult.TITLE_MISSING}
     */
    @Path("books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response updateBooks(final BookType bookType, @PathParam("isbn") final String isbn, @HeaderParam("token")
    final String token) {
        return bookService.updateBooks(token, isbn, bookType);
    }

    /**
     * Returns the disc matching the given barcode.
     *
     * @param token   Token from http header.
     * @param barcode barcode of disc.
     * @return empty Medium-array if no disc found or the disc which matching the barcode.
     */
    @Path("discs/{barcode}")
    @GET
    public Response getDisc(@PathParam("barcode") final String barcode, @HeaderParam("token") final String token) {
        return discService.getDisc(token, barcode);
    }

    /**
     * Returns all stored discs.
     *
     * @param token Token from http header.
     * @return Medium-array with all books stored.
     */
    @Path("discs")
    @GET
    public Response getDiscs(@HeaderParam("token") final String token) {
        return discService.getDiscs(token);
    }

    /**
     * Create a new disc.
     *
     * @param token    Token from http header.
     * @param discType Disc to be created.
     * @return when a error occur: {@code MediaServiceResult.INVALID_BARCODE},
     * {@code MediaServiceResult.BARCODE_ALREADY_EXISTS}, {@code MediaServiceResult.DIRECTOR_MISSING}
     */
    @Path("discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response addDisc(final DiscType discType, @HeaderParam("token") final String token) {
        return discService.addDisc(token, discType);
    }

    /**
     * Updates information about a disc.
     *
     * @param token    Token from http header.
     * @param barcode  Barcode of disc to update.
     * @param discType disc data to update.
     * @return when an error occur: {@code MediaServiceResult.BARCODE_IS_IMMUTABLE},
     * {@code MediaServiceResult.INVALID_BARCODE}, {@code MediaServiceResult.DIRECTOR_MISSING}
     */
    @Path("discs/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response updateDisc(@PathParam("barcode") final String barcode, final DiscType discType, @HeaderParam
            ("token") final String token) {
        return discService.updateDisc(token, barcode, discType);
    }
}
