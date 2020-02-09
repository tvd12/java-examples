package com.tvd12.example.common.udp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UdpClientExample {

	public static void main(String[] args) throws Exception {
		InetSocketAddress myAddress = new InetSocketAddress("127.0.0.1", 9999);
		DatagramChannel datagramChannel = DatagramChannel.open();
		datagramChannel.bind(null);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put("hello world".getBytes());
		buffer.flip();
		datagramChannel.send(buffer, myAddress);
		buffer.clear();
	}
	
}
