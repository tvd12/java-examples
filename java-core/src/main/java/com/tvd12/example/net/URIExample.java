package com.tvd12.example.net;

import java.net.URI;
import java.net.URL;

public class URIExample {

    public static void main(String[] args) throws Exception {
        URI uri = URI.create("https://tvd12.com");
        System.out.println(uri.toString());
        System.out.println(new URL("https://tvd12.com").toString());
    }

}
