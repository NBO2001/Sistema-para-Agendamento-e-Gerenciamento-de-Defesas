package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SystemUserManager extends ConnectionBase{

    public SystemUserManager(){
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

    public static boolean insert(SystemUser systemUser){

        if(systemUser.getPersonId() == -1){
            if(!People.insert(systemUser)) return false;
        }

        int idPerson = People.select(systemUser.getCpf()).getPersonId();

        String sql = "INSERT INTO system_users (login, passwd, account_status, personId) VALUES (?,?,?,?)";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, systemUser.getLogin());
            stmt.setString(2, systemUser.getPassword());
            stmt.setBoolean(3, true);
            stmt.setInt(4, idPerson);

            stmt.executeUpdate();

            stmt.close();

            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
