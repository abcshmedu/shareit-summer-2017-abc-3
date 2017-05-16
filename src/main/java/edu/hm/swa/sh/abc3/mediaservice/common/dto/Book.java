package edu.hm.swa.sh.abc3.mediaservice.common.dto;

/**
 * Book DTO.
 */
public class Book extends Medium {
    private final String author;
    private final String isbn;

    /**
     * Cstr.
     *
     * @param title  Title of book.
     * @param author Author of book.
     * @param isbn   ISBN of book.
     */
    public Book(final String title, final String author, final String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    /**
     * Returns the author.
     *
     * @return Author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the ISBN.
     *
     * @return ISBN.
     */
    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }

        Book book = (Book) other;

        return (author != null ? author.equals(book.author) : book.author == null) && isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + isbn.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{"
                + "author='" + author + '\''
                + ", isbn='" + isbn + '\''
                + ", title='" + getTitle() + '\''
                + '}';
    }
}
