package com.example.autclub.AppModel;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.autclub.R;

import java.util.Map;

import static com.example.autclub.AppModel.App.CHANNEL_1_ID;

public class ThreadConnectDatabase extends Thread {
    private static final String TAG = "ThreadConnectDatabase";
    private Map<String, String> parameter;
    private String phpFile;
    private VolleyResponseListener callback;

    private boolean stopThread = false;
    private RequestQueue requestQueue;// request info form database
    private int sleepTime;

    public ThreadConnectDatabase(RequestQueue requestQueue, Map<String, String> parameter, String phpFile, VolleyResponseListener callback1) {
        this.requestQueue = requestQueue;
        this.parameter = parameter;
        this.phpFile = phpFile;
        this.callback = callback1;
        this.sleepTime = 0;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void stopThread() {
        this.stopThread = true;
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(sleepTime);
                responseListener();
                Log.d(TAG, "run: thread running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (!stopThread);
    }

    private void stringRequest(Response.Listener<String> responseListener) {
        //datebase link
        String databaseURL = "https://softwareteamproject.000webhostapp.com/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, databaseURL + phpFile, responseListener, null) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = parameter;
                params.put("DB_HOST", "localhost");
                params.put("DB_USER", "id9336220_autclubdb");
                params.put("DB_PASSWORD", "software");
                params.put("DB_NAME", "id9336220_autclubdb");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void responseListener() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);
                callback.onResponse(response);
            }
        };
        stringRequest(responseListener);
    }

}
