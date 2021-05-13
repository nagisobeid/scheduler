package nso.scheduler;

public class Shift{

    private String day;
    private String shiftStart;
    private String shiftEnd;
    private int numEmployees;

    public Shift(String day, String shiftStart, String shiftEnd, int numEmployees) {
        this.day = day;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
        this.numEmployees = numEmployees;
    };

    public String getDay() {
        return day;
    }

    public String getShiftStart() {
        return shiftStart;
    }

    public String getShiftEnd() {
        return shiftEnd;
    }

    public int getNumEmployees() {
        return numEmployees;
    }
}
