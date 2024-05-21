package Controller;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;
import views.LoginSignUpView;
import views.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginSignUpController {
    private LoginSignUpView view;
    private String verificationCode;
    private String name;
    private String email;
    private String password;
    private User user;

    public LoginSignUpController(LoginSignUpView view) {
        this.view = view;

        // Add action listeners
        this.view.loginButton.addActionListener(new LoginButtonListener());
        this.view.signupButton.addActionListener(new SignupButtonListener());
        this.view.verifyButton.addActionListener(new VerifyButtonListener());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = view.loginEmailField.getText();

            String password = new String(view.loginPasswordField.getPassword());

            setEmail(email);
            setPassword(password);
            try {
                if(UserDAO.isUser(getEmail(), getPassword())) {
                    JOptionPane.showMessageDialog(view, "Login successful!");
                    MenuView menuView = new MenuView(getEmail());
                    new MenuController(menuView); // Initialize MenuController
                    menuView.setVisible(true);
                    // Close the current frame
                    view.dispose();
                }else {
                    JOptionPane.showMessageDialog(view, "Invalid email or password.");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class SignupButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = view.signupNameField.getText();
            String email = view.signupEmailField.getText();
            String password = new String(view.signupPasswordField.getPassword());
            // Handle signup logic: send verification code
            setName(name);
            setEmail(email);
            setPassword(password);


            setVerificationCode(GenerateOTP.getOTP());
            if (SendOTPService.sendOTP(getEmail(), getVerificationCode())){
                JOptionPane.showMessageDialog(view, "Verification code sent to your email.");

                view.verificationCodeLabel.setVisible(true);
                view.verificationCodeField.setVisible(true);
                view.verifyButton.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(view, "There is an error sending OTP");
            }
        }
    }

    class VerifyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String enteredCode = view.verificationCodeField.getText();

            if (getVerificationCode().trim().equals(enteredCode)) {

                setUser(new User(getName(), getEmail(), getPassword()));
                UserService.saveUser(getUser());
                JOptionPane.showMessageDialog(view, "Signup successful for " + getName() + "!");
                // Open new menu frame
                new MenuView(getEmail()).setVisible(true);
                // Close the current frame
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Invalid verification code.");
            }
        }
    }
}
