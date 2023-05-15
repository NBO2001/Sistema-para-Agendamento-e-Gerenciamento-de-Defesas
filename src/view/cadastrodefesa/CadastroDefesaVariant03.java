package view.cadastrodefesa;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroDefesaVariant03 {
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
    private JButton btnNext;
    private JPanel jPanelForm;
    private JPanel jPanelTitle;
    private JTextField textFieldTitle;
    private JPanel panelOption;
    private JTextField textFieldDate;
    private JTextField textFieldLocal;
    private JTextField textFieldStudent;
    private JTextField textFieldTeach;
    private JComboBox typeDefense;

    public CadastroDefesaVariant03(){

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
        this.textFieldTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldDate.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldLocal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.textFieldStudent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldTeach.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.typeDefense.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }
}
