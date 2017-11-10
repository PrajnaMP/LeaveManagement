package com.example.prajna.leave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by prajna on 28/9/16.
 */
public class EmployeeViewHoliday extends Activity {
    private ArrayList<LeaveClass> mLeaveList;
    private Button mSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_view_holiday);

        mSignOut = (Button) findViewById(R.id.employee_view_holiday_sign_out_button);
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeViewHoliday.this, FirstActivity.class);
                startActivity(intent);
            }
        });
        mLeaveList = DBHandlerForHolidays.getDBHandlerInstance(EmployeeViewHoliday.this).getAllHolidays();
        if (mLeaveList != null) {
            EmployeeViewAdapter customList = new EmployeeViewAdapter(this, mLeaveList);

            ListView lvw = (ListView) findViewById(R.id.emp_leave_list);
            lvw.setAdapter(customList);

        }
    }
}
