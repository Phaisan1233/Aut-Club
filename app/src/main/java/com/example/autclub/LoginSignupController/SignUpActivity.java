package com.example.autclub.LoginSignupController;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.autclub.AppModel.App;
import com.example.autclub.AppModel.ThreadConnectDatabase;
import com.example.autclub.AppModel.VolleyResponseListener;
import com.example.autclub.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is getting user details and saving in database
 *
 * @author AutClub
 */
public class SignUpActivity extends AppCompatActivity {

    //Define variables
    private final String databaseURL = "https://softwareteamproject.000webhostapp.com/"; //Database url link to connect
    private final String SignUpPHP = "SignUp.php"; //PHP connect database and app
    protected RequestQueue requestQueue; //get information from database
    private EditText usernameEditText; //username input
    private EditText passwordEditText;// password input
    private EditText confirmPasswordEditText;//confirm password input
    private EditText firstNameEditText;// first name input
    private EditText lastNameEditText;// last name input
    private EditText emailEditText;// email input

    private ThreadConnectDatabase thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameEditText = findViewById(R.id.reset_userName);
        passwordEditText = findViewById(R.id.reset_userPassword);
        confirmPasswordEditText = findViewById(R.id.reset_confirmpassword);
        firstNameEditText = findViewById(R.id.signup_firstName);
        lastNameEditText = findViewById(R.id.signup_lastName);
        emailEditText = findViewById(R.id.signup_email);
        requestQueue = Volley.newRequestQueue(SignUpActivity.this);

    }

    /**
     * This method handle when signup button is pressed
     */
    public void eventHandleSignUpButton(View view) {
        Map<String, String> params = new HashMap<>();
        params.put("username", usernameEditText.getText().toString());
        params.put("password", passwordEditText.getText().toString());
        params.put("firstName", firstNameEditText.getText().toString());
        params.put("lastName", lastNameEditText.getText().toString());
        params.put("email", emailEditText.getText().toString());

        if (!passwordEditText.getText().toString().equalsIgnoreCase(confirmPasswordEditText.getText().toString())) {
            App.buildDialog(SignUpActivity.this,"Password and ConfirmPassword doesn't match ", "OK");
        } else if (!emailEditText.getText().toString().contains("@") || !emailEditText.getText().toString().contains(".com")) {
            App.buildDialog(SignUpActivity.this,"Please enter a valid email address ", "OK");
        } else {
            thread = new ThreadConnectDatabase(requestQueue, params, SignUpPHP,new VolleyResponseListener() {
                @Override
                public void onResponse(String response) {
                    eventHandleSignUpButtonResponse(response);
                }
            });
        }
    }

    /**
     * This method is response with sign uo
     *
     * @param response
     */
    private void eventHandleSignUpButtonResponse(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            boolean success;
            success = jsonResponse.getBoolean("success");
            if (success) {
                App.newActivityPage(SignUpActivity.this,LoginActivity.class,"");
            } else {
                App.buildDialog(SignUpActivity.this,"Sign up Failed: \n       username already existed", "Retry");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
