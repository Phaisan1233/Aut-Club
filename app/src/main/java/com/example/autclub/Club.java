package com.example.autclub;

import org.json.JSONObject;

public class Club {
    private String name;
    private String tokens;
    private JSONObject jsonObject;

    public Club(String name, String tokens) {
        this.name = name;
        this.tokens = tokens;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }
}
