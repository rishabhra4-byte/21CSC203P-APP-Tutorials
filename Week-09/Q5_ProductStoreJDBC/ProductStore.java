import java.sql.*;
import java.util.Scanner;

/**
 * Tutorial 9 - Q5: Store product management using JDBC (Connection, PreparedStatement, ResultSet).
 * Table: Product(ProductID, ProductName, Price, Quantity)
 */
public class ProductStore {
    private static final String URL = "jdbc:mysql://localhost:3306/store_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";   // change to your MySQL password

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connected to store_db successfully.");
            int choice;
            do {
                System.out.println("\n===== STORE MENU =====");
                System.out.println("1. Insert a new product");
                System.out.println("2. Retrieve product by Product ID");
                System.out.println("3. Update product quantity");
                System.out.println("4. Display products with quantity below 10");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1 -> insertProduct(con, sc);
                    case 2 -> retrieveProduct(con, sc);
                    case 3 -> updateQuantity(con, sc);
                    case 4 -> displayLowStock(con);
                    case 5 -> System.out.println("Exiting... Goodbye!");
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 5);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void insertProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(sc.nextLine().trim());
        System.out.print("Enter Quantity: ");
        int qty = Integer.parseInt(sc.nextLine().trim());

        String sql = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, qty);
            System.out.println(ps.executeUpdate() + " product inserted successfully.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("A product with ID " + id + " already exists.");
        }
    }

    private static void retrieveProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM Product WHERE ProductID = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    printHeader();
                    printRow(rs);
                } else {
                    System.out.println("No product found with ID " + id + ".");
                }
            }
        }
    }

    private static void updateQuantity(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Enter new Quantity: ");
        int qty = Integer.parseInt(sc.nextLine().trim());

        try (PreparedStatement ps = con.prepareStatement("UPDATE Product SET Quantity = ? WHERE ProductID = ?")) {
            ps.setInt(1, qty);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Quantity updated successfully." : "No product found with ID " + id + ".");
        }
    }

    private static void displayLowStock(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM Product WHERE Quantity < ? ORDER BY Quantity")) {
            ps.setInt(1, 10);
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Products with quantity below 10:");
                printHeader();
                boolean any = false;
                while (rs.next()) {
                    printRow(rs);
                    any = true;
                }
                if (!any) System.out.println("All products have sufficient stock.");
            }
        }
    }

    private static void printHeader() {
        System.out.printf("%-10s %-20s %10s %9s%n", "ProductID", "ProductName", "Price", "Quantity");
        System.out.println("-".repeat(52));
    }

    private static void printRow(ResultSet rs) throws SQLException {
        System.out.printf("%-10d %-20s %10.2f %9d%n",
                rs.getInt("ProductID"), rs.getString("ProductName"),
                rs.getDouble("Price"), rs.getInt("Quantity"));
    }
}
