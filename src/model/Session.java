package model;

import java.util.Date;

public final class Session{

    private String tolking;
    private Date experiedIn;
    private SystemUser systemUser;


    public Session(String tolking, Date experiedIn, SystemUser systemUser) {
        this.setTolking(tolking);
        this.setExperiedIn(experiedIn);
        this.setSystemUser(systemUser);
    }

    public Session(String tolking, Date experiedIn){
        this(tolking, experiedIn, null);
    }

    public String getTolking() {
        return tolking;
    }

    public void setTolking(String tolking) {
        this.tolking = tolking;
    }

    public Date getExperiedIn() {
        return experiedIn;
    }

    public void setExperiedIn(Date experiedIn) {
        this.experiedIn = experiedIn;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }
}
