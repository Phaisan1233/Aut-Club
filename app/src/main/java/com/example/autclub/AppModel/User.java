package com.example.autclub.AppModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * The User contain user information.
 */
public class User  {
    private int userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;

    private double timeStamp;
    private ArrayList<Club> clubArrayList; //the list of club that user are following

    /**
     * The constructor to create a new User.
     */
    public User() {
    }

    public User(int userID, String firstName, String timeStamp,ArrayList clubArrayList) {
        setUserID(userID);
        setUserName(null);
        setFirstName(firstName);
        setLastName(null);
        setEmail(null);
        setTimeStamp(timeStamp);
        setClubArrayList(clubArrayList);
    }

    /**
     * The constructor to create a new User.
     *
     * @param userName  the user name
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     */
    public User(int userID,String userName, String firstName, String lastName, String email, String timeStamp ) {
        setUserID(userID);
        setUserName(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setTimeStamp(timeStamp);
    }

    /**
     * The constructor to create a new User.
     *
     * @param userName  the user name
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param timeStamp the time
     */
    public User(int userID,String userName, String firstName, String lastName, String email, String timeStamp , ArrayList clubArrayList) {
        setUserID(userID);
        setUserName(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setTimeStamp(timeStamp);
        setClubArrayList(clubArrayList);
    }


    public User(String username, String firstName, String lastName, String email,String timeStamp) {
        setUserName(username);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setTimeStamp(timeStamp);
    }

    public int getUserID() {
        return userID;
    }

    @SerializedName("userID")
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    @SerializedName("userName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    @SerializedName("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    @SerializedName("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    @SerializedName("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets time stamp.
     *
     * @return the time stamp
     */
    public double getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets time stamp.
     *
     * @param timeStamp the time stamp
     */
    @SerializedName("time")
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = Double.parseDouble(timeStamp.replaceAll("[^0-9]+", "").substring(0,14));
    }

    /**
     * Gets follow club.
     *
     * @return the follow club
     */
    public ArrayList<Club> getClubArrayList() {
        return clubArrayList;
    }

    /**
     * Sets follow club.
     *
     * @param clubArrayList the follow club
     */
    @SerializedName("clubList")
    public void setClubArrayList(ArrayList<Club> clubArrayList) {
        this.clubArrayList = clubArrayList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", timeStamp=" + timeStamp +
                ", clubArrayList=" + clubArrayList +
                '}';
    }


}
