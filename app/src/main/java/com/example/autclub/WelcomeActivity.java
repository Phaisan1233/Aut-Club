package com.example.autclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity<login> extends AppCompatActivity
        //     implements View.OnClickListener
{

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // UserName = (EditText)findViewById(R.id.UName);


        next = findViewById(R.id.btnnext);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                next();
            }

        });
        //   LogOut.setOnClickListener(this);
    }

    public void next() {
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
