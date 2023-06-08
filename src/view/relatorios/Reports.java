package view.relatorios;

import interfaces.Visibled;
import model.*;
import view.cadastrodefesa.CadastroDefesaVariant01;
import view.cadastrodefesa.CadastroDefesaVariant03;
import view.home.Home;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Reports implements Visibled {
    private JFrame jFrame;
    private JPanel panel1;
    private JButton btnMenu;
    private JPanel jPanelMenu;
    private JButton btnCadDefense;
    private JPanel jPanelHome;
    private JButton btnBack;
    private JPanel jPanelTable;
    private JButton btnSearch;

    private JTextField txtStudentName;
    private JTextField txtTeacherName;

    private JTextField txtDateStart;
    private JTextField txtDateEnd;
    private JComboBox jComboBox;
    private JButton btnDownloadCSV;
    private Visibled afterView;

    public ArrayList<Defense> getDefenses() {
        return defenses;
    }

    public void setDefenses(ArrayList<Defense> defenses) {
        this.defenses = defenses;
    }

    private ArrayList<Defense> defenses;


    public Reports(Visibled afterView){
        this.afterView = afterView;
        initialize();
    }

    private void initialize(){

        this.jFrame = new JFrame("Reports");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setMinimumSize(new Dimension(800,900));
        jPanelMenu.setVisible(false);

        btnBack.setSize(80,80);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(afterView != null) afterView.setVisible(true);
                Reports.this.destroy();
            }
        });

        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());

            }
        });

        btnCadDefense.setFont(new Font("Ubuntu", Font.BOLD, 20));
        btnCadDefense.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCadDefense.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnCadDefense.setBackground(Color.decode("#80D1E8")); // Change button color on mouse enter
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnCadDefense.setCursor(Cursor.getDefaultCursor());
                btnCadDefense.setBackground(Color.decode("#9ACFFF")); // Change button color on mouse exit
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new CadastroDefesaVariant01(Reports.this ).setVisible(true);
                Reports.this.setVisible(false);
            }
        });

        this.jFrame.add(panel1);
    }




    public void setVisible(boolean value){
        jPanelMenu.setVisible(false);
        this.jFrame.setVisible(value);

        if(!value) cleaning();
        else update();

    }

    private PanelAndTxt componentSearch(String label){

        PanelAndTxt panelAndTxt = new PanelAndTxt();

        JTextField jTextField = new JTextField();
        jTextField.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel jPanel = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        JLabel label1 = new JLabel(label);
        label1.setFont(new Font("Ubuntu", Font.PLAIN, 18));

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label1)
                        .addComponent(jTextField)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label1)
                        .addComponent(jTextField, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        );

        panelAndTxt.jTextField = jTextField;
        panelAndTxt.jPanel = jPanel;

        return panelAndTxt;

    }

    private PanelAndComboBox componentSearchCombo(String label){

        PanelAndComboBox panelAndComboBox = new PanelAndComboBox();

        JComboBox jComboBox = new JComboBox<>();
        jComboBox.setBorder(new EmptyBorder(5, 5, 5, 5));
        jComboBox.addItem(new Reports.AnyObject(-1, "Todos"));
        jComboBox.addItem(new Reports.AnyObject(0, "Apenas Não Aprovados"));
        jComboBox.addItem(new Reports.AnyObject(1, "Apenas Aprovados"));

        JPanel jPanel = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        JLabel label1 = new JLabel(label);
        label1.setFont(new Font("Ubuntu", Font.PLAIN, 18));

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label1)
                        .addComponent(jComboBox)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label1)
                        .addComponent(jComboBox, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        );

        panelAndComboBox.jComboBox = jComboBox;
        panelAndComboBox.jPanel = jPanel;

        return panelAndComboBox;

    }

    public void generateCSV() {
        // Create sample data
        ArrayList<Defense> defenses = this.getDefenses();

        // Create a StringBuilder to store the CSV content
        StringBuilder csvContent = new StringBuilder();

        // Append the header row
        csvContent.append("Title,Type_defense,Student_name,Student_Mat,Teacher_Name, Teacher_Mat,Date,Local,Status,Observation\n");

        // Append the data rows to the CSV content
        for (Defense defense : defenses) {
            Teacher teacher = defense.getTeacherAdvisor();
            Student student = defense.getStudentDefending();

            String title = "\"" + defense.getDefenseTitle() + "\"";
            String typeDefense = "\"" + Defense.parseTypeDefenseFormatString(defense.getTypeDefense()) + "\"";
            String studenName = "\"" + student.getName() + "\"";
            String studenMat = "\"" + student.getRegistration() + "\"";
            String teacherAdvision = "\"" + teacher.getName() + "\"";
            String teacherMat = "\"" + teacher.getRegister() + "\"";
            String date = "\"" + Utils.dateBrForDateEua(defense.getDate()) + "\"";
            String local = "\"" + defense.getLocal() + "\"";
            String status = "\"" + defense.getStatus() + "\"";
            String observation = "\"" + defense.getObservation() + "\"";

            String[] row = {title, typeDefense,studenName,studenMat,teacherAdvision,teacherMat, date, local, status, observation};
            csvContent.append(String.join(",", row)).append("\n");
        }

        // Create a file chooser dialog to save the CSV file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save CSV File");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                // Write the CSV content to the file
                writer.write(csvContent.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private JPanel createFilterBar() {
        JPanel jPanel = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        PanelAndTxt panelAndTxt1 = componentSearch("Student Name");
        PanelAndTxt panelAndTxt2 = componentSearch("Teacher Name");
        PanelAndTxt panelAndTxt3 = componentSearch("Data start");
        PanelAndTxt panelAndTxt4 = componentSearch("Data end");
        PanelAndComboBox panelAndComboBox = componentSearchCombo("Aprovacão");

        txtStudentName = panelAndTxt1.jTextField;
        txtTeacherName = panelAndTxt2.jTextField;
        txtDateStart   = panelAndTxt3.jTextField;
        txtDateEnd     = panelAndTxt4.jTextField;
        this.jComboBox      = panelAndComboBox.jComboBox;
        btnSearch      = new JButton("Apply filter");
        btnSearch.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnSearch.setFont(new Font("Ubuntu", Font.BOLD, 15));
        btnSearch.setBackground(Color.decode("#32F551"));

        btnDownloadCSV      = new JButton("Download CSV");
        btnDownloadCSV.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnDownloadCSV.setFont(new Font("Ubuntu", Font.BOLD, 15));
        btnDownloadCSV.setBackground(Color.decode("#3DDBCE"));

        btnDownloadCSV.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnDownloadCSV.setBackground(Color.decode("#46F9B7"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnDownloadCSV.setBackground(Color.decode("#3DDBCE"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
                generateCSV();


            }
        });
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnSearch.setBackground(Color.decode("#8EFA26"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnSearch.setBackground(Color.decode("#32F551"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
                Reports.this.cleaning();
                Reports.this.update();

                jFrame.revalidate();
                jFrame.repaint();

            }
        });


        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(panelAndTxt1.jPanel)
                                        .addComponent(panelAndTxt2.jPanel)
                        )
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(panelAndTxt3.jPanel)
                                        .addComponent(panelAndTxt4.jPanel)
                        )
                        .addComponent(panelAndComboBox.jPanel)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDownloadCSV, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                        )

        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(panelAndTxt1.jPanel)
                                        .addComponent(panelAndTxt2.jPanel)
                        )
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(panelAndTxt3.jPanel)
                                        .addComponent(panelAndTxt4.jPanel)
                        )
                        .addComponent(panelAndComboBox.jPanel)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDownloadCSV, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        )

        );

        return jPanel;
    }

    private void cleaning(){
        for (Component component : jPanelTable.getComponents()) {
            jPanelTable.remove(component);
        }
        jPanelTable.add(Home.headTable());

    }
    private void update() {
        DefenseManager defenseManager = new DefenseManager();

        Reports.AnyObject selectedObject = (Reports.AnyObject) jComboBox.getSelectedItem();
        int selectedValue = selectedObject.getValue();

        if(Utils.isValidDate(txtDateStart.getText()) && txtDateEnd.getText().isEmpty()){

            String startDate = Utils.dateBrForDateEua(Utils.strToDateBr(txtDateStart.getText()));

            if(selectedValue == -1) defenses = defenseManager.selectAll(txtStudentName.getText(), txtTeacherName.getText(), startDate);
            else defenses = defenseManager.selectAll(txtStudentName.getText(), txtTeacherName.getText(), startDate, selectedValue);

        } else if (Utils.isValidDate(txtDateStart.getText()) && Utils.isValidDate(txtDateEnd.getText())) {

            String startDate = Utils.dateBrForDateEua(Utils.strToDateBr(txtDateStart.getText()));
            String endDate = Utils.dateBrForDateEua(Utils.strToDateBr(txtDateEnd.getText()));

            if(selectedValue == -1) defenses = defenseManager.selectAll(txtStudentName.getText(), txtTeacherName.getText(), startDate, endDate);
            else defenses = defenseManager.selectAll(txtStudentName.getText(), txtTeacherName.getText(), startDate, endDate, selectedValue);

        } else{
            if(selectedValue == -1) defenses = defenseManager.selectAll(txtStudentName.getText(), txtTeacherName.getText());
            else defenses = defenseManager.selectAll(txtStudentName.getText(), txtTeacherName.getText(), selectedValue);
        }


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

    private void createUIComponents() {
        jPanelHome = new JPanel();
        jPanelHome.setBackground(Color.decode("#ffffff"));

        GroupLayout layout = new GroupLayout(jPanelHome);
        jPanelHome.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        JLabel label = new JLabel("Relatórios");
        JPanel filters = createFilterBar();
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
                        .addComponent(filters, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(filters)
                        .addComponent(scrollPane)
        );

        // Set maximum size for panels inside jPanelTable
        for (Component component : jPanelTable.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getPreferredSize().height));
            }
        }
    }

    public void destroy(){
        jFrame.dispose();
    }

    private static class PanelAndComboBox {
        public JPanel jPanel;
        public JComboBox jComboBox;
    }
    private static class PanelAndTxt {
        public JPanel jPanel;
        public JTextField jTextField;
    }

    private class AnyObject{
        private int value;
        private String text;

        public AnyObject(int value, String text) {
            this.value = value;
            this.text = text;
        }
        @Override
        public String toString() {
            return text;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

}
