/**
 * CONTROLLER - handles the button click, reads input from the View,
 * asks the Model to calculate and sends the result back to the View.
 */
public class StudentController {
    private final StudentModel model;
    private final StudentView view;

    public StudentController(StudentModel model, StudentView view) {
        this.model = model;
        this.view = view;
        this.view.addCalculateListener(e -> calculate());
    }

    private void calculate() {
        String name = view.getStudentName();
        if (name.isEmpty()) {
            view.showError("Please enter the student name.");
            return;
        }
        try {
            int m1 = parseMark(view.getSubject1());
            int m2 = parseMark(view.getSubject2());
            int m3 = parseMark(view.getSubject3());

            model.setStudentDetails(name, m1, m2, m3);
            model.calculateResult();
            view.setResult(model.getTotal(), model.getAverage(), model.getGrade());
        } catch (NumberFormatException ex) {
            view.showError("Marks must be whole numbers between 0 and 100.");
        }
    }

    private int parseMark(String text) {
        int mark = Integer.parseInt(text);
        if (mark < 0 || mark > 100) {
            throw new NumberFormatException("out of range");
        }
        return mark;
    }
}
