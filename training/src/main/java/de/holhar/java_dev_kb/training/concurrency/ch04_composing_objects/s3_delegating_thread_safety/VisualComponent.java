package de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s3_delegating_thread_safety;

import de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec01_oca_concepts_review.subsec01_access_modifiers.mouse.Mouse;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Delegating thread safety to multiple underlying state variables.
 */
public class VisualComponent {

    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

    public void addKeyListener(KeyListener listener) {
        keyListeners.add(listener);
    }

    public void addMouseListener(MouseListener listener) {
        mouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener) {
        keyListeners.remove(listener);
    }

    public void removeMouseListener(MouseListener listener) {
        mouseListeners.remove(listener);
    }
}
