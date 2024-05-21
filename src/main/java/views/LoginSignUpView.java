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

    public LoginSignUpView() {
        setTitle("File Locker - Login & Signup");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        loginPanel.add(loginEmailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        loginPanel.add(loginPasswordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(loginButton, gbc);

        // Signup Panel
        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        signupPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        signupPanel.add(signupNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        signupPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        signupPanel.add(signupEmailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        signupPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        signupPanel.add(signupPasswordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        signupPanel.add(signupButton, gbc);

        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Signup", signupPanel);

        add(tabbedPane);
    }
}
