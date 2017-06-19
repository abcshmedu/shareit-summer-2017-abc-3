package edu.hm.swa.sh.abc3.mediaservice.persistence.simplepersistence;

import edu.hm.swa.sh.abc3.dto.Book;
import edu.hm.swa.sh.abc3.dto.Disc;
import edu.hm.swa.sh.abc3.dto.Medium;
import edu.hm.swa.sh.abc3.mediaservice.persistence.PersistenceLayer;
import edu.hm.swa.sh.abc3.mediaservice.persistence.entity.BookEntity;
import edu.hm.swa.sh.abc3.mediaservice.persistence.entity.DiscEntity;
import edu.hm.swa.sh.abc3.mediaservice.persistence.simplepersistence.transformer.BookTransformer;
import edu.hm.swa.sh.abc3.mediaservice.persistence.simplepersistence.transformer.DiscTransformer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * A simple implementation of PersistenceLayer interface.
 * <p>
 * Objects stored in HashMap instead in database.
 * I know, very simple but for the first step enough I think.
 */
@Singleton
public class PersistenceLayerBean implements PersistenceLayer {
    @Inject
    private SessionFactory sessionFactory;
    @Inject
    private BookTransformer bookTransformer;
    @Inject
    private DiscTransformer discTransformer;

    @Override
    public void storeBook(final String isbn, final Book book) {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        final BookEntity entity = bookTransformer.toEntity(book);
        session.persist(entity);
        transaction.commit();
        System.out.println("Book stored: " + book);
    }

    @Override
    public Book getBook(final String isbn) {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        final BookEntity entity = session.find(BookEntity.class, isbn);

        final Book result = bookTransformer.toBook(entity);

        transaction.commit();
        System.out.println("Found book: " + result);
        return result;
    }

    @Override
    public Book[] getBooks() {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();

        final List<BookEntity> bookEntityList = session.createCriteria(BookEntity.class).list();
        final Book[] result = bookTransformer.toBookArray(bookEntityList);

        transaction.commit();
        System.out.println("Found books: " + arrayToString(result));
        return result;
    }

    @Override
    public void updateBook(final String isbn, final Book book) {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        final BookEntity entity = session.find(BookEntity.class, isbn);

        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());

        session.merge(entity);
        transaction.commit();
        System.out.println("Updated book with ISBN '" + isbn + "' to " + book);
    }

    @Override
    public void storeDisc(final String barcode, final Disc disc) {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        final DiscEntity entity = discTransformer.toEntity(disc);
        session.persist(entity);
        transaction.commit();

        System.out.println("Disc stored: " + disc);
    }

    @Override
    public Disc getDisc(final String barcode) {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        final DiscEntity entity = session.find(DiscEntity.class, barcode);

        final Disc result = discTransformer.toDisc(entity);
        transaction.commit();
        System.out.println("Found disc: " + result);
        return null;
    }

    @Override
    public Disc[] getDiscs() {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();

        final List<DiscEntity> discEntityList = session.createCriteria(DiscEntity.class).list();
        final Disc[] result = discTransformer.toDiscArray(discEntityList);

        transaction.commit();

        System.out.println("Found discs: " + arrayToString(result));
        return result;
    }

    @Override
    public void updateDisc(final String barcode, final Disc disc) {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        final DiscEntity entity = session.find(DiscEntity.class, barcode);

        entity.setTitle(disc.getTitle());
        entity.setDirector(disc.getDirector());
        entity.setFsk(disc.getFsk());

        session.merge(entity);
        transaction.commit();

        System.out.println("Updated disc with barcode '" + barcode + "' to " + disc);
    }

    /**
     * For logging.
     *
     * @param mediums Mediums to convert to string.
     * @return string representation of mediums.
     */
    private String arrayToString(final Medium[] mediums) {
        final StringBuilder builder = new StringBuilder();

        builder.append('[');
        for (final Medium medium : mediums) {
            builder.append(medium);
        }
        builder.append(']');

        return builder.toString();
    }
}
