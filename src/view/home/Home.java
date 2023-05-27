package view.home;

import interfaces.Visibled;
import view.cadastro.Cadastro;
import view.cadastroaluno.CadastroAluno;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home implements Visibled {
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
    private JPanel jPanelForm;
    private JPanel jPanelTitle;
    private JPanel panelOption;

    public Home(){
        initialize();
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());

            }
        });
        btnCadStu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Cadastro(Home.this, new CadastroAluno()).setVisible(true);
                Home.this.setVisible(false);
            }
        });
    }

    private void initialize(){

        this.jFrame = new JFrame("Home");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }
}
