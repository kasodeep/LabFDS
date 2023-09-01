package practicals.lab1;

public class Main {

    public static void main(String[] args) {

        // Using the constructor to initialize the object.
        Student student = new Student("Deep", "Male", 19, 221080029, "I.T", 3, 9.3);
        System.out.print("Student Info: ");
        System.out.println("Name: " + student.getName());
        System.out.println("Gender: " + student.getGender());
        System.out.println("Age: " + student.getAge());

        System.out.println("Roll No: " + student.getRollNo());
        System.out.println("Class: " + student.getClasses());
        System.out.println("Semester: " + student.getSemester());
        System.out.println("CPI: " + student.getCpi());

        System.out.println();

        // Using the constructor to initialize the object.
        Faculty faculty = new Faculty("Sir", "Male", 35, 102, 100000, "AI/ML");
        System.out.println("Faculty Info:");
        System.out.println("Name: " + faculty.getName());
        System.out.println("Gender: " + faculty.getGender());
        System.out.println("Age: " + student.getAge());

        System.out.println("Id: " + faculty.getEmpId());
        System.out.println("Salary: " + faculty.getSalary());
        System.out.println("Specialization: " + faculty.getSpecialization());

        student.setName("John Cena");
        System.out.println(student.getName());
    }
}
