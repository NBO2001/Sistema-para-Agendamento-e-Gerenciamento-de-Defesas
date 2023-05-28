package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DefenseManager extends ConnectionBase{

    public static boolean insert(Defense defense){

        String sql = "INSERT INTO defense (type_defense, defense_title, date, local, teacher_advisor, student_defending, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

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
}
