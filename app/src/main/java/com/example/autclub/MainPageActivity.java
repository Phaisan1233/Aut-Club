package com.example.autclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainPageActivity extends AppCompatActivity {
    private TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        mTextViewResult = findViewById(R.id.text_view_result);

        Intent intent = getIntent();
        User user = (User) intent.getExtras().getSerializable("User");

        mTextViewResult.append(user.toString());


        ;
    }
}
