package com.tvd12.example.ssl;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.Set;

public class SimpleSocketServerWithSSLAndChannel {

    public static void main(String[] args) throws Exception {
        int port = 8443; // Port for SSL Socket
        String keystoreFile = "keystore.jks"; // Path to keystore file
        String keystorePassword = "secret"; // Keystore password

        // Create SSL Context from keystore file
        SSLContext sslContext = createSSLContext(keystoreFile, keystorePassword);

        // Create a Selector
        Selector selector = Selector.open();

        // Create a ServerSocketChannel and configure it
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        // Start listening for client connections on the specified port
        ServerSocket serverSocket = serverChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));

        // Register the ServerSocketChannel with the Selector to accept new connections
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Listening for connections...");

        // Main loop to handle events from clients
        while (true) {
            // Check for events from clients
            selector.select();

            // Get the list of selected keys ready for processing
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    // If a new connection is ready, handle the connection
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = channel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // If there is data ready to read, handle the data
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    handleClientData(clientChannel, sslContext);
                    clientChannel.close(); // Close the client channel after handling data
                }
            }
        }
    }

    private static void handleClientData(SocketChannel channel, SSLContext sslContext) throws IOException {
        try {
            // Create an SSLEngine for the client connection
            SSLEngine sslEngine = sslContext.createSSLEngine();
            sslEngine.setUseClientMode(false);
//            sslEngine.setNeedClientAuth(true);
            sslEngine.beginHandshake();

            // Wrap the SocketChannel with the SSLEngine to perform the SSL handshake
            channel = wrapSocketChannel(channel, sslEngine);

            // Read data from the client
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = channel.read(buffer);
            if (bytesRead > 0) {
                buffer.flip();
                byte[] requestData = new byte[buffer.remaining()];
                buffer.get(requestData);

                // Process client request
                String requestDataString = new String(requestData);
                System.out.println("Received request: " + requestDataString);

                // Prepare response data
                String responseData = "Hello, Client!";
                ByteBuffer responseBuffer = ByteBuffer.wrap(responseData.getBytes());

                // Write response data back to the client
                while (responseBuffer.hasRemaining()) {
                    channel.write(responseBuffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SocketChannel wrapSocketChannel(SocketChannel channel, SSLEngine sslEngine) throws Exception {
        // Create the buffers for SSL encryption/decryption
        int appBufferMax = sslEngine.getSession().getApplicationBufferSize();
        ByteBuffer appBuffer = ByteBuffer.allocate(appBufferMax);
        int netBufferMax = sslEngine.getSession().getPacketBufferSize();
        ByteBuffer netBuffer = ByteBuffer.allocate(netBufferMax);

        // Wrap the SocketChannel with the SSLEngine
        SSLEngineResult.HandshakeStatus handshakeStatus = SSLEngineResult.HandshakeStatus.NEED_WRAP;
        while (handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED
            && handshakeStatus != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            Thread.sleep(3);
            switch (handshakeStatus) {
                case NEED_WRAP:
                    netBuffer.clear();
                    SSLEngineResult result = sslEngine.wrap(appBuffer, netBuffer);
                    handshakeStatus = result.getHandshakeStatus();
                    netBuffer.flip();
                    while (netBuffer.hasRemaining()) {
                        channel.write(netBuffer);
                    }
                    break;
                case NEED_UNWRAP:
                    appBuffer.clear();
                    int bytesRead = channel.read(appBuffer);
                    if (bytesRead > 0) {
                        appBuffer.flip();
                        SSLEngineResult unwrapResult = sslEngine.unwrap(appBuffer, netBuffer);
                        handshakeStatus = unwrapResult.getHandshakeStatus();
                    } else {
                        handshakeStatus = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
                    }
                    break;
                case NEED_TASK:
                    Runnable task;
                    while ((task = sslEngine.getDelegatedTask()) != null) {
                        task.run();
                    }
                    handshakeStatus = sslEngine.getHandshakeStatus();
                    break;
                default:
                    break;
            }
        }

        return channel;
    }

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
