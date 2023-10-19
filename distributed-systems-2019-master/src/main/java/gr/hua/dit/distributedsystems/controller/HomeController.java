package gr.hua.dit.distributedsystems.controller;

import gr.hua.dit.distributedsystems.dao.ApplicationDao;
import gr.hua.dit.distributedsystems.dao.UserDetailsDao;
import gr.hua.dit.distributedsystems.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    UserDetailsDao userDetailsDao;
    @Autowired
    ApplicationDao applicationDao;

    @RequestMapping("/")
    public String showHomepage(Model model) {
        model.addAttribute("term", applicationDao.getCurrentTerm());
        return "index";
    }

    @RequestMapping("/403")
    public String show403(Principal principal) {
        User user = userDetailsDao.findUserByUsername(principal.getName());
        if (user == null)
            return "403";
        if (!user.getRole().getPermissions().contains(userDetailsDao.findPermissionByName("PERM_ACCESS_CONTROL_PANEL"))) {
            return "ahahah";
        } else {
            return "403";
        }
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
}
