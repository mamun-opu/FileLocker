package Controller;
import views.LoginSignUpView;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginSignUpView view = new LoginSignUpView();
            new LoginSignUpController(view);
            view.setVisible(true);
        });
    }
}

