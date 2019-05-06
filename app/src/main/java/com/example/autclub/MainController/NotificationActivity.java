package com.example.autclub.MainController;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.autclub.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private Button clear;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Intent intent = getIntent();
        //User user = (com.example.autclub.AppModel.User) intent.getExtras().getSerializable("User");

        clear = findViewById(R.id.button_clear);
        mTextViewResult = findViewById(R.id.notification_textView);

        mQueue = Volley.newRequestQueue(NotificationActivity.this);
        getNotification();

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewResult.setText("");
            }
        });
    }

    private void getNotification() {
        String url = "https://graph.facebook.com/v3.3/me?fields=name%2Cposts%7Bstory%7D&access_token=EAAE6FvjiUB0BALY254ZADiVYD1pPifIHkWyjSUyjuc1ytf8NJPjQRZB4CwC7I3JHjYd5jd84UOjHu2n30AcrC30buGR22gKSR3TS7ZCfilEfnFSwnWswLgBpHCXqCK13maO0s3zUsqSsSGvnzyN9WaOXwZCyoPh6CZAuv73ZAP3gZDZD";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String txt = "";
                        try {
                            String name = response.getString("name");
                            JSONObject jo = response.getJSONObject("posts");
                            JSONArray jsonArray = jo.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                String story;
                                try {
                                    JSONObject a = jsonArray.getJSONObject(i);
                                    story = a.getString("story") + "\n\n";

                                } catch (JSONException e) {
                                    story = "" + name + " have a new post" + "\n\n";
                                    continue;
                                }
                                txt = txt + story;
                            }


                        } catch (JSONException e) {
                        }
                        mTextViewResult.setText(txt);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}
