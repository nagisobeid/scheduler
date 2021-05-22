package nso.scheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.savedstate.SavedStateRegistryOwner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.TimeUnit;

/**
 * LoginActivity responsible fo displaying the login view
 */
public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    CharSequence text = "";
    int duration = Toast.LENGTH_SHORT;
    private static final String TAG = null;
    Context context;
    private DataBase db;

    /**
     * Creates the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        db = new DataBase();
        context = getApplicationContext();
        username = findViewById(R.id.editTextUsernameLogin);
        password = findViewById(R.id.editTextPasswordLogin);
    }

    /**
     * Logs the user in after performing some input validation
     * @param view
     */
    public void onButtonLoginSubmit(View view) {
        //Context c = LoginActivity.this;

        if(username.getText().toString().trim().length() == 0) {
            text = "Please enter username";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if(password.getText().toString().trim().length() == 0) {
            text = "Please enter password";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            db.getDb().collection("business")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            System.out.println("INSIDE ON COMPLETE*************************************");
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    //Log.d(TAG, document.getId() + " => " + document.getData());
                                    if (username.getText().toString().equals(document.getString("username"))) {
                                        //Log.d(TAG, document.getId() + " => " + document.getString("password"));
                                        if (password.getText().toString().equals(document.getString("password"))) {
                                            //startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                            Data.getInstance().setCurrentBusiness(username.getText().toString());
                                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                            i.putExtra("business", username.getText().toString());
                                            startActivity(i);
                                            break;
                                        } else if (!password.getText().toString().equals(document.getString("password"))) {
                                            text = "Password is incorrect";
                                            Toast toast = Toast.makeText(context, text, duration);
                                            toast.show();
                                        }

                                    }
                                    //Log.d(TAG, document.getId() + " => " + document.getString("password"));
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                                //System.out.println("*********************************** ERROR NAGI");
                            }
                        }
                    });
        }
    }
}