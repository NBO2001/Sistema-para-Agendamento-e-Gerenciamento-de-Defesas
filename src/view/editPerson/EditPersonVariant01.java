package view.editPerson;

import interfaces.Visibled;
import model.People;
import model.Person;
import view.home.Home;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

public class EditPersonVariant01 implements Visibled {

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
    private JPanel jPanelBotttom;
    private JButton btnExit;
    private JButton btnAlter;
    private JButton btnUpdateAndNext;

    private Person person;

    public  EditPersonVariant01(Visibled afterView, Person person){

        this(afterView);
        setPerson(person);
        setInfo(person);

        btnVerify.setEnabled(false);

    }
    public EditPersonVariant01(Visibled afterView){
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

                Person person = People.select(textFieldCPF2.getText());

                if(person != null){
                    EditPersonVariant01.this.setPerson(person);
                    setInfo(person);
                }else{
                    JOptionPane.showMessageDialog(null, "Cpf não encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(afterView != null){
                    afterView.setVisible(true);
                }

                EditPersonVariant01.this.setVisible(false);
            }
        });
        btnAlter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(People.update(EditPersonVariant01.this.getPerson())){
                    JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                }else{
                    JOptionPane.showMessageDialog(null, "Error inserting.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                if(afterView != null){
                    afterView.setVisible(true);
                }
                EditPersonVariant01.this.setVisible(false);
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

                if(EditPersonVariant01.this.getPerson() != null){
                    EditPersonVariant01.this.getPerson().setName(textFieldName.getText());
                }

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

                if(EditPersonVariant01.this.getPerson() != null){
                    EditPersonVariant01.this.getPerson().setSocialName(textFieldSocialName.getText());
                }

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

                if(EditPersonVariant01.this.getPerson() != null){
                    EditPersonVariant01.this.getPerson().setEmail(textFieldEmail.getText());
                }

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

                if(EditPersonVariant01.this.getPerson() != null){
                    EditPersonVariant01.this.getPerson().setPhoneNumber(fieldPhone.getText());
                }

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

                if(EditPersonVariant01.this.getPerson() != null){
                    EditPersonVariant01.this.getPerson().setRg(fieldRG.getText());
                }

            }
        });

        textFieldBirdday.getDocument().addDocumentListener(new DocumentListener() {
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

                if(EditPersonVariant01.this.getPerson() != null){
                    EditPersonVariant01.this.getPerson().setBirthday(textFieldBirdday.getText());
                }

            }
        });

        textFieldCPF2.getDocument().addDocumentListener(new DocumentListener() {
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

                if(EditPersonVariant01.this.getPerson() != null){
                    EditPersonVariant01.this.getPerson().setCpf(textFieldCPF2.getText());
                }

            }
        });

        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                EditPersonVariant02 editPersonVariant02 = new EditPersonVariant02(afterView);
                editPersonVariant02.setState(person);
                editPersonVariant02.setVisible(true);
                EditPersonVariant01.this.setVisible(false);

            }
        });
        btnUpdateAndNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(People.update(EditPersonVariant01.this.getPerson())){
                    JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                }else{
                    JOptionPane.showMessageDialog(null, "Error inserting.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                EditPersonVariant02 editPersonVariant02 = new EditPersonVariant02(afterView);
                editPersonVariant02.setState(EditPersonVariant01.this.getPerson());
                editPersonVariant02.setVisible(true);
                EditPersonVariant01.this.setVisible(false);
            }
        });
    }

    private void initialize(){

        this.jFrame = new JFrame("Editar Cadastro");
        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jPanelBotttom.setVisible(false);

        this.jFrame.add(panel1);
    }
    @Override
    public void setVisible(boolean value) {
        this.jFrame.setVisible(value);
    }

    public void setInfo(Person person){

        String inputFormat = "yyyy-MM-dd";
        String outputFormat = "dd/MM/yyyy";
        if(person!=null){

            // Create input and output date format objects
            DateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
            DateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

            try {
                // Parse the input date string into a Date object
                Date date = inputDateFormat.parse(person.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());

                // Format the Date object into the desired output format
                String outputDate = outputDateFormat.format(date);

                textFieldCPF2.setText(person.getCpf());
                textFieldName.setText(person.getName());
                textFieldEmail.setText(person.getEmail());
                textFieldSocialName.setText(person.getSocialName());
                fieldPhone.setText(person.getPhoneNumber());
                fieldRG.setText(person.getRg());
                textFieldBirdday.setText(outputDate);

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


            jPanelForm.setVisible(true);
            jPanelBotttom.setVisible(true);
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
