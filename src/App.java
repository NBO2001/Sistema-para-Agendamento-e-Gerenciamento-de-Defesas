import controller.ControllingManager;

import javax.swing.*;

/**
 * Classe que inicia todo o sistema.
 *
 */

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ControllingManager controllingManager = new ControllingManager();

                controllingManager.execute();
            }
        });


    }
}
