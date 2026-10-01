import javax.swing.SwingUtilities;

/** Entry point: creates the Model, both Views and the Controller, then shows the Login Window. */
public class EmployeePortalApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmployeeModel model = new EmployeeModel();
            LoginView loginView = new LoginView();
            MainView mainView = new MainView();
            new EmployeeController(model, loginView, mainView);
            loginView.setVisible(true);
        });
    }
}
