package views;

import javax.swing.*;
import java.awt.*;

public class MyGUI extends JFrame {

    private BusinessLogic businessLogic;
    private String verificationCode;

    public MyGUI(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
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

        JTextField loginEmailField = new JTextField(20);
        loginEmailField.setBounds(100, 20, 165, 25);
        loginPanel.add(loginEmailField);

        JLabel loginPasswordLabel = new JLabel("Password:");
        loginPasswordLabel.setBounds(10, 60, 80, 25);
        loginPanel.add(loginPasswordLabel);

        JPasswordField loginPasswordField = new JPasswordField(20);
        loginPasswordField.setBounds(100, 60, 165, 25);
        loginPanel.add(loginPasswordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 100, 80, 25);
        loginPanel.add(loginButton);

        loginButton.addActionListener(e -> {
            String email = loginEmailField.getText();
            String password = new String(loginPasswordField.getPassword());
            // Handle login logic
            String result = businessLogic.login(email, password);
            JOptionPane.showMessageDialog(this, result);
        });

        // Signup Panel
        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(null);
        JLabel signupNameLabel = new JLabel("Name:");
        signupNameLabel.setBounds(10, 20, 80, 25);
        signupPanel.add(signupNameLabel);

        JTextField signupNameField = new JTextField(20);
        signupNameField.setBounds(100, 20, 165, 25);
        signupPanel.add(signupNameField);

        JLabel signupEmailLabel = new JLabel("Email:");
        signupEmailLabel.setBounds(10, 60, 80, 25);
        signupPanel.add(signupEmailLabel);

        JTextField signupEmailField = new JTextField(20);
        signupEmailField.setBounds(100, 60, 165, 25);
        signupPanel.add(signupEmailField);

        JLabel signupPasswordLabel = new JLabel("Password:");
        signupPasswordLabel.setBounds(10, 100, 80, 25);
        signupPanel.add(signupPasswordLabel);

        JPasswordField signupPasswordField = new JPasswordField(20);
        signupPasswordField.setBounds(100, 100, 165, 25);
        signupPanel.add(signupPasswordField);

        JButton signupButton = new JButton("Signup");
        signupButton.setBounds(10, 140, 80, 25);
        signupPanel.add(signupButton);

        JLabel verificationCodeLabel = new JLabel("Verification Code:");
        verificationCodeLabel.setBounds(10, 180, 120, 25);
        verificationCodeLabel.setVisible(false);
        signupPanel.add(verificationCodeLabel);

        JTextField verificationCodeField = new JTextField(20);
        verificationCodeField.setBounds(130, 180, 135, 25);
        verificationCodeField.setVisible(false);
        signupPanel.add(verificationCodeField);

        JButton verifyButton = new JButton("Verify");
        verifyButton.setBounds(10, 220, 80, 25);
        verifyButton.setVisible(false);
        signupPanel.add(verifyButton);

        signupButton.addActionListener(e -> {
            String name = signupNameField.getText();
            String email = signupEmailField.getText();
            String password = new String(signupPasswordField.getPassword());
            // Handle signup logic: send verification code
            verificationCode = businessLogic.sendVerificationCode(email);
            JOptionPane.showMessageDialog(this, "Verification code sent to your email.");
            // Show verification fields
            verificationCodeLabel.setVisible(true);
            verificationCodeField.setVisible(true);
            verifyButton.setVisible(true);
        });

        verifyButton.addActionListener(e -> {
            String enteredCode = verificationCodeField.getText();
            if (verificationCode.equals(enteredCode)) {
                String name = signupNameField.getText();
                String email = signupEmailField.getText();
                String password = new String(signupPasswordField.getPassword());
                String result = businessLogic.signup(name, email, password);
                JOptionPane.showMessageDialog(this, result);
                // Reset fields
                verificationCodeLabel.setVisible(false);
                verificationCodeField.setVisible(false);
                verifyButton.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid verification code.");
            }
        });

        // Add panels to tabbed pane
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Signup", signupPanel);

        // Add tabbed pane to frame
        getContentPane().add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BusinessLogic businessLogic = new BusinessLogic();
            MyGUI gui = new MyGUI(businessLogic);
            gui.setVisible(true);
        });
    }
}

// Simulated business logic class
class BusinessLogic {
    public String login(String email, String password) {
        // Simulate login logic
        if ("user@example.com".equals(email) && "password".equals(password)) {
            return "Login successful!";
        } else {
            return "Invalid email or password.";
        }
    }

    public String sendVerificationCode(String email) {
        // Simulate sending verification code
        return "123456"; // This is just a mock code for demonstration
    }

    public String signup(String name, String email, String password) {
        // Simulate signup logic
        return "Signup successful for " + name + "!";
    }
}
