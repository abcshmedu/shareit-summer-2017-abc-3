package edu.hm.swa.sh.abc3.buisness;

import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.common.exception.AuthorIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierAlreadyExistsException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsImmutableException;
import edu.hm.swa.sh.abc3.common.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.common.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.common.exception.TitleIsMissingException;
import edu.hm.swa.sh.abc3.persistence.PersistenceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class MediaServiceBeanTest {
    private final static String ISBN = "978-3-7657-2781-8";
    private final static String TITLE = "Test Title Book";
    private final static String AUTHOR = "Test Author Book";

    @InjectMocks
    private MediaServiceBean underTest;
    @Mock
    private PersistenceLayer persistenceLayerMock;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetBook() {
        final Book book = new Book(TITLE, AUTHOR, ISBN);
        when(persistenceLayerMock.getBook(anyString())).thenReturn(book);
        final Book result = underTest.getBook(ISBN);
        assertEquals("Should be the same book", book, result);
    }

    @Test
    public void testGetBooks() {
        final Book book = new Book(TITLE, AUTHOR, ISBN);
        final Book[] bookArray = new Book[1];
        bookArray[0] = book;

        when(persistenceLayerMock.getBooks()).thenReturn(bookArray);
        final Book[] result = underTest.getBooks();
        assertTrue("Should be 1 entry", result.length == 1);
        assertEquals("Should be the same book", book, result[0]);
    }

    @Test
    public void testAddBookGoodCase() throws TitleIsMissingException, IdentifierAlreadyExistsException,
            AuthorIsMissingException, InvalidIdentifierException {
        final Book book = new Book(TITLE, AUTHOR, ISBN);
        when(persistenceLayerMock.getBook(anyString())).thenReturn(null);
        underTest.addBook(book);
        verify(persistenceLayerMock).storeBook(book);
    }

    @Test(expected = TitleIsMissingException.class)
    public void testAddBookTitleMissing() throws TitleIsMissingException, IdentifierAlreadyExistsException,
            AuthorIsMissingException, InvalidIdentifierException {
        final Book book = new Book("", AUTHOR, ISBN);
        when(persistenceLayerMock.getBook(anyString())).thenReturn(null);
        underTest.addBook(book);
    }

    @Test(expected = AuthorIsMissingException.class)
    public void testAddBookAuthorMissing() throws TitleIsMissingException, IdentifierAlreadyExistsException,
            AuthorIsMissingException, InvalidIdentifierException {
        final Book book = new Book(TITLE, "", ISBN);
        when(persistenceLayerMock.getBook(anyString())).thenReturn(null);
        underTest.addBook(book);
    }

    @Test(expected = InvalidIdentifierException.class)
    public void testAddBookISBNInvalid() throws TitleIsMissingException, IdentifierAlreadyExistsException,
            AuthorIsMissingException, InvalidIdentifierException {
        final Book book = new Book(TITLE, AUTHOR, "123");
        when(persistenceLayerMock.getBook(anyString())).thenReturn(null);
        underTest.addBook(book);
    }

    @Test(expected = IdentifierAlreadyExistsException.class)
    public void testAddBookISBNAlreadyExists() throws TitleIsMissingException, IdentifierAlreadyExistsException,
            AuthorIsMissingException, InvalidIdentifierException {
        final Book book = new Book(TITLE, AUTHOR, ISBN);
        when(persistenceLayerMock.getBook(anyString())).thenReturn(book);
        underTest.addBook(book);
    }

    @Test
    public void testUpdateBookGoodCase() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException {
        final Book book = new Book(TITLE, AUTHOR, ISBN);
        when(persistenceLayerMock.getBook(ISBN)).thenReturn(book);
        underTest.updateBook(ISBN, book);
        verify(persistenceLayerMock).updateBook(ISBN, book);
    }

    @Test(expected = IdentifierIsMissingException.class)
    public void testUpdateBookISBNMissing() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException {
        final Book book = new Book(TITLE, AUTHOR, ISBN);
        when(persistenceLayerMock.getBook(ISBN)).thenReturn(null);
        underTest.updateBook("", book);
    }

    @Test(expected = InvalidIdentifierException.class)
    public void testUpdateBookBookNotExists() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException {
        final Book book = new Book(TITLE, AUTHOR, ISBN);
        when(persistenceLayerMock.getBook(anyString())).thenReturn(null);
        underTest.updateBook(ISBN, book);
    }
}
