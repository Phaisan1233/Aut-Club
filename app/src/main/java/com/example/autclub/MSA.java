package com.example.autclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;

public class MSA extends AppCompatActivity {
ArrayList<String> description;
private static final String TAG = "MSA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        description = new ArrayList<>();
//
//        description.add("The Muslim Students Association(MSA) at Auckland University of Technology (AUT) is dedicated to helping our fellow brothers and sisters.");
//        description.add("Horizon. ADP is a club from AUT who focus on creating a sense of community wihtin the AUT student body. The club provides social events, casual dance classes and opportunities for students who want to further develop themselves both in dance and in their own lives. We believe every day should be Pink Shirt Day and love our craft!");
//        description.add("Expression is a fun way to express yourself through dance! All styles are welcome. Join in to have fun, learn a few moves and loosen up from your hectic uni life!");
//        description.add("This group is to support and encourage women in STEM and help them develop the skills they need to do effective networking and succeed in their field of study.");
//
//        for(int i =0; i<description.size();i++){
//            Log.d(TAG, "Description: "+description.get(i));
//        }
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


