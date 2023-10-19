package gr.hua.dit.distributedsystems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "department")
public class Department {
    @Id
    public String id;

    private String name;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "department")
    private Collection<User> users;

    @JsonIgnore
    public User getSupervisor() {
        return users.stream()
                .filter(u -> u.getRole().getName().equals("ROLE_EMPLOYEE") || u.getRole().getName().equals("ROLE_SUPERVISOR"))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple supervisors found " + a.getUsername() + " " + b.getUsername());
                }).orElse(null);
    }

    @JsonIgnore
    public Collection<User> getStudents() {
        return users.stream()
                .filter((u -> u != getSupervisor()))
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department department = (Department) o;
        return id.equals(department.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
