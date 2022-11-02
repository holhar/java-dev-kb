package de.holhar.java_dev_kb.training.concurrency.ch09_gui_applications.s9_1_guis_are_single_threaded.s9_1_2_thread_confinement_in_swing;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executor build atop SwingUtilities.
 */
public class GuiExecutor extends AbstractExecutorService {

  // Singletons have a private constructor and a public factory
  private static final GuiExecutor instance = new GuiExecutor();

  private GuiExecutor() { }

  public static GuiExecutor getInstance() {
    return instance;
  }

  @Override
  public void execute(Runnable r) {
    if (SwingUtilities.isEventDispatchThread()) {
      r.run();
    } else {
      SwingUtilities.invokeLater(r);
    }
  }

  @Override
  public void shutdown() {
    // ...
  }

  @Override
  public List<Runnable> shutdownNow() {
    // ...
    return null;
  }

  @Override
  public boolean isShutdown() {
    // ...
    return false;
  }

  @Override
  public boolean isTerminated() {
    // ...
    return false;
  }

  @Override
  public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
    // ...
    return false;
  }
}
