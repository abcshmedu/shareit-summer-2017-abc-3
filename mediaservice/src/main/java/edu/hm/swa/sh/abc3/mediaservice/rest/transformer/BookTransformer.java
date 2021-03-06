package edu.hm.swa.sh.abc3.mediaservice.rest.transformer;


import edu.hm.swa.sh.abc3.dto.Book;
import edu.hm.swa.sh.abc3.types.media.BookType;

/**
 * Transform Book.
 */
public class BookTransformer {

    /**
     * Transform a booktype to a book dto.
     *
     * @param bookType Booktype.
     * @return BookDTO.
     */
    public Book toBook(final BookType bookType) {
        return new Book(bookType.getTitle(), bookType.getAuthor(), bookType.getIsbn());
    }

    /**
     * Transform a book to Booktype.
     *
     * @param book Book.
     * @return booktype.
     */
    public BookType toBookType(final Book book) {
        final BookType bookType = new BookType();
        if (book != null) {
            bookType.setAuthor(book.getAuthor());
            bookType.setIsbn(book.getIsbn());
            bookType.setTitle(book.getTitle());
        }
        return bookType;
    }

    /**
     * Transform a book array to booktype array.
     *
     * @param books book array.
     * @return bookType array.
     */
    public BookType[] toBookTypeArray(final Book[] books) {
        final BookType[] result = new BookType[books.length];

        for (int index = 0; index < books.length; index++) {
            result[index] = toBookType(books[index]);
        }

        return result;
    }
}
