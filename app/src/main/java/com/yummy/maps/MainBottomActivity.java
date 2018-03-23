package com.yummy.maps;

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

import com.yummy.maps.Entities.Order;

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
        /*orders.add(new Order(1, origin, destination, "8471", "5845620", "17 mart", "18 mart",
                "18/00", "21,00", "18,00", "19,00", "TEST", "TEST", "Britva", "99.9"));
*/
        PointsFinder pf = new PointsFinder();
        pf.execute();
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

    private class PointsFinder extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";
        String LOG_TAG = "my_log";

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с внешнего ресурса
            try {
                URL url = new URL("https://hidden-atoll-44842.herokuapp.com/example");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            // выводим целиком полученную json-строку
            Log.d(LOG_TAG, strJson);

            JSONObject dataJsonObj = null;
            String secondName = "";

            try {
                dataJsonObj = new JSONObject(strJson);
                //JSONArray dataJsonArr = new JSONObject(strJson).getJSONArray("dataSet").getJSONArray(0);
                JSONArray jsonArray = (JSONArray) dataJsonObj.get("dataSet");

                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject result = jsonArray.getJSONObject(i);
                    id = Long.parseLong(result.getString("id"));
                    addressFrom = result.getString("addressFrom");
                    addressTo = result.getString("addressTo");
                    orders.add(new Order(id, addressFrom, addressTo, "8471", "5845620", "17 mart", "18 mart",
                            "18/00", "21,00", "18,00", "19,00", "TEST", "TEST", "Britva", "99.9"));
                }

                //Log.d(LOG_TAG, "origin: " + origin);
                //Log.d(LOG_TAG, "destination: " + destination);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
