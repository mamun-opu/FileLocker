package dao;

import db.MyConnection;
import model.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FileDAO {
    public static ArrayList<File> getAllFiles (String email) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement pStatement = connection.prepareStatement("select * from files where email = ?");
        pStatement.setString(1, email);
        ResultSet resultSet = pStatement.executeQuery();
        ArrayList<File> files = new ArrayList<>();
        while(resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String path = resultSet.getString(3);
            files.add(new File(id, name, path));
        }
        return files;
    }
    public static int hideFile(File file) throws SQLException, IOException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into files (name, path, email, bin_data) values (?, ?, ?, ?)");

        preparedStatement.setString(1, file.getFileName());
        preparedStatement.setString(2, file.getPath());
        preparedStatement.setString(3, file.getEmail());
        java.io.File f = new java.io.File(file.getPath());
        FileReader fileReader = new FileReader(f);
        preparedStatement.setCharacterStream(4, fileReader, f.length());
        int ans = preparedStatement.executeUpdate();
        fileReader.close();

        f.delete();
        return ans;
    }
}
