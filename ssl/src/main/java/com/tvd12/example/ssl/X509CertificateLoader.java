package com.tvd12.example.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class X509CertificateLoader {

    public static void main(String[] args) throws Exception {
        // Specify the path to the certificate file
        String certificateFilePath = "certificate.crt";

        // Get the X509Certificate object from the certificate file
        X509Certificate certificate = getCertificate(certificateFilePath);

        // Use the certificate for further processing
        assert certificate != null;
        System.out.println("Certificate Subject: " + certificate.getSubjectDN());
    }

    private static X509Certificate getCertificate(String filePath) {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            return (X509Certificate) certificateFactory.generateCertificate(inputStream);
        } catch (CertificateException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
