package com.example.autclub.MainController;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.autclub.AppModel.Message;
import com.example.autclub.AppModel.ThreadConnectDatabase;
import com.example.autclub.AppModel.User;
import com.example.autclub.AppModel.VolleyResponseListener;
import com.example.autclub.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.autclub.AppModel.App.CHANNEL_1_ID;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";
    private static final String chatPHP = "Chat.php"; //php file
    private static final String insertMessagePHP = "InsertMessage.php";

    private EditText editText;
    private ChatAdapter messageAdapter;
    private ListView messagesView;
    private User user;
    private RequestQueue requestQueue;
    private ThreadConnectDatabase thread;

    private boolean inChatActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        user = new Gson().fromJson(intent.getStringExtra("user"), User.class);
        Log.d(TAG, "onCreate: " + user.toString());
        inChatActivity= true;

        editText = findViewById(R.id.editText);

        messageAdapter = new ChatAdapter(ChatActivity.this);
        messagesView = findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        requestQueue = Volley.newRequestQueue(ChatActivity.this);

        thread = new ThreadConnectDatabase(requestQueue, new HashMap<String, String>(), chatPHP,new VolleyResponseListener() {
            @Override
            public void onResponse(String response) {
                try {
                    eventHandleResponse(new JSONObject(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setSleepTime(1500);
        thread.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        inChatActivity = false;
        thread.setSleepTime(10000);
    }

    public void sendMessage(View view) {
        final String message = editText.getText().toString();
        if (message.length() > 0) {
            Map<String, String> params = new HashMap<>();
            params.put("userID", String.valueOf(user.getUserID()));
            params.put("message", message);

            thread = new ThreadConnectDatabase(requestQueue, params,insertMessagePHP, new VolleyResponseListener() {
                @Override
                public void onResponse(String response) {
                }
            });
            thread.stopThread();
            thread.start();
            editText.getText().clear();
        }
    }

    private String getRandomColor() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder("#");
        while (sb.length() < 7) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 7);
    }

    private void eventHandleResponse(JSONObject jsonObject) throws JSONException {
        boolean success = jsonObject.getBoolean("success");

        if (success) {
            JSONArray jsonArray = jsonObject.getJSONArray("chatList");

            for (int i = messageAdapter.getCount(); i < jsonArray.length(); i++) {
                JSONObject chatList = jsonArray.getJSONObject(i);

                int userID = chatList.getInt("userID");
                String name = chatList.getString("name");
                String message = chatList.getString("message");

                Message m = new Message(message, name, (userID == user.getUserID()), getRandomColor());
                messageAdapter.add(m);
                messagesView.setSelection(messagesView.getCount() - 1);

                if(!inChatActivity && userID != user.getUserID()){
                    setNotification("New message",name+": "+message);
                }
            }
        } else {
            Log.d(TAG, "eventHandleResponse: unable to connect database");
        }
    }
    public void setNotification( String title, String text) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ChatActivity.this);

        Notification notification = new NotificationCompat.Builder(ChatActivity.this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_chat_black_24dp)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }
}

