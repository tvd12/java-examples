package com.tvd12.example.ssl;

import javax.net.ssl.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class SSLSocketChannelClient {
    private static final String SERVER_HOST = "127.0.01";
    private static final int SERVER_PORT = 3005;

    public static void main(String[] args) {
        try {
            // Tạo SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // TrustManager để xác thực chứng chỉ
            TrustManager[] trustManagers = { new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    // Không thực hiện xác thực client
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    // Không thực hiện xác thực server
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            } };

            // Khởi tạo SSLContext với TrustManager
            sslContext.init(null, trustManagers, new SecureRandom());

            // Tạo SSLEngine
            SSLEngine sslEngine = sslContext.createSSLEngine(SERVER_HOST, SERVER_PORT);
            sslEngine.setUseClientMode(true);

            // Tạo SocketChannel
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(true);
            socketChannel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));

            // Kết nối SSL với SocketChannel
            sslEngine.beginHandshake();
            doHandshake(socketChannel, sslEngine);

            // Gửi và nhận dữ liệu từ server
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketChannel.socket().getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socketChannel.socket().getInputStream()));

            // Gửi dữ liệu tới server
            writer.write("Hello server!");
            writer.newLine();
            writer.flush();

            // Nhận dữ liệu từ server
            String response = reader.readLine();
            System.out.println("Server response: " + response);

            // Đóng kết nối
            socketChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Thực hiện SSL handshake trên SocketChannel
    private static void doHandshake(SocketChannel socketChannel, SSLEngine sslEngine) throws IOException {
        ByteBuffer inBuffer = ByteBuffer.allocate(8192);
        ByteBuffer outBuffer = ByteBuffer.allocate(8192);

        SSLEngineResult.HandshakeStatus handshakeStatus = sslEngine.getHandshakeStatus();
        while (handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED &&
            handshakeStatus != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            switch (handshakeStatus) {
                case NEED_WRAP:
                    outBuffer.clear();
                    SSLEngineResult result = sslEngine.wrap(inBuffer, outBuffer);
                    outBuffer.flip();
                    while (outBuffer.hasRemaining()) {
                        socketChannel.write(outBuffer);
                    }
                    handshakeStatus = result.getHandshakeStatus();
                    break;
                case NEED_UNWRAP:
                    inBuffer.clear();
                    int bytesRead = socketChannel.read(inBuffer);
                    if (bytesRead <= 0) {
                        throw new IOException("Failed to read data from SocketChannel.");
                    }
                    inBuffer.flip();
                    SSLEngineResult unwrapResult = sslEngine.unwrap(inBuffer, inBuffer);
                    handshakeStatus = unwrapResult.getHandshakeStatus();
                    break;
                case NEED_TASK:
                    Runnable task;
                    while ((task = sslEngine.getDelegatedTask()) != null) {
                        task.run();
                    }
                    handshakeStatus = sslEngine.getHandshakeStatus();
                    break;
                default:
                    handshakeStatus = sslEngine.getHandshakeStatus();
                    break;
            }
        }
    }
}

