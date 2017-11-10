package com.example.prajna.leave;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by prajna on 29/8/16.
 */


public class ApplyLeaveActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Button mApplyButton, mCancel, mSignOut;
    private EditText mContact;
    private EditText mReason, mDays;
    private TextView mFromDate, mToDate;
    private Spinner mLeaveType, mApplyTo;
    private String mUserName;
    private Calendar cal;
    private int day;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_apply);
        // or getActionBar();
        ActionBar actionBar = getSupportActionBar();
        // set the top title
        getSupportActionBar().setTitle("Leave Application");
        // get the title
        String title = actionBar.getTitle().toString();
        init();
    }


    private void init() {
        Intent intent = getIntent();
        if (intent != null) {
            mUserName = getIntent().getStringExtra("name");
            String text = "+ 91 ";
            mLeaveType = (Spinner) findViewById(R.id.leave_type_spinner);
            mLeaveType.setOnItemSelectedListener(this);
            setLeaveTypeSpinners();

            mFromDate = (TextView) findViewById(R.id.from_date_edit_text);
            mFromDate.setText("Set From date");
            mFromDate.setOnClickListener(ApplyLeaveActivity.this);
            mToDate = (TextView) findViewById(R.id.to_date_edit_text);
            mToDate.setText("Set To date");
            mToDate.setOnClickListener(ApplyLeaveActivity.this);
            mDays = (EditText) findViewById(R.id.days_edit_text);
            mApplyTo = (Spinner) findViewById(R.id.apply_to_spinner);
            mApplyTo.setOnItemSelectedListener(ApplyLeaveActivity.this);
            setApplyToSpinners();

            mReason = (EditText) findViewById(R.id.reason_edit_text);
            mContact = (EditText) findViewById(R.id.contact_details_edit_text);
            mContact.setText(text);
            mApplyButton = (Button) findViewById(R.id.activity_leave_apply_button);
            mApplyButton.setOnClickListener(ApplyLeaveActivity.this);
            mCancel = (Button) findViewById(R.id.cancel_button);
            mCancel.setOnClickListener(ApplyLeaveActivity.this);
            mSignOut = (Button) findViewById(R.id.leave_apply_sign_out_button);
            mSignOut.setOnClickListener(this);
            cal = Calendar.getInstance();
            day = cal.get(Calendar.DAY_OF_MONTH);
            month = cal.get(Calendar.MONTH);
            year = cal.get(Calendar.YEAR);

        }

    }

    private boolean validateData(LeaveClass leave) {
        //Implement logic to check all the fields having value otherwise show specific toast message and return false

        if (leave.getLeaveType().toString() == null || leave.getLeaveType().toString().isEmpty()) {
            Toast.makeText(this, "Select Leave Type", Toast.LENGTH_LONG).show();
            return false;
        } else if (!validateFromDate(leave.getFromDate())) {
            Toast.makeText(this, "Invalid from Date", Toast.LENGTH_LONG).show();
            return false;
        } else if (!validateToDate(leave.getToDate())) {
            Toast.makeText(this, "Invalid to Date", Toast.LENGTH_LONG).show();
            return false;
        } else if (leave.getDays().toString() == null || leave.getDays().toString().isEmpty()) {
            Toast.makeText(this, "No. of Days is required", Toast.LENGTH_LONG).show();
            return false;
        } else if (leave.getApplyTo().toString() == null || leave.getApplyTo().toString().isEmpty()) {
            Toast.makeText(this, "Apply To field is empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (leave.getReason().toString() == null || leave.getReason().toString().isEmpty()) {
            Toast.makeText(this, "Reason is empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (!validateMobileNumber(leave.getMobile().toString())) {
            Toast.makeText(this, "Invalid Mobile number", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public static boolean validateMobileNumber(String mobile) {
        String regex = "[^\\d+]";
        String PhoneDigits = mobile.replaceAll(regex, "");
        return (PhoneDigits.length() == 13);
    }

    public static boolean validateFromDate(String from) {
        if (from == "Set From date")
            return false;
        else {
            String regex = "[^\\d/]";
            String PhoneDigits = from.replaceAll(regex, "");
            return (PhoneDigits.length() <= 10);
        }
    }

    public static boolean validateToDate(String from) {
        if (from == "Set To date")
            return false;
        else {
            String regex = "[^\\d/]";
            String PhoneDigits = from.replaceAll(regex, "");
            return (PhoneDigits.length() <= 10);
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

    private void setLeaveTypeSpinners() {
        List<String> LeaveType = new ArrayList<String>();
        LeaveType.add("Sick Leave");
        LeaveType.add("Casual Leave");
        LeaveType.add("Earn Leave");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, LeaveType);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mLeaveType.setAdapter(dataAdapter);
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
            case R.id.activity_leave_apply_button:
                //If all the fields are filled by user then retrieve all user input data and store into Employee model object and send to DB insert method.
                LeaveClass leave = new LeaveClass();
                leave.setLeaveType(mLeaveType.getSelectedItem().toString().trim());
                leave.setFromDate(mFromDate.getText().toString().trim());
                leave.setToDate(mToDate.getText().toString().trim());
                leave.setDays(mDays.getText().toString().trim());
                leave.setApplyTo(mApplyTo.getSelectedItem().toString().trim());
                leave.setReason(mReason.getText().toString().trim());
                leave.setMobile(mContact.getText().toString().trim());

                if (validateData(leave)) {
                    //Send Employee data to DB class
                    DBHandler.getDBHandlerInstance(ApplyLeaveActivity.this).updateApplyLeaves(leave, mUserName);
                    Toast.makeText(this, "Successfully applied ", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.cancel_button:
                Intent intent = new Intent(ApplyLeaveActivity.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.from_date_edit_text:
                FromDateDialog();

                break;

            case R.id.to_date_edit_text:

                ToDateDialog();

                break;

            case R.id.leave_apply_sign_out_button:
                Intent m_intent = new Intent(ApplyLeaveActivity.this, FirstActivity.class);
                startActivity(m_intent);

                break;

        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
//            mFromDate.setText(data.getStringExtra("date"));
//        } else {
//            Toast.makeText(ApplyLeaveActivity.this, "Sorry, something went wrong.Please try again.", Toast.LENGTH_LONG).show();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    public void FromDateDialog() {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear += 1;

                mFromDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);

            }
        };

        DatePickerDialog dpDialog = new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();

    }

    public void ToDateDialog() {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear += 1;

                mToDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);

            }
        };

        DatePickerDialog dpDialog = new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();

    }

}

