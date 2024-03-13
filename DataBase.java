package library;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    public static String Url = "jdbc:mysql://localhost:3306/library";
    public static String UserName = "root";
    public static String Password = "affan@123";

    public static Connection getConnection() {
        Connection connection = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(Url, UserName, Password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void fillBookDatabase(ArrayList<Book> bookDatabase, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.Query1);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("bookTitle");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                int availability = resultSet.getInt("availabilityBook");

                Book book = new Book(id, title, author, genre, availability);
                bookDatabase.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillUserDatabase(ArrayList<User> userDatabase, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.Query2);

            while (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String username = resultSet.getString("userName");
                String contact = resultSet.getString("contactInfo");
                int borrowedBooks = resultSet.getInt("borrowedBooks");

                User user = new User(userId, username, contact, borrowedBooks);
                userDatabase.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}