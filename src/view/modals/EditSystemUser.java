package view.modals;

import interfaces.Visibled;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditSystemUser implements Visibled {
    private JFrame jFrame;
    private JPanel tablePanel;

    private JButton btnAlter;
    private JButton btnCloser;
    private JTextField txtSeach;
    private JTextField textFieldMatricula;

    private Teacher teacher;

    public EditSystemUser(Person person){
        initialize(person);

        btnCloser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EditSystemUser.this.setVisible(false);
            }
        });

        btnAlter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(person != null){
                    SystemUser systemUser = (SystemUser) person;
                    systemUser.setLogin(textFieldMatricula.getText());

                    SystemUserManager.update(systemUser);

                    EditSystemUser.this.setVisible(false);
                }
            }
        });
    }

    private void initialize(Person person){

        jFrame = new JFrame();
        jFrame.setSize(800, 800);
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocale(null);

        JPanel mainConteinner = new JPanel();
        mainConteinner.setLayout( new BoxLayout(mainConteinner, BoxLayout.Y_AXIS));
        mainConteinner.setSize(800,300);


        JPanel content = new JPanel();
        content.setLayout( new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.decode("#F78484"));
        content.setSize(800,200);
        content.setMaximumSize(content.getSize());


        Label label = new Label("Login");
        textFieldMatricula = new JTextField();
        textFieldMatricula.setEditable(true);

        textFieldMatricula.setSize(150,50);
        textFieldMatricula.setMaximumSize(textFieldMatricula.getSize());

        content.add(label);
        content.add(textFieldMatricula);

        JPanel flooter = new JPanel();
        flooter.setLayout( new FlowLayout(FlowLayout.LEFT));

        btnCloser = new JButton("Fechar");

        btnAlter = new JButton("Alterar");

        flooter.add(btnAlter);
        flooter.add(btnCloser);

        mainConteinner.add(content);
        mainConteinner.add(flooter);

        jFrame.add(mainConteinner);


    }

    @Override
    public void setVisible(boolean value) {
        jFrame.setVisible(value);
    }
}
