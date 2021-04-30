package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s5_synchronizers;

import de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.utils.DataLoadException;
import de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.utils.ExceptionUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Using FutureTask to preload data that is needed later.
 */
public class Preloader {

    private final FutureTask<ProductInfo> future = new FutureTask<>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }
    });

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException) {
                throw (DataLoadException) cause;
            } else {
                throw ExceptionUtils.launderThrowable(cause);
            }
        }
    }

    private ProductInfo loadProductInfo() throws DataLoadException {
        // ...
        return new ProductInfo();
    }
}

class ProductInfo {
    // ...
}
