package de.holhar.java_dev_kb.training.concurrency.ch09_gui_applications.s9_3_long_running_gui_tasks;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JButton;

/**
 * Cancelling a long-running task.
 */
public class LongRunningTaskWithUserFeedbackAndCancellation {

  ExecutorService backgroundExec = Executors.newCachedThreadPool();

  public void doSomething() {
    final JButton startButton = new JButton("do big computation");
    final JButton cancelButton = new JButton("cancel");

    Future runningTask = null; // thread-confined
    List<Future> taskList = Collections.singletonList(runningTask); // ugly, I know

    // ...
    startButton.addActionListener(e -> {
      startButton.setEnabled(false);
      startButton.setText("busy");

      backgroundExec.submit(() -> {
        if (taskList.get(0) == null) {
          Future future = taskList.get(0);
          future = backgroundExec.submit(() -> {
            while (moreWork()) {
              if (Thread.currentThread().isInterrupted()) {
                cleanUpPartialWork();
              }
              doSomeWork();
            }
          });
        }
      });
    });

    cancelButton.addActionListener((e -> {
      if (taskList.get(0) != null) {
        taskList.get(0).cancel(true);
      }
    }));
  }

  private void cleanUpPartialWork() {
  }

  private boolean moreWork() {
    // ...
    return false;
  }

  private void doSomeWork() {
    // ...
  }
}
