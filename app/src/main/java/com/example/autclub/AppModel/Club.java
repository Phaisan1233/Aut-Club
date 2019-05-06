package com.example.autclub.AppModel;

/**
 * The Club class contain club information.
 */
public class Club {
    private int clubID;
    private String name;
    private int image;
    private String tokens;

    /**
     * The constructor to create a new Club.
     */
    public Club() {
    }

    /**
     * The constructor to create a new Club.
     *
     * @param name  the club name
     * @param image the club image icon
     */
    public Club(String name, int image) {
        this.name = name;
        this.image = image;
    }

    /**
     * The constructor to create a new Club.
     *
     * @param clubID the club id
     * @param name   the club name
     * @param tokens the club fb api tokens
     */
    public Club(int clubID, String name, String tokens) {
        setClubID(clubID);
        setName(name);
        setTokens(tokens);
    }

    /**
     * Gets club id.
     *
     * @return the club id
     */
    public int getClubID() {
        return clubID;
    }

    /**
     * Sets club id.
     *
     * @param clubID the club id
     */
    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public int getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(int image) {
        this.image = image;
    }

    /**
     * Gets tokens.
     *
     * @return the tokens
     */
    public String getTokens() {
        return tokens;
    }

    /**
     * Sets tokens.
     *
     * @param tokens the tokens
     */
    public void setTokens(String tokens) {
        this.tokens = tokens;
    }


}
