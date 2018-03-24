package com.yummy.maps.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.yummy.maps.Adapters.OrdersAdapter;
import com.yummy.maps.Entities.Order;
import com.yummy.maps.R;

import java.util.ArrayList;

public class MainBottomActivity extends Activity {

    final String LOG_TAG = "myLogs";
    ArrayList<Order> orders = new ArrayList<Order>();
    OrdersAdapter ordersAdapter;
    FragmentTransaction transaction;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_order:
                    setFragment(new OrderFragment());
                    return true;
                case R.id.navigation_profile:
                    setFragment(new ProfileFragment());
                    //mTextMessage.setText(R.string.title_profile);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    //setFragment(new notificationFragment());
                    return true;
            }
            return false;
        }
    };

    private void setFragment(Fragment fragment){
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_orders, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bottom);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /*void fillData(){
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
    }*/
}
