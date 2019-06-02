package com.example.autclub.LoginSignupController;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.autclub.AppModel.Club;
import com.example.autclub.AppModel.User;
import com.example.autclub.InitialController.InstructionPage;
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
    private static final String databaseURL = "https://softwareteamproject.000webhostapp.com/"; //datebase link
    private static final String loginPHP = "Login.php"; //php file
    private static final String clubPHP = "Club.php";

    private Handler mHandler = new Handler();
    private String databaseResponse;
    private RequestQueue requestQueue;// request info form database

    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //set view variable
        requestQueue = Volley.newRequestQueue(LoginActivity.this);
        gson = new Gson();

    }

    public void eventHandleGuestButton(View view) {
        getClubListResponse();

    }

    public void eventHandleResetPasswordButton(View view) {
        newActivityPage(ResetPasswordActivity.class);
    }

    public void eventHandleSignUpTextView(View view) {
        newActivityPage(SignUpActivity.class);
    }

    public void eventHandleLoginButton(View view) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                eventHandleLoginButtonResponse(response);
            }
        };
        loginRequest(responseListener, loginPHP);
    }

    private void eventHandleLoginButtonResponse(String response) {
        try {

            Log.d(TAG, "eventHandleLoginButtonResponse: " + response);
            JSONObject jsonObject = new JSONObject(response);
            boolean success = jsonObject.getBoolean("success");
            if (success) {
                databaseResponse = response;
                newActivityPage(InstructionPage.class);
            } else {
                message("Login Failed", "Retry");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void loginRequest(Response.Listener<String> responseListener, String php) {
        final EditText usernameEditText = findViewById(R.id.login_etUserName);
        final EditText passwordEditText = findViewById(R.id.login_etPassword);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, databaseURL + php, responseListener, null) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("DB_HOST", "localhost");
                params.put("DB_USER", "id9336220_autclubdb");
                params.put("DB_PASSWORD", "software");
                params.put("DB_NAME", "id9336220_autclubdb");
                params.put("username", usernameEditText.getText().toString());
                params.put("password", passwordEditText.getText().toString());
                params.put("userID", "0");

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getClubListResponse() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d(TAG, "eventHandleLoginButtonResponse: " + response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        Type type = new TypeToken<ArrayList<Club>>() {
                        }.getType();
                        ArrayList<Club> clubList = gson.fromJson(jsonObject.getString("clubList"), type);
                        User user = new User(0, "Guest User","","","",timestamp.toString(),clubList);
                        databaseResponse = gson.toJson(user);
                        newActivityPage(InstructionPage.class);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        loginRequest(responseListener, clubPHP);
    }


    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this, 5000);
            mHandler.removeCallbacks(mToastRunnable);

        }
    };

    private void message(String message, String buttonTxt) {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(LoginActivity.this);
        alertdialog.setMessage(message);
        alertdialog.setPositiveButton(buttonTxt, null);//when ok button is pressed then the error message will go away
        alertdialog.show();
    }

    private void newActivityPage(Class nextClass) {
        mToastRunnable.run();
        Intent intent = new Intent(LoginActivity.this, nextClass);
        Log.d(TAG, "newActivityPage: " + databaseResponse);
        intent.putExtra("user", databaseResponse);
        LoginActivity.this.startActivity(intent);
    }

}




