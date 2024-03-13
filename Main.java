package library;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Creating Array List
        ArrayList<Book> bookDatabase = new ArrayList<>();
        ArrayList<User> userDatabase = new ArrayList<>();
        // Creating Database Object
        DataBase database = new DataBase();
        // Creating Database Connection Object from Database
        Connection connection = database.getConnection();
        // Loading Arrays from DataBase
        database.fillBookDatabase(bookDatabase, connection);
        database.fillUserDatabase(userDatabase, connection);
        // Creating Scanner Object
        Scanner input = new Scanner(System.in);
        // Creating Library Object
        Library library = new Library();

        int choice;
        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. View Books Record");
            System.out.println("2. View Users Record");
            System.out.println("3. Add New Book");
            System.out.println("4. Add New User");
            System.out.println("5. Return Book");
            System.out.println("6. Search Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    library.viewBooks(bookDatabase);
                    break;
                case 2:
                    library.viewUsers(userDatabase);
                    break;
                case 3:
                    library.addNewBooks(input, bookDatabase, connection);
                    break;
                case 4:
                    library.addNewUsers(input, userDatabase, connection);
                    break;
                case 5:
                    library.returnBook(input, bookDatabase, userDatabase, connection);
                    break;
                case 6:
                    library.searchBook(input, bookDatabase);
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}
