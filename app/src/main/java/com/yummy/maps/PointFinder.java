package com.yummy.maps;

import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by Man on 24.03.2018.
 */

public class PointFinder extends AsyncTask<Void, Void, String> {

    ArrayList<Order> orders = new ArrayList<Order>();

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
                orders.add(new Order(Long.parseLong(result.getString("id")), result.getString("addressFrom"), result.getString("addressTo"),
                        result.getString("telNumber1"), result.getString("telNumber2"), result.getString("dateFrom"), result.getString("dateTo"),
                        result.getString("toTime1"), result.getString("toTime2"), result.getString("fromTime1"), result.getString("fromTime2"),
                        result.getString("comment1"), result.getString("comment2"), result.getString("deliverItem"), result.getString("cost")));
            }

            //Log.d(LOG_TAG, "origin: " + origin);
            //Log.d(LOG_TAG, "destination: " + destination);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
