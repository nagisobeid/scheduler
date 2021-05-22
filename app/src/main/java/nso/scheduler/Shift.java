package nso.scheduler;

/**
 * shift class responsible for holding shift data
 */
public class Shift{

    private String day;
    private String shiftStart;
    private String shiftEnd;
    private int numEmployees;
    private int id;

    /**
     * constructr for the class
     * @param day
     * @param shiftStart
     * @param shiftEnd
     * @param numEmployees
     * @param id
     */
    public Shift(String day, String shiftStart, String shiftEnd, int numEmployees, int id) {
        this.day = day;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
        this.numEmployees = numEmployees;
        this.id = id;
    };

    /**
     * gets shift day
     * @return
     */
    public String getDay() {
        return day;
    }

    /**
     * get shift starte
     * @return
     */
    public String getShiftStart() {
        return shiftStart;
    }

    /**
     * get shift end
     * @return
     */
    public String getShiftEnd() {
        return shiftEnd;
    }

    /**
     * get number of employees
     * @return
     */
    public int getNumEmployees() {
        return numEmployees;
    }

    public int getId() {
        return id;
    }
}
