package com.tvd12.example.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class InputStreamReset2 {

    public static void main(String[] args) throws Exception {
        InputStream is = null;

        try {
            // new input stream created
            is = new BufferedInputStream(new FileInputStream("files/input_stream_reset.txt"));

            System.out.println("Characters printed:");
            // create new buffered reader

            // reads and prints BufferedReader
            is.mark(0);
            System.out.println((char) is.read());
            System.out.println((char) is.read());

            // mark invoked at this position
            System.out.println("mark() invoked");
            System.out.println((char) is.read());
            System.out.println((char) is.read());

            // reset() repositioned the stream to the mark
            if (is.markSupported()) {
                is.reset();
                System.out.println("reset() invoked");
                System.out.println((char) is.read());
                System.out.println((char) is.read());
            } else {
                System.out.print("InputStream does not support reset()");
            }

        } catch (Exception e) {
            // if any I/O error occurs
            e.printStackTrace();
        } finally {
            // releases system resources associated with this stream
            if (is != null) {
                is.close();
            }
        }
    }

}
