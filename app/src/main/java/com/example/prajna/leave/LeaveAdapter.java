package com.example.prajna.leave;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by prajna on 21/9/16.
 */
public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.CustomViewHolder> {
    private ArrayList<LeaveClass> mLeaveList;
    private Context context;

    public LeaveAdapter(Context context, ArrayList<LeaveClass> leaveList) {
        this.mLeaveList = leaveList;
        this.context = context;
    }

    @Override
    public LeaveAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.leave, null);
        CustomViewHolder viewHolder = new CustomViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final LeaveAdapter.CustomViewHolder holder, int position) {

        final LeaveClass leave = mLeaveList.get(position);

        holder.name.setText(leave.getName());
        holder.sick_leave.setText(leave.getSickLeave());
        holder.casual_leave.setText(leave.getCasualLeave());
        holder.earn_leave.setText(leave.getEarnLeave());

        holder.update.setTag(leave);
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(final View view) {
                LeaveClass selectedEmployee = (LeaveClass) view.getTag();
                Intent intent = new Intent(context, UpdateEmployeeLeaveActivity.class);
                intent.putExtra("Selected_Employee_To_Update", selectedEmployee);
                context.startActivity(intent);
            }
        });

        holder.delete.setTag(leave);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure,You wanted to delete " + leave.getName() + " details..");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        DBHandler.getDBHandlerInstance(context).deleteEmployee((LeaveClass) view.getTag());
                        Toast.makeText(context, leave.getName() + " deleted successfully", Toast.LENGTH_LONG).show();
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLeaveList.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView sick_leave;
        private TextView casual_leave, earn_leave;
        private Button update, delete;

        public CustomViewHolder(final View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView.findViewById(R.id.name_text);
            sick_leave = (TextView) itemLayoutView.findViewById(R.id.sick_text);
            casual_leave = (TextView) itemLayoutView.findViewById(R.id.casual_text);
            earn_leave = (TextView) itemLayoutView.findViewById(R.id.earn_text);
            update = (Button) itemLayoutView.findViewById(R.id.update_button);
            delete = (Button) itemLayoutView.findViewById(R.id.delete_button);

        }
    }
}