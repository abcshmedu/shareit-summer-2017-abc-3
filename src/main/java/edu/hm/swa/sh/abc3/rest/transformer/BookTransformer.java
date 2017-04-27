package edu.hm.swa.sh.abc3.rest.transformer;


import edu.hm.swa.sh.abc3.common.dto.Book;
import edu.hm.swa.sh.abc3.rest.types.BookType;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.Stateless;

/**
 * Transform Book.
 */
@Stateless
public class BookTransformer {

    /**
     * Transform a book to a JSONObject.
     *
     * @param book book to transform.
     * @return JSONObject of a book.
     */
    public JSONObject toJSONObject(final Book book) {
        final JSONObject result = new JSONObject();
        if (book != null) {
            result.put("title", book.getTitle());
            result.put("author", book.getAuthor());
            result.put("isbn", book.getIsbn());
        }
        return result;
    }

    /**
     * Transform a book array to a JSONObject.
     *
     * @param books book array.
     * @return JSONObject of book array.
     */
    public JSONObject toJSONObject(final Book[] books) {
        final JSONObject result = new JSONObject();
        final JSONArray resultArray = new JSONArray();
        if (books != null && books.length > 1) {

            for (final Book singleBook : books) {
                resultArray.put(toJSONObject(singleBook));
            }
        }
        result.put("book", resultArray);
        return result;
    }

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
