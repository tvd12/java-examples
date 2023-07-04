package com.tvd12.example.ssl;

import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;

public class SSLClientExample {
    public static void main(String[] args) {
        try {
            // Tạo SSLContext với TrustManager mặc định
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { new SimpleX509TrustManager() }, null);

            // Tạo SSLSocketFactory từ SSLContext
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();

            // Kết nối tới server qua SSL/TLS
            Socket socket = socketFactory.createSocket("127.0.0.1", 8443);

            // Gửi và nhận dữ liệu qua socket
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.write("Hello, server!");
            writer.newLine();
            writer.flush();

            String response = reader.readLine();
            System.out.println("Server response: " + response);

            // Đóng kết nối
            writer.close();
            reader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
