package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BoardOfTeachers extends ConnectionBase{
    public static ArrayList<Teacher> selectAll(int defenseId){

        ArrayList<Teacher> teachers = new ArrayList<>();
        Teacher teacher;

        String sql = "SELECT t.teacher_id, t.teacher_internal_id,p.personId, p.name, p.social_name, p.birthday, p.cpf, p.rg, p.email, p.phone_number  " +
                "FROM boardOfTeachers b " +
                "JOIN teachers t ON b.teacher = t.teacher_id " +
                "JOIN people p ON p.personId = t.personId " +
                "WHERE b.defense_id = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, defenseId);


            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                teacher = new Teacher();

                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setRegister(resultSet.getString("teacher_internal_id"));


                teacher.setPersonId(resultSet.getInt("personId"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSocialName(resultSet.getString("social_name"));
                teacher.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday")));
                teacher.setCpf(resultSet.getString("cpf"));
                teacher.setRg(resultSet.getString("rg"));
                teacher.setEmail(resultSet.getString("email"));
                teacher.setPhoneNumber(resultSet.getString("phone_number"));

                teachers.add(teacher);

            }

            resultSet.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return teachers;


    }

    /**
     * The flag inform at the search for teacher id
     * */
    public static boolean containsInBase(int teacherId){

        boolean returned = false;
        String sql = "SELECT COUNT(*) as tol " +
                "FROM boardOfTeachers b " +
                "JOIN teachers t ON b.teacher = t.teacher_id " +
                "JOIN people p ON p.personId = t.personId " +
                "WHERE t.teacher_id = ?";

        try{

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, teacherId);


            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()){

                returned = resultSet.getInt("tol") != 0;

            }

            resultSet.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return returned;


    }

    public static boolean insert(int defenseId, int teacherId){
        String sql = "INSERT INTO boardOfTeachers (teacher, defense_id) " +
                "VALUES (?, ?)";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1,teacherId);
            stmt.setInt(2, defenseId);
            int cnt = stmt.executeUpdate();

            stmt.close();
            return cnt == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static boolean delete(int defenseId, int teacherId){

        String sql = "DELETE FROM boardOfTeachers " +
                "WHERE defense_id = ? AND teacher = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, defenseId);
            stmt.setInt(2, teacherId);

            int efects = stmt.executeUpdate();

            return efects != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
