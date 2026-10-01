/**
 * MODEL - stores student details and performs the result calculation.
 * It knows nothing about Swing.
 */
public class StudentModel {
    private String name;
    private int[] marks = new int[3];
    private int total;
    private double average;
    private char grade;

    public void setStudentDetails(String name, int m1, int m2, int m3) {
        this.name = name;
        this.marks[0] = m1;
        this.marks[1] = m2;
        this.marks[2] = m3;
    }

    public void calculateResult() {
        total = marks[0] + marks[1] + marks[2];
        average = total / 3.0;

        if (average >= 90)      grade = 'A';
        else if (average >= 75) grade = 'B';
        else if (average >= 60) grade = 'C';
        else if (average >= 50) grade = 'D';
        else                    grade = 'F';
    }

    public String getName()     { return name; }
    public int getTotal()       { return total; }
    public double getAverage()  { return average; }
    public char getGrade()      { return grade; }
}
