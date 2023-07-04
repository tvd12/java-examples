package com.tvd12.example.ssl;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class SSLSocketClient {
    public static void main(String[] args) {
        String serverHost = "127.0.0.1";
        int serverPort = 8443;

        try {
            // Tạo SSL Context
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // Khởi tạo KeyStore và TrustManager (nếu cần)
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("keystore.jks"), "123456".toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, "123456".toCharArray());

            // Tạo TrustManager (nếu cần)
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            // Thiết lập SSL Context
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            // Tạo SSL Socket
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(serverHost, serverPort);

            // Thiết lập ciphersuites (nếu cần)
            // String[] enabledCiphers = sslSocket.getSupportedCipherSuites();
            // sslSocket.setEnabledCipherSuites(enabledCiphers);

            // Gửi dữ liệu tới Socket Server
            OutputStream outputStream = sslSocket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println("Hello, Server!");
            printWriter.flush();

            // Đọc dữ liệu phản hồi từ Socket Server
            InputStream inputStream = sslSocket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String response = bufferedReader.readLine();
            System.out.println("Server Response: " + response);

            // Đóng kết nối
            sslSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
