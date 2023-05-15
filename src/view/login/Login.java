package view.login;

import javax.swing.*;

public class Login {
    private JFrame jFrame;
    private JPanel panel1;
    private JPanel iconApp;
    private JPanel conteinnerLogin;
    private JPanel jPanelUserLogin;
    private JPanel connLogin;
    private JPanel connPass;
    private JTextField textFieldUsername;
    private JPasswordField passwordField1;
    private JButton btnLogin;

    public Login(){
        initialize();
    }

    private void initialize(){
        this.jFrame = new JFrame("Login");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.jFrame.add(panel1);

    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }
}
