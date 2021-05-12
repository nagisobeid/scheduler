package nso.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    public void btnLoginClicked(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void btnCreateAccountClicked(View view) {
        startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));
    }
}