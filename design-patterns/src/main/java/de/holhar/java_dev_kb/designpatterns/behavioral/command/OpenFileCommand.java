package de.holhar.java_dev_kb.designpatterns.behavioral.command;

public class OpenFileCommand implements Command {

    private final FileSystemReceiver fileSystemReceiver;

    public OpenFileCommand(FileSystemReceiver fileSystemReceiver) {
        this.fileSystemReceiver = fileSystemReceiver;
    }

    @Override
    public void execute() {
        // Open command is forwarding request to openFile method
        this.fileSystemReceiver.openFile();
    }
}
