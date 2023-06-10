package view.cadastrodefesa;

import interfaces.Visibled;
import model.*;
import view.cadastro.Cadastro;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroDefesaVariant02 implements Visibled {
    private JFrame jFrame;
    private JPanel panel1;
    private JPanel jPanelHome;
    private JTextField textFieldCPFDoProfessor;
    private JButton btnVerify;
    private JButton btnNext;
    private JPanel jPanelForm;
    private JPanel jPanelTitle;
    private JTextField textFieldName;
    private JTextField textFieldEmail;
    private JPanel panelOption;
    private JTextField textFieldNAmeTeach;
    private JTextField textFieldEmailTeach;
    private JPanel jPanelBottom;
    private JButton btnBack;

    private Defense defense;

    public CadastroDefesaVariant02(Visibled afterView, Defense defense){
        initialize();

        this.setDefense(defense);
        this.setInfoStudent(defense.getStudentDefending());

        btnBack.setSize(80,80);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(afterView != null) afterView.setVisible(true);
                CadastroDefesaVariant02.this.destroy();
            }
        });


        btnVerify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Teacher teacher = TeacherManager.select(CadastroDefesaVariant02.this.textFieldCPFDoProfessor.getText());

                if(teacher != null){
                    CadastroDefesaVariant02.this.getDefense().setTeacherAdvisor(teacher);
                    setInfoTeacher(teacher);
                }else{
                    JOptionPane.showMessageDialog(null, "Professor nao encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(CadastroDefesaVariant02.this.getDefense() != null){

                    new CadastroDefesaVariant03(afterView, CadastroDefesaVariant02.this.getDefense()).setVisible(true);
                    CadastroDefesaVariant02.this.setVisible(false);

                }
            }
        });
    }

    private void destroy() {
        jFrame.dispose();
    }

    private void initialize(){

        this.jFrame = new JFrame("Cadastro Defesa");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.jPanelBottom.setVisible(false);
        this.jPanelForm.setVisible(false);

        // setBorder(BorderFactory.createLineBorder(Color.white));
        this.textFieldCPFDoProfessor.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldName.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldEmail.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.textFieldNAmeTeach.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldEmail.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }

    public Defense getDefense() {
        return defense;
    }

    public void setDefense(Defense defense) {
        this.defense = defense;
    }

    public void setInfoStudent(Student student){
        if(student != null){
            this.textFieldName.setText(student.getName());
            this.textFieldEmail.setText(student.getEmail());

            this.textFieldEmail.setEditable(false);
            this.textFieldName.setEditable(false);
        }

    }

    public void setInfoTeacher(Teacher teacher){
        if(teacher != null){
            this.textFieldNAmeTeach.setText(teacher.getName());
            this.textFieldEmailTeach.setText(teacher.getEmail());
            this.textFieldCPFDoProfessor.setText(teacher.getCpf());

            this.textFieldNAmeTeach.setEditable(false);
            this.textFieldEmailTeach.setEditable(false);
        }

        this.jPanelBottom.setVisible(true);
        this.jPanelForm.setVisible(true);

    }
}
