package view.cadastrousuario;

import interfaces.VisiblePersonified;
import interfaces.Visibled;
import model.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroUsuario implements VisiblePersonified  {

    private JFrame jFrame;
    private JPanel panel1;
    private JButton btnMenu;
    private JPanel jPanelMenu;
    private JButton btnCadHome;
    private JButton btnCadStu;
    private JButton btnCadTeac;
    private JButton btnCadDefense;
    private JButton btnAlterCad;
    private JButton btnRelatorio;
    private JPanel jPanelHome;
    private JButton btnNext;
    private JPanel jPanelForm;
    private JTextField textFieldLogin;
    private JTextField textFieldPasswd;
    private JPanel panelOption;

    private SystemUser systemUser;

    public CadastroUsuario(Visibled afterView){
        initialize();
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());

            }
        });


        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(CadastroUsuario.this.getSystemUser() != null){
                    SystemUser systemUser1 = CadastroUsuario.this.getSystemUser();

                    systemUser1.setLogin(textFieldLogin.getText());
                    systemUser1.setPassword(textFieldPasswd.getText());

                    if(SystemUserManager.insert(systemUser1)){
                        JOptionPane.showMessageDialog(null, "Insert success!");

                        if( afterView != null ){
                            afterView.setVisible(true);
                            CadastroUsuario.this.setVisible(false);
                        }

                    }else{
                        if( afterView != null ){
                            afterView.setVisible(true);
                            CadastroUsuario.this.setVisible(false);
                        }
                        JOptionPane.showMessageDialog(null, "Error inserting.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
        btnCadHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if( afterView != null ){
                    afterView.setVisible(true);
                    CadastroUsuario.this.setVisible(false);
                }
            }
        });
    }

    private void initialize(){

        this.jFrame = new JFrame("Cadastro");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setBorder(BorderFactory.createLineBorder(Color.white));
        this.textFieldLogin.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldPasswd.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        jPanelMenu.setVisible(false);
        this.jFrame.setVisible(value);
    }

    @Override
    public void setState(Person person) {
        setSystemUser(SystemUser.parseSystemUser(person));
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }
}
