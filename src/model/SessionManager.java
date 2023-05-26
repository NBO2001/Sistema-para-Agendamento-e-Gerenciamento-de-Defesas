package model;

import utils.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SessionManager extends ConnectionBase{

    public SessionManager(){
        super();
    }

    public Session login(String userName, String password){

        Session session = null;
        int systemUserId;
        Boolean accountStatus;
        String tolkein;
        int personId;

        String sql = "SELECT system_user_id, account_status, personId FROM system_users WHERE login = ? AND passwd = ? AND account_status = true";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, userName);
            stmt.setString(2, password);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){

                systemUserId = resultSet.getInt("system_user_id");
                accountStatus = resultSet.getBoolean("account_status");
                personId = resultSet.getInt("personId");

            }else{
                return null;
            }

            sql = "SELECT tolking, experied_in, user_id FROM register_login WHERE user_id = ? ORDER BY created_at DESC LIMIT 1";

            stmt.close();
            resultSet.close();
            stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, systemUserId);

            resultSet = stmt.executeQuery();

            String tolking;

            Date experied_in;

            if(resultSet.next()){

                String dateString = resultSet.getString("experied_in");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                experied_in = dateFormat.parse(dateString);

                return  new Session(resultSet.getString("tolking"), experied_in, systemUserId);
            }else{
                stmt.close();
                resultSet.close();

                LocalDate experiedInValue = LocalDate.now().plusDays(1);

                // Create a formatted date string
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedExperiedIn = experiedInValue.format(formatter);

                ZonedDateTime zonedDateTime = experiedInValue.atStartOfDay(ZoneId.systemDefault());
                Instant instant = zonedDateTime.toInstant();
                experied_in = Date.from(instant);


                sql = "INSERT INTO register_login (tolking, experied_in, user_id) VALUES (?,?,?)";
                LocalDateTime currentDateTime = LocalDateTime.now();

                stmt = conexao.prepareStatement(sql);

                tolkein = Utils.encryptTolkienName(currentDateTime.toString() + systemUserId );
                stmt.setString(1, tolkein);
                stmt.setString(2,formattedExperiedIn);
                stmt.setInt(3, systemUserId);

                int valuesInserted = stmt.executeUpdate();

                stmt.close();

                if(valuesInserted != 1) return null;

                return  new Session(tolkein, experied_in, systemUserId);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
