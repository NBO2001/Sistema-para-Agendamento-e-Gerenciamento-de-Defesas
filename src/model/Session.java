package model;

import java.util.Date;

public final class Session{

    private String tolking;
    private Date experiedIn;
    private int systemUserId;


    public Session(String tolking, Date experiedIn, int systemUserId) {
        this.setTolking(tolking);
        this.setExperiedIn(experiedIn);
        this.setSystemUserId(systemUserId);
    }

    public Session(String tolking, Date experiedIn){
        this(tolking, experiedIn, -1);
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

    public int getSystemUserId() {
        return this.systemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String toString(){
        return "Sesion{tolking: " + getTolking() + " ,experiedIn: " + getExperiedIn() + ",systemUserId: " + getSystemUserId() + "}";
    }
}
