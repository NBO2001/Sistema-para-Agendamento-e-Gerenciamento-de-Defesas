package view.login;

import controller.Authentication;
import controller.ControllingManager;
import interfaces.Visibled;
import utils.Utils;
import view.home.Home;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
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
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnLogin.setBackground(Color.decode("#3763DB"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnLogin.setBackground(Color.decode("#49A3F2"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);

                loginExe();

            }
        });

    }

    private void loginExe() {
        if((textFieldUsername.getText().length() == 0) || (new String(passwordField1.getPassword()).length() == 0 ) ){

            JOptionPane.showMessageDialog(null, "Please type your username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String userName = Utils.encryptTolkienName(textFieldUsername.getText());

        // Modificar depois, se der tempo, adicionar seguranca nisso.
        String password = Utils.encryptTolkienName(new String(passwordField1.getPassword()));


        if(Authentication.authenticate(userName, password,Login.this, new Home(), controllingManager )){
            setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Login(){
        this(null);
    }

    private void initialize(){
        this.jFrame = new JFrame("Login");

        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setMinimumSize(new Dimension(400,600));
        this.jFrame.setPreferredSize(new Dimension(800,800));
        this.jFrame.setLocationRelativeTo(null);

        textFieldUsername.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        textFieldUsername.setBorder(new EmptyBorder(5, 5, 5,5));

        passwordField1.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        passwordField1.setBorder(new EmptyBorder(5, 5, 5,5));

        iconApp.setBackground(Color.decode("#49A3F2"));

        this.jFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = jFrame.getWidth();

                if (width < 401) {
                    iconApp.setVisible(false);
                } else {
                    iconApp.setVisible(true);
                }
            }
        });

        textFieldUsername.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField1.requestFocusInWindow();
            }
        });

        passwordField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginExe();
            }
        });

        this.jFrame.add(panel1);

    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }


}
