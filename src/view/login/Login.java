package view.login;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame{

    private JFrame jFrame;
    private JPanel panel1;
    private JPanel imageSysten;
    private JPanel loginFields;
    private JPanel img_icon;
    private JPanel jTop;
    private JPanel loginAndPasswd;
    private JPanel JPanelLogin;
    private JPanel JPanelPasswd;
    private JPanel JPanelTxt;
    private JPanel JPanelUs;
    private JTextField fieldUsername;
    private JPanel JPanelLabel;
    private JPanel JPanelPsw;
    private JPasswordField fieldPassword;
    private JButton btnLogin;
    private JPanel JPanelBtn;

    public Login(){
        this.jFrame = new JFrame();
        this.jFrame.setContentPane(this.panel1);
        this.jFrame.setTitle("Login");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(Login.this, "Warning: Invalid input", "Error", JOptionPane.WARNING_MESSAGE);

            }
        });
    }


    public void show(){

        this.jFrame.setVisible(true);

    }

}
