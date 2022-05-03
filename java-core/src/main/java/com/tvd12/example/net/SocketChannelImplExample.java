package com.tvd12.example.net;

import java.nio.channels.SocketChannel;

public class SocketChannelImplExample {
    public static void main(String[] args) throws Exception {
        System.out.println(-9 % 2);
        SocketChannel socketChannel = SocketChannel.open();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; ++i) {
            socketChannel.hashCode();
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println(elapsed);
    }
}
