package view.cadastrodefesa;

import interfaces.Visibled;
import model.Defense;
import model.Student;
import model.StudentManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroDefesaVariant01 implements Visibled {
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
    private JTextField textFieldCPFDoAluno;
    private JButton btnVerify;
    private JButton btnNext;
    private JPanel jPanelForm;
    private JPanel jPanelTitle;
    private JTextField textFieldName;
    private JTextField textFieldEmail;
    private JPanel panelOption;
    private JPanel jPanelBottom;

    private Defense defense;

    public CadastroDefesaVariant01(Visibled afterView) {

        initialize();
        this.setDefense(new Defense());
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());

            }
        });
        btnVerify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Student student = StudentManager.select(CadastroDefesaVariant01.this.textFieldCPFDoAluno.getText());

                if(student != null){
                    CadastroDefesaVariant01.this.getDefense().setStudentDefending(student);
                    setInfo(student);
                }else{
                    JOptionPane.showMessageDialog(null, "Aluno nao encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(CadastroDefesaVariant01.this.getDefense() != null){

                    new CadastroDefesaVariant02(afterView, CadastroDefesaVariant01.this.getDefense()).setVisible(true);
                    CadastroDefesaVariant01.this.setVisible(false);

                }

            }
        });
    }

    private void initialize(){

        this.jFrame = new JFrame("Cadastro Defesa");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jPanelBottom.setVisible(false);
        this.jPanelForm.setVisible(false);
        this.jPanelMenu.setVisible(false);



        // setBorder(BorderFactory.createLineBorder(Color.white));
        this.textFieldCPFDoAluno.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldName.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldEmail.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }

    public void setInfo(Student student){
        if(student != null){
            this.textFieldName.setText(student.getName());
            this.textFieldEmail.setText(student.getEmail());
            this.textFieldCPFDoAluno.setText(student.getCpf());

            this.textFieldEmail.setEditable(false);
            this.textFieldName.setEditable(false);
        }

        this.jPanelBottom.setVisible(true);
        this.jPanelForm.setVisible(true);

    }

    public Defense getDefense() {
        return defense;
    }

    public void setDefense(Defense defense) {
        this.defense = defense;
    }
}
