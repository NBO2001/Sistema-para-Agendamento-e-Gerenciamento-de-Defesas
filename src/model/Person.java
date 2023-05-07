package model;

import java.util.Date;
import java.util.Objects;

public abstract class Person {

    protected int personId;
    protected String name;
    protected String socialName;
    protected Date birthday;

    protected String cpf;
    protected String rg;

    protected String email;
    protected int phoneNumber;

    public Person(String name, String socialName, Date birthday, String cpf, String rg, String email, int phoneNumber) {
        this.name = name;
        this.socialName = socialName;
        this.birthday = birthday;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Person(String name, Date birthday, String cpf, String rg, String email) {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
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
