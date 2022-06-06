
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Employee extends AbsProject{
    static int memberNum=0;
    
    private int employeeId;
    private String name;
    private String sname;
    private int age;
    private String department;
    private float salary;

    public Employee() {
        memberNum++;
    }

    public Employee(int employeeId, String name, String sname, int age, String department, float salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.sname = sname;
        this.age = age;
        this.department = department;
        this.salary = salary;
        memberNum++;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getSname() {
        return sname;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public float getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "\nemployeeId=" + Integer.toString(employeeId) + "\nname=" + name + "\nsname=" + sname + "\nage=" + Integer.toString(age) + "\ndepartment=" + department + "\nsalary=" + Float.toString(salary) ;
    }
    
    
}
