package nso.scheduler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * class was here for testing, found an easier approach
 */
public class Day {
    String day;
    //(shift, numEmployees)
    //HashMap<String, ArrayList<String>> shifts;

    public Day(String day) {
        this.day = day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
