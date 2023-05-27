package controller;

import model.Session;
import view.login.Login;

public class ControllingManager {

    private Session session;

    public ControllingManager() {
        this.setSession(null);
    }


    public boolean execute(){

        if(!Session.isSessionActive(this.getSession())){
            Login login = new Login(this);

            login.setVisible(true);

        }

        return true;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
