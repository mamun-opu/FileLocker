package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Welcome {
    public void welcomeScreen(){
        BufferedReader bfReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the FileLocker app");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to Signup");
        System.out.println("Press 0 to exit");

        int option = 0;
        try {
            option = Integer.parseInt(bfReader.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }
        switch (option){
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
        }

    }
    private void login(){}
    private void signUp(){}
}
