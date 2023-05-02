package model;

import java.util.Date;
import java.util.Objects;

public abstract class Person {

    protected String name;
    protected String socialName;
    protected Date birthday;

    protected int cpf;
    protected int rg;

    protected String email;
    protected int phoneNumber;

    public Person(String name, String socialName, Date birthday, int cpf, int rg, String email, int phoneNumber) {
        this.name = name;
        this.socialName = socialName;
        this.birthday = birthday;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Person(String name, Date birthday, int cpf, int rg, String email) {
        this(name, null, birthday, cpf, rg, email, 0);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialName() {
        return socialName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return cpf == person.cpf && rg == person.rg && phoneNumber == person.phoneNumber && Objects.equals(name, person.name) && Objects.equals(socialName, person.socialName) && Objects.equals(birthday, person.birthday) && Objects.equals(email, person.email);
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", socialName='" + socialName + '\'' +
                ", birthday=" + birthday +
                ", cpf=" + cpf +
                ", rg=" + rg +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
