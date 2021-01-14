package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec05_parallel_streams;

import java.util.ArrayList;
import java.util.List;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class WhaleDataCalculator {

    public static void main(String[] args) {
        WhaleDataCalculator calculator = new WhaleDataCalculator();
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 4000; i++) {
            data.add(i);
        }
        long start = System.currentTimeMillis();
        calculator.processAllData(data);
        double time = (System.currentTimeMillis() - start);

        println("\nTasks completed in " + time + " milliseconds");
    }

    public int processRecord(int input) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // shouldn't happen
        }
        return input + 1;
    }

    public void processAllData(List<Integer> data) {
        // data.stream().map(a -> processRecord(a)).count();
        data.parallelStream().map(a -> processRecord(a)).count();
    }
}
