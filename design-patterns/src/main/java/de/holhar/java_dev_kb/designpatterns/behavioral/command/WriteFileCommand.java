package de.holhar.java_dev_kb.designpatterns.behavioral.command;

public class WriteFileCommand implements Command {

    private final FileSystemReceiver fileSystemReceiver;

    public WriteFileCommand(FileSystemReceiver fileSystemReceiver) {
        this.fileSystemReceiver = fileSystemReceiver;
    }

    @Override
    public void execute() {
        // Write command is forwarding request to writeFile method
        this.fileSystemReceiver.writeFile();
    }
}
