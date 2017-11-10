package com.example.prajna.leave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by prajna on 14/9/16.
 */
public class LeaveSummaryActivity extends Activity {
    private TextView mLeaveType, mFromDate, mToDate, mDays, mReason;
    private String LeaveType, FromDate, ToDate, Days, Reason;
    private Button mSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_summary);
        init();
    }

    private void init() {
        mSignOut = (Button) findViewById(R.id.leave_summary_sign_out_button);
        mLeaveType = (TextView) findViewById(R.id.leave_type_text_);
        mFromDate = (TextView) findViewById(R.id.from_date_text_);
        mToDate = (TextView) findViewById(R.id.to_date_text_);
        mDays = (TextView) findViewById(R.id.days_text_);
        mReason = (TextView) findViewById(R.id.reason_text_);

        Intent intent = getIntent();
        if (intent != null) {
            LeaveType = getIntent().getStringExtra("leave_type");
            FromDate = getIntent().getStringExtra("from_date");
            ToDate = getIntent().getStringExtra("to_date");
            Days = getIntent().getStringExtra("days");
            Reason = getIntent().getStringExtra("reason");

            mLeaveType.setText(LeaveType);
            mFromDate.setText(FromDate);
            mToDate.setText(ToDate);
            mDays.setText(Days);
            mReason.setText(Reason);
        }
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeaveSummaryActivity.this, FirstActivity.class);
                startActivity(intent);

            }
        });
    }

}
