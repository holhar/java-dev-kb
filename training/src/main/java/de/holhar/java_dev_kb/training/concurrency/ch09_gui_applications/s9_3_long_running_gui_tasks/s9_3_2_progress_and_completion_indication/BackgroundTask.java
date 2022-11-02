package de.holhar.java_dev_kb.training.concurrency.ch09_gui_applications.s9_3_long_running_gui_tasks.s9_3_2_progress_and_completion_indication;

import de.holhar.java_dev_kb.training.concurrency.ch09_gui_applications.s9_1_guis_are_single_threaded.s9_1_2_thread_confinement_in_swing.GuiExecutor;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Background task class supporting cancellation, completion, notification, and progress
 * notification.
 */
public abstract class BackgroundTask<V> implements Runnable, Future<V> {

  private final FutureTask<V> computation = new Computation();

  private class Computation extends FutureTask<V> {

    public Computation() {
      super(BackgroundTask.this::compute);
    }

    protected final void done() {
      GuiExecutor.getInstance().execute(() -> {
        V value = null;
        Throwable thrown = null;
        boolean cancelled = false;
        try {
          value = get();
        } catch (ExecutionException e) {
          thrown = e.getCause();
        } catch (CancellationException e) {
          cancelled = true;
        } catch (InterruptedException consumed ) {
        } finally {
          onCompletion(value, thrown, cancelled);
        }
      });
    }
  }

  protected void setProgress(final int current, final int max) {
    GuiExecutor.getInstance().execute(() -> onProgress(current, max));
  }

  // Called in the background thread
  protected abstract V compute() throws Exception;

  // Called in the event thread
  protected void onCompletion(V result, Throwable exception, boolean cancelled) {
    // ...
  }

  protected void onProgress(int current, int max) {
    // ...
  }

  // Other Future methods forwarded to computation

  @Override
  public void run() {

  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return false;
  }

  @Override
  public boolean isCancelled() {
    return false;
  }

  @Override
  public boolean isDone() {
    return false;
  }

  @Override
  public V get() throws InterruptedException, ExecutionException {
    return null;
  }

  @Override
  public V get(long timeout, TimeUnit unit)
      throws InterruptedException, ExecutionException, TimeoutException {
    return null;
  }
}
