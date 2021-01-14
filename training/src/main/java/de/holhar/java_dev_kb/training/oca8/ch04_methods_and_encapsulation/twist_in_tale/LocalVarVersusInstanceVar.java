package de.holhar.java_dev_kb.training.oca8.ch04_methods_and_encapsulation.twist_in_tale;

class TestPhone {
    public static void main(String[] args) {
        Phone p1 = new Phone();
        p1.setPhoneNumber();
        // Will print '123456789'
        System.out.println(p1.phoneNumber);
    }
}

class Phone {
    String phoneNumber = "123456789";

    void setPhoneNumber() {
        String phoneNumber;
        phoneNumber = "987654321";
    }
}
