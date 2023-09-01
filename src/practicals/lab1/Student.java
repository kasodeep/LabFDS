package practicals.lab1;

public class Student extends Human {

    private int rollNo;
    private String classes;
    private int semester;
    private double cpi;

    public Student(String name, String gender, int age, int rollNo, String classes, int semester, double cpi) {
        super(name, gender, age);
        this.rollNo = rollNo;
        this.classes = classes;
        this.semester = semester;
        this.cpi = cpi;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getCpi() {
        return cpi;
    }

    public void setCpi(double cpi) {
        this.cpi = cpi;
    }
}
