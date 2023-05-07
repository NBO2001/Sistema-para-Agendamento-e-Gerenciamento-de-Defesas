package view;

import javax.swing.*;

public class Login extends JFrame{

    private JFrame jFrame;
    private JPanel panel1;
    private JButton button1;
    private JTextField textField1;

    public Login(){
        this.jFrame = new JFrame();
    }

    public void show(){
        this.jFrame.setContentPane(this.panel1);
        this.jFrame.setTitle("Login");
        this.jFrame.setBounds(600,600,150,150);
        this.jFrame.setVisible(true);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
