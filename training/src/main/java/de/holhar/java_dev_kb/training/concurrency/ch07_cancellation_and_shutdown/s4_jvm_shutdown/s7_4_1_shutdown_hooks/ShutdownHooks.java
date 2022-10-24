package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s4_jvm_shutdown.s7_4_1_shutdown_hooks;

public class ShutdownHooks {

  public void start() {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        ShutdownHooks.this.stop();
      } catch (InterruptedException ignored) {
      }
    }));
  }

  private void stop() throws InterruptedException {
    // Stop the service...
  }
}
