package com.tvd12.example.ssl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64KeystoreReader {

    public static void main(String[] args) {
        String filePath = "keystore.jks"; // Path to the file

        try {
            // Read the file content as a byte array
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

            // Encode the file content to Base64
            String base64Content = Base64.getEncoder().encodeToString(fileBytes);

            // Print the Base64-encoded content
            System.out.println("Base64 Content:");
            System.out.println(base64Content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
