package de.holhar.java_dev_kb.training.ocp8.ch08_io.sec03_streams_usage.subsec02_bufferedinputstream_bufferedoutputstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getIoHome;

public class CopyBufferFileSample {

    public static void copy(File source, File destination) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read()) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
                ;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File source = new File(getIoHome() + "buecher-liste.txt");
        File destination = new File(getIoHome() + "buecher-liste-kopie.txt");
        copy(source, destination);
    }
}
