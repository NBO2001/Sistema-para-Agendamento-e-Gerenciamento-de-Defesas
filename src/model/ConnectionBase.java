package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBase {

//    private static String url = "jdbc:sqlite:/home/natanael/Sistema-para-Agendamento-e-Gerenciamento-de-Defesas/base/base.sqlite3";
private static String url = "jdbc:sqlite:/home/natanael/IdeaProjects/Sistema-para-Agendamento-e-Gerenciamento-de-Defesas/base/base.sqlite3";
    protected static Connection conexao = null;

    public ConnectionBase(){
        if(conexao==null) conecta();
    }

    private static boolean conecta() {
        try {

            conexao =  DriverManager.getConnection(url);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false; }
    }

    public static boolean desconecta() {
        try {
            conexao.close();
            return true;
        } catch (SQLException e) { return false; }
    }

}
