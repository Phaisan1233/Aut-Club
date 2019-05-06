package com.example.autclub.LoginSignupController;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResetPasswordActivity extends AppCompatActivity {
    private final String databaseURL = "https://softwareteamproject.000webhostapp.com/";
    private final String SignUpPHP = "ResetPassword.php";
    protected RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        final EditText etUsername = findViewById(R.id.reset_userName);
        final EditText etPassword = findViewById(R.id.reset_userPassword);
        final EditText etConfirmPass = findViewById(R.id.reset_confirmpassword);
        final Button btnReset = findViewById(R.id.reset_btnConfirm);

        requestQueue = Volley.newRequestQueue(ResetPasswordActivity.this);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPass.getText().toString();
                if (!password.equalsIgnoreCase(confirmPassword)) {
                    message("Password and ConfirmPassword doesn't match ", "OK");
                } else {
                    eventHandleResetButton(username, password);
                }


            }
        });

    }

    private void eventHandleResetButton(String username, String password) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                eventHandleResetButtonResponse(response);
            }
        };
        resetRequest(username, password, responseListener);
    }

    private void eventHandleResetButtonResponse(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            boolean success;
            success = jsonResponse.getBoolean("success");
            if (success) {
                //Log.d("user", "onResponse: " + response + "\n------------------------------------------------------------");
                message("Change password successful", "OK");
                newActivityPage(LoginActivity.class);
            } else {
                message("Change password Failed: \n       Username is not existed", "Retry");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void resetRequest(final String username, final String password, Response.Listener<String> responseListener) {
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
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void message(String message, String buttonTxt) {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(ResetPasswordActivity.this);
        alertdialog.setMessage(message);
        alertdialog.setPositiveButton(buttonTxt, null);//when ok button is pressed then the error message will go away
        alertdialog.show();
    }

    private void newActivityPage(Class nextClass) {
        Intent intent = new Intent(ResetPasswordActivity.this, nextClass);
        ResetPasswordActivity.this.startActivity(intent);
    }
}
