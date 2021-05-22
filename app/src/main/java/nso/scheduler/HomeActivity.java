package nso.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

/**
 * HOmeActivity responsible for displaying the Home View after a business or user has logged in
 */
public class HomeActivity extends AppCompatActivity {

    private Intent i;
    private String business;
    private static ArrayList<Shift> shifts;
    private static HashMap<Integer, Button> deletebtns;
    private DataBase db;

    /**
     * responsible for creating the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        db = new DataBase();
        db.getEmployees();
        //System.out.println("NAGI--------------------------------------" + Data.getInstance().getCurrentBusiness());
        //TEST
        //Test t = new Test();
        //
        i = getIntent();
        business = (String)i.getSerializableExtra("business");
        shifts = new ArrayList<>();
        deletebtns = new HashMap<>();

        System.out.println("DESTORYIED-------------------------------------DES");
    }

    /**
     * Navigates the user to the add employee activity
     * @param view
     */
    public void onBtnClickedAddEmployee(View view) {
        //startActivity(new Intent(HomeActivity.this, AddEmployeeActivity.class));
        i = new Intent(HomeActivity.this, AddEmployeeActivity.class);
        i.putExtra("business", business);
        startActivity(i);
    }

    /**
     * Navigates the user to the add shift activity
     * @param view
     */
    public void onBtnClickedAddShifts(View view) {
        //i = new Intent(HomeActivity.this, DayActivity.class);
        //startActivityForResult(i,1234);
        i = new Intent(HomeActivity.this, DayActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.putExtra("business", business);
        startActivity(i);
    }

    /**
     * Navigates the user to the add condition activity
     * @param view
     */
    public void onBtnClickedAddConditions(View view) {
        Data.getInstance().setSettingConditions(true);
        i = new Intent(HomeActivity.this, SelectEmployeeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.putExtra("business", business);
        startActivity(i);
    }

    /**
     * Generates a schedule by calling the schedule object then
     * Navigates the user to the Schedule activity
     * @param view
     */
    public void onBtnClickedGenerateSchedule(View view) {
        Schedule s = new Schedule();
        s.generateSchedule();
        i = new Intent(HomeActivity.this, ScheduleActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }
}