package com.example.autclub.LoginSignupController;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class ResetPasswordActivity extends AppCompatActivity {
    private final String PASSWORD_RESET_PHP = "ResetPassword.php";
    protected RequestQueue requestQueue;

    private EditText etUsername ;
    private EditText etPassword ;
    private EditText etConfirmPass ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

         etUsername = findViewById(R.id.reset_userName);
         etPassword = findViewById(R.id.reset_userPassword);
         etConfirmPass = findViewById(R.id.reset_confirmpassword);

        requestQueue = Volley.newRequestQueue(ResetPasswordActivity.this);
    }

    private void eventHandleResetButton(View view) {
        Map<String, String> params = new HashMap<>();
        params.put("username", etUsername.getText().toString());
        params.put("password", etPassword.getText().toString());

        if (!etPassword.getText().toString().equalsIgnoreCase(etConfirmPass.getText().toString())) {
            App.buildDialog(ResetPasswordActivity.this,"Password and ConfirmPassword doesn't match ", "OK");
        } else {
            ThreadConnectDatabase thread = new ThreadConnectDatabase(requestQueue, params, PASSWORD_RESET_PHP, new VolleyResponseListener() {
                @Override
                public void onResponse(String response) {
                    eventHandleResetButtonResponse(response);
                }
            });
            thread.stopThread();
            thread.start();
        }


    }

    private void eventHandleResetButtonResponse(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            boolean success;
            success = jsonResponse.getBoolean("success");
            if (success) {
                App.buildDialog(ResetPasswordActivity.this,"Change password successful", "OK");
                App.newActivityPage(ResetPasswordActivity.this,LoginActivity.class,"");
            } else {
                App.buildDialog(ResetPasswordActivity.this,"Change password Failed: \n       Username is not existed", "Retry");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
