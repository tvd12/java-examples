package com.tvd12.example.ssl;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

public class KeystoreReader {

    public static void main(String[] args) {
        String keystoreFile = "keystore.jks"; // Path to the keystore file

        try {
            // Load the keystore from the file
            KeyStore keystore = KeyStore.getInstance("JKS");
            FileInputStream fileInputStream = new FileInputStream(keystoreFile);
            keystore.load(fileInputStream, null);

            Enumeration<String> aliases = keystore.aliases();
            // Iterate over the aliases in the keystore
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                // Get the certificate for each alias
                Certificate certificate = keystore.getCertificate(alias);

                // Convert the certificate to Base64 and print it
                String base64Cert = encodeCertificateToBase64(certificate);
                System.out.println("Alias: " + alias);
                System.out.println("Certificate: " + base64Cert);
                System.out.println();
            }

            // Close the input stream
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Encode a certificate to Base64 format
    private static String encodeCertificateToBase64(
        Certificate certificate
    ) throws Exception {
        byte[] certBytes = certificate.getEncoded();
        return java.util.Base64.getEncoder().encodeToString(certBytes);
    }
}
