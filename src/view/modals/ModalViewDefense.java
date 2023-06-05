package view.modals;

import interfaces.Visibled;
import model.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

public class ModalViewDefense implements Visibled {

    private JFrame jFrame;
    private Defense defense;
    private JPanel tableOfTeachers;
    private JPanel footer;

    private LabelAndTitleContainer titleJobContainer;
    private ComboBoxAndTitleContainer typeDefenseContainer;
    private LabelAndTitleContainer localContainer;
    private LabelAndTitleContainer dateContainer;
    private LabelAndTitleContainer finalPontuation;
    private LabelAndTitleContainer observation;
    private CheckBoxAndTitleContainer statusDefense;
    private JButton alterComponents;
    private JButton closeWindow;
    private JButton deleteDefense;
    private JButton updateDefense;
    private JButton closeUpdate;
    public ModalViewDefense(Defense defense){
        this.setDefense(defense);
        initialize();
    }

    private void initialize(){

        jFrame = new JFrame();
        jFrame.setMaximumSize(new Dimension(850,800));
        jFrame.setMinimumSize(new Dimension(850,600));
        jFrame.setPreferredSize(new Dimension(850,800));
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.setBackground(Color.decode("#FFFFFF"));
        jFrame.setLocationRelativeTo(null);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));

        jPanel.setSize(850,900);
        jPanel.setPreferredSize(new Dimension(830,1200));

        jPanel.add(ModalViewDefense.topView(getDefense()));
        jPanel.add(containerDefenseInfo(getDefense()));
        jPanel.add(containerBoardOfTeacher(getDefense()));

        footer = footerAlterAndFinally();

        jPanel.add(footer);

        boolean flip = true;

        if(getDefense().getBoardOfTeachers()!= null){

            for(Teacher teacher: getDefense().getBoardOfTeachers()){

                if(flip){
                    tableOfTeachers.add(createTableRow(teacher.getName(), teacher.getRegister(), getDefense().getDefenseId(), teacher.getTeacherId(), "#F1F1F1"));
                    flip = !flip;
                }else{
                    tableOfTeachers.add(createTableRow(teacher.getName(),  teacher.getRegister(), getDefense().getDefenseId(), teacher.getTeacherId(), "#D9D9D9"));
                    flip = !flip;
                }

            }

        }

        JScrollPane scrollPane = new JScrollPane(jPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        titleJobContainer.textField.getDocument().addDocumentListener(new DocumentListener() {
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

                if(ModalViewDefense.this.getDefense() != null){
                    ModalViewDefense.this.getDefense().setDefenseTitle(titleJobContainer.textField.getText());
                }

            }
        });

        typeDefenseContainer.jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSelectionChange();
            }
            private void handleSelectionChange() {
                if (ModalViewDefense.this.getDefense() != null) {
                    ModalViewDefense.AnyObject selectedObject = (ModalViewDefense.AnyObject) typeDefenseContainer.jComboBox.getSelectedItem();
                    int selectedValue = selectedObject.getValue();
                    ModalViewDefense.this.getDefense().setTypeDefense(selectedValue);
                }
            }
        });

        localContainer.textField.getDocument().addDocumentListener(new DocumentListener() {
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

                if(ModalViewDefense.this.getDefense() != null){
                    ModalViewDefense.this.getDefense().setLocal(localContainer.textField.getText());
                }

            }
        });

        dateContainer.textField.getDocument().addDocumentListener(new DocumentListener() {
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

                if(ModalViewDefense.this.getDefense() != null){
                    ModalViewDefense.this.getDefense().setDate(dateContainer.textField.getText());
                }

            }
        });

        finalPontuation.textField.getDocument().addDocumentListener(new DocumentListener() {
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

                if(ModalViewDefense.this.getDefense() != null){
                    ModalViewDefense.this.getDefense().setFinalPontuation(Defense.stringToDouble(finalPontuation.textField.getText()));
                }

            }
        });

        observation.textField.getDocument().addDocumentListener(new DocumentListener() {
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

                if(ModalViewDefense.this.getDefense() != null){
                    ModalViewDefense.this.getDefense().setObservation(observation.textField.getText());
                }

            }
        });

        statusDefense.jCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSelectionChange();
            }
            private void handleSelectionChange() {
                if (ModalViewDefense.this.getDefense() != null) {
                    boolean isChecked = statusDefense.jCheckBox.isSelected();

                    if(isChecked)  ModalViewDefense.this.getDefense().setStatus(1);
                    else ModalViewDefense.this.getDefense().setStatus(0);
                }
            }
        });
        statusDefense.jCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSelectionChange();
            }
            private void handleSelectionChange() {
                if (ModalViewDefense.this.getDefense() != null) {
                    boolean isChecked = statusDefense.jCheckBox.isSelected();

                    if(isChecked)  ModalViewDefense.this.getDefense().setStatus(1);
                    else ModalViewDefense.this.getDefense().setStatus(0);
                }
            }
        });

        jFrame.add(scrollPane);

    }
    private JPanel footerAlterAndFinally() {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        alterComponents = new JButton("Alterar");
        alterComponents.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        alterComponents.setBackground(Color.decode("#49A3F2"));
        alterComponents.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        alterComponents.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                alterComponents.setBackground(Color.decode("#3763DB")); // Change button color on mouse enter
            }

            @Override
            public void mouseExited(MouseEvent e) {
                alterComponents.setBackground(Color.decode("#49A3F2")); // Change button color on mouse exit
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                titleJobContainer.textField.setEditable(true);
                titleJobContainer.textField.setFocusable(true);

                typeDefenseContainer.jComboBox.setEnabled(true);
                dateContainer.textField.setEditable(true);
                dateContainer.textField.setFocusable(true);
                localContainer.textField.setEditable(true);
                localContainer.textField.setFocusable(true);

                observation.textField.setEditable(true);
                observation.textField.setFocusable(true);
                finalPontuation.textField.setEditable(true);
                finalPontuation.textField.setFocusable(true);
                statusDefense.jCheckBox.setEnabled(true);

                deleteDefense.setVisible(true);
                updateDefense.setVisible(true);
                closeUpdate.setVisible(true);
                alterComponents.setVisible(false);
                closeWindow.setVisible(false);

            }
        });

        closeWindow = new JButton("Fechar");
        closeWindow.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        closeWindow.setBackground(Color.decode("#32F551"));
        closeWindow.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        closeWindow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeWindow.setBackground(Color.decode("#21DE76")); // Change button color on mouse enter
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeWindow.setBackground(Color.decode("#32F551")); // Change button color on mouse exit
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ModalViewDefense.this.setVisible(false);
            }
        });

        // Create invisible buttons
        updateDefense = new JButton("Update");
        updateDefense.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        updateDefense.setBackground(Color.decode("#49A3F2"));
        updateDefense.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        updateDefense.setVisible(false);

        updateDefense.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                updateDefense.setBackground(Color.decode("#3763DB")); // Change button color on mouse enter
            }

            @Override
            public void mouseExited(MouseEvent e) {
                updateDefense.setBackground(Color.decode("#49A3F2")); // Change button color on mouse exit
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(DefenseManager.update(defense)){
                    JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    disabilityButtons();
                }else{
                    JOptionPane.showMessageDialog(null, "Error updated.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        deleteDefense = new JButton("Delete Defense");
        deleteDefense.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        deleteDefense.setBackground(Color.decode("#FA002A"));
        deleteDefense.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        deleteDefense.setVisible(false);

        deleteDefense.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteDefense.setBackground(Color.decode("#C70021")); // Change button color on mouse enter
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteDefense.setBackground(Color.decode("#FA002A")); // Change button color on mouse exit
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    if(DefenseManager.delete(defense)){
                        JOptionPane.showMessageDialog(null, "Removido com sucesso!!");
                        ModalViewDefense.this.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error ao tentar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }


            }
        });

        closeUpdate = new JButton("Fechar Edição");
        closeUpdate.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        closeUpdate.setBackground(Color.decode("#F78484"));
        closeUpdate.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        closeUpdate.setVisible(false);

        closeUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeUpdate.setBackground(Color.decode("#E07D6C")); // Change button color on mouse enter
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeUpdate.setBackground(Color.decode("#F78484")); // Change button color on mouse exit
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                disabilityButtons();

            }
        });

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(alterComponents, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(closeWindow, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteDefense, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateDefense, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(closeUpdate, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(alterComponents, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addComponent(closeWindow, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteDefense, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateDefense, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addComponent(closeUpdate, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
        );

        return jPanel;
    }

    private void disabilityButtons() {

        titleJobContainer.textField.setEditable(false);
        titleJobContainer.textField.setFocusable(false);

        typeDefenseContainer.jComboBox.setEnabled(false);
        dateContainer.textField.setEditable(false);
        dateContainer.textField.setFocusable(false);
        localContainer.textField.setEditable(false);
        localContainer.textField.setFocusable(false);

        observation.textField.setEditable(false);
        observation.textField.setFocusable(false);
        finalPontuation.textField.setEditable(false);
        finalPontuation.textField.setFocusable(false);
        statusDefense.jCheckBox.setEnabled(false);

        alterComponents.setVisible(true);
        closeWindow.setVisible(true);

        updateDefense.setVisible(false);
        closeUpdate.setVisible(false);
        deleteDefense.setVisible(false);

    }

    private static JPanel topView(Defense defense) {
        JPanel topView = new JPanel();
        topView.setBackground(Color.decode("#FFFFFF"));

        GroupLayout layout = new GroupLayout(topView);
        topView.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel jLabel = new JLabel("Informações");
        jLabel.setFont(new Font("Ubuntu", Font.BOLD, 40));
        jLabel.setBackground(Color.decode("#FFFFFF"));

        JPanel jPanel = ModalViewDefense.createTopContainer("Aluno", defense.getStudentDefending());
        JPanel jPanel1 = ModalViewDefense.createTopContainer("Professor", defense.getTeacherAdvisor());

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel)
                                .addComponent(jPanel1))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel)
                                .addComponent(jPanel1))
        );

        return topView;
    }


    private JPanel containerBoardOfTeacher(Defense defense){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel jLabel = new JLabel("Informações de banca");
        jLabel.setFont(new Font("Ubuntu", Font.BOLD, 40));

        this.tableOfTeachers = ModalViewDefense.tableCreate(defense);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(this.tableOfTeachers)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(this.tableOfTeachers)
                        .addGap(20)
        );


        return  jPanel;

    }

    private static JPanel tableCreate(Defense defense){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));

        jPanel.add(ModalViewDefense.createHeadTable());

        return jPanel;

    }

    private JPanel createTableRow(String name, String enrollment, int idDefense, int idTeacher, String color) {
        JPanel rowPanel = new JPanel();

        GroupLayout layout = new GroupLayout(rowPanel);
        rowPanel.setLayout(layout);
        rowPanel.setBackground(Color.decode("#FFFFFF"));


//        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.decode(color));

        JLabel enrollmentLabel = new JLabel(enrollment);
        enrollmentLabel.setOpaque(true);
        enrollmentLabel.setBackground(Color.decode(color));



        JButton actionButton = new JButton("Delete");
        actionButton.setOpaque(true);
        actionButton.setBackground(Color.decode("#F78484"));
        actionButton.putClientProperty("idDefense", idDefense);
        actionButton.putClientProperty("idTeacher", idTeacher);
        actionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {

                    int defenseId = (int) actionButton.getClientProperty("idDefense");
                    int idTeacher = (int) actionButton.getClientProperty("idTeacher");

                    if(BoardOfTeachers.delete(defenseId, idTeacher)){
                        JOptionPane.showMessageDialog(null,"Removido com sucesso!!");
                        int componentCount = tableOfTeachers.getComponentCount();
                        if (componentCount > 0) {
                            int lastIndex = componentCount - 1;
                            Component lastComponent = tableOfTeachers.getComponent(lastIndex);
                            tableOfTeachers.remove(lastComponent);
                            tableOfTeachers.revalidate();
                            tableOfTeachers.repaint();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro ao tentar apagar", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }


            }
        });

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5)
                                        .addComponent(enrollmentLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5)
                                        .addComponent(actionButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5)
                                        .addComponent(enrollmentLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5)
                                        .addComponent(actionButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5)
                        )
        );



        return rowPanel;
    }

    private static JPanel createHeadTable() {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

//        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        JLabel nomeTeacher = new JLabel("Professor");
        nomeTeacher.setOpaque(true);
        nomeTeacher.setBackground(Color.decode("#D9D9D9"));
        JLabel mat = new JLabel("Matrícula");
        mat.setOpaque(true);
        mat.setBackground(Color.decode("#D9D9D9"));
        JLabel action = new JLabel("Ação");
        action.setOpaque(true);
        action.setBackground(Color.decode("#D9D9D9"));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(nomeTeacher, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5)
                                        .addComponent(mat, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5)
                                        .addComponent(action, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nomeTeacher, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5)
                                        .addComponent(mat, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5)
                                        .addComponent(action, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5)
                        )
        );

        return jPanel;
    }

    private JPanel containerDefenseInfo(Defense defense){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));

        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel jLabel = new JLabel("Dados da Defesa");
        jLabel.setFont(new Font("Ubuntu", Font.BOLD, 40));

        this.titleJobContainer = ModalViewDefense.containerLabelAndTitle("Título do trabalho", defense.getDefenseTitle());
        JPanel titleJob = this.titleJobContainer.panel;

        this.typeDefenseContainer = containerComboBoxAndTitle("Tipo de defesa", Defense.parseTypeDefenseFormatString(defense.getTypeDefense()));
        JPanel typeDefense = this.typeDefenseContainer.panel;

        String inputFormat = "yyyy-MM-dd";
        String outputFormat = "dd/MM/yyyy";

        // Create input and output date format objects
        DateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
        DateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

        try {
            // Parse the input date string into a Date object
            Date date = inputDateFormat.parse(defense.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());

            // Format the Date object into the desired output format
            String outputDate = outputDateFormat.format(date);

            this.dateContainer = ModalViewDefense.containerLabelAndTitle("Data", outputDate);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JPanel dateItem = this.dateContainer.panel;

        this.localContainer = ModalViewDefense.containerLabelAndTitle("Local", defense.getLocal());
        JPanel localItem = this.localContainer.panel;

        this.finalPontuation = ModalViewDefense.containerLabelAndTitle("Pontuação", Double.toString(defense.getFinalPontuation()));
        this.statusDefense = ModalViewDefense.containerLabelAndTitle("Status", defense.getStatus());
        this.observation =ModalViewDefense.containerLabelAndTitle("Observacão", defense.getObservation());

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                            layout.createSequentialGroup()
                                    .addComponent(jLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGap(5)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(titleJob)
                                        .addComponent(typeDefense)
                        )
                        .addGap(5)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(dateItem)
                                        .addComponent(localItem)
                        )
                        .addGap(5)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(this.finalPontuation.panel)
                                        .addComponent(this.statusDefense.panel)
                        )
                        .addGap(5)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(this.observation.panel)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGap(10)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(titleJob)
                                        .addComponent(typeDefense)
                        )
                        .addGap(10)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(dateItem)
                                        .addComponent(localItem)
                        )
                        .addGap(10)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.finalPontuation.panel)
                                        .addComponent(this.statusDefense.panel)

                        )
                        .addGap(10)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.observation.panel)
                        )
        );



        return jPanel;
    }

    private static LabelAndTitleContainer containerLabelAndTitle(String title, String content) {
        LabelAndTitleContainer container = new LabelAndTitleContainer();

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));

        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        JLabel titleLabel = new JLabel(title);
        JTextField titleJob = new JTextField();
        titleJob.setFocusable(false);
        titleJob.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        titleJob.setEditable(false);
        titleJob.setBackground(Color.decode("#D9D9D9"));
        titleJob.setSize(new Dimension(500, 80));
        titleJob.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        titleJob.setText(content);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel)
                        .addComponent(titleJob, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addComponent(titleJob, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        );

        container.panel = jPanel;
        container.textField = titleJob;

        return container;
    }

    private static CheckBoxAndTitleContainer containerLabelAndTitle(String title, int status) {
        CheckBoxAndTitleContainer container = new CheckBoxAndTitleContainer();

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));

        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        JCheckBox jCheckBox = new JCheckBox();

        JLabel titleLabel = new JLabel(title);

        jCheckBox.setFocusable(false);
        jCheckBox.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        jCheckBox.setEnabled(false);
        jCheckBox.setBackground(Color.decode("#D9D9D9"));
        jCheckBox.setSize(new Dimension(500, 80));
        jCheckBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jCheckBox.setText("Aprovado");

        // Set the checked state based on the status value
        jCheckBox.setSelected(status == 1);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel)
                        .addComponent(jCheckBox, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addComponent(jCheckBox, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        );

        container.panel = jPanel;
        container.jCheckBox = jCheckBox;

        return container;
    }


    private ComboBoxAndTitleContainer containerComboBoxAndTitle(String title, String content) {
        ComboBoxAndTitleContainer container = new ComboBoxAndTitleContainer();

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));

        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        JLabel titleLabel = new JLabel(title);
        JComboBox comboBox = new JComboBox();

        comboBox.addItem(new ModalViewDefense.AnyObject(1, "Defesa de projeto final/TCC"));
        comboBox.addItem(new ModalViewDefense.AnyObject(2, "Defesa de qualificação de mestrado"));
        comboBox.addItem(new ModalViewDefense.AnyObject(3, "Defesa de dissertação de mestrado"));
        comboBox.addItem(new ModalViewDefense.AnyObject(4, "Defesa de qualificação de doutorado"));
        comboBox.addItem(new ModalViewDefense.AnyObject(5, "Defesa de tese de doutorado"));
        comboBox.addItem(new ModalViewDefense.AnyObject(6, "Defesa de artigo"));

        for (int i = 0; i < comboBox.getItemCount(); i++) {
            ModalViewDefense.AnyObject item = (AnyObject) comboBox.getItemAt(i);
            if (item.getText().equals(content)) {
                comboBox.setSelectedItem(item);
                break;
            }
        }

        comboBox.setFocusable(false);
        comboBox.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        comboBox.setEnabled(false);
        comboBox.setBackground(Color.decode("#D9D9D9"));
        comboBox.setSize(new Dimension(500, 80));
        comboBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel)
                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        );

        container.panel = jPanel;
        container.jComboBox = comboBox;

        return container;
    }

    private static JPanel createTopContainer(String title, Person person){

        JPanel jPanel5 = new JPanel();
        jPanel5.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(layout);

        layout.setAutoCreateGaps(true);

        JLabel jLabel1 = new JLabel(title);
        JTextField student_name = new JTextField();
        student_name.setFocusable(false);
        student_name.setText(person.getName());
        student_name.setFont(new Font("Ubuntu", Font.BOLD, 18));
        student_name.setEditable(false);
        student_name.setBackground( Color.decode("#D9D9D9"));
        student_name.setSize(new Dimension(500,80));
        student_name.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));



        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                        )

                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(student_name, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                        )

        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                        )

                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(student_name, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)

        );

        return jPanel5;

    }
    @Override
    public void setVisible(boolean value) {

        jFrame.setVisible(value);

    }

    public Defense getDefense() {
        return defense;
    }

    public void setDefense(Defense defense) {
        this.defense = defense;
    }

    private static class LabelAndTitleContainer {
        public JPanel panel;
        public JTextField textField;
    }

    private static class ComboBoxAndTitleContainer {
        public JPanel panel;
        public JComboBox jComboBox;
    }
    private static class CheckBoxAndTitleContainer {
        public JPanel panel;
        public JCheckBox jCheckBox;
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


