package com.yummy.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Modules.GetPoints.PointsFinderListener;

/**
 * Created by Admin on 14.03.2018.
 */

public class EnterActivity extends Activity {

    EditText coor1;
    EditText coor2;
    private String origin = "null";
    private String destination = "null";

    Button calcButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_layout);

        coor1 = (EditText) findViewById(R.id.coor1);
        coor2 = (EditText) findViewById(R.id.coor2);

        calcButton = (Button) findViewById(R.id.calcButton);
    }
    public void calcButton_Click(View view) {
        if(coor1.getText() != null && coor2.getText() != null) {
            Intent intent = new Intent(EnterActivity.this, MainActivity.class);
            intent.putExtra("coor1", coor1.getText().toString());
            intent.putExtra("coor2", coor2.getText().toString());
            startActivity(intent);
        }
    }
    public void getPointsButton_Click(View view) {
            PointsFinder pf = new PointsFinder();
            //Исправить!
            pf.execute();

            Log.d("my_Log", origin);
            Log.d("my_log", destination);
            coor1.setText(origin);
            coor2.setText(destination);
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
                origin = dataJsonObj.getString("addressFrom");
                destination = dataJsonObj.getString("addressTo");

                //Log.d(LOG_TAG, "origin: " + origin);
                //Log.d(LOG_TAG, "destination: " + destination);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
