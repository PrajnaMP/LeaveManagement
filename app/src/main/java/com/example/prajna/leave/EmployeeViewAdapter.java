package com.example.prajna.leave;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by prajna on 28/9/16.
 */
public class EmployeeViewAdapter extends BaseAdapter {

    private ArrayList<LeaveClass> mLeaveList;
    private Context context;
    LayoutInflater inflater;

    public EmployeeViewAdapter(Context context, ArrayList leaveList) {
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
            convertView = inflater.inflate(R.layout.item_holiday_emp, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final LeaveClass leave = getItem(position);

        mViewHolder.date.setText(leave.getDate());
        mViewHolder.day.setText(leave.getHoliday());
        return convertView;
    }

    private static class MyViewHolder {
        private TextView date;
        private TextView day;

        public MyViewHolder(View item) {
            date = (TextView) item.findViewById(R.id.date_text_view);
            day = (TextView) item.findViewById(R.id.day_text_view);
        }
    }
}