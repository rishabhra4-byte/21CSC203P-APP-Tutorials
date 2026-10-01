import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * VIEW - main application window with a menu bar:
 *   Employee -> Add Employee, View Employee
 *   Tools    -> Change Password
 *   Exit     -> Logout, Exit Application
 */
public class MainView extends JFrame {
    public static final String HOME = "home", ADD = "add", VIEW = "view", CHANGE_PWD = "change";

    // Menu items
    private final JMenuItem addEmployeeItem = new JMenuItem("Add Employee");
    private final JMenuItem viewEmployeeItem = new JMenuItem("View Employee");
    private final JMenuItem changePasswordItem = new JMenuItem("Change Password");
    private final JMenuItem logoutItem = new JMenuItem("Logout");
    private final JMenuItem exitItem = new JMenuItem("Exit Application");

    // Add Employee panel
    private final JTextField empIdField = new JTextField(12);
    private final JTextField empNameField = new JTextField(12);
    private final JTextField empDeptField = new JTextField(12);
    private final JButton saveEmployeeButton = new JButton("Save Employee");

    // View Employee panel
    private final JTextArea employeeListArea = new JTextArea(10, 34);

    // Change Password panel
    private final JPasswordField oldPasswordField = new JPasswordField(12);
    private final JPasswordField newPasswordField = new JPasswordField(12);
    private final JPasswordField confirmPasswordField = new JPasswordField(12);
    private final JButton updatePasswordButton = new JButton("Update Password");

    private final CardLayout cards = new CardLayout();
    private final JPanel cardPanel = new JPanel(cards);

    public MainView() {
        super("Employee Management Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        empIdField.setName("empIdField");
        empNameField.setName("empNameField");
        empDeptField.setName("empDeptField");
        saveEmployeeButton.setName("saveEmployeeButton");
        oldPasswordField.setName("oldPasswordField");
        newPasswordField.setName("newPasswordField");
        confirmPasswordField.setName("confirmPasswordField");
        updatePasswordButton.setName("updatePasswordButton");

        JMenuBar menuBar = new JMenuBar();
        JMenu employeeMenu = new JMenu("Employee");
        employeeMenu.add(addEmployeeItem);
        employeeMenu.add(viewEmployeeItem);
        JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.add(changePasswordItem);
        JMenu exitMenu = new JMenu("Exit");
        exitMenu.add(logoutItem);
        exitMenu.add(exitItem);
        menuBar.add(employeeMenu);
        menuBar.add(toolsMenu);
        menuBar.add(exitMenu);
        setJMenuBar(menuBar);

        JLabel welcome = new JLabel("Welcome, admin! Choose an option from the menu bar.", SwingConstants.CENTER);
        cardPanel.add(welcome, HOME);
        cardPanel.add(buildAddPanel(), ADD);
        cardPanel.add(buildViewPanel(), VIEW);
        cardPanel.add(buildChangePasswordPanel(), CHANGE_PWD);

        cardPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setContentPane(cardPanel);
        setSize(460, 320);
        setLocationRelativeTo(null);
    }

    private JPanel buildAddPanel() {
        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Add Employee"));
        form.add(new JLabel("Employee ID:"));   form.add(empIdField);
        form.add(new JLabel("Employee Name:")); form.add(empNameField);
        form.add(new JLabel("Department:"));    form.add(empDeptField);
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.add(form, BorderLayout.NORTH);
        JPanel buttonRow = new JPanel();
        buttonRow.add(saveEmployeeButton);
        panel.add(buttonRow, BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildViewPanel() {
        employeeListArea.setEditable(false);
        employeeListArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Employee List"));
        panel.add(new JScrollPane(employeeListArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildChangePasswordPanel() {
        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Change Password"));
        form.add(new JLabel("Old Password:"));     form.add(oldPasswordField);
        form.add(new JLabel("New Password:"));     form.add(newPasswordField);
        form.add(new JLabel("Confirm Password:")); form.add(confirmPasswordField);
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.add(form, BorderLayout.NORTH);
        JPanel buttonRow = new JPanel();
        buttonRow.add(updatePasswordButton);
        panel.add(buttonRow, BorderLayout.CENTER);
        return panel;
    }

    public void showCard(String name) { cards.show(cardPanel, name); }

    // ---- getters used by the controller ----
    public String getEmpId()           { return empIdField.getText().trim(); }
    public String getEmpName()         { return empNameField.getText().trim(); }
    public String getEmpDept()         { return empDeptField.getText().trim(); }
    public String getOldPassword()     { return new String(oldPasswordField.getPassword()); }
    public String getNewPassword()     { return new String(newPasswordField.getPassword()); }
    public String getConfirmPassword() { return new String(confirmPasswordField.getPassword()); }

    public void clearAddForm() {
        empIdField.setText("");
        empNameField.setText("");
        empDeptField.setText("");
    }

    public void clearPasswordForm() {
        oldPasswordField.setText("");
        newPasswordField.setText("");
        confirmPasswordField.setText("");
    }

    public void displayEmployees(List<Employee> employees) {
        StringBuilder sb = new StringBuilder(String.format("%-8s %-16s %s%n", "ID", "Name", "Department"));
        sb.append("-".repeat(40)).append('\n');
        if (employees.isEmpty()) {
            sb.append("No employees added yet.");
        }
        for (Employee e : employees) {
            sb.append(String.format("%-8s %-16s %s%n", e.getId(), e.getName(), e.getDepartment()));
        }
        employeeListArea.setText(sb.toString());
    }

    public void showMessage(String message, String title, int type) {
        JOptionPane.showMessageDialog(this, message, title, type);
    }

    // ---- listener registration ----
    public void addAddEmployeeMenuListener(ActionListener l)    { addEmployeeItem.addActionListener(l); }
    public void addViewEmployeeMenuListener(ActionListener l)   { viewEmployeeItem.addActionListener(l); }
    public void addChangePasswordMenuListener(ActionListener l) { changePasswordItem.addActionListener(l); }
    public void addLogoutListener(ActionListener l)             { logoutItem.addActionListener(l); }
    public void addExitListener(ActionListener l)               { exitItem.addActionListener(l); }
    public void addSaveEmployeeListener(ActionListener l)       { saveEmployeeButton.addActionListener(l); }
    public void addUpdatePasswordListener(ActionListener l)     { updatePasswordButton.addActionListener(l); }
}
