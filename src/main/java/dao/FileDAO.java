package dao;

import db.MyConnection;
import model.File;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {
    public static List<File> getAllFiles (String email) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement pStatement = connection.prepareStatement("select * from files where email = ?");
        pStatement.setString(1, email);
        ResultSet resultSet = pStatement.executeQuery();
        List<File> files = new ArrayList<>();
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
        PreparedStatement ps = connection.prepareStatement("insert into files (name, path, email, bin_data) values (?, ?, ?, ?)");

        ps.setString(1, file.getFileName());
        ps.setString(2, file.getPath());
        ps.setString(3, file.getEmail());
        java.io.File f = new java.io.File(file.getPath());
        FileReader fr = new FileReader(f);
        ps.setCharacterStream(4, fr, f.length());
        int ans = ps.executeUpdate();
        fr.close();
        f.delete();
        return ans;
    }
    public static void unhide(int id) throws SQLException, IOException{
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select path, bin_data from files where id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String path = rs.getString("path");
        Clob clob = rs.getClob("bin_data");

        Reader reader = clob.getCharacterStream();
        FileWriter fw = new FileWriter(path);
        int i;
        while((i = reader.read()) != -1){
            fw.write((char) i);
        }
        fw.close();
        ps = connection.prepareStatement("delete from files where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Successfully unhidden");
    }
}
