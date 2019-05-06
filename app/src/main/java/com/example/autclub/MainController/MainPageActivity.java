package com.example.autclub.MainController;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.autclub.InitialController.WelcomeActivity;
import com.example.autclub.R;
import com.example.autclub.User;

public class MainPageActivity extends AppCompatActivity {
    Button logoutButton;
    private TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        mTextViewResult = findViewById(R.id.text_view_result);

        Intent intent = getIntent();
        User user = (com.example.autclub.User) intent.getExtras().getSerializable("User");
        mTextViewResult.append(user.toString());
        logoutButton = findViewById(R.id.btnLogOut);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                store.clear();//to clear user data when user log out
//                store.setLoggedInUser(false);// it says that the user is logged out now

                Intent intent = new Intent(MainPageActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
