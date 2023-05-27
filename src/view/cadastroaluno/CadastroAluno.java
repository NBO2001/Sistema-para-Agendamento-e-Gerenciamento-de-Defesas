package view.cadastroaluno;

import interfaces.Visibled;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroAluno implements Visibled{

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
    private JButton btnNext;
    private JPanel jPanelForm;
    private JTextField textFieldMatricula;
    private JPanel panelOption;
    private JComboBox boxTypeStudent;

    public CadastroAluno(Visibled afterView) {
        initialize();
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());

            }
        });
    }

    public CadastroAluno(){
        initialize();
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanelMenu.setVisible(!jPanelMenu.isVisible());

            }
        });
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
