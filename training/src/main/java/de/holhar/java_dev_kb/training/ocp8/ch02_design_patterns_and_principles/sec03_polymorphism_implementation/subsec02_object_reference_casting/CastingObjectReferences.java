package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec03_polymorphism_implementation.subsec02_object_reference_casting;

import de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec03_polymorphism_implementation.subsec01_object_and_reference_distinguish.Lemur;
import de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec03_polymorphism_implementation.subsec01_object_and_reference_distinguish.Primate;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 *
 * Basic rules to keep in mind when casting variables:
 * 1. Casting an object from a subclass to a superclass doesn't require an explicit cast.
 * 2. Casting an object from a superclass to a subclass requires an explicit cast.
 * 3. The compiler will not allow casts to unrelated types.
 * 4. Even when the code compiles without issue, an exception may be thrown at runtime if the object being cast is not
 *    actually an instance of that class.
 */
public class CastingObjectReferences {

    public static void main(String[] args) {
        Lemur lemur = new Lemur();

        // See rule 1
        Primate primate = lemur;

        // See rule 2
        //Lemur lemur2 = primate; // DOES NOT COMPILE
        Lemur lemur3 = (Lemur)primate;

        println(lemur3.age);

        // See rule 3
        Fish fish = new Fish();
        // Bird bird = (Fish)bird; // DOES NOT COMPILE

        // See rule 4
        Rodent rodent = new Rodent();
        Capybara capybara = (Capybara)rodent; // Throws ClassCastException at runtime
        // |-> Casting will fail, as the object being referenced is not of type Capybara!!!
        // |-> That's why we need an instanceof check

        Capybara capybara1 = null;
        if (rodent instanceof Capybara) {
            capybara1 = (Capybara)rodent;
        } else {
            println("We can't cast here...");
        }
    }
}
class Bird {}
class Fish {}
class Rodent {}
class Capybara extends Rodent {}