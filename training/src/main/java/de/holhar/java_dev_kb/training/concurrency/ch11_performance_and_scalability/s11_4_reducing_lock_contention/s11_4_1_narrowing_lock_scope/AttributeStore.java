package de.holhar.java_dev_kb.training.concurrency.ch11_performance_and_scalability.s11_4_reducing_lock_contention.s11_4_1_narrowing_lock_scope;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Holding a lock longer than necessary.
 */
@ThreadSafe
public class AttributeStore {

  @GuardedBy("this")
  private final Map<String, String> attributes = new HashMap<>();

  public synchronized boolean userLocationMatches(String name, String regexp) {
    String key = "users." + name + ".location";
    String location = attributes.get(key);
    if (location == null) {
      return false;
    } else {
      return Pattern.matches(regexp, location);
    }
  }
}
