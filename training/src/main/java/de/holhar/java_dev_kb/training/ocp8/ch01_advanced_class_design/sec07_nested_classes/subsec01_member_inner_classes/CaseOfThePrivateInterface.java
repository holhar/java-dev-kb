package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec07_nested_classes.subsec01_member_inner_classes;

public class CaseOfThePrivateInterface {

    private interface Secret {
        public void shh();
    }

    class DontTell implements Secret {
        public void shh() {
        }
    }
}
