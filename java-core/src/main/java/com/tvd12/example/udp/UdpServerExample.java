package com.tvd12.example.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdpServerExample {

	public static void main(String[] args) throws Exception {
		DatagramChannel datagramChannel = DatagramChannel.open();
	    datagramChannel.configureBlocking(false);
	    datagramChannel.socket().bind(new InetSocketAddress("0.0.0.0", 2612));
	    datagramChannel.socket().setReuseAddress(true);
		Selector udpSelector = Selector.open();
		datagramChannel.register(udpSelector, SelectionKey.OP_READ);
		DatagramReader reader = new DatagramReader(udpSelector);
		reader.init();
	}

	public static class DatagramReader implements Runnable {
		private final Logger logger;
		private final Thread readerThread;
		private Selector udpSelector;
		private volatile boolean isActive = false;
		private volatile int readBytes = 0;

		public DatagramReader(Selector udpSelector) {
			this.udpSelector = udpSelector;
			logger = LoggerFactory.getLogger(getClass());
			readerThread = new Thread(this, "DatagramReader");
		}

		public void init() {
			if (isActive) {
				throw new IllegalArgumentException("has already destroyed!");
			}
			isActive = true;
			readerThread.start();
		}

		public void destroy(Object o) {
			isActive = false;
			try {
				Thread.sleep(500L);

				udpSelector.close();
			} catch (Exception e) {
				logger.warn("destroy error", e);
			}
		}

		public void run() {
			ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);

			while (isActive) {
				try {
					readIncomingDatagrams(readBuffer);

				} catch (Throwable t) {
					logger.warn("Problems in DatagramReader main loop: " + t);
				}
			}

		}

		private void readIncomingDatagrams(ByteBuffer readBuffer) {
			DatagramChannel chan = null;
			SelectionKey key = null;

			try {
				udpSelector.select();
				System.out.println("hello");
				Iterator<SelectionKey> selectedKeys = udpSelector.selectedKeys().iterator();
				while (selectedKeys.hasNext()) {
					try {
						key = (SelectionKey) selectedKeys.next();
						selectedKeys.remove();

						if (key.isValid()) {

							if (key.isReadable()) {
								readBuffer.clear();
								chan = (DatagramChannel) key.channel();
								readPacket(chan, readBuffer);
							}
						}
					} catch (IOException e) {
						logger.warn(

						        String.format(

						                "read data error",
						                new Object[] { chan.toString(), e.toString() }));
					}

				}

			} catch (ClosedSelectorException e) {
				logger.debug("ClosedSelectorException");

			} catch (CancelledKeyException localCancelledKeyException) {
			} catch (IOException ioe) {

				logger.warn("IOException", ioe);

			} catch (Exception err) {
				logger.warn("Exception", err);
			}
		}

		private void readPacket(DatagramChannel chan, ByteBuffer readBuffer) throws IOException {
			long byteCount = 0L;
			InetSocketAddress address = (InetSocketAddress) chan.receive(readBuffer);

			if (address != null) {

				byteCount = readBuffer.position();

				if (byteCount > 0L) {

					readBytes += byteCount;

					readBuffer.flip();

					byte[] binaryData = new byte[readBuffer.limit()];
					readBuffer.get(binaryData);

					System.out.println("received: " + new String(binaryData));
				}
			} else {
				logger.info("read error", chan);
			}
		}

		public long getReadBytes() {
			return readBytes;
		}

	}
}
