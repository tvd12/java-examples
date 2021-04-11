package com.tvd12.example.udp;

import java.nio.channels.DatagramChannel;
import java.util.LinkedList;
import java.util.Queue;

public class DatagramChannelPool {
	
	protected final Queue<DatagramChannel> queue;
	
	public DatagramChannelPool() {
		this.queue = new LinkedList<>();
        for (int i = 0; i < 16; ++i) {
        	this.queue.add(newChannel());
        }
	}
	
	public DatagramChannel newChannel() {
		try {
	        DatagramChannel chan = DatagramChannel.open();
	        chan.configureBlocking(false);
	        chan.socket().setReuseAddress(true);
	        return chan;
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
    }
	
	public DatagramChannel get() {
		DatagramChannel last = null;
		synchronized (queue) {
			last = queue.poll();
			queue.offer(last);
		}
		return last;
	}
}
