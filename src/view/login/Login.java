package view.login;

import controller.Authentication;
import controller.ControllingManager;
import interfaces.Visibled;
import utils.Utils;
import view.home.Home;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class Login implements Visibled {

    private ControllingManager controllingManager;
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

    public Login(ControllingManager controllingManager){

        this.controllingManager = controllingManager;

        initialize();
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String userName = Utils.encryptTolkienName(textFieldUsername.getText());

                // Modificar depois, se der tempo, adicionar seguranca nisso.
                String password = Utils.encryptTolkienName(new String(passwordField1.getPassword()));


                if(Authentication.authenticate(userName, password,Login.this, new Home(), controllingManager )){
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }
    public Login(){
        this(null);
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
