package de.holhar.java_dev_kb.training.concurrency.ch09_gui_applications.s9_3_long_running_gui_tasks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;

/**
 * Building a long-running task to a visual component.
 */
public class LongRunningGuiTasks {

  ExecutorService backgroundExec = Executors.newCachedThreadPool();

  public void doSomething() {
    final JButton button = new JButton();

    // ...
    button.addActionListener(e-> backgroundExec.execute(this::doBigComputation));
  }

  private void doBigComputation() {
    // ...
  }
}
