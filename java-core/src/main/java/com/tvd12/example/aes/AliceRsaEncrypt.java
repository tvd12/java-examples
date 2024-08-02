package com.tvd12.example.aes;

import com.tvd12.ezyfox.security.EzyAsyCrypt;
import com.tvd12.ezyfox.security.EzyBase64;

public class AliceRsaEncrypt {

    public static void main(String[] args) throws Exception {
        EzyAsyCrypt ezyAsyCrypt = EzyAsyCrypt.builder()
            .publicKey(EzyBase64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAovHRRfqPq4K+LBT0ARjCTQUc/jzBa9t/I3i2pH237uDNJzmpIZw7qW1s56yIlDQC7mysW3v5eEmD6cRwTr4LUVvaMsDSe5yRhwLXACIZqVVqyvMesJGkLLm26UiG/OabQoD89D17vhHFv/lW/hsUcYOwo/QsnTpmFgpGRd0tT3xzV6GyvxbIuZeX6GG9I9WWATRXNTxY32KG2qd1pgDePYXbRYMtxTejUSe9QKvYJm1k/x8znNPRBOIQkPXnkEVpq4Yg7iU63fEblA/CHLteQjAKnxuaW57UY7Bje3RfsjrzaFpyDPnyMB8x1fgAgkfdN9VQDdFMJX+S5EiiQq5QJwIDAQAB"))
            .build();
        String text = "techMaster";
        byte[] encryptedData = ezyAsyCrypt.encrypt(text);
        System.out.println(EzyBase64.encode2utf(encryptedData));
    }
}
