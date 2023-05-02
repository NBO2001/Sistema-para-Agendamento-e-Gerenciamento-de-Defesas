package model;

import java.util.Date;

public final class Teacher extends Person {
    private int teacherId;

    public Teacher(String name, String socialName, Date birthday, int cpf, int rg, String email, int phoneNumber, int teacherId) {
        super(name, socialName, birthday, cpf, rg, email, phoneNumber);
        setTeacherId(teacherId);
    }

    public Teacher(String name, Date birthday, int cpf, int rg, String email, int teacherId) {
        super(name, birthday, cpf, rg, email);
        setTeacherId(teacherId);
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
