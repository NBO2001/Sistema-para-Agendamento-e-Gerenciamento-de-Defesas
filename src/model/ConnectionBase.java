package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBase {

    private static String url = "jdbc:sqlite:" + ConstatesPath.PATH + ConstatesPath.DATABASE;
    protected static Connection conexao;

    public ConnectionBase(){
        if(conexao==null) conecta();
    }

    private boolean conecta() {
        try {
            this.conexao =  DriverManager.getConnection(url);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean desconecta() {
        try {
            conexao.close();
            return true;
        } catch (SQLException e) { return false; }
    }

    public boolean statusConnection(){
        return conexao != null;
    }

}
