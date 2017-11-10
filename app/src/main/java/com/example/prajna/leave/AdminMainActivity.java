package com.example.prajna.leave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prajna on 7/9/16.
 */
public class AdminMainActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Button mAdd, mSignOut;
    private EditText mName, mSickLeave, mCasualLeave, mEarnLeave;
    private Spinner mApplyTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        init();
    }

    private void init() {
        mSignOut = (Button) findViewById(R.id.admin_main_sign_out_button);
        mSignOut.setOnClickListener(this);
        mName = (EditText) findViewById(R.id.admin_main_name_edit_text);
        mSickLeave = (EditText) findViewById(R.id.admin_main_sick_leave_edit_text);
        mCasualLeave = (EditText) findViewById(R.id.admin_main_casual_leave_edit_text);
        mEarnLeave = (EditText) findViewById(R.id.admin_main_earn_leave_edit_text);
        mApplyTo = (Spinner) findViewById(R.id.admin_main_apply_to_spinner);
        mApplyTo.setOnItemSelectedListener(AdminMainActivity.this);
        setApplyToSpinners();
        mAdd = (Button) findViewById(R.id.admin_main_add_leave_button);
        mAdd.setOnClickListener(this);
    }

    private void setApplyToSpinners() {
        List<String> ApplyTo = new ArrayList<String>();
        ApplyTo.add("Manager");
        ApplyTo.add("Senior Software Engineer");
        ApplyTo.add("Lead");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ApplyTo);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mApplyTo.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.admin_main_add_leave_button:
                LeaveClass leave = new LeaveClass();
                leave.setName(mName.getText().toString().trim());
                leave.setSickLeave(mSickLeave.getText().toString().trim());
                leave.setCasualLeave(mCasualLeave.getText().toString().trim());
                leave.setEarnLeave(mEarnLeave.getText().toString().trim());
                leave.setApplyTo(mApplyTo.getSelectedItem().toString().trim());
                if (validateName(leave)) {

                    DBHandler.getDBHandlerInstance(AdminMainActivity.this).insertAllDataIntoLeaveList(leave);
                    Toast.makeText(this, "Successfully added ", Toast.LENGTH_LONG).show();

                }
                mName.setText("");
                mSickLeave.setText("");
                mCasualLeave.setText("");
                mEarnLeave.setText("");

                break;

            case R.id.admin_main_sign_out_button:
                Intent intent = new Intent(AdminMainActivity.this, FirstActivity.class);
                startActivity(intent);
                break;

        }

    }

    private boolean validateName(LeaveClass leave) {

        if (leave.getName().toString() == null || leave.getName().isEmpty()) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
