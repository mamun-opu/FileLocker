package views;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuView extends JFrame {
    private String email;
    public JButton showHiddenFilesButton = new JButton("Show Hidden Files");
    public JButton hideFileButton = new JButton("Hide File");
    public JButton unhideFileButton = new JButton("Unhide File");
    public JButton exitButton = new JButton("Exit");

    public MenuView(String email) {
        this.email = email;
        setTitle("Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }
    public MenuView() {
        setTitle("Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }


    private void initComponents() {
        setLayout(new GridLayout(4, 1));
        add(showHiddenFilesButton);
        add(hideFileButton);
        add(unhideFileButton);
        add(exitButton);
    }

    public String getEmail() {
        return email;
    }
}
