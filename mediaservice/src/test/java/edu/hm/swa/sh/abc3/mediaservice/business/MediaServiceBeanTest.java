package edu.hm.swa.sh.abc3.mediaservice.business;

import edu.hm.swa.sh.abc3.dto.Book;
import edu.hm.swa.sh.abc3.dto.Disc;
import edu.hm.swa.sh.abc3.exception.AuthorIsMissingException;
import edu.hm.swa.sh.abc3.exception.DirectorIsMissingException;
import edu.hm.swa.sh.abc3.exception.IdentifierAlreadyExistsException;
import edu.hm.swa.sh.abc3.exception.IdentifierIsImmutableException;
import edu.hm.swa.sh.abc3.exception.IdentifierIsMissingException;
import edu.hm.swa.sh.abc3.exception.InvalidIdentifierException;
import edu.hm.swa.sh.abc3.exception.TitleIsMissingException;
import edu.hm.swa.sh.abc3.mediaservice.business.validate.IdentifierValidator;
import edu.hm.swa.sh.abc3.mediaservice.persistence.PersistenceLayer;
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
    private final static String CLEANED_ISBN = "9783765727818";
    private final static String TITLE = "Test Title Book";
    private final static String AUTHOR = "Test Author Book";
    private static final String BARCODE = "9783765727818";
    private static final String DIRECTOR = "Test Director Disc";
    private static final int FSK = 18;

    @InjectMocks
    private MediaServiceBean underTest;
    @Mock
    private PersistenceLayer persistenceLayerMock;
    @Mock
    private IdentifierValidator identifierValidatorMock;

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
        when(identifierValidatorMock.checkIdentifier(anyString())).thenReturn(true);
        underTest.addBook(book);
        verify(persistenceLayerMock).storeBook(CLEANED_ISBN, book);
    }

    @Test(expected = TitleIsMissingException.class)
    public void testAddBookTitleMissing() throws TitleIsMissingException, IdentifierAlreadyExistsException,
            AuthorIsMissingException, InvalidIdentifierException {
        final Book book = new Book("", AUTHOR, ISBN);
        when(identifierValidatorMock.checkIdentifier(anyString())).thenReturn(true);
        underTest.addBook(book);
    }

    @Test(expected = AuthorIsMissingException.class)
    public void testAddBookAuthorMissing() throws TitleIsMissingException, IdentifierAlreadyExistsException,
            AuthorIsMissingException, InvalidIdentifierException {
        final Book book = new Book(TITLE, "", ISBN);
        when(identifierValidatorMock.checkIdentifier(anyString())).thenReturn(true);
        underTest.addBook(book);
    }

    @Test(expected = InvalidIdentifierException.class)
    public void testAddBookISBNInvalid() throws TitleIsMissingException, IdentifierAlreadyExistsException,
            AuthorIsMissingException, InvalidIdentifierException {
        final Book book = new Book(TITLE, AUTHOR, "123");
        underTest.addBook(book);
    }

    @Test(expected = IdentifierAlreadyExistsException.class)
    public void testAddBookISBNAlreadyExists() throws TitleIsMissingException, IdentifierAlreadyExistsException,
            AuthorIsMissingException, InvalidIdentifierException {
        final Book book = new Book(TITLE, AUTHOR, ISBN);
        when(persistenceLayerMock.getBook(anyString())).thenReturn(book);
        when(identifierValidatorMock.checkIdentifier(anyString())).thenReturn(true);
        underTest.addBook(book);
    }

    @Test
    public void testUpdateBookGoodCase() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException {
        final Book book = new Book(TITLE, AUTHOR, ISBN);
        when(persistenceLayerMock.getBook(CLEANED_ISBN)).thenReturn(book);
        underTest.updateBook(CLEANED_ISBN, book);
        verify(persistenceLayerMock).updateBook(CLEANED_ISBN, book);
    }

    @Test(expected = IdentifierIsMissingException.class)
    public void testUpdateBookISBNMissing() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException {
        final Book book = new Book(TITLE, AUTHOR, ISBN);
        underTest.updateBook("", book);
    }

    @Test(expected = InvalidIdentifierException.class)
    public void testUpdateBookBookNotExists() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException {
        final Book book = new Book(TITLE, AUTHOR, ISBN);
        when(persistenceLayerMock.getBook(anyString())).thenReturn(null);
        underTest.updateBook(ISBN, book);
    }

    @Test(expected = IdentifierIsImmutableException.class)
    public void testUpdateBookChangeISBN() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException {
        final Book book = new Book(TITLE, AUTHOR, "1123");
        final Book oldBook = new Book(TITLE, AUTHOR, ISBN);
        when(persistenceLayerMock.getBook(anyString())).thenReturn(oldBook);
        underTest.updateBook(ISBN, book);
    }


    @Test
    public void testGetDisc() {
        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);
        when(persistenceLayerMock.getDisc(anyString())).thenReturn(disc);
        final Disc result = underTest.getDisc(BARCODE);
        assertEquals("Should be the same disc", disc, result);
    }

    @Test
    public void testGetDiscs() {
        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);
        final Disc[] discArray = new Disc[1];
        discArray[0] = disc;

        when(persistenceLayerMock.getDiscs()).thenReturn(discArray);
        final Disc[] result = underTest.getDiscs();
        assertTrue("Should be 1 entry", result.length == 1);
        assertEquals("Should be the same disc", disc, result[0]);
    }

    @Test
    public void testAddDiscGoodCase() throws DirectorIsMissingException, InvalidIdentifierException,
            IdentifierAlreadyExistsException, TitleIsMissingException {
        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);
        when(persistenceLayerMock.getDisc(anyString())).thenReturn(null);
        when(identifierValidatorMock.checkIdentifier(anyString())).thenReturn(true);
        underTest.addDisc(disc);
        verify(persistenceLayerMock).storeDisc(BARCODE, disc);
    }

    @Test(expected = TitleIsMissingException.class)
    public void testAddDiscTitleMissing() throws DirectorIsMissingException, InvalidIdentifierException,
            IdentifierAlreadyExistsException, TitleIsMissingException {
        final Disc disc = new Disc("", BARCODE, DIRECTOR, FSK);
        when(identifierValidatorMock.checkIdentifier(anyString())).thenReturn(true);
        underTest.addDisc(disc);
    }

    @Test(expected = DirectorIsMissingException.class)
    public void testAddDiscDirectorMissing() throws DirectorIsMissingException, InvalidIdentifierException,
            IdentifierAlreadyExistsException, TitleIsMissingException {
        final Disc disc = new Disc(TITLE, BARCODE, "", FSK);
        when(identifierValidatorMock.checkIdentifier(anyString())).thenReturn(true);
        underTest.addDisc(disc);
    }

    @Test(expected = InvalidIdentifierException.class)
    public void testAddDiscBarcodeInvalid() throws DirectorIsMissingException, InvalidIdentifierException,
            IdentifierAlreadyExistsException, TitleIsMissingException {
        final Disc disc = new Disc(TITLE, "123", DIRECTOR, FSK);
        underTest.addDisc(disc);
    }

    @Test(expected = IdentifierAlreadyExistsException.class)
    public void testAddDiscBarcodeAlreadyExists() throws IdentifierAlreadyExistsException, DirectorIsMissingException,
            InvalidIdentifierException, TitleIsMissingException {
        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);
        when(persistenceLayerMock.getDisc(anyString())).thenReturn(disc);
        when(identifierValidatorMock.checkIdentifier(anyString())).thenReturn(true);
        underTest.addDisc(disc);
    }

    @Test
    public void testUpdateDiscGoodCase() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException {
        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);
        when(persistenceLayerMock.getDisc(BARCODE)).thenReturn(disc);
        underTest.updateDisc(BARCODE, disc);
        verify(persistenceLayerMock).updateDisc(BARCODE, disc);
    }

    @Test(expected = IdentifierIsMissingException.class)
    public void testUpdateDiscBarcodeMissing() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException {
        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);
        underTest.updateDisc("", disc);
    }

    @Test(expected = InvalidIdentifierException.class)
    public void testUpdateDiscDiscNotExists() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException {
        final Disc disc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);
        when(persistenceLayerMock.getDisc(BARCODE)).thenReturn(null);
        underTest.updateDisc(BARCODE, disc);
    }

    @Test(expected = IdentifierIsImmutableException.class)
    public void testUpdateDiscChangeBarcode() throws IdentifierIsImmutableException, InvalidIdentifierException,
            IdentifierIsMissingException {
        final Disc disc = new Disc(TITLE, "123", DIRECTOR, FSK);
        final Disc oldDisc = new Disc(TITLE, BARCODE, DIRECTOR, FSK);
        when(persistenceLayerMock.getDisc(anyString())).thenReturn(oldDisc);
        underTest.updateDisc(BARCODE, disc);
    }

}
