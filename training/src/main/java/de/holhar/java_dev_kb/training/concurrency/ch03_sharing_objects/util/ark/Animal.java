package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.util.ark;

public class Animal {

    public enum Gender {
        MALE, FEMALE
    }

    private final Gender gender;

    public Animal(Gender gender) {
        this.gender = gender;
    }

    public boolean isPotentialMate(Animal a) {
        return !this.gender.equals(a.gender);
    }

    public Gender getGender() {
        return gender;
    }
}
