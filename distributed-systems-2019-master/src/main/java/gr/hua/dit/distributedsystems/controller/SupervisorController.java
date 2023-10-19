package gr.hua.dit.distributedsystems.controller;

import gr.hua.dit.distributedsystems.dao.ApplicationDao;
import gr.hua.dit.distributedsystems.dao.UserDetailsDao;
import gr.hua.dit.distributedsystems.model.Application;
import gr.hua.dit.distributedsystems.model.ApplicationTerm;
import gr.hua.dit.distributedsystems.model.Department;
import gr.hua.dit.distributedsystems.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/super")
public class SupervisorController {

    @Autowired
    UserDetailsDao userDetailsDao;
    @Autowired
    ApplicationDao applicationDao;

    @Transactional
    @GetMapping("/activationQueue")
    @PreAuthorize("hasAuthority('PERM_VERIFY_STUDENT')")
    public String activationQueue(Principal principal, Model model) {
        User loggedIn = userDetailsDao.findUserByUsername(principal.getName());

        Optional<User> unactivated = loggedIn.getDepartment().getStudents().stream()
                .filter(u -> u.getState() == User.UserState.PENDING)
                .findFirst();

        if (!unactivated.isPresent()) {
            model.addAttribute("msg", "All students have already been activated");
            return "redirect:/";
        }
        model.addAttribute("user", unactivated.get());
        return "student-pending-activation";
    }

    @Transactional
    @PostMapping("/activationQueue")
    @PreAuthorize("hasAuthority('PERM_VERIFY_STUDENT')")
    public String activationQueue(Principal principal, Model model, @RequestParam String username, @RequestParam String action) {
        User student = userDetailsDao.findUserByUsername(username);
        switch (action) {
            case "Activate":
                student.approve();
                break;
            case "Reject":
                student.reject();
                break;
        }

        return activationQueue(principal, model);
    }

    @Transactional
    @PostMapping("/endTerm")
    @PreAuthorize("hasAuthority('PERM_MANAGE_TERM')")
    public String endTerm() {
        ApplicationTerm term = applicationDao.endCurrentTerm();

        ArrayList<Application> applicationList = term.getApplications().stream()
                .filter(a -> a.getState() == Application.ApplicationState.APPROVED)
                .collect(Collectors.toCollection(ArrayList::new));
        applicationList.forEach(Application::calculateScore);
        applicationList.sort(Application::compareTo);

        int numberToApprove = (int) Math.ceil(applicationList.size() * ((double) term.getPercent() / 100.0));
        applicationList.stream().limit(numberToApprove).forEach(a -> a.setState(Application.ApplicationState.ACCEPTED));
        term.getApplications().forEach(a -> {
            if (a.getState() != Application.ApplicationState.ACCEPTED)
                a.setState(Application.ApplicationState.REJECTED);
        });

        return "redirect:/";
    }

    @PostMapping("/startNewTerm")
    @PreAuthorize("hasAuthority('PERM_MANAGE_TERM')")
    public String startTerm(Model model, @RequestParam short percentage) {
        applicationDao.startNewTerm(percentage);
        return "redirect:/";
    }

    @Transactional
    @GetMapping("/applicationNewQueue")
    @PreAuthorize("hasAuthority('PERM_VERIFY_APPLICATIONS')")
    public String applicationNewQueue(Principal principal, Model model) {
        User loggedIn = userDetailsDao.findUserByUsername(principal.getName());

        ApplicationTerm term = applicationDao.getCurrentTerm();
        // TODO: Check for null?

        Optional<Application> application = term.getApplications().stream()
                .filter(a -> a.getState() == Application.ApplicationState.NEW &&
                        a.getSubmitter().getDepartment() == loggedIn.getDepartment())
                .findFirst();

        if (!application.isPresent()) {
            model.addAttribute("msg", "All applications have already been processed");
            return "redirect:/";
        }
        model.addAttribute("application", application.get());
        return "pending-application";
    }

    @Transactional
    @PostMapping("/applicationNewQueue")
    @PreAuthorize("hasAuthority('PERM_VERIFY_APPLICATIONS')")
    public String approveApplication(Principal principal, Model model,
                                     @RequestParam int id,
                                     @RequestParam String action) {
        Application application = applicationDao.getApplicationById(id);
        // TODO: Check term
        switch (action) {
            case "Approve":
                application.setState(Application.ApplicationState.APPROVED);
                break;
            case "Reject":
                application.setState(Application.ApplicationState.REJECTED);
                break;
        }

        return applicationNewQueue(principal, model);
    }

    @Transactional
    @GetMapping("/acceptedApplications")
    @PreAuthorize("hasAuthority('PERM_VIEW_ACCEPTED_APPLICATIONS')")
    public String acceptedApplications(Principal principal, Model model) {
        Department dept = userDetailsDao.findUserByUsername(principal.getName()).getDepartment();
        ApplicationTerm term = applicationDao.getLastActiveTerm();
        Collection<Application> applications = term.getApplications().stream()
                .filter(a -> a.getState() == Application.ApplicationState.ACCEPTED)
                .collect(Collectors.toList());

        model.addAttribute("applications", applications);
        model.addAttribute("dept", dept);
        return "accepted-applications";
    }
}