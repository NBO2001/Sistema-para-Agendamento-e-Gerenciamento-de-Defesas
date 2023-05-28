package model;

import utils.Utils;

import java.time.LocalDate;
import java.util.*;

public final class SystemUser extends Person{

    private String login;
    private String password;
    public SystemUser(String name, String socialName, Date birthday, String cpf, String rg, String email, String phoneNumber) {
        super(name, socialName, birthday, cpf, rg, email, phoneNumber);
    }
    public SystemUser(int personId,String name, String socialName, Date birthday, String cpf, String rg, String email, String phoneNumber) {
        super(personId, name, socialName, birthday, cpf, rg, email, phoneNumber);
    }

    public SystemUser(String name, Date birthday, String cpf, String rg, String email) {
        super(name, birthday, cpf, rg, email);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {

//        this.login = login;
        this.login = Utils.encryptTolkienName(login);
        login = null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
//        this.password = password;
        this.password = Utils.encryptTolkienName(password);
        password = null;
    }

    public static SystemUser parseSystemUser(Person person){
        return new SystemUser(person.getPersonId(),person.getName(), person.getSocialName(),
                person.getBirthday(), person.getCpf(), person.getRg(),
                person.getEmail(), person.getPhoneNumber()
        );
    }
}
