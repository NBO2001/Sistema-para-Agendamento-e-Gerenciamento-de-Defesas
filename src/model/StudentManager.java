package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            return false;
        }


    }

    public static Student select(String cpf){

        Student student = null;

        String sql = "SELECT s.student_id, s.typeStudent, s.student_internal_id, p.personId, p.name, p.social_name, p.birthday, p.cpf, p.rg, p.email, p.phone_number " +
                "FROM students s " +
                "JOIN people p ON s.personId = p.personId " +
                "WHERE p.cpf LIKE ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, cpf);


            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){

                student = new Student();

                student.setStudentId(resultSet.getInt("student_id"));
                student.setTypeStudent(resultSet.getInt("typeStudent"));
                student.setRegistration(resultSet.getString("student_internal_id"));
                student.setPersonId(resultSet.getInt("personId"));
                student.setName(resultSet.getString("name"));
                student.setSocialName(resultSet.getString("social_name"));
                student.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday")));
                student.setCpf(resultSet.getString("cpf"));
                student.setRg(resultSet.getString("rg"));
                student.setEmail(resultSet.getString("email"));
                student.setPhoneNumber(resultSet.getString("phone_number"));

            }

            resultSet.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return student;

    }

}
