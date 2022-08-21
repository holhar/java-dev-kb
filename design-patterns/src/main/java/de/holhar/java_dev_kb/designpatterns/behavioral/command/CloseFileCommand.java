package de.holhar.java_dev_kb.designpatterns.behavioral.command;

public class CloseFileCommand implements Command {

    private final FileSystemReceiver fileSystemReceiver;

    public CloseFileCommand(FileSystemReceiver fileSystemReceiver) {
        this.fileSystemReceiver = fileSystemReceiver;
    }

    @Override
    public void execute() {
        // Close command is forwarding request to closeFile method
        this.fileSystemReceiver.closeFile();
    }
}
