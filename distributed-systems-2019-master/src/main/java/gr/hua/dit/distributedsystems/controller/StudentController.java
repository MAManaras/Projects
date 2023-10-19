package gr.hua.dit.distributedsystems.controller;

import gr.hua.dit.distributedsystems.dao.ApplicationDao;
import gr.hua.dit.distributedsystems.dao.UserDetailsDao;
import gr.hua.dit.distributedsystems.model.Application;
import gr.hua.dit.distributedsystems.model.ApplicationTerm;
import gr.hua.dit.distributedsystems.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    UserDetailsDao userDetailsDao;
    @Autowired
    ApplicationDao applicationDao;

    @GetMapping("/user/me")
    public User getUserDetails(Principal principal) {
        return userDetailsDao.findUserByUsername(principal.getName());
    }

    @Transactional
    @PostMapping("/user/contact")
    public ResponseEntity<String> updateContactDetails(Principal principal, @RequestBody User user) {
        User student = userDetailsDao.findUserByUsername(principal.getName());
        student.setPhone(user.getPhone());
        return ResponseEntity.ok("{}");
    }

    @GetMapping("/user/applicationInfo")
    public ResponseEntity<Application> getLastApplication(Principal principal) {
        User student = userDetailsDao.findUserByUsername(principal.getName());
        ApplicationTerm term = applicationDao.getCurrentTerm();
        if (term == null)
            term = applicationDao.getLastActiveTerm();

        Optional<Application> application = term.getApplications().stream()
                .filter(a -> a.getSubmitter().getUsername().equals(student.getUsername()))
                .findAny();

        return application.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/user/termInfo")
    public ResponseEntity<ApplicationTerm> getTermInfo(Principal principal) {
        ApplicationTerm term = applicationDao.getCurrentTerm();
        if (term == null) {
            term = applicationDao.getLastActiveTerm();
        }
        return ResponseEntity.ok(term);
    }

    @Transactional
    @PostMapping("/user/submitApplication")
    public ResponseEntity<String> submitApplication(Principal principal, @RequestBody Application application) {
        User student = userDetailsDao.findUserByUsername(principal.getName());
        ApplicationTerm term = applicationDao.getCurrentTerm();
        if (term == null) {
            return new ResponseEntity<>("Term is not currently active", HttpStatus.BAD_REQUEST);
        }
        if (term.getApplications().stream()
                .anyMatch(a -> a.getSubmitter().getUsername().equals(student.getUsername()))) {
            return new ResponseEntity<>("Application already submitted", HttpStatus.BAD_REQUEST);
        }
        application.setId(0);
        application.setSubmitter(student);
        application.setState(Application.ApplicationState.NEW);
        application.setTerm(term);
        applicationDao.saveApplication(application);
        return ResponseEntity.ok("{}");
    }
}