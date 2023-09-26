package pro.sky.StreamAPIAndOptional;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Employee {
    private int id;

    private String firstName;
    private String lastName;
    private int departmentNumber;

    private double salaryEmployee;

    private static int Counter;


    public Employee(String firstName, String lastName, int departmentNumber) {
        this.firstName = chekFirstName(firstName);
        this.lastName = chekLastName(lastName);
        this.departmentNumber = departmentNumber;
        salaryEmployee = randomSalary();
        id=Counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getDepartmentNumber() == employee.getDepartmentNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartmentNumber());
    }

    public String getFirstName() {
        return firstName;
    }

    public int getID() {
        return id;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }
    public void setDepartmentNumber(int departmentNumber) {
        this.departmentNumber = departmentNumber;
    }
    public double getSalaryEmployee() {
        return salaryEmployee;
    }

    public void setCounter(int counter) {
        this.Counter = counter;
    }

    public void setSalaryEmployee(double salaryEmployee) {
        this.salaryEmployee = salaryEmployee;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    private static double randomSalary(){
        double min = Math.ceil(1);
        double max = Math.floor(100000);
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
    public String chekFirstName (String firstName){
       if(!StringUtils.isAlpha(firstName)||StringUtils.isBlank(firstName)){
           throw new RuntimeException("имя введено не верно");}
       return StringUtils.capitalize(firstName);

       }
    public String chekLastName (String lastName){
        if (!StringUtils.isAlpha(lastName)||StringUtils.isBlank(lastName)){
            throw new RuntimeException("фамилия введена не верно");}

        return StringUtils.capitalize(lastName);
    }



}

