package de.holhar.java_dev_kb.training.concurrency.ch11_performance_and_scalability.s11_4_reducing_lock_contention.s11_4_2_reducing_lock_granularity;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;
import java.util.Set;

/**
 * Candidate for lock splitting.
 */
@ThreadSafe
public class ServerStatus {

  @GuardedBy("this")
  public final Set<String> users;

  @GuardedBy("this")
  public final Set<String> queries;

  public ServerStatus(Set<String> users, Set<String> queries) {
    this.users = users;
    this.queries = queries;
  }

  public synchronized void addUser(String u) {
    users.add(u);
  }

  public synchronized void addQuery(String q) {
    queries.add(q);
  }

  public synchronized void removeUser(String u) {
    users.remove(u);
  }

  public synchronized void removeQuery(String q) {
    queries.remove(q);
  }
}
