import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/** VIEW - the Login Window (JTextField + JPasswordField + Login button). */
public class LoginView extends JFrame {
    private final JTextField usernameField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JButton loginButton = new JButton("Login");

    public LoginView() {
        super("Employee Portal - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usernameField.setName("usernameField");
        passwordField.setName("passwordField");
        loginButton.setName("loginButton");

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("Username:"));
        form.add(usernameField);
        form.add(new JLabel("Password:"));
        form.add(passwordField);

        JLabel title = new JLabel("Employee Management Portal", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));

        JPanel buttonRow = new JPanel();
        buttonRow.add(loginButton);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        root.add(title, BorderLayout.NORTH);
        root.add(form, BorderLayout.CENTER);
        root.add(buttonRow, BorderLayout.SOUTH);
        setContentPane(root);
        getRootPane().setDefaultButton(loginButton);
        pack();
        setLocationRelativeTo(null);
    }

    public String getUsername() { return usernameField.getText().trim(); }
    public String getPassword() { return new String(passwordField.getPassword()); }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}
