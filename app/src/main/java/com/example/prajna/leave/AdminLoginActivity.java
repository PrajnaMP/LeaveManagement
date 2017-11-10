package com.example.prajna.leave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by prajna on 7/9/16.
 */
public class AdminLoginActivity extends Activity {
    private Button mLogin;
    private EditText mUserName, mPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        init();
    }

    private void init() {
        mUserName = (EditText) findViewById(R.id.admin_username_edit_text);
        mPassWord = (EditText) findViewById(R.id.admin_password_edit_text);
        mLogin = (Button) findViewById(R.id.admin_login_button);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPassWord.getText().toString().equals("admin") && mPassWord.getText().toString().equals("admin")) {
                    Intent intent = new Intent(AdminLoginActivity.this, UpdateLeaveActivity.class);
                    startActivity(intent);
                } else

                    Toast.makeText(AdminLoginActivity.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
            }
        });
    }
}
