package de.bre.threadpool;

import java.util.concurrent.BlockingQueue;

/**
 * Created by nils on 21.02.13.
 */
class PoolThread extends Thread {

  private BlockingQueue<Runnable> taskQueue = null;
  private boolean isStopped = false;

  public PoolThread(BlockingQueue<Runnable> queue) {
    taskQueue = queue;
  }

  @Override
  public void run() {
    while (!isStopped()) {
      try {
        Runnable runnable = taskQueue.take();
        runnable.run();
      } catch (InterruptedException e) {
        //erwünschtes Verhalten, wenn ThreadPoolExecutor gestoppt wird
      } catch (Exception e) {
        e.printStackTrace();
        System.exit(0);
      }
    }
  }

  public synchronized void halt() {
    isStopped = true;
    this.interrupt(); //break pool thread out of dequeue() call.
  }

  private synchronized boolean isStopped() {
    return isStopped;
  }
}
