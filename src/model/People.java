package model;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class People extends ConnectionBase {

    public People(){
        super();
    }

    public Person select(int id){
        Person person1 = null;
        try {
            Statement st = conexao.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM people WHERE personId LIKE " + id);
            if (rs.next()) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                person1 = new Person(rs.getInt(1),rs.getString(2),rs.getString(3),
                        dateFormat.parse(rs.getString(4)),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8));

            }

        }catch (SQLException | ParseException e) {
            System.out.println(e);
        }
//        catch (ParseException e) {
//            throw new RuntimeException(e);
//        }

        return person1;
    }


    public static Person select(String cpf){

        Person person = null;

        String sql = "SELECT * FROM people WHERE cpf LIKE ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, cpf);


            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){




                person = new Person(
                        resultSet.getInt("personId"), resultSet.getString("name"),
                        resultSet.getString("social_name"), (new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday")),
                        resultSet.getString("cpf"), resultSet.getString("rg"), resultSet.getString("email"),
                        resultSet.getString("phone_number")
                );

            }

            resultSet.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return person;

    }

    public static Person select(Person person1){

        Person person = null;

        String sql = "SELECT * FROM people WHERE personId LIKE ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, person1.getPersonId());

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){

                person = new Person(
                        resultSet.getInt("personId"), resultSet.getString("name"),
                        resultSet.getString("social_name"), (new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday")),
                        resultSet.getString("cpf"), resultSet.getString("rg"), resultSet.getString("email"),
                        resultSet.getString("phone_number")
                );

            }

            resultSet.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return person;

    }

    public static ArrayList<Person> selectAll(){

        ArrayList<Person> people = new ArrayList<>();
        Person person;

        String sql = "SELECT p.personId, p.name, p.social_name, p.birthday, p.cpf, p.rg, p.email, p.phone_number " +
                "FROM people p " +
                " LIMIT 25";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                person = new Person();

                person.setPersonId(resultSet.getInt("personId"));
                person.setName(resultSet.getString("name"));
                person.setSocialName(resultSet.getString("social_name"));
                person.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday")));
                person.setCpf(resultSet.getString("cpf"));
                person.setRg(resultSet.getString("rg"));
                person.setEmail(resultSet.getString("email"));
                person.setPhoneNumber(resultSet.getString("phone_number"));

                people.add(person);

            }

            resultSet.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return people;


    }

    public static ArrayList<Person> selectAll(String querry){

        ArrayList<Person> people = new ArrayList<>();
        Person person;

        String sql = "SELECT p.personId, p.name, p.social_name, p.birthday, p.cpf, p.rg, p.email, p.phone_number " +
                "FROM people p WHERE  p.name LIKE ? OR p.social_name LIKE ?" +
                " ORDER BY p.name, p.social_name LIMIT 25";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, "%" + querry + "%" );
            stmt.setString(2, "%" + querry + "%" );
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                person = new Person();

                person.setPersonId(resultSet.getInt("personId"));
                person.setName(resultSet.getString("name"));
                person.setSocialName(resultSet.getString("social_name"));
                person.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday")));
                person.setCpf(resultSet.getString("cpf"));
                person.setRg(resultSet.getString("rg"));
                person.setEmail(resultSet.getString("email"));
                person.setPhoneNumber(resultSet.getString("phone_number"));

                people.add(person);

            }


            resultSet.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return people;


    }
    public static boolean insert(Person person){
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

    public static boolean update(Person person){
        String sql = "UPDATE people SET name = ?, cpf = ?, social_name = ?, birthday = ?, rg = ?, email = ?, phone_number = ? WHERE personId = ?";

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
            pstmt.setInt(8, person.getPersonId());
            
            int rowsAffected = pstmt.executeUpdate();

            if(rowsAffected != 0) return true;
            else return false;

        }catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean delete(Person person1){

        ArrayList<Student> students = StudentManager.selectAll(person1.getPersonId());
        ArrayList<Teacher> teachers = TeacherManager.selectAll(person1.getPersonId());

        if(students.size() != 0 || teachers.size() != 0) return false;

        String sql = "DELETE FROM people WHERE personId = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, person1.getPersonId() );

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected != 0;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }


}
