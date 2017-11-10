package com.example.prajna.leave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Button mApplyButton, mViewButton, mHolidayButton;
    private TextView mSickLeave, mCasualLeave, mEarnLeave, mName;
    private String key_name, key_sick_leave, key_casual_leave, key_earn_leave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Intent intent = getIntent();
        if (intent != null) {

            key_name = getIntent().getStringExtra("name");
            key_sick_leave = getIntent().getStringExtra("sick_leave");
            key_casual_leave = getIntent().getStringExtra("casual_leave");
            key_earn_leave = getIntent().getStringExtra("earn_leave");
            mSickLeave.setText(key_sick_leave);
            mCasualLeave.setText(key_casual_leave);
            mEarnLeave.setText(key_earn_leave);
            mName.setText(key_name);
        }
    }

    private void init() {
        mName = (TextView) findViewById(R.id.main_name_text_view);
        mSickLeave = (TextView) findViewById(R.id.sick_leave_text);
        mCasualLeave = (TextView) findViewById(R.id.casual_leave_text);
        mEarnLeave = (TextView) findViewById(R.id.earn_leave_text);
        mViewButton = (Button) findViewById(R.id.view_last_leave_button);
        mViewButton.setOnClickListener(MainActivity.this);
        mHolidayButton = (Button) findViewById(R.id.activity_main_holidays_button);
        mHolidayButton.setOnClickListener(MainActivity.this);
        mApplyButton = (Button) findViewById(R.id.activity_main_apply_button);
        mApplyButton.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.activity_main_apply_button:
                Intent intent = new Intent(MainActivity.this, ApplyLeaveActivity.class);
                intent.putExtra("name", key_name);
                startActivity(intent);
                break;

            case R.id.view_last_leave_button:
                LeaveClass last_leave = DBHandler.getDBHandlerInstance(MainActivity.this).getLastLeave(key_name);
                String leave_type = last_leave.getLeaveType();
                String from_date = last_leave.getFromDate();
                String to_date = last_leave.getToDate();
                String days = last_leave.getDays();
                String reason = last_leave.getReason();

                Intent v_intent = new Intent(MainActivity.this, LeaveSummaryActivity.class);
                v_intent.putExtra("leave_type", leave_type);
                v_intent.putExtra("from_date", from_date);
                v_intent.putExtra("to_date", to_date);
                v_intent.putExtra("days", days);
                v_intent.putExtra("reason", reason);
                startActivity(v_intent);
                break;

            case R.id.activity_main_holidays_button:
                Intent intent_ = new Intent(MainActivity.this, EmployeeViewHoliday.class);
                startActivity(intent_);
                break;
        }

    }
}




