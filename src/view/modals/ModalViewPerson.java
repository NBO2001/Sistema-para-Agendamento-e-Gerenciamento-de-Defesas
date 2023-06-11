package view.modals;

import interfaces.Visibled;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ModalViewPerson implements Visibled {

    private JFrame jFrame;
    private Person person;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;

    private JTextField txtNamePerson;
    private JTextField txtSocialName;
    private JTextField txtPersonCpf;
    private JTextField txtBirth;
    private JTextField txtPersonRg;
    private JTextField txtPersonEmail;
    private JTextField txtPersonPhone;
    private JButton btnDeletePerson;
    private JButton btnEnableEdition;
    private JButton btnUpdatePerson;
    private JButton btnClosePersonWithoutSave;

    private Person oldPerson;
    private Visibled afterWindow;
    public ModalViewPerson(Visibled afterWindow,Person person){
        this.afterWindow = afterWindow;
        this.person = person;
        if(person != null){
            this.students = StudentManager.selectAll(person.getPersonId());
            if(this.students.size() == 0) this.students = null;
            this.teachers = TeacherManager.selectAll(person.getPersonId());
            if(this.teachers.size() == 0) this.teachers = null;
        }

        initialize();
    }

    private void initialize(){

        jFrame = new JFrame("Person View");
        jFrame.setMaximumSize(new Dimension(1000,900));
        jFrame.setMinimumSize(new Dimension(850,600));
        jFrame.setPreferredSize(new Dimension(1000,900));
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBackground(Color.decode("#FFFFFF"));
        jFrame.setLocationRelativeTo(null);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));

        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        jPanel.setSize(1000,900);

        JPanel infoPersonJPanel     = infoPerson();
        JPanel infoStudentJPanel    = infoStudent();
        JPanel infoTeacherJPanel    = infoTeacher();


        if(this.students == null) infoStudentJPanel.setVisible(false);
        if(this.teachers == null) infoTeacherJPanel.setVisible(false);

        JScrollPane scrollPane = new JScrollPane(jPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(infoPersonJPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(infoStudentJPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(infoTeacherJPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(infoPersonJPanel)
                        .addComponent(infoStudentJPanel)
                        .addComponent(infoTeacherJPanel)
        );


        jFrame.add(scrollPane);

    }

    private JPanel infoTeacher() {
        JPanel jPanel = new JPanel();

        jPanel.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel label = new JLabel("Informações Profissionais");
        label.setFont(new Font("Ubuntu", Font.BOLD, 40));
        label.setBackground(Color.decode("#FFFFFF"));

        JPanel teacherRegisters = new JPanel();
        teacherRegisters.setLayout(new BoxLayout(teacherRegisters, BoxLayout.Y_AXIS));

        if(teachers != null){

            for(Teacher teacher: teachers){
                teacherRegisters.add(rowComponentTeacher(teacher));
            }

        }


        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(teacherRegisters, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(teacherRegisters)
        );

        return jPanel;

    }

    private JPanel rowComponentStudent(Student student){

        JPanel jPanel = new JPanel();

        jPanel.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        ComponentElement mat = componentElementLine("Matrícula");
        ComponentElement typeStudent = componentElementLine("Descrição");

        mat.textField.setText(student.getRegistration());
        typeStudent.textField.setText(Student.typeIntToString(student.getTypeStudent()));

        ComponentElementButton componentElementButton = componentElementButton("Delete", 150,"#F01001" );

        JButton deleteStudent = componentElementButton.jButton;
        deleteStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {

                    if(StudentManager.delete(student)){
                        JOptionPane.showMessageDialog(null, "Removido com sucesso!!");
                        jPanel.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null, "Aluno com registro de defesa", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                deleteStudent.setBackground(Color.decode("#BD0D00"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                deleteStudent.setBackground(Color.decode("#F01001"));
            }
        });

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(mat.jPanel)
                                        .addComponent(typeStudent.jPanel)
                                        .addComponent(componentElementButton.jPanel)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(mat.jPanel)
                                        .addComponent(typeStudent.jPanel)
                                        .addComponent(componentElementButton.jPanel)
                        )
        );

        return jPanel;
    }

    private JPanel rowComponentTeacher(Teacher teacher){

        JPanel jPanel = new JPanel();

        jPanel.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        ComponentElement mat = componentElementLine("Matrícula");

        mat.textField.setText(teacher.getRegister());

        ComponentElementButton componentElementButton = componentElementButton("Delete", 150,"#F01001" );

        JButton deleteStudent = componentElementButton.jButton;
        deleteStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {

                    if(TeacherManager.delete(teacher)){
                        JOptionPane.showMessageDialog(null, "Removido com sucesso!!");
                        jPanel.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null, "Professor como orientador ou em banca", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                deleteStudent.setBackground(Color.decode("#BD0D00"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                deleteStudent.setBackground(Color.decode("#F01001"));
            }
        });

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(mat.jPanel)
                                        .addComponent(componentElementButton.jPanel)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(mat.jPanel)
                                        .addComponent(componentElementButton.jPanel)
                        )
        );

        return jPanel;
    }

    private JPanel infoStudent() {
        JPanel jPanel = new JPanel();

        jPanel.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel label = new JLabel("Informações Estudantis");
        label.setFont(new Font("Ubuntu", Font.BOLD, 40));
        label.setBackground(Color.decode("#FFFFFF"));

        JPanel studentsRegisters = new JPanel();
        studentsRegisters.setLayout(new BoxLayout(studentsRegisters, BoxLayout.Y_AXIS));

        if(students != null){

            for(Student student1: students){
                studentsRegisters.add(rowComponentStudent(student1));
            }

        }


        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(studentsRegisters, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(studentsRegisters)
        );

        return jPanel;
    }

    private ComponentElement componentElementLine(String labelValue){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        ComponentElement componentElement = new ComponentElement();

        JLabel jLabel = new JLabel(labelValue);
        jLabel.setFont(new Font("Ubuntu", Font.BOLD, 15));
        JTextField jTextField = new JTextField();
        jTextField.setMinimumSize(new Dimension(300, 0));
        jTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        jTextField.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        jTextField.setEditable(false);
        jTextField.setBackground( Color.decode("#D9D9D9"));
        jTextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        int widthComponents = 400;
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel)
                        .addComponent(jTextField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel)
                        .addComponent(jTextField, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        );

        componentElement.jPanel = jPanel;
        componentElement.textField = jTextField;

        return componentElement;

    }

    private ComponentElement componentElementLine(String labelValue, int minimize){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        ComponentElement componentElement = new ComponentElement();

        JLabel jLabel = new JLabel(labelValue);
        jLabel.setFont(new Font("Ubuntu", Font.BOLD, 15));
        JTextField jTextField = new JTextField();
        jTextField.setMinimumSize(new Dimension(minimize, 0));
        jTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        jTextField.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        jTextField.setEditable(false);
        jTextField.setBackground( Color.decode("#D9D9D9"));
        jTextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel)
                        .addComponent(jTextField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel)
                        .addComponent(jTextField, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        );

        componentElement.jPanel = jPanel;
        componentElement.textField = jTextField;

        return componentElement;

    }

    private ComponentElementButton componentElementButton(String labelValue, int minimize, String color){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        ComponentElementButton componentElementButton = new ComponentElementButton();

        JLabel jLabel = new JLabel("Action");
        jLabel.setFont(new Font("Ubuntu", Font.BOLD, 15));

        JButton deleteStudent             = new JButton(labelValue);
        deleteStudent.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        deleteStudent.setBackground(Color.decode(color));
        deleteStudent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        deleteStudent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel)
                        .addComponent(deleteStudent, GroupLayout.PREFERRED_SIZE, minimize, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel)
                        .addComponent(deleteStudent, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        );

        componentElementButton.jPanel = jPanel;
        componentElementButton.jButton = deleteStudent;

        return componentElementButton;

    }


    public JPanel infoPerson(){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel label = new JLabel("Informações Pessoais");
        label.setFont(new Font("Ubuntu", Font.BOLD, 40));
        label.setBackground(Color.decode("#FFFFFF"));

        ComponentElement namePersonComponent = componentElementLine("Person Name");
        txtNamePerson = namePersonComponent.textField;
        txtNamePerson.setText(person.getName());

        ComponentElement socialNamePersonComponent = componentElementLine("Person Social Name");
        txtSocialName = socialNamePersonComponent.textField;
        txtSocialName.setText(person.getSocialName());

        ComponentElement cpfPersonComponent = componentElementLine("CPF");
        txtPersonCpf = cpfPersonComponent.textField;
        txtPersonCpf.setText(person.getCpf());

        ComponentElement rgPersonComponent = componentElementLine("RG");
        txtPersonRg = rgPersonComponent.textField;
        txtPersonRg.setText(person.getRg());

        ComponentElement birthPersonComponent = componentElementLine("Data de Nascimento");
        txtBirth = birthPersonComponent.textField;
        txtBirth.setText(Utils.dateForBr(person.getBirthday()));

        ComponentElement emailPersonComponent = componentElementLine("Email");
        txtPersonEmail = emailPersonComponent.textField;
        txtPersonEmail.setText(person.getEmail());

        ComponentElement phonePersonComponent = componentElementLine("Phone");
        txtPersonPhone = phonePersonComponent.textField;
        txtPersonPhone.setText(person.getPhoneNumber());


        btnDeletePerson             = new JButton("Delete");
        btnDeletePerson.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        btnDeletePerson.setBackground(Color.decode("#F01001"));
        btnDeletePerson.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnDeletePerson.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDeletePerson.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    if(People.delete(person)){
                        JOptionPane.showMessageDialog(null, "Removido com sucesso!!");
                        if(afterWindow != null) afterWindow.setVisible(true);
                        ModalViewPerson.this.jFrame.dispose();

                    }else{
                        JOptionPane.showMessageDialog(null, "Pessoa com cadastro de professor ou aluno.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnDeletePerson.setBackground(Color.decode("#BD0D00"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnDeletePerson.setBackground(Color.decode("#F01001"));
            }
        });
        btnEnableEdition            = new JButton("Editar");
        btnEnableEdition.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        btnEnableEdition.setBackground(Color.decode("#49A3F2"));
        btnEnableEdition.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEnableEdition.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        btnEnableEdition.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                oldPerson = new Person();
                oldPerson.clone(person);

                txtNamePerson.setEditable(true);
                txtSocialName.setEditable(true);
                txtPersonCpf.setEditable(true);
                txtBirth.setEditable(true);
                txtPersonRg.setEditable(true);
                txtPersonEmail.setEditable(true);
                txtPersonPhone.setEditable(true);

                btnEnableEdition.setVisible(false);
                btnDeletePerson.setVisible(false);
                btnUpdatePerson.setVisible(true);
                btnClosePersonWithoutSave.setVisible(true);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnEnableEdition.setBackground(Color.decode("#93C7F4"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnEnableEdition.setBackground(Color.decode("#49A3F2"));
            }
        });

        btnUpdatePerson             = new JButton("Savar");
        btnUpdatePerson.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        btnUpdatePerson.setBackground(Color.decode("#0ABFBF"));
        btnUpdatePerson.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnUpdatePerson.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        btnUpdatePerson.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                person.setName(txtNamePerson.getText());
                person.setSocialName(txtSocialName.getText());
                person.setCpf(txtPersonCpf.getText());
                person.setBirthday(Utils.strToDateBr(txtBirth.getText()));
                person.setRg(txtPersonRg.getText());
                person.setEmail(txtPersonEmail.getText());
                person.setPhoneNumber(txtPersonPhone.getText());

                if(person != null && person.isValidValues()){

                    if(People.update(person)){
                        JOptionPane.showMessageDialog(null, "Sucesso!");
                        txtNamePerson.setText(person.getName());
                        txtSocialName.setText(person.getSocialName());
                        txtPersonCpf.setText(person.getCpf());
                        txtBirth.setText(Utils.dateForBr(person.getBirthday()));
                        txtPersonRg.setText(person.getRg());
                        txtPersonEmail.setText(person.getEmail());
                        txtPersonPhone.setText(person.getPhoneNumber());

                        txtNamePerson.setEditable(false);
                        txtSocialName.setEditable(false);
                        txtPersonCpf.setEditable(false);
                        txtBirth.setEditable(false);
                        txtPersonRg.setEditable(false);
                        txtPersonEmail.setEditable(false);
                        txtPersonPhone.setEditable(false);

                        btnEnableEdition.setVisible(true);
                        btnDeletePerson.setVisible(true);
                        btnUpdatePerson.setVisible(false);
                        btnClosePersonWithoutSave.setVisible(false);

                    }else {
                        JOptionPane.showMessageDialog(null, "Erro ao tentar", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnUpdatePerson.setBackground(Color.decode("#0061CC"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnUpdatePerson.setBackground(Color.decode("#0ABFBF"));
            }
        });

        btnClosePersonWithoutSave   = new JButton("Descartar Alterações");
        btnClosePersonWithoutSave.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        btnClosePersonWithoutSave.setBackground(Color.decode("#D7F205"));
        btnClosePersonWithoutSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnClosePersonWithoutSave.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        btnClosePersonWithoutSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                person.clone(oldPerson);

                txtNamePerson.setText(person.getName());
                txtSocialName.setText(person.getSocialName());
                txtPersonCpf.setText(person.getCpf());
                txtBirth.setText(Utils.dateForBr(person.getBirthday()));
                txtPersonRg.setText(person.getRg());
                txtPersonEmail.setText(person.getEmail());
                txtPersonPhone.setText(person.getPhoneNumber());

                txtNamePerson.setEditable(false);
                txtSocialName.setEditable(false);
                txtPersonCpf.setEditable(false);
                txtBirth.setEditable(false);
                txtPersonRg.setEditable(false);
                txtPersonEmail.setEditable(false);
                txtPersonPhone.setEditable(false);

                btnEnableEdition.setVisible(true);
                btnDeletePerson.setVisible(true);
                btnUpdatePerson.setVisible(false);
                btnClosePersonWithoutSave.setVisible(false);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnClosePersonWithoutSave.setBackground(Color.decode("#34F907"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnClosePersonWithoutSave.setBackground(Color.decode("#D7F205"));
            }
        });



        btnUpdatePerson.setVisible(false);
        btnClosePersonWithoutSave.setVisible(false);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(namePersonComponent.jPanel,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(socialNamePersonComponent.jPanel,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(cpfPersonComponent.jPanel,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rgPersonComponent.jPanel,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(birthPersonComponent.jPanel,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(emailPersonComponent.jPanel,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(phonePersonComponent.jPanel,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                        )
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnEnableEdition, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDeletePerson, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnUpdatePerson, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnClosePersonWithoutSave, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(namePersonComponent.jPanel)
                                        .addComponent(socialNamePersonComponent.jPanel)
                        )
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(cpfPersonComponent.jPanel)
                                        .addComponent(rgPersonComponent.jPanel)
                        )
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(birthPersonComponent.jPanel)
                                        .addComponent(emailPersonComponent.jPanel)
                        )
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(phonePersonComponent.jPanel)
                        )
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(btnEnableEdition, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDeletePerson, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnUpdatePerson, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnClosePersonWithoutSave, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        )
        );



        return jPanel;

    }

    @Override
    public void setVisible(boolean value) {
        jFrame.setVisible(value);
    }

    private class ComponentElement{

        public JTextField textField;
        public JPanel jPanel;

    }

    private class ComponentElementButton{

        public JButton jButton;
        public JPanel jPanel;

    }


}
