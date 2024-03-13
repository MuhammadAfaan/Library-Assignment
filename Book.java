package library;

public class Book {

    int bookID;
    String bookTitle;
    String author;
    String genre;
    int availabilityBook;

    Book(int bookID, String bookTitle, String author, String genre, int availabilityBook) {

        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
        this.availabilityBook = availabilityBook;

    }

    // Getters Method
    public int getBookID() {
        return bookID;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getAvailabilityBook() {
        return availabilityBook;
    }

    public String getGenre() {
        return genre;
    }

    // Setters Method
    public void setAvailabilityBook(int availabilityBook) {
        this.availabilityBook = availabilityBook;
    }
}
