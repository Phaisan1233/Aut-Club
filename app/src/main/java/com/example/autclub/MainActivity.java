package com.example.autclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
        //     implements View.OnClickListener
{

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // UserName = (EditText)findViewById(R.id.UName);


        next = (Button) findViewById(R.id.btnnext);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                next();

            }

        });
        //   LogOut.setOnClickListener(this);
    }

    public void next() {
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
    }
//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.btnlogout:
//
//                startActivity(new Intent(MainActivity.this,login.class));
//               // startActivity(new Intent(this,signuppage.class));
//
//                break;
//        }
//    }

}
