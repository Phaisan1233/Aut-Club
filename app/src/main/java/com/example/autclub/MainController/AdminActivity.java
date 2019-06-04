package com.example.autclub.MainController;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.autclub.AppModel.App;
import com.example.autclub.R;

public class AdminActivity extends AppCompatActivity {
    private static final String TAG = "AdminActivity";
    private int clubID;
    private Button pendingBtn;
    private Button joined_usersBtn;
    private Button feedback;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        pendingBtn = findViewById(R.id.pending);
        joined_usersBtn = findViewById(R.id.joinedusers);
        feedback = findViewById(R.id.feedback);
        imageView = findViewById(R.id.imageView4);

        setAdminActivity();

        joined_usersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.newActivityPage(AdminActivity.this, JoinRequestListActivity.class,
                        String.valueOf(clubID),"SELECT CONCAT(u.firstName, ' ', u.lastName) AS Fullname, u.email " +
                                "FROM USER u , following f WHERE u.user_ID = f.user_ID AND f.club_ID = "+clubID+" AND f.joinStatus = 1");


            }
        });

        pendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                App.newActivityPage(AdminActivity.this, JoinRequestListActivity.class, String.valueOf(clubID),
                        "SELECT * FROM joinRequest WHERE clubID =" + clubID);

            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Intent.ACTION_SEND);
                in.putExtra(Intent.EXTRA_EMAIL,new String[]{"autclubs@outlook.com"});
                in.putExtra(Intent.EXTRA_SUBJECT,"Admin Feedback");

                in.setType("text/plain");
                startActivity(in);
            }
        });

    }

    private void setAdminActivity() {
        Intent intent = getIntent();
        clubID = Integer.parseInt(intent.getStringExtra("value"));
        switch (clubID) {
            case 1:
                imageView.setImageResource(R.drawable.msa);
                return;
            case 2:
                imageView.setImageResource(R.drawable.expression);
                return;
            case 3:
                imageView.setImageResource(R.drawable.horizon);
                return;
            case 4:
                imageView.setImageResource(R.drawable.stemwomen);
                return;
        }
    }

}



