package com.example.autclub.AppModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The User contain user information.
 */
public class User implements Serializable {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private double timeStamp;
    private ArrayList<Club> followClub; //the list of club that user are following


    /**
     * The constructor to create a new User.
     *
     * @param userName  the user name
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     */
    public User(String userName, String firstName, String lastName, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
    public User(String userName, String firstName, String lastName, String email, double timeStamp) {
        setUserName(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setTimeStamp(timeStamp);
    }


    /**
     * The constructor to create a new User.
     */
    public User() {
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
    public void setTimeStamp(double timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Gets follow club.
     *
     * @return the follow club
     */
    public ArrayList<Club> getFollowClub() {
        return followClub;
    }

    /**
     * Sets follow club.
     *
     * @param followClub the follow club
     */
    public void setFollowClub(ArrayList<Club> followClub) {
        this.followClub = followClub;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", timeStamp=" + timeStamp +
                '}' + followClub.toString();
    }
}
