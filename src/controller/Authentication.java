package controller;

import interfaces.Visibled;
import model.Session;
import model.SessionManager;

import java.awt.event.MouseAdapter;

public class Authentication {


    public static boolean authenticate(String username, String password, Visibled viewNow , Visibled callback, ControllingManager controllingManager){

        SessionManager sessionManager = new SessionManager();
        Session session =  sessionManager.login(username, password);

        if(session != null){

            controllingManager.setSession(session);

            callback.setVisible(true);
            viewNow.setVisible(false);

            return true;
        }

        return false;
    }


}
