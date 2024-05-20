package views;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

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
    private void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        try{
          if(UserDAO.isExists(email)){
              String genOTP = GenerateOTP.getOTP();
              SendOTPService.sendOTP(email, genOTP);
              System.out.println("Enter the OTP");
              String otp = scanner.nextLine();
              if(genOTP.contains(otp)){
                  System.out.println("Welcome");
                  new UserView(email).home();
              }else{
                  System.out.println("Wrong OTP");
              }
          }else {
              System.out.println("User not found");
          }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    private void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email, genOTP);
        System.out.println("Enter the OTP");
        String otp = scanner.nextLine();

        if(genOTP.contains(otp)){
            User user = new User(name, email);
            int response = UserService.saveUser(user);
            switch (response){
                case 0 -> System.out.println("User registered successfully");
                case 1 -> System.out.println("User already exists");
            }
        }else{
            System.out.println("Wrong OTP");
        }

    }
}
