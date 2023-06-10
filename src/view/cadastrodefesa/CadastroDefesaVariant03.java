package view.cadastrodefesa;

import interfaces.Visibled;
import model.Defense;
import model.Teacher;
import model.Utils;
import view.cadastroaluno.CadastroAluno;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroDefesaVariant03 implements Visibled {
    private  JFrame jFrame;
    private JPanel panel1;
    private JPanel jPanelHome;
    private JButton btnNext;
    private JPanel jPanelForm;
    private JPanel jPanelTitle;
    private JTextField textFieldTitle;
    private JPanel panelOption;
    private JTextField textFieldDate;
    private JTextField textFieldLocal;
    private JTextField textFieldStudent;
    private JTextField textFieldTeach;
    private JComboBox typeDefense;
    private JPanel jPanelBottom;
    private JButton btnBack;
    private JTextField txtHour;

    private Defense defense;

    public CadastroDefesaVariant03(Visibled afterView, Defense defense){

        initialize();
        this.setDefense(defense);
        this.setInfo();

        btnBack.setSize(80,80);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(afterView != null) afterView.setVisible(true);
                CadastroDefesaVariant03.this.destroy();
            }
        });

        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(textFieldTitle.getText().trim().length() <= 2){
                    JOptionPane.showMessageDialog(null, "Campo titulo vazio");
                    return;
                } else if (!Utils.isValidDate(textFieldDate.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Data invalida");
                    return;
                } else if (textFieldLocal.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campo local vazio");
                    return;
                }else if (!Utils.isValidTime(txtHour.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Horario invalido");
                    return;
                }

                Defense defense1 = CadastroDefesaVariant03.this.getDefense();
                CadastroDefesaVariant03.AnyObject selectedObject = (CadastroDefesaVariant03.AnyObject) typeDefense.getSelectedItem();
                int selectedValue = selectedObject.getValue();

                defense1.setLocal(textFieldLocal.getText());
                defense1.setTypeDefense(selectedValue);
                defense1.setDefenseTitle(textFieldTitle.getText());
                defense1.setDate(Utils.strToDateBrWithHour(textFieldDate.getText().trim()+" "+txtHour.getText().trim()));

                new CadastroDefesaVariant04(afterView, defense1).setVisible(true);
                CadastroDefesaVariant03.this.setVisible(false);



            }
        });
    }

    private void destroy() {
        jFrame.dispose();
    }

    private void initialize(){

        this.jFrame = new JFrame("Cadastro Defesa");

        this.jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setBorder(BorderFactory.createLineBorder(Color.white));
        this.textFieldTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldDate.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldLocal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.textFieldStudent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textFieldTeach.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.typeDefense.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.txtHour.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.typeDefense.addItem(new CadastroDefesaVariant03.AnyObject(1, "Defesa de projeto final/TCC"));
        this.typeDefense.addItem(new CadastroDefesaVariant03.AnyObject(2, "Defesa de qualificação de mestrado"));
        this.typeDefense.addItem(new CadastroDefesaVariant03.AnyObject(3, "Defesa de dissertação de mestrado"));
        this.typeDefense.addItem(new CadastroDefesaVariant03.AnyObject(4, "Defesa de qualificação de doutorado"));
        this.typeDefense.addItem(new CadastroDefesaVariant03.AnyObject(5, "Defesa de tese de doutorado"));
        this.typeDefense.addItem(new CadastroDefesaVariant03.AnyObject(6, "Defesa de artigo"));


        this.jFrame.add(panel1);
    }

    public void setVisible(boolean value){
        this.jFrame.setVisible(value);
    }

    public Defense getDefense() {
        return defense;
    }

    public void setDefense(Defense defense) {
        this.defense = defense;
    }

    public void setInfo(){

        if(defense != null){
            this.textFieldTeach.setText(defense.getTeacherAdvisor().getName());
            this.textFieldStudent.setText(defense.getStudentDefending().getName());


            this.textFieldTeach.setEditable(false);
            this.textFieldStudent.setEditable(false);
        }


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
