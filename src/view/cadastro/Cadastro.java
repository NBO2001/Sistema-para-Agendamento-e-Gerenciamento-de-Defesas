package view.cadastro;

import interfaces.VisiblePersonified;
import interfaces.Visibled;
import model.People;
import model.Person;
import model.Utils;
import view.modals.ModalViewDefense;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.ZoneId;

public class Cadastro implements Visibled {

    private JFrame jFrame;
    private JPanel panel1;
    private JPanel jPanelHome;
    private JTextField textFieldCPF2;
    private JButton btnVerify;
    private JButton btnNext;
    private JPanel jPanelForm;
    private JPanel jPanelTitle;
    private JTextField textFieldName;
    private JTextField textFieldSocialName;
    private JTextField textFieldDateOfBirth;
    private JTextField textFieldEmail;
    private JPanel panelOption;
    private JTextField fieldRG;
    private JTextField fieldPhone;
    private JButton btnBack;

    private Boolean noInBase;
    private Visibled afterView;
    private Person person;
    public Cadastro(Visibled afterView, VisiblePersonified nextView){
        this.afterView = afterView;
        initialize();


        btnVerify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(Utils.isValidCpf(textFieldCPF2.getText())){

                    person = People.select(textFieldCPF2.getText());

                    Cadastro.this.setPerson(person);

                    if(person == null){
                        jPanelForm.setVisible(true);
                        person = new Person();
                        person.setCpf(textFieldCPF2.getText());
                    }else{
                        textFieldName.setText(person.getName());
                        textFieldSocialName.setText(person.getSocialName());
                        textFieldCPF2.setText(person.getCpf());
                        textFieldDateOfBirth.setText(person.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
                        textFieldEmail.setText(person.getEmail());
                        fieldRG.setText(person.getRg());
                        fieldPhone.setText(person.getPhoneNumber());


                        textFieldName.setEditable(false);
                        textFieldSocialName.setEditable(false);
                        textFieldCPF2.setEditable(false);
                        textFieldDateOfBirth.setEditable(false);
                        textFieldEmail.setEditable(false);
                        fieldRG.setEditable(false);
                        fieldPhone.setEditable(false);

                        jPanelForm.setVisible(true);


                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Cpf invalid","Insira um cpf válido", JOptionPane.ERROR_MESSAGE);
                }



            }
        });

        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(person != null && person.isValidValues()){

                    nextView.setState(person);
                    nextView.setVisible(true);
                    Cadastro.this.destroy();

                }else {
                    JOptionPane.showMessageDialog(null,"Preechar os campos obrigatorios", "Error", JOptionPane.ERROR_MESSAGE);
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
        this.textFieldCPF2.setFont(new Font("Ubuntu", Font.PLAIN, 20));

        this.textFieldName.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldName.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        this.textFieldEmail.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldEmail.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        this.textFieldSocialName.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldSocialName.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        this.textFieldDateOfBirth.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldDateOfBirth.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        this.fieldRG.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.fieldRG.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        this.fieldPhone.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.fieldPhone.setFont(new Font("Ubuntu", Font.PLAIN, 20));


        btnBack.setSize(80,80);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(afterView != null) afterView.setVisible(true);
                Cadastro.this.destroy();
            }
        });
        textFieldName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleTextChange();
            }

            private void handleTextChange() {

                if(textFieldName.getText().length() >= 2) person.setName(textFieldName.getText());

            }
        });
        textFieldEmail.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleTextChange();
            }

            private void handleTextChange() {

                if(Utils.isValidEmail(textFieldEmail.getText())) person.setEmail(textFieldEmail.getText());

            }
        });
        textFieldSocialName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleTextChange();
            }

            private void handleTextChange() {

                if(textFieldSocialName.getText().length() > 2) person.setSocialName(textFieldSocialName.getText());

            }
        });
        textFieldDateOfBirth.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleTextChange();
            }

            private void handleTextChange() {
                if(Utils.isValidDate(textFieldDateOfBirth.getText())) person.setBirthday(Utils.strToDateBr(textFieldDateOfBirth.getText()));
            }
        });
        fieldRG.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleTextChange();
            }

            private void handleTextChange() {
                person.setRg(fieldRG.getText());
            }
        });
        fieldPhone.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleTextChange();
            }

            private void handleTextChange() {
                person.setPhoneNumber(fieldPhone.getText());
            }
        });

        this.jFrame.add(panel1);
    }

    private void destroy() {
        jFrame.dispose();
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}
