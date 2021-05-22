package nso.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * @author Nagi Obeid
 * @version 1.0
 */

/**
 * main entry point of the application allows the user to make a choice between login or create account
 */
public class MainActivity extends AppCompatActivity {

    /**
     * creates the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    /**
     * navigates the usr to the login screen
     * @param view
     */
    public void btnLoginClicked(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    /**
     * navigates the user to the create account screen
     * @param view
     */
    public void btnCreateAccountClicked(View view) {
        startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));
    }
}