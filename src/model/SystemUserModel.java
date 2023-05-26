package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SystemUserModel extends ConnectionBase{

    public SystemUserModel(){
        super();
    }

    public SystemUser selectUser(SystemUser systemUser){


        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM people WHERE personId LIKE " + systemUser.getPersonId());
        }catch (SQLException e) {
            System.out.println(e);
        }

        return systemUser;
    }
}
