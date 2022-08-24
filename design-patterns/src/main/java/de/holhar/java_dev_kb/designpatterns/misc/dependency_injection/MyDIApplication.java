package de.holhar.java_dev_kb.designpatterns.misc.dependency_injection;

public class MyDIApplication implements Consumer {

    private MessageService messageService;

    // Constructor dependency injection
    public MyDIApplication(MessageService messageService) {
        this.messageService = messageService;
    }

    // Setter dependency injection
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void processMessages(String msg, String rec) {
        // So some msg validation, manipulation logic etc.
        this.messageService.sendMessage(msg, rec);
    }
}
