package model;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class People extends ConnectionBase {
public class People extends ConnectionBase {

    public People(){
    public People(){
        super();
    }

    public Person selectPerson(Person person){
        Person person1 = null;
        try {
            Statement st = conexao.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM people WHERE personId LIKE " + person.getPersonId());
            if (rs.next()) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                person1 = new Person(rs.getInt(1),rs.getString(2),rs.getString(3),
                        dateFormat.parse(rs.getString(4)),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8));

            }

        }catch (SQLException e) {
            System.out.println(e);
        }
//        catch (ParseException e) {
//            throw new RuntimeException(e);
//        }

        return person1;
    }

    public boolean insertPerson(Person person){
        String sql = "INSERT INTO people (name, cpf, social_name, birthday, rg, email, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Create a SimpleDateFormat object to format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);

            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getCpf());
            pstmt.setString(3, person.getSocialName());
            pstmt.setString(4, dateFormat.format(person.getBirthday()).toString() );
            pstmt.setString(5, person.getRg());
            pstmt.setString(6, person.getEmail());
            pstmt.setString(7, person.getPhoneNumber());

            pstmt.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }
}
