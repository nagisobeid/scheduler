package nso.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddConditionActivity extends AppCompatActivity {

    private TextView textView;
    private EditText timeStart;
    private EditText timeEnd;
    private String[] tf;
    private CharSequence text = "";
    private int duration = Toast.LENGTH_SHORT;
    private Context context;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_add_employee);
        setContentView(R.layout.activity_add_condition);

        //
        textView = findViewById(R.id.textViewDay);
        textView.setText(Data.getInstance().getCurrentDay());

        timeStart = findViewById(R.id.editTextTimeStartCondition);
        timeEnd = findViewById(R.id.editTextTimeEndCondition);
        context = getApplicationContext();
        layout = (LinearLayout)findViewById(R.id.conditionLinearLayout);
        //
        try {
            ArrayList<String[]> s = Data.getInstance().getCurrentEmployee().getDayConditions(Data.getInstance().getCurrentDay());
            populate(s);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ERORRRRRRR");
        }
    }

    public void btnAddCondition(View view) {
        if(timeStart.getText().toString().trim().length() == 0) {
            text = "Please enter start time";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if(timeEnd.getText().toString().trim().length() == 0) {
            text = "Please enter end time";
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
            tf = new String[2];
            tf[0] = timeStart.getText().toString();
            tf[1] = timeEnd.getText().toString();
            Data.getInstance().getCurrentEmployee().addCondition(Data.getInstance().getCurrentDay(), tf);
            ArrayList<String[]> s = Data.getInstance().getCurrentEmployee().getDayConditions(Data.getInstance().getCurrentDay());
            populate(s);
            //System.out.println(Data.getInstance().getCurrentEmployee().getConditions());
            //Data.getInstance().getCurrentEmployee().printConditions();
        }
    }

    public void populate(ArrayList<String[]> dayConditions) {
        layout.removeAllViews();
        for(String[] x: dayConditions) {
            TextView conditionDetails = new TextView(this);

            LinearLayout col1 = new LinearLayout(this);
            col1.setOrientation(LinearLayout.HORIZONTAL);

            conditionDetails.setText(x[0] + " - " + x[1]);

            conditionDetails.setTextColor(getResources().getColor(R.color.limegreen));;
            conditionDetails.setTextSize(20);
            conditionDetails.setClickable(true);

            TableLayout.LayoutParams params1 = new TableLayout.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
            conditionDetails.setLayoutParams(params1);

            col1.addView(conditionDetails);
            layout.addView(col1);
        }
    }

    public void btnDone(View view) {
        Intent i;
        i = new Intent(AddConditionActivity.this, DayActivity.class);
        startActivity(i);
    }
}