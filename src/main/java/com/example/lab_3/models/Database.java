package com.example.lab_3.models;

import java.sql.*;

public class Database {
    private final String url = "jdbc:postgresql://localhost/bug_tracker";
    private final String user = "postgres";
    private final String password = "password1";
    private static Database database;
    private Connection conn;

    private Database() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean selectAllUsers(String username,String password) throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT password FROM \"LAB3_ASH_User\" WHERE username=?");
        preparedStatement.setString(1,username);
        ResultSet set = preparedStatement.executeQuery();
        String pass = null;
        while (set.next()){
            pass = set.getString(1);
        }
        return pass.equals(password);
    }

    public void registration(String username, String email, String password) throws SQLException{

        PreparedStatement validationUser = conn.prepareStatement("SELECT COUNT (id) FROM \"LAB3_ASH_User\" WHERE \"username\" = ? OR \"email\" = ?");
        validationUser.setString(1,username);
        validationUser.setString(2,email);
        ResultSet resultSet = validationUser.executeQuery();
        while (resultSet.next()){
            if(resultSet.getInt(1) == 0){
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO \"LAB3_ASH_User\" VALUES ((SELECT COUNT(id) FROM \"LAB3_ASH_User\" )+1,?, ?, ?)");
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,email);
                preparedStatement.setString(3,password);
                preparedStatement.executeUpdate();
            }else {
                System.out.println("ERROR");
            }
        }
    }

    public static Database database(){
        if(database == null){
            database = new Database();
        }
        return database;
    }
}
