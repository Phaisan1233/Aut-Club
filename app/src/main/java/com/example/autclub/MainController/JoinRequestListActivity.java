package com.example.autclub.MainController;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
    private ListView list;
    private RequestQueue requestQueue;
    private ArrayList<Info> userList;
    private int clubID;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_request_list);

        list=findViewById(R.id.listRequest);
        requestQueue = Volley.newRequestQueue(JoinRequestListActivity.this);
        userList = new ArrayList<>();
        setJoinList();
        Intent intent = getIntent();
        clubID = Integer.parseInt(intent.getStringExtra("value"));
        Log.d(TAG, "onCreate: "+clubID);

    }

    private void setJoinList(){
        String sql = "SELECT * FROM joinRequest WHERE clubID ="+clubID;
        Map<String, String> params = new HashMap<>();
        params.put("sql", sql);
        Log.d(TAG, "setJoinList: "+sql);
        ThreadConnectDatabase threadConnectDatabase = new ThreadConnectDatabase(requestQueue,params,GET_PHP,new VolleyResponseListener() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);
                try {
                    userList = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getBoolean("success")){
                        JSONArray jsonArray = jsonObject.getJSONArray("chatList");
                        for (int i= 0;i < jsonArray.length();i++){
                            JSONObject user = jsonArray.getJSONObject(i);
                            userList.add(new Info(user.getInt("requestID"),
                                    user.getInt("userID"),
                                    user.getString("Fullname"),
                                    user.getString("email")));
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
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,userList);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
              AlertDialog.Builder alertDialog=new AlertDialog.Builder(JoinRequestListActivity.this);
                alertDialog.setTitle("Respond to Requests");
                alertDialog.setMessage("Accept to allow user to join club"+"\n"+"Decline to not allow user to join club");
                alertDialog.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql = "DELETE FROM joinRequest WHERE requestID ="+userList.get(position).requestId;
                        Map<String, String> params = new HashMap<>();
                        params.put("sql", sql);

                        ThreadConnectDatabase threadConnectDatabase = new ThreadConnectDatabase(requestQueue,params, POST_PHP, new VolleyResponseListener() {
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
                        String sql = "DELETE FROM joinRequest WHERE requestID ="+userList.get(position).requestId;
                        Map<String, String> params = new HashMap<>();
                        params.put("sql", sql);

                        ThreadConnectDatabase threadConnectDatabase = new ThreadConnectDatabase(requestQueue,params, POST_PHP, new VolleyResponseListener() {
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
            }

        });



    }

    public class Info{
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
    }

}


