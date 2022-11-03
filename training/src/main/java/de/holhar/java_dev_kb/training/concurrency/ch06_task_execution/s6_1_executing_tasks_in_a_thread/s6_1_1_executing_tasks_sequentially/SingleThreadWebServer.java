package de.holhar.java_dev_kb.training.concurrency.ch06_task_execution.s6_1_executing_tasks_in_a_thread.s6_1_1_executing_tasks_sequentially;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Sequential web server.
 */
public class SingleThreadWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }

    private static void handleRequest(Socket connection) {
        // ...
    }
}
