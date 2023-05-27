package view.cadastro;

import interfaces.Visibled;
import model.People;
import model.Person;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.ZoneId;

public class Cadastro implements Visibled {

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
    private JTextField textFieldCPF2;
    private JButton btnVerify;
    private JButton btnNext;
    private JPanel jPanelForm;
    private JPanel jPanelTitle;
    private JTextField textFieldName;
    private JTextField textFieldSocialName;
    private JTextField textFieldBirdday;
    private JTextField textFieldEmail;
    private JPanel panelOption;
    private JTextField fieldRG;
    private JTextField fieldPhone;

    private Boolean noInBase;
    public Cadastro(Visibled afterView, Visibled nextView){
        initialize();
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());

            }
        });

        btnVerify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Person person = People.selectPerson(textFieldCPF2.getText());

                if(person == null){
                    noInBase = true;
                    jPanelForm.setVisible(true);
                }else{
                    noInBase = false;
                    textFieldName.setText(person.getName());
                    textFieldSocialName.setText(person.getSocialName());
                    textFieldCPF2.setText(person.getCpf());
                    textFieldBirdday.setText(person.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
                    textFieldEmail.setText(person.getEmail());
                    fieldRG.setText(person.getRg());
                    fieldPhone.setText(person.getPhoneNumber());


                    textFieldName.setEditable(false);
                    textFieldSocialName.setEditable(false);
                    textFieldCPF2.setEditable(false);
                    textFieldBirdday.setEditable(false);
                    textFieldEmail.setEditable(false);
                    fieldRG.setEditable(false);
                    fieldPhone.setEditable(false);

                    jPanelForm.setVisible(true);


                }
            }
        });

        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(noInBase){
                    Person person = new Person();
                    person.setName(textFieldName.getText());
                    person.setSocialName(textFieldSocialName.getText());
                    person.setCpf(textFieldCPF2.getText());
                    person.setBirthday(textFieldBirdday.getText());
                    person.setEmail(textFieldEmail.getText());
                    person.setRg(fieldRG.getText());
                    person.setPhoneNumber(fieldPhone.getText());

                    if(People.insertPerson(person)){
                        nextView.setVisible(true);
                        Cadastro.this.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro inesperado", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }else{
                    nextView.setVisible(true);
                    Cadastro.this.setVisible(false);
                }



            }
        });


    }
    public Cadastro(){
        this(null, null);
    }

    private void initialize(){

        this.jFrame = new JFrame("Cadastro");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setBorder(BorderFactory.createLineBorder(Color.white));
        this.textFieldCPF2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }
}
