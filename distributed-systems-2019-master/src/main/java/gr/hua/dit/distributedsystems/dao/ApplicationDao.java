package gr.hua.dit.distributedsystems.dao;

import gr.hua.dit.distributedsystems.model.Application;
import gr.hua.dit.distributedsystems.model.ApplicationTerm;

public interface ApplicationDao {
    ApplicationTerm getCurrentTerm();

    ApplicationTerm getLastActiveTerm();

    ApplicationTerm endCurrentTerm();

    void startNewTerm(short percentage);

    Application getApplicationById(int id);

    void saveApplication(Application application);
}
