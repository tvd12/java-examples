package com.tvd12.example.extension;

import java.math.BigInteger;

public class IntegerExtensionKt {
    private IntegerExtensionKt() {}

    public static BigInteger toBigInteger(Integer original) {
        return BigInteger.valueOf(original);
    }

    public static void main(String[] args) {
        Integer value = Integer.valueOf(10);
        System.out.println(IntegerExtensionKt.toBigInteger(value));
    }
}
