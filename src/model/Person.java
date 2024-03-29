package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Person {

    protected int personId;
    protected String name;
    protected String socialName;
    protected java.util.Date birthday;

    protected String cpf;
    protected String rg;

    protected String email;
    protected String phoneNumber;

    public Person(int personId, String name, String socialName, java.util.Date birthday, String cpf, String rg, String email, String phoneNumber) {
        this.setPersonId(personId);
        this.name = name;
        this.socialName = socialName;
        this.birthday = birthday;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Person(String name, String socialName, java.util.Date birthday, String cpf, String rg, String email, String phoneNumber) {
        this(-1,name,socialName,birthday, cpf, rg, email, phoneNumber);
    }

    public Person(String name, java.util.Date birthday, String cpf, String rg, String email) {
        this(name, null, birthday, cpf, rg, email, null);
    }

    public Person(int personId){
        this(personId, null, null, null,null, null,null,null);
    }

    public Person(){
        this(-1);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name!=null? name.trim(): null;
    }

    public String getSocialName() {
        return socialName;
    }

    public void setSocialName(String socialName) {

        this.socialName = socialName!=null? socialName.trim(): null;
    }

    public Date getBirthday() {
        return (Date) birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(String birthday) {
        birthday = birthday.trim();
        birthday = birthday.replaceAll("\\s", "");
        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
//        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = inputFormat.parse(birthday);
            this.birthday = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        cpf = cpf.trim();
        String cleanedCpf = cpf.replaceAll("[.-]", "");

        this.cpf = cleanedCpf;

    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg!=null? rg.trim(): null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email!=null? email.trim(): null;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber!=null? phoneNumber.trim(): null;
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


    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public boolean isValidValues(){

        if(!Utils.isValidCpf(this.getCpf())){
            System.out.println("Cpf é null");
            return false;
        }
        else if ( this.getName() == null || !(this.getName().length() >= 2)){
            System.out.println("Name is null");
            return false;
        }
        else if (this.getBirthday() == null){
            System.out.println("Birdday is null");
            return false;
        }
        else if (!(Utils.isValidEmail(this.getEmail()))){
            System.out.println("Email is null");
            return false;
        }
        else if (this.getRg() == null){
            System.out.println("Rg is null");
            return false;
        }

        return true;

    }


    public void clone(Person person){

        this.setName(person.getName());
        this.setSocialName(person.getSocialName());
        this.setCpf(person.getCpf());
        this.setRg(person.getRg());
        this.setBirthday(person.getBirthday());
        this.setPersonId(person.getPersonId());
        this.setEmail(person.getEmail());
        this.setPhoneNumber(person.getPhoneNumber());

    }
}
