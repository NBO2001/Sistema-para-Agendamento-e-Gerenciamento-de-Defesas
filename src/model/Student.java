package model;

import java.util.Date;

public class Student extends Person{

    private int typeStudent;

    private int studentId;
    public Student(String name, String socialName, Date birthday, int cpf, int rg, String email, int phoneNumber, int typeStudent, int studentId) {
        super(name, socialName, birthday, cpf, rg, email, phoneNumber);
        setStudentId(studentId);
        setTypeStudent(typeStudent);
    }

    public Student(String name, Date birthday, int cpf, int rg, String email, int typeStudent, int studentId) {
        super(name, birthday, cpf, rg, email);
        setStudentId(studentId);
        setTypeStudent(typeStudent);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", socialName='" + socialName + '\'' +
                ", birthday=" + birthday +
                ", cpf=" + cpf +
                ", rg=" + rg +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", typeStudent=" + getTypeStudent() +
                ", studentId=" + getStudentId() +
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
     * */
    public int getTypeStudent() {
        return typeStudent;
    }

    public void setTypeStudent(int typeStudent) {
        this.typeStudent = typeStudent;
    }

    /**
     * studentId is the method at the university using for identify student.
     * */
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
