package com.tvd12.example.jwt;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import io.jsonwebtoken.Jwts;

public class JwtVerifyExample {

    private static final String publicKeyBase64 = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE02Ar8685UapqDlOT8TneixaeR2Uu0tRu0R8WTG5ATA7j3Nk9x6poNYiH/Ez6jhwQg/ocioHsjzOOStUeSN8aIg==";
    private static final String token = "eyJhbGciOiJFUzI1NiJ9.eyJzdWIiOiJ0ZXN0LW5vYyIsImF1dGgiOiJCSV9FWFBPUlQiLCJlbWFpbCI6ImFiYy5jb20udm4iLCJmdWxsX25hbWUiOiJCVSBOT0MiLCJST0xFX0pXVCI6IkJJX0VYUE9SVCIsIk1OVl9KV1QiOiJ0ZXN0LW5vY19ub2MiLCJleHAiOjk5OTk5OTk5OTk5OTk5fQ.xdtY5Rbay7I5733gBOzSoPFrADCwqE7PhuNGd0zdkyB81wbGunFny2CjagQfgVr_kg0RviUmusjGmjiVk3zB_Q";

    public static void main(String[] args) {
        try {
            PublicKey publicKey = getPublicKey();
            Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PublicKey getPublicKey() throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(JwtVerifyExample.publicKeyBase64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC"); // Or whatever algorithm your key is using
        return keyFactory.generatePublic(keySpec);
    }
}
