package com.example.autclub;

public class user {
    String name,password,username,email,JoinedClub,FollowedClub;
    //constructor
    public user(String name,String username,String password,String email){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public user(String username,String password){
        this.username = username;
        this.password = password;
        this.email="";
        this.name="";
    }
    public user(String JoinedClub){
        this.username="";
        this.password="";
        this.email="";
        this.name="";
        this.JoinedClub=JoinedClub;



    }
//    public user(String FollowedClub){
//
//        this.username="";
//        this.password="";
//        this.email="";
//        this.name="";
//        this.FollowedClub=FollowedClub;
//
//    }
}
