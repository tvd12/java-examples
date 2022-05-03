package com.tvd12.example.pattern;

import lombok.Getter;

@Getter
public class Activity {

    private final String name;

    public Activity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
