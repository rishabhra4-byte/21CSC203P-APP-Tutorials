import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MODEL - holds the login credentials and the employee list,
 * and contains all validation rules.
 */
public class EmployeeModel {
    private final String username = "admin";
    private String password = "admin123";
    private final List<Employee> employees = new ArrayList<>();

    public boolean validateLogin(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }

    /** @return true if added, false if an employee with the same ID already exists */
    public boolean addEmployee(String id, String name, String department) {
        for (Employee e : employees) {
            if (e.getId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        employees.add(new Employee(id, name, department));
        return true;
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public boolean isCurrentPassword(String pass) {
        return password.equals(pass);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
