package com.example.autclub.ClubController;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.autclub.R;
import com.example.autclub.MainController.View;

public class Horizon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizon);

        Button button = (Button) findViewById(R.id.click);
        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(Horizon.this, View.class);
                startActivity(intent);
            }
        });
    }
}
