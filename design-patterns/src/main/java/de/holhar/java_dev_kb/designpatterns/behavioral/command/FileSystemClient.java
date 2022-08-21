package de.holhar.java_dev_kb.designpatterns.behavioral.command;

public class FileSystemClient {

    public static void main(String[] args) {
        // Creating the receiver object
        FileSystemReceiver fs = FileSystemReceiverUtil.getUnderlyingFileSystem();

        // Creating command and associating with receiver
        OpenFileCommand openFileCommand = new OpenFileCommand(fs);

        // Creating invoker and associating with command
        FileInvoker fileInvoker = new FileInvoker(openFileCommand);

        // Perform action on invoker object
        fileInvoker.execute();

        WriteFileCommand writeFileCommand = new WriteFileCommand(fs);
        fileInvoker = new FileInvoker(writeFileCommand);
        fileInvoker.execute();

        CloseFileCommand closeFileCommand = new CloseFileCommand(fs);
        fileInvoker = new FileInvoker(closeFileCommand);
        fileInvoker.execute();
    }
}
