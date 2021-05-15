package nso.scheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SelectEmployeeActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_employee);

        RecyclerView rvEmployees = (RecyclerView) findViewById(R.id.rvEmployees);
        //ArrayList<Employee> emps = new ArrayList<>();
        db = new DataBase().getDb();

        // Create adapter passing in the sample user data
        EmployeeAdapter adapter = new EmployeeAdapter(Data.getInstance().getEmployees());
        // Attach the adapter to the recyclerview to populate items
        rvEmployees.setAdapter(adapter);
        // Set layout manager to position the items
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }

    public void btnClickedEmployee(View view) {
        Employee e = (Employee) view.getTag();
        Data.getInstance().setCurrentEmployee(e);
        System.out.println("CLICKED EMPLOYEE" + e.getEmployeeName());
        Intent i = new Intent(SelectEmployeeActivity.this, AddConditionActivity.class);
        startActivity(i);
    }

}