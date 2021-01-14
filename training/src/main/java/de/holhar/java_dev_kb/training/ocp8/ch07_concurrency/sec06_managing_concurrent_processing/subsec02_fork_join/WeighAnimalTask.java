package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec06_managing_concurrent_processing.subsec02_fork_join;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * Working with a RecursiveTask
 * <p>
 * Let's say that we want to compute the sum of all weight values while processing the data...
 */
public class WeighAnimalTask extends RecursiveTask<Double> {

    /*
     * The arguments on which the ForkJoinTask will operate.
     */
    private int start;
    private int end;
    private Double[] weights;

    public WeighAnimalTask(Double[] weights, int start, int end) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];

        // 1. Create ForkJoinTask (note the specified type the task incorporates)
        ForkJoinTask<Double> task = new WeighAnimalTask(weights, 0, weights.length);

        // 2. Create ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        // 3. Start the ForkJoinTask with a single task object (note the return value, that corresponds to the
        // defined type of the RecursiveTask
        Double sum = pool.invoke(task);

        println("Sum: " + sum);
    }

    /*
     * Define ForkJoinTask implementing the recursive process by overriding the compute() method - as we're extending
     * RecursiveTask, the compute() method's return type corresponds to the defined generic type of this class
     * implementation, quite similar to Callable's call() method.
     */
    @Override
    protected Double compute() {
        // Define base case: 'we weigh animals if there are at most three left in the set'.
        if (end - start <= 3) {
            double sum = 0;
            for (int i = start; i < end; i++) {
                double weight = (double) new Random().nextInt(100);
                println("Animal weighed: " + i);
                sum += weight;
            }
            // Return the sum
            return sum;
            // Define recursive case:'split the work from one WeighAnimalAction object into two instances by dividing
            // the available indices between the two objects'.
        } else {
            int middle = start + ((end - start) / 2);
            println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");

            RecursiveTask<Double> otherTask = new WeighAnimalTask(weights, start, middle);

            /*
             * Since the invokeAll() method doesn't retrun a value, we instead issue a fork() and join() command to
             * retrieve the data. The fork() method instructs the fork/join framework to complete the task in a
             * separate thread, while the join() method causes the current thread to wait for the results.
             */
            otherTask.fork();

            /*
             * Compute the [middle, end] range using the current thread (since we already have one available) and
             * the [start, middle] range with a separate thread. Finally, we combine the results, waiting for the
             * other task to complete.
             */
            return new WeighAnimalTask(weights, middle, end).compute() + otherTask.join();
        }
    }
}
