package com.example.prajna.leave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by prajna on 19/9/16.
 */
public class RegisterActivity extends Activity {
    EditText mUserName, mPassWord, mConfirmPassword;
    Button mCreateAccount;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        mUserName = (EditText) findViewById(R.id.register_username_edit_text);
        mPassWord = (EditText) findViewById(R.id.register_password_edit_text);
        mConfirmPassword = (EditText) findViewById(R.id.register_confirm_password_edit_text);
        mCreateAccount = (Button) findViewById(R.id.register_create_account_button);
        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mUserName.getText().toString();
                String password = mPassWord.getText().toString();
                String confirmPassword = mConfirmPassword.getText().toString();

                // check if any of the fields are vaccant
                if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if both password matches
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    // Save the Data in Database
                    loginDataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, EmployeeLoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}