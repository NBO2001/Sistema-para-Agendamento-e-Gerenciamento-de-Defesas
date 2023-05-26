package model;

import java.time.LocalDate;
import java.util.*;

public final class SystemUser extends Person{

    public SystemUser(String name, String socialName, Date birthday, String cpf, String rg, String email, String phoneNumber) {
        super(name, socialName, birthday, cpf, rg, email, phoneNumber);
    }

    public SystemUser(String name, Date birthday, String cpf, String rg, String email) {
        super(name, birthday, cpf, rg, email);
    }
}
