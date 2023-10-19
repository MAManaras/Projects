package gr.hua.dit.distributedsystems.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role")
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department")
    private Department department;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "submitter")
    private Set<Application> submittedApplications;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private UserState state = UserState.PENDING;

    public void approve() {
        if (state != UserState.PENDING)
            throw new IllegalStateException();
        state = UserState.APPROVED;
    }

    public void reject() {
        if (state != UserState.PENDING)
            throw new IllegalStateException();
        state = UserState.REJECTED;
    }

    public UserState getState() {
        return state;
    }

    public enum UserState {
        PENDING, APPROVED, REJECTED
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
        if (getState() != UserState.APPROVED &&
                role.getPermissions().stream().anyMatch(p -> p.getName().equals("PERM_ACCESS_CONTROL_PANEL"))) {
            approve();
        }
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Application> getSubmittedApplications() {
        return submittedApplications;
    }

    public void setSubmittedApplications(Set<Application> submittedApplications) {
        this.submittedApplications = submittedApplications;
    }
}