package view.modals;

import interfaces.Visibled;
import model.Defense;

import javax.swing.*;
import java.awt.*;

public class ModalViewPerson implements Visibled {

    private JFrame jFrame;

    public ModalViewPerson(){
        initialize();
    }

    private void initialize(){

        jFrame = new JFrame("Person View");
        jFrame.setMaximumSize(new Dimension(850,800));
        jFrame.setMinimumSize(new Dimension(850,600));
        jFrame.setPreferredSize(new Dimension(850,800));
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.setBackground(Color.decode("#FFFFFF"));
        jFrame.setLocationRelativeTo(null);

    }

    @Override
    public void setVisible(boolean value) {
        jFrame.setVisible(value);
    }


}
