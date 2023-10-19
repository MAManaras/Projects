package gr.hua.dit.distributedsystems.dao;

import gr.hua.dit.distributedsystems.model.Application;
import gr.hua.dit.distributedsystems.model.ApplicationTerm;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ApplicationDaoImpl implements ApplicationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ApplicationTerm getCurrentTerm() {
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<ApplicationTerm> cq = cb.createQuery(ApplicationTerm.class);
        Root<ApplicationTerm> root = cq.from(ApplicationTerm.class);
        cq.select(root);
        cq.where(cb.isTrue(root.get("active")));

        List<ApplicationTerm> rs = sessionFactory.getCurrentSession().createQuery(cq).getResultList();
        return rs.size() == 1 ? rs.get(0) : null;
    }

    @Override
    public ApplicationTerm getLastActiveTerm() {
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<ApplicationTerm> cq = cb.createQuery(ApplicationTerm.class);
        Root<ApplicationTerm> root = cq.from(ApplicationTerm.class);
        cq.select(root);
        cq.orderBy(cb.desc(root.get("id")));

        List<ApplicationTerm> rs = sessionFactory.getCurrentSession().createQuery(cq).getResultList();
        return rs.size() != 0 ? rs.get(0) : null;
    }

    @Override
    public ApplicationTerm endCurrentTerm() {
        ApplicationTerm current = getCurrentTerm();
        current.setActive(false);
        sessionFactory.getCurrentSession().update(current);
        return current;
    }

    @Override
    public void startNewTerm(short percentage) {
        ApplicationTerm term = new ApplicationTerm();
        term.setActive(true);
        term.setPercent(percentage);
        sessionFactory.getCurrentSession().save(term);
    }

    public Application getApplicationById(int id) {
        return DaoUtils.getEntityFromPrimaryKey(sessionFactory, Application.class, id);
    }

    @Override
    public void saveApplication(Application application) {
        sessionFactory.getCurrentSession().save(application);
    }
}
