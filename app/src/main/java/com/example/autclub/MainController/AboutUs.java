package com.example.autclub.MainController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.autclub.R;

public class AboutUs extends AppCompatActivity {

    private Button more_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        more_button = findViewById(R.id.morebutton);

        more_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUs.this, "CLicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AboutUs.this, AboutUs2.class);
                startActivity(intent);
            }


        });
    }
}


