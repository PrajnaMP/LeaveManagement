package com.example.prajna.leave;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by prajna on 28/9/16.
 */
public class HolidayAdapter extends BaseAdapter {

    private ArrayList<LeaveClass> mLeaveList;
    private Context context;
    LayoutInflater inflater;
    Button mDeleteButton;

    public HolidayAdapter(Context context, ArrayList leaveList) {
        this.mLeaveList = leaveList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mLeaveList.size();
    }

    @Override
    public LeaveClass getItem(int position) {
        return mLeaveList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_holiday_admin, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final LeaveClass leave = getItem(position);
        mViewHolder.date.setText(leave.getDate());
        mViewHolder.day.setText(leave.getHoliday());

        mDeleteButton = (Button) convertView.findViewById(R.id.delete_holiday);
        mDeleteButton.setTag(leave);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage(leave.getHoliday() + " will be deleted");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DBHandlerForHolidays.getDBHandlerInstance(context).deleteHoliday((LeaveClass) view.getTag());
                        Toast.makeText(context, leave.getHoliday() + " deleted successfully", Toast.LENGTH_LONG).show();
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


        return convertView;
    }

    private static class MyViewHolder {
        private TextView date;
        private TextView day;
        private Button delete;

        public MyViewHolder(View item) {
            date = (TextView) item.findViewById(R.id.date_text_view);
            day = (TextView) item.findViewById(R.id.day_text_view);
            delete = (Button) item.findViewById(R.id.delete_holiday);
        }
    }
}