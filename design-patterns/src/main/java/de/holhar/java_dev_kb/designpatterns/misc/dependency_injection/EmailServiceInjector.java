package de.holhar.java_dev_kb.designpatterns.misc.dependency_injection;

public class EmailServiceInjector implements MessageServiceInjector {

    @Override
    public Consumer getConsumer() {
        MyDIApplication diApplication = new MyDIApplication(new EmailService());
        // Alternative - Setter DI:
        //diApplication.setMessageService(new EmailService());
        return diApplication;
    }
}
