package nso.scheduler;

import java.util.ArrayList;

/**
 * this class was used for testing purposes
 */
public class Test {
    public Test() {

        Shift shift1 = new Shift("monday", "9:00", "16:00", 2, 4747);
        Shift shift2 = new Shift("monday", "16:00", "18:00", 3, 477);
        Shift shift3 = new Shift("tuesday", "9:00", "12:00", 1, 47547);
        Shift shift4 = new Shift("tuesday", "12:00", "16:00", 2, 47647);
        Shift shift5 = new Shift("wednesday", "8:00", "14:00", 1, 47747);
        Shift shift6 = new Shift("wednesday", "14:00", "16:00", 4, 47847);
        Data.getInstance().addShift(shift1);
        Data.getInstance().addShift(shift2);
        Data.getInstance().addShift(shift3);
        Data.getInstance().addShift(shift4);
        Data.getInstance().addShift(shift5);
        Data.getInstance().addShift(shift6);

        //String day, String timeFrame[]) {
        //        conditions.put(day, timeFrame);
        Employee nagi = new Employee("nagi");
        Employee jose = new Employee("jose");
        Employee nathan = new Employee("nathan");
        Employee james = new Employee("james");
        Employee layla = new Employee("layla");
        Employee isaac = new Employee("isaac");
        Employee robert = new Employee("robert");


        String[] s1 = new String[2];
        s1[0] = "9:00"; s1[1] = "13:00";
        nagi.addCondition("monday", s1);
        String[] s2 = new String[2];
        s2[0] = "12:00"; s2[1] = "15:00";
        nagi.addCondition("tuesday", s2);
        Data.getInstance().addEmployee(nagi);

        String[] s3 = new String[2];
        s3[0] = "9:00"; s3[1] = "13:00";
        jose.addCondition("monday", s3);
        String[] s4 = new String[2];
        s4[0] = "7:00"; s4[1] = "7:50";
        jose.addCondition("wednesday", s4);
        Data.getInstance().addEmployee(jose);

        String[] s = new String[2];
        s[0] = "15:00"; s[1] = "24:00";
        nathan.addCondition("wednesday", s);
        Data.getInstance().addEmployee(nathan);

        String[] s5 = new String[2];
        s5[0] = "8:00"; s5[1] = "13:00";
        james.addCondition("monday", s5);
        Data.getInstance().addEmployee(james);

        String[] s6 = new String[2];
        s6[0] = "9:00"; s6[1] = "13:00";
        layla.addCondition("monday", s6);
        Data.getInstance().addEmployee(layla);

        Data.getInstance().addEmployee(isaac);
        Data.getInstance().addEmployee(robert);

    }
}
