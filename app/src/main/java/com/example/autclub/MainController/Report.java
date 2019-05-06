package com.example.autclub.MainController;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.autclub.R;

public class Report extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display); //store the values
        int height = display.heightPixels;
        int width = display.widthPixels;

        getWindow().setLayout((int) (width * 0.6), (int) (height * 0.3));
    }
}