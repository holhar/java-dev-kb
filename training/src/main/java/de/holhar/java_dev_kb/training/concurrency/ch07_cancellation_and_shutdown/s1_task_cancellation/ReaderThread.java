package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s1_task_cancellation;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Encapsulating nonstandard cancellation in a Thread by overriding interrupt.
 */
public class ReaderThread extends Thread {
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {
        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1028];
            while (true) {
                int count = in.read(buffer);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buffer, count);
                }
            }
        } catch (IOException e) {
            // Allow thread to exit
        }
    }

    private void processBuffer(byte[] buffer, int count) {
        // ...
    }
}
