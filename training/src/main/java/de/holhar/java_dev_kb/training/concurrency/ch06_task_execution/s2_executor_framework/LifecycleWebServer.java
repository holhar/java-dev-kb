package de.holhar.java_dev_kb.training.concurrency.ch06_task_execution.s2_executor_framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * Web server with shutdown support.
 */
public class LifecycleWebServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LifecycleWebServer.class);

    private static final int NTHREADS = 100;
    private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);

    public void start() throws IOException {
        final ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()) {
            try {
                final Socket connection = socket.accept();
                exec.execute(() -> handleRequest(connection));
            } catch (RejectedExecutionException e) {
                if (!exec.isShutdown()) {
                    LOGGER.error("task submission rejected", e);
                }
            }
        }
    }

    private void stop() {
        exec.shutdown();
    }

    void handleRequest(Socket connection) {
        final HttpServletRequest request = readRequest(connection);
        if (isShutdownRequest(request)) {
            stop();
        } else {
            dispatchRequest(request);
        }
    }

    private HttpServletRequest readRequest(Socket connection) {
        // ...
        return null;
    }

    private boolean isShutdownRequest(HttpServletRequest request) {
        // ...
        return false;
    }

    private void dispatchRequest(HttpServletRequest request) {
        // ...
    }
}
