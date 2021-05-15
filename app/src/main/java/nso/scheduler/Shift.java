package nso.scheduler;

public class Shift{

    private String day;
    private String shiftStart;
    private String shiftEnd;
    private int numEmployees;
    private int id;

    public Shift(String day, String shiftStart, String shiftEnd, int numEmployees, int id) {
        this.day = day;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
        this.numEmployees = numEmployees;
        this.id = id;
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

    public int getId() {
        return id;
    }
}
