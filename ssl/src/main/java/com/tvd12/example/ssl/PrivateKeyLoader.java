package com.tvd12.example.ssl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class PrivateKeyLoader {

    public static void main(String[] args) {
        try {
            // Specify the path to the private key file
            String privateKeyFilePath = "private_key.pem";

            // Load the private key from the file
            PrivateKey privateKey = loadPrivateKey(privateKeyFilePath);

            // Use the private key for further processing
            System.out.println("Private Key Algorithm: " + privateKey.getAlgorithm());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PrivateKey loadPrivateKey(
        String filePath
    ) throws Exception {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(filePath));
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(privateKeySpec);
    }
}
