package com.tvd12.example.ssl;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class BksKeystoreGenerator {

    public static void main(String[] args) {
        try {
            Security.addProvider(new BouncyCastleProvider());

            String certificateFile = "certificate.crt"; // Path to the certificate file
            String keystoreFile = "keystore.bks"; // Path to the output BKS keystore file
            String keystorePassword = "secret"; // Password for the keystore

            // Load the certificate from the certificate file
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Certificate certificate = certificateFactory.generateCertificate(new FileInputStream(certificateFile));

            // Create a new BKS keystore
            KeyStore keyStore = KeyStore.getInstance("BKS", "BC");
            keyStore.load(null, keystorePassword.toCharArray());

            // Add the certificate to the keystore
            keyStore.setCertificateEntry("alias", certificate);

            // Save the keystore to the keystore file
            FileOutputStream fos = new FileOutputStream(keystoreFile);
            keyStore.store(fos, keystorePassword.toCharArray());
            fos.close();

            System.out.println("BKS keystore generated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

