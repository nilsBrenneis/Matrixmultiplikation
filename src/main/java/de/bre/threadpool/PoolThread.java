package de.bre.threadpool;

import java.util.concurrent.BlockingQueue;

class PoolThread extends Thread {

  private BlockingQueue<Runnable> taskQueue;
  private boolean isStopped = false;

  PoolThread(BlockingQueue<Runnable> queue) {
    taskQueue = queue;
  }

  @Override
  public void run() {
    while (!isStopped()) {
      try {
        Runnable runnable = taskQueue.take();
        runnable.run();
      } catch (InterruptedException e) {
        //desireable behavior when ThreadPoolExecutor gets stopped
      } catch (Exception e) {
        e.printStackTrace();
        System.exit(0);
      }
    }
  }

  synchronized void halt() {
    isStopped = true;
    this.interrupt(); //break pool thread out of dequeue() call.
  }

  private synchronized boolean isStopped() {
    return isStopped;
  }
}
