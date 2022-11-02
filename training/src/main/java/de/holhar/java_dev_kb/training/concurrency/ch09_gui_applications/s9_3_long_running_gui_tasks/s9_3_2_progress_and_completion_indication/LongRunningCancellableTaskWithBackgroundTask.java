package de.holhar.java_dev_kb.training.concurrency.ch09_gui_applications.s9_3_long_running_gui_tasks.s9_3_2_progress_and_completion_indication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JButton;
import net.bytebuddy.implementation.bytecode.Throw;

/**
 * Initiating a long-running, cancellable task with BackgroundTask.
 */
public class LongRunningCancellableTaskWithBackgroundTask {

  ExecutorService backgroundExec = Executors.newCachedThreadPool();

  public void doSomething() {
    final JButton startButton = new JButton("do big computation");
    final JButton cancelButton = new JButton("cancel");

    // ...
    startButton.addActionListener(e -> {

      startButton.setEnabled(false);
      startButton.setText("busy");

      class CancelListener implements ActionListener {
        BackgroundTask<?> task;

        public void actionPerformed(ActionEvent event) {
          if (task != null) {
            task.cancel(true);
          }
        }
      }

      final CancelListener listener = new CancelListener();
      listener.task = new BackgroundTask<Void>() {

        protected Void compute() {
          while (moreWork() && !isCancelled()) {
            doSomeWork();
          }
          return null;
        }

        public void onCompletion(boolean cancelled, String s, Throwable exception) {
          cancelButton.removeActionListener(listener);
          startButton.setText("done");
        }
      };

      cancelButton.addActionListener(listener);
      backgroundExec.execute(listener.task);
    });
  }

  private boolean moreWork() {
    // ...
    return false;
  }

  private void doSomeWork() {
    // ...
  }
}
