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

public class HomeActivity extends AppCompatActivity {

    private Intent i;
    private String business;
    private static ArrayList<Shift> shifts;
    private static HashMap<Integer, Button> deletebtns;
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        db = new DataBase();
        db.getEmployees();


        i = getIntent();
        business = (String)i.getSerializableExtra("business");
        shifts = new ArrayList<>();
        deletebtns = new HashMap<>();
        System.out.println("DESTORYIED-------------------------------------DES");
    }

    public void onBtnClickedAddEmployee(View view) {
        //startActivity(new Intent(HomeActivity.this, AddEmployeeActivity.class));
        i = new Intent(HomeActivity.this, AddEmployeeActivity.class);
        i.putExtra("business", business);
        startActivity(i);
    }

    public void onBtnClickedAddShifts(View view) {
        //i = new Intent(HomeActivity.this, DayActivity.class);
        //startActivityForResult(i,1234);
        i = new Intent(HomeActivity.this, DayActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.putExtra("business", business);
        startActivity(i);
    }

    public void onBtnClickedAddConditions(View view) {
        i = new Intent(HomeActivity.this, SelectEmployeeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.putExtra("business", business);
        startActivity(i);
    }
}