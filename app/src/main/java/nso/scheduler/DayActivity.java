package nso.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DayActivity extends AppCompatActivity {

    private CharSequence text = "";
    int duration = Toast.LENGTH_SHORT;
    private Context context;
    private String button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_day);
    }

    public void btnClickedDay(View view) {
        switch (view.getId()) {
            case R.id.btnMonday:
                button = "monday";
                break;
            case R.id.btnTuesday:
                button = "tuesday";
                break;
            case R.id.btnWednesday:
                button = "wednesday";
                break;
            case R.id.btnThursday:
                button = "thursday";
                break;
            case R.id.btnFriday:
                button = "friday";
                break;
            case R.id.btnSaturday:
                button = "saturday";
                break;
            case R.id.btnSunday:
                button = "sunday";
                break;
        }

        System.out.println("-------------------------------------" + button);
        Intent i = new Intent(DayActivity.this, AddShiftActivity.class);
        i.putExtra("day", button);
        startActivity(i);
    }
}