package gr.hua.dit.distributedsystems.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "application")
public class Application implements Comparable<Application> {

    public enum ApplicationState {
        NEW, APPROVED, ACCEPTED, REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "submitter")
    private User submitter;

    @Column(name = "submission_date")
    private Date submissionDate = new Date();

    @Enumerated(EnumType.STRING)
    private ApplicationState state;

    public ApplicationState getState() {
        return state;
    }

    @ManyToOne
    @JoinColumn(name = "application_term")
    private ApplicationTerm term;

    private boolean commuting;
    @Column(name = "student_siblings")
    private int studentSiblings;
    @Column(name = "student_income")
    private int studentIncome;
    @Column(name = "family_income")
    private int familyIncome;
    @Column(name = "both_parents_unemployed")
    private boolean bothParentsUnemployed;

    @Transient
    private int score = -1;

    public Application() {
    }


    public void calculateScore() {
        score = 0;
        if (studentIncome == 0 && bothParentsUnemployed) {
            score = 999;
            return;
        }

        if (familyIncome < 10000) {
            score += 100;
        } else if (familyIncome < 15000) {
            score += 30;
        }

        score += studentSiblings * 20;

        if (commuting)
            score += 50;
    }

    public int getRanking() {
        return term.getRankingForApplication(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ApplicationTerm getTerm() {
        return term;
    }

    public void setTerm(ApplicationTerm term) {
        this.term = term;
    }

    public User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(User submitter) {
        this.submitter = submitter;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submission_date) {
        this.submissionDate = submission_date;
    }

    public void setState(ApplicationState state) {
        this.state = state;
    }

    public boolean isCommuting() {
        return commuting;
    }

    public void setCommuting(boolean commuting) {
        this.commuting = commuting;
    }

    public int getStudentSiblings() {
        return studentSiblings;
    }

    public void setStudentSiblings(int student_siblings) {
        this.studentSiblings = student_siblings;
    }

    public int getStudentIncome() {
        return studentIncome;
    }

    public void setStudentIncome(int student_income) {
        this.studentIncome = student_income;
    }

    public int getFamilyIncome() {
        return familyIncome;
    }

    public void setFamilyIncome(int family_income) {
        this.familyIncome = family_income;
    }

    public boolean isBothParentsUnemployed() {
        return bothParentsUnemployed;
    }

    public void setBothParentsUnemployed(boolean both_parents_unemployed) {
        this.bothParentsUnemployed = both_parents_unemployed;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Application other) {
        if (score == -1)
            throw new IllegalStateException("Score uninitialized");
        return other.score - score;
    }
}
