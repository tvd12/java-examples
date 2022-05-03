package com.tvd12.example.common;

import java.util.concurrent.ThreadLocalRandom;

public final class Randoms {
    private static final String ALPHABET_CHARS = "abcdefghijklmnopqrstuvwxyz";

    private Randoms() {}

    public static String randomAlphabetic(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            builder.append(ALPHABET_CHARS.charAt(ThreadLocalRandom.current().nextInt(ALPHABET_CHARS.length())));
        }
        return builder.toString();
    }
}
