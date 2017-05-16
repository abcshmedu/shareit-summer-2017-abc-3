package edu.hm.swa.sh.abc3.mediaservice.rest;

import edu.hm.swa.sh.abc3.mediaservice.buisness.MediaService;
import edu.hm.swa.sh.abc3.mediaservice.buisness.MediaServiceBean;
import edu.hm.swa.sh.abc3.mediaservice.common.dto.Disc;
import edu.hm.swa.sh.abc3.mediaservice.common.exception.DirectorIsMissingException;
import edu.hm.swa.sh.abc3.mediaservice.common.exception.IdentifierAlreadyExistsException;
import edu.hm.swa.sh.abc3.mediaservice.common.exception.IdentifierIsImmutableException;
import edu.hm.swa.sh.abc3.mediaservice.common.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.mediaservice.common.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.mediaservice.common.exception.TitleIsMissingException;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.DiscTransformer;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.ExceptionTransformer;
import edu.hm.swa.sh.abc3.mediaservice.rest.types.DiscType;
import edu.hm.swa.sh.abc3.mediaservice.rest.types.MessageResponseType;

import javax.ws.rs.core.Response;

/**
 * Services for discs.
 */
public class DiscService {
    private static final int STATUS_OK = 200;

    private MediaService mediaService;
    private DiscTransformer discTransformer;
    private ExceptionTransformer exceptionTransformer;

    /**
     * Cstr.
     */
    public DiscService() {
        this.mediaService = new MediaServiceBean();
        this.discTransformer = new DiscTransformer();
        this.exceptionTransformer = new ExceptionTransformer();
    }

    /**
     * Returns a single disc.
     *
     * @param barcode Barcode of disc.
     * @return Response.
     */
    public Response getDisc(final String barcode) {
        final Disc disc = mediaService.getDisc(barcode);
        final DiscType result = discTransformer.toDiscType(disc);

        return Response
                .status(Response.Status.OK)
                .entity(result)
                .build();
    }

    /**
     * Returns all saved discs.
     *
     * @return Response.
     */
    public Response getDiscs() {
        final Disc[] discs = mediaService.getDiscs();
        final DiscType[] result = discTransformer.toDiscTypeArray(discs);

        return Response
                .status(Response.Status.OK)
                .entity(result)
                .build();
    }

    /**
     * Add a new Disc.
     *
     * @param discType disc to add.
     * @return Response.
     */
    public Response addDisc(final DiscType discType) {
        final Disc disc = discTransformer.toDisc(discType);
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        MessageResponseType result;
        try {
            mediaService.addDisc(disc);
            result = createOkMessageResponse();
        } catch (final InvalidIdentifierException | DirectorIsMissingException
                | IdentifierAlreadyExistsException | TitleIsMissingException exception) {
            result = exceptionTransformer.handleException(exception);
        }
        response.entity(result);

        return response.build();
    }

    /**
     * Update a disc.
     *
     * @param barcode  Barcode of Disc to update.
     * @param discType new disc data.
     * @return Response.
     */
    public Response updateDisc(final String barcode, final DiscType discType) {
        final Disc disc = discTransformer.toDisc(discType);
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        MessageResponseType result;
        try {
            mediaService.updateDisc(barcode, disc);
            result = createOkMessageResponse();
        } catch (final InvalidIdentifierException | IdentifierIsMissingException
                | IdentifierIsImmutableException exception) {
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
