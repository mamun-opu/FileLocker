package views;

import java.util.Scanner;

public class UserView {
    private String email;
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
                case 1 ->
            }
        }while(true);
    }
}
