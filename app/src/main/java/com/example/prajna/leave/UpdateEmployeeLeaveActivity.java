package com.example.prajna.leave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prajna on 23/9/16.
 */
public class UpdateEmployeeLeaveActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private TextView mName;
    private EditText mSickLeave, mCasualLeave, mEarnLeave;
    private Button mUpdateButton, mSignOut;
    private Spinner mApplyTo;
    Intent intent;
    LeaveClass leave = new LeaveClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee_leave);

        Intent intent = getIntent();
        if (intent != null) {
            LeaveClass leave = (LeaveClass) intent.getSerializableExtra("Selected_Employee_To_Update");
            mSignOut = (Button) findViewById(R.id.update_employee_leave_sign_out_button);
            mSignOut.setOnClickListener(this);
            mName = (TextView) findViewById(R.id.update_employee_leave_name_text);
            mName.setText(leave.getName());
            mSickLeave = (EditText) findViewById(R.id.update_employee_leave_sick_leave_edit_text);
            mSickLeave.setText(leave.getSickLeave());
            mCasualLeave = (EditText) findViewById(R.id.update_employee_leave_casual_leave_edit_text);
            mCasualLeave.setText(leave.getCasualLeave());
            mEarnLeave = (EditText) findViewById(R.id.update_employee_leave_earn_leave_edit_text);
            mEarnLeave.setText(leave.getEarnLeave());

            mApplyTo = (Spinner) findViewById(R.id.update_employee_leave_apply_to_spinner);
            mApplyTo.setOnItemSelectedListener(this);
            setApplyToSpinners();
            mUpdateButton = (Button) findViewById(R.id.update_employee_leave_update_leave_button);
            mUpdateButton.setOnClickListener(this);
            Toast.makeText(UpdateEmployeeLeaveActivity.this, leave.getName() + "  is Clicked.", Toast.LENGTH_LONG).show();
        }
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
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        // On selecting a spingetMobilener itemadapterView
        String item = parent.getItemAtPosition(i).toString();
//        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.update_employee_leave_update_leave_button:
                //If all the fields are filled by user then retrieve all user input data and store into Employee model object and send to DB insert method.
                //Send Employee data to DB class
                leave.setName(mName.getText().toString().trim());
                leave.setSickLeave(mSickLeave.getText().toString().trim());
                leave.setCasualLeave(mCasualLeave.getText().toString().trim());
                leave.setEarnLeave(mEarnLeave.getText().toString().trim());
                DBHandler.getDBHandlerInstance(UpdateEmployeeLeaveActivity.this).updateEmployeeLeaves(leave);
                Toast.makeText(this, "Successfully updated ", Toast.LENGTH_LONG).show();
                Intent mIntent = new Intent(UpdateEmployeeLeaveActivity.this, AdminView.class);
                startActivity(mIntent);
                break;

            case R.id.update_employee_leave_sign_out_button:
                Intent intent = new Intent(UpdateEmployeeLeaveActivity.this, FirstActivity.class);
                startActivity(intent);
                break;

        }
    }
}
