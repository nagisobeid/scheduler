package nso.scheduler;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {

    private static Employee currentEmployee;
    private static String currentBusiness;
    private static ArrayList<Shift> shifts = new ArrayList<>();
    private static ArrayList<Employee> employees = new ArrayList<>();

    //private static HashMap<String, ArrayList<HashMap<Day, ArrayList<String>>>> allEmployeeConditions;
                           //EMP                   //DAY  //CONDITION

    public static void setCurrentBusiness(String businessName) {
        currentBusiness = businessName;
    }

    public static String getCurrentBusiness() {
        return currentBusiness;
    }

    public static void addEmployee(Employee e) {
        employees.add(e);
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    public static void setCurrentEmployee(Employee emp) {
        currentEmployee = emp;
    }

    public static Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void addShift(Shift shift) {
        shifts.add(shift);
    }

    public static ArrayList getShifts() {
        return shifts;
    }

    public static void deleteShift(int id) {
        for(Shift s: shifts) {
            if(s.getId() == id) {
                shifts.remove(s);
            }
        }
    }

    public static void addEmployeeData(Employee employee) {
        employees.add(employee);
    }



    /////////////////////////////////////////////
    private static final Data data = new Data();
    public static Data getInstance() {return data;}
}
