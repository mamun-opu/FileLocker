package Controller;

import views.Welcome;

public class Main {
    public static void main(String[] args) {

        Welcome welcome = new Welcome();
        while(true){
            welcome.welcomeScreen();
        }
    }
}