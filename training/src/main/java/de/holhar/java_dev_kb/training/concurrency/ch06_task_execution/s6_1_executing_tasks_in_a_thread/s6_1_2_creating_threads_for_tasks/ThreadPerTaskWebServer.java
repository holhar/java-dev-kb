package de.holhar.java_dev_kb.training.concurrency.ch06_task_execution.s6_1_executing_tasks_in_a_thread.s6_1_2_creating_threads_for_tasks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Web server that starts a new thread for each request.
 */
public class ThreadPerTaskWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = () -> handleRequest(connection);
            new Thread(task).start();
        }
    }

    private static void handleRequest(Socket connection) {
        // ...
    }
}
