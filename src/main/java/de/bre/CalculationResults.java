package de.bre;

import de.bre.datatypes.Result;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class CalculationResults {

  private final int INIT_PERMITS = 0;
  private final boolean FAIR = true;
  private Semaphore sem = new Semaphore(INIT_PERMITS, FAIR);

  private final int countResultsExpected;

  private ConcurrentLinkedQueue<Result> resultsList = new ConcurrentLinkedQueue<>();

  public CalculationResults(int countResultsExpected) {
    this.countResultsExpected = countResultsExpected;
  }

  public void addResult(Result r) {
    resultsList.add(r);
    sem.release(1);
  }

  public Result[] waitForResults() throws InterruptedException {
    sem.acquire(countResultsExpected);
    System.out.println("Anzahl Ergebnisse: " + resultsList.size());
    return resultsList.toArray(new Result[0]);
  }
}
