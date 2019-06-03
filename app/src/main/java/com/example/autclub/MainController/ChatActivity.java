package com.example.autclub.MainController;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.autclub.AppModel.Message;
import com.example.autclub.AppModel.ThreadConnectDatabase;
import com.example.autclub.AppModel.User;
import com.example.autclub.AppModel.VolleyResponseListener;
import com.example.autclub.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";
    private static final String chatPHP = "Chat.php"; //php file
    private static final String insertMessagePHP = "InsertMessage.php";


    private EditText editText;
    private ChatAdapter messageAdapter;
    private ListView messagesView;
    private User user;
    private RequestQueue requestQueue;
    private boolean exit = true;

    private ThreadConnectDatabase thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Gson gson = new Gson();
        Intent intent = getIntent();
        user = gson.fromJson(intent.getStringExtra("user"), User.class);
        Log.d(TAG, "onCreate: " + user.toString());

        editText = (EditText) findViewById(R.id.editText);

        messageAdapter = new ChatAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        requestQueue = Volley.newRequestQueue(this);

        thread = new ThreadConnectDatabase(requestQueue, null, chatPHP,new VolleyResponseListener() {
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
        thread.stopThread();
    }

    public void sendMessage(View view) {
        final String message = editText.getText().toString();
        if (message.length() > 0) {
            Map<String, String> params = new HashMap<>();
            params.put("DB_HOST", "localhost");
            params.put("DB_USER", "id9336220_autclubdb");
            params.put("DB_PASSWORD", "software");
            params.put("DB_NAME", "id9336220_autclubdb");
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

    private String getRandomName() {
        String[] adjs = {"autumn", "hidden", "bitter", "misty", "silent", "empty", "dry", "dark", "summer", "icy", "delicate", "quiet", "white", "cool", "spring", "winter", "patient", "twilight", "dawn", "crimson", "wispy", "weathered", "blue", "billowing", "broken", "cold", "damp", "falling", "frosty", "green", "long", "late", "lingering", "bold", "little", "morning", "muddy", "old", "red", "rough", "still", "small", "sparkling", "throbbing", "shy", "wandering", "withered", "wild", "black", "young", "holy", "solitary", "fragrant", "aged", "snowy", "proud", "floral", "restless", "divine", "polished", "ancient", "purple", "lively", "nameless"};
        String[] nouns = {"waterfall", "river", "breeze", "moon", "rain", "wind", "sea", "morning", "snow", "lake", "sunset", "pine", "shadow", "leaf", "dawn", "glitter", "forest", "hill", "cloud", "meadow", "sun", "glade", "bird", "brook", "butterfly", "bush", "dew", "dust", "field", "fire", "flower", "firefly", "feather", "grass", "haze", "mountain", "night", "pond", "darkness", "snowflake", "silence", "sound", "sky", "shape", "surf", "thunder", "violet", "water", "wildflower", "wave", "water", "resonance", "sun", "wood", "dream", "cherry", "tree", "fog", "frost", "voice", "paper", "frog", "smoke", "star"};
        return (
                adjs[(int) Math.floor(Math.random() * adjs.length)] +
                        "_" +
                        nouns[(int) Math.floor(Math.random() * nouns.length)]
        );
    }

    private String getRandomColor() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer("#");
        while (sb.length() < 7) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 7);
    }





    private void parseJSON() {
        String url = "https://softwareteamproject.000webhostapp.com/Chat.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean success = response.getBoolean("success");

                            if (success) {
                                eventHandleResponse(response);
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
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
            }
        } else {

        }

    }


    class ExampleThread extends Thread {

        ExampleThread() {
        }

        @Override
        public void run() {

            while (exit) {
                try {
                    Thread.sleep(1000);
                    Log.d(TAG, "run: running");
                    parseJSON();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}

