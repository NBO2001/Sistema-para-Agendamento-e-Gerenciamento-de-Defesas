package view.editPerson;

import interfaces.VisiblePersonified;
import interfaces.Visibled;
import model.*;
import view.modals.EditStudent;
import view.modals.EditSystemUser;
import view.modals.EditTeacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.BiConsumer;
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
    private JButton btnCloser;
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


        btnCloser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(afterView != null) afterView.setVisible(true);

                EditPersonVariant02.this.setVisible(false);

            }
        });
    }


    @Override
    public void setState(Person person1) {
        setPerson(person1);

        if(person1 != null){
            ArrayList<Teacher> teachers = TeacherManager.selectAll(getPerson().getPersonId());

            for(Teacher teacher: teachers){
                BiFunction<Person, Boolean, Boolean> actionDelete = (person, flag) -> TeacherManager.delete(person);


                BiConsumer<Person, Boolean> actionEdit = (person, flag) -> {
                    new EditTeacher(person).setVisible(true);
                };

                this.jPanelForm.add(createTableRow(teacher.getRegister(), "Professor", teacher,
                        actionEdit,"Edit", "#50D4F2", actionDelete,"Delete", "#F78484"));
            }

            ArrayList<Student> students = StudentManager.selectAll(getPerson().getPersonId());
            for(Student student: students){

                BiFunction<Person, Boolean, Boolean> actionDelete = (person, flag) -> StudentManager.delete(person);

                BiConsumer<Person, Boolean> actionEdit = (person, flag) -> {
                    new EditStudent(person).setVisible(true);
                };

                this.jPanelForm.add(createTableRow(student.getRegistration(), Student.typeIntToString(student.getTypeStudent()), student,
                        actionEdit,"Edit", "#50D4F2", actionDelete,"Delete", "#F78484"));
            }

            ArrayList<SystemUser> systemUsers = SystemUserManager.selectAll(getPerson().getPersonId());
            for(SystemUser systemUser: systemUsers){
                BiFunction<Person, Boolean, Boolean> actionDelete = (person, flag) -> SystemUserManager.delete(person);

                BiConsumer<Person, Boolean> actionEdit = (person, flag) -> {
                    new EditSystemUser(person).setVisible(true);
                };

                this.jPanelForm.add(createTableRow(Integer.toString(systemUser.getSystemUserId()), "Usuário", systemUser,
                        actionEdit,"Edit", "#50D4F2", actionDelete,"Delete", "#F78484"));
            }
        }
    }

    private void clening(){
        for (Component component : jPanelForm.getComponents()) {
            jPanelForm.remove(component);
        }

        this.jPanelForm.add(createHead("Matricula", "Descrição","Action", "Action"));

    }

    private void initialize(){

        this.jFrame = new JFrame("Editar Cadastro");
        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.jPanelForm.setLayout(new BoxLayout(this.jPanelForm, BoxLayout.Y_AXIS));
        this.jPanelForm.add(createHead("Matricula", "Descrição","Action", "Action"));


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
                                  BiConsumer<Person, Boolean> action_fisrt, String label_first, String color_first,
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
                action_fisrt.accept(person1, true);

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

                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    // Perform delete operation
                    boolean retuned_function = action_sec.apply(person1, true);

                    if(retuned_function){

                        JOptionPane.showMessageDialog(null,"Excluido com sucesso!!");
                        EditPersonVariant02.this.clening();
                        EditPersonVariant02.this.setState(person1);
                    }else{
                        JOptionPane.showMessageDialog(null,"Error ao tentar!","Erro!", JOptionPane.ERROR_MESSAGE);
                    }

                }


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
