package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

    public static Teacher select(String cpf){

        Teacher teacher = null;

        String sql = "SELECT t.teacher_id, t.teacher_internal_id,p.personId, p.name, p.social_name, p.birthday, p.cpf, p.rg, p.email, p.phone_number " +
                "FROM teachers t " +
                "JOIN people p ON t.personId = p.personId " +
                "WHERE p.cpf LIKE ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, cpf);


            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){

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

            }

            resultSet.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return teacher;

    }

    public static ArrayList<Teacher> selectAll(String name){

        ArrayList<Teacher> teachers = new ArrayList<>();
        Teacher teacher;

        String sql = "SELECT t.teacher_id, t.teacher_internal_id,p.personId, p.name, p.social_name, p.birthday, p.cpf, p.rg, p.email, p.phone_number " +
                "FROM teachers t " +
                "JOIN people p ON t.personId = p.personId " +
                "WHERE p.name LIKE ? LIMIT 10";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, name+"%");


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

    public static ArrayList<Teacher> selectAll(int personId){

        ArrayList<Teacher> teachers = new ArrayList<>();
        Teacher teacher;

        String sql = "SELECT t.teacher_id, t.teacher_internal_id,p.personId, p.name, p.social_name, p.birthday, p.cpf, p.rg, p.email, p.phone_number " +
                "FROM teachers t " +
                "JOIN people p ON t.personId = p.personId " +
                "WHERE p.personId = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, personId);


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

    public static boolean delete(Person teacher){

        Teacher teacher1 = (Teacher) teacher;

        DefenseManager defenseManager = new DefenseManager();

        ArrayList<Defense> defenses =  defenseManager.selectAll(((Teacher) teacher).getTeacherId(),1);

        if(defenses.size() != 0) return false;

        if(BoardOfTeachers.containsInBase(teacher1.getTeacherId())) return false;

        // SQL delete statement
        String sql = "DELETE FROM teachers WHERE teacher_id = ?";


        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, teacher1.getTeacherId() );

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected != 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }


    }

    public static boolean update(Teacher teacher1) {
        System.out.println("Falta implentar o update Teacher");
        return true;
    }
}
