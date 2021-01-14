package de.holhar.java_dev_kb.training.ocp8.ch08_io.sec03_streams_usage.subsec05_printstream_printwriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getIoHome;

public class PrintWriterSample {

    public static void main(String[] args) throws IOException {
        File source = new File(getIoHome() + "zoo.log");

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(source)))) {
            out.print("Today's weather is: ");
            out.println("Sunny");
            out.print("Today's temperature at the zoo is: ");
            out.print(1 / 3.0);
            out.println('C');
            out.format("It has rained 10.12 inches this year");
            out.println();
            out.printf("It may rain 21.2 more inches this year");
        }
    }
}
