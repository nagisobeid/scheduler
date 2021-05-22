package nso.scheduler;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Employee {
    private String name;
    private Multimap<String, String[]> conditions;
    private HashMap<String, String> schedule;

    public Employee() {
        conditions = ArrayListMultimap.create();
        schedule = new HashMap<String, String>();
    }

    public Employee(String name) {
        this.name = name;
        conditions = ArrayListMultimap.create();
        schedule = new HashMap<>();
    }

    public void addCondition(String day, String timeFrame[]) {
        conditions.put(day, timeFrame);
    }

    public Multimap getConditions() {
        return conditions;
    }

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

    public String getEmployeeName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addToSchedule(String day, String shift) {
        schedule.put(day, shift);
    }

    public void resetSchedule() {
        schedule.clear();
    }
    public HashMap getSchedule() {
        return schedule;
    }
    public String getScheduleForDay(String day) {
        return schedule.get(day);
    }

}
