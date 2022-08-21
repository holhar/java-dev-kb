package de.holhar.java_dev_kb.designpatterns.behavioral.command;

public interface FileSystemReceiver {
    void openFile();
    void writeFile();
    void closeFile();
}
