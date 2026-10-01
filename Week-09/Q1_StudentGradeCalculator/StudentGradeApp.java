import javax.swing.SwingUtilities;

/** Entry point: wires Model, View and Controller together. */
public class StudentGradeApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentModel model = new StudentModel();
            StudentView view = new StudentView();
            new StudentController(model, view);
            view.setVisible(true);
        });
    }
}
