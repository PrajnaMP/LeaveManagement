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
public class FirstActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Button mAdmin, mEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        init();
    }

    private void init() {
        mAdmin = (Button) findViewById(R.id.admin_button);
        mAdmin.setOnClickListener(this);
        mEmployee = (Button) findViewById(R.id.employee_button);
        mEmployee.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.admin_button:
                Intent intent = new Intent(FirstActivity.this, AdminLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.employee_button:
                Intent i_ntent = new Intent(FirstActivity.this, EmployeeLoginActivity.class);
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

    @Override
    public void onBackPressed() {
    }
}
