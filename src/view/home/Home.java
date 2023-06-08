package view.home;

import interfaces.Visibled;
import model.BoardOfTeachers;
import model.ConstatesPath;
import model.Defense;
import model.DefenseManager;
import view.gerenciacadastros.GerenciarCadastros;
import view.modals.ModalViewDefense;
import view.relatorios.Reports;
import view.tools.ConteinerPanelAndTxt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Home implements Visibled {
    private JFrame jFrame;
    private JPanel panel1;
    private JButton btnMenu;
    private JPanel jPanelMenu;

    private JButton btnRelatorio;
    private JPanel jPanelHome;

    private JPanel jPanelTable;
    private JButton btnGerenciarCad;
    private JPanel panel2;

    public Home(){
        initialize();
    }

    private void initialize(){

        this.jFrame = new JFrame("Home");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setMinimumSize(new Dimension(800,900));
        jPanelMenu.setVisible(false);

        btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());

            }
        });

        btnGerenciarCad.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        btnGerenciarCad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnGerenciarCad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnGerenciarCad.setBackground(Color.decode("#80D1E8")); // Change button color on mouse enter
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnGerenciarCad.setCursor(Cursor.getDefaultCursor());
                btnGerenciarCad.setBackground(Color.decode("#9ACFFF")); // Change button color on mouse exit
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new GerenciarCadastros(Home.this).setVisible(true);
                Home.this.setVisible(false);
            }
        });

        btnRelatorio.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        btnRelatorio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnRelatorio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnRelatorio.setBackground(Color.decode("#80D1E8")); // Change button color on mouse enter
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnRelatorio.setCursor(Cursor.getDefaultCursor());
                btnRelatorio.setBackground(Color.decode("#9ACFFF")); // Change button color on mouse exit
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Reports(Home.this).setVisible(true);
                Home.this.setVisible(false);

            }
        });

        this.jFrame.add(panel1);
    }

    private void cleaning(){
        for (Component component : jPanelTable.getComponents()) {
            jPanelTable.remove(component);
        }
        jPanelTable.add(Home.headTable());

    }

    private void updateHome() {
        DefenseManager defenseManager = new DefenseManager();

        ArrayList<Defense> defenses = defenseManager.selectAllOpened();
        Boolean color = true;

        for(Defense defense: defenses){

            if(color){
                jPanelTable.add(Home.rowTableCreate(defense, "#F1F1F1", "#D3E2EB" ));
            }else{
                jPanelTable.add(Home.rowTableCreate(defense, "#EAEAEA", "#D3E2EB"));
            }

            color = !color;
        }

        for (Component component : jPanelTable.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getPreferredSize().height));
            }
        }
    }

    public void setVisible(boolean value){
        jPanelMenu.setVisible(false);
        this.jFrame.setVisible(value);

        if(value == false){
            cleaning();
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



    public static JPanel headTable(){
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
                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
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

    public static JPanel rowTableCreate(Defense defense, String color, String secondColor) {
        JPanel jPanel = new JPanel();


        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        ConteinerPanelAndTxt jPanel1 = Home.createCellElement(defense.getDefenseTitle(), color);
        ConteinerPanelAndTxt jPanel2 = Home.createCellElement(defense.getStudentDefending().getName(), color);
        ConteinerPanelAndTxt jPanel3 = Home.createCellElement(defense.getTeacherAdvisor().getName(), color);
        ConteinerPanelAndTxt jPanel4 = Home.createCellElement(Defense.dateToString(defense.getDate()), color);
        JPanel jPanel5 = Home.createCellButton("View detalhes", color, defense);

        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jPanel.setBackground(Color.decode(secondColor));
                jPanel1.jTextArea.setBackground(Color.decode(secondColor));
                jPanel2.jTextArea.setBackground(Color.decode(secondColor));
                jPanel3.jTextArea.setBackground(Color.decode(secondColor));
                jPanel4.jTextArea.setBackground(Color.decode(secondColor));
                jPanel5.setBackground(Color.decode(secondColor));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jPanel.setBackground(Color.decode(color)); // Set the original color when the mouse exits
                jPanel1.jTextArea.setBackground(Color.decode(color));
                jPanel2.jTextArea.setBackground(Color.decode(color));
                jPanel3.jTextArea.setBackground(Color.decode(color));
                jPanel4.jTextArea.setBackground(Color.decode(color));
                jPanel5.setBackground(Color.decode(color));
            }
        });

        jPanel1.jTextArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        jPanel2.jTextArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        jPanel3.jTextArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        jPanel4.jTextArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

        jPanel1.jTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jPanel.setBackground(Color.decode(secondColor));
                jPanel1.jTextArea.setBackground(Color.decode(secondColor));
                jPanel2.jTextArea.setBackground(Color.decode(secondColor));
                jPanel3.jTextArea.setBackground(Color.decode(secondColor));
                jPanel4.jTextArea.setBackground(Color.decode(secondColor));
                jPanel5.setBackground(Color.decode(secondColor));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jPanel.setBackground(Color.decode(color)); // Set the original color when the mouse exits
                jPanel1.jTextArea.setBackground(Color.decode(color));
                jPanel2.jTextArea.setBackground(Color.decode(color));
                jPanel3.jTextArea.setBackground(Color.decode(color));
                jPanel4.jTextArea.setBackground(Color.decode(color));
                jPanel5.setBackground(Color.decode(color));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                jPanel1.jTextArea.requestFocusInWindow();
                jPanel1.jTextArea.selectAll();
            }
        });

        jPanel2.jTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jPanel.setBackground(Color.decode(secondColor));
                jPanel1.jTextArea.setBackground(Color.decode(secondColor));
                jPanel2.jTextArea.setBackground(Color.decode(secondColor));
                jPanel3.jTextArea.setBackground(Color.decode(secondColor));
                jPanel4.jTextArea.setBackground(Color.decode(secondColor));
                jPanel5.setBackground(Color.decode(secondColor));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jPanel.setBackground(Color.decode(color)); // Set the original color when the mouse exits
                jPanel1.jTextArea.setBackground(Color.decode(color));
                jPanel2.jTextArea.setBackground(Color.decode(color));
                jPanel3.jTextArea.setBackground(Color.decode(color));
                jPanel4.jTextArea.setBackground(Color.decode(color));
                jPanel5.setBackground(Color.decode(color));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                jPanel2.jTextArea.requestFocusInWindow();
                jPanel2.jTextArea.selectAll();
            }
        });
        jPanel3.jTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jPanel.setBackground(Color.decode(secondColor));
                jPanel1.jTextArea.setBackground(Color.decode(secondColor));
                jPanel2.jTextArea.setBackground(Color.decode(secondColor));
                jPanel3.jTextArea.setBackground(Color.decode(secondColor));
                jPanel4.jTextArea.setBackground(Color.decode(secondColor));
                jPanel5.setBackground(Color.decode(secondColor));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jPanel.setBackground(Color.decode(color)); // Set the original color when the mouse exits
                jPanel1.jTextArea.setBackground(Color.decode(color));
                jPanel2.jTextArea.setBackground(Color.decode(color));
                jPanel3.jTextArea.setBackground(Color.decode(color));
                jPanel4.jTextArea.setBackground(Color.decode(color));
                jPanel5.setBackground(Color.decode(color));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                jPanel3.jTextArea.requestFocusInWindow();
                jPanel3.jTextArea.selectAll();
            }
        });
        jPanel4.jTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jPanel.setBackground(Color.decode(secondColor));
                jPanel1.jTextArea.setBackground(Color.decode(secondColor));
                jPanel2.jTextArea.setBackground(Color.decode(secondColor));
                jPanel3.jTextArea.setBackground(Color.decode(secondColor));
                jPanel4.jTextArea.setBackground(Color.decode(secondColor));
                jPanel5.setBackground(Color.decode(secondColor));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jPanel.setBackground(Color.decode(color)); // Set the original color when the mouse exits
                jPanel1.jTextArea.setBackground(Color.decode(color));
                jPanel2.jTextArea.setBackground(Color.decode(color));
                jPanel3.jTextArea.setBackground(Color.decode(color));
                jPanel4.jTextArea.setBackground(Color.decode(color));
                jPanel5.setBackground(Color.decode(color));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                jPanel4.jTextArea.requestFocusInWindow();
                jPanel4.jTextArea.selectAll();
            }
        });

        // Add MouseListener to jPanel1 for hover color change

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1.jPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2.jPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel3.jPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel4.jPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1.jPanel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2.jPanel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel3.jPanel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel4.jPanel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
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

    public static ConteinerPanelAndTxt createCellElement(String title, String color) {

        ConteinerPanelAndTxt conteinerPanelAndTxt = new ConteinerPanelAndTxt();
        JPanel container = new JPanel(new BorderLayout());
        container.setMinimumSize(new Dimension(350, 80));
        container.setBackground(Color.decode(color));

        conteinerPanelAndTxt.jPanel = container;

        JTextArea textArea = new JTextArea(title);
        textArea.setBorder(new EmptyBorder(5,5,5,5));
        textArea.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBackground(Color.decode(color));
        textArea.setBorder(null);

        conteinerPanelAndTxt.jTextArea = textArea;

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.add(scrollPane, BorderLayout.CENTER);

        return conteinerPanelAndTxt;
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
        jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
