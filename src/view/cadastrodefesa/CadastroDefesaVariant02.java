package view.cadastrodefesa;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroDefesaVariant02 {
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
    private JTextField textFieldNAmeTeach;
    private JTextField textFieldEmailTeach;

    public CadastroDefesaVariant02(){
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
        this.textFieldCPFDoAluno.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldName.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldEmail.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.textFieldNAmeTeach.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldEmail.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }
}
