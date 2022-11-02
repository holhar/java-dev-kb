package de.holhar.java_dev_kb.training.concurrency.ch09_gui_applications.s9_3_long_running_gui_tasks;

import de.holhar.java_dev_kb.training.concurrency.ch09_gui_applications.s9_1_guis_are_single_threaded.s9_1_2_thread_confinement_in_swing.GuiExecutor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;

/**
 * Long-running task with user feedback.
 */
public class LongRunningTaskWithUserFeedback {

  ExecutorService backgroundExec = Executors.newCachedThreadPool();

  public void doSomething() {
    final JButton button = new JButton("do big computation");

    // ...
    button.addActionListener(e -> {
      button.setEnabled(false);
      button.setText("busy");

      backgroundExec.execute(() -> {
        try {
          doBigComputation();
        } finally {
          GuiExecutor.getInstance().execute(() -> {
            button.setEnabled(true);
            button.setText("idle");
          });
        }
      });
    });
  }

  private void doBigComputation() {
    // ...
  }
}
