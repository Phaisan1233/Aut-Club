package com.example.autclub.AppModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * The User contain user information.
 */
public class User implements Parcelable {
    @SerializedName("userID")
    private int userID;

    @SerializedName("userName")
    private String userName;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("time")
    private double timeStamp;

    @SerializedName("club")
    private ArrayList<Club> clubArrayList; //the list of club that user are following

    /**
     * The constructor to create a new User.
     */
    public User() {
    }

    public User(int userID, String firstName, String timeStamp) {
        setUserID(userID);
        setUserName(null);
        setFirstName(firstName);
        setLastName(null);
        setEmail(null);
        setTimeStamp(timeStamp);
        setClubArrayList(null);
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

    protected User(Parcel in) {
        userID = in.readInt();
        userName = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        timeStamp = in.readDouble();
        clubArrayList = in.readArrayList(null);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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
    public void setClubArrayList(ArrayList<Club> clubArrayList) {
        this.clubArrayList = clubArrayList;
    }
    //SELECT c.club_ID ,c.tokens ,c.name,c.Description,f.followStatus,f.joinStatus FROM CLUB c LEFT JOIN following f on c.club_ID = f.club_ID AND f.user_ID = 23



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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userID);
        dest.writeString(userName);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeDouble(timeStamp);
        dest.writeList(clubArrayList);
    }
}
