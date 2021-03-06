package nso.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * AddEmployeeActivity class responsible for adding an employee
 */
public class AddEmployeeActivity extends AppCompatActivity {

    private EditText employee;
    private CharSequence text = "";
    int duration = Toast.LENGTH_SHORT;
    private Context context;
    private DataBase db;
    private String business;

    /**
     * overridden function that creates the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_employee);

        employee = findViewById(R.id.editTextTextEmployeeName);
        context = getApplicationContext();
        db = new DataBase();
        Intent i = getIntent();
        business = (String)i.getSerializableExtra("business");
    }

    /**
     * this button adds the employee to the database after performing some filtering
     * @param view
     */
    public void btnClickAddEmployeeSubmit(View view) {
        if (employee.getText().toString().trim().length() == 0) {
            text = "Please enter employee name";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            db.addEmployee(employee.getText().toString(), Data.getInstance().getCurrentBusiness());
            text = "Employee Added Successfully";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            employee.setText("");
            Data.getInstance().getEmployees().clear();
            db.getEmployees();
        }
    }
}