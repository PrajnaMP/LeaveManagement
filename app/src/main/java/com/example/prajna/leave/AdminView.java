package com.example.prajna.leave;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by prajna on 21/9/16.
 */
public class AdminView extends Activity {
    Intent intent;
    private ArrayList<LeaveClass> mLeaveList;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

        mLeaveList = DBHandler.getDBHandlerInstance(AdminView.this).getAllStoredEmployeeRecord();

        if (mLeaveList != null) {

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            //  set layoutManger
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            //  create an adapter
            LeaveAdapter mAdapter = new LeaveAdapter(this, mLeaveList);
            // set adapter
            recyclerView.setAdapter(mAdapter);
        }

    }

    private void refreshItems() {
        // Load items
        mLeaveList = DBHandler.getDBHandlerInstance(AdminView.this).getAllStoredEmployeeRecord();

        if (mLeaveList != null) {

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            LeaveAdapter mAdapter = new LeaveAdapter(this, mLeaveList);
            recyclerView.setAdapter(mAdapter);
        }

        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        mRefreshLayout.setRefreshing(false);
    }

}



