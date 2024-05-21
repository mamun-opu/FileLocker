package Controller;


import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            WelcomeView view = new WelcomeView();
            new WelcomeController(view);
            view.setVisible(true);
        });
    }
}
