package de.bre.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by nils on 21.02.13.
 */
public class MyThreadPoolExecutor {

  private BlockingQueue<Runnable> taskQueue = null;
  private final List<PoolThread> threads = new ArrayList<>();
  private boolean isStopped = false;

  public MyThreadPoolExecutor(int noOfThreads, int maxNoOfTasks) {
    taskQueue = new ArrayBlockingQueue(maxNoOfTasks);

    for (int i = 0; i < noOfThreads; i++) {
      threads.add(new PoolThread(taskQueue));
    }
    for (PoolThread thread : threads) {
      thread.start();
    }
  }

  public synchronized void execute(Runnable task) throws InterruptedException {
    if (this.isStopped) {
      throw new IllegalStateException("ThreadPool is stopped");
    }

    taskQueue.put(task);
  }

  public synchronized void stop() {
    this.isStopped = true;
    for (PoolThread thread : threads) {
      thread.halt();
    }
  }
}
