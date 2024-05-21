package Controller;

import Controller.LoginSignUpController;
import views.LoginSignUpView;

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginSignUpView view = new LoginSignUpView();
            new LoginSignUpController(view);
            view.setVisible(true);
        });
    }
}
