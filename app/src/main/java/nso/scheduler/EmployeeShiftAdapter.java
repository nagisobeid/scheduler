package nso.scheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmployeeShiftAdapter extends RecyclerView.Adapter<EmployeeShiftAdapter.ViewHolder> {

    private ArrayList<Employee> employees;
    private String day;

    public EmployeeShiftAdapter(ArrayList<Employee> employees, String day) {
        this.employees = employees;
        this.day = day;
    }

    @Override
    public EmployeeShiftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View employeeView = inflater.inflate(R.layout.employee_shift_btn_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(employeeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmployeeShiftAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Employee employee = employees.get(position);

        // Set item views based on your views and data model
        Button button = holder.employeeShiftBtn;
        button.setTag(employee);
        button.setText(employee.getScheduleForDay(day));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Button employeeShiftBtn;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            super(itemView);
            employeeShiftBtn = (Button) itemView.findViewById(R.id.employee_shift_button);
        }
    }
}