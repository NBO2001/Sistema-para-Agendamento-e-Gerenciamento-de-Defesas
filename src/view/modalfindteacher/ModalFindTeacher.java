package view.modalfindteacher;

import interfaces.Visibled;
import model.Teacher;
import model.TeacherManager;
import view.cadastrodefesa.CadastroDefesaVariant04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ModalFindTeacher implements Visibled {

    private JFrame jFrame;
    private JPanel tablePanel;

    private JButton btnSeach;
    private JTextField txtSeach;

    private Teacher teacher;

    private CadastroDefesaVariant04 cadastroDefesaVariant04;

    public ModalFindTeacher(String seach, CadastroDefesaVariant04 cadastroDefesaVariant04){
        initialize();

        if(cadastroDefesaVariant04 != null){
            this.cadastroDefesaVariant04 = cadastroDefesaVariant04;
        }

        if(seach != null){
            txtSeach.setText(seach);
            updateElements();
        }

        btnSeach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                updateElements();

            }
        });
    }
    public ModalFindTeacher(){
        this(null, null);
    }

    private void initialize(){

        jFrame = new JFrame();
        jFrame.setSize(800, 800);
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // First Panel
        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(750, 100));
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

        txtSeach = new JTextField(10);
        btnSeach = new JButton("Buscar");

        panel1.add(txtSeach);
        panel1.add(btnSeach);

        // Second Panel
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(750, 500));
        panel2.setLayout(new BorderLayout());

        createTable();

        JScrollPane scrollPane = new JScrollPane(tablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel2.add(scrollPane, BorderLayout.CENTER);

        // Third Panel
        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(750, 100));
        panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton finishButton = new JButton("Finish");
        panel3.add(finishButton);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        jFrame.add(mainPanel);


    }

    private void createTable(){
        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.add(createHead("Name", "Enrollment","Action"));
    }

    private void clening(){
        for (Component component : tablePanel.getComponents()) {
            tablePanel.remove(component);
        }

        tablePanel.add(createHead("Name", "Enrollment","Action"));

    }
    private static JPanel createHead(String nameLabelA, String enrollmentLabelA, String bottom) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel nameLabel = new JLabel(nameLabelA);
        JLabel enrollmentLabel = new JLabel(enrollmentLabelA);
        JLabel actionLabel = new JLabel(bottom);

        nameLabel.setPreferredSize(new Dimension(150, 50));
        enrollmentLabel.setPreferredSize(new Dimension(150, 50));
        actionLabel.setPreferredSize(new Dimension(150, 50));

        rowPanel.add(nameLabel);
        rowPanel.add(enrollmentLabel);
        rowPanel.add(actionLabel);

        return rowPanel;
    }

    private JPanel createTableRow(String name, String enrollment, Teacher teacher) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setPreferredSize(new Dimension(150, 50));
        JLabel enrollmentLabel = new JLabel(enrollment);
        enrollmentLabel.setPreferredSize(new Dimension(150, 50));
        JButton actionButton = new JButton("Add");
        actionButton.putClientProperty("teacher", teacher);

        actionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Teacher teacher = (Teacher) actionButton.getClientProperty("teacher");
                cadastroDefesaVariant04.addBoardOfTeachers(teacher);
                ModalFindTeacher.this.setTeacher(teacher);
                ModalFindTeacher.this.setVisible(false);
            }
        });
        actionButton.setPreferredSize(new Dimension(150, 50));

        rowPanel.add(nameLabel);
        rowPanel.add(enrollmentLabel);
        rowPanel.add(actionButton);

        return rowPanel;
    }
    @Override
    public void setVisible(boolean value) {
        jFrame.setVisible(value);
    }

    private void addTeacher(Teacher teacher){
        if(teacher != null){
            tablePanel.add(createTableRow(teacher.getName(), teacher.getRegister(), teacher));
            tablePanel.revalidate();
            tablePanel.repaint();
        }
    }

    private void updateElements(){
        clening();
        ArrayList<Teacher> teachers = TeacherManager.selectAll(txtSeach.getText());

        for(Teacher teacher: teachers){
            addTeacher(teacher);
        }
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
