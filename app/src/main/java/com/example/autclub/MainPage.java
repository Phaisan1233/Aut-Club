package com.example.autclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;



public class MainPage extends AppCompatActivity {
Button  logout,club;
EditText username;

//to create a localstore
    userStore store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
       username = (EditText)findViewById(R.id.username);
        logout=(Button)findViewById(R.id.btnLogOut);
club =(Button)findViewById(R.id.btnClub);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                store.clear();//to clear user data when user log out
                store.setLoggedInUser(false);// it says that the user is logged out now

                Intent intent = new Intent(MainPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
        store = new userStore(this);
    club.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            Intent intent = new Intent(MainPage.this,ClubPage.class);
            startActivity(intent);
        }
    });


    }


    protected void onStart(){

        super.onStart();
        if(validate()==true) {

            display();// if user is logged in then it will display the details of the user
        }
//        else{

//            startActivity(new Intent(MainPage.this,login.class));
//        }
    }

    // to check if the user is logged in or loggedout

    private boolean validate(){
        if(store.getLoggedInUser()==null){
            Intent intent = new Intent(MainPage.this,MainActivity.class);
            startActivity(intent);
            return false;

        }
return true;//return true if the user is logged in
    }

    // to display user details
    private void display(){

      user user = store.getLoggedInUser();
      username.setText(user.name);
      username.setText(user.username);
      //username.setText(user.password);
      username.setText(user.email);


    }


}
