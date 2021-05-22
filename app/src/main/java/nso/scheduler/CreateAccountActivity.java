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
 * CreateAccountActivity responsible for creating a user account
 */
public class CreateAccountActivity extends AppCompatActivity {

    private DataBase db;

    /**
     * OVerridden function responsible for generating the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_account);

        //initializing the  database
        db = new DataBase();
    }

    /**
     * responsible for performing input validation and calling the database addBusiness function
     * @param view
     */
    public void onButtonCreateAccountSubmit(View view) {
        Context context = getApplicationContext();
        CharSequence text = "";
        int duration = Toast.LENGTH_SHORT;

        EditText username = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextPassword);
        EditText passwordVerify = findViewById(R.id.editTextPasswordVerify);
        if(username.getText().toString().trim().length() == 0) {
            //System.out.println("Username Required");
            text = "Username Required";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if (password.getText().toString().trim().length() == 0) {
            //System.out.println("Username Required");
            text = "Password Required";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else if (passwordVerify.getText().toString().trim().length() == 0) {
            //System.out.println("Username Required");
            text = "Verify Password";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if (!password.getText().toString().equals(passwordVerify.getText().toString())) {
            text = "Passwords Do Not Match";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            //If all the data is there, insert into database.
            db.addBusiness(username.getText().toString(), password.getText().toString());
            Data.getInstance().setCurrentBusiness(username.getText().toString());
            startActivity(new Intent(CreateAccountActivity.this, HomeActivity.class));
        }
    }


}
