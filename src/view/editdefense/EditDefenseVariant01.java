package view.editdefense;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditDefenseVariant01 {

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
    private JButton btnConclusion;
    private JPanel jPanelForm;
    private JTextField textFieldAluno;
    private JTextField textFieldProfessor;
    private JPanel panelOption;
    private JButton btnEditStudent;
    private JButton btnEditProf;
    private JTextField txtFieldTitleJob;
    private JTextField txtFieldDate;
    private JComboBox comboBoxDefenseType;
    private JTextField txtFieldLocal;
    private JButton btnChangeBanca;

    public EditDefenseVariant01(){
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

        this.jFrame = new JFrame("Cadastro");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setBorder(BorderFactory.createLineBorder(Color.white));
        this.textFieldAluno.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldProfessor.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.txtFieldTitleJob.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.txtFieldDate.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.txtFieldLocal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.btnEditProf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.btnEditStudent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.btnChangeBanca.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.comboBoxDefenseType.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.btnConclusion.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }
}
