import java.sql.*;
import java.util.Scanner;

/**
 * Tutorial 9 - Q6: Display all students registered for a course.
 * Flow: MySQL connection -> PreparedStatement -> executeQuery() -> ResultSet processing
 * Table: CourseRegistration(StudentID, StudentName, CourseCode, CourseName, Semester)
 */
public class CourseRegistrationSearch {
    private static final String URL = "jdbc:mysql://localhost:3306/college_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";   // change to your MySQL password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Course Code: ");
        String courseCode = sc.nextLine().trim();

        String sql = "SELECT StudentID, StudentName, CourseCode, CourseName, Semester "
                   + "FROM CourseRegistration WHERE CourseCode = ? ORDER BY StudentID";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, courseCode);
            try (ResultSet rs = ps.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    if (count == 0) {
                        System.out.println("\nStudents registered for " + rs.getString("CourseCode")
                                + " - " + rs.getString("CourseName") + ":");
                        System.out.printf("%-18s %-20s %-10s %-32s %s%n",
                                "StudentID", "StudentName", "Course", "CourseName", "Semester");
                        System.out.println("-".repeat(92));
                    }
                    System.out.printf("%-18s %-20s %-10s %-32s %d%n",
                            rs.getString("StudentID"), rs.getString("StudentName"),
                            rs.getString("CourseCode"), rs.getString("CourseName"), rs.getInt("Semester"));
                    count++;
                }
                if (count == 0) {
                    System.out.println("No students are registered for the course code " + courseCode + ".");
                } else {
                    System.out.println("\nTotal students registered: " + count);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
