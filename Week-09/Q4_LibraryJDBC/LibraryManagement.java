import java.sql.*;
import java.util.Scanner;

/**
 * Tutorial 9 - Q4: Library management using JDBC and MySQL.
 * Table: Book(BookID, Title, Author, Price, Availability)
 *
 * Compile: javac LibraryManagement.java
 * Run:     java -cp ".;mysql-connector-j-8.x.x.jar" LibraryManagement   (Windows)
 *          java -cp ".:mysql-connector-j-8.x.x.jar" LibraryManagement   (Linux/Mac)
 */
public class LibraryManagement {
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";   // change to your MySQL password

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connected to library_db successfully.");
            int choice;
            do {
                System.out.println("\n===== LIBRARY MENU =====");
                System.out.println("1. Insert a new book");
                System.out.println("2. Search book by Book ID");
                System.out.println("3. Display all available books");
                System.out.println("4. Issue a book (change availability)");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1 -> insertBook(con, sc);
                    case 2 -> searchBook(con, sc);
                    case 3 -> displayAvailableBooks(con);
                    case 4 -> issueBook(con, sc);
                    case 5 -> System.out.println("Exiting... Goodbye!");
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 5);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // 1. Insert details of a new book
    private static void insertBook(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Book ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Enter Title: ");
        String title = sc.nextLine().trim();
        System.out.print("Enter Author: ");
        String author = sc.nextLine().trim();
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(sc.nextLine().trim());

        String sql = "INSERT INTO Book (BookID, Title, Author, Price, Availability) VALUES (?, ?, ?, ?, 'Available')";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setDouble(4, price);
            int rows = ps.executeUpdate();
            System.out.println(rows + " book inserted successfully.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("A book with ID " + id + " already exists.");
        }
    }

    // 2. Search for a book using its Book ID
    private static void searchBook(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Book ID to search: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        String sql = "SELECT * FROM Book WHERE BookID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    printHeader();
                    printRow(rs);
                } else {
                    System.out.println("No book found with ID " + id + ".");
                }
            }
        }
    }

    // 3. Display all available books
    private static void displayAvailableBooks(Connection con) throws SQLException {
        String sql = "SELECT * FROM Book WHERE Availability = 'Available' ORDER BY BookID";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            printHeader();
            boolean any = false;
            while (rs.next()) {
                printRow(rs);
                any = true;
            }
            if (!any) System.out.println("No books are currently available.");
        }
    }

    // 4. Change the availability status of a book after it is issued
    private static void issueBook(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Book ID to issue: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        String sql = "UPDATE Book SET Availability = 'Issued' WHERE BookID = ? AND Availability = 'Available'";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Book " + id + " issued. Availability changed to 'Issued'.");
            } else {
                System.out.println("Book " + id + " does not exist or is already issued.");
            }
        }
    }

    private static void printHeader() {
        System.out.printf("%-7s %-28s %-22s %9s  %s%n", "BookID", "Title", "Author", "Price", "Availability");
        System.out.println("-".repeat(82));
    }

    private static void printRow(ResultSet rs) throws SQLException {
        System.out.printf("%-7d %-28s %-22s %9.2f  %s%n",
                rs.getInt("BookID"), rs.getString("Title"), rs.getString("Author"),
                rs.getDouble("Price"), rs.getString("Availability"));
    }
}
