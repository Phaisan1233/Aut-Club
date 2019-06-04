package com.example.autclub.MainController;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.autclub.AppModel.ThreadConnectDatabase;
import com.example.autclub.AppModel.VolleyResponseListener;
import com.example.autclub.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JoinRequestListActivity extends AppCompatActivity {
    private static final String TAG = "JoinRequestListActivity";
    private static final String GET_PHP = "Get.php"; //php file
    private static final String POST_PHP = "Post.php";
    private ListView listView;
    private RequestQueue requestQueue;
    private ArrayList<Info> userList;
    private int clubID;
    private ArrayAdapter arrayAdapter;
    private String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_request_list);

        listView = findViewById(R.id.listRequest);

        requestQueue = Volley.newRequestQueue(JoinRequestListActivity.this);
        userList = new ArrayList<>();
        Intent intent = getIntent();
        clubID = Integer.parseInt(intent.getStringExtra("value"));
        sql = intent.getStringExtra("value2");
        Log.d(TAG, "onCreate: " + clubID);
        setJoinList();
    }

    private void setJoinList() {
        Map<String, String> params = new HashMap<>();
        params.put("sql", sql);
        Log.d(TAG, "setJoinList: " + sql);
        ThreadConnectDatabase threadConnectDatabase = new ThreadConnectDatabase(requestQueue, params, GET_PHP, new VolleyResponseListener() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);
                try {
                    userList = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("chatList");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject user = jsonArray.getJSONObject(i);
                            if (user.getString("requestID") == "null") {
                                Log.d(TAG, "onResponse: 1");
                                userList.add(new Info(user.getString("Fullname"), user.getString("email")));

                            } else if (user.getInt("requestID") > 0) {
                                Log.d(TAG, "onResponse: 2");
                                userList.add(new Info(user.getInt("requestID"), user.getInt("userID"), user.getString("Fullname"), user.getString("email")));
                            }
                        }
                    }
                    reponseJoinList();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        threadConnectDatabase.stopThread();
        threadConnectDatabase.start();
    }

    private void reponseJoinList() {
        arrayAdapter = new ArrayAdapter(JoinRequestListActivity.this, android.R.layout.simple_list_item_1, userList);
        listView.setAdapter(arrayAdapter);
        Log.d(TAG, "reponseJoinList: " + listView.getItemAtPosition(0).toString());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(JoinRequestListActivity.this);
                alertDialog.setTitle("Respond to Requests");
                alertDialog.setMessage("Accept to allow user to join club" + "\n" + "Decline to not allow user to join club");
                alertDialog.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql = "DELETE FROM joinRequest WHERE requestID =" + userList.get(position).requestId;
                        Map<String, String> params = new HashMap<>();
                        params.put("sql", sql);

                        ThreadConnectDatabase threadConnectDatabase = new ThreadConnectDatabase(requestQueue, params, POST_PHP, new VolleyResponseListener() {
                            @Override
                            public void onResponse(String response) {

                            }
                        });
                        threadConnectDatabase.stopThread();
                        threadConnectDatabase.start();
                        userList.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });
                alertDialog.setNegativeButton("DECLINE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql = "DELETE FROM joinRequest WHERE requestID =" + userList.get(position).requestId;
                        Map<String, String> params = new HashMap<>();
                        params.put("sql", sql);

                        ThreadConnectDatabase threadConnectDatabase = new ThreadConnectDatabase(requestQueue, params, POST_PHP, new VolleyResponseListener() {
                            @Override
                            public void onResponse(String response) {

                            }
                        });
                        threadConnectDatabase.stopThread();
                        threadConnectDatabase.start();
                        userList.remove(position);
                        arrayAdapter.notifyDataSetChanged();

                    }
                });
                alertDialog.show();
            }

        });

    }

    public class Info {
        int requestId;
        int userID;
        String fullname;
        String email;

        public Info(int requestId, int userID, String fullname, String email) {
            this.requestId = requestId;
            this.userID = userID;
            this.fullname = fullname;
            this.email = email;
        }

        public Info(String fullname, String email) {
            this.fullname = fullname;
            this.email = email;
        }

        @Override
        public String toString() {
            return "Fullname :" + fullname +
                    "\nEmail :" + email;
        }
    }

}


