package de.holhar.java_dev_kb.training.ocp8.special_interest.patterns;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * @author hhs@dasburo.com
 */
public class ForkJoinActionExample extends RecursiveAction {

    private int start;
    private int end;
    private Double[] weights;

    public ForkJoinActionExample(Double[] weights, int start, int end) {
        this.weights = weights;
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<?> task = new ForkJoinActionExample(weights, 0, weights.length);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);

        System.out.println("Weighed all animals");
    }

    @Override
    public void compute() {
        if (end - start <= 3) {
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                System.out.println("Weighed an animal no. " + i + " with: " + weights[i]);
            }
        } else {
            int middle = start + ((end - start) / 2);
            System.out.println("start[" + start + "], middle[" + middle + "], end[" + end + "]");
            invokeAll(new ForkJoinActionExample(weights, start, middle),
                    new ForkJoinActionExample(weights, middle, end));
        }
    }
}
