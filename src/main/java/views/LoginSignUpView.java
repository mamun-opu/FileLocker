package views;

import javax.swing.*;
import java.awt.*;

public class LoginSignUpView extends JFrame {
    // Components for Login
    public JTextField loginEmailField = new JTextField(20);
    public JPasswordField loginPasswordField = new JPasswordField(20);
    public JButton loginButton = new JButton("Login");

    // Components for Signup
    public JTextField signupNameField = new JTextField(20);
    public JTextField signupEmailField = new JTextField(20);
    public JPasswordField signupPasswordField = new JPasswordField(20);
    public JButton signupButton = new JButton("Signup");
    public JLabel verificationCodeLabel = new JLabel("Verification Code:");
    public JTextField verificationCodeField = new JTextField(20);
    public JButton verifyButton = new JButton("Verify");

    public LoginSignUpView() {
        setTitle("My GUI Application");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        JLabel loginEmailLabel = new JLabel("Email:");
        loginEmailLabel.setBounds(10, 20, 80, 25);
        loginPanel.add(loginEmailLabel);

        loginEmailField.setBounds(100, 20, 165, 25);
        loginPanel.add(loginEmailField);

        JLabel loginPasswordLabel = new JLabel("Password:");
        loginPasswordLabel.setBounds(10, 60, 80, 25);
        loginPanel.add(loginPasswordLabel);

        loginPasswordField.setBounds(100, 60, 165, 25);
        loginPanel.add(loginPasswordField);

        loginButton.setBounds(10, 100, 80, 25);
        loginPanel.add(loginButton);

        // Signup Panel
        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(null);
        JLabel signupNameLabel = new JLabel("Name:");
        signupNameLabel.setBounds(10, 20, 80, 25);
        signupPanel.add(signupNameLabel);

        signupNameField.setBounds(100, 20, 165, 25);
        signupPanel.add(signupNameField);

        JLabel signupEmailLabel = new JLabel("Email:");
        signupEmailLabel.setBounds(10, 60, 80, 25);
        signupPanel.add(signupEmailLabel);

        signupEmailField.setBounds(100, 60, 165, 25);
        signupPanel.add(signupEmailField);

        JLabel signupPasswordLabel = new JLabel("Password:");
        signupPasswordLabel.setBounds(10, 100, 80, 25);
        signupPanel.add(signupPasswordLabel);

        signupPasswordField.setBounds(100, 100, 165, 25);
        signupPanel.add(signupPasswordField);

        signupButton.setBounds(10, 140, 80, 25);
        signupPanel.add(signupButton);

        verificationCodeLabel.setBounds(10, 180, 120, 25);
        verificationCodeLabel.setVisible(false);
        signupPanel.add(verificationCodeLabel);

        verificationCodeField.setBounds(130, 180, 135, 25);
        verificationCodeField.setVisible(false);
        signupPanel.add(verificationCodeField);

        verifyButton.setBounds(10, 220, 80, 25);
        verifyButton.setVisible(false);
        signupPanel.add(verifyButton);

        // Add panels to tabbed pane
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Signup", signupPanel);

        // Add tabbed pane to frame
        getContentPane().add(tabbedPane);
    }
}
