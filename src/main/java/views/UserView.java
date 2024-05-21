package views;

import dao.FileDAO;
import model.File;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final String email;
    UserView(String email){
        this.email = email;
    }
    public void home(){
        do{
            System.out.println("Welcome " + this.email);
            System.out.println("Enter 1 to show hidden files");
            System.out.println("Enter 2 to hide new file");
            System.out.println("Enter 3 to move hidden to visible");
            System.out.println("Enter 0 to exit");
            Scanner scanner = new Scanner(System.in);
            int option = Integer.parseInt(scanner.nextLine());
            switch (option){
                case 1 -> {
                    try {
                        List<File> files = FileDAO.getAllFiles(this.email);
                        System.out.println("ID - File Name");
                        for (File file: files
                        ) {
                            System.out.println(file.getId() + " " + file.getFileName());
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    System.out.println("Enter the file path");
                    String path = scanner.nextLine();
                    java.io.File f = new java.io.File(path);
                    File file = new File(0, f.getName(), path, this.email);

                    try {
                        FileDAO.hideFile(file);
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 -> {
                    List<File> files = null;
                    try {
                        files = FileDAO.getAllFiles(this.email);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("ID - File Name");
                    for (File file: files
                    ) {
                        System.out.println(file.getId() + " " + file.getFileName());
                    }
                    System.out.println("Enter the id of file to unhide: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean isValidID = false;
                    for (File file :
                            files) {
                        if(file.getId() == id){
                            isValidID = true;
                            break;
                        }
                        if(isValidID){
                            try {
                                FileDAO.unhide(id);
                            } catch (SQLException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        }else {
                            System.out.println("Wrong id");
                        }
                    }
                }
                case 0 -> {
                    System.exit(0);
                }
                default -> System.out.println("Wrong input!");
            }
        }while(true);
    }
}
