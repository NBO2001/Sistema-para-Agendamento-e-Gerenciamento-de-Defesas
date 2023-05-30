package model;

import interfaces.Personificated;

import java.time.LocalDate;
import java.util.*;

public final class Student extends Person {

    private int typeStudent;

    private int studentId;
    private String registration;

    public Student(String name, String socialName, Date birthday, String cpf, String rg, String email, String phoneNumber, int typeStudent, int studentId) {
        super(name, socialName, birthday, cpf, rg, email, phoneNumber);
        setStudentId(studentId);
        setTypeStudent(typeStudent);
    }

    public Student(int personId,String name, String socialName, Date birthday, String cpf, String rg, String email, String phoneNumber) {
        super(personId, name, socialName, birthday, cpf, rg, email, phoneNumber);
        setStudentId(-1);
        setTypeStudent(1);
    }

    public Student(String name, Date birthday, String cpf, String rg, String email, int typeStudent, int studentId) {
        super(name, birthday, cpf, rg, email);
        setStudentId(studentId);
        setTypeStudent(typeStudent);
    }
    public Student(){
        super();
    }

    @Override
    public String toString() {
        return "Student: {" +
                "name='" + name + '\'' +
                ", socialName='" + socialName + '\'' +
                ", birthday=" + birthday +
                ", cpf=" + cpf +
                ", rg=" + rg +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", typeStudent=" + getTypeStudent() +
                ", studentId=" + getStudentId() +
                ", registration" + getRegistration() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return getTypeStudent() == student.getTypeStudent() && getStudentId() == student.getStudentId();
    }

    /**
     * typeStudent is of the student is 1-graduation, 2-master and 3-doctorate
     */

    public static String typeIntToString(int type){
        switch (type){
            case 1:
                return "Aluno de Graduação";
            case 2:
                return "Aluno de Mestrado";
            case 3:
                return "Aluno de Doutorado";
            default:
                return null;
        }
    }

    public int getTypeStudent() {
        return typeStudent;
    }

    public void setTypeStudent(int typeStudent) {
        this.typeStudent = typeStudent;
    }

    /**
     * studentId is the method at the university using for identify student.
     */
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public static Student toStudent(Person person){
        return new Student(person.getPersonId(),person.getName(), person.getSocialName(),
                person.getBirthday(), person.getCpf(), person.getRg(),
                person.getEmail(), person.getPhoneNumber()
        );

    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
}
