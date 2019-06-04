package com.example.autclub.MainController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.autclub.AppModel.App;
import com.example.autclub.AppModel.ThreadConnectDatabase;
import com.example.autclub.AppModel.User;
import com.example.autclub.AppModel.VolleyResponseListener;
import com.example.autclub.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class JoinRequest extends AppCompatActivity {
    private static final String TAG = "JoinRequest";
    private static final String postPHP = "Post.php"; //php file

    private User user;
    private int  clubID;

    private TextView fullnameTextView;
    private TextView emailTextView;

    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_request);
        Intent intent = getIntent();

        requestQueue = Volley.newRequestQueue(JoinRequest.this);
        fullnameTextView = findViewById(R.id.fullname);
        emailTextView = findViewById(R.id.email);

        getUserData();

    }

    public void getUserData(){
        Intent intent = getIntent();
        intent.getStringExtra("value");
        intent.getStringExtra("value2");
        user = new Gson().fromJson(intent.getStringExtra("value"), User.class);
        clubID = Integer.parseInt(intent.getStringExtra("value2"))-1;
        Log.d(TAG, "onCreate: " + user.toString());


        if(user.getUserID() == 0){
            findViewById(R.id.submit).setEnabled(false);
            App.buildDialog(JoinRequest.this,"Please login to join club","Ok");
        }
        fullnameTextView.setText(user.getFirstName()+ " "+user.getLastName());
        emailTextView.setText(user.getEmail());

    }

    public void submitButton(View view) {
        Map<String, String> params = new HashMap<>();
        String sql = "INSERT INTO joinRequest (requestID, userID, clubID, Fullname, email) " +
                "VALUES (null,"+user.getUserID()+","+clubID+", \""+fullnameTextView.getText().toString()+"\", \""+emailTextView.getText().toString()+"\")";
        params.put("sql", sql);

        ThreadConnectDatabase threadConnectDatabase = new ThreadConnectDatabase(requestQueue,params,postPHP, new VolleyResponseListener() {
            @Override
            public void onResponse(String response) {

            }
        });
        threadConnectDatabase.stopThread();
        threadConnectDatabase.start();

        App.newActivityPage(JoinRequest.this,MainActivity.class,new Gson().toJson(user));

    }
}
