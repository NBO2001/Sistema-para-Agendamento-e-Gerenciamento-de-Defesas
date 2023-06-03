package view.home;

import interfaces.Visibled;
import model.BoardOfTeachers;
import model.Defense;
import model.DefenseManager;
import view.cadastro.Cadastro;
import view.cadastroaluno.CadastroAluno;
import view.cadastrodefesa.CadastroDefesaVariant01;
import view.cadastroprofessor.CadastroProfessor;
import view.cadastrousuario.CadastroUsuario;
import view.editPerson.EditPersonVariant01;
import view.modals.ModalViewDefense;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
    private JButton btnCadStu;
    private JButton btnCadTeac;
    private JButton btnCadUser;
    private JButton btnCadDefense;
    private JButton btnAlterCad;
    private JButton btnRelatorio;
    private JPanel jPanelHome;

    private JPanel jPanelTable;
    private JButton btnAlterDefesas;

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
        btnAlterCad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new EditPersonVariant01(Home.this).setVisible(true);
                Home.this.setVisible(false);
            }
        });
    }

    private void initialize(){

        this.jFrame = new JFrame("Home");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setMinimumSize(new Dimension(800,900));
        jPanelMenu.setVisible(false);

//        updateHome();

        this.jFrame.add(panel1);
    }

    private void clening(){
        for (Component component : jPanelTable.getComponents()) {
            jPanelTable.remove(component);
        }

        JPanel line1 = new JPanel();
        line1.setLayout(new BoxLayout(line1,BoxLayout.X_AXIS));

        line1.add(Home.createCell("Titulo", "#EAEAEA"));
        line1.add(Home.createCell("Aluno", "#EAEAEA"));
        line1.add(Home.createCell("Professor", "#EAEAEA"));
        line1.add(Home.createCell("Data", "#EAEAEA"));
        line1.add(Home.createCell("Action", "#EAEAEA"));


        jPanelTable.add(line1);

    }

    private void updateHome() {
        DefenseManager defenseManager = new DefenseManager();

        ArrayList<Defense> defenses = defenseManager.selectAllOppend();
        Boolean color = true;

        for(Defense defense: defenses){

            if(color){
                jPanelTable.add(Home.createElementLine(defense, "#ffffff"));
            }else{
                jPanelTable.add(Home.createElementLine(defense, "#EAEAEA"));
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
        jPanelHome.setLayout(new BoxLayout(jPanelHome,BoxLayout.Y_AXIS));

        JPanel jPanelTop = new JPanel();
        jPanelTop.setLayout(new BorderLayout());
        jPanelTop.setBackground(Color.decode("#FFFFFF"));


        JLabel label = new JLabel("Defesas futuras");
        label.setFont(new Font("Ubuntu", Font.BOLD, 40));
        label.setBorder(new EmptyBorder(20, 20, 20,0));
        jPanelTop.add(label, BorderLayout.LINE_START);

        jPanelTable = new JPanel();
        jPanelTable.setLayout(new BoxLayout(jPanelTable,BoxLayout.Y_AXIS));
        jPanelTable.setBackground(Color.decode("#FFFFFF"));

        jPanelTable.setMinimumSize(new Dimension(800,500));
        jPanelTable.setPreferredSize(new Dimension(1000,500));
        jPanelTable.setMaximumSize(new Dimension(1000,500));

        JPanel line1 = new JPanel();
        line1.setLayout(new BoxLayout(line1,BoxLayout.X_AXIS));

        line1.add(Home.createCell("Titulo", "#EAEAEA"));
        line1.add(Home.createCell("Aluno", "#EAEAEA"));
        line1.add(Home.createCell("Professor", "#EAEAEA"));
        line1.add(Home.createCell("Data", "#EAEAEA"));
        line1.add(Home.createCell("Action", "#EAEAEA"));


        jPanelTable.add(line1);

        JScrollPane jScrollPane = new JScrollPane(jPanelTable);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        jPanelHome.add(jPanelTop);
        jPanelHome.add(jScrollPane);

    }

    private static JPanel createElementLine(Defense defense, String color){
        JPanel line = new JPanel();
        line.setLayout(new BoxLayout(line,BoxLayout.X_AXIS));
        line.setMinimumSize(new Dimension(800,200));
        line.add(Home.createCellElement(defense.getDefenseTitle(), color));
        line.add(Home.createCellElement(defense.getStudentDefending().getName(), color));
        line.add(Home.createCellElement(defense.getTeacherAdvisor().getName(), color));

        String inputFormat = "yyyy-MM-dd";
        String outputFormat = "dd/MM/yyyy";

        DateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
        DateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
        // Parse the input date string into a Date object
        try{
            Date date = inputDateFormat.parse(defense.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
            // Format the Date object into the desired output format
            String outputDate = outputDateFormat.format(date);
            line.add(Home.createCellElement(outputDate, color));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        line.add(Home.createCellButton("Open", color, defense));

        return line;
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

    public static JPanel createCellElement(String title, String color){

        JPanel conteinnerTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        conteinnerTitle.setPreferredSize(new Dimension(300,50));
        conteinnerTitle.setBackground(Color.decode(color));

        String labelText = "<html><div style='width: 300px; word-wrap: break-word;'>" + title + "</div></html>";

        JLabel jLabel = new JLabel(labelText);
        jLabel.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        conteinnerTitle.add(jLabel);
        return conteinnerTitle;
    }

    public static JPanel createCellButton(String title, String color, Defense defense){

        JPanel conteinnerTitle = new JPanel();
        conteinnerTitle.setPreferredSize(new Dimension(300,50));
        conteinnerTitle.setBackground(Color.decode(color));

        JButton jButton = new JButton(title);
        jButton.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        jButton.setPreferredSize(new Dimension(150,25));

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
