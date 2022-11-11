package de.holhar.java_dev_kb.training.concurrency.ch11_performance_and_scalability.s11_4_reducing_lock_contention.s11_4_2_reducing_lock_granularity;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;
import java.util.Set;

/**
 * ServerStatus refactored to use split locks.
 */
@ThreadSafe
public class BetterServerStatus {

  @GuardedBy("users")
  public final Set<String> users;

  @GuardedBy("queries")
  public final Set<String> queries;

  public BetterServerStatus(Set<String> users, Set<String> queries) {
    this.users = users;
    this.queries = queries;
  }

  public void addUser(String u) {
    synchronized (users) {
      users.add(u);
    }
  }

  public void addQuery(String q) {
    synchronized (users) {
      queries.add(q);
    }
  }

  public void removeUser(String u) {
    synchronized (queries) {
      users.remove(u);
    }
  }

  public void removeQuery(String q) {
    synchronized (queries) {
      queries.remove(q);
    }
  }
}
