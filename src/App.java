import controller.ControllingManager;
import model.Defense;
import model.DefenseManager;
import model.Student;
import model.Teacher;
import view.modalfindteacher.ModalFindTeacher;
import view.modals.ModalViewDefense;

import javax.swing.*;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        ControllingManager controllingManager = new ControllingManager();

        controllingManager.execute();
    }
}
