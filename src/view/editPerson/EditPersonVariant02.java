package view.editPerson;

import interfaces.VisiblePersonified;
import interfaces.Visibled;
import model.*;
import view.modalfindteacher.ModalFindTeacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BiFunction;

public class EditPersonVariant02 implements VisiblePersonified {
    private JFrame jFrame;
    private JPanel panel1;
    private JButton btnMenu;
    private JPanel jPanelMenu;
    private JButton btnCadStu;
    private JButton btnCadTeac;
    private JButton btnCadUser;
    private JButton btnCadDefense;
    private JButton btnAlterCad;
    private JButton btnRelatorio;
    private JPanel jPanelHome;
    private JPanel jPanelForm;
    private Person person;

    public EditPersonVariant02(Visibled afterView){
        initialize();

        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());

            }
        });

//        BiFunction<Person, Boolean, Boolean> action = (person, flag) -> TeacherManager.delete(person);
//        Teacher teacher = new Teacher();
//        this.jPanelForm.add(createTableRow("Natanael", "2514", teacher,action,"Edit", "#50D4F2", action,"Delete", "#F78484"));

    }


    @Override
    public void setState(Person person) {
        setPerson(person);
    }

    private void initialize(){

        this.jFrame = new JFrame("Editar Cadastro");
        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.jPanelForm.setLayout(new BoxLayout(this.jPanelForm, BoxLayout.Y_AXIS));
        this.jPanelForm.add(createHead("Nome", "Matricula","Action", "Action"));

//        this.jPanelBotttom.setVisible(false);

        this.jFrame.add(panel1);
    }

    @Override
    public void setVisible(boolean value) {
        jFrame.setVisible(value);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    private static JPanel createHead(String nameLabelA, String enrollmentLabelA, String bottom, String labelTwo) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel nameLabel = new JLabel(nameLabelA);
        JLabel enrollmentLabel = new JLabel(enrollmentLabelA);
        JLabel actionLabel = new JLabel(bottom);
        JLabel actionLabelTwo = new JLabel(labelTwo);

        nameLabel.setPreferredSize(new Dimension(150, 50));
        enrollmentLabel.setPreferredSize(new Dimension(150, 50));
        actionLabel.setPreferredSize(new Dimension(150, 50));
        actionLabelTwo.setPreferredSize(new Dimension(150, 50));

        rowPanel.add(nameLabel);
        rowPanel.add(enrollmentLabel);
        rowPanel.add(actionLabel);
        rowPanel.add(actionLabelTwo);

        return rowPanel;
    }

    private JPanel createTableRow(String name, String enrollment,Person person,
                                  BiFunction<Person, Boolean, Boolean> action_fisrt, String label_first, String color_first,
                                  BiFunction<Person, Boolean, Boolean> action_sec, String label_sec, String color_sec) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setPreferredSize(new Dimension(150, 50));
        JLabel enrollmentLabel = new JLabel(enrollment);
        enrollmentLabel.setPreferredSize(new Dimension(150, 50));

        JButton actionButton = new JButton(label_first);
        actionButton.setPreferredSize(new Dimension(150, 50));
        actionButton.setBackground(Color.decode(color_first));
        actionButton.setForeground(Color.BLACK);
        actionButton.setFocusPainted(false);
        actionButton.setFont(actionButton.getFont().deriveFont(Font.BOLD));
        actionButton.putClientProperty("person", person);
        actionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Person person1 = (Person) actionButton.getClientProperty("person");
                action_fisrt.apply(person1, true);

            }
        });
        actionButton.setPreferredSize(new Dimension(150, 50));


        JButton actionButtonSec = new JButton(label_sec);
        actionButtonSec.setPreferredSize(new Dimension(150, 50));
        actionButtonSec.setBackground(Color.decode(color_sec));
        actionButtonSec.setForeground(Color.BLACK);
        actionButtonSec.setFocusPainted(false);
        actionButtonSec.setFont(actionButton.getFont().deriveFont(Font.BOLD));
        actionButtonSec.putClientProperty("person", person);
        actionButtonSec.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Person person1 = (Person) actionButtonSec.getClientProperty("person");
                action_sec.apply(person1, true);

            }
        });
        actionButtonSec.setPreferredSize(new Dimension(150, 50));


        rowPanel.add(nameLabel);
        rowPanel.add(enrollmentLabel);
        rowPanel.add(actionButton);
        rowPanel.add(actionButtonSec);


        return rowPanel;
    }
}
