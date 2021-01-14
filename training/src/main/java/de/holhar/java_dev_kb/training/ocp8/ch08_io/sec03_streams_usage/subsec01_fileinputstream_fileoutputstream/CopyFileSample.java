package de.holhar.java_dev_kb.training.ocp8.ch08_io.sec03_streams_usage.subsec01_fileinputstream_fileoutputstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getIoHome;

public class CopyFileSample {

    public static void copy(File source, File destination) throws IOException {
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(destination)) {
            int b;
            // Read returns -1 if there's no data to be read, i.e. the file has ended
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File source = new File(getIoHome() + "buecher-liste.txt");
        File destination = new File(getIoHome() + "buecher-liste-kopie.txt");
        copy(source, destination);
    }
}
