package view.cadastrodefesa;

import interfaces.Visibled;
import model.Defense;
import model.DefenseManager;
import model.StudentManager;
import model.Teacher;
import view.cadastroaluno.CadastroAluno;
import view.modalfindteacher.ModalFindTeacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CadastroDefesaVariant04 implements Visibled {

    private  JFrame jFrame;
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
    private JTextField textFieldStudent;
    private JTextField textFieldTeach;
    private JButton btnNext;
    private JPanel jPanelForm;
    private JTextField textFieldTitle;
    private JComboBox typeDefense;
    private JPanel panelOption;
    private JTextField textFieldNomeProfessor;
    private JButton btnVerify;
    private JTable tableInfors;
    private JTextField textTypeDefesa;
    private JPanel panelFundoTable;

    private DefaultTableModel model;

    private Defense defense;
    public CadastroDefesaVariant04(Visibled afterView, Defense defense){

        initialize();
        defense.setBoardOfTeachers(new ArrayList<Teacher>());
        this.setDefense(defense);

        this.setInfo();
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
                ModalFindTeacher modalFindTeacher = new ModalFindTeacher(textFieldNomeProfessor.getText(), CadastroDefesaVariant04.this);
                modalFindTeacher.setVisible(true);


            }
        });
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
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

    private void createUIComponents() {

        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new Object[]{"Nome", "Matrícula", "Ação"});

        this.tableInfors = new JTable(tableModel);


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

    public void addBoardOfTeachers(Teacher teacher){
        if(teacher != null){
            DefaultTableModel model = (DefaultTableModel) this.tableInfors.getModel();
            model.addRow(new Object[]{teacher.getName(), teacher.getRegister(), "Ação"});
            this.defense.getBoardOfTeachers().add(teacher);
        }

    }
}
