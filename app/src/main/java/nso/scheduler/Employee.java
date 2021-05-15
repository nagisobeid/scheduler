package nso.scheduler;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.HashMap;

public class Employee {
    private String name;
    private Multimap<String, String> conditions;

    public Employee() {

    }

    public Employee(String name) {
        this.name = name;
        conditions = ArrayListMultimap.create();
    }

    public void addCondition(String day, String timeFrame) {
        conditions.put(day, timeFrame);
    }

    public Multimap getConditions() {
        return conditions;
    }

    public String getEmployeeName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
