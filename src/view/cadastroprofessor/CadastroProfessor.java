package view.cadastroprofessor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroProfessor {

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

    public CadastroProfessor(){
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
        this.textFieldMatricula.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }



}
