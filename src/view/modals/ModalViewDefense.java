package view.modals;

import interfaces.VisiblePersonified;
import interfaces.Visibled;
import model.Defense;

import javax.swing.*;
import java.awt.*;

public class ModalViewDefense implements Visibled {

    private JFrame jFrame;
    private Defense defense;

    public ModalViewDefense(Defense defense){
        this.defense = defense;
        initialize();
    }

    private void initialize(){

        jFrame = new JFrame();
        jFrame.setMaximumSize(new Dimension(1920,1080));
        jFrame.setMinimumSize(new Dimension(500,500));
        jFrame.setPreferredSize(new Dimension(1000,1000));
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));

        JPanel topView = new JPanel();
        topView.setLayout(new BoxLayout(topView,BoxLayout.Y_AXIS));

        JLabel jLabel = new JLabel("Informações");
        JPanel title = new JPanel();
        title.add(jLabel);

        JPanel stundent_teacher = new JPanel();
        stundent_teacher.setLayout(new BoxLayout(stundent_teacher,BoxLayout.X_AXIS));

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));

        JLabel jLabel1 = new JLabel("Nome");

        JTextField student_name = new JTextField();
        student_name.setSize(new Dimension(500,80));
        student_name.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jPanel1.add(jLabel1);
        jPanel1.add(student_name);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));

        JLabel jLabel2 = new JLabel("Professor");

        JTextField teacher_name = new JTextField();
        teacher_name.setSize(new Dimension(500,80));
        teacher_name.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jPanel2.add(jLabel2);
        jPanel2.add(teacher_name);


        JPanel jPanel3 = new JPanel();

        stundent_teacher.add(jPanel1);
        stundent_teacher.add(jPanel3);
        stundent_teacher.add(jPanel2);

        stundent_teacher.setMaximumSize(new Dimension(1000,600));

        topView.add(title);
        topView.add(stundent_teacher);


        JPanel body = new JPanel();
        JPanel flooter = new JPanel();

        jPanel.add(topView);
        jPanel.add(body);
        jPanel.add(flooter);

        jFrame.add(jPanel);



    }
    @Override
    public void setVisible(boolean value) {

        jFrame.setVisible(value);

    }
}
