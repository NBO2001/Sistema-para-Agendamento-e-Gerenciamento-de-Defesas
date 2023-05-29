package view.editdefense;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditDefenseVariant02 {
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
    private JTextField txtFieldStudent;
    private JTextField txtTitleJob;
    private JComboBox comboBoxDefenseType;
    private JTextField txtTypeDefense;
    private JButton btnChangeBanca;
    private JTextField txtFieldTeach;

    public EditDefenseVariant02(){
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

        this.txtFieldStudent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.txtTitleJob.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.txtTypeDefense.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.btnChangeBanca.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.btnConclusion.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }
}
