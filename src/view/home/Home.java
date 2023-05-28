package view.home;

import interfaces.Visibled;
import view.cadastro.Cadastro;
import view.cadastroaluno.CadastroAluno;
import view.cadastrodefesa.CadastroDefesaVariant01;
import view.cadastroprofessor.CadastroProfessor;
import view.cadastrousuario.CadastroUsuario;

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
                new Cadastro(Home.this, new CadastroAluno(Home.this)).setVisible(true);
                Home.this.setVisible(false);
            }
        });

        btnCadTeac.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Cadastro(Home.this, new CadastroProfessor(Home.this)).setVisible(true);
                Home.this.setVisible(false);
            }
        });

        btnCadUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Cadastro(Home.this, new CadastroUsuario(Home.this)).setVisible(true);
                Home.this.setVisible(false);
            }
        });
        btnCadDefense.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new CadastroDefesaVariant01(Home.this ).setVisible(true);
                Home.this.setVisible(false);
            }
        });
    }

    private void initialize(){

        this.jFrame = new JFrame("Home");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jPanelMenu.setVisible(false);

        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        jPanelMenu.setVisible(false);
        this.jFrame.setVisible(value);
    }
}
