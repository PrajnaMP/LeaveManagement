package com.example.prajna.leave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by prajna on 29/8/16.
 */
public class EmployeeLoginActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Button mNewAccount;
    private Button mLogin;
    private EditText mUserName, mPassWord;
    LoginDataBaseAdapter loginDataBaseAdapter;
    LeaveClass leave = new LeaveClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        init();
    }

    private void init() {
        mNewAccount = (Button) findViewById(R.id.new_account_button);
        mNewAccount.setOnClickListener(this);
        mUserName = (EditText) findViewById(R.id.employee_username_edit_text);
        mPassWord = (EditText) findViewById(R.id.employee_password_edit_text);
        mLogin = (Button) findViewById(R.id.employee_login_button);
        mLogin.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
// Close The Database
        loginDataBaseAdapter.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_account_button:
                Intent intentReg = new Intent(EmployeeLoginActivity.this, RegisterActivity.class);
                startActivity(intentReg);
                break;

            case R.id.employee_login_button:
                String userName = mUserName.getText().toString();
                String password = mPassWord.getText().toString();

// fetch the Password form database for respective user name
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

// check if the Stored password matches with Password entered by user
                if (password.equals(storedPassword)) {
                    LeaveClass all_leave = DBHandler.getDBHandlerInstance(EmployeeLoginActivity.this).getLeaves(userName);

                    String name = all_leave.getName();
                    String sick = all_leave.getSickLeave();
                    String casual = all_leave.getCasualLeave();
                    String earn = all_leave.getEarnLeave();

                    Intent intent = new Intent(EmployeeLoginActivity.this, MainActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("sick_leave", sick);
                    intent.putExtra("casual_leave", casual);
                    intent.putExtra("earn_leave", earn);
                    startActivity(intent);

                    Toast.makeText(EmployeeLoginActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EmployeeLoginActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }

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

