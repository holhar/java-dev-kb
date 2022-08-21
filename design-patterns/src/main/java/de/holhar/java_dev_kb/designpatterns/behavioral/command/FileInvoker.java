package de.holhar.java_dev_kb.designpatterns.behavioral.command;

public class FileInvoker {

    private final Command command;

    public FileInvoker(Command command) {
        this.command = command;
    }

    public void execute() {
        this.command.execute();
    }
}
