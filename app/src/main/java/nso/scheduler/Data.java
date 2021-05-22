package nso.scheduler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Singleton Data holder class responsible for holding data across multiple activities
 */
public class Data {

    private static Employee currentEmployee;
    private static String currentBusiness;
    private static String currentDay;
    private static ArrayList<Shift> shifts = new ArrayList<>();
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static boolean settingConditions;

    //private static HashMap<String, ArrayList<HashMap<Day, ArrayList<String>>>> allEmployeeConditions;
                           //EMP                   //DAY  //CONDITION

    /**
     * accepts a string to set the current day
     * @param day
     */
    public static void setCurrentDay(String day) {
        currentDay = day;
    }

    /**
     * return a string representin gthe current day
     * @return
     */
    public String getCurrentDay() {
        return currentDay;
    }

    /**
     * sets the status of whether user is on conditions page or shifts page and redirects from DayActivity to approproate activity
     * @param status
     */
    public static void setSettingConditions(boolean status) {
        settingConditions = status;
    }

    /**
     * returns the boolean value of the setting conditions status
     * @return
     */
    public static boolean getSettingConditions() {
        return settingConditions;
    }

    /**
     * sets the business name of the current business or user
     * @param businessName
     */
    public static void setCurrentBusiness(String businessName) {
        currentBusiness = businessName;
    }

    /**
     * gets the business or user name
     * @return
     */
    public static String getCurrentBusiness() {
        return currentBusiness;
    }

    /**
     * adds an Employee to the employee arraylist
     * @param e
     */
    public static void addEmployee(Employee e) {
        employees.add(e);
    }

    /**
     * gets all Employees in arraylist
     * @return
     */
    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * sets the current employee of the session, used in add condition activity
     * @param emp
     */
    public static void setCurrentEmployee(Employee emp) {
        currentEmployee = emp;
    }

    /**
     * returns an employee object representing the current employee
     * @return
     */
    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    /**
     * adds a shift to the shifts activity
     * @param shift
     */
    public static void addShift(Shift shift) {
        shifts.add(shift);
    }

    /**
     * returns all the shifts in the current session
     * @return
     */
    public static ArrayList getShifts() {
        return shifts;
    }

    /**
     * responsibnle for removing a shift from the arraylist
     * @param id
     */
    public static void deleteShift(int id) {
        for(Shift s: shifts) {
            if(s.getId() == id) {
                shifts.remove(s);
            }
        }
    }

    /**
     * no user for this, was here for testing
     * @param employee
     */
    public static void addEmployeeData(Employee employee) {
        employees.add(employee);
    }

    /////////////////////////////////////////////
    /**
     * Instantiate the singlton class
     */
    private static final Data data = new Data();

    /**
     * responsible for getting an instance of the Data class.
     * @return
     */
    public static Data getInstance() {return data;}
}
