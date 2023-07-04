package com.tvd12.example.ssl;

import com.sun.net.httpserver.*;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.KeyStore;

public class SimpleHttpServerWithSSL {

    public static void main(String[] args) throws Exception {
        int port = 8443; // Cổng sử dụng cho HTTPS
        String keystoreFile = "keystore.jks"; // Đường dẫn tới file keystore
        String keystorePassword = "123456"; // Mật khẩu của keystore

        // Khởi tạo SSL Context từ file keystore
        SSLContext sslContext = createSSLContext(keystoreFile, keystorePassword);

        // Tạo một HttpsServer
        HttpsServer server = HttpsServer.create(new InetSocketAddress(port), 0);
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext));

        // Đăng ký một HttpContext với đường dẫn "/hello" để xử lý request
        server.createContext("/hello", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "Hello, World!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                exchange.getResponseBody().write(response.getBytes());
                exchange.close();
            }
        });

        // Bắt đầu server
        server.start();
        System.out.println("Server is running on port " + port);
    }

    // Tạo SSL Context từ keystore file
    private static SSLContext createSSLContext(
        String keystoreFile,
        String keystorePassword
    ) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(keystoreFile), keystorePassword.toCharArray());

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, keystorePassword.toCharArray());

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        trustManagerFactory.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        return sslContext;
    }
}
