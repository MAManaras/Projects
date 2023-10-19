package gr.hua.dit.distributedsystems.controller;

import gr.hua.dit.distributedsystems.dao.UserDetailsDao;
import gr.hua.dit.distributedsystems.model.Department;
import gr.hua.dit.distributedsystems.model.Permission;
import gr.hua.dit.distributedsystems.model.Role;
import gr.hua.dit.distributedsystems.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('PERM_EDIT_USERS')")
public class AdminController {

    @Autowired
    UserDetailsDao userDetailsDao;

    @GetMapping("/roles")
    public String roleList(Model model) {
        model.addAttribute("roles", userDetailsDao.getRoleList());
        model.addAttribute("newRole", new Role());
        return "role-list";
    }

    @RequestMapping(value = "/roles/{roleName}/edit", method = RequestMethod.GET)
    public String editRole(Model model, @PathVariable String roleName) {
        model.addAttribute("role", userDetailsDao.findRoleByName(roleName));
        model.addAttribute("permissions", userDetailsDao.getPermissionList());
        return "edit-role";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Permission.class, "permissions", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(userDetailsDao.findPermissionByName(text));
            }
        });
        binder.registerCustomEditor(Role.class, "role", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(userDetailsDao.findRoleByName(text));
            }
        });
        binder.registerCustomEditor(Department.class, "department", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(userDetailsDao.findDepartmentByName(text));
            }
        });
    }

    @Transactional
    @RequestMapping(value = "/roles/{roleName}/edit", method = RequestMethod.POST)
    public String submitRole(Model model, @PathVariable String roleName,
                             @Validated Role role) {
        Role realRole = userDetailsDao.findRoleByName(roleName);
        realRole.setFriendlyName(role.getFriendlyName());
        realRole.setDescription(role.getDescription());
        realRole.setPermissions(role.getPermissions());
        return editRole(model, roleName);
    }

    @GetMapping("/roles/{roleName}/delete")
    public String deleteRole(Model model, @PathVariable String roleName) {
        Role role = userDetailsDao.findRoleByName(roleName);
        userDetailsDao.deleteRole(role);
        return "redirect:/admin/roles/";
    }

    @PostMapping("/roles/new")
    public String newRole(Model model, Role role) {
        role.setName("ROLE_" + role.getFriendlyName().toUpperCase());
        Role existingRole = userDetailsDao.findRoleByName(role.getName());
        if (existingRole != null) {
            return "Role already exists";
        }

        userDetailsDao.saveRole(role);
        return "redirect:/admin/roles/" + role.getName() + "/edit";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userDetailsDao.getUserList());
        model.addAttribute("newUser", new User());
        return "user-list";
    }

    @GetMapping("/users/{username}/delete")
    public String deleteUser(Model model, @PathVariable String username) {
        User user = userDetailsDao.findUserByUsername(username);
        userDetailsDao.deleteUser(user);
        return "redirect:/admin/users/";
    }

    @RequestMapping(value = "/users/{username}/edit", method = RequestMethod.GET)
    public String editUser(Model model, @PathVariable String username) {
        User user = userDetailsDao.findUserByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
        }
        model.addAttribute("user", user);
        model.addAttribute("departments", userDetailsDao.getDepartments());
        model.addAttribute("roles", userDetailsDao.getRoleList());
        return "edit-user";
    }


    @Transactional
    @RequestMapping(value = "/users/{username}/edit", method = RequestMethod.POST)
    public String submitUser(Model model, @PathVariable String username,
                             @Validated User user) {
        User realUser = userDetailsDao.findUserByUsername(username);
        if (realUser != null) {
            realUser.setFullName(user.getFullName());
            realUser.setPhone(user.getPhone());
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                realUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            }
            realUser.setDepartment(user.getDepartment());
            realUser.setRole(user.getRole());
            userDetailsDao.saveUser(realUser);
        } else if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userDetailsDao.saveUser(user);
        }
        return editUser(model, username);
    }


    @PostMapping("/users/new")
    public String newUser(Model model, User user) {
        User existingUser = userDetailsDao.findUserByUsername(user.getUsername());
        if (existingUser != null) {
            return "user-list";
        }

        return "redirect:/admin/users/" + user.getUsername() + "/edit";
    }
}
