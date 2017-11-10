package com.example.prajna.leave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

/**
 * Created by prajna on 7/9/16.
 */
public class UpdateLeaveActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Button mAddLeave, mUpdateHoliday, mViewLeave, mSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_leave);
        init();
    }

    private void init() {
        mViewLeave = (Button) findViewById(R.id.view_employee_leave_button);
        mViewLeave.setOnClickListener(this);
        mUpdateHoliday = (Button) findViewById(R.id.update_holidays_button);
        mUpdateHoliday.setOnClickListener(this);
        mAddLeave = (Button) findViewById(R.id.add_employee_leave_button);
        mAddLeave.setOnClickListener(this);
        mSignOut = (Button) findViewById(R.id.update_sign_out_button);
        mSignOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_employee_leave_button:
                Intent intent = new Intent(UpdateLeaveActivity.this, AdminMainActivity.class);
                startActivity(intent);
                break;

            case R.id.update_holidays_button:
                Intent intent_u = new Intent(UpdateLeaveActivity.this, AdminViewHoliday.class);
                startActivity(intent_u);
                break;

            case R.id.view_employee_leave_button:
                Intent mintent = new Intent(UpdateLeaveActivity.this, AdminView.class);
                startActivity(mintent);
                break;

            case R.id.update_sign_out_button:
                Intent i_ntent = new Intent(UpdateLeaveActivity.this, FirstActivity.class);
                startActivity(i_ntent);
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
