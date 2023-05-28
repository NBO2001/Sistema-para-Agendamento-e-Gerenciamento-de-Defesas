package model;

import java.time.LocalDate;
import java.util.*;


public final class Teacher extends Person {
    private int teacherId;
    private String register;

    public Teacher(String name, String socialName, Date birthday, String cpf, String rg, String email, String phoneNumber, int teacherId) {
        super(name, socialName, birthday, cpf, rg, email, phoneNumber);
        setTeacherId(teacherId);
    }

    public Teacher(int personId, String name, String socialName, Date birthday, String cpf, String rg, String email, String phoneNumber) {
        super(personId, name, socialName, birthday, cpf, rg, email, phoneNumber);
        setTeacherId(-1);
    }

    public Teacher(String name, Date birthday, String cpf, String rg, String email, int teacherId) {
        super(name, birthday, cpf, rg, email);
        setTeacherId(teacherId);
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public static Teacher parseTeacher(Person person){
        return new Teacher(person.getPersonId(),person.getName(), person.getSocialName(),
                person.getBirthday(), person.getCpf(), person.getRg(),
                person.getEmail(), person.getPhoneNumber()
        );
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }
}
