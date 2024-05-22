package dao;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean isExists(String email)throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement pStatement = connection.prepareStatement("select email from users");
        ResultSet result = pStatement.executeQuery();
        while(result.next()){
            String e = result.getString(1);
            if(e.equals(email))return true;
        }
        return false;
    }

    public static int saveUser(User user) throws SQLException{
        Connection connection = MyConnection.getConnection();
        PreparedStatement pStatement = connection.prepareStatement("insert into users values (default, ?, ?, ?)");
        pStatement.setString(1, user.getName());
        pStatement.setString(2, user.getEmail());
        pStatement.setString(3, user.getPassword());
        return pStatement.executeUpdate();
    }

    public static boolean isUser(String email, String password) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement pStatement = connection.prepareStatement("select email, password from users");
        ResultSet result = pStatement.executeQuery();
        while(result.next()){
            String mail = result.getString(1);
            String pass = result.getString(2);
            if(mail.equals(email) && pass.equals(password))return true;
        }
        return false;
    }

}
