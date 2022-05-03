package com.tvd12.example.udp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class UdpClientExample {

    public static void main(String[] args) throws Exception {
        InetSocketAddress myAddress = new InetSocketAddress("127.0.0.1", 2612);
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(null);
        datagramChannel.connect(myAddress);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (true) {
                        byte header = 0;
                        header |= 1 << 5;
                        buffer.put(header);
                        byte[] content = "hello world".getBytes();
                        buffer.putShort((short) (8 + 2 + content.length));
                        buffer.putLong(12345L);
                        buffer.putShort((short) content.length);
                        buffer.put(content);
                        buffer.flip();
                        datagramChannel.write(buffer);
//						datagramChannel.send(buffer, myAddress);
                        buffer.clear();
                        Thread.sleep(1000);
                    }
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        })
            .start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    ByteBuffer buf = ByteBuffer.allocate(48);

                    while (true) {
                        try {
                            buf.clear();
                            datagramChannel.receive(buf);
                            if (buf.position() > 0) {
                                byte[] bytes = new byte[buf.position()];
                                buf.get(bytes);
                                System.out.println("rev: " + Arrays.toString(bytes));
                            } else {
                                break;
                            }
                        } catch (ClosedChannelException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    System.out.println("datagram stop");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        })
            .start();
        Thread.sleep(3000);
        System.out.println("call disconnect");
        datagramChannel.close();
        System.out.println("call disconnect ok");
        Thread.sleep(1000);

        System.out.println("continue send");

        DatagramChannel datagramChannel1 = DatagramChannel.open();
        datagramChannel1.bind(null);
        datagramChannel1.connect(myAddress);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (true) {
                        byte header = 0;
                        header |= 1 << 5;
                        buffer.put(header);
                        byte[] content = "hello world".getBytes();
                        buffer.putShort((short) (8 + 2 + content.length));
                        buffer.putLong(12345L);
                        buffer.putShort((short) content.length);
                        buffer.put(content);
                        buffer.flip();
                        datagramChannel1.send(buffer, myAddress);
                        buffer.clear();
                        Thread.sleep(1000);
                    }
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        })
            .start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    ByteBuffer buf = ByteBuffer.allocate(48);

                    while (true) {
                        try {
                            buf.clear();
                            datagramChannel1.receive(buf);
                            if (buf.position() > 0) {
                                byte[] bytes = new byte[buf.position()];
                                buf.get(bytes);
                                System.out.println("rev: " + Arrays.toString(bytes));
                            } else {
                                break;
                            }
                        } catch (ClosedChannelException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    System.out.println("datagram stop");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        })
            .start();
    }

}
