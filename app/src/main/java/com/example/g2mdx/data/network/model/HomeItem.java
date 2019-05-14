package com.example.g2mdx.data.network.model;

public class HomeItem {

    private String name = "";
    private int action = 0;

    public HomeItem(String name, int action) {
        this.name = name;
        this.action = action;
    }

    public String getTitle() {
        return name;
    }

    public int getAction() {
        return action;
    }
}
