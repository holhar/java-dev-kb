package de.holhar.java_dev_kb.training.ocp8.ch09_nio.sec03_file_attributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getNioHome;

public class BasicFileAttributeViewSample {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(getNioHome() + "stream-sources/source-data.txt");

        BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        BasicFileAttributes data = view.readAttributes();

        FileTime loadModifiedTime = FileTime.fromMillis(data.lastModifiedTime().toMillis() + 10_000);

        view.setTimes(loadModifiedTime, null, null);
    }
}
