package com.tvd12.example.aes;

import com.tvd12.ezyfox.security.EzyBase64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class BobKeyGenerator {

    public static void main(String[] args) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        String publicKey = EzyBase64.encode2utf(pair.getPublic().getEncoded());
        String privateKey = EzyBase64.encode2utf(pair.getPrivate().getEncoded());

        System.out.println("Bob public key: " + publicKey);
        System.out.println("Bob private key: " + privateKey);
    }
}
