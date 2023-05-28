package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentManager extends ConnectionBase{


    public static boolean insert(Student student){

        if(student.getPersonId() == -1){
            if(!People.insert(student)) return false;
        }

        int idPerson = People.select(student.getCpf()).getPersonId();

        String sql = "INSERT INTO students (typeStudent, student_internal_id, personId) VALUES (?,?,?)";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, student.getTypeStudent());
            stmt.setString(2, student.getRegistration());
            stmt.setInt(3, idPerson);

            stmt.executeUpdate();

            stmt.close();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
