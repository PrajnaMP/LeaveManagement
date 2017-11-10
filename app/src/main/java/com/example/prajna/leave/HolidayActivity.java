//package com.example.prajna.leave;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//
///**
// * Created by prajna on 10/9/16.
// */
//public class HolidayActivity extends Activity {
//    private Context context;
//    private Button mSignOut;
//    String holiday[] = {"26-01-2016   Republic Day", "08-4-2016   Ugadi", "01-05-2016   May Day",
//            "10-07-2016   Ed Milad", "15-08-2016   Independence Day",
//            "05-09-2016   Gowri Ganesha", "2-10-2016   Gandhi Jayanthi", "25-12-2016   Christmas Day"};
//            final Holiday holiday[] = {new Holiday("Republic Day", "26-01-2016"),
//            new Holiday("Ugadi", "08-4-2016"),
//            new Holiday("May Day", "01-05-2016"),
//            new Holiday("Ed Milad", "10-07-2016"),
//            new Holiday("Independence Day", "15-08-2016"),
//            new Holiday("Gowri Ganesha", "05-09-2016"),
//            new Holiday("Gandhi Jayanthi", "2-10-2016"),
//            new Holiday("Christmas Day", "25-12-2016"),};

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_holiday);
//mSignOut=(Button)findViewById(R.id.holiday_sign_out_button);
//        ListView listview = (ListView) findViewById(R.id.holidays_list_view);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_holiday, holiday);
//        listview.setAdapter(adapter);
////        final  ArrayList<Holiday> holidayList=new ArrayList<Holiday>();
////        holidayList.add(new Holiday("Republic Day", "26-01-2016"));
////        holidayList.add(new Holiday("Ugadi", "08-4-2016"));
////        holidayList.add(new Holiday("May Day", "01-05-2016"));
////        holidayList.add(new Holiday("Ed Milad", "10-07-2016"));
////        holidayList.add(new Holiday("Independence Day", "15-08-2016"));
////        holidayList.add(new Holiday("Gowri Ganesha", "05-09-2016"));
////        holidayList.add(new Holiday("Gandhi Jayanthi", "2-10-2016"));
////        holidayList.add(new Holiday("Christmas Day", "25-12-2016"));
////
////
////        CustomAdapter customList=new CustomAdapter(context,holidayList);
////        ListView listview = (ListView) findViewById(R.id.holidays_list_view);
////        listview.setAdapter(customList);
//
////        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////            }
////        });
//mSignOut.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        Intent intent=new Intent(HolidayActivity.this,FirstActivity.class);
//        startActivity(intent);
//    }
//});
//    }
//}
//
//
