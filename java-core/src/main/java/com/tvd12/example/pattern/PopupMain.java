package com.tvd12.example.pattern;

import com.tvd12.example.pattern.PopupBuilder.Popup;

public class PopupMain {

    public static void main(String[] args) throws Exception {
        Popup popup = new PopupBuilder()
            .url("http://tvd12.com/api/popup")
            .token("any thing")
            .activity(new Activity("main"))
            .build();
        popup.open();
    }

}
