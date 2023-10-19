package gr.hua.dit.distributedsystems.dao;

import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.Collection;

public class DaoUtils {
    public static <T> Collection<T> getObjectList(SessionFactory sessionFactory, Class<T> clazz) {
        CriteriaQuery<T> cr = sessionFactory.getCriteriaBuilder().createQuery(clazz);
        cr.select(cr.from(clazz));
        return sessionFactory.getCurrentSession().createQuery(cr).getResultList();
    }

    public static  <T, P extends Serializable> T getEntityFromPrimaryKey(SessionFactory sessionFactory, Class<T> clazz, P pk) {
        return sessionFactory.getCurrentSession().get(clazz, pk);
    }
}
