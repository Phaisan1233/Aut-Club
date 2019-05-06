package com.example.autclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.autclub.MainActivity.MSAevent;
import static com.example.autclub.MainActivity.event;


public class Newsfeed extends AppCompatActivity {

    TextView showevents;
   // Event event;
    String Events;
    ArrayList<String> st=new ArrayList<>();
    ActionBar actionBar;
    ArrayList<Event> e=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed);

        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        showevents=findViewById(R.id.textView2);
       Intent intent = getIntent();
        String reply=intent.getStringExtra("ReplyMsa");
        String reply1=intent.getStringExtra("ReplyExpression");
        String reply2=intent.getStringExtra("ReplyHorizon");
        String reply3=intent.getStringExtra("ReplyStemwomen");
        if(reply.equalsIgnoreCase("msa club")){
            st.add(MSAevent.toString());
            showevents.setText(MSAevent.toString());
        }if(reply1.equalsIgnoreCase("expression club")) {
            st.add(event.toString());
            showevents.setText(event.toString());
        }if(reply2.equalsIgnoreCase("horizon club")) {
            st.add(event.toString());
           showevents.setText(event.toString());
        }if(reply3.equalsIgnoreCase("stemwomen club")) {
            st.add(event.toString());
            showevents.setText(event.toString());
        }




    }
}
