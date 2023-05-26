package view.cadastrodefesa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroDefesaVariant04 {

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
    private JTextField fieldTypeDefesa;
    private JPanel panelFundoTable;

    private DefaultTableModel model;
    public CadastroDefesaVariant04(){

        initialize();
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
        this.fieldTypeDefesa.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));



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
}
