package nso.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddShiftActivity extends AppCompatActivity {

    private Intent i;
    private String day;
    private TextView textView;

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

    }
}