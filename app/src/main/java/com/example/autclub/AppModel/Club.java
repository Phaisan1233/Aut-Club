package com.example.autclub.AppModel;

public class Club {
    private int clubID;
    private String name;
    private int image;
    private String tokens;

    public Club() {
    }

    public Club(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public Club(int clubID, String name, String tokens) {
        setClubID(clubID);
        setName(name);
        setTokens(tokens);
    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }


}
