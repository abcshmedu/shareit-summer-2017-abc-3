package edu.hm.swa.sh.abc3.service.rest;

import edu.hm.swa.sh.abc3.common.Book;
import edu.hm.swa.sh.abc3.common.Disc;
import edu.hm.swa.sh.abc3.common.Medium;
import edu.hm.swa.sh.abc3.service.MediaService;
import edu.hm.swa.sh.abc3.service.MediaServiceResult;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * REST Service outbound implementation.
 */
@Path("media")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestService {

    @Inject
    private MediaService mediaService;

    /**
     * Create a new book.
     *
     * @param book Book to be created.
     * @return when a error occur: {@code MediaServiceResult.INVALID_ISBN}, {@code MediaServiceResult.ISBN_ALREADY_EXISTS},
     * {@code MediaServiceResult.AUTHOR_MISSING}, {@code MediaServiceResult.TITLE_MISSING}
     */
    @Path("books")
    @POST
    public MediaServiceResult addBook(final Book book) {
        return null;
    }

    /**
     * Returns the book matching the given ISBN.
     *
     * @return empty Medium-array if no book found or the book which matching the ISBN.
     */
    @Path("books/{isbn}")
    @GET
    public Medium[] getBook() {
        return null;
    }

    /**
     * Returns all books.
     *
     * @return Medium-array with all books stored.
     */
    @Path("books")
    @GET
    public Medium[] getBooks() {
        return null;
    }

    /**
     * Updates information about a book.
     *
     * @param book Book data to update.
     * @return when an error occur: {@code MediaServiceResult.ISBN_IS_IMMUTABLE},
     * {@code MediaServiceResult.INVALID_ISBN}, {@code MediaServiceResult.AUTHOR_MISSING},
     * {@code MediaServiceResult.TITLE_MISSING}
     */
    @Path("books/{isbn}")
    @PUT
    public MediaServiceResult updateBooks(final Book book) {
        return null;
    }

    /**
     * Returns the disk matching the given barcode.
     *
     * @return empty Medium-array if no disc found or the disc which matching the barcode.
     */
    @Path("discs/{barcode}")
    @POST
    public Medium[] getDisc() {
        return null;
    }

    /**
     * Returns all stored discs.
     *
     * @return Medium-array with all books stored.
     */
    @Path("discs")
    @POST
    public Medium[] getDiscs() {
        return new Medium[0];
    }

    /**
     * Create a new disc.
     *
     * @param disc Disc to be created.
     * @return when a error occur: {@code MediaServiceResult.INVALID_BARCODE},
     * {@code MediaServiceResult.BARCODE_ALREADY_EXISTS}, {@code MediaServiceResult.DIRECTOR_MISSING}
     */
    @Path("discs")
    @POST
    public MediaServiceResult addDisc(final Disc disc) {
        return null;
    }

    /**
     * Updates information about a disc.
     *
     * @param disc disc data to update.
     * @return when an error occur: {@code MediaServiceResult.BARCODE_IS_IMMUTABLE},
     * {@code MediaServiceResult.INVALID_BARCODE}, {@code MediaServiceResult.DIRECTOR_MISSING}
     */
    @Path("discs/{barcode}")
    @PUT
    public MediaServiceResult updateDisc(final Disc disc) {
        return null;
    }
}
