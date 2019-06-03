package com.example.autclub.AppModel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.autclub.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;

public class ThreadConnectDatabase extends Thread {
    private static final String TAG = "ThreadConnectDatabase";
    private final String databaseURL = "https://softwareteamproject.000webhostapp.com/"; //datebase link
    private Map<String,String> parameter ;
    private String phpFile ;
    public VolleyResponseListener callback;

    boolean stopThread = false;
    private RequestQueue requestQueue;// request info form database
    private int sleepTime;

    public ThreadConnectDatabase(RequestQueue requestQueue,Map<String,String> parameter,String phpFile) {
        this.requestQueue = requestQueue;
        this.parameter = parameter;
        this.phpFile = phpFile;
        this.sleepTime = 0;
    }

    public ThreadConnectDatabase(RequestQueue requestQueue,Map<String,String> parameter,String phpFile,VolleyResponseListener callback1) {
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
                Log.d(TAG, "run: running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }while (!stopThread);
    }
    private void stringRequest(Response.Listener<String> responseListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, databaseURL + phpFile, responseListener, null) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = parameter;
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void responseListener() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onResponse(response);
            }
        };
        stringRequest(responseListener);
    }


}
