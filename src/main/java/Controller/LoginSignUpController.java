package Controller;

import dao.UserDAO;
import model.User;
import views.LoginSignUpView;
import views.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginSignUpController {
    private LoginSignUpView view;
    private UserDAO userDAO;

    public LoginSignUpController(LoginSignUpView view) {
        this.view = view;
        this.userDAO = new UserDAO();

        // Add action listeners
        this.view.loginButton.addActionListener(new LoginButtonListener());
        this.view.signupButton.addActionListener(new SignupButtonListener());
    }

    class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = view.loginEmailField.getText();
            String password = new String(view.loginPasswordField.getPassword());
            // For now, skip password verification for simplicity
            try {
                if (userDAO.isExists(email)) {
                    JOptionPane.showMessageDialog(view, "Login successful!");
                    view.dispose();
                    MenuView menuView = new MenuView(email);
                    new MenuController(menuView);
                    menuView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(view, "Email not found!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage());
            }
        }
    }

    class SignupButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = view.signupNameField.getText();
            String email = view.signupEmailField.getText();
            String password = new String(view.signupPasswordField.getPassword());
            User user = new User(name, email);

            try {
                if (!userDAO.isExists(email)) {
                    userDAO.saveUser(user);
                    JOptionPane.showMessageDialog(view, "Signup successful!");
                } else {
                    JOptionPane.showMessageDialog(view, "Email already exists!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage());
            }
        }
    }
}
