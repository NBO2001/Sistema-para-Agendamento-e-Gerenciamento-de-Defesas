package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DefenseManager extends ConnectionBase{

    public DefenseManager(){
        super();
    }
    public static boolean insert(Defense defense){

        String sql = "INSERT INTO defense (type_defense, defense_title, date, local, teacher_advisor, student_defending, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";


        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, defense.getTypeDefense());
            stmt.setString(2, defense.getDefenseTitle());
            stmt.setString(3, Utils.dateForStringEUAWithHour(defense.getDate()));
            stmt.setString(4, defense.getLocal() );
            stmt.setInt(5, defense.getTeacherAdvisor().getTeacherId());
            stmt.setInt(6, defense.getStudentDefending().getStudentId());
            stmt.setInt(7, defense.getStatus());

            stmt.executeUpdate();

            // Get the generated keys (including the index)
            ResultSet generatedKeys = stmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                int insertedIndex = generatedKeys.getInt(1);

                stmt.close();
                generatedKeys.close();
                sql = "INSERT INTO boardOfTeachers (teacher, defense_id) " +
                        "VALUES (?, ?)";
                stmt = conexao.prepareStatement(sql);

                for(Teacher teacher: defense.getBoardOfTeachers()){
                    stmt.setInt(1,teacher.getTeacherId());
                    stmt.setInt(2, insertedIndex);
                    stmt.executeUpdate();
                }

                stmt.close();

            }

        }catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public static boolean update(Defense defense){

        String sql = "UPDATE defense  SET type_defense = ?, defense_title=?, date=?, local=?, teacher_advisor=?, student_defending=?, status=?, final_pontuation=?, observation=? " +
                " WHERE defense_id = ?";

        // Create a SimpleDateFormat object to format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, defense.getTypeDefense());
            stmt.setString(2, defense.getDefenseTitle());
            stmt.setString(3,  dateFormat.format(defense.getDate()).toString());
            stmt.setString(4, defense.getLocal() );
            stmt.setInt(5, defense.getTeacherAdvisor().getTeacherId());
            stmt.setInt(6, defense.getStudentDefending().getStudentId());
            stmt.setInt(7, defense.getStatus());

            stmt.setDouble(8, defense.getFinalPontuation());
            stmt.setString(9, defense.getObservation());

            stmt.setInt(10, defense.getDefenseId());

            int updIfo = stmt.executeUpdate();

            return updIfo != 0;

        }catch (SQLException e) {
            System.out.println(e);
            return false;
        }

    }

    public ArrayList<Defense> selectAllOpened(){

        ArrayList<Defense> defenses = new ArrayList<>();
        Teacher teacher;
        Student student;
        Defense defense;

        String sql = "SELECT d.defense_id, d.type_defense, d.defense_title, d.local, d.final_pontuation, d.status, d.observation, d.date, d.student_defending, " +
                "s.typeStudent, s.student_internal_id, p.personId as personId_student, p.name as name_student , p.social_name as social_name_student, p.birthday as birthday_student,   " +
                "p.cpf as cpf_student, p.rg as rg_student, p.email as email_student, p.phone_number as phone_number_student,  " +
                "t.teacher_id, t.teacher_internal_id,p2.personId as personId_teacher, p2.name as name_teacher, p2.social_name as social_name_teacher,  " +
                "p2.birthday as birthday_teacher, p2.cpf as cpf_teacher, p2.rg as rg_teacher, p2.email as email_teacher, p2.phone_number as phone_number_teacher " +
                "FROM defense d " +
                "JOIN students s ON d.student_defending = s.student_id " +
                "JOIN people p ON s.personId = p.personId " +
                "JOIN teachers t ON d.teacher_advisor = t.teacher_id " +
                "JOIN people p2 ON t.personId = p2.personId " +
                "WHERE d.status = ? ORDER BY d.date";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1,0);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                student = new Student();

                student.setStudentId(resultSet.getInt("student_defending"));
                student.setTypeStudent(resultSet.getInt("typeStudent"));
                student.setRegistration(resultSet.getString("student_internal_id"));

                student.setPersonId(resultSet.getInt("personId_student"));
                student.setName(resultSet.getString("name_student"));
                student.setSocialName(resultSet.getString("social_name_student"));
                student.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_student")));
                student.setCpf(resultSet.getString("cpf_student"));
                student.setRg(resultSet.getString("rg_student"));
                student.setEmail(resultSet.getString("email_student"));
                student.setPhoneNumber(resultSet.getString("phone_number_student"));

                teacher = new Teacher();

                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setRegister(resultSet.getString("teacher_internal_id"));

                teacher.setPersonId(resultSet.getInt("personId_teacher"));
                teacher.setName(resultSet.getString("name_teacher"));
                teacher.setSocialName(resultSet.getString("social_name_teacher"));
                teacher.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_teacher")));
                teacher.setCpf(resultSet.getString("cpf_teacher"));
                teacher.setRg(resultSet.getString("rg_teacher"));
                teacher.setEmail(resultSet.getString("email_teacher"));
                teacher.setPhoneNumber(resultSet.getString("phone_number_teacher"));

                defense = new Defense();

                defense.setDefenseId(resultSet.getInt("defense_id"));
                defense.setTypeDefense(resultSet.getInt("type_defense"));
                defense.setDefenseTitle(resultSet.getString("defense_title"));
                defense.setLocal(resultSet.getString("local"));
                defense.setFinalPontuation(resultSet.getDouble("final_pontuation"));
                defense.setStatus(resultSet.getInt("status"));
                defense.setObservation(resultSet.getString("observation"));
                defense.setDate((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("date")));
                defense.setStudentDefending(student);
                defense.setTeacherAdvisor(teacher);

                defenses.add(defense);

            }

            return defenses;
        }catch (SQLException | ParseException e) {
            System.out.println(e);

            return null;

        }
    }

    /**
     * If flag equal 0 find student, if equal 1, find teacher
     * */
    public ArrayList<Defense> selectAll(int id, int flag){

        ArrayList<Defense> defenses = new ArrayList<>();
        Teacher teacher;
        Student student;
        Defense defense;

        String sql;

        if(flag == 0){
            sql = "SELECT d.defense_id, d.type_defense, d.defense_title, d.local, d.final_pontuation, d.status, d.observation, d.date, d.student_defending, " +
                    "s.typeStudent, s.student_internal_id, p.personId as personId_student, p.name as name_student , p.social_name as social_name_student, p.birthday as birthday_student,   " +
                    "p.cpf as cpf_student, p.rg as rg_student, p.email as email_student, p.phone_number as phone_number_student,  " +
                    "t.teacher_id, t.teacher_internal_id,p2.personId as personId_teacher, p2.name as name_teacher, p2.social_name as social_name_teacher,  " +
                    "p2.birthday as birthday_teacher, p2.cpf as cpf_teacher, p2.rg as rg_teacher, p2.email as email_teacher, p2.phone_number as phone_number_teacher " +
                    "FROM defense d " +
                    "JOIN students s ON d.student_defending = s.student_id " +
                    "JOIN people p ON s.personId = p.personId " +
                    "JOIN teachers t ON d.teacher_advisor = t.teacher_id " +
                    "JOIN people p2 ON t.personId = p2.personId " +
                    "WHERE d.student_defending = ? ORDER BY d.date";
        }
        else{
            sql = "SELECT d.defense_id, d.type_defense, d.defense_title, d.local, d.final_pontuation, d.status, d.observation, d.date, d.student_defending, " +
                    "s.typeStudent, s.student_internal_id, p.personId as personId_student, p.name as name_student , p.social_name as social_name_student, p.birthday as birthday_student,   " +
                    "p.cpf as cpf_student, p.rg as rg_student, p.email as email_student, p.phone_number as phone_number_student,  " +
                    "t.teacher_id, t.teacher_internal_id,p2.personId as personId_teacher, p2.name as name_teacher, p2.social_name as social_name_teacher,  " +
                    "p2.birthday as birthday_teacher, p2.cpf as cpf_teacher, p2.rg as rg_teacher, p2.email as email_teacher, p2.phone_number as phone_number_teacher " +
                    "FROM defense d " +
                    "JOIN students s ON d.student_defending = s.student_id " +
                    "JOIN people p ON s.personId = p.personId " +
                    "JOIN teachers t ON d.teacher_advisor = t.teacher_id " +
                    "JOIN people p2 ON t.personId = p2.personId " +
                    "WHERE d.teacher_advisor = ? ORDER BY d.date";
        }

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, id );

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                student = new Student();

                student.setStudentId(resultSet.getInt("student_defending"));
                student.setTypeStudent(resultSet.getInt("typeStudent"));
                student.setRegistration(resultSet.getString("student_internal_id"));

                student.setPersonId(resultSet.getInt("personId_student"));
                student.setName(resultSet.getString("name_student"));
                student.setSocialName(resultSet.getString("social_name_student"));
                student.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_student")));
                student.setCpf(resultSet.getString("cpf_student"));
                student.setRg(resultSet.getString("rg_student"));
                student.setEmail(resultSet.getString("email_student"));
                student.setPhoneNumber(resultSet.getString("phone_number_student"));

                teacher = new Teacher();

                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setRegister(resultSet.getString("teacher_internal_id"));

                teacher.setPersonId(resultSet.getInt("personId_teacher"));
                teacher.setName(resultSet.getString("name_teacher"));
                teacher.setSocialName(resultSet.getString("social_name_teacher"));
                teacher.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_teacher")));
                teacher.setCpf(resultSet.getString("cpf_teacher"));
                teacher.setRg(resultSet.getString("rg_teacher"));
                teacher.setEmail(resultSet.getString("email_teacher"));
                teacher.setPhoneNumber(resultSet.getString("phone_number_teacher"));

                defense = new Defense();

                defense.setDefenseId(resultSet.getInt("defense_id"));
                defense.setTypeDefense(resultSet.getInt("type_defense"));
                defense.setDefenseTitle(resultSet.getString("defense_title"));
                defense.setLocal(resultSet.getString("local"));
                defense.setFinalPontuation(resultSet.getDouble("final_pontuation"));
                defense.setStatus(resultSet.getInt("status"));
                defense.setObservation(resultSet.getString("observation"));
                defense.setDate((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("date")));
                defense.setStudentDefending(student);
                defense.setTeacherAdvisor(teacher);

                defenses.add(defense);

            }

            return defenses;
        }catch (SQLException | ParseException e) {
            System.out.println(e);

            return null;

        }
    }
    public ArrayList<Defense> selectAll(String studentQuery, String teacherQuery){

        ArrayList<Defense> defenses = new ArrayList<>();
        Teacher teacher;
        Student student;
        Defense defense;

        String sql = "SELECT d.defense_id, d.type_defense, d.defense_title, d.local, d.final_pontuation, d.status, d.observation, d.date, d.student_defending, " +
                "s.typeStudent, s.student_internal_id, p.personId as personId_student, p.name as name_student , p.social_name as social_name_student, p.birthday as birthday_student,   " +
                "p.cpf as cpf_student, p.rg as rg_student, p.email as email_student, p.phone_number as phone_number_student,  " +
                "t.teacher_id, t.teacher_internal_id,p2.personId as personId_teacher, p2.name as name_teacher, p2.social_name as social_name_teacher,  " +
                "p2.birthday as birthday_teacher, p2.cpf as cpf_teacher, p2.rg as rg_teacher, p2.email as email_teacher, p2.phone_number as phone_number_teacher " +
                "FROM defense d " +
                "JOIN students s ON d.student_defending = s.student_id " +
                "JOIN people p ON s.personId = p.personId " +
                "JOIN teachers t ON d.teacher_advisor = t.teacher_id " +
                "JOIN people p2 ON t.personId = p2.personId " +
                "WHERE p.name LIKE ? AND p2.name LIKE ? ORDER BY d.date";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1,"%" + studentQuery + "%");
            stmt.setString(2,"%" + teacherQuery + "%");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                student = new Student();

                student.setStudentId(resultSet.getInt("student_defending"));
                student.setTypeStudent(resultSet.getInt("typeStudent"));
                student.setRegistration(resultSet.getString("student_internal_id"));

                student.setPersonId(resultSet.getInt("personId_student"));
                student.setName(resultSet.getString("name_student"));
                student.setSocialName(resultSet.getString("social_name_student"));
                student.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_student")));
                student.setCpf(resultSet.getString("cpf_student"));
                student.setRg(resultSet.getString("rg_student"));
                student.setEmail(resultSet.getString("email_student"));
                student.setPhoneNumber(resultSet.getString("phone_number_student"));

                teacher = new Teacher();

                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setRegister(resultSet.getString("teacher_internal_id"));

                teacher.setPersonId(resultSet.getInt("personId_teacher"));
                teacher.setName(resultSet.getString("name_teacher"));
                teacher.setSocialName(resultSet.getString("social_name_teacher"));
                teacher.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_teacher")));
                teacher.setCpf(resultSet.getString("cpf_teacher"));
                teacher.setRg(resultSet.getString("rg_teacher"));
                teacher.setEmail(resultSet.getString("email_teacher"));
                teacher.setPhoneNumber(resultSet.getString("phone_number_teacher"));

                defense = new Defense();

                defense.setDefenseId(resultSet.getInt("defense_id"));
                defense.setTypeDefense(resultSet.getInt("type_defense"));
                defense.setDefenseTitle(resultSet.getString("defense_title"));
                defense.setLocal(resultSet.getString("local"));
                defense.setFinalPontuation(resultSet.getDouble("final_pontuation"));
                defense.setStatus(resultSet.getInt("status"));
                defense.setObservation(resultSet.getString("observation"));
                defense.setDate((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("date")));
                defense.setStudentDefending(student);
                defense.setTeacherAdvisor(teacher);

                defenses.add(defense);

            }

            return defenses;
        }catch (SQLException | ParseException e) {
            System.out.println(e);

            return null;

        }
    }
    public ArrayList<Defense> selectAll(String studentQuery, String teacherQuery, int status){

        ArrayList<Defense> defenses = new ArrayList<>();
        Teacher teacher;
        Student student;
        Defense defense;

        String sql = "SELECT d.defense_id, d.type_defense, d.defense_title, d.local, d.final_pontuation, d.status, d.observation, d.date, d.student_defending, " +
                "s.typeStudent, s.student_internal_id, p.personId as personId_student, p.name as name_student , p.social_name as social_name_student, p.birthday as birthday_student,   " +
                "p.cpf as cpf_student, p.rg as rg_student, p.email as email_student, p.phone_number as phone_number_student,  " +
                "t.teacher_id, t.teacher_internal_id,p2.personId as personId_teacher, p2.name as name_teacher, p2.social_name as social_name_teacher,  " +
                "p2.birthday as birthday_teacher, p2.cpf as cpf_teacher, p2.rg as rg_teacher, p2.email as email_teacher, p2.phone_number as phone_number_teacher " +
                "FROM defense d " +
                "JOIN students s ON d.student_defending = s.student_id " +
                "JOIN people p ON s.personId = p.personId " +
                "JOIN teachers t ON d.teacher_advisor = t.teacher_id " +
                "JOIN people p2 ON t.personId = p2.personId " +
                "WHERE p.name LIKE ? AND p2.name LIKE ? AND d.status LIKE ? ORDER BY d.date";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1,"%" + studentQuery + "%");
            stmt.setString(2,"%" + teacherQuery + "%");
            stmt.setInt(3, status);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                student = new Student();

                student.setStudentId(resultSet.getInt("student_defending"));
                student.setTypeStudent(resultSet.getInt("typeStudent"));
                student.setRegistration(resultSet.getString("student_internal_id"));

                student.setPersonId(resultSet.getInt("personId_student"));
                student.setName(resultSet.getString("name_student"));
                student.setSocialName(resultSet.getString("social_name_student"));
                student.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_student")));
                student.setCpf(resultSet.getString("cpf_student"));
                student.setRg(resultSet.getString("rg_student"));
                student.setEmail(resultSet.getString("email_student"));
                student.setPhoneNumber(resultSet.getString("phone_number_student"));

                teacher = new Teacher();

                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setRegister(resultSet.getString("teacher_internal_id"));

                teacher.setPersonId(resultSet.getInt("personId_teacher"));
                teacher.setName(resultSet.getString("name_teacher"));
                teacher.setSocialName(resultSet.getString("social_name_teacher"));
                teacher.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_teacher")));
                teacher.setCpf(resultSet.getString("cpf_teacher"));
                teacher.setRg(resultSet.getString("rg_teacher"));
                teacher.setEmail(resultSet.getString("email_teacher"));
                teacher.setPhoneNumber(resultSet.getString("phone_number_teacher"));

                defense = new Defense();

                defense.setDefenseId(resultSet.getInt("defense_id"));
                defense.setTypeDefense(resultSet.getInt("type_defense"));
                defense.setDefenseTitle(resultSet.getString("defense_title"));
                defense.setLocal(resultSet.getString("local"));
                defense.setFinalPontuation(resultSet.getDouble("final_pontuation"));
                defense.setStatus(resultSet.getInt("status"));
                defense.setObservation(resultSet.getString("observation"));
                defense.setDate((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("date")));
                defense.setStudentDefending(student);
                defense.setTeacherAdvisor(teacher);

                defenses.add(defense);

            }

            return defenses;
        }catch (SQLException | ParseException e) {
            System.out.println(e);

            return null;

        }
    }

    public ArrayList<Defense> selectAll(String studentQuery, String teacherQuery, String start){

        ArrayList<Defense> defenses = new ArrayList<>();
        Teacher teacher;
        Student student;
        Defense defense;

        String sql = "SELECT d.defense_id, d.type_defense, d.defense_title, d.local, d.final_pontuation, d.status, d.observation, d.date, d.student_defending, " +
                "s.typeStudent, s.student_internal_id, p.personId as personId_student, p.name as name_student , p.social_name as social_name_student, p.birthday as birthday_student,   " +
                "p.cpf as cpf_student, p.rg as rg_student, p.email as email_student, p.phone_number as phone_number_student,  " +
                "t.teacher_id, t.teacher_internal_id,p2.personId as personId_teacher, p2.name as name_teacher, p2.social_name as social_name_teacher,  " +
                "p2.birthday as birthday_teacher, p2.cpf as cpf_teacher, p2.rg as rg_teacher, p2.email as email_teacher, p2.phone_number as phone_number_teacher " +
                "FROM defense d " +
                "JOIN students s ON d.student_defending = s.student_id " +
                "JOIN people p ON s.personId = p.personId " +
                "JOIN teachers t ON d.teacher_advisor = t.teacher_id " +
                "JOIN people p2 ON t.personId = p2.personId " +
                "WHERE p.name LIKE ? AND p2.name LIKE ? AND d.date >= ? ORDER BY d.date";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1,"%" + studentQuery + "%");
            stmt.setString(2,"%" + teacherQuery + "%");
            stmt.setString(3, start);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                student = new Student();

                student.setStudentId(resultSet.getInt("student_defending"));
                student.setTypeStudent(resultSet.getInt("typeStudent"));
                student.setRegistration(resultSet.getString("student_internal_id"));

                student.setPersonId(resultSet.getInt("personId_student"));
                student.setName(resultSet.getString("name_student"));
                student.setSocialName(resultSet.getString("social_name_student"));
                student.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_student")));
                student.setCpf(resultSet.getString("cpf_student"));
                student.setRg(resultSet.getString("rg_student"));
                student.setEmail(resultSet.getString("email_student"));
                student.setPhoneNumber(resultSet.getString("phone_number_student"));

                teacher = new Teacher();

                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setRegister(resultSet.getString("teacher_internal_id"));

                teacher.setPersonId(resultSet.getInt("personId_teacher"));
                teacher.setName(resultSet.getString("name_teacher"));
                teacher.setSocialName(resultSet.getString("social_name_teacher"));
                teacher.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_teacher")));
                teacher.setCpf(resultSet.getString("cpf_teacher"));
                teacher.setRg(resultSet.getString("rg_teacher"));
                teacher.setEmail(resultSet.getString("email_teacher"));
                teacher.setPhoneNumber(resultSet.getString("phone_number_teacher"));

                defense = new Defense();

                defense.setDefenseId(resultSet.getInt("defense_id"));
                defense.setTypeDefense(resultSet.getInt("type_defense"));
                defense.setDefenseTitle(resultSet.getString("defense_title"));
                defense.setLocal(resultSet.getString("local"));
                defense.setFinalPontuation(resultSet.getDouble("final_pontuation"));
                defense.setStatus(resultSet.getInt("status"));
                defense.setObservation(resultSet.getString("observation"));
                defense.setDate((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("date")));
                defense.setStudentDefending(student);
                defense.setTeacherAdvisor(teacher);

                defenses.add(defense);

            }

            return defenses;
        }catch (SQLException | ParseException e) {
            System.out.println(e);

            return null;

        }
    }
    public ArrayList<Defense> selectAll(String studentQuery, String teacherQuery, String start, int status){

        ArrayList<Defense> defenses = new ArrayList<>();
        Teacher teacher;
        Student student;
        Defense defense;

        String sql = "SELECT d.defense_id, d.type_defense, d.defense_title, d.local, d.final_pontuation, d.status, d.observation, d.date, d.student_defending, " +
                "s.typeStudent, s.student_internal_id, p.personId as personId_student, p.name as name_student , p.social_name as social_name_student, p.birthday as birthday_student,   " +
                "p.cpf as cpf_student, p.rg as rg_student, p.email as email_student, p.phone_number as phone_number_student,  " +
                "t.teacher_id, t.teacher_internal_id,p2.personId as personId_teacher, p2.name as name_teacher, p2.social_name as social_name_teacher,  " +
                "p2.birthday as birthday_teacher, p2.cpf as cpf_teacher, p2.rg as rg_teacher, p2.email as email_teacher, p2.phone_number as phone_number_teacher " +
                "FROM defense d " +
                "JOIN students s ON d.student_defending = s.student_id " +
                "JOIN people p ON s.personId = p.personId " +
                "JOIN teachers t ON d.teacher_advisor = t.teacher_id " +
                "JOIN people p2 ON t.personId = p2.personId " +
                "WHERE p.name LIKE ? AND p2.name LIKE ? AND d.date >= ? AND d.status LIKE ? ORDER BY d.date";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1,"%" + studentQuery + "%");
            stmt.setString(2,"%" + teacherQuery + "%");
            stmt.setString(3, start);
            stmt.setInt(4, status);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                student = new Student();

                student.setStudentId(resultSet.getInt("student_defending"));
                student.setTypeStudent(resultSet.getInt("typeStudent"));
                student.setRegistration(resultSet.getString("student_internal_id"));

                student.setPersonId(resultSet.getInt("personId_student"));
                student.setName(resultSet.getString("name_student"));
                student.setSocialName(resultSet.getString("social_name_student"));
                student.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_student")));
                student.setCpf(resultSet.getString("cpf_student"));
                student.setRg(resultSet.getString("rg_student"));
                student.setEmail(resultSet.getString("email_student"));
                student.setPhoneNumber(resultSet.getString("phone_number_student"));

                teacher = new Teacher();

                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setRegister(resultSet.getString("teacher_internal_id"));

                teacher.setPersonId(resultSet.getInt("personId_teacher"));
                teacher.setName(resultSet.getString("name_teacher"));
                teacher.setSocialName(resultSet.getString("social_name_teacher"));
                teacher.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_teacher")));
                teacher.setCpf(resultSet.getString("cpf_teacher"));
                teacher.setRg(resultSet.getString("rg_teacher"));
                teacher.setEmail(resultSet.getString("email_teacher"));
                teacher.setPhoneNumber(resultSet.getString("phone_number_teacher"));

                defense = new Defense();

                defense.setDefenseId(resultSet.getInt("defense_id"));
                defense.setTypeDefense(resultSet.getInt("type_defense"));
                defense.setDefenseTitle(resultSet.getString("defense_title"));
                defense.setLocal(resultSet.getString("local"));
                defense.setFinalPontuation(resultSet.getDouble("final_pontuation"));
                defense.setStatus(resultSet.getInt("status"));
                defense.setObservation(resultSet.getString("observation"));
                defense.setDate((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("date")));
                defense.setStudentDefending(student);
                defense.setTeacherAdvisor(teacher);

                defenses.add(defense);

            }

            return defenses;
        }catch (SQLException | ParseException e) {
            System.out.println(e);

            return null;

        }
    }
    public ArrayList<Defense> selectAll(String studentQuery, String teacherQuery, String start, String end){

        ArrayList<Defense> defenses = new ArrayList<>();
        Teacher teacher;
        Student student;
        Defense defense;

        String sql = "SELECT d.defense_id, d.type_defense, d.defense_title, d.local, d.final_pontuation, d.status, d.observation, d.date, d.student_defending, " +
                "s.typeStudent, s.student_internal_id, p.personId as personId_student, p.name as name_student , p.social_name as social_name_student, p.birthday as birthday_student,   " +
                "p.cpf as cpf_student, p.rg as rg_student, p.email as email_student, p.phone_number as phone_number_student,  " +
                "t.teacher_id, t.teacher_internal_id,p2.personId as personId_teacher, p2.name as name_teacher, p2.social_name as social_name_teacher,  " +
                "p2.birthday as birthday_teacher, p2.cpf as cpf_teacher, p2.rg as rg_teacher, p2.email as email_teacher, p2.phone_number as phone_number_teacher " +
                "FROM defense d " +
                "JOIN students s ON d.student_defending = s.student_id " +
                "JOIN people p ON s.personId = p.personId " +
                "JOIN teachers t ON d.teacher_advisor = t.teacher_id " +
                "JOIN people p2 ON t.personId = p2.personId " +
                "WHERE p.name LIKE ? AND p2.name LIKE ? AND d.date >= ? AND d.date <= ? ORDER BY d.date";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1,"%" + studentQuery + "%");
            stmt.setString(2,"%" + teacherQuery + "%");
            stmt.setString(3, start);
            stmt.setString(4, end);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                student = new Student();

                student.setStudentId(resultSet.getInt("student_defending"));
                student.setTypeStudent(resultSet.getInt("typeStudent"));
                student.setRegistration(resultSet.getString("student_internal_id"));

                student.setPersonId(resultSet.getInt("personId_student"));
                student.setName(resultSet.getString("name_student"));
                student.setSocialName(resultSet.getString("social_name_student"));
                student.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_student")));
                student.setCpf(resultSet.getString("cpf_student"));
                student.setRg(resultSet.getString("rg_student"));
                student.setEmail(resultSet.getString("email_student"));
                student.setPhoneNumber(resultSet.getString("phone_number_student"));

                teacher = new Teacher();

                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setRegister(resultSet.getString("teacher_internal_id"));

                teacher.setPersonId(resultSet.getInt("personId_teacher"));
                teacher.setName(resultSet.getString("name_teacher"));
                teacher.setSocialName(resultSet.getString("social_name_teacher"));
                teacher.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_teacher")));
                teacher.setCpf(resultSet.getString("cpf_teacher"));
                teacher.setRg(resultSet.getString("rg_teacher"));
                teacher.setEmail(resultSet.getString("email_teacher"));
                teacher.setPhoneNumber(resultSet.getString("phone_number_teacher"));

                defense = new Defense();

                defense.setDefenseId(resultSet.getInt("defense_id"));
                defense.setTypeDefense(resultSet.getInt("type_defense"));
                defense.setDefenseTitle(resultSet.getString("defense_title"));
                defense.setLocal(resultSet.getString("local"));
                defense.setFinalPontuation(resultSet.getDouble("final_pontuation"));
                defense.setStatus(resultSet.getInt("status"));
                defense.setObservation(resultSet.getString("observation"));
                defense.setDate((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("date")));
                defense.setStudentDefending(student);
                defense.setTeacherAdvisor(teacher);

                defenses.add(defense);

            }

            return defenses;
        }catch (SQLException | ParseException e) {
            System.out.println(e);

            return null;

        }
    }
    public ArrayList<Defense> selectAll(String studentQuery, String teacherQuery, String start, String end, int status){

        ArrayList<Defense> defenses = new ArrayList<>();
        Teacher teacher;
        Student student;
        Defense defense;

        String sql = "SELECT d.defense_id, d.type_defense, d.defense_title, d.local, d.final_pontuation, d.status, d.observation, d.date, d.student_defending, " +
                "s.typeStudent, s.student_internal_id, p.personId as personId_student, p.name as name_student , p.social_name as social_name_student, p.birthday as birthday_student,   " +
                "p.cpf as cpf_student, p.rg as rg_student, p.email as email_student, p.phone_number as phone_number_student,  " +
                "t.teacher_id, t.teacher_internal_id,p2.personId as personId_teacher, p2.name as name_teacher, p2.social_name as social_name_teacher,  " +
                "p2.birthday as birthday_teacher, p2.cpf as cpf_teacher, p2.rg as rg_teacher, p2.email as email_teacher, p2.phone_number as phone_number_teacher " +
                "FROM defense d " +
                "JOIN students s ON d.student_defending = s.student_id " +
                "JOIN people p ON s.personId = p.personId " +
                "JOIN teachers t ON d.teacher_advisor = t.teacher_id " +
                "JOIN people p2 ON t.personId = p2.personId " +
                "WHERE p.name LIKE ? AND p2.name LIKE ? AND d.date >= ? AND d.date <= ? AND d.status LIKE ? ORDER BY d.date";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1,"%" + studentQuery + "%");
            stmt.setString(2,"%" + teacherQuery + "%");
            stmt.setString(3, start);
            stmt.setString(4, end);
            stmt.setInt(5, status);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                student = new Student();

                student.setStudentId(resultSet.getInt("student_defending"));
                student.setTypeStudent(resultSet.getInt("typeStudent"));
                student.setRegistration(resultSet.getString("student_internal_id"));

                student.setPersonId(resultSet.getInt("personId_student"));
                student.setName(resultSet.getString("name_student"));
                student.setSocialName(resultSet.getString("social_name_student"));
                student.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_student")));
                student.setCpf(resultSet.getString("cpf_student"));
                student.setRg(resultSet.getString("rg_student"));
                student.setEmail(resultSet.getString("email_student"));
                student.setPhoneNumber(resultSet.getString("phone_number_student"));

                teacher = new Teacher();

                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setRegister(resultSet.getString("teacher_internal_id"));

                teacher.setPersonId(resultSet.getInt("personId_teacher"));
                teacher.setName(resultSet.getString("name_teacher"));
                teacher.setSocialName(resultSet.getString("social_name_teacher"));
                teacher.setBirthday((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("birthday_teacher")));
                teacher.setCpf(resultSet.getString("cpf_teacher"));
                teacher.setRg(resultSet.getString("rg_teacher"));
                teacher.setEmail(resultSet.getString("email_teacher"));
                teacher.setPhoneNumber(resultSet.getString("phone_number_teacher"));

                defense = new Defense();

                defense.setDefenseId(resultSet.getInt("defense_id"));
                defense.setTypeDefense(resultSet.getInt("type_defense"));
                defense.setDefenseTitle(resultSet.getString("defense_title"));
                defense.setLocal(resultSet.getString("local"));
                defense.setFinalPontuation(resultSet.getDouble("final_pontuation"));
                defense.setStatus(resultSet.getInt("status"));
                defense.setObservation(resultSet.getString("observation"));
                defense.setDate((new SimpleDateFormat("yyyy-MM-dd")).parse(resultSet.getString("date")));
                defense.setStudentDefending(student);
                defense.setTeacherAdvisor(teacher);

                defenses.add(defense);

            }

            return defenses;
        }catch (SQLException | ParseException e) {
            System.out.println(e);

            return null;

        }
    }

    public static boolean delete(Defense defense){

        String sql = "DELETE FROM boardOfTeachers " +
                "WHERE defense_id = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, defense.getDefenseId());

            stmt.executeUpdate();

            sql = "DELETE FROM defense " +
                    "WHERE defense_id = ?";

            stmt.close();

            stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, defense.getDefenseId());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected != 0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
