package view.cadastrodefesa;

import interfaces.Visibled;
import model.Defense;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        this.setDefense(defense);
        this.setInfo();
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());

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



        DefaultTableModel model = (DefaultTableModel) this.tableInfors.getModel();
        model.addRow(new Object[]{"Nome", "Matrícula", "Ação"});

        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }

    private void createUIComponents() {

        this.model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Matrícula");
        model.addColumn("Ação");

        this.tableInfors = new JTable(this.model);


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
