package edu.hm.swa.sh.abc3.mediaservice.rest;

import edu.hm.swa.sh.abc3.dto.Disc;
import edu.hm.swa.sh.abc3.exception.BaseException;
import edu.hm.swa.sh.abc3.mediaservice.business.MediaService;
import edu.hm.swa.sh.abc3.mediaservice.rest.auth.AuthserviceOutbound;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.DiscTransformer;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.ExceptionTransformer;
import edu.hm.swa.sh.abc3.types.MessageResponseType;
import edu.hm.swa.sh.abc3.types.media.DiscType;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * Services for discs.
 */
public class DiscService {
    private static final int STATUS_OK = 200;
    @Inject
    private MediaService mediaService;
    @Inject
    private DiscTransformer discTransformer;
    @Inject
    private ExceptionTransformer exceptionTransformer;
    @Inject
    private AuthserviceOutbound authserviceOutbound;

    /**
     * Returns a single disc.
     *
     * @param token   user token.
     * @param barcode Barcode of disc.
     * @return Response.
     */
    public Response getDisc(final String token, final String barcode) {
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        Object result;

        try {
            this.authserviceOutbound.validateToken(token, "getDisc");
            final Disc disc = mediaService.getDisc(barcode);
            result = discTransformer.toDiscType(disc);
        } catch (final BaseException exception) {
            result = exceptionTransformer.handleException(exception);
        }

        response.entity(result);
        return response.build();
    }

    /**
     * Returns all saved discs.
     *
     * @param token user token.
     * @return Response.
     */
    public Response getDiscs(final String token) {
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        Object result;

        try {
            this.authserviceOutbound.validateToken(token, "getDiscs");
            final Disc[] discs = mediaService.getDiscs();
            result = discTransformer.toDiscTypeArray(discs);
        } catch (final BaseException exception) {
            result = exceptionTransformer.handleException(exception);
        }

        response.entity(result);
        return response.build();
    }

    /**
     * Add a new Disc.
     *
     * @param token    user token.
     * @param discType disc to add.
     * @return Response.
     */
    public Response addDisc(final String token, final DiscType discType) {
        final Disc disc = discTransformer.toDisc(discType);
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        MessageResponseType result;
        try {
            this.authserviceOutbound.validateToken(token, "addDisc");
            mediaService.addDisc(disc);
            result = createOkMessageResponse();
        } catch (final BaseException exception) {
            result = exceptionTransformer.handleException(exception);
        }
        response.entity(result);

        return response.build();
    }

    /**
     * Update a disc.
     *
     * @param token    user token.
     * @param barcode  Barcode of Disc to update.
     * @param discType new disc data.
     * @return Response.
     */
    public Response updateDisc(final String token, final String barcode, final DiscType discType) {
        final Disc disc = discTransformer.toDisc(discType);
        final Response.ResponseBuilder response = Response.status(STATUS_OK);
        MessageResponseType result;
        try {
            this.authserviceOutbound.validateToken(token, "updateDisc");
            mediaService.updateDisc(barcode, disc);
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
