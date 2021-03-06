package edu.hm.swa.sh.abc3.mediaservice.rest;

import edu.hm.swa.sh.abc3.dto.Disc;
import edu.hm.swa.sh.abc3.exception.AuthorIsMissingException;
import edu.hm.swa.sh.abc3.exception.DirectorIsMissingException;
import edu.hm.swa.sh.abc3.exception.IdentifierAlreadyExistsException;
import edu.hm.swa.sh.abc3.exception.IdentifierIsImmutableException;
import edu.hm.swa.sh.abc3.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.exception.TitleIsMissingException;
import edu.hm.swa.sh.abc3.mediaservice.business.MediaService;
import edu.hm.swa.sh.abc3.mediaservice.common.AuthException;
import edu.hm.swa.sh.abc3.mediaservice.rest.auth.AuthserviceOutbound;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.DiscTransformer;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.ExceptionTransformer;
import edu.hm.swa.sh.abc3.types.MessageResponseType;
import edu.hm.swa.sh.abc3.types.media.DiscType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscServiceTest {
    private static final int STATUS_OK = 200;
    private static final String DIRECTOR = "Test Author";
    private static final String DIRECTOR_2 = "Test Author 2";
    private static final String TITLE = "Test Title";
    private static final String TITLE_2 = "Test Title 2";
    private static final String BARCODE = "9783765727818";
    private static final String BARCODE_2 = "9783888293436";
    private static final String EXCEPTION_MESSAGE = "BARCODE is not valid.";
    private static final String EXCEPTION_MESSAGE_IMMUTABLE = "BARCODE is immutable.";
    private static final int FSK = 16;
    private static final int FSK_2 = 18;

    @Mock
    private MediaService mediaService;
    @Mock
    private DiscTransformer discTransformer;
    @Mock
    private AuthserviceOutbound authserviceOutbound;
    @Mock
    private ExceptionTransformer exceptionTransformer;
    @InjectMocks
    private DiscService underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddDiscGoodCase() throws DirectorIsMissingException, IdentifierAlreadyExistsException,
            TitleIsMissingException, InvalidIdentifierException, AuthException {
        final DiscType discType = new DiscType();
        discType.setBarcode(BARCODE);
        discType.setDirector(DIRECTOR);
        discType.setTitle(TITLE);
        discType.setFsk(FSK);

        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);

        when(discTransformer.toDisc(discType)).thenReturn(disc);
        final Response result = underTest.addDisc("", discType);
        verify(authserviceOutbound).validateToken(anyString(), anyString());
        verify(mediaService).addDisc(disc);

        final Response expected = createOkResponse().build();

        equalsResponses(expected, result);
    }

    @Test
    public void testAddDiscExceptionMapping() throws DirectorIsMissingException, IdentifierAlreadyExistsException,
            TitleIsMissingException, InvalidIdentifierException, AuthException {
        final DiscType discType = new DiscType();
        discType.setBarcode("123");
        discType.setDirector(DIRECTOR);
        discType.setTitle(TITLE);
        discType.setFsk(FSK);

        final Disc disc = new Disc(TITLE, "123", DIRECTOR, FSK);

        final InvalidIdentifierException exception = new InvalidIdentifierException(EXCEPTION_MESSAGE);

        final MessageResponseType exceptionResponseType = new MessageResponseType();
        exceptionResponseType.setCode(exception.getErrorCode());
        exceptionResponseType.setMessage(EXCEPTION_MESSAGE);

        when(discTransformer.toDisc(discType)).thenReturn(disc);
        when(exceptionTransformer.handleException(exception)).thenReturn(exceptionResponseType);
        doThrow(exception).when(mediaService).addDisc(disc);
        final Response result = underTest.addDisc("", discType);
        verify(authserviceOutbound).validateToken(anyString(), anyString());
        verify(mediaService).addDisc(disc);

        final Response expected = createBaseResponse().entity(exceptionResponseType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testGetDiscGoodCase() throws AuthException {
        final DiscType discType = new DiscType();
        discType.setBarcode(BARCODE);
        discType.setDirector(DIRECTOR);
        discType.setTitle(TITLE);
        discType.setFsk(FSK);

        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);

        when(discTransformer.toDiscType(disc)).thenReturn(discType);
        when(mediaService.getDisc(BARCODE)).thenReturn(disc);
        final Response result = underTest.getDisc("", BARCODE);
        verify(authserviceOutbound).validateToken(anyString(), anyString());

        final Response expected = createBaseResponse().entity(discType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testGetDiscEmptyResponse() throws AuthException {
        final DiscType discType = new DiscType();

        when(mediaService.getDisc(BARCODE)).thenReturn(null);
        when(discTransformer.toDiscType(null)).thenReturn(discType);

        final Response result = underTest.getDisc("", BARCODE);
        verify(authserviceOutbound).validateToken(anyString(), anyString());

        final Response expected = createBaseResponse().entity(discType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testGetDiscsGoodCase() throws AuthException {
        final DiscType discType1 = new DiscType();
        discType1.setBarcode(BARCODE);
        discType1.setDirector(DIRECTOR);
        discType1.setTitle(TITLE);
        discType1.setFsk(FSK);

        final DiscType discType2 = new DiscType();
        discType2.setBarcode(BARCODE_2);
        discType2.setDirector(DIRECTOR_2);
        discType2.setTitle(TITLE_2);
        discType2.setFsk(FSK_2);

        final Disc disc1 = new Disc(TITLE, BARCODE, DIRECTOR, FSK);
        final Disc disc2 = new Disc(TITLE_2, BARCODE_2, DIRECTOR_2, FSK_2);

        final Disc[] discArray = new Disc[2];
        discArray[0] = disc1;
        discArray[1] = disc2;

        final DiscType[] discArrayType = new DiscType[2];
        discArrayType[0] = discType1;
        discArrayType[1] = discType2;

        when(mediaService.getDiscs()).thenReturn(discArray);
        when(discTransformer.toDiscTypeArray(discArray)).thenReturn(discArrayType);

        final Response result = underTest.getDiscs("");
        verify(authserviceOutbound).validateToken(anyString(), anyString());

        final Response expected = createBaseResponse().entity(discArrayType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testGetDiscsEmptyResult() throws AuthException {
        final DiscType[] discTypeArray = new DiscType[0];
        final Disc[] discArray = new Disc[0];

        when(mediaService.getDiscs()).thenReturn(discArray);
        when(discTransformer.toDiscTypeArray(discArray)).thenReturn(discTypeArray);

        final Response result = underTest.getDiscs("");
        verify(authserviceOutbound).validateToken(anyString(), anyString());

        final Response expected = createBaseResponse().entity(discTypeArray).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testUpdateDiscsGoodCase() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException, AuthException {
        final DiscType discType = new DiscType();
        discType.setBarcode(BARCODE);
        discType.setDirector(DIRECTOR);
        discType.setTitle(TITLE);
        discType.setFsk(FSK);

        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);

        when(discTransformer.toDisc(discType)).thenReturn(disc);
        final Response result = underTest.updateDisc("", BARCODE, discType);
        verify(mediaService).updateDisc(BARCODE, disc);
        verify(authserviceOutbound).validateToken(anyString(), anyString());

        final Response expected = createOkResponse().build();

        equalsResponses(expected, result);
    }

    @Test
    public void testUpdateBooksChangeBarcode() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException, AuthException {
        final DiscType discType = new DiscType();
        discType.setBarcode(BARCODE);
        discType.setDirector(DIRECTOR);
        discType.setTitle(TITLE);
        discType.setFsk(FSK);

        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);

        final IdentifierIsImmutableException exception = new IdentifierIsImmutableException
                (EXCEPTION_MESSAGE_IMMUTABLE);

        final MessageResponseType exceptionResponseType = new MessageResponseType();
        exceptionResponseType.setCode(exception.getErrorCode());
        exceptionResponseType.setMessage(EXCEPTION_MESSAGE_IMMUTABLE);

        when(discTransformer.toDisc(discType)).thenReturn(disc);
        when(exceptionTransformer.handleException(exception)).thenReturn(exceptionResponseType);
        doThrow(exception).when(mediaService).updateDisc(BARCODE, disc);
        final Response result = underTest.updateDisc("", BARCODE, discType);
        verify(authserviceOutbound).validateToken(anyString(), anyString());
        verify(mediaService).updateDisc(BARCODE, disc);
        final Response expected = createBaseResponse().entity(exceptionResponseType).build();

        equalsResponses(expected, result);
    }


    @Test
    public void testAddDiscUnauthorized() throws AuthException, TitleIsMissingException,
            IdentifierAlreadyExistsException, AuthorIsMissingException, InvalidIdentifierException,
            DirectorIsMissingException {
        final DiscType discType = new DiscType();
        discType.setBarcode(BARCODE);
        discType.setDirector(DIRECTOR);
        discType.setTitle(TITLE);
        discType.setFsk(FSK);

        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);

        final AuthException exception = new AuthException(EXCEPTION_MESSAGE, -999);

        final MessageResponseType exceptionResponseType = new MessageResponseType();
        exceptionResponseType.setCode(exception.getErrorCode());
        exceptionResponseType.setMessage(EXCEPTION_MESSAGE);

        when(discTransformer.toDisc(discType)).thenReturn(disc);
        doThrow(exception).when(authserviceOutbound).validateToken(anyString(), anyString());
        when(exceptionTransformer.handleException(exception)).thenReturn(exceptionResponseType);

        final Response result = underTest.addDisc("token", discType);
        verify(mediaService, never()).addDisc(any());
        final Response expected = createBaseResponse().entity(exceptionResponseType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testGetDiscUnauthorized() throws AuthException {
        final AuthException exception = new AuthException(EXCEPTION_MESSAGE, -999);

        final MessageResponseType exceptionResponseType = new MessageResponseType();
        exceptionResponseType.setCode(exception.getErrorCode());
        exceptionResponseType.setMessage(EXCEPTION_MESSAGE);

        doThrow(exception).when(authserviceOutbound).validateToken(anyString(), anyString());
        when(exceptionTransformer.handleException(exception)).thenReturn(exceptionResponseType);

        final Response result = underTest.getDisc("token", BARCODE);
        verify(mediaService, never()).getDisc(any());
        final Response expected = createBaseResponse().entity(exceptionResponseType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testGetDiscsUnauthorized() throws AuthException {
        final AuthException exception = new AuthException(EXCEPTION_MESSAGE, -999);

        final MessageResponseType exceptionResponseType = new MessageResponseType();
        exceptionResponseType.setCode(exception.getErrorCode());
        exceptionResponseType.setMessage(EXCEPTION_MESSAGE);

        doThrow(exception).when(authserviceOutbound).validateToken(anyString(), anyString());
        when(exceptionTransformer.handleException(exception)).thenReturn(exceptionResponseType);

        final Response result = underTest.getDiscs("token");
        verify(mediaService, never()).getDiscs();
        final Response expected = createBaseResponse().entity(exceptionResponseType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testUpdateBookUnauthorized() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException, AuthException {
        final DiscType discType = new DiscType();
        discType.setBarcode(BARCODE);
        discType.setDirector(DIRECTOR);
        discType.setTitle(TITLE);
        discType.setFsk(FSK);

        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);

        final AuthException exception = new AuthException(EXCEPTION_MESSAGE, -999);

        final MessageResponseType exceptionResponseType = new MessageResponseType();
        exceptionResponseType.setCode(exception.getErrorCode());
        exceptionResponseType.setMessage(EXCEPTION_MESSAGE);

        when(discTransformer.toDisc(discType)).thenReturn(disc);
        doThrow(exception).when(authserviceOutbound).validateToken(anyString(), anyString());
        when(exceptionTransformer.handleException(exception)).thenReturn(exceptionResponseType);

        final Response result = underTest.updateDisc("token", BARCODE, discType);
        verify(mediaService, never()).updateDisc(any(), any());
        final Response expected = createBaseResponse().entity(exceptionResponseType).build();

        equalsResponses(expected, result);
    }

    private void equalsResponses(final Response expected, final Response acutal) {
        assertEquals("Should be the same status", expected.getStatus(), acutal.getStatus());
        assertEquals("Should have the same 'hasEntity' Tag", expected.hasEntity(), acutal.hasEntity());
        assertEquals("Should be the same entity", expected.getEntity(), acutal.getEntity());
    }

    private Response.ResponseBuilder createOkResponse() {
        return createBaseResponse().entity(new MessageResponseType().setCode(200).setMessage("OK"));
    }

    private Response.ResponseBuilder createBaseResponse() {
        return Response.status(STATUS_OK);
    }
}
