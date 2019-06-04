package com.example.autclub.ClubController;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.autclub.AppModel.App;
import com.example.autclub.AppModel.User;
import com.example.autclub.MainController.JoinRequest;
import com.example.autclub.R;
import com.google.gson.Gson;


public class ClubPageActiviy extends AppCompatActivity {
    private static final String TAG = "ClubPageActiviy";
    private int position;
    private User user;
    private Button joinButton;
    private TextView description;
    private TextView clubname;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubpage);

        joinButton = findViewById(R.id.Join);
        description = findViewById(R.id.Description);
        clubname = findViewById(R.id.clubname);
        imageView = findViewById(R.id.exp_pic);

        setDisplay();
    }

    public void setDisplay() {
        Intent intent = getIntent();
        intent.getStringExtra("value");
        intent.getStringExtra("value2");
        user = new Gson().fromJson(intent.getStringExtra("value"), User.class);
        position = Integer.parseInt(intent.getStringExtra("value2"));
        clubname.setText(user.getClubArrayList().get(position).getName());
        imageView.setImageResource(user.getClubArrayList().get(position).getImage());
        description.setText(user.getClubArrayList().get(position).getDescription());
        Log.d(TAG, "setDisplay: "+position);
    }
    
    public void eventHandleJoinButton(View v){
        App.newActivityPage(ClubPageActiviy.this, JoinRequest.class, user.toString(), String.valueOf(position));
    }
}

