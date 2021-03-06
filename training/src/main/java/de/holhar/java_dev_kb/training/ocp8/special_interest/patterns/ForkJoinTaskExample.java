package de.holhar.java_dev_kb.training.ocp8.special_interest.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTaskExample extends RecursiveTask<Double> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForkJoinTaskExample.class);

    private Double[] weights;
    private int start;
    private int end;

    public ForkJoinTaskExample(Double[] weights, int start, int end) {
        this.weights = weights;
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<Double> task = new ForkJoinTaskExample(weights, 0, weights.length);
        ForkJoinPool pool = new ForkJoinPool();
        Double sum = pool.invoke(task);

        LOGGER.debug("Weighed all animals: {}", sum);
    }

    @Override
    public Double compute() {
        Double sum = new Double(0.0d);
        if (end - start <= 3) {
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                LOGGER.debug("Weighed animal no. {}, with weight: {}", i,  weights[i]);
                sum += weights[i];
            }
        } else {
            int middle = start + ((end - start) / 2);
            ForkJoinTaskExample otherTask = new ForkJoinTaskExample(weights, start, middle);
            otherTask.fork();
            sum += new ForkJoinTaskExample(weights, middle, end).compute() + otherTask.join();
        }
        return sum;
    }
}
