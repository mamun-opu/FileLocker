package Controller;

import views.LoginSignUpView;
import views.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginSignUpController {
    private LoginSignUpView view;
    private String verificationCode;

    public LoginSignUpController(LoginSignUpView view) {
        this.view = view;

        // Add action listeners
        this.view.loginButton.addActionListener(new LoginButtonListener());
        this.view.signupButton.addActionListener(new SignupButtonListener());
        this.view.verifyButton.addActionListener(new VerifyButtonListener());
    }

    class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = view.loginEmailField.getText();
            String password = new String(view.loginPasswordField.getPassword());
            // Handle login logic
            if ("user@example.com".equals(email) && "password".equals(password)) {
                JOptionPane.showMessageDialog(view, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(view, "Invalid email or password.");
            }
        }
    }

    class SignupButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = view.signupNameField.getText();
            String email = view.signupEmailField.getText();
            String password = new String(view.signupPasswordField.getPassword());
            // Handle signup logic: send verification code
            verificationCode = "123456"; // Mock verification code
            JOptionPane.showMessageDialog(view, "Verification code sent to your email.");
            // Show verification fields
            view.verificationCodeLabel.setVisible(true);
            view.verificationCodeField.setVisible(true);
            view.verifyButton.setVisible(true);
        }
    }

    class VerifyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String enteredCode = view.verificationCodeField.getText();
            if (verificationCode.equals(enteredCode)) {
                String name = view.signupNameField.getText();
                String email = view.signupEmailField.getText();
                String password = new String(view.signupPasswordField.getPassword());
                JOptionPane.showMessageDialog(view, "Signup successful for " + name + "!");
                // Open new menu frame
                new MenuView().setVisible(true);
                // Close the current frame
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Invalid verification code.");
            }
        }
    }
}
