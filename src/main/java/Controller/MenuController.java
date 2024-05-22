package Controller;

import dao.FileDAO;
import model.File;
import views.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MenuController {
    private final MenuView view;

    public MenuController(MenuView view) {
        this.view = view;

        // Add action listeners
        this.view.showHiddenFilesButton.addActionListener(new ShowHiddenFilesListener());
        this.view.hideFileButton.addActionListener(new HideFileListener());
        this.view.unhideFileButton.addActionListener(new UnhideFileListener());
        this.view.exitButton.addActionListener(new ExitListener());
    }

    class ShowHiddenFilesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                List<File> files = FileDAO.getAllFiles(view.getEmail());
                StringBuilder fileList = new StringBuilder("ID - File Name\n");
                for (File file : files) {
                    fileList.append(file.getId()).append(" - ").append(file.getFileName()).append("\n");
                }
                JOptionPane.showMessageDialog(view, fileList.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error retrieving hidden files: " + ex.getMessage());
            }
        }
    }

    class HideFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String path = JOptionPane.showInputDialog(view, "Enter the file path to hide:");
            java.io.File f = new java.io.File(path);
            File file = new File(0, f.getName(), path, view.getEmail());

            try {
                FileDAO.hideFile(file);
                JOptionPane.showMessageDialog(view, "File hidden successfully!");
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error hiding file: " + ex.getMessage());
            }
        }
    }

    class UnhideFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                List<File> files = FileDAO.getAllFiles(view.getEmail());
                StringBuilder fileList = new StringBuilder("ID - File Name\n");
                for (File file : files) {
                    fileList.append(file.getId()).append(" - ").append(file.getFileName()).append("\n");
                }
                String input = JOptionPane.showInputDialog(view, "Enter the id of file to unhide:\n" + fileList.toString());
                int id = Integer.parseInt(input);

                boolean isValidID = files.stream().anyMatch(file -> file.getId() == id);
                if (isValidID) {
                    FileDAO.unhide(id);
                    JOptionPane.showMessageDialog(view, "File unhidden successfully!");
                } else {
                    JOptionPane.showMessageDialog(view, "Invalid file ID!");
                }
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error unhiding file: " + ex.getMessage());
            }
        }
    }

    class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
