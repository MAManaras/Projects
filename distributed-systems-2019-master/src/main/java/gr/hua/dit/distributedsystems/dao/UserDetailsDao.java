package gr.hua.dit.distributedsystems.dao;

import gr.hua.dit.distributedsystems.model.Department;
import gr.hua.dit.distributedsystems.model.Permission;
import gr.hua.dit.distributedsystems.model.Role;
import gr.hua.dit.distributedsystems.model.User;

import java.util.Collection;

public interface UserDetailsDao {
    User findUserByUsername(String username);

    Role findRoleByName(String name);

    Permission findPermissionByName(String name);

    Department findDepartmentByName(String name);

    void saveRole(Role role);

    void deleteRole(Role role);

    void saveUser(User user);

    void deleteUser(User user);

    Collection<User> getUserList();

    Collection<Role> getRoleList();

    Collection<Permission> getPermissionList();

    Collection<Department> getDepartments();
}
