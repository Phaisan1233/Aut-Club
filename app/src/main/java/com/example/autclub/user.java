package com.example.autclub;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private long timeStamp;
    private Club followClub[];
    private Club joinCLub[];


    public User(String userName, String firstName, String lastName, String email) {
        setUserName(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }
    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
