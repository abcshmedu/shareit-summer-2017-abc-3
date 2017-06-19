package edu.hm.swa.sh.abc3.mediaservice.persistence.simplepersistence.transformer;

import edu.hm.swa.sh.abc3.dto.Book;
import edu.hm.swa.sh.abc3.mediaservice.persistence.entity.BookEntity;

import java.util.List;

/**
 * Transform entity to dto.
 */
public class BookTransformer {
    /**
     * Transform Book dto to its entity.
     *
     * @param dto Book.
     * @return bookEntity.
     */
    public BookEntity toEntity(final Book dto) {
        if (dto == null) {
            return null;
        }
        final BookEntity entity = new BookEntity();
        entity.setAuthor(dto.getAuthor());
        entity.setTitle(dto.getTitle());
        entity.setIsbn(simplifyIdentifier(dto.getIsbn()));
        return entity;
    }

    /**
     * Transform entity of a book to a dto.
     *
     * @param entity BookEntity.
     * @return Book dto.
     */
    public Book toBook(final BookEntity entity) {
        if (entity == null) {
            return null;
        }
        final String author = entity.getAuthor();
        final String title = entity.getTitle();
        final String isbn = entity.getIsbn();

        return new Book(title, author, isbn);
    }

    /**
     * Transform list of entities to book array.
     *
     * @param entities List of BookEntity.
     * @return Book array.
     */
    public Book[] toBookArray(final List<BookEntity> entities) {
        final Book[] books = new Book[entities.size()];

        for (int index = 0; index < entities.size(); index++) {
            books[index] = toBook(entities.get(index));
        }

        return books;
    }

    /**
     * Remove unnessessary chars from identifier.
     *
     * @param identifier original identifier.
     * @return cleaned identifier.
     */
    private String simplifyIdentifier(final String identifier) {
        return identifier.replace("-", "").replace(" ", "");
    }
}
