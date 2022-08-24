package de.holhar.java_dev_kb.designpatterns.misc.dependency_injection;

public class MyMessageDIClient {

    public static void main(String[] args) {
        String msg = "Hello World!";
        String email = "max@power.com";
        String phone = "555-13245";

        MessageServiceInjector injector = null;
        Consumer app = null;

        // Send mail
        injector = new EmailServiceInjector();
        app = injector.getConsumer();
        app.processMessages(msg, email);

        // Send SMS
        injector = new SmsServiceInjector();
        app = injector.getConsumer();
        app.processMessages(msg, phone);
    }
}
