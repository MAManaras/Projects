package gr.hua.dit.distributedsystems.dao;

import gr.hua.dit.distributedsystems.model.Department;
import gr.hua.dit.distributedsystems.model.Permission;
import gr.hua.dit.distributedsystems.model.Role;
import gr.hua.dit.distributedsystems.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class UserDetailsDaoImp implements UserDetailsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserByUsername(String username) {
        return DaoUtils.getEntityFromPrimaryKey(sessionFactory, User.class, username);
    }

    @Override
    public Role findRoleByName(String name) {
        return DaoUtils.getEntityFromPrimaryKey(sessionFactory, Role.class, name);
    }

    @Override
    public Permission findPermissionByName(String name) {
        return DaoUtils.getEntityFromPrimaryKey(sessionFactory, Permission.class, name);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return DaoUtils.getEntityFromPrimaryKey(sessionFactory, Department.class, name);
    }

    @Override
    public void saveRole(Role role) {
        sessionFactory.getCurrentSession().saveOrUpdate(role);
    }

    @Override
    public void deleteRole(Role role) {
        sessionFactory.getCurrentSession().delete(role);
    }

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    public Collection<User> getUserList() {
        return DaoUtils.getObjectList(sessionFactory, User.class);
    }

    public Collection<Role> getRoleList() {
        return DaoUtils.getObjectList(sessionFactory, Role.class);
    }

    public Collection<Permission> getPermissionList() {
        return DaoUtils.getObjectList(sessionFactory, Permission.class);
    }

    @Override
    public Collection<Department> getDepartments() {
        return DaoUtils.getObjectList(sessionFactory, Department.class);
    }

}
