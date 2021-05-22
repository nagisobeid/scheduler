package nso.scheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Activity responsible for displaying the scheudle view
 */
public class ScheduleActivity extends AppCompatActivity {
    private Context context;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_schedule);


        context = getApplicationContext();;
        layout = (LinearLayout)findViewById(R.id.ScheduleLinearLayout);
        //layout.removeAllViews();
        ArrayList<Shift> shifts = Data.getInstance().getShifts();
        ArrayList<Employee> employees = Data.getInstance().getEmployees();
        for(Shift shift: shifts) {
            TextView shiftDetails = new TextView(this);
            LinearLayout col1 = new LinearLayout(this);

            shiftDetails.setText(shift.getDay());
            shiftDetails.setTextColor(getResources().getColor(R.color.limegreen));;
            shiftDetails.setTextSize(20);
            TableLayout.LayoutParams params1 = new TableLayout.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
            shiftDetails.setLayoutParams(params1);
            col1.addView(shiftDetails);
            layout.addView(col1);
            for(Employee employee: employees) {
                //String eShift = employee.getScheduleForDay(shift.getDay());
                if(employee.getSchedule().containsKey(shift.getDay())) {
                    if(employee.getScheduleForDay(shift.getDay()).equals(shift.getShiftStart()+"-"+shift.getShiftEnd())) {
                        String eShift = employee.getScheduleForDay(shift.getDay());
                        shiftDetails = new TextView(this);
                        col1 = new LinearLayout(this);

                        shiftDetails.setText(employee.getEmployeeName() + ": " + eShift);
                        shiftDetails.setTextColor(getResources().getColor(R.color.red));;
                        shiftDetails.setTextSize(20);
                        params1 = new TableLayout.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
                        shiftDetails.setLayoutParams(params1);
                        col1.addView(shiftDetails);
                        layout.addView(col1);
                    }
                }
            }
        }
        //
    }
}