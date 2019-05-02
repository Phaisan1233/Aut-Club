package com.example.autclub;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signpage extends AppCompatActivity {
    Button register;
    EditText Name, Username, Password, Confirmpassword, Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signpage);
        Name = (EditText) findViewById(R.id.name);
        Username = (EditText) findViewById(R.id.userName);
        Password = (EditText) findViewById(R.id.userPassword);
        Confirmpassword = (EditText) findViewById(R.id.Uconfirmpassword);
        Email = (EditText) findViewById(R.id.email);
        register = (Button) findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String username = Username.getText().toString();

                String password = Password.getText().toString();
                String confirmpassword = Confirmpassword.getText().toString();
                String email = Email.getText().toString();



   if(password.equalsIgnoreCase(confirmpassword) && email.contains("@") && email.contains(".com")) {
        user userdetails = new user(name, username, password, email);
        signedupuser(userdetails);
    }
       else if (!password.equalsIgnoreCase(confirmpassword)) {
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(signpage.this);
            alertdialog.setMessage("Password and ConfirmPassword doesn't match ");
            alertdialog.setPositiveButton("OK", null);//when ok button is pressed then the error message will go away
            alertdialog.show();


        }
//                else
//                {
//                    user userdetails = new user(name, username, password, email);
//                    signedupuser(userdetails);
//
//
//                }
       else if (!email.contains("@") || !email.contains(".com")) {
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(signpage.this);
            alertdialog.setMessage("Please enter a valid email address ");
            alertdialog.setPositiveButton("OK", null);//when ok button is pressed then the error message will go away
            alertdialog.show();

        }




//                else
//                {




              //  }


//        Intent intent = new Intent(signpage.this,login.class);
//        startActivity(intent);
            }
        });
    }

    private void signedupuser(user user) {

        Server server = new Server(this);
        server.savingUserDetailsInServer(user, new getReturnedUser() {
            @Override
            public void done(user returneduser) {
                message();
                Intent intent = new Intent(signpage.this, login.class);


                startActivity(intent);
            }
        });

    }
    private void message(){
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(signpage.this);
        alertdialog.setMessage("Sign Up Successfull!!");
        alertdialog.setPositiveButton("OK",null);//when ok button is pressed then the error message will go away
        alertdialog.show();


    }
}
