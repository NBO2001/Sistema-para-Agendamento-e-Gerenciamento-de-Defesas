package view.cadastroprofessor;

import interfaces.VisiblePersonified;
import interfaces.Visibled;
import model.*;
import view.cadastroaluno.CadastroAluno;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroProfessor implements VisiblePersonified {

    private JFrame jFrame;
    private JPanel panel1;
    private JPanel jPanelMenu;
    private JButton btnMenu;
    private JButton btnCadStu;
    private JButton btnCadTeac;
    private JButton btnCadUser;
    private JButton btnCadDefense;
    private JButton btnAlterCad;
    private JButton btnRelatorio;
    private JPanel jPanelHome;
    private JTextField textFieldCPF2;
    private JPanel jPanelForm;
    private JTextField textFieldMatricula;
    private JTextField textFieldSocialName;
    private JTextField textFieldBirdday;
    private JTextField textFieldEmail;
    private JButton btnNext;
    private JPanel panelOption;
    private JTextField fieldRG;
    private JTextField fieldPhone;

    private Teacher teacher;

    public CadastroProfessor(Visibled afterView){
        initialize();
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());

            }
        });

        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(CadastroProfessor.this.getTeacher() != null){
                    Teacher teacher1 = CadastroProfessor.this.getTeacher();

                    teacher1.setRegister(textFieldMatricula.getText());


                    if(TeacherManager.insert(teacher1)){
                        JOptionPane.showMessageDialog(null, "Insert success!");

                        if( afterView != null ){
                            afterView.setVisible(true);
                            CadastroProfessor.this.setVisible(false);
                        }

                    }else{
                        if( afterView != null ){
                            afterView.setVisible(true);
                            CadastroProfessor.this.setVisible(false);
                        }
                        JOptionPane.showMessageDialog(null, "Error inserting.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }
        });
    }

    private void initialize(){

        this.jFrame = new JFrame("Cadastro");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setBorder(BorderFactory.createLineBorder(Color.white));
        this.textFieldMatricula.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }


    @Override
    public void setState(Person person) {
        this.setTeacher(Teacher.parseTeacher(person));
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
