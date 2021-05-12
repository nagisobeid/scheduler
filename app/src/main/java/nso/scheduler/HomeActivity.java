package nso.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class HomeActivity extends AppCompatActivity {

    private Intent i;
    private String business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        i = getIntent();
        business = (String)i.getSerializableExtra("business");
    }

    public void onBtnClickedAddEmployee(View view) {
        //startActivity(new Intent(HomeActivity.this, AddEmployeeActivity.class));
        i = new Intent(HomeActivity.this, AddEmployeeActivity.class);
        i.putExtra("business", business);
        startActivity(i);
    }

    public void onBtnClickedAddShifts(View view) {
        i = new Intent(HomeActivity.this, DayActivity.class);
        i.putExtra("business", business);
        startActivity(i);
    }
}