package nso.scheduler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Database class responsible for handling all the database interaction
 */
public class DataBase {

    private FirebaseFirestore db;
    CharSequence text = "";
    int duration = Toast.LENGTH_SHORT;
    Context context;
    private static final String TAG = null;
    private int success;


    /**
     * Default constructor that initialize the database object
     */
    DataBase() {
        db = FirebaseFirestore.getInstance();
        success = 0;
    }

    /**
     * retuns and instance of the database
     * @return
     */
    public FirebaseFirestore getDb() {
        return db;
    }

    /**
     * was here for testing
     */
    public void resetSuccess() {
        success = 0;
    }
    /**
     * was here for testing
     */
    public void setSuccess(int val) {
        success = val;
    }
    /**
     * was here for testing
     */
    private int getSuccess() {
        return success;
    }

    /**
     * function is responsible for adding a business to the database
     * @param bname
     * @param password
     */
    public void addBusiness(String bname, String password) {
        Map<String, Object> business = new HashMap<>();
        business.put("username", bname);
        business.put("password", password);
        db.collection("business")
                .add(business)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    /**
     * responsible for adding an employee to the database
     * @param eName
     * @param bName
     */
    public void addEmployee(String eName, String bName) {
        Map<String, Object> employee = new HashMap<>();
        employee.put("username", eName);
        employee.put("bid", bName);
        db.collection("employee")
                .add(employee)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    /**
     * responsible for getting all employees from the database for a specefic business
     */
    public void getEmployees() {
        String TAG = null;
        System.out.println("INSIDE GET EMPLOYEES" + Data.getInstance().getCurrentBusiness());
        db.collection("employee").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if(document.get("bid").equals(Data.getInstance().getCurrentBusiness())) {
                            Employee e = new Employee();
                            e.setName(document.get("username").toString());
                            Data.addEmployee(e);
                        }
                    }
                    //Log.d(TAG, list.toString());
                } else {
                    System.out.println("ERRRROR FETCHING EMPLOYEES----------------------------------------+");
                    //Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }


}
