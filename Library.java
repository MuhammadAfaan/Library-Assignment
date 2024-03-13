package library;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    public void addNewBooks(Scanner input, ArrayList<Book> bookDatabase, Connection connection) {
        // Taking Input
        System.out.print("Enter Book Name: ");
        input.nextLine();
        String name = input.nextLine();
        System.out.print("Enter Book ID: ");
        int id = input.nextInt();
        System.out.print("Enter Book Availability: ");
        int availability = input.nextInt();
        System.out.print("Enter Book genre: ");
        input.nextLine();
        String genre = input.nextLine();
        System.out.print("Enter Book Author: ");
        String author = input.nextLine();

        // Updating in Array List
        Book newBook = new Book(id, name, author, genre, availability);
        bookDatabase.add(newBook);
        System.out.println("New Book Added Successfully");

        // Updating in Database
        try (Statement statement = connection.createStatement()) {
            String sqlQuery = "INSERT INTO books (id, bookTitle, author, genre, availabilityBook) VALUES (" +
                    newBook.getBookID() + ", " +
                    "'" + newBook.getBookTitle() + "', " +
                    "'" + newBook.getAuthor() + "', " +
                    "'" + newBook.getGenre() + "', " +
                    newBook.getAvailabilityBook() + ")";

            // Execute the update query
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addNewUsers(Scanner input, ArrayList<User> userDatabase, Connection connection) {
        // Taking Input
        System.out.print("Enter User Name: ");
        input.nextLine();
        String name = input.nextLine();
        System.out.print("Enter User ID: ");
        int id = input.nextInt();
        System.out.print("Enter Borrowed Books: ");
        int borrowedbook = input.nextInt();
        System.out.print("Enter Contact Info: ");
        input.nextLine();
        String contact = input.nextLine();

        // Updating in Array List
        User newUser = new User(id, name, contact, borrowedbook);
        userDatabase.add(newUser);
        System.out.println("New User Added Successfully");

        // Updating in Database
        try (Statement statement = connection.createStatement()) {
            String sqlQuery = "INSERT INTO users (userId, userName, contactInfo, borrowedBooks) VALUES (" +
                    newUser.getUserId() + ", " +
                    "'" + newUser.getUserName() + "', " +
                    "'" + newUser.getContactInfo() + "', " +
                    "'" + newUser.getBorrowedBooks() + "')";

            // Execute the update query
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void viewBooks(ArrayList<Book> bookDatabase) {
        if (bookDatabase.size() > 0) {
            System.out.println("Books Record");
            for (Book book : bookDatabase) {
                int ID = book.getBookID();
                String NAME = book.getBookTitle();
                String AUTHOR = book.getAuthor();
                String GENRE = book.getGenre();
                int AVAILABILITY = book.getAvailabilityBook();
                System.out.println("ID: " + ID + ", Name: " + NAME + ", Author: " + AUTHOR + ", Genre: " + GENRE
                        + ", Book Availability: " + AVAILABILITY);
            }
        } else {
            System.out.println("No Books Found");
        }
    }

    public void viewUsers(ArrayList<User> userDatabase) {
        if (userDatabase.size() > 0) {
            System.out.println("Books Record");
            for (User user : userDatabase) {
                int ID = user.getUserId();
                String NAME = user.getUserName();
                String CONTACT = user.getContactInfo();
                int BORROWED = user.getBorrowedBooks();
                System.out.println("User ID: " + ID + ", Name: " + NAME + ", Contact: " + CONTACT + ", Book Borrowed: "
                        + BORROWED);
            }
        } else {
            System.out.println("No Users Found");
        }
    }

    public void returnBook(Scanner input, ArrayList<Book> bookDatabase, ArrayList<User> userDatabase,
            Connection connection) {
        // Taking Input
        System.out.print("Enter User ID: ");
        input.nextLine();
        int searchUser = input.nextInt();
        System.out.print("\nEnter Book Name: ");
        input.nextLine();
        String searchBook = input.nextLine();
        // Searching in Array List
        for (User user : userDatabase) {
            // Updating in Array List
            if (user.getUserId() == searchUser) {
                int ch = user.getBorrowedBooks();
                user.setBorrowedBooks(ch - 1);
                break;
            }
        }
        // Updating in Database
        String SqlQueryUser = "UPDATE users SET borrowedBooks = borrowedBooks - 1 WHERE userId = " + searchUser;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SqlQueryUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Searching in Array List
        for (Book book : bookDatabase) {
            // Updating Array List
            if (book.getBookTitle().equals(searchBook)) {
                int ch = book.getAvailabilityBook();
                book.setAvailabilityBook(ch + 1);
                break;
            }
        }
        // Updating In database
        String SqlQueryBook = "UPDATE books SET availabilityBook = availabilityBook + 1 WHERE bookTitle = '"
                + searchBook + "'";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SqlQueryBook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Record Updated Successfully");
    }

    public void searchBook(Scanner input, ArrayList<Book> bookDatabase) {
        System.out.println("Search by:\n1)Title\n2)Author");
        int check = input.nextInt();
        boolean found = false;
        switch (check) {
            case 1: {
                System.out.print("Enter Title which book you want to Search: ");
                input.nextLine();
                String searchTitle = input.nextLine();
                for (Book book : bookDatabase) {
                    if (book.getBookTitle().equals(searchTitle)) {
                        int id = book.getBookID();
                        String bookTitle = book.getBookTitle();
                        String author = book.getAuthor();
                        String genre = book.getGenre();
                        int availabilityBook = book.getAvailabilityBook();

                        // Display the results
                        System.out.println(
                                "ID: " + id + ", Name: " + bookTitle + ", Author: " + author + ", Genre: " + genre
                                        + ", Book Availability: " + availabilityBook);
                        found = true;
                        break;
                    }
                }
                break;
            }
            case 2: {
                System.out.print("Enter Author which book you want to Search: ");
                input.nextLine();
                String searchAuthor = input.nextLine();
                for (Book book : bookDatabase) {
                    if (book.getAuthor().equals(searchAuthor)) {
                        System.out.println();
                        int id = book.getBookID();
                        String bookTitle = book.getBookTitle();
                        String author = book.getAuthor();
                        String genre = book.getGenre();
                        int availabilityBook = book.getAvailabilityBook();

                        System.out.println(
                                "ID: " + id + ", Name: " + bookTitle + ", Author: " + author + ", Genre: " + genre
                                        + ", Book Availability: " + availabilityBook);
                        found = true;
                        break;
                    }
                }
                break;
            }
            default:
                System.out.println("No Option Selected");
                break;
        }
        if (!found) {
            System.out.println("No Book Found");
        }

    }
}
