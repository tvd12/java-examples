package com.tvd12.example.aes;

import com.tvd12.ezyfox.security.EzyAesCrypt;
import com.tvd12.ezyfox.security.EzyBase64;

public class EzyAESExample {

    public static void main(String[] args) {
        String password = EzyBase64.encode2utf(EzyAesCrypt.randomKey());
        System.out.println(password);
    }
}
