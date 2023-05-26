package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class People extends ConnectionBase {

    public People(){
        super();
    }

    public Person selectPerson(Person person){
        Person person1 = null;
        try {
            Statement st = conexao.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM people WHERE personId LIKE 1");
            while (rs.next()) {

//                person1 = new Person(rs.getInt(1),rs.getString(2),rs.getString(3),
//                        new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(4)),rs.getString(5),rs.getString(6),
//                        rs.getString(7),rs.getString(8));

            }

        }catch (SQLException e) {
            System.out.println(e);
        }
//        catch (ParseException e) {
//            throw new RuntimeException(e);
//        }

        return person1;
    }
}
