package gr.hua.dit.distributedsystems.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "application_term")
public class ApplicationTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean active;

    private short percent;

    @JsonBackReference
    @OneToMany(mappedBy = "term", fetch = FetchType.EAGER)
    private Collection<Application> applications;

    @Transient
    private List<Application> applicationRanking;

    public int getRankingForApplication(Application application) {
        if (applicationRanking == null) {
            applications.forEach(Application::calculateScore);
            applicationRanking = new ArrayList<>(applications);
            applicationRanking.sort(Application::compareTo);
        }
        return applicationRanking.indexOf(application) + 1;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public short getPercent() {
        return percent;
    }

    public void setPercent(short percent) {
        this.percent = percent;
    }

    public Collection<Application> getApplications() {
        return applications;
    }

    public void setApplications(Collection<Application> applications) {
        this.applications = applications;
    }
}
