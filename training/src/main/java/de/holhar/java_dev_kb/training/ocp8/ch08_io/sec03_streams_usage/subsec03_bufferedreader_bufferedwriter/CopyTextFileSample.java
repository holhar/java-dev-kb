package de.holhar.java_dev_kb.training.ocp8.ch08_io.sec03_streams_usage.subsec03_bufferedreader_bufferedwriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getIoHome;
import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class CopyTextFileSample {

    public static List<String> readFile(File source) throws IOException {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String s;
            while ((s = reader.readLine()) != null) {
                data.add(s);
            }
        }
        return data;
    }

    public static void writeFile(File destination, List<String> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
            for (String s : data) {
                writer.write(s);
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File source = new File(getIoHome() + "buecher-liste.txt");
        File destination = new File(getIoHome() + "buecher-liste-kopie.txt");
        List<String> data = readFile(source);
        for (String s : data) {
            println(s);
        }
        writeFile(destination, data);
    }
}
