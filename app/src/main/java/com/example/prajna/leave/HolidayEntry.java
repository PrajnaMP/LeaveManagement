package com.example.prajna.leave;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by prajna on 28/9/16.
 */
public class HolidayEntry extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private TextView mDate;
    private EditText mDay;
    private Button mSetButton, mSignOut;
    private Calendar cal;
    private int day;
    private int month;
    private int year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holiday_entry);
//
//        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
//        getSupportActionBar().setTitle("Enter Employee Details"); // set the top title
//        String title = actionBar.getTitle().toString(); // get the title
      /*  getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.back_button);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);*/
        init();
    }


    private void init() {

        mSignOut = (Button) findViewById(R.id.holiday_entry_sign_out_button);
        mSignOut.setOnClickListener(this);
        mSetButton = (Button) findViewById(R.id.set);
        mSetButton.setOnClickListener(this);
        mDate = (TextView) findViewById(R.id.date_view);
        mDate.setOnClickListener(this);
        mDay = (EditText) findViewById(R.id.day_view);

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.set:
                //If all the fields are filled by user then retrieve all user input data and store into Employee model object and send to DB insert method.
                LeaveClass leave = new LeaveClass();
                leave.setDate(mDate.getText().toString().trim());
                leave.setHoliday(mDay.getText().toString().trim());

                DBHandlerForHolidays.getDBHandlerInstance(HolidayEntry.this).insertAllHoliday(leave);
                Toast.makeText(this, "Successfully added ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HolidayEntry.this, AdminViewHoliday.class);
                startActivity(intent);
                break;

            case R.id.holiday_entry_sign_out_button:
                Intent i_ntent = new Intent(HolidayEntry.this, FirstActivity.class);
                startActivity(i_ntent);
                break;

            case R.id.date_view:
                DateDialog();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
//            mDobView.setText(data.getStringExtra("date"));
//        } else {
//            Toast.makeText(EmployeeEntryDetailsActivity.this, "Sorry, something went wrong.Please try again.", Toast.LENGTH_LONG).show();
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void DateDialog() {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear += 1;

                mDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);

            }
        };

        DatePickerDialog dpDialog = new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();

    }
}