package edu.hm.swa.sh.abc3.service.rest;

import edu.hm.swa.sh.abc3.common.Book;
import edu.hm.swa.sh.abc3.common.Disc;
import edu.hm.swa.sh.abc3.common.Medium;
import edu.hm.swa.sh.abc3.service.MediaService;
import edu.hm.swa.sh.abc3.service.MediaServiceResult;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

/**
 * Created by Sebastian on 21.04.2017.
 */
@Path("media")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestService {

    @Inject
    private MediaService mediaService;

    @Path("books")
    @POST
    public MediaServiceResult addBook(final Book book) {
        return null;
    }

    @Path("books/{isbn}")
    @GET
    public Medium[] getBook() {
        return null;
    }

    @Path("books")
    @GET
    public Medium[] getBooks() {
        return null;
    }

    @Path("books/{isbn}")
    @PUT
    public MediaServiceResult updateBooks(final Book book) {
        return null;
    }

    @Path("discs/{barcode}")
    @POST
    public Medium[] getDisc() {
        return null;
    }

    @Path("discs")
    @POST
    public Medium[] getDiscs() {
        return new Medium[0];
    }

    @Path("discs")
    @POST
    public MediaServiceResult addDisc(final Disc disc) {
        return null;
    }

    @Path("discs/{barcode}")
    @PUT
    public MediaServiceResult updateDisc(final Disc disc) {
        return null;
    }
}
