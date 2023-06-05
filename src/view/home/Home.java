package view.home;

import interfaces.Visibled;
import model.BoardOfTeachers;
import model.ConstatesPath;
import model.Defense;
import model.DefenseManager;
import view.cadastro.Cadastro;
import view.cadastroaluno.CadastroAluno;
import view.cadastrodefesa.CadastroDefesaVariant01;
import view.cadastroprofessor.CadastroProfessor;
import view.cadastrousuario.CadastroUsuario;
import view.editPerson.EditPersonVariant01;
import view.gerenciacadastros.GerenciarCadastros;
import view.modals.ModalViewDefense;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Home implements Visibled {
    private JFrame jFrame;
    private JPanel panel1;
    private JButton btnMenu;
    private JPanel jPanelMenu;
    private JButton btnCadDefense;
    private JButton btnAlterCad;
    private JButton btnRelatorio;
    private JPanel jPanelHome;

    private JPanel jPanelTable;
    private JButton btnAlterDefesas;
    private JButton btnGerenciarCad;

    public Home(){
        initialize();

    }

    private void initialize(){

        this.jFrame = new JFrame("Home");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setMinimumSize(new Dimension(800,900));
        jPanelMenu.setVisible(false);

        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());

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

        btnGerenciarCad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new GerenciarCadastros(Home.this).setVisible(true);
                Home.this.setVisible(false);
            }
        });

//        updateHome();

        this.jFrame.add(panel1);
    }

    private void clening(){
        for (Component component : jPanelTable.getComponents()) {
            jPanelTable.remove(component);
        }
        jPanelTable.add(Home.headTable());

    }

    private void updateHome() {
        DefenseManager defenseManager = new DefenseManager();

        ArrayList<Defense> defenses = defenseManager.selectAllOppend();
        Boolean color = true;

        for(Defense defense: defenses){

            if(color){
                jPanelTable.add(Home.rowTableCreate(defense, "#F1F1F1", "#D3E2EB" ));
            }else{
                jPanelTable.add(Home.rowTableCreate(defense, "#EAEAEA", "#D3E2EB"));
            }

            color = !color;
        }
    }

    public void setVisible(boolean value){
        jPanelMenu.setVisible(false);
        this.jFrame.setVisible(value);

        if(value == false){
            clening();
        }else{
            updateHome();
        }
    }

    private void createUIComponents() {
        jPanelHome = new JPanel();
        jPanelHome.setBackground(Color.decode("#ffffff"));

        GroupLayout layout = new GroupLayout(jPanelHome);
        jPanelHome.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        JLabel label = new JLabel("Defesas Futuras");
        label.setFont(new Font("Ubuntu", Font.BOLD, 40));

        jPanelTable = new JPanel();
        jPanelTable.setBackground(Color.decode("#ffffff"));
        jPanelTable.setBorder(new EmptyBorder(5, 5, 5, 5));
        jPanelTable.setLayout(new BoxLayout(jPanelTable, BoxLayout.Y_AXIS));
        jPanelTable.add(Home.headTable());

        JScrollPane scrollPane = new JScrollPane(jPanelTable);
        scrollPane.setBorder(null); // Remove the color border

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
        );
    }



    private static JPanel headTable(){
        JPanel jPanel = new JPanel();

        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        JPanel jPanel1 =  Home.createCell("Titulo", "#EAEAEA");
        JPanel jPanel2 =  Home.createCell("Aluno", "#EAEAEA");
        JPanel jPanel3 = Home.createCell("Professor", "#EAEAEA");
        JPanel jPanel4 = Home.createCell("Data", "#EAEAEA");
        JPanel jPanel5 = Home.createCell("Action", "#EAEAEA");

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
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
                        )
        );

        return  jPanel;
    }

    private static JPanel rowTableCreate(Defense defense, String color, String secondColor) {
        JPanel jPanel = new JPanel();


        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        JPanel jPanel1 = Home.createCellElement(defense.getDefenseTitle(), color);
        JPanel jPanel2 = Home.createCellElement(defense.getStudentDefending().getName(), color);
        JPanel jPanel3 = Home.createCellElement(defense.getTeacherAdvisor().getName(), color);
        JPanel jPanel4 = Home.createCellElement(Defense.dateToString(defense.getDate()), color);
        JPanel jPanel5 = Home.createCellButton("Viee detalhes", color, defense);

        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jPanel.setBackground(Color.decode(secondColor)); // Set the hover color
                jPanel1.setBackground(Color.decode(secondColor));
                jPanel2.setBackground(Color.decode(secondColor));
                jPanel3.setBackground(Color.decode(secondColor));
                jPanel4.setBackground(Color.decode(secondColor));
                jPanel5.setBackground(Color.decode(secondColor));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jPanel.setBackground(Color.decode(color)); // Set the original color when the mouse exits
                jPanel1.setBackground(Color.decode(color));
                jPanel2.setBackground(Color.decode(color));
                jPanel3.setBackground(Color.decode(color));
                jPanel4.setBackground(Color.decode(color));
                jPanel5.setBackground(Color.decode(color));
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
                        )
        );

        return jPanel;
    }


    public static JPanel createCell(String title, String color){

        JPanel conteinnerTitle = new JPanel();
        conteinnerTitle.setPreferredSize(new Dimension(300,30));
        conteinnerTitle.setBackground(Color.decode(color));

        JLabel jLabel = new JLabel(title);
        jLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        conteinnerTitle.add(jLabel);
        return conteinnerTitle;
    }

    public static JPanel createCellElement(String title, String color) {
        JPanel conteinnerTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        conteinnerTitle.setPreferredSize(new Dimension(300, 50));
        conteinnerTitle.setBackground(Color.decode(color));

        String labelText = title;
        JTextField jTextField = new JTextField(labelText);
        jTextField.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        jTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));


        // Set the text field to be read-only and selectable
        jTextField.setEditable(false);
        jTextField.setBackground(null);
        jTextField.setBorder(null);
        jTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jTextField.requestFocusInWindow();
                jTextField.selectAll();
            }
        });

        conteinnerTitle.add(jTextField);
        return conteinnerTitle;
    }


    public static JPanel createCellButton(String title, String color, Defense defense){

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

        actionCreate(jButton, defense);

        conteinnerTitle.add(jButton);
        return conteinnerTitle;
    }

    private static void actionCreate(JButton button, Defense defense){

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                defense.setBoardOfTeachers(BoardOfTeachers.selectAll(defense.getDefenseId()));
                new ModalViewDefense(defense).setVisible(true);
            }
        });

    }
}
