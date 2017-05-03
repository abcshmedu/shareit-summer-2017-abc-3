package edu.hm.swa.sh.abc3.rest.transformer;

import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.rest.types.BookType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookTransfomerTest {
    private static final String AUTHOR = "Test Author";
    private static final String AUTHOR_2 = "Test Author 2";
    private static final String TITLE = "Test Title";
    private static final String TITLE_2 = "Test Title 2";
    private static final String ISBN = "978-3-7657-2781-8";
    private static final String ISBN_2 = "978-3-8882-9343-6";

    @Test
    public void testToBook() {
        final BookType bookType = new BookType();
        bookType.setTitle(TITLE);
        bookType.setAuthor(AUTHOR);
        bookType.setIsbn(ISBN);

        final BookTransformer underTest = new BookTransformer();

        final Book result = underTest.toBook(bookType);

        assertEquals("Should be " + AUTHOR, AUTHOR, result.getAuthor());
        assertEquals("Should be " + TITLE, TITLE, result.getTitle());
        assertEquals("Should be " + ISBN, ISBN, result.getIsbn());
    }

    @Test
    public void testToBookType() {
        final Book book = new Book(TITLE, AUTHOR, ISBN);
        final BookTransformer underTest = new BookTransformer();

        final BookType result = underTest.toBookType(book);

        assertEquals("Should be " + AUTHOR, AUTHOR, result.getAuthor());
        assertEquals("Should be " + TITLE, TITLE, result.getTitle());
        assertEquals("Should be " + ISBN, ISBN, result.getIsbn());
    }

    @Test
    public void testToBookTypeArray() {
        final Book book1 = new Book(TITLE, AUTHOR, ISBN);
        final Book book2 = new Book(TITLE_2, AUTHOR_2, ISBN_2);

        final Book[] bookArray = new Book[2];
        bookArray[0] = book1;
        bookArray[1] = book2;

        final BookTransformer underTest = new BookTransformer();

        final BookType[] result = underTest.toBookTypeArray(bookArray);

        assertTrue("Array should have length of 2", result.length == 2);

        final BookType bookType1 = result[0];
        assertEquals("Should be " + AUTHOR, AUTHOR, bookType1.getAuthor());
        assertEquals("Should be " + TITLE, TITLE, bookType1.getTitle());
        assertEquals("Should be " + ISBN, ISBN, bookType1.getIsbn());

        final BookType bookType2 = result[1];
        assertEquals("Should be " + AUTHOR_2, AUTHOR_2, bookType2.getAuthor());
        assertEquals("Should be " + TITLE_2, TITLE_2, bookType2.getTitle());
        assertEquals("Should be " + ISBN_2, ISBN_2, bookType2.getIsbn());
    }
}
