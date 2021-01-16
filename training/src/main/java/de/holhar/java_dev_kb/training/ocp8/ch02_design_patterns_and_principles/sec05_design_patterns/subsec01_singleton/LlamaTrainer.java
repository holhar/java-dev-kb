package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec01_singleton;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class LlamaTrainer {

    public boolean feedLlamas(int numberOfLlamas) {
        int amountNeeded = 5 * numberOfLlamas;
        HayStorage hayStorage = HayStorage.getInstance();
        if (hayStorage.getHayQuantity() < amountNeeded) {
            hayStorage.addHay(amountNeeded + 10);
        }
        boolean fed = hayStorage.removeHay(amountNeeded);
        if (fed)
            println("Llamas have been fed");
        return fed;
    }
}
