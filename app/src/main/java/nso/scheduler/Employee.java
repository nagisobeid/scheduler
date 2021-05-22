package nso.scheduler;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Employee class responsbile for holding all employee data
 */
public class Employee {
    private String name;
    private Multimap<String, String[]> conditions;
    private HashMap<String, String> schedule;

    /**
     * default constructor initialized the conditions multimap and schedule Hashmap for the employee
     */
    public Employee() {
        conditions = ArrayListMultimap.create();
        schedule = new HashMap<String, String>();
    }

    /**
     * Employee constructor that accepts and employee name
     * @param name
     */
    public Employee(String name) {
        this.name = name;
        conditions = ArrayListMultimap.create();
        schedule = new HashMap<>();
    }

    /**
     * adds a condition for the employee and inserts it into the multimap
     * @param day
     * @param timeFrame
     */
    public void addCondition(String day, String timeFrame[]) {
        conditions.put(day, timeFrame);
    }

    /**
     * gets all the conditions for the employee
     * @return
     */
    public Multimap getConditions() {
        return conditions;
    }

    /**
     * returns all the conditions for a specefic day
     * @param day
     * @return
     */
    public ArrayList getDayConditions(String day) {
        ArrayList<String[]> dayConditions = new ArrayList<>();

        Collection x = conditions.get(day);
        Iterator<String[]> iterator = x.iterator();
        while (iterator.hasNext()) {
            String[] aName = iterator.next();
            dayConditions.add(aName);
        }

        return dayConditions;
    }

    /**
     * return semployee name
     * @return
     */
    public String getEmployeeName() {
        return name;
    }

    /**
     * sets employee name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * adds the employee to the schedule
     * @param day
     * @param shift
     */
    public void addToSchedule(String day, String shift) {
        schedule.put(day, shift);
    }

    /**
     * resets the employee schedule
     */
    public void resetSchedule() {
        schedule.clear();
    }

    /**
     * gets the current employee shcedule
     * @return
     */
    public HashMap getSchedule() {
        return schedule;
    }

    /**
     * gets the employee schedule for a specefic day
     * @param day
     * @return
     */
    public String getScheduleForDay(String day) {
        return schedule.get(day);
    }

}
