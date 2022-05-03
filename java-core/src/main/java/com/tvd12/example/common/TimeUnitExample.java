package com.tvd12.example.common;

import java.util.concurrent.TimeUnit;

public class TimeUnitExample {

    public static void main(String[] args) {
        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.MILLISECONDS));
        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS));
        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES));
        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS));
    }

}
