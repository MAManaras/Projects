package basics;


import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    private String id;

    private boolean fullyRetrieved = false;
    private Instant lastUpdated = Instant.now();

    BaseEntity() {}

    BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFullyRetrieved() {
        return fullyRetrieved;
    }

    public void setFullyRetrieved(boolean fullyRetrieved) {
        this.fullyRetrieved = fullyRetrieved;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "(" + getClass().getSimpleName() + ")";
    }
}
