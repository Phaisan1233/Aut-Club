package com.example.autclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.autclub.listing.description;

public class Clubpage extends AppCompatActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clubpage);
        Button Join = (Button) findViewById(R.id.Join);
        TextView t=findViewById(R.id.Description);
        TextView clubname=findViewById(R.id.clubname);
        ImageView im=findViewById(R.id.exp_pic);;
        Intent in=getIntent();
        String text=in.getStringExtra("msa");
        String text2=in.getStringExtra("exp");
        String text3=in.getStringExtra("horizon");
        String text4=in.getStringExtra("stem");
        if(text.equalsIgnoreCase("msa")){
            String name="AUT MSA";
            clubname.setText(name);
            im.setImageResource(R.drawable.msa);
            String msadescription=description.get(0);
            t.setText(msadescription);
        }if(text2.equalsIgnoreCase("exp")){
            String name="EXPRESSION";
            clubname.setText(name);
            im.setImageResource(R.drawable.expression);
            String expressiondescription=description.get(2);
            t.setText(expressiondescription);
        }if(text3.equalsIgnoreCase("horizon")){
            String name="HORIZON";
            clubname.setText(name);
            im.setImageResource(R.drawable.horizon);
            String Horizondescription=description.get(1);
            t.setText(Horizondescription);
        }if(text4.equalsIgnoreCase("stem")){
            String name="STEM WOMEN";
            clubname.setText(name);
            im.setImageResource(R.drawable.stemwomen);
            String stemdescription=description.get(3);
            t.setText(stemdescription);
        }

        Join.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(Clubpage.this, View.class);
                startActivity(intent);
            }
        });

        //t.setText("YAY!!!");
    }
}
