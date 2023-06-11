package view.modals;

import interfaces.Visibled;
import model.*;
import view.modalfindteacher.ModalFindTeacher;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
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
    private LabelAndTitleContainer hourContainer;
    private LabelAndTitleContainer finalPontuation;
    private LabelAndTitleContainer observation;
    private CheckBoxAndTitleContainer statusDefense;
    private JButton alterComponents;
    private JButton certificatePrint;
    private JButton callToDefense;
    private JButton closeWindow;
    private JButton deleteDefense;
    private JButton updateDefense;
    private JButton closeUpdate;
    private JButton btnChangeTeacherOfBoards;
    private Visibled afterView;
    private Defense oldDefense;
    private JScrollPane scrollPane;
    public ModalViewDefense(Defense defense, Visibled afterView){
        this.setDefense(defense);
        this.afterView = afterView;
        initialize();
    }
    public ModalViewDefense(Defense defense){
        this.setDefense(defense);
        this.afterView = null;
        initialize();
    }

    private void initialize(){

        jFrame = new JFrame();

        jFrame.setMinimumSize(new Dimension(850,600));
        jFrame.setPreferredSize(new Dimension(1500,1500));
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBackground(Color.decode("#FFFFFF"));


        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));

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

        scrollPane = new JScrollPane(jPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        titleJobContainer.jTextArea.getDocument().addDocumentListener(new DocumentListener() {
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
                    ModalViewDefense.this.getDefense().setDefenseTitle(titleJobContainer.jTextArea.getText());
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
                    if(Utils.isValidDate(dateContainer.textField.getText())){
                        ModalViewDefense.this.getDefense().setDate(Utils.strToDateBr(dateContainer.textField.getText()));
                    }

                }

            }
        });

        hourContainer.textField.getDocument().addDocumentListener(new DocumentListener() {
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
                    if(Utils.isValidTime(hourContainer.textField.getText())){
                        String date = Utils.dateForBr(defense.getDate()) + " " + hourContainer.textField.getText();
                        ModalViewDefense.this.getDefense().setDate(Utils.strToDateBrWithHour(date));
                    }

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
                    if(!finalPontuation.textField.getText().isEmpty()) ModalViewDefense.this.getDefense().setFinalPontuation(Defense.stringToDouble(finalPontuation.textField.getText()));
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

        jFrame.getContentPane().add(scrollPane);

        jFrame.pack();

    }
    private JPanel footerAlterAndFinally() {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#0ABFBF"));
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
                titleJobContainer.jTextArea.setEditable(true);
                titleJobContainer.jTextArea.setFocusable(true);

                typeDefenseContainer.jComboBox.setEnabled(true);
                dateContainer.textField.setEditable(true);
                dateContainer.textField.setFocusable(true);

                hourContainer.textField.setEditable(true);
                hourContainer.textField.setFocusable(true);

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
                certificatePrint.setVisible(false);
                callToDefense.setVisible(false);
                closeWindow.setVisible(false);

                btnChangeTeacherOfBoards.setVisible(true);

                oldDefense = new Defense();
                oldDefense.clone(defense);

            }
        });

        callToDefense = new JButton("Call Defense");
        callToDefense.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        callToDefense.setBackground(Color.decode("#D7F205"));
        callToDefense.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        callToDefense.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        callToDefense.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save HTML File");

                // Set file filter to accept only HTML files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML Files", "html");
                fileChooser.setFileFilter(filter);


                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();

                    // Check if the file name has an extension
                    String fileName = fileToSave.getName();

                    if (!fileName.contains(".")) {
                        // Add .html extension if no extension is specified
                        fileToSave = new File(fileToSave.getAbsolutePath() + ".html");
                    } else {
                        // Check if the extension is different from .html
                        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                        if (!fileExtension.equalsIgnoreCase(".html")) {
                            // Replace the extension with .html
                            fileToSave = new File(fileToSave.getAbsolutePath().replace(fileExtension, ".html"));
                        }
                    }

                    if(Defense.callForDefense(String.valueOf(fileToSave.getAbsoluteFile()), defense))
                        JOptionPane.showMessageDialog(null, "Salvo com sucesso!!");

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                callToDefense.setBackground(Color.decode("#F9D107"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                callToDefense.setBackground(Color.decode("#D7F205"));
            }
        });

        certificatePrint = new JButton("Certificado");
        certificatePrint.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        certificatePrint.setBackground(Color.decode("#E8940C"));
        certificatePrint.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        certificatePrint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                certificatePrint.setBackground(Color.decode("#FF4E00")); // Change button color on mouse enter
            }

            @Override
            public void mouseExited(MouseEvent e) {
                certificatePrint.setBackground(Color.decode("#E8940C")); // Change button color on mouse exit
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                new Certificate(ModalViewDefense.this.getDefense()).printed();

            }
        });

        if(defense != null && defense.getStatus() == 1){
            certificatePrint.setVisible(true);
            callToDefense.setVisible(false);
        }else{
            certificatePrint.setVisible(false);
            callToDefense.setVisible(true);
        }


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

                if(DefenseManager.update(defense, oldDefense)){
                    JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    disabilityButtons(false);
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
        closeUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

                disabilityButtons(true);

            }
        });

        alterComponents.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        certificatePrint.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeWindow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteDefense.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateDefense.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(alterComponents, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(certificatePrint, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(callToDefense, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(closeWindow, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteDefense, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(updateDefense, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(closeUpdate, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addGap(20)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(10)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(alterComponents, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(certificatePrint, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(callToDefense, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(closeWindow, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteDefense, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(updateDefense, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(closeUpdate, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addGap(20)
                        )
                        .addGap(10)
        );

        return jPanel;
    }
    private void disabilityButtons(boolean clear) {

        titleJobContainer.jTextArea.setEditable(false);
        titleJobContainer.jTextArea.setFocusable(false);
        typeDefenseContainer.jComboBox.setEnabled(false);
        dateContainer.textField.setEditable(false);
        dateContainer.textField.setFocusable(false);
        hourContainer.textField.setEditable(false);
        hourContainer.textField.setFocusable(false);
        localContainer.textField.setEditable(false);
        localContainer.textField.setFocusable(false);
        observation.textField.setEditable(false);
        observation.textField.setFocusable(false);
        finalPontuation.textField.setEditable(false);
        finalPontuation.textField.setFocusable(false);
        statusDefense.jCheckBox.setEnabled(false);

        if(clear && oldDefense != null) defense.clone(oldDefense);

        titleJobContainer.jTextArea.setText(defense.getDefenseTitle());
        dateContainer.textField.setText(Utils.dateForBr(defense.getDate()));
        hourContainer.textField.setText(Utils.hourOfTheDate(defense.getDate()));
        localContainer.textField.setText(defense.getLocal());
        observation.textField.setText(defense.getObservation());
        finalPontuation.textField.setText( Double.toString(defense.getFinalPontuation()));
        statusDefense.jCheckBox.setSelected(defense.getStatus() == 1);

        typeDefenseContainer.jComboBox.setEnabled(false);

        for (int i = 0; i <  typeDefenseContainer.jComboBox.getItemCount(); i++) {
            ModalViewDefense.AnyObject item = (AnyObject)  typeDefenseContainer.jComboBox.getItemAt(i);
            if (item.getValue() == defense.getTypeDefense()) {
                typeDefenseContainer.jComboBox.setSelectedItem(item);
                break;
            }
        }

        alterComponents.setVisible(true);

        if(defense != null && defense.getStatus() == 1){
            certificatePrint.setVisible(true);
            callToDefense.setVisible(false);
        }else{
            certificatePrint.setVisible(false);
            callToDefense.setVisible(true);
        }

        updateViewAndTeacherBoards(defense.getBoardOfTeachers());

        closeWindow.setVisible(true);

        updateDefense.setVisible(false);
        closeUpdate.setVisible(false);
        deleteDefense.setVisible(false);
        btnChangeTeacherOfBoards.setVisible(false);

    }
    private static JPanel contentTopView(Defense defense){
        JPanel content = new JPanel();

        GroupLayout layout = new GroupLayout(content);
        content.setLayout(layout);
        layout.setAutoCreateGaps(true);

        content.setBackground(Color.decode("#fffff7"));

        JPanel jPanel = ModalViewDefense.createTopContainer("Aluno", defense.getStudentDefending());
        JPanel jPanel1 = ModalViewDefense.createTopContainer("Professor", defense.getTeacherAdvisor());

        jPanel.setBackground(Color.decode("#fffff7"));
        jPanel1.setBackground(Color.decode("#fffff7"));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )

        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        )
        );

        return content;
    }
    private static JPanel topView(Defense defense) {
        JPanel topView = new JPanel();

        topView.setBackground(Color.decode("#0ABFBF"));

        GroupLayout layout = new GroupLayout(topView);
        topView.setLayout(layout);
        layout.setAutoCreateGaps(true);

        JLabel jLabel = new JLabel("Informações");
        jLabel.setFont(new Font("Ubuntu", Font.BOLD, 40));
        jLabel.setForeground(Color.decode("#ffffff"));
        jLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel content = contentTopView(defense);


        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(content, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addComponent(content, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        );

        return topView;
    }
    private JPanel containerBoardOfTeacher(Defense defense){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#0ABFBF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);


        JLabel jLabel = new JLabel("Informações de banca");
        jLabel.setFont(new Font("Ubuntu", Font.BOLD, 40));
        jLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jLabel.setForeground(Color.decode("#ffffff"));

        btnChangeTeacherOfBoards = new JButton("Change Board");
        btnChangeTeacherOfBoards.setBackground(Color.decode("#D7F205"));
        btnChangeTeacherOfBoards.setFocusPainted(false);
        btnChangeTeacherOfBoards.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        btnChangeTeacherOfBoards.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnChangeTeacherOfBoards.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnChangeTeacherOfBoards.setVisible(false);

        btnChangeTeacherOfBoards.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new ModalFindTeacher(ModalViewDefense.this, ModalViewDefense.this.getDefense().getBoardOfTeachers())
                        .setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnChangeTeacherOfBoards.setBackground(Color.decode("#F9D107"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnChangeTeacherOfBoards.setBackground(Color.decode("#D7F205"));
            }
        });

        this.tableOfTeachers = ModalViewDefense.tableCreate(defense);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(jLabel, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnChangeTeacherOfBoards, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addGap(10)

                        )
                        .addComponent(this.tableOfTeachers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(jLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnChangeTeacherOfBoards, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        )
                        .addComponent(this.tableOfTeachers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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


        JTextArea nameLabel = new JTextArea(name);
        nameLabel.setWrapStyleWord(true);
        nameLabel.setLineWrap(true);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.decode(color));

        JLabel enrollmentLabel = new JLabel(enrollment);
        enrollmentLabel.setOpaque(true);
        enrollmentLabel.setBackground(Color.decode(color));


        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(enrollmentLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)

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
                        )
        );



        return rowPanel;
    }
    private static JPanel createHeadTable() {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        JLabel nomeTeacher = new JLabel("Professor");
        nomeTeacher.setOpaque(true);
        nomeTeacher.setBackground(Color.decode("#D9D9D9"));
        JLabel mat = new JLabel("Matrícula");
        mat.setOpaque(true);
        mat.setBackground(Color.decode("#D9D9D9"));


        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(nomeTeacher, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(mat, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)

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
                        )
        );

        return jPanel;
    }
    private JPanel contentDefenseInfo(Defense defense){
        JPanel jPanel = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        this.titleJobContainer = ModalViewDefense.containerLabelAndTitleTxt("Título do trabalho", defense.getDefenseTitle());
        JPanel titleJob = this.titleJobContainer.panel;
        this.titleJobContainer.panel.setBackground(Color.decode("#fffff7"));

        this.typeDefenseContainer = containerComboBoxAndTitle("Tipo de defesa", Defense.parseTypeDefenseFormatString(defense.getTypeDefense()));
        JPanel typeDefense = this.typeDefenseContainer.panel;
        this.typeDefenseContainer.panel.setBackground(Color.decode("#fffff7"));

        this.dateContainer = ModalViewDefense.containerLabelAndTitle("Data", Utils.dateForBr(defense.getDate()));
        JPanel dateItem = this.dateContainer.panel;
        this.dateContainer.panel.setBackground(Color.decode("#fffff7"));
        this.hourContainer = ModalViewDefense.containerLabelAndTitle("Hora", Utils.hourOfTheDate(defense.getDate()));
        this.hourContainer.panel.setBackground(Color.decode("#fffff7"));

        this.localContainer = ModalViewDefense.containerLabelAndTitle("Local", defense.getLocal());
        JPanel localItem = this.localContainer.panel;
        this.localContainer.panel.setBackground(Color.decode("#fffff7"));

        this.finalPontuation = ModalViewDefense.containerLabelAndTitle("Pontuação", Double.toString(defense.getFinalPontuation()));
        this.finalPontuation.panel.setBackground(Color.decode("#fffff7"));
        this.statusDefense = ModalViewDefense.containerLabelAndTitle("Status", defense.getStatus());
        this.statusDefense.panel.setBackground(Color.decode("#fffff7"));
        this.observation =ModalViewDefense.containerLabelAndTitle("Observacão", defense.getObservation());
        this.observation.panel.setBackground(Color.decode("#fffff7"));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(5)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(titleJob, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(typeDefense, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGap(5)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dateItem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.hourContainer.panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGap(5)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(localItem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.finalPontuation.panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                        )
                        .addGap(5)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.statusDefense.panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.observation.panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(titleJob)
                                        .addComponent(typeDefense)
                        )
                        .addGap(10)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(dateItem)
                                        .addComponent(this.hourContainer.panel)
                        )
                        .addGap(10)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(localItem)
                                        .addComponent(this.finalPontuation.panel)
                        )
                        .addGap(10)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(this.statusDefense.panel)
                                        .addComponent(this.observation.panel)
                        )
        );

        return jPanel;

    }
    private JPanel containerDefenseInfo(Defense defense){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#0ABFBF"));

        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        JLabel jLabel = new JLabel("Dados da Defesa");
        jLabel.setFont(new Font("Ubuntu", Font.BOLD, 40));
        jLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jLabel.setForeground(Color.decode("#ffffff"));

        JPanel content = contentDefenseInfo(defense);
        content.setBackground(Color.decode("#fffff7"));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(content, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addComponent(content, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
        );


        return jPanel;
    }
    private static LabelAndTitleContainer containerLabelAndTitleTxt(String title, String content) {
        LabelAndTitleContainer container = new LabelAndTitleContainer();

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#FFFFFF"));

        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        JLabel titleLabel = new JLabel(title);
        JTextArea titleJob = new JTextArea();
        titleJob.setFocusable(false);
        titleJob.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        titleJob.setEditable(false);
        titleJob.setBackground(Color.decode("#D9D9D9"));
        titleJob.setSize(new Dimension(500, 80));
        titleJob.setLineWrap(true);
        titleJob.setWrapStyleWord(true);

        titleJob.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        titleJob.setText(content);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(titleLabel)
                        .addComponent(titleJob, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addComponent(titleJob, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        );

        container.panel = jPanel;
        container.jTextArea = titleJob;

        return container;
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

    public void updateViewAndTeacherBoards(ArrayList<Teacher> teachers){

        for(Component component: tableOfTeachers.getComponents()){
            tableOfTeachers.remove(component);
        }

        tableOfTeachers.add(ModalViewDefense.createHeadTable());

        boolean flip = true;

        if(teachers != null) this.defense.setBoardOfTeachers(teachers);

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

        tableOfTeachers.revalidate();
        tableOfTeachers.repaint();

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
        public JTextArea jTextArea;
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


