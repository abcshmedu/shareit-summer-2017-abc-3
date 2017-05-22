package edu.hm.swa.sh.abc3.mediaservice.rest;

import edu.hm.swa.sh.abc3.mediaservice.rest.types.BookType;
import edu.hm.swa.sh.abc3.mediaservice.rest.types.DiscType;

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
    private BookService bookService = new BookService();
    private DiscService discService = new DiscService();

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
        return bookService.addBook(bookType);
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
        return bookService.getBook(isbn);
    }

    /**
     * Returns all books.
     *
     * @return Medium-array with all books stored.
     */
    @Path("books")
    @GET
    public Response getBooks() {
        return bookService.getBooks();
    }

    /**
     * Updates information about a book.
     *
     * @param bookType Book data to update.
     * @param isbn     ISBN for book to update.
     * @return when an error occur: {@code MediaServiceResult.ISBN_IS_IMMUTABLE},
     * {@code MediaServiceResult.INVALID_ISBN}, {@code MediaServiceResult.AUTHOR_MISSING},
     * {@code MediaServiceResult.TITLE_MISSING}
     */
    @Path("books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response updateBooks(final BookType bookType, @PathParam("isbn") final String isbn) {
        return bookService.updateBooks(isbn, bookType);
    }

    /**
     * Returns the disc matching the given barcode.
     *
     * @param barcode barcode of disc.
     * @return empty Medium-array if no disc found or the disc which matching the barcode.
     */
    @Path("discs/{barcode}")
    @GET
    public Response getDisc(@PathParam("barcode") final String barcode) {
        return discService.getDisc(barcode);
    }

    /**
     * Returns all stored discs.
     *
     * @return Medium-array with all books stored.
     */
    @Path("discs")
    @GET
    public Response getDiscs() {
        return discService.getDiscs();
    }

    /**
     * Create a new disc.
     *
     * @param discType Disc to be created.
     * @return when a error occur: {@code MediaServiceResult.INVALID_BARCODE},
     * {@code MediaServiceResult.BARCODE_ALREADY_EXISTS}, {@code MediaServiceResult.DIRECTOR_MISSING}
     */
    @Path("discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response addDisc(final DiscType discType) {
        return discService.addDisc(discType);
    }

    /**
     * Updates information about a disc.
     *
     * @param barcode  Barcode of disc to update.
     * @param discType disc data to update.
     * @return when an error occur: {@code MediaServiceResult.BARCODE_IS_IMMUTABLE},
     * {@code MediaServiceResult.INVALID_BARCODE}, {@code MediaServiceResult.DIRECTOR_MISSING}
     */
    @Path("discs/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response updateDisc(@PathParam("barcode") final String barcode, final DiscType discType) {
        return discService.updateDisc(barcode, discType);
    }
}
