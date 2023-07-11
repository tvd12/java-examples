package com.tvd12.example.ssl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;

public class JKSFileCreator {

    public static void main(String[] args) throws Exception {
        String jksFilePath = "keystore.jks";
        String jksFilePassword = "secret";
        String entryAlias = "ezyfox-server";
        String entryPassword = "secret";

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null);
        Certificate certificate = getCertificate();
        PrivateKey privateKey = getPrivateKey();
        keyStore.setCertificateEntry(entryAlias, certificate);
        keyStore.setKeyEntry(entryAlias, privateKey, entryPassword.toCharArray(), new Certificate[]{certificate});
        try (FileOutputStream fileOutputStream = new FileOutputStream(jksFilePath)) {
            keyStore.store(fileOutputStream, jksFilePassword.toCharArray());
            System.out.println("JKS file created successfully.");
        }
    }

    private static Certificate getCertificate() {
        try (InputStream inputStream = Files.newInputStream(Paths.get("certificate.crt"))) {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            return certificateFactory.generateCertificate(inputStream);
        } catch (CertificateException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static PrivateKey getPrivateKey() throws Exception {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get("private_key.pem"));
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(privateKeySpec);
    }
}