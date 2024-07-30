package com.tvd12.example.io;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal value = new BigDecimal("10.0000100");
        System.out.println(value.stripTrailingZeros().toPlainString());
    }
}
