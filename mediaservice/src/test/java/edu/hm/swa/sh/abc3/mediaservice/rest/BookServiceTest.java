package edu.hm.swa.sh.abc3.mediaservice.rest;

import edu.hm.swa.sh.abc3.dto.Book;
import edu.hm.swa.sh.abc3.exception.AuthorIsMissingException;
import edu.hm.swa.sh.abc3.exception.IdentifierAlreadyExistsException;
import edu.hm.swa.sh.abc3.exception.IdentifierIsImmutableException;
import edu.hm.swa.sh.abc3.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.exception.TitleIsMissingException;
import edu.hm.swa.sh.abc3.mediaservice.business.MediaService;
import edu.hm.swa.sh.abc3.mediaservice.common.AuthException;
import edu.hm.swa.sh.abc3.mediaservice.rest.auth.AuthserviceOutbound;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.BookTransformer;
import edu.hm.swa.sh.abc3.mediaservice.rest.transformer.ExceptionTransformer;
import edu.hm.swa.sh.abc3.types.MessageResponseType;
import edu.hm.swa.sh.abc3.types.media.BookType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    private static final int STATUS_OK = 200;
    private static final String AUTHOR = "Test Author";
    private static final String AUTHOR_2 = "Test Author 2";
    private static final String TITLE = "Test Title";
    private static final String TITLE_2 = "Test Title 2";
    private static final String ISBN = "978-3-7657-2781-8";
    private static final String ISBN_2 = "978-3-8882-9343-6";
    private static final String EXCEPTION_MESSAGE = "ISBN number is not valid.";
    private static final String EXCEPTION_MESSAGE_IMMUTABLE = "ISBN number is immutable.";
    private static final String TOKEN = "token";

    @Mock
    private MediaService mediaService;
    @Mock
    private BookTransformer bookTransformer;
    @Mock
    private AuthserviceOutbound authserviceOutbound;
    @Mock
    private ExceptionTransformer exceptionTransformer;
    @InjectMocks
    private BookService underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddBookGoodCase() throws TitleIsMissingException, IdentifierAlreadyExistsException,
            AuthorIsMissingException, InvalidIdentifierException, AuthException {
        final BookType bookType = new BookType();
        bookType.setIsbn(ISBN);
        bookType.setAuthor(AUTHOR);
        bookType.setTitle(TITLE);

        final Book book = new Book(TITLE, AUTHOR, ISBN);

        when(bookTransformer.toBook(bookType)).thenReturn(book);
        final Response result = underTest.addBook(TOKEN, bookType);
        verify(authserviceOutbound).validateToken(anyString(), anyString());
        verify(mediaService).addBook(book);

        final Response expected = createOkResponse().build();

        equalsResponses(expected, result);
    }

    @Test
    public void testAddBookExceptionMapping() throws TitleIsMissingException, IdentifierAlreadyExistsException,
            AuthorIsMissingException, InvalidIdentifierException, AuthException {
        final BookType bookType = new BookType();
        bookType.setIsbn("123");
        bookType.setAuthor(AUTHOR);
        bookType.setTitle(TITLE);

        final Book book = new Book(TITLE, AUTHOR, "123");

        final InvalidIdentifierException exception = new InvalidIdentifierException(EXCEPTION_MESSAGE);

        final MessageResponseType exceptionResponseType = new MessageResponseType();
        exceptionResponseType.setCode(exception.getErrorCode());
        exceptionResponseType.setMessage(EXCEPTION_MESSAGE);

        when(bookTransformer.toBook(bookType)).thenReturn(book);
        doThrow(exception).when(mediaService).addBook(book);
        when(exceptionTransformer.handleException(exception)).thenReturn(exceptionResponseType);

        final Response result = underTest.addBook(TOKEN, bookType);
        verify(authserviceOutbound).validateToken(anyString(), anyString());

        final Response expected = createBaseResponse().entity(exceptionResponseType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testGetBookGoodCase() throws AuthException {
        final BookType bookType = new BookType();
        bookType.setIsbn(ISBN);
        bookType.setAuthor(AUTHOR);
        bookType.setTitle(TITLE);

        final Book book = new Book(TITLE, AUTHOR, ISBN);

        when(mediaService.getBook(ISBN)).thenReturn(book);
        when(bookTransformer.toBookType(book)).thenReturn(bookType);

        final Response result = underTest.getBook(TOKEN, ISBN);
        verify(authserviceOutbound).validateToken(anyString(), anyString());

        final Response expected = createBaseResponse().entity(bookType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testGetBookEmptyResponse() throws AuthException {
        final BookType bookType = new BookType();

        when(mediaService.getBook(ISBN)).thenReturn(null);
        when(bookTransformer.toBookType(null)).thenReturn(bookType);

        final Response result = underTest.getBook(TOKEN, ISBN);
        verify(authserviceOutbound).validateToken(anyString(), anyString());

        final Response expected = createBaseResponse().entity(bookType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testGetBooksGoodCase() throws AuthException {
        final BookType bookType1 = new BookType();
        bookType1.setIsbn(ISBN);
        bookType1.setAuthor(AUTHOR);
        bookType1.setTitle(TITLE);

        final BookType bookType2 = new BookType();
        bookType2.setIsbn(ISBN_2);
        bookType2.setAuthor(AUTHOR_2);
        bookType2.setTitle(TITLE_2);

        final Book book1 = new Book(TITLE, AUTHOR, ISBN);
        final Book book2 = new Book(TITLE_2, AUTHOR_2, ISBN_2);

        final Book[] bookArray = new Book[2];
        bookArray[0] = book1;
        bookArray[1] = book2;

        final BookType[] bookTypeArray = new BookType[2];
        bookTypeArray[0] = bookType1;
        bookTypeArray[1] = bookType2;

        when(mediaService.getBooks()).thenReturn(bookArray);
        when(bookTransformer.toBookTypeArray(bookArray)).thenReturn(bookTypeArray);

        final Response result = underTest.getBooks(TOKEN);
        verify(authserviceOutbound).validateToken(anyString(), anyString());

        final Response expected = createBaseResponse().entity(bookTypeArray).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testGetBooksEmptyResult() throws AuthException {
        final BookType[] bookTypeArray = new BookType[0];
        final Book[] bookArray = new Book[0];

        when(mediaService.getBooks()).thenReturn(bookArray);
        when(bookTransformer.toBookTypeArray(bookArray)).thenReturn(bookTypeArray);

        final Response result = underTest.getBooks(TOKEN);
        verify(authserviceOutbound).validateToken(anyString(), anyString());

        final Response expected = createBaseResponse().entity(bookTypeArray).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testUpdateBooksGoodCase() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException, AuthException {
        final BookType bookType = new BookType();
        bookType.setIsbn(ISBN);
        bookType.setAuthor(AUTHOR);
        bookType.setTitle(TITLE);

        final Book book = new Book(TITLE, AUTHOR, ISBN);

        when(bookTransformer.toBook(bookType)).thenReturn(book);
        final Response result = underTest.updateBooks(TOKEN, ISBN, bookType);
        verify(authserviceOutbound).validateToken(anyString(), anyString());
        verify(mediaService).updateBook(ISBN, book);

        final Response expected = createOkResponse().build();

        equalsResponses(expected, result);
    }

    @Test
    public void testUpdateBooksChangeISBN() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException, AuthException {
        final BookType bookType = new BookType();
        bookType.setIsbn(ISBN_2);
        bookType.setAuthor(AUTHOR);
        bookType.setTitle(TITLE);

        final Book book = new Book(TITLE, AUTHOR, ISBN_2);

        final IdentifierIsImmutableException exception = new IdentifierIsImmutableException
                (EXCEPTION_MESSAGE_IMMUTABLE);

        final MessageResponseType exceptionResponseType = new MessageResponseType();
        exceptionResponseType.setCode(exception.getErrorCode());
        exceptionResponseType.setMessage(EXCEPTION_MESSAGE_IMMUTABLE);

        when(bookTransformer.toBook(bookType)).thenReturn(book);
        when(exceptionTransformer.handleException(exception)).thenReturn(exceptionResponseType);
        doThrow(exception).when(mediaService).updateBook(ISBN, book);
        final Response result = underTest.updateBooks(TOKEN, ISBN, bookType);
        verify(authserviceOutbound).validateToken(anyString(), anyString());
        verify(mediaService).updateBook(ISBN, book);
        final Response expected = createBaseResponse().entity(exceptionResponseType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testAddBookUnauthorized() throws AuthException, TitleIsMissingException,
            IdentifierAlreadyExistsException, AuthorIsMissingException, InvalidIdentifierException {
        final BookType bookType = new BookType();
        bookType.setIsbn("123");
        bookType.setAuthor(AUTHOR);
        bookType.setTitle(TITLE);

        final Book book = new Book(TITLE, AUTHOR, "123");

        final AuthException exception = new AuthException(EXCEPTION_MESSAGE, -999);

        final MessageResponseType exceptionResponseType = new MessageResponseType();
        exceptionResponseType.setCode(exception.getErrorCode());
        exceptionResponseType.setMessage(EXCEPTION_MESSAGE);

        when(bookTransformer.toBook(bookType)).thenReturn(book);
        doThrow(exception).when(authserviceOutbound).validateToken(anyString(), anyString());
        when(exceptionTransformer.handleException(exception)).thenReturn(exceptionResponseType);

        final Response result = underTest.addBook(TOKEN, bookType);
        verify(mediaService, never()).addBook(book);
        final Response expected = createBaseResponse().entity(exceptionResponseType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testGetBookUnauthorized() throws AuthException {
        final AuthException exception = new AuthException(EXCEPTION_MESSAGE, -999);

        final MessageResponseType exceptionResponseType = new MessageResponseType();
        exceptionResponseType.setCode(exception.getErrorCode());
        exceptionResponseType.setMessage(EXCEPTION_MESSAGE);

        doThrow(exception).when(authserviceOutbound).validateToken(anyString(), anyString());
        when(exceptionTransformer.handleException(exception)).thenReturn(exceptionResponseType);

        final Response result = underTest.getBook(TOKEN, ISBN);

        verify(mediaService, never()).getBook(anyString());
        final Response expected = createBaseResponse().entity(exceptionResponseType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testGetBooksUnauthorized() throws AuthException {
        final AuthException exception = new AuthException(EXCEPTION_MESSAGE, -999);

        final MessageResponseType exceptionResponseType = new MessageResponseType();
        exceptionResponseType.setCode(exception.getErrorCode());
        exceptionResponseType.setMessage(EXCEPTION_MESSAGE);

        doThrow(exception).when(authserviceOutbound).validateToken(anyString(), anyString());
        when(exceptionTransformer.handleException(exception)).thenReturn(exceptionResponseType);

        final Response result = underTest.getBooks(TOKEN);

        verify(mediaService, never()).getBooks();
        final Response expected = createBaseResponse().entity(exceptionResponseType).build();

        equalsResponses(expected, result);
    }

    @Test
    public void testUpdateBookUnauthorized() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException, AuthException {
        final BookType bookType = new BookType();
        bookType.setIsbn(ISBN);
        bookType.setAuthor(AUTHOR);
        bookType.setTitle(TITLE);

        final Book book = new Book(TITLE, AUTHOR, ISBN);

        final AuthException exception = new AuthException(EXCEPTION_MESSAGE, -999);

        final MessageResponseType exceptionResponseType = new MessageResponseType();
        exceptionResponseType.setCode(exception.getErrorCode());
        exceptionResponseType.setMessage(EXCEPTION_MESSAGE);

        doThrow(exception).when(authserviceOutbound).validateToken(anyString(), anyString());
        when(exceptionTransformer.handleException(exception)).thenReturn(exceptionResponseType);

        when(bookTransformer.toBook(bookType)).thenReturn(book);

        final Response result = underTest.updateBooks(TOKEN, ISBN, bookType);
        verify(mediaService, never()).updateBook(ISBN, book);

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
