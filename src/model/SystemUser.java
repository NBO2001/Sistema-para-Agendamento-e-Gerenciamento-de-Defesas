package model;

import java.util.Date;

public class SystemUser extends Person{

    public SystemUser(String name, String socialName, Date birthday, String cpf, String rg, String email, int phoneNumber) {
        super(name, socialName, birthday, cpf, rg, email, phoneNumber);
    }

    public SystemUser(String name, Date birthday, String cpf, String rg, String email) {
        super(name, birthday, cpf, rg, email);
    }
}
