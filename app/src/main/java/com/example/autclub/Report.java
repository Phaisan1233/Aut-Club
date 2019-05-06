package com.example.autclub;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class Report extends Activity
{
    protected void onCreate(Bundle  savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display); //store the values
        int height = display.heightPixels;
        int width = display.widthPixels;

        getWindow().setLayout((int) (width*0.6) , (int) (height*0.3));
    }
}