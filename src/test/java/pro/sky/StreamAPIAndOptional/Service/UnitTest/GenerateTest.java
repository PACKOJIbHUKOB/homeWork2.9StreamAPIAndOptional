package pro.sky.StreamAPIAndOptional.Service.UnitTest;

import pro.sky.StreamAPIAndOptional.Employee;

import java.util.Arrays;
import java.util.List;

public class GenerateTest {

    static public final String FIRST_NAME_1 = "Иван";
    static public final String LAST_NAME_1 = "Иванов";
    static public final double SALARY_1 = 36000;

    static public final String FIRST_NAME_2 = "Николай";
    static public final String LAST_NAME_2 = "Николаев";
    static public final double SALARY_2 = 58000;

    static public final String FIRST_NAME_3 = "Сергей";
    static public final String LAST_NAME_3 = "Сергеев";
    static public final double SALARY_3 = 94000;

    static public final Integer FIRST_DEPARTMENT_ID = 1;
    static public final Integer LAST_DEPARTMENT_ID = 2;


    public static Employee getEmployee1(){
        return new Employee(FIRST_NAME_1,LAST_NAME_1,FIRST_DEPARTMENT_ID, SALARY_1);
    }
    public static Employee getEmployee2() {
        return new Employee(FIRST_NAME_2, LAST_NAME_2,FIRST_DEPARTMENT_ID , SALARY_2);
    }
    public static Employee getEmployee3(){
        return new Employee(FIRST_NAME_3,LAST_NAME_3,LAST_DEPARTMENT_ID, SALARY_3);
    }
    public static List<Employee> getAllEmployee() {
        return Arrays.asList(getEmployee1(), getEmployee2(), getEmployee3());
    }

}
