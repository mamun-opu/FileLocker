# File Locker Application

File Locker is a Java-based application that allows users to securely hide and unhide files. The application provides a simple and intuitive GUI for users to sign up, log in, and manage their hidden files.

## Features

- **User Authentication**: Sign up and log in with email verification using OTP.
- **File Management**: Hide and unhide files securely.
- **File Storage**: Store file metadata and content in a MySQL database.
- **Intuitive GUI**: User-friendly interface built with Java Swing.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL database
- Internet connection for OTP email service

## Getting Started

### Setting Up the Database

1. Install MySQL and create a database named `fileLocker`.
2. Create the necessary tables:

    ```sql
    CREATE DATABASE fileLocker;

    USE fileLocker;

    CREATE TABLE users (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        email VARCHAR(100) NOT NULL UNIQUE
    );

    CREATE TABLE files (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        path VARCHAR(255) NOT NULL,
        email VARCHAR(100) NOT NULL,
        bin_data TEXT NOT NULL
    );
    ```

### Configuration

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/file-locker.git
    cd file-locker
    ```

2. Configure database connection:
    - Open `db/MyConnection.java`.
    - Modify the following line with your MySQL username and password:

   ```java
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fileLocker?useSSl=false", "root", "yourpassword");
    ```

3. Configure email service:
    - Open `service/SendOTPService.java`.
    - Replace the email and password with your own Gmail credentials:

    ```java
        String from = "your-email@gmail.com";
        return new PasswordAuthentication(from, "your-email-password");
    ```

### Running the Application

1. Compile and run the application:

    ```sh
    javac MainApp.java
    java MainApp
    ```

### Using the Application

- **Sign Up**:
  - Enter your name and email.
  - An OTP will be sent to your email. Enter the OTP to complete the sign-up process.

- **Login**:
  - Enter your registered email and password.

- **Main Menu**:
  - **Show Hidden Files**: Display a list of hidden files.
  - **Hide File**: Enter the file path to hide a file.
  - **Unhide File**: Enter the file ID to unhide a file.
  - **Exit**: Exit the application.

## Project Structure

- **Controller/**: Contains the main entry point of the application.
- **dao/**: Data Access Objects for interacting with the database.
- **db/**: Database connection class.
- **model/**: Model classes representing User and File entities.
- **service/**: Services for generating and sending OTP, and managing user data.
- **views/**: GUI classes for the welcome screen, login/signup, and main menu.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgements

-  ``Java Swing for GUI components.``
-  ``JavaMail API for sending OTP emails. ``
-  ``MySQL for the database.``
