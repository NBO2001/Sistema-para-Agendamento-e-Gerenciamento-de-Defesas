package view.modals;

import interfaces.Visibled;
import model.*;

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

        jPanel.setSize(900,900);
        jPanel.setPreferredSize(new Dimension(1500,900));

        JPanel body = new JPanel();
        JPanel footer = new JPanel();

        jPanel.add(ModalViewDefense.topView());
        jPanel.add(body);
        jPanel.add(footer);

        jFrame.add(jPanel);



    }

    private static JPanel topView(){

        Student student =  new Student();
        student.setName("Natanael Bezerra");

        Teacher teacher = new Teacher();
        teacher.setName("Edleno Moura");


        JPanel topView = new JPanel();
        topView.setLayout(new BoxLayout(topView,BoxLayout.Y_AXIS));
        topView.setPreferredSize(new Dimension(1500,400));
        topView.setBackground(Color.decode("#d7d414"));

        JLabel jLabel = new JLabel("Informações");
        JPanel title = new JPanel();
        title.setMaximumSize(new Dimension(1920, 50));
        title.add(jLabel);

        JPanel student_teacher = new JPanel();
        student_teacher.setLayout(new FlowLayout());
        student_teacher.setPreferredSize(new Dimension(500,80));

        JPanel jPanel3 = new JPanel();
        jPanel3.setMaximumSize(new Dimension(100,1080));

        student_teacher.add(ModalViewDefense.createTopContainer("Aluno",student ));
        student_teacher.add(jPanel3);
        student_teacher.add(ModalViewDefense.createTopContainer("Professor",teacher ));

        topView.setBackground(Color.decode("#1c8c17"));


        topView.add(title);
        topView.add(student_teacher);

        return topView;

    }

    private static JPanel createTopContainer(String title, Person person){

        JPanel jPanel5 = new JPanel();
        jPanel5.setLayout(new BoxLayout(jPanel5,BoxLayout.Y_AXIS));


        JPanel infoSuperior = new JPanel();
        infoSuperior.setLayout(new FlowLayout());
        infoSuperior.setMaximumSize(new Dimension(1500,50));

        JPanel infoInferior = new JPanel();

        infoInferior.setMaximumSize(new Dimension(1500,150));


        JLabel jLabel1 = new JLabel(title);
        infoSuperior.add(jLabel1);

        JTextField student_name = new JTextField();
        student_name.setFocusable(false);
        student_name.setText(person.getName());
        student_name.setFont(new Font("Ubuntu", Font.BOLD, 18));
        student_name.setEditable(false);
        student_name.setBackground( Color.decode("#ffffff"));
        student_name.setSize(new Dimension(500,80));
        student_name.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        infoInferior.add(student_name);

        JPanel harf = new JPanel();
        infoInferior.add(harf);

        JButton jButton = new JButton("Edit");
        jButton.setSize(new Dimension(80,80));
        infoInferior.add(jButton);

        jPanel5.add(infoSuperior);
        jPanel5.add(infoInferior);

        return jPanel5;

    }
    @Override
    public void setVisible(boolean value) {

        jFrame.setVisible(value);

    }
}
