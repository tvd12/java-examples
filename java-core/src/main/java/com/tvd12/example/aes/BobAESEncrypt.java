package com.tvd12.example.aes;

import com.tvd12.ezyfox.security.EzyAesCrypt;
import com.tvd12.ezyfox.security.EzyBase64;

import java.nio.charset.StandardCharsets;

public class BobAESEncrypt {

    public static void main(String[] args) throws Exception {
        String text = "TextMaster";
        EzyAesCrypt ezyAesCrypt = EzyAesCrypt.builder()
            .build();
        byte[] encryptedData = ezyAesCrypt.encrypt(
            text.getBytes(StandardCharsets.UTF_8),
            EzyBase64.decode("velEHPXYROgXUT8wFdbYL4Guw5CVaxKnp/4lSwSyeV0=")
        );
        System.out.println(EzyBase64.encode2utf(encryptedData));
    }
}
