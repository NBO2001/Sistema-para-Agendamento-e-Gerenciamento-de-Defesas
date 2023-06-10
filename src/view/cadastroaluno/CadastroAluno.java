package view.cadastroaluno;

import interfaces.VisiblePersonified;
import interfaces.Visibled;
import model.Person;
import model.Student;
import model.StudentManager;
import view.cadastro.Cadastro;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroAluno implements VisiblePersonified {

    private JFrame jFrame;
    private JPanel panel1;
    private JPanel jPanelHome;
    private JButton btnNext;
    private JPanel jPanelForm;
    private JTextField textFieldMatricula;
    private JPanel panelOption;
    private JComboBox boxTypeStudent;
    private JButton btnBack;

    private Student student;

    public CadastroAluno(Visibled afterView) {
        this.setStudent(null);
        initialize();

        btnBack.setSize(80,80);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(afterView != null) afterView.setVisible(true);
                CadastroAluno.this.destroy();
            }
        });

        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                AnyObject selectedObject = (AnyObject) boxTypeStudent.getSelectedItem();
                String selectedValue = selectedObject.getValue();

                if(CadastroAluno.this.getStudent() != null){
                    Student student1 = CadastroAluno.this.getStudent();
                    student1.setTypeStudent(Integer.parseInt(selectedValue));
                    student1.setRegistration(textFieldMatricula.getText());


                    if(StudentManager.insert(student1)){
                        JOptionPane.showMessageDialog(null, "Insert success!");

                        if( afterView != null ){
                            afterView.setVisible(true);
                            CadastroAluno.this.setVisible(false);
                        }

                    }else{
                        if( afterView != null ){
                            afterView.setVisible(true);
                            CadastroAluno.this.setVisible(false);
                        }
                        JOptionPane.showMessageDialog(null, "Error inserting.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });


    }

    private void destroy() {
        jFrame.dispose();
    }

    public CadastroAluno(){
        this(null);

    }

    private void initialize(){

        this.jFrame = new JFrame("Cadastro");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setBorder(BorderFactory.createLineBorder(Color.white));
        this.textFieldMatricula.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.boxTypeStudent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.boxTypeStudent.addItem(new AnyObject("1", "Aluno de Graduação"));
        this.boxTypeStudent.addItem(new AnyObject("2", "Aluno de Mestrado"));
        this.boxTypeStudent.addItem(new AnyObject("3", "Aluno de Doutorado"));
        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStudent(Person student) {
        this.student = Student.toStudent(student) ;
    }

    @Override
    public void setState(Person person) {
        this.setStudent(person);
    }

    private class AnyObject{
       private String value;
       private String text;

       public AnyObject(String value, String text) {
           this.value = value;
           this.text = text;
       }
       @Override
       public String toString() {
           return text;
       }

       public String getValue() {
           return value;
       }

       public void setValue(String value) {
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
