package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s3_blocking_queues;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * Starting the desktop search.
 */
public class CrawlerApplication {

    private static final int BOUND = 1000;
    private static final int N_CONSUMERS = 10;

    public static void main(String[] args) {
        // input validation etc...
        final List<File> roots = Arrays.stream(args).map(File::new).collect(Collectors.toList());
        startIndexing(roots);
    }

    private static void startIndexing(List<File> roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
        final FileFilter filter = (File file) -> true;

        for (File root : roots) {
            new Thread(new FileCrawler(queue, filter, root)).start();
        }

        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}
