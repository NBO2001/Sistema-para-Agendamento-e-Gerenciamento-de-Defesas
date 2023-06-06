package view.gerenciacadastros;

import interfaces.Visibled;
import model.*;
import view.cadastro.Cadastro;
import view.cadastroaluno.CadastroAluno;
import view.cadastroprofessor.CadastroProfessor;
import view.cadastrousuario.CadastroUsuario;
import view.editPerson.EditPersonVariant01;
import view.home.Home;
import view.modals.ModalViewDefense;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GerenciarCadastros implements Visibled {
    private JFrame  jFrame;
    private JPanel panel1;
    private JButton btnMenu;
    private JPanel jPanelMenu;
    private JButton btnCadStu;
    private JButton btnCadTeac;
    private JButton btnCadUser;
    private JButton btnAlterCad;
    private JPanel jPanelMain;
    private JButton btnHome;
    private Visibled afterView;
    private JPanel jPanelTable;

    private LabelAndSeach querryPerson;

    public GerenciarCadastros(Visibled afterView){
        this.afterView = afterView;
        initialize();
    }

    private void initialize(){

        this.jFrame = new JFrame("Gerenciamento de Cadastros");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setMinimumSize(new Dimension(800,900));

        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());
            }
        });
        btnHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(afterView!=null) afterView.setVisible(true);
                GerenciarCadastros.this.setVisible(false);
            }
        });

        btnCadStu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Cadastro(GerenciarCadastros.this, new CadastroAluno(GerenciarCadastros.this)).setVisible(true);
                GerenciarCadastros.this.setVisible(false);
            }
        });
        btnCadTeac.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Cadastro(GerenciarCadastros.this, new CadastroProfessor(GerenciarCadastros.this)).setVisible(true);
                GerenciarCadastros.this.setVisible(false);
            }
        });
        btnCadUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Cadastro(GerenciarCadastros.this, new CadastroUsuario(GerenciarCadastros.this)).setVisible(true);
                GerenciarCadastros.this.setVisible(false);
            }
        });
        btnAlterCad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new EditPersonVariant01(GerenciarCadastros.this).setVisible(true);
                GerenciarCadastros.this.setVisible(false);
            }
        });

        ArrayList<Person> people = People.selectAll();

        Boolean color = true;

        for(Person person: people){

            if(color){
                jPanelTable.add(rowTableCreate(person, "#F1F1F1", "#D3E2EB" ));
            }else{
                jPanelTable.add(rowTableCreate(person, "#EAEAEA", "#D3E2EB"));
            }

            color = !color;
        }

        querryPerson.jTextField.getDocument().addDocumentListener(new DocumentListener() {
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

                GerenciarCadastros.this.clening();

                Boolean color = true;

                for(Person person: People.selectAll(querryPerson.jTextField.getText())){
                    
                    if(color){
                        jPanelTable.add(rowTableCreate(person, "#F1F1F1", "#D3E2EB" ));
                    }else{
                        jPanelTable.add(rowTableCreate(person, "#EAEAEA", "#D3E2EB"));
                    }

                    color = !color;
                }

                jFrame.revalidate();
                jFrame.repaint();

            }
        });
        jFrame.add(panel1);

    }
    @Override
    public void setVisible(boolean value) {

        if(value) jPanelMenu.setVisible(false);

        jFrame.setVisible(value);
    }

    private JPanel rowTableCreate(Person person, String color, String secondColor) {
        JPanel jPanel = new JPanel();

        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        JPanel jPanel1;
        if(person.getSocialName().length() == 0){
            jPanel1 = Home.createCellElement(person.getName(), color);
        }else{
            jPanel1 = Home.createCellElement(person.getSocialName(), color);
        }


        JPanel jPanel2 = Home.createCellElement(person.getCpf(), color);
        JPanel jPanel3 = Home.createCellElement(person.getEmail(), color);
        JPanel jPanel4 = Home.createCellElement(Defense.dateToString(person.getBirthday()), color);
        JPanel jPanel5 = Home.createCellElement(person.getPhoneNumber(), color);
        JPanel jPanel6 = createCellButton("Viee detalhes", color, person);

        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jPanel.setBackground(Color.decode(secondColor)); // Set the hover color
                jPanel1.setBackground(Color.decode(secondColor));
                jPanel2.setBackground(Color.decode(secondColor));
                jPanel3.setBackground(Color.decode(secondColor));
                jPanel4.setBackground(Color.decode(secondColor));
                jPanel5.setBackground(Color.decode(secondColor));
                jPanel6.setBackground(Color.decode(secondColor));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jPanel.setBackground(Color.decode(color)); // Set the original color when the mouse exits
                jPanel1.setBackground(Color.decode(color));
                jPanel2.setBackground(Color.decode(color));
                jPanel3.setBackground(Color.decode(color));
                jPanel4.setBackground(Color.decode(color));
                jPanel5.setBackground(Color.decode(color));
                jPanel6.setBackground(Color.decode(color));
            }
        });

        // Add MouseListener to jPanel1 for hover color change

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        )
        );

        return jPanel;
    }

    private void clening(){
        for (Component component : jPanelTable.getComponents()) {
            jPanelTable.remove(component);
        }
        jPanelTable.add(GerenciarCadastros.headTable());

        jFrame.revalidate();
        jFrame.repaint();

    }
    public JPanel createCellButton(String title, String color, Person person){

        JPanel conteinnerTitle = new JPanel();
        conteinnerTitle.setPreferredSize(new Dimension(300,50));
        conteinnerTitle.setBackground(Color.decode(color));

        JButton jButton = new JButton();
        jButton.setPreferredSize(new Dimension(50,50));
        jButton.setBackground(Color.decode(color));
        jButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        jButton.setToolTipText(title);

        // Mudar depois
        String imagePath = ConstatesPath.PATH + "/src/imgs/search_30x30.jpg";

        ImageIcon icon = new ImageIcon(imagePath);

        jButton.setIcon(icon);

        actionCreate(jButton, person);

        conteinnerTitle.add(jButton);
        return conteinnerTitle;
    }

    private void createUIComponents() {
        jPanelMain = new JPanel();
        jPanelMain.setBackground(Color.decode("#ffffff"));

        GroupLayout layout = new GroupLayout(jPanelMain);
        jPanelMain.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        JLabel label = new JLabel("Gerenciamento de Pessoas");
        label.setFont(new Font("Ubuntu", Font.BOLD, 40));

        querryPerson = containerQuery("Buscar nome: ");

        jPanelTable = tableCreate();

//        jPanelTable.add(Home.headTable());

        JScrollPane scrollPane = new JScrollPane(jPanelTable);
        scrollPane.setBorder(null); // Remove the color border

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(querryPerson.jPanel)
                        .addComponent(scrollPane)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(querryPerson.jPanel)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
        );
    }

    private JPanel tableCreate(){
        jPanelTable = new JPanel();
        jPanelTable.setBackground(Color.decode("#ffffff"));
        jPanelTable.setBorder(new EmptyBorder(5, 5, 5, 5));
        jPanelTable.setLayout(new BoxLayout(jPanelTable, BoxLayout.Y_AXIS));

        jPanelTable.add(GerenciarCadastros.headTable());

        return jPanelTable;
    }

    private static JPanel headTable(){
        JPanel jPanel = new JPanel();

        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        JPanel jPanel1 =  Home.createCell("Nome", "#EAEAEA");
        JPanel jPanel2 =  Home.createCell("CPF", "#EAEAEA");
        JPanel jPanel3 = Home.createCell("Email", "#EAEAEA");
        JPanel jPanel4 = Home.createCell("Data Nas.", "#EAEAEA");
        JPanel jPanel5 = Home.createCell("Telefone", "#EAEAEA");
        JPanel jPanel6 = Home.createCell("Action", "#EAEAEA");

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        )
        );

        return  jPanel;
    }
    private void actionCreate(JButton button, Person person){

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new EditPersonVariant01(GerenciarCadastros.this, person).setVisible(true);
                GerenciarCadastros.this.setVisible(false);
            }
        });

    }


    private LabelAndSeach containerQuery(String title){

        JPanel jPanel = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel);

        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel jLabel = new  JLabel(title);
        jLabel.setFont(new Font("Ubuntu", Font.BOLD, 17));

        JTextField jTextField = new JTextField();
        jTextField.setBorder(new EmptyBorder(5, 5, 5, 5));

        LabelAndSeach labelAndSeach = new LabelAndSeach();
        labelAndSeach.jPanel = jPanel;
        labelAndSeach.jTextField = jTextField;

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                            layout.createSequentialGroup()
                                    .addComponent(jLabel)
                                    .addComponent(jTextField)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel)
                                .addComponent(jTextField)
                        )
        );

        return  labelAndSeach;
    }

    private class LabelAndSeach{
        public JPanel jPanel;
        public JTextField jTextField;

    }

}
