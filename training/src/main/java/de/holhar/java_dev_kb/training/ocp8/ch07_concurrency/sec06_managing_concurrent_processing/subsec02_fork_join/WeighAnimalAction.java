package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec06_managing_concurrent_processing.subsec02_fork_join;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class WeighAnimalAction extends RecursiveAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeighAnimalAction.class);

    /*
     * The arguments on which the ForkJoinTask will operate.
     */
    private int start;
    private int end;
    private Double[] weights;

    public WeighAnimalAction(Double[] weights, int start, int end) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];

        // 1. Create ForkJoinTask (although RecursiveAction does not return a value the code compiles, as we're using
        // generic ForkJoinTask reference)
        ForkJoinTask<?> task = new WeighAnimalAction(weights, 0, weights.length);

        // 2. Create ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        // 3. Start the ForkJoinTask with a single task object
        pool.invoke(task);

        println("");
        println("Weights: ");
        Arrays.asList(weights).forEach(weight -> LOGGER.debug("{}", weight));
    }

    /*
     * Define ForkJoinTask implementing the recursive process by overriding the compute() method - as we're extending
     * RecursiveAction, the compute() method's return type is void and corresponds to Runnable's run() method.
     */
    @Override
    protected void compute() {
        // Define base case: 'we weigh animals if there are at most three left in the set'.
        if (end - start <= 3) {
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                println("Animal weighed: " + i);
            }
            // Define recursive case: 'split the work from one WeighAnimalAction object into two instances by dividing
            // the available indices between the two objects'.
        } else {
            int middle = start + ((end - start) / 2);
            println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");
            invokeAll(new WeighAnimalAction(weights, start, middle),
                    new WeighAnimalAction(weights, middle, end));
        }
    }
}
