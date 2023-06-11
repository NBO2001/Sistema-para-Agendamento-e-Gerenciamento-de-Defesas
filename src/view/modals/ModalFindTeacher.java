package view.modals;

import interfaces.Visibled;
import model.Teacher;
import model.TeacherManager;
import view.cadastrodefesa.CadastroDefesaVariant04;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class ModalFindTeacher implements Visibled {

    private JFrame jFrame;
    private JPanel tablePanel;

    private JButton btnFinish;
    private JTextField txtSeach;
    private JPanel boardOfTeacherSearch;
    private JPanel boardOfTeacherSelected;


    private ArrayList<Teacher> teachersSelected;
    private ArrayList<Teacher> teachersOptions;

    private String oldSeach;

    private CadastroDefesaVariant04 cadastroDefesaVariant04;
    private ModalViewDefense modalViewDefense;

    public ModalFindTeacher(String search, CadastroDefesaVariant04 cadastroDefesaVariant04, ArrayList<Teacher> teachersSelected){
        this.teachersSelected = teachersSelected;


        if(cadastroDefesaVariant04 != null){
            this.cadastroDefesaVariant04 = cadastroDefesaVariant04;
        }

        if(search != null){
            this.teachersOptions = TeacherManager.selectAll(search);
            this.oldSeach = search;
        }else{
            this.teachersOptions = TeacherManager.selectAll("");
        }

        initialize();

    }

    public ModalFindTeacher( ModalViewDefense modalViewDefense, ArrayList<Teacher> teachersSelected){
        this.teachersSelected = teachersSelected;

        if(modalViewDefense != null){
            this.modalViewDefense = modalViewDefense;
        }

        this.teachersOptions = TeacherManager.selectAll("");

        initialize();

    }
    public ModalFindTeacher(String search, CadastroDefesaVariant04 cadastroDefesaVariant04){
        teachersSelected = new ArrayList<Teacher>();
        initialize();

        if(cadastroDefesaVariant04 != null){
            this.cadastroDefesaVariant04 = cadastroDefesaVariant04;
        }

        if(search != null){
            this.teachersOptions = TeacherManager.selectAll(search);
            this.oldSeach = search;
        }else{
            this.teachersOptions = TeacherManager.selectAll("");
        }

    }
    public ModalFindTeacher(){
        this(null, null, new ArrayList<Teacher>());
    }

    private void initialize(){

        jFrame = new JFrame();
        jFrame.setSize(1000, 900);
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);

        // Main Panel
        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);

        JPanel header = headerCreate();

        JPanel body = bodyCreate();

        JPanel footer = footerCreate();


        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(header, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(body, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(footer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(header, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                        .addComponent(body, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                        .addComponent(footer, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        );

        jFrame.add(mainPanel);


    }


    @Override
    public void setVisible(boolean value) {
        jFrame.setVisible(value);
    }
    private JPanel headerCreate(){

        JPanel jPanel = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        jPanel.setBackground(Color.decode("#0ABFBF"));
        JLabel title = new JLabel("Teacher of Board");
        title.setForeground(Color.decode("#ffffff"));
        title.setFont(new Font("Ubuntu", Font.BOLD, 40));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        boardOfTeacherSelected = createTableHead();
        JScrollPane scrollPane = new JScrollPane(boardOfTeacherSelected);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
        );

        return  jPanel;
    }

    private WrapperButton wrapperButtonGenerator(String label, String colorPanel, String colorButton) {
        WrapperButton wrapperButton = new WrapperButton();

        JPanel jPanel = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        jPanel.setBackground(Color.decode(colorPanel));

        JButton btnAddTeacher = new JButton(label);
        btnAddTeacher.setPreferredSize(new Dimension(100, 40));
        btnAddTeacher.setBackground(Color.decode(colorButton));

        btnAddTeacher.setBorder(new EmptyBorder(5,5,5,5));
        btnAddTeacher.setFont(new Font("Ubuntu", Font.BOLD, 40));
        btnAddTeacher.setForeground(Color.decode("#ffffff"));
        btnAddTeacher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAddTeacher, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addGap(100)
                        )

        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(btnAddTeacher, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        )

        );


        wrapperButton.jPanel = jPanel;
        wrapperButton.jButton = btnAddTeacher;
        return wrapperButton;
    }

    private JPanel rowComponentCreator(Teacher teacher, String color){
        JPanel jPanel = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        jPanel.setBackground(Color.decode(color));

        JTextArea nameTeacher = new JTextArea(teacher.getName());
        nameTeacher.setBorder(new EmptyBorder(5,5,5,5));
        nameTeacher.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        nameTeacher.setLineWrap(true);
        nameTeacher.setWrapStyleWord(true);
        nameTeacher.setEditable(false);
        nameTeacher.setBackground(Color.decode(color));
        nameTeacher.setBorder(null);

        JTextArea matTeacher = new JTextArea(teacher.getRegister());
        matTeacher.setBorder(new EmptyBorder(5,5,5,5));
        matTeacher.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        matTeacher.setLineWrap(true);
        matTeacher.setWrapStyleWord(true);
        matTeacher.setEditable(false);
        matTeacher.setBackground(Color.decode(color));
        matTeacher.setBorder(null);

        WrapperButton wrapperButton = wrapperButtonGenerator("+",color ,"#D7F205");
        wrapperButton.jButton.setToolTipText("Adicionar Professor");
        wrapperButton.jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                updateAndInsert(teacher);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                wrapperButton.jButton.setBackground(Color.decode("#78DB07"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                wrapperButton.jButton.setBackground(Color.decode("#D7F205"));
            }
        });



        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nameTeacher, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(matTeacher, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(wrapperButton.jPanel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(nameTeacher, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(matTeacher, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(wrapperButton.jPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        )
        );



        return jPanel;
    }

    private JPanel rowComponentCreatorHeard(Teacher teacher, String color){
        JPanel jPanel = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);
        jPanel.setBackground(Color.decode(color));

        JTextArea nameTeacher = new JTextArea(teacher.getName());
        nameTeacher.setBorder(new EmptyBorder(5,5,5,5));
        nameTeacher.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        nameTeacher.setLineWrap(true);
        nameTeacher.setWrapStyleWord(true);
        nameTeacher.setEditable(false);
        nameTeacher.setBackground(Color.decode(color));
        nameTeacher.setBorder(null);

        JTextArea matTeacher = new JTextArea(teacher.getRegister());
        matTeacher.setBorder(new EmptyBorder(5,5,5,5));
        matTeacher.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        matTeacher.setLineWrap(true);
        matTeacher.setWrapStyleWord(true);
        matTeacher.setEditable(false);
        matTeacher.setBackground(Color.decode(color));
        matTeacher.setBorder(null);

        WrapperButton wrapperButton = wrapperButtonGenerator("-",color ,"#D93A2B");
        wrapperButton.jButton.setToolTipText("Remover Professor");
        wrapperButton.jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                updateAndRemove(teacher);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                wrapperButton.jButton.setBackground(Color.decode("#F02450"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                wrapperButton.jButton.setBackground(Color.decode("#D93A2B"));
            }
        });



        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nameTeacher, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(matTeacher, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(wrapperButton.jPanel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(nameTeacher, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(matTeacher, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(wrapperButton.jPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        )
        );



        return jPanel;
    }

    private void updateAndInsert(Teacher teacher) {

        boolean updateIsNecessary = true;
        for(Teacher teacher1: this.teachersSelected){
            if(teacher1.getTeacherId() == teacher.getTeacherId()){
                updateIsNecessary = false;
                break;
            }
        }

        if(updateIsNecessary){

            this.teachersSelected.add(teacher);

            for(Component component: boardOfTeacherSelected.getComponents()) boardOfTeacherSelected.remove(component);

            boolean flag = true;

            for(Teacher teacher1: this.teachersSelected ){

                if(flag){
                    boardOfTeacherSelected.add(rowComponentCreatorHeard(teacher1, "#EFEFEF"));
                }else{
                    boardOfTeacherSelected.add(rowComponentCreatorHeard(teacher1, "#FFFFF7"));
                }
                flag = !flag;

            }
            boardOfTeacherSelected.revalidate();
            boardOfTeacherSelected.repaint();



        }

    }

    private void updateAndRemove(Teacher teacher) {

        Iterator<Teacher> iterator = this.teachersSelected.iterator();
        while (iterator.hasNext()) {
            Teacher teacher1 = iterator.next();
            if (teacher1.getTeacherId() == teacher.getTeacherId()) {
                iterator.remove();

            }
        }

        for (Component component : boardOfTeacherSelected.getComponents()) {
            boardOfTeacherSelected.remove(component);
        }

        boolean flag = true;
        for (Teacher teacher1 : this.teachersSelected) {
            if (flag) {
                boardOfTeacherSelected.add(rowComponentCreatorHeard(teacher1, "#EFEFEF"));
            } else {
                boardOfTeacherSelected.add(rowComponentCreatorHeard(teacher1, "#FFFFF7"));
            }
            flag = !flag;
        }

        boardOfTeacherSelected.revalidate();
        boardOfTeacherSelected.repaint();
    }

    private JPanel createTable(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        boolean flag = true;
        for(Teacher teacher: this.teachersOptions){
            if(flag){
                jPanel.add(rowComponentCreator(teacher, "#EFEFEF"));
            }else{
                jPanel.add(rowComponentCreator(teacher, "#FFFFF7"));
            }
            flag = !flag;
        }

        return  jPanel;
    }

    private JPanel createTableHead(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        boolean flag = true;
        for(Teacher teacher: this.teachersSelected){
            if(flag){
                jPanel.add(rowComponentCreatorHeard(teacher, "#EFEFEF"));
            }else{
                jPanel.add(rowComponentCreatorHeard(teacher, "#FFFFF7"));
            }
            flag = !flag;
        }

        return  jPanel;
    }
    private JPanel bodyCreate(){

        JPanel jPanel = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        txtSeach = new JTextField();
        txtSeach.setBackground(Color.decode("#D9D9D9"));
        txtSeach.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtSeach.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        if(oldSeach != null) txtSeach.setText(oldSeach);

        txtSeach.getDocument().addDocumentListener(new DocumentListener() {
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

                updateBoardOfSearch(txtSeach.getText());



            }
        });

        jPanel.setBackground(Color.decode("#0ABFBF"));
        JLabel title = new JLabel("Search Teachers");
        title.setForeground(Color.decode("#ffffff"));
        title.setFont(new Font("Ubuntu", Font.BOLD, 40));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        boardOfTeacherSearch = createTable();
        JScrollPane scrollPane = new JScrollPane(boardOfTeacherSearch);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtSeach, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addGap(30)
                        )
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSeach, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        )
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
        );

        return  jPanel;
    }

    private void updateBoardOfSearch(String text) {

        for (Component component : boardOfTeacherSearch.getComponents()) {
            boardOfTeacherSearch.remove(component);
        }

        ModalFindTeacher.this.teachersOptions = TeacherManager.selectAll(text);

        boolean flag = true;
        for(Teacher teacher: ModalFindTeacher.this.teachersOptions){
            if(flag){
                boardOfTeacherSearch.add(rowComponentCreator(teacher, "#EFEFEF"));
            }else{
                boardOfTeacherSearch.add(rowComponentCreator(teacher, "#FFFFF7"));
            }
            flag = !flag;
        }

        boardOfTeacherSearch.revalidate();
        boardOfTeacherSearch.repaint();
    }

    private JPanel footerCreate(){

        JPanel jPanel = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel);
        jPanel.setLayout(layout);

        jPanel.setBackground(Color.decode("#0ABFBF"));

        btnFinish = new JButton("FINISH");
        btnFinish.setBackground(Color.decode("#D7F205"));
        btnFinish.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        btnFinish.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnFinish.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnFinish.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(cadastroDefesaVariant04 != null){
                    cadastroDefesaVariant04.getDefense().setBoardOfTeachers(ModalFindTeacher.this.teachersSelected);
                }
                if(modalViewDefense != null){
                    modalViewDefense.updateViewAndTeacherBoards(ModalFindTeacher.this.teachersSelected);
                }
                ModalFindTeacher.this.destroy();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnFinish.setBackground(Color.decode("#34F907"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnFinish.setBackground(Color.decode("#D7F205"));
            }
        });

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnFinish, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addGap(20)
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(5)
                        .addGroup(

                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(btnFinish, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        )
        );

        return  jPanel;
    }

    private void destroy() {
        jFrame.dispose();
    }

    private class WrapperButton{
        JPanel jPanel;
        JButton jButton;
    }

}
