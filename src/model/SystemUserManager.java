package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

    public static ArrayList<SystemUser> selectAll(int personId){

        ArrayList<SystemUser> systemUsers = new ArrayList<>();
        SystemUser systemUser;

        String sql = "SELECT s.system_user_id, s.account_status,p.personId, p.name, p.social_name, p.birthday, p.cpf, p.rg, p.email, p.phone_number " +
                "FROM system_users s  " +
                "JOIN people p ON s.personId = p.personId " +
                "WHERE p.personId = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, personId);


            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                systemUser = new SystemUser();

                systemUser.setSystemUserId(resultSet.getInt("system_user_id"));
                systemUser.setStatus(resultSet.getInt("account_status"));

                systemUser.setPersonId(resultSet.getInt("personId"));
                systemUser.setName(resultSet.getString("name"));
                systemUser.setSocialName(resultSet.getString("social_name"));
                systemUser.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday")));
                systemUser.setCpf(resultSet.getString("cpf"));
                systemUser.setRg(resultSet.getString("rg"));
                systemUser.setEmail(resultSet.getString("email"));
                systemUser.setPhoneNumber(resultSet.getString("phone_number"));

                systemUsers.add(systemUser);

            }

            resultSet.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return systemUsers;


    }

    public static boolean delete(Person systemUser){

        SystemUser systemUser1 = (SystemUser) systemUser;

        System.out.println(systemUser1);
        return true;
    }
}
