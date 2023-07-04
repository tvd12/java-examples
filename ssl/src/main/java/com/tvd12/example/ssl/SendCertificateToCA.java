package com.tvd12.example.ssl;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SendCertificateToCA {

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = loadKeyPair();
        X509Certificate certificate = loadCertificate();
        byte[] csrBytes = generateCSR(keyPair, certificate);
        saveCSRToFile(csrBytes, "certificate_signing_request.csr");
        String signedCertificate = submitCSRToCA(csrBytes, keyPair.getPrivate());
        saveSignedCertificateToFile(signedCertificate, "signed_certificate.crt");
    }

    private static KeyPair loadKeyPair() throws Exception {
        PrivateKey privateKey = loadPrivateKey("private_key.pem");
        PublicKey publicKey = loadPublicKey("public_key.pem");
        return new KeyPair(publicKey, privateKey);
    }

    private static PrivateKey loadPrivateKey(
        String filePath
    ) throws Exception {
        byte[] keyBytes = readKeyBytesFromFile(filePath);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    private static PublicKey loadPublicKey(
        String filePath
    ) throws Exception {
        byte[] keyBytes = readKeyBytesFromFile(filePath);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private static byte[] readKeyBytesFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }

    private static X509Certificate loadCertificate() throws Exception {
        try (InputStream inputStream = Files.newInputStream(Paths.get("certificate.crt"))) {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            return (X509Certificate) certificateFactory.generateCertificate(inputStream);
        }
    }

    private static byte[] generateCSR(
        KeyPair keyPair,
        X509Certificate certificate
    ) throws Exception {
        X500Name subject = new X500Name(certificate.getSubjectX500Principal().getName());
        PKCS10CertificationRequestBuilder builder = new JcaPKCS10CertificationRequestBuilder(
                subject,
                certificate.getPublicKey()
        );
        ContentSigner signer = new JcaContentSignerBuilder("SHA256withRSA").build(keyPair.getPrivate());
        PKCS10CertificationRequest csr = builder.build(signer);
        return csr.getEncoded();
    }

    private static void saveCertificateToFile(
        X509Certificate certificate,
        String filePath
    ) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(certificate.getEncoded());
        }
    }

    private static void saveCSRToFile(
        byte[] csrBytes,
        String filePath
    ) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(csrBytes);
        }
    }

    private static String submitCSRToCA(
        byte[] csrBytes,
        PrivateKey privateKey
    ) throws Exception {
        // Set up the connection to the CA
        URL caUrl = new URL("https://ca.example.com/sign"); // Replace with the CA's URL
        HttpURLConnection connection = (HttpURLConnection) caUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Set the appropriate headers
        connection.setRequestProperty("Content-Type", "application/pkcs10");
        connection.setRequestProperty("Content-Length", String.valueOf(csrBytes.length));

        // Sign the CSR with the private key
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(csrBytes);
        byte[] signatureBytes = signature.sign();

        // Encode the CSR and signature as Base64
        String encodedCSR = Base64
            .getEncoder()
            .encodeToString(csrBytes);
        String encodedSignature = Base64
            .getEncoder()
            .encodeToString(signatureBytes);

        // Build the request body with the CSR and signature
        String requestBody = "csr=" + encodedCSR + "&signature=" + encodedSignature;

        // Send the request
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
        }

        // Read the response from the CA
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            return responseBuilder.toString();
        }
    }

    private static void saveSignedCertificateToFile(
        String certificate,
        String filePath
    ) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(certificate.getBytes(StandardCharsets.UTF_8));
        }
    }
}

