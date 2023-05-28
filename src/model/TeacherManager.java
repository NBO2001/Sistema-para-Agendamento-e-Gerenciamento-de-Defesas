package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherManager extends ConnectionBase{

    public static boolean insert(Teacher teacher){

        if(teacher.getPersonId() == -1){
            if(!People.insert(teacher)) return false;
        }

        int idPerson = People.select(teacher.getCpf()).getPersonId();

        String sql = "INSERT INTO teachers (teacher_internal_id, personId) VALUES (?,?)";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, teacher.getRegister());
            stmt.setInt(2, idPerson);

            stmt.executeUpdate();

            stmt.close();

            return true;

        } catch (SQLException e) {
            return false;
        }

    }
}
