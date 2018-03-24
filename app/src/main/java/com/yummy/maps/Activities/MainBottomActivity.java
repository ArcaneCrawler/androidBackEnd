package com.yummy.maps.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yummy.maps.Adapters.OrdersAdapter;
import com.yummy.maps.Entities.Order;
import com.yummy.maps.PointFinder;
import com.yummy.maps.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainBottomActivity extends AppCompatActivity {

    private TextView mTextMessage;
    final String LOG_TAG = "myLogs";
    ArrayList<Order> orders = new ArrayList<Order>();
    OrdersAdapter ordersAdapter;
    private String addressFrom = "null";
    private String addressTo = "null";
    long id;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_order:
                    //mTextMessage.setText(R.string.title_orders);
                    getList();
                    return true;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bottom);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    void fillData(){
        orders.clear();

        PointFinder pf = new PointFinder();
        pf.execute();
        orders = pf.getOrders();
    }

    private void getList(){
        fillData();
        ordersAdapter = new OrdersAdapter(this, orders);

        ListView lvOrders = (ListView) findViewById(R.id.list_Orders);
        lvOrders.setAdapter(ordersAdapter);
        lvOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG_TAG, "itemClick: position = " + position + ", id = "
                        + id);
            }
        });
    }



}
