package com.example.autclub.AppModel;

public class Message {
    private String message;
    private String name;
    private boolean belongsToCurrentUser;
    private String color;


    public Message(String message, String name, boolean belongsToCurrentUser, String color) {
        this.message = message;
        this.name = name;
        this.belongsToCurrentUser = belongsToCurrentUser;
        this.color = color;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }

    public String getColor() {
        return color;
    }
}
