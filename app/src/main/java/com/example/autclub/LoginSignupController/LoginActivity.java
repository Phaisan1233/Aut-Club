package com.example.autclub.LoginSignupController;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.autclub.AppModel.Club;
import com.example.autclub.AppModel.User;
import com.example.autclub.InitialController.InstructionPage;
import com.example.autclub.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
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

    private RequestQueue requestQueue;// request info form database

    private Gson gson;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //set view variable
        requestQueue = Volley.newRequestQueue(LoginActivity.this);
        gson = new Gson();

    }

    public void eventHandleGuestButton(View view) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user = new User(2,"Guest",timestamp.toString());
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
        loginRequest(responseListener);
    }

    private void eventHandleLoginButtonResponse(String response) {
        try {
            Log.d(TAG, "eventHandleLoginButtonResponse: "+response);
            JSONObject jsonObject = new JSONObject(response);
            boolean success = jsonObject.getBoolean("success");
            if (success) {
                user = gson.fromJson(jsonObject.toString(), User.class);
                getClubListResponse();
            } else {
                message("Login Failed", "Retry");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void loginRequest(Response.Listener<String> responseListener) {
        String loginRequestURL = databaseURL + loginPHP;
        final EditText usernameEditText = findViewById(R.id.login_etUserName);
        final EditText passwordEditText = findViewById(R.id.login_etPassword);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginRequestURL, responseListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: user volletError");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("DB_HOST", "localhost");
                params.put("DB_USER", "id9336220_autclubdb");
                params.put("DB_PASSWORD", "software");
                params.put("DB_NAME", "id9336220_autclubdb");
                params.put("username", usernameEditText.getText().toString());
                params.put("password", passwordEditText.getText().toString());
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
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        Type clubType = new TypeToken<ArrayList<Club>>() {}.getType();
                        ArrayList<Club> clubList = gson.fromJson(jsonObject.getString("clubList"), clubType);
                        user.setClubArrayList(clubList);
                        Log.d(TAG, "getClubListResponse: "+user.toString());
                    } else {
                        message("Unable to connect server", "Ok");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        clubRequest(responseListener);
        newActivityPage(InstructionPage.class);

    }


    private void clubRequest(Response.Listener<String> responseListener) {
        String clubRequestURL = databaseURL + clubPHP;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, clubRequestURL, responseListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: club volletError");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("DB_HOST", "localhost");
                params.put("DB_USER", "id9336220_autclubdb");
                params.put("DB_PASSWORD", "software");
                params.put("DB_NAME", "id9336220_autclubdb");
                params.put("userID", String.valueOf(user.getUserID()));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void message(String message, String buttonTxt) {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(LoginActivity.this);
        alertdialog.setMessage(message);
        alertdialog.setPositiveButton(buttonTxt, null);//when ok button is pressed then the error message will go away
        alertdialog.show();
    }

    private void newActivityPage(Class nextClass) {
        Intent intent = new Intent(LoginActivity.this, nextClass);
        intent.putExtra("user", user);
        LoginActivity.this.startActivity(intent);
    }

}




