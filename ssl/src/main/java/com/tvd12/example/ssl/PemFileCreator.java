package com.tvd12.example.ssl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class PemFileCreator {

    public static void main(String[] args) throws Exception {
        // Assume you have the X509Certificate object representing the certificate
        X509Certificate certificate = getCertificate();

        // Convert the certificate to PEM format
        String pemCertificate = convertToPEMFormat(certificate);

        // Create a FileOutputStream to write the CRT file
        try (FileOutputStream fileOutputStream = new FileOutputStream("certificate.pem")) {
            // Write the PEM formatted certificate to the CRT file
            fileOutputStream.write(pemCertificate.getBytes());

            System.out.println("CRT file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static X509Certificate getCertificate() throws Exception {
        try (InputStream inputStream = Files.newInputStream(Paths.get("certificate.crt"))) {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            return (X509Certificate) certificateFactory.generateCertificate(inputStream);
        }
    }

    private static String convertToPEMFormat(X509Certificate certificate) throws Exception {
        String encodedCert = Base64.getEncoder().encodeToString(certificate.getEncoded());
        StringBuilder pemCertificate = new StringBuilder();
        pemCertificate.append("-----BEGIN CERTIFICATE-----\n");
        int chunkSize = 64;
        int length = encodedCert.length();
        for (int i = 0; i < length; i += chunkSize) {
            int endIndex = Math.min(i + chunkSize, length);
            pemCertificate.append(encodedCert, i, endIndex).append("\n");
        }
        pemCertificate.append("-----END CERTIFICATE-----\n");
        return pemCertificate.toString();
    }
}

