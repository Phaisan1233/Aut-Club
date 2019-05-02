package com.example.autclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClubPage extends AppCompatActivity {

    Button joinMSA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_page);
        joinMSA =(Button)findViewById(R.id.btnjoin);
        joinMSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //joinMSA();
            }
        });
    }
//    public void joinMSA(){
//        user userDetails = new user();
//
//
//
//    }
}
