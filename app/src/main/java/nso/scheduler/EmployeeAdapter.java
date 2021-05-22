package nso.scheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeAdapter class responsible for display8ing the EMployees inside a RecyclerView
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private ArrayList<Employee> employees;

    /**
     * Initializes the employees arraylist to a list of employees
     * @param employees
     */
    public EmployeeAdapter(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Overridden function that sets the context, layoutInflator, view and viewHolder
     * @param parent
     * @param viewType
     * @return viewHolder
     */
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View employeeView = inflater.inflate(R.layout.employees_btn_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(employeeView);
        return viewHolder;
    }

    /**
     * overridden function that binds the view holder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(EmployeeAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Employee employee = employees.get(position);

        // Set item views based on your views and data model
        Button button = holder.empBtn;
        button.setTag(employee);
        button.setText(employee.getEmployeeName());
    }

    /**
     * overridden function that gets the count of items in the view
     * @return
     */
    @Override
    public int getItemCount() {
        return employees.size();
    }

    /**
     * ViewHolder class sets the button and calls the button xml
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public Button empBtn;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            super(itemView);
            empBtn = (Button) itemView.findViewById(R.id.employee_name_button);
        }
    }
}
