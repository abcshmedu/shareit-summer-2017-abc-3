package edu.hm.swa.sh.abc3.authorize.persistence;

import edu.hm.swa.sh.abc3.authorize.common.Permission;
import edu.hm.swa.sh.abc3.authorize.common.TokenDTO;
import edu.hm.swa.sh.abc3.authorize.common.UserDTO;
import edu.hm.swa.sh.abc3.authorize.persistence.entity.PermissionEntity;
import edu.hm.swa.sh.abc3.authorize.persistence.entity.TokenEntity;
import edu.hm.swa.sh.abc3.authorize.persistence.entity.UserEntity;
import edu.hm.swa.sh.abc3.authorize.persistence.entity.UserRoleEntity;
import edu.hm.swa.sh.abc3.authorize.persistence.transformer.UserTransformer;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of AuthPersistenceLayer.
 */
public final class SimpleAuthPersistenceLayerImpl implements AuthPersistenceLayer {
    private static List<UserDTO> userList;
    private static List<String> adminPermissionList;
    private static List<String> registredPermissionList;
    private static List<TokenDTO> tokenList;

    @Inject
    private SessionFactory sessionFactory;
    @Inject
    private UserTransformer userTransformer;

    private boolean isInit = false;

    /**
     * Cstr.
     */
    public SimpleAuthPersistenceLayerImpl() {
    }

    /**
     * Init lists for static data.
     */
    public void initPersistence() {
        if (!isInit) {
            final Session session = sessionFactory.getCurrentSession();
            final Transaction transaction = session.beginTransaction();

            try {
                final PermissionEntity addBook = new PermissionEntity();
                addBook.setMethod("addBook");
                final PermissionEntity getBook = new PermissionEntity();
                getBook.setMethod("getBook");
                final PermissionEntity getBooks = new PermissionEntity();
                getBooks.setMethod("getBooks");
                final PermissionEntity updateBooks = new PermissionEntity();
                updateBooks.setMethod("updateBooks");
                final PermissionEntity addDisc = new PermissionEntity();
                addDisc.setMethod("addDisc");
                final PermissionEntity getDisc = new PermissionEntity();
                getDisc.setMethod("getDisc");
                final PermissionEntity getDiscs = new PermissionEntity();
                getDiscs.setMethod("getDiscs");
                final PermissionEntity updateDisc = new PermissionEntity();
                updateDisc.setMethod("updateDisc");

                session.persist(addBook);
                session.persist(getBook);
                session.persist(getBooks);
                session.persist(updateBooks);
                session.persist(addDisc);
                session.persist(getDisc);
                session.persist(getDiscs);
                session.persist(updateDisc);

                final UserRoleEntity adminRole = new UserRoleEntity();
                adminRole.setName(Permission.ADMIN.name());
                final Set<PermissionEntity> adminPermissions = new HashSet<>();
                adminPermissions.add(addBook);
                adminPermissions.add(getBook);
                adminPermissions.add(getBooks);
                adminPermissions.add(updateBooks);
                adminPermissions.add(addDisc);
                adminPermissions.add(getDisc);
                adminPermissions.add(getDiscs);
                adminPermissions.add(updateDisc);
                adminRole.setPermissions(adminPermissions);

                final UserRoleEntity userRole = new UserRoleEntity();
                userRole.setName(Permission.REGISTRED.name());
                final Set<PermissionEntity> permissions = new HashSet<>();
                permissions.add(getBook);
                permissions.add(getBooks);
                permissions.add(getDisc);
                permissions.add(getDiscs);
                userRole.setPermissions(permissions);

                session.persist(adminRole);
                session.persist(userRole);

                final UserEntity userAdmin = new UserEntity();
                userAdmin.setUsername("shareitadmin");
                userAdmin.setSecret("shareitpassword");
                userAdmin.setUserRoleEntity(adminRole);

                final UserEntity userRegistred = new UserEntity();
                userRegistred.setUsername("shareituser");
                userRegistred.setSecret("shareitpassword");
                userRegistred.setUserRoleEntity(userRole);

                session.persist(userAdmin);
                session.persist(userRegistred);
            } catch (Exception ignored) {
            }
            transaction.commit();
            isInit = true;
        }
    }

    @Override
    public UserDTO getUser(final String username) {
        initPersistence();
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        Query query = session.createNamedQuery(UserEntity.FIND_BY_USERNAME, UserEntity.class);
        query.setParameter("username", username);
        try {
            final UserEntity result = (UserEntity) query.getSingleResult();
            return userTransformer.toUserDTO(result);
        } catch (final NoResultException exception) {
            return null;
        } finally {
            transaction.commit();
        }
    }

    @Override
    public UserDTO getUser(final long userid) {
        initPersistence();
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        final UserEntity result = session.find(UserEntity.class, userid);
        transaction.commit();
        return userTransformer.toUserDTO(result);
    }

    @Override
    public void storeToken(final TokenDTO token) {
        initPersistence();
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();

        final UserEntity result = session.find(UserEntity.class, token.getUserid());

        final TokenEntity entity = new TokenEntity();
        entity.setToken(token.getToken());
        entity.setUser(result);
        entity.setTtl(token.getTimeToLive());

        session.persist(entity);
        transaction.commit();
    }

    @Override
    public void updateToken(final TokenDTO token) {
        initPersistence();
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();

        final TokenEntity entity = session.find(TokenEntity.class, token.getToken());
        if (entity != null) {
            entity.setTtl(token.getTimeToLive());
            session.merge(entity);
        }
        transaction.commit();
    }

    @Override
    public TokenDTO getToken(final String token) {
        initPersistence();
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        try {
            final TokenEntity entity = session.load(TokenEntity.class, token);
            final Long userid = entity.getUser().getId();

            return new TokenDTO(entity.getToken(), userid, entity.getTtl());
        } catch (final ObjectNotFoundException exception) {
            return null;
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void destroyToken(final String token) {
        initPersistence();
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();

        final TokenEntity entity = session.load(TokenEntity.class, token);
        if (entity != null) {
            session.delete(entity);
        }
        transaction.commit();
    }

    @Override
    public List<String> getMethodsForPermission(final Permission permission) {
        initPersistence();
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery(UserRoleEntity.FIND_BY_NAME, UserRoleEntity.class);
        query.setParameter("name", permission.name());

        final UserRoleEntity result = (UserRoleEntity) query.getSingleResult();
        final List<String> methods = new ArrayList<>();

        for (final PermissionEntity permissionEntity : result.getPermissions()) {
            methods.add(permissionEntity.getMethod());
        }
        transaction.commit();
        return methods;
    }
}
