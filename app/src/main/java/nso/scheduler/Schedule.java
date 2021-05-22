package nso.scheduler;

import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * Schedule class responsible for generate and storing schedule data
 */
public class Schedule {
    ArrayList<String> days;
    ArrayList<Shift> shifts;
    ArrayList<Employee> employees;


    /**
     * initializes the days of the week
     */
    public Schedule() {
        days = new ArrayList<>();
        days.add("monday");
        days.add("tuesday");
        days.add("wednesday");
        days.add("thursday");
        days.add("friday");
        days.add("saturday");
        days.add("sunday");

        shifts = Data.getInstance().getShifts();
        employees = Data.getInstance().getEmployees();
    }

    /**
     * generates a schedule based on all the applied shifts and conditions and employees
     */
    public void generateSchedule() {
        //ArrayList<Employee> es = Data.getInstance().getEmployees();

        //Looping through all employees to resets any schedule that was already made for them
        for(Employee employee: employees) {
            employee.resetSchedule();
        }

        //Looping through each shift and finding employees that meet the shift condiions
        for(Shift shift : shifts) {
            //System.out.println("SHIFT STARTED----------------------------------|");
            int[] shiftStart = new int[2];
            int[] shiftEnd = new int[2];

            ArrayList<Employee> employeePool = new ArrayList<>();
            // limit = number of employees required for the current shift
            int limit = 0;

            // loop until limit has been reached and each position has been filled for the current shift
            while(limit < shift.getNumEmployees()) {

                // Loop throuh all employees and analyze their conditions
                for(Employee employee : employees) {
                    //System.out.println("EMPLOYEE STARTED----------------------------------|");
                    int[] empCondStart = new int[2];
                    int[] empCondEnd = new int[2];
                    //System.out.println("current employee" + employee.getEmployeeName());
                    HashMap<String, String> eSchedule = employee.getSchedule();

                    //CONVERTING SHIFT TIMES TO INT VALUES FOR COMPARISION
                    if(shift.getShiftStart().length() == 4) {
                        char x = shift.getShiftStart().charAt(0);
                        shiftStart[0] = Character.getNumericValue(x);
                        String e = "" + shift.getShiftStart().charAt(2) + shift.getShiftStart().charAt(3);
                        shiftStart[1] = Integer.parseInt(e);
                    }
                    if(shift.getShiftEnd().length() == 4) {
                        char x = shift.getShiftEnd().charAt(0);
                        shiftEnd[0] = Character.getNumericValue(x);
                        String e = "" + shift.getShiftEnd().charAt(2) + shift.getShiftEnd().charAt(3);
                        shiftEnd[1] = Integer.parseInt(e);
                    }
                    if(shift.getShiftStart().length() == 5) {
                        String s = "" + shift.getShiftStart().charAt(0) + shift.getShiftStart().charAt(1);
                        shiftStart[0] = Integer.parseInt(s);

                        String e = "" + shift.getShiftStart().charAt(3) + shift.getShiftStart().charAt(4);
                        shiftStart[1] = Integer.parseInt(e);
                    }
                    if(shift.getShiftEnd().length() == 5) {
                        String s = "" + shift.getShiftEnd().charAt(0) + shift.getShiftEnd().charAt(1);
                        shiftEnd[0] = Integer.parseInt(s);

                        String e = "" + shift.getShiftEnd().charAt(3) + shift.getShiftEnd().charAt(4);
                        shiftEnd[1] = Integer.parseInt(e);
                    }

                    // IF EMPLOYEE HAS NOT BEEN SCHEDULED CONSIDER CANDIDATE FOR CURRENT SHIFT
                    if (!eSchedule.containsKey(shift.getDay())) {
                        ArrayList<String[]> eDayConditions = employee.getDayConditions(shift.getDay());

                        //LOOP THROUGH EMPLOYEES CONDITIONS FOR THE DAY AND COMPARE WITH THE SHIFT CONDITIONS
                        // IF A MATCH IS FOUND MOVE FORWARD BY ADDING THE EMPLOYEE TO THE EMPLOYEE POOL
                        for(String[] x: eDayConditions) {
                            if(x[0].length() == 4) {
                                char z = x[0].charAt(0);
                                empCondStart[0] = Character.getNumericValue(z);
                                String e = "" + x[0].charAt(2) + x[0].charAt(3);
                                empCondStart[1] = Integer.parseInt(e);
                            }
                            if(x[1].length() == 4) {
                                char z = x[1].charAt(0);
                                empCondEnd[0] = Character.getNumericValue(z);
                                String e = "" + x[1].charAt(2) + x[1].charAt(3);
                                empCondEnd[1] = Integer.parseInt(e);
                            }
                            if(x[0].length() == 5) {
                                String s = "" + x[0].charAt(0) + x[0].charAt(1);
                                empCondStart[0] = Integer.parseInt(s);
                                String e = "" + x[0].charAt(3) + x[0].charAt(4);
                                empCondStart[1] = Integer.parseInt(e);
                            }
                            if(x[1].length() == 5) {
                                String s = "" + x[1].charAt(0) + x[1].charAt(1);
                                empCondEnd[0] = Integer.parseInt(s);
                                String e = "" + x[1].charAt(3) + x[1].charAt(4);
                                empCondEnd[1] = Integer.parseInt(e);
                            }
                            //System.out.println(employee.getEmployeeName() + empCondStart[0] +  "-" + empCondEnd[0]);
                            //System.out.println(empCondStart[0] + ":" + empCondStart[1] + "-" + empCondEnd[0]+ ":" + empCondEnd[1]);
                            if(empCondEnd[0] < shiftStart[0] || empCondStart[0] > shiftEnd[0]) {
                                //ADD EMPLOYEE TO EMPLOYEE POOL, EMPLOYEES HERE WILL BE CONSIDIERED
                                employeePool.add(employee);
                                //System.out.println("x");
                            }
                        }
                        // AUTOMATICALLY ADD THE EMPLOYEE TO THE POOL IF TH EMPLOYEE HAS NO CONDITIONS FOR THE DAY
                        if(eDayConditions.isEmpty()) {
                            employeePool.add(employee);
                        }
                    }
                }
                // SHUFFLE THE EMPLOYEE POOL 5 TIMES FOR RANDOMIZATION, AS THE EMPLOYEE TO BE SELECT WILL BE AT INDEX 0
                if(!employeePool.isEmpty()) {
                    for(int i = 0; i < 5; i++) {
                        Collections.shuffle(employeePool);
                    }
                    // ADD THE CURRENT EMPLOYEE TO THE SCHEDULE
                    employeePool.get(0).addToSchedule(shift.getDay(), shift.getShiftStart() + "-" + shift.getShiftEnd());
                    System.out.println("NAME: " + employeePool.get(0).getEmployeeName() +" "+ "DAY: "+ shift.getDay() + " SHIFT: "+ shift.getShiftStart() + "-" + shift.getShiftEnd());
                    limit++;
                    // RESET THE EMPLOYEE POOL AND PERFORM LOOP AGAIN UNTIL LIMIT IS REACHED
                    employeePool.clear();
                }
                //break;
            }
        }
        System.out.println("-----------------------------------------------------------");
    }
}
