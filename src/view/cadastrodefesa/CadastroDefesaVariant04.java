package view.cadastrodefesa;

import interfaces.Visibled;
import model.Defense;
import model.DefenseManager;
import model.Teacher;
import view.modals.ModalFindTeacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CadastroDefesaVariant04 implements Visibled {

    private  JFrame jFrame;
    private JPanel panel1;
    private JPanel jPanelHome;
    private JTextField textFieldStudent;
    private JTextField textFieldTeach;
    private JButton btnNext;
    private JPanel jPanelForm;
    private JTextField textFieldTitle;
    private JComboBox typeDefense;
    private JPanel panelOption;
    private JTextField textFieldNomeProfessor;
    private JButton btnVerify;
    private JTextField textTypeDefesa;
    private JButton btnBack;

    private DefaultTableModel model;

    private Defense defense;
    public CadastroDefesaVariant04(Visibled afterView, Defense defense){

        initialize();
        defense.setBoardOfTeachers(new ArrayList<Teacher>());
        this.setDefense(defense);

        this.setInfo();

        btnBack.setSize(80,80);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(afterView != null) afterView.setVisible(true);
                CadastroDefesaVariant04.this.destroy();
            }
        });

        btnVerify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ModalFindTeacher modalFindTeacher = new ModalFindTeacher(textFieldNomeProfessor.getText(), CadastroDefesaVariant04.this, CadastroDefesaVariant04.this.getDefense().getBoardOfTeachers());
                modalFindTeacher.setVisible(true);


            }
        });
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(defense.getBoardOfTeachers().size() == 0){
                    JOptionPane.showMessageDialog(null,"Teacher of board is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
                    ModalFindTeacher modalFindTeacher = new ModalFindTeacher(textFieldNomeProfessor.getText(), CadastroDefesaVariant04.this, CadastroDefesaVariant04.this.getDefense().getBoardOfTeachers());
                    modalFindTeacher.setVisible(true);
                    return;
                }
                if(DefenseManager.insert(defense)){
                    JOptionPane.showMessageDialog(null, "Insert success!");

                    if( afterView != null ){
                        afterView.setVisible(true);
                        CadastroDefesaVariant04.this.setVisible(false);
                    }

                }else{
                    if( afterView != null ){
                        afterView.setVisible(true);
                        CadastroDefesaVariant04.this.setVisible(false);
                    }
                    JOptionPane.showMessageDialog(null, "Error inserting.", "Error", JOptionPane.ERROR_MESSAGE);
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

        // setBorder(BorderFactory.createLineBorder(Color.white));


        this.textFieldStudent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldTeach.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textTypeDefesa.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));



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

    private void setInfo() {

        if(defense != null){
            this.textFieldTeach.setText(defense.getTeacherAdvisor().getName());
            this.textFieldStudent.setText(defense.getStudentDefending().getName());
            this.textFieldTitle.setText(defense.getDefenseTitle());
            this.textTypeDefesa.setText(Defense.parseTypeDefenseFormatString(defense.getTypeDefense()));


            this.textFieldTeach.setEditable(false);
            this.textFieldStudent.setEditable(false);
        }
    }

}
