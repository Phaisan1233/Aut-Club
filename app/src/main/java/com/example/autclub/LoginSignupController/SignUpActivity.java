package com.example.autclub.LoginSignupController;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.autclub.R;
import com.example.autclub.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private final String databaseURL = "https://softwareteamproject.000webhostapp.com/";
    private final String SignUpPHP = "Register.php";
    protected RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText usernameEditText = findViewById(R.id.reset_userName);
        final EditText passwordEditText = findViewById(R.id.reset_userPassword);
        final EditText confirmPasswordEditText = findViewById(R.id.reset_confirmpassword);
        final EditText firstNameEditText = findViewById(R.id.signup_firstName);
        final EditText lastNameEditText = findViewById(R.id.signup_lastName);
        final EditText emailEditText = findViewById(R.id.signup_email);
        final Button registerButton = findViewById(R.id.signup_btnRegister);
        requestQueue = Volley.newRequestQueue(SignUpActivity.this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                if (!password.equalsIgnoreCase(confirmPassword)) {
                    message("Password and ConfirmPassword doesn't match ", "OK");
                } else if (!email.contains("@") || !email.contains(".com")) {
                    message("Please enter a valid email address ", "OK");
                } else {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String timeFormat = timestamp.toString().replaceAll("[\\s:-]", "");
                    final User user = new User(username, firstName, lastName, email, Double.parseDouble(timeFormat));
                    eventHandleRegisterButton(user, password);
                }
            }
        });
    }

    private void eventHandleRegisterButton(User user, String password) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                eventHandleRegisterButtonResponse(response);
            }
        };
        signUpRequest(user, password, responseListener);
    }

    private void eventHandleRegisterButtonResponse(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            boolean success;
            success = jsonResponse.getBoolean("success");
            if (success) {
                responseHandleSuccess(response);
            } else {
                message("Register Failed: \n       username already existed", "Retry");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void responseHandleSuccess(String response) {
        Log.d("user", "onResponse: " + response + "\n------------------------------------------------------------");
        message("Sign up successful", "OK");
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        SignUpActivity.this.startActivity(intent);
    }

    private void signUpRequest(final User user, final String password, Response.Listener<String> responseListener) {
        String loginRequestURL = databaseURL + SignUpPHP;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginRequestURL, responseListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("DB_HOST", "localhost");
                params.put("DB_USER", "id9336220_autclubdb");
                params.put("DB_PASSWORD", "software");
                params.put("DB_NAME", "id9336220_autclubdb");
                params.put("username", user.getUserName());
                params.put("password", password);
                params.put("firstName", user.getFirstName());
                params.put("lastName", user.getLastName());
                params.put("email", user.getEmail());
                params.put("time", String.valueOf(user.getTimeStamp()));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void message(String message, String buttonTxt) {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(SignUpActivity.this);
        alertdialog.setMessage(message);
        alertdialog.setPositiveButton(buttonTxt, null);//when ok button is pressed then the error message will go away
        alertdialog.show();
    }
}
