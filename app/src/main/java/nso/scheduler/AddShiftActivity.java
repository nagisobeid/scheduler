package nso.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.io.LineReader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class AddShiftActivity extends AppCompatActivity {

    private Intent i;
    private String day;
    private TextView textView;
    private EditText timeStart;
    private EditText timeEnd;
    private EditText numEmployees;
    private CharSequence text = "";
    private int duration = Toast.LENGTH_SHORT;
    private Context context;
     //(shift, numEmployees)
    //private HashMap<String, Integer> shifts;
    private ArrayList<Shift> shifts;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        setContentView(R.layout.activity_add_shift);

        textView = findViewById(R.id.textViewDay);
        i = getIntent();
        day = (String)i.getSerializableExtra("day");
        textView.setText(day);

        timeStart = findViewById(R.id.editTextTimeStart);
        timeEnd = findViewById(R.id.editTextTimeEnd);
        numEmployees = findViewById(R.id.editTextNumberEmp);
        context = getApplicationContext();
        layout = (LinearLayout)findViewById(R.id.shiftLinearLayout);
        shifts = new ArrayList<>();
    }

    public void btnAddShift(View view) {
        if(timeStart.getText().toString().trim().length() == 0) {
            text = "Please enter start time";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if(timeEnd.getText().toString().trim().length() == 0) {
            text = "Please enter end time";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if(numEmployees.getText().toString().trim().length() == 0) {
            text = "Please enter number of employees";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if(!timeStart.getText().toString().contains(":")) {
            text = "time start must be in format 00:00";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if(!timeEnd.getText().toString().contains(":")) {
            text = "time end must be in format 00:00";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            //shifts.put(timeStart.getText().toString()+"-"+timeEnd.getText().toString(), Integer.parseInt(numEmployees.getText().toString()));
            Shift shift = new Shift(day,timeStart.getText().toString(),timeEnd.getText().toString(), Integer.parseInt(numEmployees.getText().toString()));
            shifts.add(shift);
            // layouts
            TextView shiftDetails = new TextView(this);
            Switch delete = new Switch(this);
            delete.setChecked(true);
            LinearLayout col1 = new LinearLayout(this);
            col1.setOrientation(LinearLayout.HORIZONTAL);

            shiftDetails.setText(shift.getShiftStart() +" - "+ shift.getShiftEnd() + " ~ " + shift.getNumEmployees());
            shiftDetails.setTextColor(getResources().getColor(R.color.limegreen));
            shiftDetails.setTextSize(20);

            TableLayout.LayoutParams params1 = new TableLayout.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
            shiftDetails.setLayoutParams(params1);

            col1.addView(shiftDetails);
            col1.addView(delete);

            layout.addView(col1);
        }

    }

    public void btnHome(View view) {
        startActivity(new Intent(AddShiftActivity.this, HomeActivity.class));
    }
}