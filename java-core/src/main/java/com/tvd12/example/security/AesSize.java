package com.tvd12.example.security;

import com.tvd12.ezyfox.security.EzyAesCrypt;

public class AesSize {

    public static void main(String[] args) throws Exception {
        byte[] key = EzyAesCrypt.randomKey();
        byte[] bytes = new byte[1024];
        byte[] encryptedBytes = EzyAesCrypt.getDefault()
            .encrypt(bytes, key);
        System.out.println(encryptedBytes.length);
        System.out.println((1.0D * encryptedBytes.length) / bytes.length);
    }
}
