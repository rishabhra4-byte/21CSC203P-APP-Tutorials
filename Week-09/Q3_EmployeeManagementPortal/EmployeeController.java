import javax.swing.JOptionPane;

/**
 * CONTROLLER - handles login, menu actions, adding employees and changing the password.
 * It reads from the Views, updates the Model and tells the Views what to show.
 */
public class EmployeeController {
    private final EmployeeModel model;
    private final LoginView loginView;
    private final MainView mainView;

    public EmployeeController(EmployeeModel model, LoginView loginView, MainView mainView) {
        this.model = model;
        this.loginView = loginView;
        this.mainView = mainView;

        loginView.addLoginListener(e -> handleLogin());

        mainView.addAddEmployeeMenuListener(e -> mainView.showCard(MainView.ADD));
        mainView.addViewEmployeeMenuListener(e -> {
            mainView.displayEmployees(model.getEmployees());
            mainView.showCard(MainView.VIEW);
        });
        mainView.addChangePasswordMenuListener(e -> mainView.showCard(MainView.CHANGE_PWD));
        mainView.addLogoutListener(e -> handleLogout());
        mainView.addExitListener(e -> System.exit(0));

        mainView.addSaveEmployeeListener(e -> handleAddEmployee());
        mainView.addUpdatePasswordListener(e -> handleChangePassword());
    }

    private void handleLogin() {
        if (model.validateLogin(loginView.getUsername(), loginView.getPassword())) {
            JOptionPane.showMessageDialog(loginView, "Login successful! Welcome, admin.",
                    "Login", JOptionPane.INFORMATION_MESSAGE);
            loginView.clearFields();
            loginView.setVisible(false);
            mainView.showCard(MainView.HOME);
            mainView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(loginView, "Invalid username or password.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLogout() {
        mainView.setVisible(false);
        loginView.setVisible(true);
    }

    private void handleAddEmployee() {
        String id = mainView.getEmpId();
        String name = mainView.getEmpName();
        String dept = mainView.getEmpDept();

        if (id.isEmpty() || name.isEmpty() || dept.isEmpty()) {
            mainView.showMessage("All fields are required.", "Add Employee", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (model.addEmployee(id, name, dept)) {
            mainView.showMessage("Employee " + name + " added successfully.", "Add Employee",
                    JOptionPane.INFORMATION_MESSAGE);
            mainView.clearAddForm();
        } else {
            mainView.showMessage("An employee with ID " + id + " already exists.", "Add Employee",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleChangePassword() {
        String oldPass = mainView.getOldPassword();
        String newPass = mainView.getNewPassword();
        String confirm = mainView.getConfirmPassword();

        if (!model.isCurrentPassword(oldPass)) {
            mainView.showMessage("Old password is incorrect.", "Change Password", JOptionPane.ERROR_MESSAGE);
        } else if (newPass.isEmpty()) {
            mainView.showMessage("New password cannot be empty.", "Change Password", JOptionPane.WARNING_MESSAGE);
        } else if (!newPass.equals(confirm)) {
            mainView.showMessage("New password and Confirm password do not match.", "Change Password",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            model.changePassword(newPass);
            mainView.showMessage("Password changed successfully.", "Change Password",
                    JOptionPane.INFORMATION_MESSAGE);
            mainView.clearPasswordForm();
        }
    }
}
