import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * VIEW - contains only the Swing components. No calculation logic here.
 */
public class StudentView extends JFrame {
    private final JTextField nameField = new JTextField(15);
    private final JTextField subject1Field = new JTextField(5);
    private final JTextField subject2Field = new JTextField(5);
    private final JTextField subject3Field = new JTextField(5);
    private final JButton calculateButton = new JButton("Calculate Result");

    private final JLabel totalLabel = new JLabel("-");
    private final JLabel averageLabel = new JLabel("-");
    private final JLabel gradeLabel = new JLabel("-");

    public StudentView() {
        super("Student Grade Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nameField.setName("nameField");
        subject1Field.setName("subject1Field");
        subject2Field.setName("subject2Field");
        subject3Field.setName("subject3Field");
        calculateButton.setName("calculateButton");

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Student Details"));
        form.add(new JLabel("Student Name:"));   form.add(nameField);
        form.add(new JLabel("Subject 1 Marks:")); form.add(subject1Field);
        form.add(new JLabel("Subject 2 Marks:")); form.add(subject2Field);
        form.add(new JLabel("Subject 3 Marks:")); form.add(subject3Field);

        JPanel result = new JPanel(new GridLayout(0, 2, 8, 8));
        result.setBorder(BorderFactory.createTitledBorder("Result"));
        result.add(new JLabel("Total:"));   result.add(totalLabel);
        result.add(new JLabel("Average:")); result.add(averageLabel);
        result.add(new JLabel("Grade:"));   result.add(gradeLabel);
        gradeLabel.setFont(gradeLabel.getFont().deriveFont(Font.BOLD, 16f));

        JPanel buttonRow = new JPanel();
        buttonRow.add(calculateButton);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        root.add(form, BorderLayout.NORTH);
        root.add(buttonRow, BorderLayout.CENTER);
        root.add(result, BorderLayout.SOUTH);

        setContentPane(root);
        pack();
        setLocationRelativeTo(null);
    }

    public String getStudentName() { return nameField.getText().trim(); }
    public String getSubject1()    { return subject1Field.getText().trim(); }
    public String getSubject2()    { return subject2Field.getText().trim(); }
    public String getSubject3()    { return subject3Field.getText().trim(); }

    public void setResult(int total, double average, char grade) {
        totalLabel.setText(String.valueOf(total));
        averageLabel.setText(String.format("%.2f", average));
        gradeLabel.setText(String.valueOf(grade));
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }

    public void addCalculateListener(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }
}
