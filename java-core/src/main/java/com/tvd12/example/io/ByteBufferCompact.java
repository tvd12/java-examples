package com.tvd12.example.io;

import java.nio.ByteBuffer;

public class ByteBufferCompact {

    public static void main(String[] args) {
        ByteBuffer first = ByteBuffer.allocate(5);
        first.put(new byte[] {1, 2, 3, 4, 5});
        first.flip();
        System.out.println("first.hasRemaining: " + first.hasRemaining());
        first.compact();
        System.out.println("first: " + first);

        ByteBuffer buffer0 = ByteBuffer.allocate(5);
        buffer0.put(new byte[] {1, 2, 3, 4, 5});
        buffer0.flip();
        ByteBuffer buffer = ByteBuffer.allocate(5);
        System.out.println(buffer.position());
        if (buffer.position() > 0) {
            buffer.compact();
        }
        buffer.put(buffer0);
        buffer.flip();
        System.out.println("1: " + buffer);
        buffer.get(new byte[3]);
        System.out.println("2: " + buffer);
        buffer.compact();
        ByteBuffer newBuffer = ByteBuffer.allocate(100);
        newBuffer.flip();
        buffer.put(newBuffer);
        buffer.put(new byte[] {6, 7, 8});
        System.out.println("3: " + buffer + ", limit = " + buffer.limit());
    }
}
