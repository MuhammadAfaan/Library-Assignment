package library;

public class User {

    int userId;
    String userName;
    String contactInfo;
    int borrowedBooks;

    User(int userId, String userName, String contactInfo, int borrowedBooks) {
        this.userId = userId;
        this.userName = userName;
        this.contactInfo = contactInfo;
        this.borrowedBooks = borrowedBooks;

    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getUserName() {
        return userName;
    }

    // Setters
    public void setBorrowedBooks(int borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
