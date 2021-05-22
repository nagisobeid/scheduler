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

public class Schedule {
    ArrayList<String> days;
    ArrayList<Shift> shifts;
    ArrayList<Employee> employees;


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

    public void generateSchedule() {
        //ArrayList<Employee> es = Data.getInstance().getEmployees();
        for(Employee employee: employees) {
            employee.resetSchedule();
        }

        for(Shift shift : shifts) {
            //System.out.println("SHIFT STARTED----------------------------------|");
            int[] shiftStart = new int[2];
            int[] shiftEnd = new int[2];

            ArrayList<Employee> employeePool = new ArrayList<>();
            int limit = 0;
            while(limit < shift.getNumEmployees()) {
                for(Employee employee : employees) {
                    //System.out.println("EMPLOYEE STARTED----------------------------------|");
                    int[] empCondStart = new int[2];
                    int[] empCondEnd = new int[2];
                    //System.out.println("current employee" + employee.getEmployeeName());
                    HashMap<String, String> eSchedule = employee.getSchedule();

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
                    //System.out.println(shiftStart[0]+"-" + shiftEnd[0]);
                    //System.out.println(shiftStart[0]+ ":" + shiftStart[1] + "-" + shiftEnd[0]+ ":" + shiftEnd[1]);

                    // IF EMPLOYEE HAS NOT BEEN SCHEDULED
                    if (!eSchedule.containsKey(shift.getDay())) {
                        ArrayList<String[]> eDayConditions = employee.getDayConditions(shift.getDay());

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
                                //System.out.println("INSIDE POOL");
                                employeePool.add(employee);
                                //System.out.println("x");
                            }
                        }
                        if(eDayConditions.isEmpty()) {
                            employeePool.add(employee);
                        }
                        /*if(empCondEnd[0] < shiftStart[0] || empCondStart[0] > shiftEnd[0]) {
                            employeePool.add(employee);
                            //System.out.println("x");
                        }*/

                        // CHECK WITH TIME CONFLICTS
                        /*
                        if(empCondEnd[0] > shiftStart[0] && empCondEnd[0] < shiftEnd[0] || !(empCondStart[0] < shiftStart[0] && empCondEnd[0] > shiftStart[0])
                        || !(empCondStart[0] > shiftStart[0] && empCondEnd[0] < shiftEnd[0])
                        || !(empCondStart[0] < shiftEnd[0] && empCondEnd[0] > shiftEnd[0])) {
                            employeePool.add(employee);
                        } else {
                            //employeePool.add(employee);
                        }
                        */
                        /*if(!(empCondStart[0] > shiftStart[0] && empCondStart[0] < shiftEnd[0])
                            || !(empCondEnd[0] > shiftStart[0] && empCondEnd[0] < shiftEnd[0])) {
                            employeePool.add(employee);
                            //System.out.println("valid " + shift.getDay() +  shift.getShiftStart() + shift.getShiftEnd() + employee.getEmployeeName() + empCondStart[0] + empCondEnd[0]);
                        }*/

                        /*
                        if(empCondStart[0] < shiftStart[0] && empCondEnd[0] < shiftStart[0]         //ok
                                || empCondStart[0] > shiftEnd[0]//ok
                                || eDayConditions.isEmpty()
                                || empCondEnd[0] > shiftStart[0] && empCondEnd[0] < shiftEnd[0]) {
                            employeePool.add(employee);
                            System.out.println("valid " + shift.getDay() +  shift.getShiftStart() + shift.getShiftEnd() + employee.getEmployeeName() + empCondStart[0] + empCondEnd[0]);
                        }

                         */


                    }
                }
                // SHUFFLE THE EMPLOYEE POOL
                if(!employeePool.isEmpty()) {
                    for(int i = 0; i < 5; i++) {
                        Collections.shuffle(employeePool);
                    }
                    employeePool.get(0).addToSchedule(shift.getDay(), shift.getShiftStart() + "-" + shift.getShiftEnd());
                    System.out.println("NAME: " + employeePool.get(0).getEmployeeName() +" "+ "DAY: "+ shift.getDay() + " SHIFT: "+ shift.getShiftStart() + "-" + shift.getShiftEnd());
                    limit++;
                    employeePool.clear();
                }
                //break;
            }
        }
        System.out.println("-----------------------------------------------------------");
    }
}
