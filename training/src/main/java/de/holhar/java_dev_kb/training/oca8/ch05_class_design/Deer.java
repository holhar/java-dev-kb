package de.holhar.java_dev_kb.training.oca8.ch05_class_design;

public class Deer {

    public Deer() {
        System.out.print("Deer");
    }

    public Deer(int age) {
        System.out.print("DeerAge");
    }

    public static void main(String[] args) {
        Deer deer = new Reindeer(5);
        // As Deer is the reference class, the hidden 'hasHorns()' gets triggered.
        System.out.println("," + deer.hasHorns());
        // => output is 'DeerReindeer,false'
    }

    // Private method gets hidden, not overridden
    private boolean hasHorns() {
        return false;
    }
}

class Reindeer extends Deer {

    public Reindeer(int age) {
        // Since there is no explicit call to the parent constructor,
        // the default no-argument 'super()' is inserted as the first
        // line of the constructor.
        System.out.print("Reindeer");
    }

    public boolean hasHorns() {
        return true;
    }
}
