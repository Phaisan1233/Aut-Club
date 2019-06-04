package com.example.autclub.LoginSignupController;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.autclub.AppModel.App;
import com.example.autclub.AppModel.Club;
import com.example.autclub.AppModel.ThreadConnectDatabase;
import com.example.autclub.AppModel.User;
import com.example.autclub.AppModel.VolleyResponseListener;
import com.example.autclub.InitialController.InstructionPage;
import com.example.autclub.MainController.AdminActivity;
import com.example.autclub.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The Login activity control the login activity page.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final String LOGIN_PHP = "Login.php"; //php file

    private RequestQueue requestQueue;// request info form database

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.login_etUserName);
        passwordEditText = findViewById(R.id.login_etPassword);
        //set view variable
        requestQueue = Volley.newRequestQueue(LoginActivity.this);
        gson = new Gson();
        if (!App.isConnected(LoginActivity.this)) {
            App.buildDialog(LoginActivity.this, "No Internet Connection", "Ok");
        }

    }

    public void eventHandleGuestButton(View view) {
        ThreadConnectDatabase thread = new ThreadConnectDatabase(requestQueue, new HashMap<String, String>(), LOGIN_PHP, new VolleyResponseListener() {
            @Override
            public void onResponse(String response) {
                eventHandleGuestButtonResponse(response);
            }
        });
        thread.stopThread();
        thread.start();
    }

    public void eventHandleResetPasswordButton(View view) {
        App.newActivityPage(LoginActivity.this, ResetPasswordActivity.class, "");
    }

    public void eventHandleSignUpTextView(View view) {
        App.newActivityPage(LoginActivity.this, SignUpActivity.class, "");
    }

    public void eventHandleLoginButton(View view) {
        Map<String, String> params = new HashMap<>();
        params.put("username", usernameEditText.getText().toString());
        params.put("password", passwordEditText.getText().toString());
        params.put("userID", "0");

        ThreadConnectDatabase thread = new ThreadConnectDatabase(requestQueue, params, LOGIN_PHP, new VolleyResponseListener() {
            @Override
            public void onResponse(String response) {
                eventHandleLoginButtonResponse(response);
            }
        });
        thread.stopThread();
        thread.start();

    }

    private void eventHandleLoginButtonResponse(String response) {
        try {
            Log.d(TAG, "eventHandleLoginButtonResponse: " + response);
            JSONObject jsonObject = new JSONObject(response);
            boolean success = jsonObject.getBoolean("success");
            if (success) {
                switch (jsonObject.getString("userName")) {
                    case "MSA_admin":
                        App.newActivityPage(LoginActivity.this, AdminActivity.class, "1");
                        return;
                    case "Expression_admin":
                        App.newActivityPage(LoginActivity.this, AdminActivity.class, "2");
                        return;
                    case "Horizon_admin":
                        App.newActivityPage(LoginActivity.this, AdminActivity.class, "3");
                        return;
                    case "STEW_admin":
                        App.newActivityPage(LoginActivity.this, AdminActivity.class, "4");
                        return;
                    default:
                        App.newActivityPage(LoginActivity.this, InstructionPage.class, response);

                }
            } else {
                App.buildDialog(LoginActivity.this, "Login Failed", "Retry");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void eventHandleGuestButtonResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Type type = new TypeToken<ArrayList<Club>>() {
            }.getType();
            ArrayList<Club> clubList = gson.fromJson(jsonObject.getString("clubList"), type);
            User user = new User(0, "Guest User", "", "", "", timestamp.toString(), clubList);
            App.newActivityPage(LoginActivity.this, InstructionPage.class, gson.toJson(user));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}




