package views;
import javax.swing.*;
import java.awt.*;
public class MenuFrame extends JFrame {
    public MenuFrame() {
        setTitle("Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton option1Button = new JButton("Option 1");
        JButton option2Button = new JButton("Option 2");
        JButton option3Button = new JButton("Option 3");
        JButton exitButton = new JButton("Exit");

        option1Button.addActionListener(e -> handleOption(1));
        option2Button.addActionListener(e -> handleOption(2));
        option3Button.addActionListener(e -> handleOption(3));
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(option1Button);
        panel.add(option2Button);
        panel.add(option3Button);
        panel.add(exitButton);

        getContentPane().add(panel);
    }

    private void handleOption(int option) {
        JOptionPane.showMessageDialog(this, "Option " + option + " selected.");
    }

    public static class BusinessLogic {
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
}
