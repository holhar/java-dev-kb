package de.holhar.java_dev_kb.designpatterns.misc.dependency_injection;

public class SmsServiceInjector implements MessageServiceInjector {

    @Override
    public Consumer getConsumer() {
        MyDIApplication diApplication = new MyDIApplication(new SmsService());
        // Alternative - Setter DI:
        //diApplication.setMessageService(new SmsService());
        return diApplication;
    }
}
