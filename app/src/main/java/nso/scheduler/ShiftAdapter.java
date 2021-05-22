package nso.scheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.ViewHolder> {

    private ArrayList<Shift> shifts;
    private ArrayList<Employee> employees;

    public ShiftAdapter(ArrayList<Shift> shifts, ArrayList<Employee> employees) {
        this.shifts = shifts;
    }

    @Override
    public ShiftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View employeeView = inflater.inflate(R.layout.day_shift_btn_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(employeeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShiftAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Shift shift = shifts.get(position);

        // Set item views based on your views and data model
        Button button = holder.dayShiftBtn;
        button.setTag(shift);
        button.setText(shift.getDay() + " : " + shift.getShiftStart()+"-"+shift.getShiftEnd());
    }

    @Override
    public int getItemCount() {
        return shifts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Button dayShiftBtn;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            super(itemView);
            dayShiftBtn = (Button) itemView.findViewById(R.id.day_shift_btn);
        }
    }
}

