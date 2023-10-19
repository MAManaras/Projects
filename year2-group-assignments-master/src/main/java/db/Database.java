package db;

import basics.*;
import org.hibernate.*;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import java.util.List;

public class Database {

    private static SessionFactory sessionFactory = null;

    private Session session;

    public Database(String connectionUrl, String username, String password) {
        if (sessionFactory == null) {
            Configuration config = new Configuration().configure();
            config.setImplicitNamingStrategy(ImplicitNamingStrategyComponentPathImpl.INSTANCE);
            config.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@//" + connectionUrl);
            config.setProperty("hibernate.connection.username", username);
            config.setProperty("hibernate.connection.password", password);
            sessionFactory = config.buildSessionFactory();
        }
        session = sessionFactory.openSession();
    }

    private boolean shouldUpdate(BaseEntity newEntity) {
        TypedQuery<Boolean> query = session.createQuery("SELECT e.fullyRetrieved FROM " +
                        newEntity.getClass().getName() + " e WHERE e.id = :id",
                Boolean.class);
        query.setParameter("id", newEntity.getId());
        List<Boolean> result = query.getResultList();

        if (result.isEmpty())
            return true;

        boolean fullyRetrieved = result.get(0);
        return !fullyRetrieved
                && newEntity.isFullyRetrieved();
    }

    private int saveArtist(Artist artist) {
        int updates = 0;
        if (artist instanceof Group) {
            for (Artist member : ((Group) artist).getMembers()) {
                updates += saveArtist(member);
            }
        }

        if (shouldUpdate(artist)) {
            session.saveOrUpdate(artist);
            updates++;
        }

        return updates;
    }

    private int saveRelease(Release release) {
        int updates = 0;
        if (release instanceof Compilation) {
            for (Artist artist : ((Compilation) release).getArtists()) {
                updates += saveArtist(artist);
            }
        } else if (release instanceof Album) {
            updates += saveArtist(((Album) release).getArtist());
        }

        if (shouldUpdate(release)) {
                session.saveOrUpdate(release);
                updates++;
        }

        return updates;
    }

    private int saveEntity(BaseEntity entity) {
        int updates = 0;
        if (entity instanceof Artist) {
            updates += saveArtist((Artist) entity);
        } else if (entity instanceof Release) {
            updates += saveRelease((Release) entity);
        } else {
            throw new IllegalArgumentException();
        }
        return updates;
    }

    /**
     * Save an entity in the database only if it doesn't exist or is more populated than the current entry.
     * @param entity The entity to save
     * @return The number of columns affected (1 for success, 0 for failure in this case)
     */
    public int save(BaseEntity entity) {
        int updates = 0;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            updates = saveEntity(entity);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        }
        return updates;
    }

    /**
     * Save all entities in the list in the database only if it doesn't exist or is more populated than the current entry.
     * @param entities The list of entities to save
     * @return The number of columns affected
     */
    public <T extends BaseEntity> int saveAll(List<T> entities) {
        int updates = 0;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (BaseEntity entity : entities) {
                updates += saveEntity(entity);
            }
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        }
        return updates;
    }

    public void detach(Object object) {
        session.detach(object);
    }

    public Artist getArtistFromId(String id) {
        return session.get(Artist.class, id);
    }

    public Release getReleaseFromId(String id) {
        return session.get(Release.class, id);
    }

    /**
     * Return the artists matching the given string as a name. The results will also contain partial matches
     * @param name The string to search for
     * @return The list of results
     */
    public List<Artist> searchArtistsByName(String name) {
        TypedQuery<Artist> query = session.createQuery(
                "SELECT a from Artist a WHERE lower(a.name) LIKE lower(:name)",
                Artist.class);

        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    public List<Artist> searchArtistByTags(String tag) {
        TypedQuery<Artist> query = session.createQuery(
                "SELECT a FROM Artist a JOIN a.tags t WHERE t = :tag",
                Artist.class);
        query.setParameter("tag", tag);
        return query.getResultList();
    }

    /**
     * Return the releases matching the given string as a title. The results will also contain partial matches
     * @param title The string to search for
     * @return The list of results
     */
    public List<Release> searchReleasesByTitle(String title) {
        TypedQuery<Release> query = session.createQuery(
                "SELECT r from Release r WHERE lower(r.title) LIKE lower(:title)",
                Release.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    public void close() {
        session.close();
    }

}
