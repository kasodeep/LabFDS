package practicals.lab1;

public class Faculty extends Human {

    private int empId;
    private int salary;
    private String specialization;

    public Faculty(String name, String gender, int age, int empId, int salary, String specialization) {
        super(name, gender, age);
        this.empId = empId;
        this.salary = salary;
        this.specialization = specialization;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
