package com.tvd12.example.ssl;

import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.security.KeyStore;

public class SimpleSocketServerWithSSL {

    public static void main(String[] args) throws Exception {
        int port = 8443; // Cổng sử dụng cho SSL Socket
        String keystoreFile = "keystore.jks"; // Đường dẫn tới file keystore
        String keystorePassword = "secret"; // Mật khẩu của keystore

        // Khởi tạo SSL Context từ file keystore
        SSLContext sslContext = createSSLContext(keystoreFile, keystorePassword);

        // Tạo một SSL Server Socket
        SSLServerSocketFactory socketFactory = sslContext.getServerSocketFactory();
        SSLServerSocket serverSocket = (SSLServerSocket) socketFactory.createServerSocket(port);

        System.out.println("listening ...");
        // Chấp nhận các kết nối từ client
        while (true) {
            Socket clientSocket = serverSocket.accept();
            // Xử lý kết nối từ client
            handleClientConnection(clientSocket);
        }
    }

    // Xử lý kết nối từ client
    private static void handleClientConnection(Socket socket) throws IOException {
        // Đọc dữ liệu từ client
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String requestData = reader.readLine();
        System.out.println(requestData);

        // Xử lý yêu cầu từ client
        String responseData = "Hello, Client!";

        // Gửi dữ liệu phản hồi về client
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write(responseData);
        writer.newLine();
        writer.flush();

        // Đóng kết nối với client
        socket.close();
    }

    // Tạo SSL Context từ keystore file
    private static SSLContext createSSLContext(String keystoreFile, String keystorePassword) throws Exception {
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

