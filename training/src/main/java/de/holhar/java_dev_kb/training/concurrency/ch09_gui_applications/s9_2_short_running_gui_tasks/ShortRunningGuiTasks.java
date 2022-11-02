package de.holhar.java_dev_kb.training.concurrency.ch09_gui_applications.s9_2_short_running_gui_tasks;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JButton;

/**
 * Simple event listener.
 */
public class ShortRunningGuiTasks {

  public void doSomething() {
    final Random random = new Random();
    final JButton button = new JButton("Change color");

    // ...

    button.addActionListener((ActionEvent e) -> {
      button.setBackground(new Color(random.nextInt()));
    });
  }
}
