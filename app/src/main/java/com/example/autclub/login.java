package com.example.autclub;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class login extends AppCompatActivity
     //   implements View.OnClickListener
{
    // Assigning the variables
     EditText UserName;
  EditText Password;
  TextView Info,SignUpLink;
    Button LogIn,guest;
    userStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // assign variables with respective xml ids
        UserName = (EditText)findViewById(R.id.userName);
        Password =(EditText)findViewById(R.id.Upassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        SignUpLink=(TextView)findViewById(R.id.tvSignupLink);
        LogIn = (Button)findViewById(R.id.btnRegister);
        guest = (Button)findViewById(R.id.btnguest);
    // assign a click listener so that something will happen when the button is clicked and we need to implements View.OnClickListener on the whole class
store =new userStore(this);

        LogIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                login();

            }

        });

        guest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent =new Intent(login.this, MainPage.class);
                startActivity(intent);

            }

        });

        SignUpLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent =new Intent(login.this, signpage.class);
                startActivity(intent);


            }

        });

    }

private void login() {

    String username = UserName.getText().toString();
    String password = Password.getText().toString();


    user userdetails = new user(username, password);// creating user objective
    validate(userdetails);

//    store.storedata(userdetails);// to store details of the user that logged in
//    store.setLoggedInUser(true);
}

 private void validate(user user){
        Server server = new Server(this);

     server.getUserDetailsInServer(user,new getReturnedUser(){
         @Override
         public void done(user returneduser) {

             if(returneduser==null){


                 message();


             }else {
                 loguserIn(returneduser);

             }

         }
     });

 }
 private void message(){
     AlertDialog.Builder alertdialog = new AlertDialog.Builder(login.this);
     alertdialog.setMessage("Invalid Details");
     alertdialog.setPositiveButton("OK",null);//when ok button is pressed then the error message will go away
      alertdialog.show();

 }
private void loguserIn(user returnedUser){
        store.storedata(returnedUser);
        store.setLoggedInUser(true);
        Intent intent = new Intent(login.this,MainPage.class);
        startActivity(intent);
//  startActivity(new Intent(this, MainActivity.class));

}

}
