
package com.example.autclub;

import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;


//this class will store data of user on a file
public class userStore {
    //file where user details are gonna store when they login
    public static final String SP_NAME = "UserData";
    SharedPreferences userDatabase;// we need SharedPreferences to store data in the mobile
    signpage signup = new signpage();
    //constructor
    public userStore(Context context)
    //As userStore is a java class.So userDatabase that is a sharedprefernce can't be initiated. So, we need to use context in the parameters
    {
        userDatabase = context.getSharedPreferences(SP_NAME, 0);//0 is the default value

    }

    //methods to get the user data from the database
    // to store user data
    public void storedata(user user) {
        SharedPreferences.Editor ed = userDatabase.edit();// to edit the data in userDatabase
        ed.putString("Name", user.name);
        ed.putString("Username", user.username);
//        if(userDatabase.getBoolean("Username",true)==true){
//            System.out.println("Username already exist");
//        }
        ed.putString("Password", user.password);
        ed.putString("email", user.email);
        ed.commit();

    }

    //get currently logged in user's details
    public user getLoggedInUser() {
        if(userDatabase.getBoolean("LoggedIn",false)==false){
            return null;
        }

        String name = userDatabase.getString("Name", "");
        String username = userDatabase.getString("Username", "");
        String password = userDatabase.getString("Password", "");
        String email = userDatabase.getString("email", "");

        user loggedinuser = new user(name, username, password, email);
        return loggedinuser;


    }

    // to check if the user is loggedin or not
    public void setLoggedInUser(boolean loggedin) {
        SharedPreferences.Editor ed = userDatabase.edit();
        ed.putBoolean("LoggedIn", loggedin);
        ed.commit();
    }

//    // to check if the user is logged in or not using the database
//    public boolean getUserLoggedIn() {
//        if (userDatabase.getBoolean("LoggedIn", false)) {
//            return true;
//        } else {
//            return false;
//        }
//
//
//    }


    //to clear the data in the userDatabase
    public void clear() {
        SharedPreferences.Editor ed = userDatabase.edit();
        ed.clear();
        ed.commit();
    }
}
