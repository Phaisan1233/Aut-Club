package com.example.autclub.ClubController;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.autclub.R;
import com.example.autclub.MainController.View;

import java.util.ArrayList;

public class MSA extends AppCompatActivity {
    private static final String TAG = "MSA";
    ArrayList<String> description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.click);
        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MSA.this, View.class);
                startActivity(intent);
            }
        });
    }
}


