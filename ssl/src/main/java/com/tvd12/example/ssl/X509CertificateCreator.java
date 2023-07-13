package com.tvd12.example.ssl;

import com.tvd12.ezyfox.util.EzyFileUtil;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.util.Date;

public class X509CertificateCreator {

    public static void main(String[] args) throws Exception {
        try {
            // Generate a new KeyPair for the certificate
            KeyPair keyPair = generateKeyPair();

            // Generate the X.509 certificate
            X509Certificate certificate = generateX509Certificate(keyPair);

            // Save the certificate to a file
            saveCertificateToFile(certificate, "certificate.crt");

            System.out.println("X.509 certificate created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        EzyFileUtil.createFileIfNotExists(new File("private_key.pem"));
        EzyFileUtil.createFileIfNotExists(new File("public_key.pem"));
        Files.write(
            Paths.get("private_key.pem"),
            keyPair.getPrivate().getEncoded()
        );
        Files.write(
            Paths.get("public_key.pem"),
            keyPair.getPublic().getEncoded()
        );
        return keyPair;
    }

    private static X509Certificate generateX509Certificate(
        KeyPair keyPair
    ) throws Exception {
        X509Certificate cert;
        X509v3CertificateBuilder certBuilder;
        ContentSigner contentSigner;

        // Generate a self-signed X.509 certificate
        Instant now = Instant.now();
        certBuilder = new JcaX509v3CertificateBuilder(
            new X500Name("CN=youngmonkeys.org"),
            new BigInteger(64, new SecureRandom()),
            Date.from(now),
            Date.from(now.plusSeconds(365 * 1000 * 24 * 3600L)),
            new X500Name("CN=ezyfox-server"),
            keyPair.getPublic()
        );

        // Sign the certificate using the private key
        contentSigner = new JcaContentSignerBuilder("SHA256WithRSA")
            .build(keyPair.getPrivate());
        cert = new JcaX509CertificateConverter()
            .getCertificate(certBuilder.build(contentSigner));

        return cert;
    }

    private static void saveCertificateToFile(
        X509Certificate certificate,
        String filePath
    ) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(certificate.getEncoded());
        }
    }
}
