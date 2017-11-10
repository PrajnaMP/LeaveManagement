package com.example.prajna.leave;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by prajna on 28/9/16.
 */
public class AdminViewHoliday extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Intent intent;
    private Button mAdd, mSignOut;
    private ArrayList<LeaveClass> mLeaveList;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view_holiday);
        mAdd = (Button) findViewById(R.id.add_holiday_button);
        mAdd.setOnClickListener(this);
        mSignOut = (Button) findViewById(R.id.admin_view_holiday_sign_out_button);
        mSignOut.setOnClickListener(this);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

        mLeaveList = DBHandlerForHolidays.getDBHandlerInstance(AdminViewHoliday.this).getAllHolidays();

        if (mLeaveList != null) {
            HolidayAdapter customList = new HolidayAdapter(this, mLeaveList);

            ListView lvw = (ListView) findViewById(R.id.admin_leave_list);
            lvw.setAdapter(customList);

        }
    }

    private void refreshItems() {
        // Load items
        mLeaveList = DBHandlerForHolidays.getDBHandlerInstance(AdminViewHoliday.this).getAllHolidays();

        if (mLeaveList != null) {

            ListView lvw = (ListView) findViewById(R.id.admin_leave_list);
            HolidayAdapter customList = new HolidayAdapter(this, mLeaveList);
            lvw.setAdapter(customList);
        }

        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        mRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_holiday_button:
                intent = new Intent(AdminViewHoliday.this, HolidayEntry.class);
                startActivity(intent);
                break;

            case R.id.admin_view_holiday_sign_out_button:
                Intent intent = new Intent(AdminViewHoliday.this, FirstActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}